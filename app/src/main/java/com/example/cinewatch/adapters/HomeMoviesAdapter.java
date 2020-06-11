package com.example.cinewatch.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.CastItemBinding;
import com.example.cinewatch.databinding.HomeMovieItemBinding;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.ui.Fragments.HomeDirections;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class HomeMoviesAdapter extends RecyclerView.Adapter<HomeMoviesAdapter.HomeRecyclerViewHolder> {
    private ArrayList<MovieListResult> moviesList;
    private Context mContext;
    private HomeMovieItemBinding binding;

    public HomeMoviesAdapter(Context mContext, ArrayList<MovieListResult> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = HomeMovieItemBinding.inflate(inflater,parent,false);
        return new HomeRecyclerViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
        holder.binding.movieItemRelativeLayout.setClipToOutline(true);
        holder.binding.movieItemName.setText(moviesList.get(position).getTitle());
//        temp = "";
//        for(String genre : moviesList.get(position).getGenre_names()){
//            temp+= genre + " | ";
//        }
        holder.binding.movieItemVotes.setText(moviesList.get(position).getVote_count()+"");

        Glide.with(mContext).load(Constants.ImageBaseURL+moviesList.get(position).getPoster_path())
                .into(holder.binding.movieItemImage);
        holder.binding.movieItemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovieDetails action = HomeDirections
                        .actionHome3ToMovieDetails(moviesList.get(position).getId());
                Navigation.findNavController(view).navigate(action);

            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder{

        private HomeMovieItemBinding binding;

        public HomeRecyclerViewHolder(HomeMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setMoviesList(ArrayList<MovieListResult> moviesList){
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }
}
