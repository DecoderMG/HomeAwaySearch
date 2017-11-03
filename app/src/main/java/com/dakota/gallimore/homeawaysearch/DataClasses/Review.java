package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/3/2017.
 * Class that holds review data for units.
 */

public class Review {

    private String arrivalDate;
    private String reviewerName;
    private String body;
    private String headline;
    private int helpfulCount;
    private int unhelpfulCount;
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
