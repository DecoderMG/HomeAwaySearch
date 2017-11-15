package com.dakota.gallimore.homeawaysearch.DataClasses;

import java.io.Serializable;

/**
 * Created by galli_000 on 11/10/2017.
 */

public class PriceRange implements Serializable {

    private double to;
    private String currencyUnits;
    private String periodType;
    private double from;

    public PriceRange() {
    }

    public PriceRange(double to, String currencyUnits, String periodType, double from) {
        this.to = to;
        this.currencyUnits = currencyUnits;
        this.periodType = periodType;
        this.from = from;
    }

    public double getToPrice() {
        return to;
    }

    public void setToPrice(double to) {
        this.to = to;
    }

    public String getCurrencyUnits() {
        return currencyUnits;
    }

    public void setCurrencyUnits(String currencyUnits) {
        this.currencyUnits = currencyUnits;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public double getFromPrice() {
        return from;
    }

    public void setFromPrice(double from) {
        this.from = from;
    }
}
