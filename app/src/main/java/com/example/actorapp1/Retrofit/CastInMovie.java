package com.example.actorapp1.Retrofit;

import com.google.gson.annotations.SerializedName;

public class CastInMovie {
    @SerializedName("name")
    private String name;
    @SerializedName("character")
    private String character;
    @SerializedName("cast_id")
    private int cast_id;
    @SerializedName("id")
    private int id;
    @SerializedName("profile_path")
    private String profile_path;

    public CastInMovie(String name, String character, int cast_id, int id, String profile_path) {
        this.name = name;
        this.character = character;
        this.cast_id = cast_id;
        this.id = id;
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public int getCast_id() {
        return cast_id;
    }

    public void setCast_id(int cast_id) {
        this.cast_id = cast_id;
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
