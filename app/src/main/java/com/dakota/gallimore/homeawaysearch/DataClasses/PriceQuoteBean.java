package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class PriceQuoteBean implements Serializable {

    @SerializedName("amount")
    @Expose
    private double amount;
    @SerializedName("averageNightly")
    @Expose
    private double averageNightly;
    @SerializedName("currencyUnits")
    @Expose
    private String currencyUnits;
    @SerializedName("fees")
    @Expose
    private double fees;
    @SerializedName("other")
    @Expose
    private double other;
    @SerializedName("rent")
    @Expose
    private double rent;
    @SerializedName("tax")
    @Expose
    private double tax;

    public PriceQuoteBean() {
    }

    public PriceQuoteBean(double amount, double averageNightly, String currencyUnits, double fees, double other, double rent, double tax) {
        this.amount = amount;
        this.averageNightly = averageNightly;
        this.currencyUnits = currencyUnits;
        this.fees = fees;
        this.other = other;
        this.rent = rent;
        this.tax = tax;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAverageNightly() {
        return averageNightly;
    }

    public void setAverageNightly(double averageNightly) {
        this.averageNightly = averageNightly;
    }

    public String getCurrencyUnits() {
        return currencyUnits;
    }

    public void setCurrencyUnits(String currencyUnits) {
        this.currencyUnits = currencyUnits;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
}
