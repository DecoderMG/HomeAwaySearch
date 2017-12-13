package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/12/2017.
 */

public class ListingAdPhoto implements Serializable {
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("large")
    @Expose
    private PhotoImage large;
    @SerializedName("medium")
    @Expose
    private PhotoImage medium;
    @SerializedName("small")
    @Expose
    private PhotoImage small;

    @SerializedName("originalDimension")
    @Expose
    private Dimension originalDimensions;

    public ListingAdPhoto() {
    }

    public ListingAdPhoto(String caption, PhotoImage large, PhotoImage medium, PhotoImage small, Dimension originalDimensions) {
        this.caption = caption;
        this.large = large;
        this.medium = medium;
        this.small = small;
        this.originalDimensions = originalDimensions;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public PhotoImage getLarge() {
        return large;
    }

    public void setLarge(PhotoImage large) {
        this.large = large;
    }

    public PhotoImage getMedium() {
        return medium;
    }

    public void setMedium(PhotoImage medium) {
        this.medium = medium;
    }

    public PhotoImage getSmall() {
        return small;
    }

    public void setSmall(PhotoImage small) {
        this.small = small;
    }

    public Dimension getOriginalDimensions() {
        return originalDimensions;
    }

    public void setOriginalDimensions(Dimension originalDimensions) {
        this.originalDimensions = originalDimensions;
    }
}
