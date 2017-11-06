package com.dakota.gallimore.homeawaysearch.DataClasses;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/6/2017.
 * Class containing all information relating to a HomeAway listing
 */

public class Listing {
    private String listingId;
    private String listingUrl;
    private String sourceLocale;
    private String sourceLocaleName;
    private String description;
    private String headline;
    private ArrayList<Feature> features;
    private Location location;
    private ArrayList<Site> sites;
    private ArrayList<ListingMedia> photos;
    private ArrayList<Unit> units;

    public Listing() {
    }

    public Listing(String listingId, String listingUrl, String sourceLocale,
                   String sourceLocaleName, String description, String headline,
                   ArrayList<Feature> features, Location location, ArrayList<Site> sites,
                   ArrayList<ListingMedia> photos, ArrayList<Unit> units) {
        this.listingId = listingId;
        this.listingUrl = listingUrl;
        this.sourceLocale = sourceLocale;
        this.sourceLocaleName = sourceLocaleName;
        this.description = description;
        this.headline = headline;
        this.features = features;
        this.location = location;
        this.sites = sites;
        this.photos = photos;
        this.units = units;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getListingUrl() {
        return listingUrl;
    }

    public void setListingUrl(String listingUrl) {
        this.listingUrl = listingUrl;
    }

    public String getSourceLocale() {
        return sourceLocale;
    }

    public void setSourceLocale(String sourceLocale) {
        this.sourceLocale = sourceLocale;
    }

    public String getSourceLocaleName() {
        return sourceLocaleName;
    }

    public void setSourceLocaleName(String sourceLocaleName) {
        this.sourceLocaleName = sourceLocaleName;
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

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public void addFeature(Feature feature) {
        if (feature != null) {
            this.features.add(feature);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null feature");
        }
    }

    public void removeFeature(Feature feature) {
        if (feature != null && this.features.contains(feature)) {
            this.features.remove(feature);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Feature not associated with Listing");
        }
    }

    public Feature getFeature(int i) {
        if (i < this.features.size()) {
            return this.features.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get feature outside of Feature array");
            return null;
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Site> sites) {
        this.sites = sites;
    }

    public void addSite(Site site) {
        if (site != null) {
            this.sites.add(site);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null Site");
        }
    }

    public void removeSite(Site site) {
        if (site != null && this.sites.contains(site)) {
            this.sites.remove(site);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Site not associated with Listing");
        }
    }

    public Site getSite(int i) {
        if (i < this.sites.size()) {
            return this.sites.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get site outside of Site array");
            return null;
        }
    }

    public ArrayList<ListingMedia> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<ListingMedia> photos) {
        this.photos = photos;
    }

    public void addPhoto(ListingMedia listingMedia) {
        if (listingMedia != null) {
            this.photos.add(listingMedia);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null photo, check ListingMedia");
        }
    }

    public void removePhoto(ListingMedia listingMedia) {
        if (listingMedia != null && this.photos.contains(listingMedia)) {
            this.photos.remove(listingMedia);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Listing photo not associated with Listing");
        }
    }

    public ListingMedia getPhoto(int i) {
        if (i < this.photos.size()) {
            return this.photos.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get listing media outside of Photo array");
            return null;
        }
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void addUnit(Unit unit) {
        if (unit != null) {
            this.units.add(unit);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null unit");
        }
    }

    public void removeUnit(Unit unit) {
        if (unit != null && this.units.contains(unit)) {
            this.units.remove(unit);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Unit not associated with Listing");
        }
    }

    public Unit getUnit(int i) {
        if (i < this.units.size()) {
            return this.units.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get unit outside of unit array");
            return null;
        }
    }
}
