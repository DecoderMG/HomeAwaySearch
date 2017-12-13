package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 11/6/2017.
 */

public class ListingMedia implements Serializable {

    @SerializedName("photos")
    @Expose
    private ArrayList<ListingAdPhoto> photos;
    @SerializedName("thumbnails")
    @Expose
    private ArrayList<ListingAdHeadlinePhoto> thumbnails;

    public ListingMedia() {
    }

    public ListingMedia(ArrayList<ListingAdPhoto> photos, ArrayList<ListingAdHeadlinePhoto> thumbnails) {
        this.photos = photos;
        this.thumbnails = thumbnails;
    }

    public ArrayList<ListingAdPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<ListingAdPhoto> photos) {
        this.photos = photos;
    }

    public ArrayList<ListingAdHeadlinePhoto> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(ArrayList<ListingAdHeadlinePhoto> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
