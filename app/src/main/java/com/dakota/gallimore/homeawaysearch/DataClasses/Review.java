package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 11/3/2017.
 * Class that holds review data for units.
 */

public class Review implements Serializable {

    @SerializedName("arrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("reviewerName")
    @Expose
    private String reviewerName;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("helpfulCount")
    @Expose
    private int helpfulCount;
    @SerializedName("unhelpfulCount")
    @Expose
    private int unhelpfulCount;
    @SerializedName("reviewLocales")
    @Expose
    private String reviewLocale;

    public Review() {

    }

    public Review(String arrivalDate, String reviewerName, String body,
                  String headline, int helpfulCount, int unhelpfulCount,
                  String reviewLocale) {
        this.arrivalDate = arrivalDate;
        this.reviewerName = reviewerName;
        this.body = body;
        this.headline = headline;
        this.helpfulCount = helpfulCount;
        this.unhelpfulCount = unhelpfulCount;
        this.reviewLocale = reviewLocale;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public int getHelpfulCount() {
        return helpfulCount;
    }

    public void setHelpfulCount(int helpfulCount) {
        this.helpfulCount = helpfulCount;
    }

    public int getUnhelpfulCount() {
        return unhelpfulCount;
    }

    public void setUnhelpfulCount(int unhelpfulCount) {
        this.unhelpfulCount = unhelpfulCount;
    }

    public String getReviewLocale() {
        return reviewLocale;
    }

    public void setReviewLocale(String reviewLocale) {
        this.reviewLocale = reviewLocale;
    }
}