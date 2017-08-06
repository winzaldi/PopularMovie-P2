package com.learn.android.udacity.udacity_popularmovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by winzaldi on 8/4/17.
 */

public class MovieTrailers implements Parcelable {

    private String site;

    private String id;

    @SerializedName("iso_639_1")
    private String iso6391;

    private String name;

    private String type;

    private String key;

    @SerializedName("iso_3166_1")
    private String iso31661;

    private String size;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    protected MovieTrailers(Parcel in) {
        site = in.readString();
        id = in.readString();
        iso6391 = in.readString();
        name = in.readString();
        type = in.readString();
        key = in.readString();
        iso31661 = in.readString();
        size = in.readString();

    }

    public static final Creator<MovieTrailers> CREATOR = new Creator<MovieTrailers>() {
        @Override
        public MovieTrailers createFromParcel(Parcel in) {
            return new MovieTrailers(in);
        }

        @Override
        public MovieTrailers[] newArray(int size) {
            return new MovieTrailers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getUrlForVideoTrailer() {
        return "https://i1.ytimg.com/vi/" + key + "/0.jpg";
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.site);
        parcel.writeString(this.id);
        parcel.writeString(this.iso6391);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.key);
        parcel.writeString(this.iso31661);
        parcel.writeString(this.size);

    }

    @Override
    public String toString() {
        return "MovieTrailers{" +
                "site='" + site + '\'' +
                ", id='" + id + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key='" + key + '\'' +
                ", iso31661='" + iso31661 + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
