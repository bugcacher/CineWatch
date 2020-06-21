package com.example.cinewatch.ui.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.CategoryMoviesAdapter;
import com.example.cinewatch.databinding.SearchMoviesBinding;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
@AndroidEntryPoint
public class SearchMovies extends Fragment {
    private SearchMoviesBinding binding;
    private HomeViewModel viewModel;
    private HashMap<String, String> queryMap;
    private CategoryMoviesAdapter adapter;
    private ArrayList<Movie> moviesList;
    private String queryText = "";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SearchMoviesBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchMoviesArgs args = SearchMoviesArgs.fromBundle(getArguments());
        queryText = args.getQuery();
        binding.searchKeyword.setText(queryText);

        queryMap = new HashMap<>();
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        queryMap.put("api_key", Constants.API_KEY);
        queryMap.put("query",queryText);

        initRecyclerView();
        observeData();
        viewModel.getQueriedMovies(queryMap);

        binding.searchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                queryText = binding.searchKeyword.getText().toString().trim().toLowerCase();
                queryMap.clear();
                queryMap.put("api_key", Constants.API_KEY);
                queryMap.put("query",queryText);

                viewModel.getQueriedMovies(queryMap);
            }
        });
        binding.searchKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    queryText = binding.searchKeyword.getText().toString().trim().toLowerCase();
                    queryMap.clear();
                    queryMap.put("api_key", Constants.API_KEY);
                    queryMap.put("query",queryText);

                    viewModel.getQueriedMovies(queryMap);
                }
                return false;
            }
        });
    }

    private void initRecyclerView() {
        binding.searchMoviesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
        adapter = new CategoryMoviesAdapter(getContext(),moviesList);
        binding.searchMoviesRecyclerView.setAdapter(adapter);
    }

    private void observeData() {
        viewModel.getQueriesMovies().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                adapter.setList(movies);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
