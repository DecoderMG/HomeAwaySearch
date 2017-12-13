package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/12/2017.
 */

public class AdContent implements Serializable {

    @SerializedName("accommodationsSummary")
    @Expose
    private String accommodationsSummary;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("headline")
    @Expose
    private String headline;

    public AdContent() {
        accommodationsSummary = "";
        description = "";
        headline = "";
    }

    public AdContent(String accommodationsSummary, String description, String headline) {
        this.accommodationsSummary = accommodationsSummary;
        this.description = description;
        this.headline = headline;
    }

    public String getAccommodationsSummary() {
        return accommodationsSummary;
    }

    public void setAccommodationsSummary(String accommodationsSummary) {
        this.accommodationsSummary = accommodationsSummary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }
}
