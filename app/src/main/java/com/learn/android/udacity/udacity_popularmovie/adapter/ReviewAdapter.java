package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.android.udacity.udacity_popularmovie.MovieDetailActivity;
import com.learn.android.udacity.udacity_popularmovie.R;
import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.model.MovieReview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winzaldi on 8/5/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewViewHolder> {

    private List<MovieReview> mMovieReviewList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ReviewAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieReviewList = new ArrayList<>();
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_review, parent, false);
        final ReviewViewHolder viewHolder = new ReviewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        final MovieReview review= mMovieReviewList.get(position);
        holder.setTvAuthor(review.getAuthor());
        holder.setTvContent(review.getContent());
    }

    @Override
    public int getItemCount() {
        return mMovieReviewList.size();
    }
}
