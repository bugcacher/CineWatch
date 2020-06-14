package com.example.cinewatch.di;

import android.app.Application;

import androidx.room.Room;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.db.MovieDatabase;
import com.example.cinewatch.db.WishListDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {


    @Provides
    @Singleton
    MovieDatabase provideMovieDatabase(Application application){
        return Room.databaseBuilder(application,MovieDatabase.class, Constants.DataBaseName)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    WishListDao provideWishListDao(MovieDatabase movieDatabase){
        return movieDatabase.wishListDao();
    }
}
