package com.learn.android.udacity.udacity_popularmovie;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.android.udacity.udacity_popularmovie.adapter.ReviewAdapter;
import com.learn.android.udacity.udacity_popularmovie.adapter.TrailersAdapter;
import com.learn.android.udacity.udacity_popularmovie.data.MovieConstant;
import com.learn.android.udacity.udacity_popularmovie.data.MovieDbContract;
import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.model.MovieReview;
import com.learn.android.udacity.udacity_popularmovie.model.MovieTrailers;
import com.learn.android.udacity.udacity_popularmovie.model.response.MovieResult;
import com.learn.android.udacity.udacity_popularmovie.model.response.MovieReviewResult;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServiceFactory;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServices;
import com.learn.android.udacity.udacity_popularmovie.utils.EndlessRecyclerViewScrollListener;
import com.learn.android.udacity.udacity_popularmovie.utils.ImageUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by winzaldi on 6/29/17.
 */
public class MovieDetailActivity extends AppCompatActivity {

    public static final String TAG = MovieDetailActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE = "movie";
    private ImageView imgBackDrop, imgPoster;
    private Toolbar toolbar;
    private TextView tvTitle, tvReleaseDate, tvRating, tvOverview;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FloatingActionButton fabFavorite;

    private RecyclerView rvReview, rvTrailers;

    private ReviewAdapter mReviewAdapter;
    private TrailersAdapter mTrailersAdapter;
    private static int totalReviewPages;
    private EndlessRecyclerViewScrollListener mScrollListener;

    private List<MovieReview> reviewList = new ArrayList<>();
    private List<MovieTrailers> trailerList = new ArrayList<>();
    private boolean isFavorite = false;

    private Movie mMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        if (getIntent().hasExtra(EXTRA_MOVIE)) {
            mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate : In Save Instance State");
            trailerList = savedInstanceState.getParcelableArrayList(MovieConstant.KEY_MOVIE_VIDEOS);
            reviewList = savedInstanceState.getParcelableArrayList(MovieConstant.KEY_MOVIE_REVIEWS);
            mMovie = savedInstanceState.getParcelable(MovieConstant.KEY_MOVIE);
        }

        imgBackDrop = (ImageView) findViewById(R.id.img_backdrop);
        imgPoster = (ImageView) findViewById(R.id.poster);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvRating = (TextView) findViewById(R.id.tv_rating);
        tvOverview = (TextView) findViewById(R.id.tv_overview);
        fabFavorite = (FloatingActionButton) findViewById(R.id.fab_favorite);
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) {
                    deleteMovieFromDatabase(mMovie.getId());
                    Toast.makeText(MovieDetailActivity.this, "Remove From Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    insertMovieToDatabase(mMovie);
                    Toast.makeText(MovieDetailActivity.this, "Add To Favorite", Toast.LENGTH_SHORT).show();

                }
            }
        });
        rvReview = (RecyclerView) findViewById(R.id.rvReview);
        rvTrailers = (RecyclerView) findViewById(R.id.rvTrailers);

        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(mMovie.getTitle());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });


        if (mMovie != null) {

            tvTitle.setText(mMovie.getTitle());
            tvOverview.setText(mMovie.getDescription());
            tvReleaseDate.setText(mMovie.getReleaseDate());
            tvRating.setText(mMovie.getVoteAverage());

            ImageUtils.loadPosterImg(this, imgPoster, mMovie.getPoster());
            ImageUtils.loadBackdropImg(this, imgBackDrop, mMovie.getBackdrop());


        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (trailerList != null) {
            outState.putParcelableArrayList(MovieConstant.KEY_MOVIE_VIDEOS, new ArrayList<>(trailerList));
        }
        if (reviewList != null) {
            outState.putParcelableArrayList(MovieConstant.KEY_MOVIE_REVIEWS, new ArrayList<>(reviewList));
        }
        if (mMovie != null) {
            outState.putParcelable(MovieConstant.KEY_MOVIE, Parcels.wrap(mMovie));
        }
    }

    private void initRecycleViewserTrailers() {
        rvTrailers.setLayoutManager(new LinearLayoutManager(this));
        mTrailersAdapter = new TrailersAdapter(this);
        rvTrailers.setAdapter(mTrailersAdapter);
    }

    private void initRecycleViewserReviews() {
        rvReview.setLayoutManager(new GridLayoutManager(this, 3));
        mReviewAdapter = new ReviewAdapter(this);
        rvReview.setAdapter(mReviewAdapter);

    }

    private void openVideoOnYoutube(String key) {
        final String URL = "http://www.youtube.com/watch?v=" + key;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(URL)));
    }

    private void deleteMovieFromDatabase(int id) {
        ContentResolver resolver = getContentResolver();
        String[] selectionArguments = new String[]{String.valueOf(id)};
        resolver.delete(MovieDbContract.Movie.CONTENT_URI,
                MovieDbContract.Movie.COLUMN_MOVIE_ID + " = ? ", selectionArguments);
    }

    private void insertMovieToDatabase(Movie mMovie) {
        ContentResolver resolver = getContentResolver();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_ID, mMovie.getId());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_TITLE, mMovie.getTitle());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_OVERVIEW, mMovie.getDescription());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_VOTE_COUNT, mMovie.getVoteCount());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_VOTE_AVERAGE, mMovie.getVoteAverage());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_RELEASE_DATE, mMovie.getReleaseDate());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_FAVORED, mMovie.getFavMovie());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_POSTER_PATH, mMovie.getPoster());
        contentValues.put(MovieDbContract.Movie.COLUMN_MOVIE_BACKDROP_PATH, mMovie.getBackdrop());

        resolver.insert(MovieDbContract.Movie.CONTENT_URI,contentValues);
    }

    private void getMovieReviews(int id, int page) {
        MovieDbServices services = new MovieDbServiceFactory().create();
        Call<MovieReviewResult> call = services.getMovieReview(id,page);
        call.enqueue(new Callback<MovieReviewResult>() {
            @Override
            public void onResponse(Call<MovieReviewResult> call, Response<MovieReviewResult> response) {
                if(response.body()!=null){
                    totalReviewPages = response.body().getTotal_pages();
                    List<MovieReview> results = response.body().getResults();
                    if(!results.isEmpty()){
                        reviewList.addAll(results);
                        mReviewAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResult> call, Throwable t) {

            }
        });

    }

}
