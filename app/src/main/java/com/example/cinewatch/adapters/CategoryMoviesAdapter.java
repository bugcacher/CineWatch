package com.example.cinewatch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.MovieItemBinding;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.ui.Fragments.MoviesDirections;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class CategoryMoviesAdapter extends RecyclerView.Adapter<CategoryMoviesAdapter.CategoryMovieRecyclerViewHolder> {
    private ArrayList<Movie> moviesList;
    private Context mContext;
    private MovieItemBinding binding;
    private String temp;

    public CategoryMoviesAdapter(Context mContext, ArrayList<Movie> moviesList) {
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
//        for (int i = 0; i < moviesList.get(position).getGenre_names().size(); i++){
//            if(i ==  moviesList.get(position).getGenre_names().size() -1)
//                temp+= moviesList.get(position).getGenre_names().get(i);
//            else
//                temp+= moviesList.get(position).getGenre_names().get(i) + " | ";
//        }
        holder.binding.movieGenre.setText(temp);
        holder.binding.movieRating.setRating(moviesList.get(position).getVote_average().floatValue()/2);
        String[] movieYear = moviesList.get(position).getRelease_date()
                .split("-");
        holder.binding.movieYear.setText(movieYear[0]);
        Glide.with(mContext).load(Constants.ImageBaseURLw500 + moviesList.get(position).getPoster_path())
        .into(holder.binding.movieImage);

        holder.binding.movieItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MoviesDirections.ActionMoviesToMovieDetails action =
                        MoviesDirections.actionMoviesToMovieDetails(moviesList.get(position).getId());
                Navigation.findNavController(view).navigate(action);
            }
        });

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
    public void setList(ArrayList<Movie> list){
        moviesList = list;
        notifyDataSetChanged();
    }
}
