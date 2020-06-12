package com.example.cinewatch.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@Database(entities = {MovieEntity.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao movieDao();

}
