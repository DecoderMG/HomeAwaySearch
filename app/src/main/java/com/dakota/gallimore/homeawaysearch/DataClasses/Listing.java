package com.dakota.gallimore.homeawaysearch.DataClasses;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 11/6/2017.
 * Class containing all information relating to a HomeAway listing
 */

public class Listing implements Serializable {

    @SerializedName("listingId")
    @Expose
    private String listingId;

    @SerializedName("listingUrl")
    @Expose
    private String listingUrl;
    @SerializedName("sourceLocale")
    @Expose
    private String sourceLocale;
    @SerializedName("sourceLocaleName")
    @Expose
    private String sourceLocaleName;
    @SerializedName("adContent")
    @Expose
    private AdContent adContent;

    @SerializedName("listingAdFeatures")
    @Expose
    private ArrayList<ListingAdFeature> listingAdFeatures;
    @SerializedName("listingLocation")
    @Expose
    private ListingLocation listingLocation;
    @SerializedName("sites")
    @Expose
    private ArrayList<Link> sites;
    @SerializedName("photos")
    @Expose
    private ListingMedia photos;

    @SerializedName("units")
    @Expose
    private ArrayList<Unit> units;

    public Listing() {
    }

    public Listing(String listingId, String listingUrl, String sourceLocale,
                   String sourceLocaleName, AdContent adContent,
                   ArrayList<ListingAdFeature> listingAdFeatures, ListingLocation listingLocation, ArrayList<Link> sites,
                   ListingMedia photos, ArrayList<Unit> units) {
        this.listingId = listingId;
        this.listingUrl = listingUrl;
        this.sourceLocale = sourceLocale;
        this.sourceLocaleName = sourceLocaleName;
        this.adContent = adContent;
        this.listingAdFeatures = listingAdFeatures;
        this.listingLocation = listingLocation;
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

    public AdContent getAdContent() {
        return adContent;
    }

    public void setAdContent(AdContent adContent) {
        this.adContent = adContent;
    }

    public ArrayList<ListingAdFeature> getListingAdFeatures() {
        return listingAdFeatures;
    }

    public void setListingAdFeatures(ArrayList<ListingAdFeature> listingAdFeatures) {
        this.listingAdFeatures = listingAdFeatures;
    }

    public void addFeature(ListingAdFeature listingAdFeature) {
        if (listingAdFeature != null) {
            this.listingAdFeatures.add(listingAdFeature);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null listingAdFeature");
        }
    }

    public void removeFeature(ListingAdFeature listingAdFeature) {
        if (listingAdFeature != null && this.listingAdFeatures.contains(listingAdFeature)) {
            this.listingAdFeatures.remove(listingAdFeature);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "ListingAdFeature not associated with Listing");
        }
    }

    public ListingAdFeature getFeature(int i) {
        if (i < this.listingAdFeatures.size()) {
            return this.listingAdFeatures.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get feature outside of ListingAdFeature array");
            return null;
        }
    }

    public ListingLocation getListingLocation() {
        return listingLocation;
    }

    public void setListingLocation(ListingLocation listingLocation) {
        this.listingLocation = listingLocation;
    }

    public ArrayList<Link> getSites() {
        return sites;
    }

    public void setSites(ArrayList<Link> sites) {
        this.sites = sites;
    }

    public void addSite(Link link) {
        if (link != null) {
            this.sites.add(link);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Can not add null Site");
        }
    }

    public void removeSite(Link link) {
        if (link != null && this.sites.contains(link)) {
            this.sites.remove(link);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Site not associated with Listing");
        }
    }

    public Link getSite(int i) {
        if (i < this.sites.size()) {
            return this.sites.get(i);
        } else {
            Log.d(Constants.LISTING_CLASS_LOG_TAG, "Error: attempting to get site outside of Site array");
            return null;
        }
    }

    public ListingMedia getPhotos() {
        return photos;
    }

    public void setPhotos(ListingMedia photos) {
        this.photos = photos;
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
