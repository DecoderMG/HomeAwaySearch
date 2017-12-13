package com.dakota.gallimore.homeawaysearch.DataClasses;

import android.util.Log;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by galli_000 on 11/7/2017.
 */

public class SearchListing implements Serializable {
    ArrayList<PriceRangeBean> priceRangeBeans;
    private String headline;
    private String accommodations;
    private ListingLocation listingLocation;
    private double bathrooms;
    private double bedrooms;
    private URL detailsUrl;
    private boolean bookWithConfidence;
    private String listingId;
    private String thumbnailUrl;
    private String description;
    private int reviewCount;
    private String listingSource;
    private String listingUrl;
    private double reviewAverage;
    private PriceQuote priceQuote;

    public SearchListing() {
    }

    public SearchListing(String headline, String accommodations, ListingLocation listingLocation,
                         double bathrooms, double bedrooms,
                         URL detailsUrl, boolean bookWithConfidence,
                         String listingId, String thumbnailUrl, String description,
                         int reviewCount, String listingSource, String listingUrl,
                         double reviewAverage, PriceQuote priceQuote) {
        this.headline = headline;
        this.accommodations = accommodations;
        this.listingLocation = listingLocation;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.detailsUrl = detailsUrl;
        this.bookWithConfidence = bookWithConfidence;
        this.listingId = listingId;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.reviewCount = reviewCount;
        this.listingSource = listingSource;
        this.listingUrl = listingUrl;
        this.reviewAverage = reviewAverage;
        this.priceQuote = priceQuote;
    }

    public SearchListing(String headline, String accommodations, ListingLocation listingLocation,
                         double bathrooms, double bedrooms, URL detailsUrl,
                         boolean bookWithConfidence, String listingId, String thumbnailUrl,
                         String description, int reviewCount, String listingSource,
                         String listingUrl, double reviewAverage, ArrayList<PriceRangeBean> priceRangeBeans,
                         PriceQuote priceQuote) {
        this.headline = headline;
        this.accommodations = accommodations;
        this.listingLocation = listingLocation;
        this.bathrooms = bathrooms;
        this.bedrooms = bedrooms;
        this.detailsUrl = detailsUrl;
        this.bookWithConfidence = bookWithConfidence;
        this.listingId = listingId;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.reviewCount = reviewCount;
        this.listingSource = listingSource;
        this.listingUrl = listingUrl;
        this.reviewAverage = reviewAverage;
        this.priceRangeBeans = priceRangeBeans;
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

    public ListingLocation getListingLocation() {
        return listingLocation;
    }

    public void setListingLocation(ListingLocation listingLocation) {
        this.listingLocation = listingLocation;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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

    public ArrayList<PriceRangeBean> getPriceRangeBeans() {
        return priceRangeBeans;
    }

    public void setPriceRangeBeans(ArrayList<PriceRangeBean> priceRangeBeans) {
        this.priceRangeBeans = priceRangeBeans;
    }

    public void addPriceRange(PriceRangeBean priceRangeBean) {
        if (priceRangeBean != null) {
            priceRangeBeans.add(priceRangeBean);
        } else {
            Log.d("Search Listing Log: ", "Unable to add null Price Range to Search Listing");
        }
    }

    public void removePriceRange(PriceRangeBean priceRangeBean) {
        if (priceRangeBean != null && priceRangeBeans.contains(priceRangeBean)) {
            priceRangeBeans.remove(priceRangeBean);
        } else {
            Log.d("Search Listing Log: ", "Price range is either null or not associated with Search Listing");
        }
    }

    public PriceRangeBean getPriceRange(int i) {
        if (i < priceRangeBeans.size()) {
            return priceRangeBeans.get(i);
        } else {
            Log.d("Search Listing Log: ", "Error: Attempting to get price range outside the index of the Search Listing Price Ranges");
            return null;
        }
    }

    public PriceQuote getPriceQuote() {
        return priceQuote;
    }

    public void setPriceQuote(PriceQuote priceQuote) {
        this.priceQuote = priceQuote;
    }
}