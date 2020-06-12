package com.example.cinewatch.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cinewatch.model.Genre;

import java.util.ArrayList;

/**
 * Created by Abhinav Singh on 12,June,2020
 */
@Entity(tableName = "wishlist_table")
public class MovieEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Integer id;

    private String poster_path,overview,release_date,title,backdrop_path;
    private Integer vote_count,runtime;
    private Number popularity,vote_average;
    private ArrayList<Genre> genres;

    public MovieEntity(@NonNull  Integer id, String poster_path, String overview, String release_date, String title, String backdrop_path, Integer vote_count, Integer runtime, Number popularity, Number vote_average, ArrayList<Genre> genres) {
        this.id = id;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.vote_count = vote_count;
        this.runtime = runtime;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public Number getVote_average() {
        return vote_average;
    }

    public void setVote_average(Number vote_average) {
        this.vote_average = vote_average;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}
