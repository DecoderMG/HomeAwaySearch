package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class UnitContent implements Serializable {

    @SerializedName("area")
    @Expose
    private int area;
    @SerializedName("areaUnit")
    @Expose
    private String areaUnit;
    @SerializedName("bathroomDetails")
    @Expose
    private String bathroomDetails;
    @SerializedName("bathrooms")
    @Expose
    private ArrayList<ListingAdRoom> bathrooms;
    @SerializedName("bedroomDetails")
    @Expose
    private String bedroomDetails;
    @SerializedName("bedrooms")
    @Expose
    private ArrayList<ListingAdRoom> bedrooms;
    @SerializedName("diningSeating")
    @Expose
    private int diningSeating;
    @SerializedName("features")
    @Expose
    private ArrayList<ListingAdFeature> features;
    @SerializedName("maxSleep")
    @Expose
    private int maxSleep;
    @SerializedName("maxSleepInBeds")
    @Expose
    private int maxSleepInBeds;
    @SerializedName("numberOfBathrooms")
    @Expose
    private double numberOfBathrooms;
    @SerializedName("numberOfBedrooms")
    @Expose
    private double numberOfBedrooms;
    @SerializedName("propertyType")
    @Expose
    private String propertyType;

    public UnitContent() {
    }

    public UnitContent(int area, String areaUnit, String bathroomDetails, ArrayList<ListingAdRoom> bathrooms, String bedroomDetails, ArrayList<ListingAdRoom> bedrooms, int diningSeating, ArrayList<ListingAdFeature> features, int maxSleep, int maxSleepInBeds, double numberOfBathrooms, double numberOfBedrooms, String propertyType) {
        this.area = area;
        this.areaUnit = areaUnit;
        this.bathroomDetails = bathroomDetails;
        this.bathrooms = bathrooms;
        this.bedroomDetails = bedroomDetails;
        this.bedrooms = bedrooms;
        this.diningSeating = diningSeating;
        this.features = features;
        this.maxSleep = maxSleep;
        this.maxSleepInBeds = maxSleepInBeds;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.propertyType = propertyType;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public String getBathroomDetails() {
        return bathroomDetails;
    }

    public void setBathroomDetails(String bathroomDetails) {
        this.bathroomDetails = bathroomDetails;
    }

    public ArrayList<ListingAdRoom> getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(ArrayList<ListingAdRoom> bathrooms) {
        this.bathrooms = bathrooms;
    }

    public String getBedroomDetails() {
        return bedroomDetails;
    }

    public void setBedroomDetails(String bedroomDetails) {
        this.bedroomDetails = bedroomDetails;
    }

    public ArrayList<ListingAdRoom> getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(ArrayList<ListingAdRoom> bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getDiningSeating() {
        return diningSeating;
    }

    public void setDiningSeating(int diningSeating) {
        this.diningSeating = diningSeating;
    }

    public ArrayList<ListingAdFeature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<ListingAdFeature> features) {
        this.features = features;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

    public void setMaxSleep(int maxSleep) {
        this.maxSleep = maxSleep;
    }

    public int getMaxSleepInBeds() {
        return maxSleepInBeds;
    }

    public void setMaxSleepInBeds(int maxSleepInBeds) {
        this.maxSleepInBeds = maxSleepInBeds;
    }

    public double getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(double numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public double getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(double numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
