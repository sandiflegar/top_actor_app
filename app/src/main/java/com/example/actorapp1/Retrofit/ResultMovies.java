package com.example.actorapp1.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

 public class ResultMovies {
    @SerializedName("results")
    public ArrayList<Movies> movies;

}
