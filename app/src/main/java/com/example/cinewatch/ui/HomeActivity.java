package com.example.cinewatch.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.cinewatch.databinding.ActivityMainBinding;
import com.example.cinewatch.databinding.MovieDetailsBinding;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.viewmodel.HomeViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import me.ibrahimsn.lib.SmoothBottomBar;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    HomeViewModel viewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);



    }
}
