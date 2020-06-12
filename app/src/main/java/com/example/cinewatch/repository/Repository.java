package com.example.cinewatch.repository;

import com.example.cinewatch.db.MovieDao;
import com.example.cinewatch.db.MovieEntity;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.model.MovieResponse;
import com.example.cinewatch.network.MovieApiService;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by Abhinav Singh on 10,June,2020
 */
public class Repository {
    private static final String TAG = "Repository";

    MovieApiService apiService;
    MovieDao movieDao;


    @Inject
    public Repository(MovieApiService apiService, MovieDao movieDao) {
        this.apiService = apiService;
        this.movieDao = movieDao;
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
    public Observable<Actor>  getActorDetails(int personId, String api_key){
        return apiService.getActorDetails(personId,api_key);
    }

    public Observable<JsonObject> getMoviesBySearch( HashMap<String, String> map){
        return apiService.getMoviesBySearch(map);

    }

    public void insertMovie(MovieEntity movieEntity){
        movieDao.insert(movieEntity);
    }

    public void deleteMovie(MovieEntity movieEntity){
        movieDao.delete(movieEntity);
    }

    public void deleteAll(){
        movieDao.deleteAll();
    }

    public ArrayList<MovieEntity> getWishList(){
        return  movieDao.getWishList();
    }




}
