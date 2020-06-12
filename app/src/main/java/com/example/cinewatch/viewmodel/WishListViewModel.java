package com.example.cinewatch.viewmodel;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinewatch.db.MovieEntity;
import com.example.cinewatch.repository.Repository;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 12,June,2020
 */

public class WishListViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<ArrayList<MovieEntity>> wishListMoviesList = new MutableLiveData<>();

    @ViewModelInject
    public WishListViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<MovieEntity>> getWishListMoviesList() {
        return wishListMoviesList;
    }

    public void getWishListMovies(){
        wishListMoviesList.setValue(repository.getWishList());
    }
}
