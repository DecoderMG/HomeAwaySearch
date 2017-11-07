package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/7/2017.
 */

public class PriceQuote {

    private String currencyUnits;
    private double amount;
    private double other;
    private double tax;
    private double averageNightly;
    private double fullyLoadedPriceQuote;
    private double rent;
    private double fees;
    private double travelerFee;

    public PriceQuote() {
    }

    public PriceQuote(String currencyUnits, double amount, double other,
                      double tax, double averageNightly, double fullyLoadedPriceQuote,
                      double rent, double fees, double travelerFee) {
        this.currencyUnits = currencyUnits;
        this.amount = amount;
        this.other = other;
        this.tax = tax;
        this.averageNightly = averageNightly;
        this.fullyLoadedPriceQuote = fullyLoadedPriceQuote;
        this.rent = rent;
        this.fees = fees;
        this.travelerFee = travelerFee;
    }

    public String getCurrencyUnits() {
        return currencyUnits;
    }

    public void setCurrencyUnits(String currencyUnits) {
        this.currencyUnits = currencyUnits;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getAverageNightly() {
        return averageNightly;
    }

    public void setAverageNightly(double averageNightly) {
        this.averageNightly = averageNightly;
    }

    public double getFullyLoadedPriceQuote() {
        return fullyLoadedPriceQuote;
    }

    public void setFullyLoadedPriceQuote(double fullyLoadedPriceQuote) {
        this.fullyLoadedPriceQuote = fullyLoadedPriceQuote;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public double getTravelerFee() {
        return travelerFee;
    }

    public void setTravelerFee(double travelerFee) {
        this.travelerFee = travelerFee;
    }
}
