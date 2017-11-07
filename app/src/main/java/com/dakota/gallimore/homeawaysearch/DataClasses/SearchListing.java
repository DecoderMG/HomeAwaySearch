package com.dakota.gallimore.homeawaysearch.DataClasses;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by galli_000 on 11/7/2017.
 */

public class SearchListing {
    private String headline;
    private String accommodations;
    private Location location;
    private double bathrooms;
    private double bedrooms;
    private URL detailsUrl;
    private boolean bookWithConfidence;
    private String listingId;
    private Bitmap thumbnail;
    private String description;
    private int reviewCount;
    private String listingSource;
    private String listingUrl;
    private double reviewAverage;

    private double priceRangeTo;
    private double priceRangeFrom;
    private String priceRangePeriodType;
    private String priceRangeCurrency;

    private PriceQuote priceQuote;

    public SearchListing() {
    }

    public SearchListing(String headline, String accommodations, Location location,
                         double bathrooms, double bedrooms,
                         URL detailsUrl, boolean bookWithConfidence,
                         String listingId, Bitmap thumbnail, String description,
                         int reviewCount, String listingSource, String listingUrl,
                         double reviewAverage, PriceQuote priceQuote) {
        this.headline = headline;
        this.accommodations = accommodations;
        this.location = location;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.detailsUrl = detailsUrl;
        this.bookWithConfidence = bookWithConfidence;
        this.listingId = listingId;
        this.thumbnail = thumbnail;
        this.description = description;
        this.reviewCount = reviewCount;
        this.listingSource = listingSource;
        this.listingUrl = listingUrl;
        this.reviewAverage = reviewAverage;
        this.priceQuote = priceQuote;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(String accommodations) {
        this.accommodations = accommodations;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public URL getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(URL detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public boolean isBookWithConfidence() {
        return bookWithConfidence;
    }

    public void setBookWithConfidence(boolean bookWithConfidence) {
        this.bookWithConfidence = bookWithConfidence;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getListingSource() {
        return listingSource;
    }

    public void setListingSource(String listingSource) {
        this.listingSource = listingSource;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(double reviewAverage) {
        this.reviewAverage = reviewAverage;
    }

    public double getPriceRangeTo() {
        return priceRangeTo;
    }

    public void setPriceRangeTo(double priceRangeTo) {
        this.priceRangeTo = priceRangeTo;
    }

    public double getPriceRangeFrom() {
        return priceRangeFrom;
    }

    public void setPriceRangeFrom(double priceRangeFrom) {
        this.priceRangeFrom = priceRangeFrom;
    }

    public String getPriceRangePeriodType() {
        return priceRangePeriodType;
    }

    public void setPriceRangePeriodType(String priceRangePeriodType) {
        this.priceRangePeriodType = priceRangePeriodType;
    }

    public String getPriceRangeCurrency() {
        return priceRangeCurrency;
    }

    public void setPriceRangeCurrency(String priceRangeCurrency) {
        this.priceRangeCurrency = priceRangeCurrency;
    }

    public PriceQuote getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(PriceQuote priceQuote) {
        this.priceQuote = priceQuote;
    }
}