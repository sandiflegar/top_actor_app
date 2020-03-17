package com.example.actorapp1.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Actors {

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("profile_path")
    private String profile_path;

    public Actors(String name, int id, String profile_path){
        this.name = name;
        this.id = id;
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
