package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by galli_000 on 12/12/2017.
 */

public class ListingUnitRatePeriod implements Serializable {

    @SerializedName("dateRange")
    @Expose
    private DateRange dateRange;
    @SerializedName("minimumStay")
    @Expose
    private int minimumStay;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("rates")
    @Expose
    private Map<String, ListingUnitRate> rates;

    public ListingUnitRatePeriod() {
    }

    public ListingUnitRatePeriod(DateRange dateRange, int minimumStay, String name, String note, Map<String, ListingUnitRate> rates) {
        this.dateRange = dateRange;
        this.minimumStay = minimumStay;
        this.name = name;
        this.note = note;
        this.rates = rates;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public int getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(int minimumStay) {
        this.minimumStay = minimumStay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Map<String, ListingUnitRate> getRates() {
        return rates;
    }

    public void setRates(Map<String, ListingUnitRate> rates) {
        this.rates = rates;
    }
}
