package com.learn.android.udacity.udacity_popularmovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.learn.android.udacity.udacity_popularmovie.R;

/**
 * Created by winzaldi on 8/5/17.
 */

public class ReviewViewHolder  extends RecyclerView.ViewHolder  {
    public TextView tv_author,tv_content;

    public ReviewViewHolder(View itemView) {
        super(itemView);
        tv_author = (TextView) itemView.findViewById(R.id.tv_author);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }

    public  void setTvAuthor(String text){
        tv_author.setText(text);
    }

    public void setTvContent(String text){
        tv_content.setText(text);
    }
}
