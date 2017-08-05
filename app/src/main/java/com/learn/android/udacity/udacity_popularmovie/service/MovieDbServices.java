package com.learn.android.udacity.udacity_popularmovie.service;

import com.learn.android.udacity.udacity_popularmovie.model.response.MovieResult;
import com.learn.android.udacity.udacity_popularmovie.model.response.MovieReviewResult;
import com.learn.android.udacity.udacity_popularmovie.model.response.MovieTrailerResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by winzaldi on 6/27/17.
 */


public interface MovieDbServices {

    String PATH_POPULAR = "popular";
    String PATH_TOP_RATED = "top_rated";


    @GET("/3/movie/{sort}")
    //Call<MovieResult> getMovies(@Path("sort") String order);
    Call<MovieResult> getMovies(@Path("sort") String order,@Query("page") int page);


    @GET("/3/movie/{id}/videos")
    Call<MovieTrailerResult> getMovieTrailers(@Path("id") int movieId);

    @GET("/3/movie/{id}/reviews")
    Call<MovieReviewResult> getMovieReview(@Path("id") int movieId, @Query("page") int page);
    //Call<MovieResult> getMovieReview(@Path("id") int movieId);


}
