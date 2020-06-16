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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.adapters.HomeMoviesAdapter;
import com.example.cinewatch.adapters.KnownForMoviesAdapter;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.databinding.PeopleLayoutBinding;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.viewmodel.HomeViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
@AndroidEntryPoint
public class ActorDetails extends Fragment {
    private static final String TAG = "ActorDetails";
    private PeopleLayoutBinding binding;
    private HomeViewModel viewModel;
    private Integer personID;
    private HashMap<String , String> queries;
    private KnownForMoviesAdapter adapter;
    private ArrayList<Movie> popularMovies;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = PeopleLayoutBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        ActorDetailsArgs args = ActorDetailsArgs.fromBundle(getArguments());
        personID = args.getPersonId();

        queries = new HashMap<>();
        queries.put("api_key",Constants.API_KEY);
        queries.put("append_to_response","movie_credits");

        viewModel.getActorDetails(personID, queries);

        viewModel.getActor().observe(getViewLifecycleOwner(), new Observer<Actor>() {
            @Override
            public void onChanged(Actor actor) {

                binding.actorName.setText(actor.getName());
                binding.actorBirthday.setText(actor.getBirthday());
                binding.actorBio.setText(actor.getBiography());
                binding.actorPlace.setText(actor.getPlace_of_birth());
                Glide.with(getContext()).load(Constants.ImageBaseURL + actor.getProfile_path())
                        .into(binding.actorImage);
                binding.actorPopularity.setText(actor.getPopularity() + "");
                binding.actorBioText.setVisibility(View.VISIBLE);
                binding.knownForText.setVisibility(View.VISIBLE);
                binding.popularityIcon.setVisibility(View.VISIBLE);
                JsonArray array = actor.getMovie_credits().getAsJsonArray("cast");
                popularMovies = new Gson().fromJson(array.toString(),
                        new TypeToken<ArrayList<Movie>>(){}.getType());
                initKnownFor(popularMovies);

            }
        });
        binding.knownForRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        // todo famous for list



    }

    private void initKnownFor(ArrayList<Movie> movies) {
        Log.e(TAG, "initKnownFor: "+ movies.size() );
        binding.knownForRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
        adapter = new KnownForMoviesAdapter(getContext(),movies);
        binding.knownForRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
