package com.example.muvime.model;

public class Crew {
    private int id;
    private String name;
    private String job;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Crew(int id, String name, String job) {
        this.id = id;
        this.name = name;
        this.job = job;
    }

    public Crew(){}
}
