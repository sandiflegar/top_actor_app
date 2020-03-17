package com.example.actorapp1.baza;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Actor {
   /* @PrimaryKey(autoGenerate = true)
    public int uid;*/

    @PrimaryKey @NonNull
    public String actorName;

    @ColumnInfo(name = "actor_last_name")
    public String actorLastName;

    @ColumnInfo(name = "birth")
    public String birth;

    @ColumnInfo(name = "rating_actor")
    public String ratingActor;

/*
    public int getUid() {
        return uid;
    }
*/

   /* public Actor(int uid, String actorName, String actorLastName, String birth, String ratingActor) {
        this.uid = uid;
        this.actorName = actorName;
        this.actorLastName = actorLastName;
        this.birth = birth;
        this.ratingActor = ratingActor;
    }*/

//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorLastName() {
        return actorLastName;
    }

    public void setActorLastName(String actorLastName) {
        this.actorLastName = actorLastName;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRatingActor() {
        return ratingActor;
    }

    public void setRatingActor(String ratingActor) {
        this.ratingActor = ratingActor;
    }
}
