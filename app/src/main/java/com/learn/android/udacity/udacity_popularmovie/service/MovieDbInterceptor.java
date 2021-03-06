package com.learn.android.udacity.udacity_popularmovie.service;

import com.learn.android.udacity.udacity_popularmovie.BuildConfig;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by winzaldi on 6/29/17.
 */


public class MovieDbInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        final HttpUrl url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.THEMOVIEDB_KEY)
                .build();
        final Request request = chain.request().newBuilder().url(url).build();
        return chain.proceed(request);
    }

}
