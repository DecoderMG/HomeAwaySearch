package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class UnitAvailabilityConfiguration implements Serializable {

    @SerializedName("availability")
    @Expose
    private String availability;
    @SerializedName("availableUnitCount")
    @Expose
    private String availableUnitCount;
    @SerializedName("changeOver")
    @Expose
    private String changeOver;
    @SerializedName("maxStay")
    @Expose
    private String maxStay;
    @SerializedName("minPriorNotify")
    @Expose
    private String minPriorNotify;
    @SerializedName("minStay")
    @Expose
    private String minStay;
    @SerializedName("stayIncrement")
    @Expose
    private String stayIncrement;

    public UnitAvailabilityConfiguration() {
    }

    public UnitAvailabilityConfiguration(String availability, String availableUnitCount, String changeOver, String maxStay, String minPriorNotify, String minStay, String stayIncrement) {
        this.availability = availability;
        this.availableUnitCount = availableUnitCount;
        this.changeOver = changeOver;
        this.maxStay = maxStay;
        this.minPriorNotify = minPriorNotify;
        this.minStay = minStay;
        this.stayIncrement = stayIncrement;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAvailableUnitCount() {
        return availableUnitCount;
    }

    public void setAvailableUnitCount(String availableUnitCount) {
        this.availableUnitCount = availableUnitCount;
    }

    public String getChangeOver() {
        return changeOver;
    }

    public void setChangeOver(String changeOver) {
        this.changeOver = changeOver;
    }

    public String getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(String maxStay) {
        this.maxStay = maxStay;
    }

    public String getMinPriorNotify() {
        return minPriorNotify;
    }

    public void setMinPriorNotify(String minPriorNotify) {
        this.minPriorNotify = minPriorNotify;
    }

    public String getMinStay() {
        return minStay;
    }

    public void setMinStay(String minStay) {
        this.minStay = minStay;
    }

    public String getStayIncrement() {
        return stayIncrement;
    }

    public void setStayIncrement(String stayIncrement) {
        this.stayIncrement = stayIncrement;
    }
}
