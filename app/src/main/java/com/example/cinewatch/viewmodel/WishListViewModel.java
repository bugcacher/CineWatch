package com.example.cinewatch.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.cinewatch.db.WishListMovie;
import com.example.cinewatch.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Abhinav Singh on 12,June,2020
 */

public class WishListViewModel extends ViewModel {
    private static final String TAG = "WishListViewModel";
    private Repository repository;
    private LiveData<List<WishListMovie>> wishListMoviesList;

    @ViewModelInject
    public WishListViewModel(Repository repository) {
        this.repository = repository;
        wishListMoviesList = repository.getWishList();
    }

    public LiveData<List<WishListMovie>> getWishListMoviesList() {
        return wishListMoviesList;
    }

    public void clearWishList(){
        repository.clearWishList();
    }
}
