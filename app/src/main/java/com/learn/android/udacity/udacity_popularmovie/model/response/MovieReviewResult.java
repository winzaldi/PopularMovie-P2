package com.learn.android.udacity.udacity_popularmovie.model.response;

import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.model.MovieReview;

import java.util.List;

/**
 * Created by winzaldi on 6/29/17.
 */

public class MovieReviewResult {

    private String id;

    private String page;

    private int total_pages;

    private String total_results;


    private List<MovieReview> results;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public List<MovieReview> getResults() {
        return results;
    }
}
