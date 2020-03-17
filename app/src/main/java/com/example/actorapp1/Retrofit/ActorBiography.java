package com.example.actorapp1.Retrofit;

import com.google.gson.annotations.SerializedName;

public class ActorBiography {
    @SerializedName("id")
    private int id;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("name")
    private String name;
    @SerializedName("biography")
    private String biography;
    @SerializedName("profile_path")
    private String profile_path;

    public ActorBiography(int id, String birthday, String name, String biography, String profile_path) {
        this.id = id;
        this.birthday = birthday;
        this.name = name;
        this.biography = biography;
        this.profile_path = profile_path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
