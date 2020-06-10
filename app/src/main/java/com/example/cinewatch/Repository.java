package com.example.cinewatch;

import android.util.Log;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.model.MovieResponse;
import com.example.cinewatch.network.MovieApiService;

import java.util.ArrayList;
import java.util.HashMap;

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

    private ArrayList<MovieListResult> moviesList;

    public Repository() {

    }

    public Observable<MovieResponse>  getCurrentlyShowing(HashMap<String, String> map){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseURL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MovieApiService service = retrofit.create(MovieApiService.class);

        return service.getCurrentlyShowing(map);
    }
}
