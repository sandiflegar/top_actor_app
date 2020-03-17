package com.example.actorapp1.baza;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Movie {
    @PrimaryKey @NonNull
    public String movieName;

    @ColumnInfo(name = "rating_movie")
    public String ratingMovie;

    @ColumnInfo(name = "year")
    public String year;

    //   @PrimaryKey(autoGenerate = true)
//    public int uid;

    /*public Movie(int uid, String movieName, String ratingMovie, String year) {
        this.uid = uid;
        this.movieName = movieName;
        this.ratingMovie = ratingMovie;
        this.year = year;

    }*/

//    public int getUid() {
//        return uid;
//    }
//
//    public void setUid(int uid) {
//        this.uid = uid;
//    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRatingMovie() {
        return ratingMovie;
    }

    public void setRatingMovie(String ratingMovie) {
        this.ratingMovie = ratingMovie;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
