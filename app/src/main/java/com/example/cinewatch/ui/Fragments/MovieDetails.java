package com.example.cinewatch.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.databinding.MovieDetailsBinding;
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
public class MovieDetails extends Fragment {
    private MovieDetailsBinding binding;
    private HomeViewModel viewModel;
    private Integer movieId;
    private HashMap<String, String> queryMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MovieDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(MovieDetails.this).get(HomeViewModel.class);

        observeData();

        queryMap.put("api_key", Constants.API_KEY);
        queryMap.put("page","1");

        viewModel.getMovieDetails(movieId,queryMap);
        viewModel.getCast(movieId,queryMap);


    }

    private void observeData() {
        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {

            }
        });

        viewModel.getMovieCastList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Actor>>() {
            @Override
            public void onChanged(ArrayList<Actor> actors) {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
