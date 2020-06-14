package com.example.cinewatch.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinewatch.adapters.WishListAdapter;
import com.example.cinewatch.databinding.WishlistMoviesBinding;
import com.example.cinewatch.db.WishListMovie;
import com.example.cinewatch.viewmodel.WishListViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@AndroidEntryPoint
public class WishListFragment extends Fragment {
    private static final String TAG = "WishListFragment";
    
    private WishListViewModel viewModel;
    private WishlistMoviesBinding binding;
    private WishListAdapter adapter;
    private List<WishListMovie> moviesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WishlistMoviesBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return (view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(WishListFragment.this).get(WishListViewModel.class);
        
        intiRecyclerView();
        observeData();
        //viewModel.getWishListMovies();
        
    }

    private void observeData() {
        viewModel.getWishListMoviesList().observe(getViewLifecycleOwner(), new Observer<List<WishListMovie>>() {
            @Override
            public void onChanged(List<WishListMovie> wishListMovies) {
                for(int i = 0 ;i < wishListMovies.size();i++){
                    Log.e(TAG, "onChanged: " + wishListMovies.get(i).getTitle() );
                    Log.e(TAG, "onChanged: " + wishListMovies.get(i).getOverview() );
                    Log.e(TAG, "onChanged: " + wishListMovies.get(i).getVote_count() );
                    Log.e(TAG, "onChanged: " + wishListMovies.get(i).getId() );
                }
                adapter.setMoviesList(wishListMovies);
            }
        });

    }

    private void intiRecyclerView() {
        binding.wishListRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter  = new WishListAdapter(getContext(),moviesList);
        binding.wishListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
