package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.android.udacity.udacity_popularmovie.R;

/**
 * Created by winzaldi on 8/5/17.
 */

public class TrailersViewHolder extends RecyclerView.ViewHolder {
    public ImageView img_trailers;
    public TextView tv_name;

    public TrailersViewHolder(View itemView) {

        super(itemView);
        img_trailers = (ImageView) itemView.findViewById(R.id.img_trailers);
        tv_name = (TextView)itemView.findViewById(R.id.tv_name);
    }

    public void setTvName(String text){
        tv_name.setText(text);
    }
}
