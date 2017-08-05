package com.learn.android.udacity.udacity_popularmovie;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.utils.ImageUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by winzaldi on 6/29/17.
 */
public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";
    private ImageView imgBackDrop,imgPoster;
    private Toolbar toolbar;
    private TextView tvTitle,tvReleaseDate,tvRating,tvOverview;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;

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

        imgBackDrop = (ImageView) findViewById(R.id.img_backdrop);
        imgPoster = (ImageView) findViewById(R.id.poster);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvRating = (TextView) findViewById(R.id.tv_rating);
        tvOverview = (TextView) findViewById(R.id.tv_overview);

        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
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


        tvTitle.setText(mMovie.getTitle());
        tvOverview.setText(mMovie.getDescription());
        tvReleaseDate.setText(mMovie.getReleaseDate());
        tvRating.setText(mMovie.getVoteAverage());

        /* Picasso.with(this)
                .load(BuildConfig.POSTER_URL + mMovie.getPoster())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgPoster);  */
        /* Picasso.with(this)
                .load(BuildConfig.BACKDROP_URL + mMovie.getBackdrop())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imgBackDrop); */

        ImageUtils.loadPosterImg(this,imgPoster,mMovie.getPoster());
        ImageUtils.loadBackdropImg(this,imgBackDrop,mMovie.getBackdrop());



    }
}
