package com.example.cinewatch.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinewatch.adapters.WishListAdapter;
import com.example.cinewatch.databinding.WishlistMoviesBinding;
import com.example.cinewatch.db.MovieEntity;
import com.example.cinewatch.viewmodel.WishListViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@AndroidEntryPoint
public class WishListFragment extends Fragment {
    
    private WishListViewModel viewModel;
    private WishlistMoviesBinding binding;
    private WishListAdapter adapter;
    private ArrayList<MovieEntity> moviesList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = WishlistMoviesBinding.inflate(inflater,container,false);
        return (binding.getRoot());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(WishListFragment.this).get(WishListViewModel.class);
        moviesList = new ArrayList<>();
        
        intiRecyclerView();
        observeData();
        viewModel.getWishListMovies();
        
    }

    private void observeData() {
        adapter.setMoviesList(moviesList);
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
