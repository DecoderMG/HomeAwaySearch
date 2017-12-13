package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingSearchHit implements Serializable {

    @SerializedName("accommodations")
    @Expose
    private String accommodations;
    @SerializedName("bathrooms")
    @Expose
    private double bathrooms;
    @SerializedName("bedrooms")
    @Expose
    private int bedrooms;
    @SerializedName("bookWithConfidence")
    @Expose
    private boolean bookWithConfidence;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("detailsUrl")
    @Expose
    private String detailsUrl;
    @SerializedName("headline")
    @Expose
    private String headline;
    @SerializedName("listingId")
    @Expose
    private String listingId;
    @SerializedName("listingSource")
    @Expose
    private String listingSource;
    @SerializedName("listingUrl")
    @Expose
    private String listingUrl;
    @SerializedName("location")
    @Expose
    private ListingLocation location;
    @SerializedName("priceQuote")
    @Expose
    private PriceQuoteBean priceQuote;
    @SerializedName("priceRanges")
    @Expose
    private ArrayList<PriceRangeBean> priceRanges;
    @SerializedName("regionPath")
    @Expose
    private String regionPath;
    @SerializedName("reviewAverage")
    @Expose
    private double reviewAverage;
    @SerializedName("reviewCount")
    @Expose
    private int reviewCount;
    @SerializedName("thumbnail")
    @Expose
    private ImageFileBean thumbnail;

    public ListingSearchHit() {
    }

    public ListingSearchHit(String accommodations, double bathrooms, int bedrooms, boolean bookWithConfidence, String description, String detailsUrl, String headline, String listingId, String listingSource, String listingUrl, ListingLocation location, PriceQuoteBean priceQuote, ArrayList<PriceRangeBean> priceRanges, String regionPath, double reviewAverage, int reviewCount, ImageFileBean thumbnail) {
        this.accommodations = accommodations;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.bookWithConfidence = bookWithConfidence;
        this.description = description;
        this.detailsUrl = detailsUrl;
        this.headline = headline;
        this.listingId = listingId;
        this.listingSource = listingSource;
        this.listingUrl = listingUrl;
        this.location = location;
        this.priceQuote = priceQuote;
        this.priceRanges = priceRanges;
        this.regionPath = regionPath;
        this.reviewAverage = reviewAverage;
        this.reviewCount = reviewCount;
        this.thumbnail = thumbnail;
    }

    public String getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(String accommodations) {
        this.accommodations = accommodations;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public boolean isBookWithConfidence() {
        return bookWithConfidence;
    }

    public void setBookWithConfidence(boolean bookWithConfidence) {
        this.bookWithConfidence = bookWithConfidence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
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

    public ListingLocation getLocation() {
        return location;
    }

    public void setLocation(ListingLocation location) {
        this.location = location;
    }

    public PriceQuoteBean getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(PriceQuoteBean priceQuote) {
        this.priceQuote = priceQuote;
    }

    public ArrayList<PriceRangeBean> getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(ArrayList<PriceRangeBean> priceRanges) {
        this.priceRanges = priceRanges;
    }

    public String getRegionPath() {
        return regionPath;
    }

    public void setRegionPath(String regionPath) {
        this.regionPath = regionPath;
    }

    public double getReviewAverage() {
        return reviewAverage;
    }

    public void setReviewAverage(double reviewAverage) {
        this.reviewAverage = reviewAverage;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public ImageFileBean getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageFileBean thumbnail) {
        this.thumbnail = thumbnail;
    }
}
