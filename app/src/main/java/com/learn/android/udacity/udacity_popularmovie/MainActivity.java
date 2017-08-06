package com.learn.android.udacity.udacity_popularmovie;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.learn.android.udacity.udacity_popularmovie.adapter.MoviesAdapter;
import com.learn.android.udacity.udacity_popularmovie.data.MovieConstant;
import com.learn.android.udacity.udacity_popularmovie.data.MovieDbContract;
import com.learn.android.udacity.udacity_popularmovie.model.Movie;
import com.learn.android.udacity.udacity_popularmovie.model.response.MovieResult;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServiceFactory;
import com.learn.android.udacity.udacity_popularmovie.service.MovieDbServices;
import com.learn.android.udacity.udacity_popularmovie.utils.EndlessScrollListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by winzaldi on 6/29/17.
 */
public class MainActivity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int POPULAR = 0;
    public static final int TOP_RATED = 1;
    public static final int FAVORITE = 2;
    private int selectedSort = 0;
    private int page = 1;
    private int recyclerPosition;
    private List<Movie> movieList = new ArrayList<>();

    //loader ID
    private static final int ID_LOADER_FOR_MOVIE = 45;

    private ProgressBar progressBar;
    private TextView tvMessage;
    private RecyclerView mRecyclerView;
    private MoviesAdapter mAdapter;
    private SwipeRefreshLayout refreshLayout;
    private EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_movie);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        tvMessage = (TextView) findViewById(R.id.tv_pesan);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.sr_content);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRecycleViewer();

        if (savedInstanceState == null) {
            /** Get Movies from The server */
            getMovies(POPULAR, page);
        } else {
            if (selectedSort == FAVORITE) {
                getSupportLoaderManager().initLoader(ID_LOADER_FOR_MOVIE, null, this);
            } else {
                page = savedInstanceState.getInt(MovieConstant.PAGE_STATE);
                recyclerPosition = savedInstanceState.getInt(MovieConstant.RECYCLER_POSITION, 0);

                movieList = Parcels.unwrap(savedInstanceState.getParcelable(MovieConstant.MOVIE_VIDEOS_STATE));

                Log.d(TAG,"PAGE ::" + page);
                Log.d(TAG,"RECYCLE ::" +recyclerPosition);
                Log.d(TAG,"SIZE ::" + movieList.size());

                mAdapter.setMovieList(movieList);
                mAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mRecyclerView != null)
            outState.putInt(MovieConstant.RECYCLER_POSITION, mRecyclerView.getScrollY());
        if (movieList != null) {
            outState.putParcelable(MovieConstant.MOVIE_VIDEOS_STATE, Parcels.wrap(movieList));
        }
        outState.putInt(MovieConstant.PAGE_STATE, page);
    }

    private void initRecycleViewer() {
        /**  change count of grid column depend on screen orientation **/
        Resources res = getResources();
        int columnCount = res.getInteger(R.integer.column_portrait);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            columnCount = res.getInteger(R.integer.column_portrait);
        } else {
            columnCount = res.getInteger(R.integer.column_land);
        }
        final LinearLayoutManager layoutManager = new GridLayoutManager(this, columnCount);
        mRecyclerView.setLayoutManager(layoutManager);
        refreshLayout.setOnRefreshListener(this);
        mAdapter = new MoviesAdapter(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        endlessScrollListener = new EndlessScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage){
                Log.i(TAG, "onLoadMore: " + currentPage);
                getMovies(selectedSort, currentPage);
            }
        };
        mRecyclerView.addOnScrollListener(endlessScrollListener);

        if (recyclerPosition > 0)
            mRecyclerView.setScrollY(recyclerPosition);

    }


    private void getMovies(int selectedSort, int page) {

        MovieDbServices services = new MovieDbServiceFactory().create();
        Call<MovieResult> call = null;

        switch (selectedSort) {
            case POPULAR:
                call = services.getMovies(MovieDbServices.PATH_POPULAR, page);
                break;
            case TOP_RATED:
                call = services.getMovies(MovieDbServices.PATH_TOP_RATED, page);
                break;
            default:
                call = services.getMovies(MovieDbServices.PATH_POPULAR, page);
        }
        progressBar.setVisibility(View.VISIBLE);
        tvMessage.setVisibility(View.GONE);
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                movieList.addAll(response.body().getResults());
                mAdapter.setMovieList(movieList);
                mRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Log.e("TAG", t.getLocalizedMessage());
                progressBar.setVisibility(View.GONE);
                tvMessage.setVisibility(View.VISIBLE);
                tvMessage.setText("Sorry, Failured get data from Server");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        page = 1;
        movieList.clear();
        switch (item.getItemId()) {

            case R.id.mn_sort_by_popularity:
                selectedSort = POPULAR;
                getMovies(selectedSort, page);
                break;
            case R.id.mn_sort_by_top_rate:
                selectedSort = TOP_RATED;
                getMovies(selectedSort, page);
                break;
            case R.id.mn_favorites:
                Log.d(TAG,"FAVORITE CLICK");
                selectedSort = FAVORITE;
                getSupportLoaderManager().initLoader(ID_LOADER_FOR_MOVIE, null, this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        refreshLayout.setRefreshing(false);
        page = 1;
        getMovies(selectedSort, page);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case ID_LOADER_FOR_MOVIE:
                Uri movieUri = MovieDbContract.Movie.CONTENT_URI;
                String sortOder = MovieDbContract.Movie._ID + " DESC";
                return new CursorLoader(this, movieUri, MovieConstant.MovieProjection, null, null, sortOder);
            default:
                throw new RuntimeException("Loader Not Implemented" + id);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount() > 0) {
            List<Movie> listOfMovies = convertCursorToMovie(data);
            mAdapter.setMovieList(listOfMovies);
        } else {
            hideContent();
            tvMessage.setVisibility(View.VISIBLE);
            tvMessage.setText(" Favorited Movies Not Available ");
        }
        selectedSort = FAVORITE;
    }

    private void hideContent() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        movieList.clear();
        mAdapter.notifyDataSetChanged();
    }

    private List<Movie> convertCursorToMovie(Cursor data) {
        List<Movie> listOfMovies = new ArrayList<>();
        for (int i = 0; i < data.getCount(); i++) {
            data.moveToPosition(i);
            Movie model = new Movie();
            model.setId(data.getInt(MovieConstant.COLUMNMOVIE._ID));
            model.setTitle(data.getString(MovieConstant.COLUMNMOVIE.TITLE));
            model.setDescription(data.getString(MovieConstant.COLUMNMOVIE.OVERVIEW));
            model.setVoteCount(data.getString(MovieConstant.COLUMNMOVIE.VOTE_COUNT));
            model.setVoteAverage(data.getString(MovieConstant.COLUMNMOVIE.VOTE_AVERAGE));
            model.setReleaseDate(data.getString(MovieConstant.COLUMNMOVIE.RELEASE_DATE));
            model.setFavMovie(data.getString(MovieConstant.COLUMNMOVIE.FAVORED));
            model.setPoster(data.getString(MovieConstant.COLUMNMOVIE.POSTER_PATH));
            model.setBackdrop(data.getString(MovieConstant.COLUMNMOVIE.BACKDROP_PATH));
            listOfMovies.add(model);
        }
        return listOfMovies;
    }


}
