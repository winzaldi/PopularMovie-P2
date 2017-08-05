package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.learn.android.udacity.udacity_popularmovie.R;

/**
 * Created by winzaldi on 6/29/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder  {
    public ImageView imageView;
       public MovieViewHolder(View view){
           super(view);
           imageView = (ImageView)view.findViewById(R.id.img_movie);
       }

}
