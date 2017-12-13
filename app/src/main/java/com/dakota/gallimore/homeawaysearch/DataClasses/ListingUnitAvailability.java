package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingUnitAvailability implements Serializable {

    @SerializedName("availabilityDefault")
    @Expose
    private String availabilityDefault;
    @SerializedName("changeOverDefault")
    @Expose
    private String changeOverDefault;
    @SerializedName("dateRange")
    @Expose
    private DateRange dateRange;
    @SerializedName("maxStayDefault")
    @Expose
    private int maxStayDefault;
    @SerializedName("minPriorNotifyDefault")
    @Expose
    private int minPriorNotifyDefault;
    @SerializedName("minStayDefault")
    @Expose
    private int minStayDefault;
    @SerializedName("stayIncrementDefault")
    @Expose
    private String stayIncrementDefault;
    @SerializedName("unitAvailibilityConfiguration")
    @Expose
    private UnitAvailabilityConfiguration unitAvailabilityConfiguration;

    public ListingUnitAvailability() {
    }

    public ListingUnitAvailability(String availabilityDefault, String changeOverDefault, DateRange dateRange, int maxStayDefault, int minPriorNotifyDefault, int minStayDefault, String stayIncrementDefault, UnitAvailabilityConfiguration unitAvailabilityConfiguration) {
        this.availabilityDefault = availabilityDefault;
        this.changeOverDefault = changeOverDefault;
        this.dateRange = dateRange;
        this.maxStayDefault = maxStayDefault;
        this.minPriorNotifyDefault = minPriorNotifyDefault;
        this.minStayDefault = minStayDefault;
        this.stayIncrementDefault = stayIncrementDefault;
        this.unitAvailabilityConfiguration = unitAvailabilityConfiguration;
    }

    public String getAvailabilityDefault() {
        return availabilityDefault;
    }

    public void setAvailabilityDefault(String availabilityDefault) {
        this.availabilityDefault = availabilityDefault;
    }

    public String getChangeOverDefault() {
        return changeOverDefault;
    }

    public void setChangeOverDefault(String changeOverDefault) {
        this.changeOverDefault = changeOverDefault;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public int getMaxStayDefault() {
        return maxStayDefault;
    }

    public void setMaxStayDefault(int maxStayDefault) {
        this.maxStayDefault = maxStayDefault;
    }

    public int getMinPriorNotifyDefault() {
        return minPriorNotifyDefault;
    }

    public void setMinPriorNotifyDefault(int minPriorNotifyDefault) {
        this.minPriorNotifyDefault = minPriorNotifyDefault;
    }

    public int getMinStayDefault() {
        return minStayDefault;
    }

    public void setMinStayDefault(int minStayDefault) {
        this.minStayDefault = minStayDefault;
    }

    public String getStayIncrementDefault() {
        return stayIncrementDefault;
    }

    public void setStayIncrementDefault(String stayIncrementDefault) {
        this.stayIncrementDefault = stayIncrementDefault;
    }

    public UnitAvailabilityConfiguration getUnitAvailabilityConfiguration() {
        return unitAvailabilityConfiguration;
    }

    public void setUnitAvailabilityConfiguration(UnitAvailabilityConfiguration unitAvailabilityConfiguration) {
        this.unitAvailabilityConfiguration = unitAvailabilityConfiguration;
    }
}
