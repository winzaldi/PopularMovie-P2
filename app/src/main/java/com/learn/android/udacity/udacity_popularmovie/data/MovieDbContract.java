package com.learn.android.udacity.udacity_popularmovie.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by winzaldi on 8/4/17.
 */

public class MovieDbContract {

    public static final String CONTENT_AUTHORITY ="com.udacity.movies";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_MOVIES = "movies";

    public static final class  Movie implements BaseColumns {
        public static final Uri CONTENT_URI  = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String TABLE_NAME ="t_movies";
        public static final String COLUMN_MOVIE_ID = "id";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_OVERVIEW = "overview";
        public static final String COLUMN_MOVIE_VOTE_COUNT = "vote_count";
        public static final String COLUMN_MOVIE_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_FAVORED = "favored";
        public static final String COLUMN_MOVIE_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_MOVIE_POSTER_PATH = "poster_path";



        static Uri buildMovieUri(String movieId){
            return CONTENT_URI.buildUpon().appendPath(movieId).build();
        }


        public static String getMovieId(Uri uri) {
            return uri.getPathSegments().get(1);
        }

    }


}
