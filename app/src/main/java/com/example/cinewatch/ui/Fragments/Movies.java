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
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.CategoryMoviesAdapter;
import com.example.cinewatch.databinding.MoviesBinding;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
@AndroidEntryPoint
public class Movies extends Fragment {
    private MoviesBinding binding;
    private HomeViewModel viewModel;
    private HashMap<String, String> queryMap;
    private String moviesCategory="";
    private CategoryMoviesAdapter adapter;
    private ArrayList<Movie> moviesList;


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
        queryMap = new HashMap<>();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        MoviesArgs args = MoviesArgs.fromBundle(getArguments());
        moviesCategory = args.getMovieCategory();

        queryMap.put("api_key", Constants.API_KEY);
        queryMap.put("page","1");

        initRecyclerView();
        observeData();
        getMoviesList();




    }

    private void initRecyclerView() {
        binding.moviesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new CategoryMoviesAdapter(getContext(),moviesList);
        binding.moviesRecyclerView.setAdapter(adapter);
    }

    private void observeData() {
        switch (moviesCategory){
            case Constants.Current:
                viewModel.getCurrentlyShowingList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        adapter.setList(movies);
                    }
                });
                break;
            case Constants.Upcoming:
                viewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        adapter.setList(movies);
                    }
                });
                break;
            case Constants.TopRated:
                viewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        adapter.setList(movies);
                    }
                });
                break;
            case Constants.Popular:
                viewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
                    @Override
                    public void onChanged(ArrayList<Movie> movies) {
                        adapter.setList(movies);
                    }
                });
                break;
        }
    }

    private void getMoviesList() {
            switch (moviesCategory){
                case Constants.Current:
                    viewModel.getCurrentlyShowingMovies(queryMap);
                    break;
                case Constants.Upcoming:
                    viewModel.getUpcomingMovies(queryMap);
                    break;
                case Constants.TopRated:
                    viewModel.getTopRatedMovies(queryMap);
                    break;
                case Constants.Popular:
                    viewModel.getPopularMovies(queryMap);
                    break;
            }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
