package com.example.cinewatch.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.network.MovieApiInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class Home extends Fragment {
    private static final String TAG = "Home";
    private HomeLayoutBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BaseURL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         MovieApiInterface service = retrofit.create(MovieApiInterface.class);
        HashMap<String,String> map = new HashMap<>();
        map.put("api_key",Constants.API_KEY);
        map.put("page","1");
        Call<MovieListResult> call= service.getMovieDetails(496243,map);
        call.enqueue(new Callback<MovieListResult>() {
            @Override
            public void onResponse(Call<MovieListResult> call, Response<MovieListResult> response) {
                MovieListResult listResult = response.body();
                Log.e(TAG, "onResponse: " + listResult.getTitle() + listResult.getGenres().get(0).getName() );
            }

            @Override
            public void onFailure(Call<MovieListResult> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });

//        Call<JsonObject> call1= service.getCast(496243,map);
//        call1.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                JsonArray jsonArray = response.body().getAsJsonArray("cast");
//                ArrayList<Cast> castList = new Gson().fromJson(jsonArray.toString(),new TypeToken<Cast>(){}.getType());
//                Log.e(TAG, "onResponse: " + castList.get(0).getName() );
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.e(TAG, "\nonFailure: " + t.getMessage() );
//            }
//        });
        Call<Actor> call2= service.getActorDetails(20738,map);
        call2.enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {
                Actor actor = response.body();
                Log.e(TAG, "\nonResponse: " + actor.getName() + actor.getId());
            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
