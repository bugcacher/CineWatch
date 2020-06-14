package com.example.cinewatch.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Abhinav Singh on 14,June,2020
 */

@Dao
public interface WishListDao {

    @Insert
    void insert(WishListMovie wishListMovie);

    @Delete
    void delete(WishListMovie wishListMovie);

    @Query("SELECT * FROM wishlist_table")
    LiveData<List<WishListMovie>> getWishList();
}
