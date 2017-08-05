package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.android.udacity.udacity_popularmovie.BuildConfig;
import com.learn.android.udacity.udacity_popularmovie.MovieDetailActivity;
import com.learn.android.udacity.udacity_popularmovie.R;
import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by winzaldi on 6/29/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {


    private List<Movie> mMovieList;
    private LayoutInflater mInflater;
    private Context mContext;

    public MoviesAdapter(Context context)
    {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMovieList = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_movie, parent, false);
        final MovieViewHolder viewHolder = new MovieViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, mMovieList.get(position));
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);

        //Log.i("TAG",movie.getPoster());

        // This is how we use Picasso to load images from the internet.
        //Log.i("TAG",BuildConfig.POSTER_URL + movie.getPoster());
        Picasso.with(mContext)
                .load(BuildConfig.POSTER_URL + movie.getPoster())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(holder.imageView);
    }

    public void setMovieList(List<Movie> movieList)
    {
        this.mMovieList.clear();
        this.mMovieList.addAll(movieList);
        // The adapter needs to know that the data has changed. If we don't call this, app will crash.
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
