package com.learn.android.udacity.udacity_popularmovie.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.learn.android.udacity.udacity_popularmovie.data.MovieDbContract;


/**
 * Created by winzaldi on 6/29/17.
 */

public class Movie implements Parcelable{

    private int id;
    private String title;
    @SerializedName("poster_path")
    private String poster;
    @SerializedName("overview")
    private String description;
    @SerializedName("backdrop_path")
    private String backdrop;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_count")
    private String voteCount;
    @SerializedName("vote_average")
    private String voteAverage;
    private String favMovie ;

    public Movie() {
    }

    protected Movie(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster = in.readString();
        description = in.readString();
        backdrop = in.readString();
        releaseDate = in.readString();
        voteCount = in.readString();
        voteAverage = in.readString();
        favMovie = in.readString();
    }




    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public String getFavMovie() {
        return favMovie;
    }

    public void setFavMovie(String favMovie) {
        this.favMovie = favMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(poster);
        parcel.writeString(description);
        parcel.writeString(backdrop);
        parcel.writeString(releaseDate);
        parcel.writeString(voteAverage);

    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }




}
