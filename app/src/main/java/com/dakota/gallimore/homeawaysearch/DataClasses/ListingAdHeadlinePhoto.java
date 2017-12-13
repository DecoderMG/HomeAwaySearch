package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/12/2017.
 */

public class ListingAdHeadlinePhoto implements Serializable {

    @SerializedName("photo")
    @Expose
    private ListingAdPhoto photo;
    @SerializedName("unitNumber")
    @Expose
    private int unitNumber;

    public ListingAdHeadlinePhoto() {
    }

    public ListingAdHeadlinePhoto(ListingAdPhoto photo, int unitNumber) {
        this.photo = photo;
        this.unitNumber = unitNumber;
    }

    public ListingAdPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(ListingAdPhoto photo) {
        this.photo = photo;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }
}
