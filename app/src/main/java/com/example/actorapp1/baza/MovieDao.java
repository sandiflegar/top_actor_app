package com.example.actorapp1.baza;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movie")
    List<Movie> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movies);

    @Update
    void update(Movie movies);
}
