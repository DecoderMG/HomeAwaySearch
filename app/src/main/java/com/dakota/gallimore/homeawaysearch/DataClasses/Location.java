package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/2/2017.
 * Class containing location data for listings
 */

public class Location {

    private double latitude;
    private double longitude;
    private String city;
    private String state;
    private String country;

    public Location() {
    }

    public Location(double latitude, double longitude, String city,
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
