package com.example.cinewatch.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.CurrentlyShowingMovieItemBinding;
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.ui.Fragments.HomeDirections;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private static final String TAG = "ViewPagerAdapter";
    private ArrayList<Movie> movieList;
    private Context mContext;
    private CurrentlyShowingMovieItemBinding binding;

    public ViewPagerAdapter(Context mContext, ArrayList<Movie> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = CurrentlyShowingMovieItemBinding.inflate(inflater,parent,false);
        return new ViewPagerViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: " + movieList.get(position).getTitle() );
        holder.binding.currentlyShowingMovieName.setText(movieList.get(position).getTitle());
        Glide.with(mContext).load(Constants.ImageBaseURL+ movieList.get(position).getBackdrop_path())
                .into(holder.binding.currentlyShowingMovieImage);
        holder.binding.currentlyShowingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeDirections.ActionHome3ToMovieDetails action = HomeDirections
                        .actionHome3ToMovieDetails(movieList.get(position).getId());
                Navigation.findNavController(view).navigate(action);
            }
        });
        holder.binding.currentlyShowingMovieImage.setClipToOutline(true);
        holder.binding.currentlyShowingLayout.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder{

        private CurrentlyShowingMovieItemBinding binding;

        public ViewPagerViewHolder(CurrentlyShowingMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setMovieListResults(ArrayList<Movie> movieList){
        this.movieList = movieList;
    }
}
