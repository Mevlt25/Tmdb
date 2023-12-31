package com.example.muvime.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.muvime.model.Cast;
import com.example.muvime.R;
import com.example.muvime.view.CastFragment;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    private List<Cast> castList;
    private Context context;

    public CastAdapter(List<Cast> castList, Context context) {
        this.castList = castList;
        this.context = context;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_card, parent, false);
        return new CastViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = castList.get(position);
        String image_link = "https://image.tmdb.org/t/p/original" + cast.getProfile_path();

        Glide.with(context).load(image_link).into(holder.imageView);
        holder.nameTextView.setText(cast.getName());
        holder.characterTextView.setText(cast.getCharacter());
        holder.episodeTextView.setText("");

        holder.imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager(); // Eğer fragment içindeyseniz getActivity() kullanın
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                CastFragment castFragment = new CastFragment(); // Diğer fragmentın sınıfını kullanın
                Bundle bundle = new Bundle();
                bundle.putInt("id", cast.getId());
                castFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.main_frame_layout, castFragment);
                fragmentTransaction.addToBackStack(null); // Geri düğmesine basıldığında önceki fragmenta dönebilmek için geri yığın ekleyin
                fragmentTransaction.commit();

                //Intent intent = new Intent(context, CastActivity.class);
                //intent.putExtra("id", cast.getId());
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public static class CastViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nameTextView;
        public TextView characterTextView;
        public TextView episodeTextView;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cast_image);
            nameTextView = itemView.findViewById(R.id.cast_name);
            characterTextView = itemView.findViewById(R.id.cast_character);
            episodeTextView = itemView.findViewById(R.id.cast_episode);
        }
    }
}
