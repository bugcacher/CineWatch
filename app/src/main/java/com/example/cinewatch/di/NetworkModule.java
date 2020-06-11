package com.example.cinewatch.di;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.network.MovieApiService;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abhinav Singh on 11,June,2020
 */

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    public static MovieApiService provideMovieApiService(){
        return  new Retrofit.Builder()
                .baseUrl(Constants.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MovieApiService.class);
    }
}
