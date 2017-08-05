package com.learn.android.udacity.udacity_popularmovie.model.response;

import com.learn.android.udacity.udacity_popularmovie.model.MovieTrailers;

import java.util.List;

/**
 * Created by winzaldi on 6/29/17.
 */

public class MovieTrailerResult {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private List<MovieTrailers> results;

    public List<MovieTrailers> getResults() {
        return results;
    }
}
