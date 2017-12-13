package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 11/2/2017.
 * Class containing location data for listings
 */

public class ListingLocation implements Serializable {

    @SerializedName("lat")
    @Expose
    private double latitude;
    @SerializedName("lng")
    @Expose
    private double longitude;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;

    public ListingLocation() {
        latitude = 0.0;
        longitude = 0.0;
        city = "";
        state = "";
        country = "";
    }

    public ListingLocation(double latitude, double longitude, String city,
                           String state, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
