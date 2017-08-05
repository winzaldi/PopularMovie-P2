package com.learn.android.udacity.udacity_popularmovie.data;

/**
 * Created by winzaldi on 8/5/17.
 */

public class MovieConstant {

    public static final String RECYCLER_POSITION = "recycleview_position";
    public static final String MOVIE_VIDEOS_STATE = "state_movie";
    public static final String PAGE_STATE = "state_page";

    //Key in detail view
    public static final String KEY_MOVIE_VIDEOS = "KEY_MOVIES_TRAILERS";
    public static final String KEY_MOVIE_REVIEWS = "KEY_REVIEW";
    public static final String KEY_MOVIE = "KEY_MOVIE";


    public final static class COLUMNMOVIE {
        public static final int _ID = 0;
        public static final int TITLE = 1;
        public static final int OVERVIEW = 2;
        public static final int VOTE_COUNT = 3;
        public static final int VOTE_AVERAGE = 4;
        public static final int RELEASE_DATE = 5;
        public static final int FAVORED = 6;
        public static final int POSTER_PATH = 7;
        public static final int BACKDROP_PATH = 8;

    }

    public static final String[] MovieProjection = new String[]{
            MovieDbContract.Movie.COLUMN_MOVIE_ID,
            MovieDbContract.Movie.COLUMN_MOVIE_TITLE,
            MovieDbContract.Movie.COLUMN_MOVIE_OVERVIEW,
            MovieDbContract.Movie.COLUMN_MOVIE_VOTE_COUNT,
            MovieDbContract.Movie.COLUMN_MOVIE_VOTE_AVERAGE,
            MovieDbContract.Movie.COLUMN_MOVIE_RELEASE_DATE,
            MovieDbContract.Movie.COLUMN_MOVIE_FAVORED,
            MovieDbContract.Movie.COLUMN_MOVIE_POSTER_PATH,
            MovieDbContract.Movie.COLUMN_MOVIE_BACKDROP_PATH,
    };


}
