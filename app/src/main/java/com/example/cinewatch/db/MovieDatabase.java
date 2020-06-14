package com.example.cinewatch.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@Database(entities = {WishListMovie.class}, version = 5,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

   // public abstract MovieDao movieDao();
    public abstract WishListDao wishListDao();

}
