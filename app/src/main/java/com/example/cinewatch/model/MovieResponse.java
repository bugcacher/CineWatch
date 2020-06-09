package com.example.cinewatch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhinav Singh on 09,June,2020
 */
public class MovieResponse {
    private int page,total_pages,total_results;
    private ArrayList<MovieListResult> results;

    public MovieResponse(int page, int total_pages, int total_results, ArrayList<MovieListResult> results) {
        this.page = page;
        this.total_pages = total_pages;
        this.total_results = total_results;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public ArrayList<MovieListResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieListResult> results) {
        this.results = results;
    }

}
