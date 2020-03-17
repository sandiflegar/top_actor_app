package com.example.actorapp1.Retrofit;

import com.example.actorapp1.baza.Actor;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResultActors {
    @SerializedName("results")
    public ArrayList<Actors> actors;

}
