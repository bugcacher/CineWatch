package com.example.cinewatch.ui.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.HomeMoviesAdapter;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */

@AndroidEntryPoint
public class Home extends Fragment {

    private static final String TAG = "Home";
    private HomeViewModel viewModel;
    private HomeLayoutBinding binding;
    private ArrayList<MovieListResult> currentMovies,popularMovies,topRatedMovies,upcomingMovies;
    private HomeMoviesAdapter upcomingAdapter,popularAdapter,topRatedAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeLayoutBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentMovies = new ArrayList<>();
        currentMovies = new ArrayList<>();
        currentMovies = new ArrayList<>();
        currentMovies = new ArrayList<>();

        viewModel = new ViewModelProvider(Home.this).get(HomeViewModel.class);

        observeData();

        HashMap<String,String> map = new HashMap<>();
        map.put("api_key",Constants.API_KEY);
        map.put("page","1");

        viewModel.getCurrentlyShowingMovies(map);
        viewModel.getUpcomingMovies(map);
        viewModel.getTopRatedMovies(map);
        viewModel.getPopularMovies(map);

        setUpRecyclerViews();

//        Call<MovieListResult> call= service.getMovieDetails(496243,map);
//        call.enqueue(new Callback<MovieListResult>() {
//            @Override
//            public void onResponse(Call<MovieListResult> call, Response<MovieListResult> response) {
//                MovieListResult listResult = response.body();
//                Log.e(TAG, "onResponse: " + listResult.getTitle() + listResult.getGenres().get(0).getName() );
//            }
//
//            @Override
//            public void onFailure(Call<MovieListResult> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage() );
//            }
//        });
//
//        Call<JsonObject> call1= service.getCast(496243,map);
//        call1.enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//
//                if(response.isSuccessful()){
//                    JsonArray jsonArray = response.body().getAsJsonArray("cast");
//                    ArrayList<Cast> castList = new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<Cast>>(){}.getType());
//                    if(castList != null)
//                        Log.e(TAG, "onResponse: element 1  " +castList.get(0).getName());
//                    else
//                        Log.e(TAG, "onResponse: element 1  " + " null ");
//                }
//                else
//                    Log.e(TAG, "onResponse: "  + "failed" );
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                Log.e(TAG, "\nonFailure: " + t.getMessage() );
//            }
//        });

//        Call<Actor> call2= service.getActorDetails(20738,Constants.API_KEY);
//        call2.enqueue(new Callback<Actor>() {
//            @Override
//            public void onResponse(Call<Actor> call, Response<Actor> response) {
//                //Log.e(TAG, "onResponse: " + "request body " + call.request().body().toString() );
//                if(response.isSuccessful()){
//                    Actor actor = response.body();
//                    Log.e(TAG, "\nonResponse: yes " + actor.getName() + actor.getId());
//                }
//                else
//                    Log.e(TAG, "\nonResponse: "  + "failed" + response.raw() + response.errorBody().toString()
//                    + response.message());
//            }
//
//            @Override
//            public void onFailure(Call<Actor> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage() );
//            }
//        });


    }

    private void setUpRecyclerViews() {
        binding.upcomingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        upcomingAdapter = new HomeMoviesAdapter(getContext(),upcomingMovies);
        binding.upcomingRecyclerView.setAdapter(upcomingAdapter);

        binding.topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        topRatedAdapter = new HomeMoviesAdapter(getContext(),topRatedMovies);
        binding.topRatedRecyclerView.setAdapter(topRatedAdapter);

        binding.popularRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        popularAdapter = new HomeMoviesAdapter(getContext(),popularMovies);
        binding.popularRecyclerView.setAdapter(popularAdapter);
    }

    private void observeData() {

        viewModel.getCurrentlyShowingList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieListResult>>() {
            @Override
            public void onChanged(ArrayList<MovieListResult> movieListResults) {
                Log.e(TAG, "onChanged: " + movieListResults.get(0).getTitle() );
            }
        });
        viewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieListResult>>() {
            @Override
            public void onChanged(ArrayList<MovieListResult> movieListResults) {
                Log.e(TAG, "onChanged: " + movieListResults.get(0).getTitle() );
                upcomingAdapter.setMoviesList(movieListResults);
            }
        });
        viewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieListResult>>() {
            @Override
            public void onChanged(ArrayList<MovieListResult> movieListResults) {
                Log.e(TAG, "onChanged: " + movieListResults.get(0).getTitle() );
                topRatedAdapter.setMoviesList(movieListResults);

            }
        });
        viewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<MovieListResult>>() {
            @Override
            public void onChanged(ArrayList<MovieListResult> movieListResults) {
                Log.e(TAG, "onChanged: " + movieListResults.get(0).getTitle() );
                popularAdapter.setMoviesList(movieListResults);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
