package com.example.cinewatch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cinewatch.databinding.MovieItemBinding;
import com.example.cinewatch.model.MovieListResult;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class CategoryMoviesAdapter extends RecyclerView.Adapter<CategoryMoviesAdapter.CategoryMovieRecyclerViewHolder> {
    private ArrayList<MovieListResult> moviesList;
    private Context mContext;
    private MovieItemBinding binding;
    private String temp;

    public CategoryMoviesAdapter(Context mContext, ArrayList<MovieListResult> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public CategoryMovieRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = MovieItemBinding.inflate(inflater,parent,false);
        return new CategoryMovieRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMovieRecyclerViewHolder holder, int position) {
        holder.binding.movieName.setText(moviesList.get(position).getTitle());
        temp = "";
        for(String genre : moviesList.get(position).getGenre_names()){
            temp+= genre + " | ";
        }
        holder.binding.movieGenre.setText(temp);

        Glide.with(mContext).load(moviesList.get(position).getPoster_path())
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.movieImage);
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    class CategoryMovieRecyclerViewHolder extends RecyclerView.ViewHolder{

        private MovieItemBinding binding;

        public CategoryMovieRecyclerViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
