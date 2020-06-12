package com.example.cinewatch.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.databinding.CastItemBinding;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.ui.Fragments.MovieDetailsDirections;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastRecyclerViewHolder> {
    private ArrayList<Cast> castList;
    private Context mContext;
    private CastItemBinding binding;

    public CastAdapter(Context mContext, ArrayList<Cast> castList) {
        this.mContext = mContext;
        this.castList = castList;
    }

    @NonNull
    @Override
    public CastAdapter.CastRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = CastItemBinding.inflate(inflater,parent,false);
        return new CastAdapter.CastRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.CastRecyclerViewHolder holder, int position) {
        holder.binding.castName.setText(castList.get(position).getName());
        holder.binding.castRole.setText(castList.get(position).getCharacter());
        Glide.with(mContext).load(Constants.ImageBaseURL + castList.get(position).getProfile_path())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.binding.castImage);
        holder.binding.castItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MovieDetailsDirections.ActionMovieDetailsToActorDetails action =
                        MovieDetailsDirections.actionMovieDetailsToActorDetails(castList.get(position).getId());
                Navigation.findNavController(view).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return castList == null ? 0 : castList.size();
    }

    class CastRecyclerViewHolder extends RecyclerView.ViewHolder{

        private CastItemBinding binding;

        public CastRecyclerViewHolder(CastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setCastList(ArrayList<Cast> castList){
        this.castList = castList;
    }
}
