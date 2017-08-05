package com.learn.android.udacity.udacity_popularmovie.service;


import com.learn.android.udacity.udacity_popularmovie.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by winzaldi on 6/28/17.
 */

public class MovieDbServiceFactory {

    HttpLoggingInterceptor interceptor =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    final MovieDbInterceptor dbInterceptor = new MovieDbInterceptor();

    final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(dbInterceptor)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.THEMOVIEDB_API)
            .build();
    public MovieDbServices create(){
        return  retrofit.create(MovieDbServices.class);
    }

}
