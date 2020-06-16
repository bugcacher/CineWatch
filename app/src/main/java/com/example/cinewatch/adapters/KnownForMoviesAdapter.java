package com.example.cinewatch.adapters;

import android.content.Context;
import android.os.Build;
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
import com.example.cinewatch.model.Movie;
import com.example.cinewatch.ui.Fragments.ActorDetailsDirections;
import com.example.cinewatch.ui.Fragments.HomeDirections;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class KnownForMoviesAdapter extends RecyclerView.Adapter<KnownForMoviesAdapter.KnowForViewHolder> {
    private ArrayList<Movie> moviesList;
    private Context mContext;
    private HomeMovieItemBinding binding;

    public KnownForMoviesAdapter(Context mContext, ArrayList<Movie> moviesList) {
        this.mContext = mContext;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public KnowForViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = HomeMovieItemBinding.inflate(inflater,parent,false);
        return new KnowForViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull KnowForViewHolder holder, int position) {
        holder.binding.movieItemRelativeLayout.setClipToOutline(true);
        holder.binding.movieItemName.setText(moviesList.get(position).getTitle());
//        temp = "";
//        for(String genre : moviesList.get(position).getGenre_names()){
//            temp+= genre + " | ";
//        }
        holder.binding.movieItemVotes.setText(moviesList.get(position).getVote_count()+"");

        Glide.with(mContext).load(Constants.ImageBaseURLw500 + moviesList.get(position).getPoster_path())
                .into(holder.binding.movieItemImage);
        holder.binding.movieItemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActorDetailsDirections.ActionActorDetailsToMovieDetails2 action = ActorDetailsDirections
                        .actionActorDetailsToMovieDetails2(moviesList.get(position).getId());
                Navigation.findNavController(view).navigate(action);


            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList == null ? 0 : moviesList.size();
    }

    class KnowForViewHolder extends RecyclerView.ViewHolder{

        private HomeMovieItemBinding binding;

        public KnowForViewHolder(HomeMovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setMoviesList(ArrayList<Movie> moviesList){
        this.moviesList = moviesList;
        notifyDataSetChanged();
    }
}
