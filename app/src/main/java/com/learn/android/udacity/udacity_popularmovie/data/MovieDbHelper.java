package com.learn.android.udacity.udacity_popularmovie.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by winzaldi on 8/4/17.
 */

public class MovieDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "moviedb.db";
    private static final int VERSION = 1;
    private static Context context;

    public MovieDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE " + MovieDbContract.Movie.TABLE_NAME + " ( "
                + BaseColumns._ID + " INTEGER NOT NULL PRIMARY KEY,"
                + MovieDbContract.Movie.COLUMN_MOVIE_ID + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_TITLE + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_OVERVIEW + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_VOTE_AVERAGE + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_VOTE_COUNT + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_FAVORED + " TEXT NOT NULL  ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_POSTER_PATH + " TEXT NOT NULL ,"
                + MovieDbContract.Movie.COLUMN_MOVIE_BACKDROP_PATH + " TEXT NOT NULL, "
                + " UNIQUE (" + MovieDbContract.Movie.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE )";
        Log.e("SQL :",SQL);
        db.execSQL(SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieDbContract.Movie.TABLE_NAME);
        onCreate(db);
    }

    void deleteDatabase() {
        context.deleteDatabase(DB_NAME);
    }




}
