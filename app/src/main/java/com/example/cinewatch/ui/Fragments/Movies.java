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
import com.example.cinewatch.databinding.MoviesBinding;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.HashMap;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class Movies extends Fragment {
    private MoviesBinding binding;
    private HomeViewModel viewModel;
    private Integer movieId;
    private HashMap<String, String> queryMap;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MoviesBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<MovieListResult>() {
            @Override
            public void onChanged(MovieListResult movieListResult) {

            }
        });
        queryMap.put("api_key", Constants.API_KEY);
        queryMap.put("page","1");

        viewModel.getMovieDetails(movieId,queryMap);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
