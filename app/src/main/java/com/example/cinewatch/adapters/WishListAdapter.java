package com.example.cinewatch.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.HomeMovieItemBinding;
import com.example.cinewatch.db.WishListMovie;
import com.example.cinewatch.ui.Fragments.HomeDirections;
import com.example.cinewatch.ui.Fragments.WishListFragmentDirections;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.WishListViewHolder> {
    private static final String TAG = "WishListAdapter";
    private List<WishListMovie> moviesList;
    private Context mContext;
    private HomeMovieItemBinding binding;

    public WishListAdapter(Context mContext, List<WishListMovie> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public WishListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = HomeMovieItemBinding.inflate(inflater,parent,false);
        return new WishListViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull WishListViewHolder holder, int position) {
        holder.binding.movieItemRelativeLayout.setClipToOutline(true);
        holder.binding.movieItemName.setText(moviesList.get(position).getTitle());

        holder.binding.movieItemVotes.setText(moviesList.get(position).getVote_count()+"");

        Glide.with(mContext).load(Constants.ImageBaseURLw500 + moviesList.get(position).getPoster_path())
                .into(holder.binding.movieItemImage);
        holder.binding.movieItemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WishListFragmentDirections.ActionWishListToMovieDetails action =
                        WishListFragmentDirections.actionWishListToMovieDetails(moviesList.get(position).getId());
                Navigation.findNavController(view).navigate(action);

            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    class WishListViewHolder extends RecyclerView.ViewHolder{

        private HomeMovieItemBinding binding;

        public WishListViewHolder(HomeMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setMoviesList(List<WishListMovie> moviesList){
        Log.e(TAG, "setMoviesList: " + "called" );
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }
}
