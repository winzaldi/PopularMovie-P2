package com.learn.android.udacity.udacity_popularmovie.utils;

import android.content.Context;
import android.widget.ImageView;

import com.learn.android.udacity.udacity_popularmovie.BuildConfig;
import com.learn.android.udacity.udacity_popularmovie.R;
import com.squareup.picasso.Picasso;

/**
 * Created by winzaldi on 8/4/17.
 */

public class ImageUtils {

    public static void loadPosterImg(Context context, ImageView view, String url) {
        Picasso.with(context)
                .load(BuildConfig.POSTER_URL + url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view);

    }

    public static void loadBackdropImg(Context context, ImageView view, String url) {
        Picasso.with(context)
                .load(BuildConfig.BACKDROP_URL + url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view);

    }

    public static void loadImgVideo(Context context, ImageView view, String url) {
        Picasso.with(view.getContext())
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(view);
    }


}
