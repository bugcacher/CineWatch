package com.example.cinewatch.network;

import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.model.MovieResponse;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public interface MovieApiService {

    @GET("movie/now_playing")
    Observable<MovieResponse> getCurrentlyShowing(@QueryMap HashMap<String,String> queries);

    @GET("movie/popular")
    Observable<List<MovieResponse>> getPopular(@QueryMap HashMap<String,String> queries);

    @GET("movie/upcoming")
    Observable<List<MovieResponse>> getUpcoming(@QueryMap HashMap<String,String> queries);

    @GET("movie/top_rated")
    Observable<List<MovieResponse>> getTopRated(@QueryMap HashMap<String,String> queries);

    @GET("movie/{movie_id}")
    Observable<MovieListResult> getMovieDetails(@Path ("movie_id") int id, @QueryMap HashMap<String,String> queries);

    @GET("movie/{movie_id}/credits")
    Observable<JsonObject> getCast(@Path ("movie_id") int id, @QueryMap HashMap<String,String> queries);

    @GET("person/{person_id}")
    Observable<Actor> getActorDetails(@Path ("person_id") int id, @Query("api_key") String api);




}
