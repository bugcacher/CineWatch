package com.example.cinewatch.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinewatch.Repository;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.model.MovieResponse;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class HomeViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<ArrayList<MovieListResult>> currentMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> popularMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> topRatedMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> upcomingMoviesList = new MutableLiveData<>();
    private MutableLiveData<MovieListResult> movieDetails = new MutableLiveData<>();
    private MutableLiveData<Actor> actorDetails = new MutableLiveData<>();

    private final io.reactivex.rxjava3.disposables.CompositeDisposable disposables = new CompositeDisposable();

    public HomeViewModel() {
        repository = new Repository();
    }

    public MutableLiveData<ArrayList<MovieListResult>> currentlyShowingList(){
        return currentMoviesList;
    }

    public MutableLiveData<ArrayList<MovieListResult>> getPopularMoviesList() {
        return popularMoviesList;
    }

    public MutableLiveData<ArrayList<MovieListResult>> getTopRatedMoviesList() {
        return topRatedMoviesList;
    }

    public MutableLiveData<ArrayList<MovieListResult>> getUpcomingMoviesList() {
        return upcomingMoviesList;
    }

    public MutableLiveData<MovieListResult> getMovieDetails() {
        return movieDetails;
    }

    public MutableLiveData<Actor> getActorDetails() {
        return actorDetails;
    }

    public void getCurrentlyShowingMovieList(HashMap<String, String> map){
        disposables.add(repository.getCurrentlyShowing(map)
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieResponse, ArrayList<MovieListResult>>() {
                    @Override
                    public ArrayList<MovieListResult> apply(MovieResponse movieResponse) throws Throwable {
                        ArrayList<MovieListResult> listResults = movieResponse.getResults();
                        return listResults;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<MovieListResult>>() {
                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull ArrayList<MovieListResult> movieListResults) {
                        currentMoviesList.setValue(movieListResults);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
