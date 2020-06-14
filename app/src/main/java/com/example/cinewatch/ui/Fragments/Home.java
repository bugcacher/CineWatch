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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.HomeMoviesAdapter;
import com.example.cinewatch.adapters.ViewPagerAdapter;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.model.Movie;
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
    private ArrayList<Movie> currentMovies,popularMovies,topRatedMovies,upcomingMovies;
    private HomeMoviesAdapter upcomingAdapter,popularAdapter,topRatedAdapter;
    private ViewPagerAdapter currentlyShowingAdapter;
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
        setUpRecyclerViewsAndViewPager();

        setUpOnclick();


    }

    private void setUpOnclick() {

        binding.viewAllCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovies action = HomeDirections.actionHome3ToMovies();
                action.setMovieCategory(Constants.Current);
                Navigation.findNavController(view).navigate(action);
            }
        });
        binding.viewAllTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovies action = HomeDirections.actionHome3ToMovies();
                action.setMovieCategory(Constants.TopRated);
                Navigation.findNavController(view).navigate(action);
            }
        });
        binding.viewAllPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovies action = HomeDirections.actionHome3ToMovies();
                action.setMovieCategory(Constants.Popular);
                Navigation.findNavController(view).navigate(action);
            }
        });
        binding.viewAllUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovies action = HomeDirections.actionHome3ToMovies();
                action.setMovieCategory(Constants.Upcoming);
                Navigation.findNavController(view).navigate(action);
            }
        });
        binding.searchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToSearchMovies action =
                        HomeDirections.actionHome3ToSearchMovies(binding.searchKeyword.getText().toString()
                                .toLowerCase().trim());
                Navigation.findNavController(view).navigate(action);
            }
        });

        binding.filterSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(HomeDirections.actionHome3ToWishListFrag());
            }
        });
    }

    private void setUpRecyclerViewsAndViewPager() {

        currentlyShowingAdapter = new ViewPagerAdapter(getContext(),currentMovies);
        binding.currentlyShowingViewPager.setAdapter(currentlyShowingAdapter);


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

        viewModel.getCurrentlyShowingList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                currentlyShowingAdapter.setMovieListResults(movies);
            }
        });
        viewModel.getUpcomingMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                upcomingAdapter.setMoviesList(movies);
            }
        });
        viewModel.getTopRatedMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                topRatedAdapter.setMoviesList(movies);

            }
        });
        viewModel.getPopularMoviesList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                popularAdapter.setMoviesList(movies);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
