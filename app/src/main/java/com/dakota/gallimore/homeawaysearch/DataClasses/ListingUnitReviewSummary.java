package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingUnitReviewSummary implements Serializable {

    @SerializedName("averageRating")
    @Expose
    private float averageRating;
    @SerializedName("fiveStarReviewCount")
    @Expose
    private int fiveStarReviewCount;
    @SerializedName("fourStarReviewCount")
    @Expose
    private int fourStarReviewCount;
    @SerializedName("threeStarReviewCount")
    @Expose
    private int threeStarReviewCount;
    @SerializedName("twoStarReviewCount")
    @Expose
    private int twoStarReviewCount;
    @SerializedName("oneStarReviewCount")
    @Expose
    private int oneStarReviewCount;
    @SerializedName("reviewCount")
    @Expose
    private int reviewCount;
    @SerializedName("guestbookReviewCount")
    @Expose
    private int guestbookReviewCount;

    public ListingUnitReviewSummary() {
    }

    public ListingUnitReviewSummary(float averageRating, int fiveStarReviewCount, int fourStarReviewCount, int threeStarReviewCount, int twoStarReviewCount, int oneStarReviewCount, int reviewCount, int guestbookReviewCount) {
        this.averageRating = averageRating;
        this.fiveStarReviewCount = fiveStarReviewCount;
        this.fourStarReviewCount = fourStarReviewCount;
        this.threeStarReviewCount = threeStarReviewCount;
        this.twoStarReviewCount = twoStarReviewCount;
        this.oneStarReviewCount = oneStarReviewCount;
        this.reviewCount = reviewCount;
        this.guestbookReviewCount = guestbookReviewCount;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getFiveStarReviewCount() {
        return fiveStarReviewCount;
    }

    public void setFiveStarReviewCount(int fiveStarReviewCount) {
        this.fiveStarReviewCount = fiveStarReviewCount;
    }

    public int getFourStarReviewCount() {
        return fourStarReviewCount;
    }

    public void setFourStarReviewCount(int fourStarReviewCount) {
        this.fourStarReviewCount = fourStarReviewCount;
    }

    public int getThreeStarReviewCount() {
        return threeStarReviewCount;
    }

    public void setThreeStarReviewCount(int threeStarReviewCount) {
        this.threeStarReviewCount = threeStarReviewCount;
    }

    public int getTwoStarReviewCount() {
        return twoStarReviewCount;
    }

    public void setTwoStarReviewCount(int twoStarReviewCount) {
        this.twoStarReviewCount = twoStarReviewCount;
    }

    public int getOneStarReviewCount() {
        return oneStarReviewCount;
    }

    public void setOneStarReviewCount(int oneStarReviewCount) {
        this.oneStarReviewCount = oneStarReviewCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getGuestbookReviewCount() {
        return guestbookReviewCount;
    }

    public void setGuestbookReviewCount(int guestbookReviewCount) {
        this.guestbookReviewCount = guestbookReviewCount;
    }
}
