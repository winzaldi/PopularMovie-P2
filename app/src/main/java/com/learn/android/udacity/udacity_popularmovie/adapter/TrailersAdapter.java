package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.android.udacity.udacity_popularmovie.R;
import com.learn.android.udacity.udacity_popularmovie.model.MovieTrailers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winzaldi on 8/5/17.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersViewHolder> {

    private List<MovieTrailers> mMovieVideoList;
    private LayoutInflater mInflater;
    private Context mContext;

    public TrailersAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mMovieVideoList = new ArrayList<>();
    }

    @Override
    public TrailersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_review, parent, false);
        final TrailersViewHolder viewHolder = new TrailersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrailersViewHolder holder, int position) {
        MovieTrailers video = mMovieVideoList.get(position);
        holder.setTvName(video.getName());
    }

    @Override
    public int getItemCount() {
        return mMovieVideoList.size();
    }
}
