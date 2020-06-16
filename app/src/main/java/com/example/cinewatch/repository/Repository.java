package com.example.cinewatch.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cinewatch.db.WishListDao;
import com.example.cinewatch.db.WishListMovie;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.model.MovieResponse;
import com.example.cinewatch.network.MovieApiService;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Abhinav Singh on 10,June,2020
 */
public class Repository {
    private static final String TAG = "Repository";

    MovieApiService apiService;
    //MovieDao movieDao;
    WishListDao wishListDao;


    @Inject
    public Repository(MovieApiService apiService, WishListDao wishListDao) {
        this.apiService = apiService;
        this.wishListDao= wishListDao;
    }

    public Observable<MovieResponse>  getCurrentlyShowing(HashMap<String, String> map){
        return apiService.getCurrentlyShowing(map);
    }

    public Observable<MovieResponse>  getPopular(HashMap<String, String> map){
        return apiService.getPopular(map);
    }

    public Observable<MovieResponse>  getTopRated(HashMap<String, String> map){
        return apiService.getTopRated(map);
    }

    public Observable<MovieResponse>  getUpcoming(HashMap<String, String> map){
        return apiService.getUpcoming(map);
    }

    public Observable<Movie>  getMovieDetails(int movieId, HashMap<String, String> map){
        return apiService.getMovieDetails(movieId, map);
    }

    public Observable<JsonObject>  getCast(int movieId, HashMap<String, String> map){
        return apiService.getCast(movieId,map);
    }
    public Observable<Actor>  getActorDetails(int personId,HashMap<String,String> map){
        return apiService.getActorDetails(personId,map);
    }

    public Observable<JsonObject> getMoviesBySearch( HashMap<String, String> map){
        return apiService.getMoviesBySearch(map);

    }

    public void insertMovie(WishListMovie wishListMovie){
        Log.e(TAG, "insertMovie: " );
        wishListDao.insert(wishListMovie);
    }

    public void deleteMovie(int movieId){
        wishListDao.delete(movieId);
    }

    public void clearWishList(){
        wishListDao.clearWishList();
    }

    public LiveData<List<WishListMovie>> getWishList(){
        return  wishListDao.getWishList();
    }

    public WishListMovie getWishListMovie(int movieId){
        return wishListDao.getWishListMovie(movieId);
    }




}
