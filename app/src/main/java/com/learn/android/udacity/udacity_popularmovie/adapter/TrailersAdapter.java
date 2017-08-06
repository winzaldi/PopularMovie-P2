package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.android.udacity.udacity_popularmovie.MovieDetailActivity;
import com.learn.android.udacity.udacity_popularmovie.R;
import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.model.MovieTrailers;
import com.learn.android.udacity.udacity_popularmovie.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winzaldi on 8/5/17.
 */

public class TrailersAdapter extends RecyclerView.Adapter<TrailersViewHolder> {

    private List<MovieTrailers> movieTrailersList;
    private LayoutInflater mInflater;
    private Context mContext;

    public TrailersAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.movieTrailersList = new ArrayList<>();
    }

    @Override
    public TrailersViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_trailers, parent, false);
        final TrailersViewHolder viewHolder = new TrailersViewHolder(view);
        viewHolder.img_trailers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                MovieTrailers trailer = movieTrailersList.get(position);
                openVideoOnYoutube(trailer.getKey());
            }
        });
        return viewHolder;
    }
    private void openVideoOnYoutube(String key) {
        final String URL = "http://www.youtube.com/watch?v=" + key;
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
    }


    @Override
    public void onBindViewHolder(TrailersViewHolder holder, int position) {
        MovieTrailers video = movieTrailersList.get(position);
        holder.setTvName(video.getName());
        ImageUtils.loadImgVideo(mContext,holder.img_trailers,video.getUrlForVideoTrailer());
    }

    public void setTrailerList(List<MovieTrailers> trailersList)
    {
        this.movieTrailersList.clear();
        this.movieTrailersList.addAll(trailersList);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return movieTrailersList.size();
    }
}
