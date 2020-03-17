package com.example.actorapp1.baza;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.actorapp1.R;

@Database(entities = {Movie.class, Actor.class}, version =10, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract ActorDao actorDao();

    public static AppDatabase db;
    private static final String DATABASE_NAME = "top_actor_baza";

    public static AppDatabase getDatabase(final Context context) {
        if(db == null) {
            synchronized (AppDatabase.class) {
                if(db == null) {
                    db = Room.databaseBuilder(context,
                            AppDatabase.class,
                            AppDatabase.DATABASE_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return db;
    }
}
