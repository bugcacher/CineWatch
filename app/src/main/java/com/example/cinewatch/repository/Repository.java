package com.example.cinewatch.repository;

import android.util.Log;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.model.MovieResponse;
import com.example.cinewatch.network.MovieApiService;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav Singh on 10,June,2020
 */
public class Repository {
    private static final String TAG = "Repository";

    MovieApiService apiService;


    @Inject
    public Repository(MovieApiService apiService) {
        this.apiService = apiService;
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

    public Observable<MovieListResult>  getMovieDetails(int movieId,HashMap<String, String> map){
        return apiService.getMovieDetails(movieId, map);
    }

    public Observable<JsonObject>  getCast(int movieId, HashMap<String, String> map){
        return apiService.getCast(movieId,map);
    }
    public Observable<Actor>  getActorDetails(int personId, String api_key){
        return apiService.getActorDetails(personId,api_key);
    }



}
