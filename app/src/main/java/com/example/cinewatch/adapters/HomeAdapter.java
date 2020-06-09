package com.example.cinewatch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinewatch.databinding.MovieItemBinding;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeRecyclerViewHolder> {
    private Context mContext;
    private MovieItemBinding binding;

    public HomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HomeRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = MovieItemBinding.inflate(inflater,parent,false);
        return new HomeRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeRecyclerViewHolder extends RecyclerView.ViewHolder{

        private MovieItemBinding binding;

        public HomeRecyclerViewHolder(MovieItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
