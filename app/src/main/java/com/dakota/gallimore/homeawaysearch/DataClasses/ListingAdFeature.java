package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 11/3/2017.
 * Class containing information about a listing or rooms unique feature. (I.E: Washer and Dryer Connections)
 */

public class ListingAdFeature implements Serializable {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("localizedName")
    @Expose
    private String localizedName;

    public ListingAdFeature() {
    }

    public ListingAdFeature(int count, String category, String description, String localizedName) {
        this.count = count;
        this.category = category;
        this.description = description;
        this.localizedName = localizedName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
