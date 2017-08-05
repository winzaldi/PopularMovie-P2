package com.learn.android.udacity.udacity_popularmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winzaldi on 8/4/17.
 */

public class MovieReview implements Parcelable {

    @SerializedName("content")
    private String content;

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("url")
    private String url;




    protected MovieReview(Parcel in) {
        this.author = in.readString();
        this.content = in.readString();
        this.id = in.readString();
        this.url = in.readString();
    }

    public static final Creator<MovieVideo> CREATOR = new Creator<MovieVideo>() {
        @Override
        public MovieVideo createFromParcel(Parcel in) {
            return new MovieVideo(in);
        }

        @Override
        public MovieVideo[] newArray(int size) {
            return new MovieVideo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.author);
        parcel.writeString(this.content);
        parcel.writeString(this.url);
        parcel.writeString(this.id);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MovieReview{" +
                "content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}

