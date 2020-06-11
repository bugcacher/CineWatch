package com.example.cinewatch.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinewatch.Utils.Constants;
import com.example.cinewatch.model.Cast;
import com.example.cinewatch.model.Genre;
import com.example.cinewatch.repository.Repository;
import com.example.cinewatch.model.Actor;
import com.example.cinewatch.model.MovieListResult;
import com.example.cinewatch.model.MovieResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class HomeViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<ArrayList<MovieListResult>> currentMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> popularMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> topRatedMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<MovieListResult>> upcomingMoviesList = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Cast>> movieCastList = new MutableLiveData<>();
    private MutableLiveData<MovieListResult> movieDetails = new MutableLiveData<>();
    private MutableLiveData<Actor> actorDetails = new MutableLiveData<>();

    private final io.reactivex.rxjava3.disposables.CompositeDisposable disposables = new CompositeDisposable();

    @ViewModelInject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<MovieListResult>> getCurrentlyShowingList(){
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

    public MutableLiveData<MovieListResult> getMovie() {
        return movieDetails;
    }

    public MutableLiveData<Actor> getActor() {
        return actorDetails;
    }

    public MutableLiveData<ArrayList<Cast>> getMovieCastList() {
        return movieCastList;
    }


    public void getCurrentlyShowingMovies(HashMap<String, String> map){
        disposables.add(repository.getCurrentlyShowing(map)
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieResponse, ArrayList<MovieListResult>>() {
                    @Override
                    public ArrayList<MovieListResult> apply(MovieResponse movieResponse) throws Throwable {
                        return movieResponse.getResults();
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

    public void getPopularMovies(HashMap<String, String> map){
        disposables.add(repository.getPopular(map)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result->popularMoviesList.setValue(result.getResults()),
                error-> Log.e(TAG, "getPopularMovies: " + error.getMessage() ))
        );
    }

    public void getTopRatedMovies(HashMap<String, String> map) {
        disposables.add(repository.getTopRated(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> topRatedMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getTopRated: " + error.getMessage()))
        );
    }

    public void getUpcomingMovies(HashMap<String, String> map) {
        disposables.add(repository.getUpcoming(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> upcomingMoviesList.setValue(result.getResults()),
                        error -> Log.e(TAG, "getUpcoming: " + error.getMessage()))
        );
    }

    public void getMovieDetails(int movieId, HashMap<String, String> map) {
        disposables.add(repository.getMovieDetails(movieId, map)
                .subscribeOn(Schedulers.io())
                .map(new Function<MovieListResult, MovieListResult>() {
                    @Override
                    public MovieListResult apply(MovieListResult movieListResult) throws Throwable {
                        ArrayList<String> genreNames = new ArrayList<>();
                        for(Genre genre : movieListResult.getGenres()){
                            genreNames.add(genre.getName());
                        }
                        movieListResult.setGenre_names(genreNames);
                        return movieListResult;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieDetails.setValue(result),
                        error -> Log.e(TAG, "getMovieDetails: " + error.getMessage()))
        );
    }

    public void getCast(int movieId, HashMap<String, String> map) {
        disposables.add(repository.getCast(movieId, map)
                .subscribeOn(Schedulers.io())
                .map(new Function<JsonObject, ArrayList<Cast>>() {
                    @Override
                    public ArrayList<Cast> apply(JsonObject jsonObject) throws Throwable {
                        JsonArray jsonArray = jsonObject.getAsJsonArray("cast");
                        return  new Gson().fromJson(jsonArray.toString(), new TypeToken<ArrayList<Cast>>(){}.getType());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> movieCastList.setValue(result),
                        error -> Log.e(TAG, "getCastList: " + error.getMessage()))
        );
    }

    public void getActorDetails(int personId, String api_key) {
        disposables.add(repository.getActorDetails(personId, api_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> actorDetails.setValue(result),
                        error -> Log.e(TAG, "getActorDetails: " + error.getMessage()))
        );
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
