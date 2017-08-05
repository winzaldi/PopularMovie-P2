package com.learn.android.udacity.udacity_popularmovie.model.response;

import com.learn.android.udacity.udacity_popularmovie.model.Movie;

import java.util.List;

/**
 * Created by winzaldi on 6/29/17.
 */

public class MovieResult {

    private String page;

    private int total_pages;

    private int total_results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
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

    private List<Movie> results;

    public List<Movie> getResults() {
        return results;
    }
}
