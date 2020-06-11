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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.HomeLayoutBinding;
import com.example.cinewatch.databinding.PeopleLayoutBinding;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.HashMap;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
@AndroidEntryPoint
public class ActorDetails extends Fragment {
    private PeopleLayoutBinding binding;
    private HomeViewModel viewModel;
    private Integer personID;
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

            }
        });
        binding.knownForRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false));
        // todo famous for list

        viewModel.getActorDetails(personID, Constants.API_KEY);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
