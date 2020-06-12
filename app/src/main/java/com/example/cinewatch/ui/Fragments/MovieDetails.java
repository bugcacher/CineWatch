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

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.CastAdapter;
import com.example.cinewatch.databinding.MovieDetailsBinding;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
@AndroidEntryPoint
public class MovieDetails extends Fragment {
    private static final String TAG = "MovieDetails";
    private MovieDetailsBinding binding;
    private HomeViewModel viewModel;
    private Integer movieId;
    private HashMap<String, String> queryMap;
    private String temp;
    private CastAdapter adapter;
    private ArrayList<Cast> castList;
    private int hour =0,min = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MovieDetailsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(MovieDetails.this).get(HomeViewModel.class);
        castList = new ArrayList<>();
        queryMap = new HashMap<>();

        MovieDetailsArgs args = MovieDetailsArgs.fromBundle(getArguments());
        movieId = args.getMovieId();


        observeData();

        queryMap.put("api_key", Constants.API_KEY);
        queryMap.put("page","1");

        viewModel.getMovieDetails(movieId,queryMap);
        viewModel.getCast(movieId,queryMap);

        binding.castRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        adapter = new CastAdapter(getContext(),castList);
        binding.castRecyclerView.setAdapter(adapter);
        binding.moviePoster.setClipToOutline(true);



    }

    private void observeData() {
        viewModel.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                Glide.with(getContext()).load(Constants.ImageBaseURL + movie.getPoster_path())
                        .centerCrop()
                        .into(binding.moviePoster);

                binding.movieName.setText(movie.getTitle());

                hour = movie.getRuntime()/60;
                min = movie.getRuntime()%60;
                binding.movieRuntime.setText(hour+"h "+min+"m");
                binding.moviePlot.setText(movie.getOverview());
                temp = "";
                for (int i = 0; i < movie.getGenres().size(); i++){
                    if(i ==  movie.getGenres().size() -1)
                        temp+= movie.getGenres().get(i).getName();
                    else
                        temp+= movie.getGenres().get(i).getName() + " | ";
                }

                binding.movieGenre.setText(temp);
                binding.playTrailer.setVisibility(View.VISIBLE);
                binding.movieCastText.setVisibility(View.VISIBLE);
                binding.moviePlotText.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getMovieCastList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Cast>>() {
            @Override
            public void onChanged(ArrayList<Cast> actors) {
                Log.e(TAG, "onChanged: " + actors.size() );
                adapter.setCastList(actors);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
