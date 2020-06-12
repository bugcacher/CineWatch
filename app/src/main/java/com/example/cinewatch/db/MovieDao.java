package com.example.cinewatch.db;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@Dao
public interface MovieDao {

    @Insert
    void insert(MovieEntity movieEntity);

    @Query("DELETE FROM MovieEntity")
    void deleteAll();

    @Delete
    void delete(MovieEntity movieEntity);

    @Query("SELECT * FROM MovieEntity")
    MutableLiveData<ArrayList<MovieEntity>> getWishList();
}
