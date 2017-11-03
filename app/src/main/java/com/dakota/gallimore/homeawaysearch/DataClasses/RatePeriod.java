package com.dakota.gallimore.homeawaysearch.DataClasses;

import java.util.Date;

/**
 * Created by galli_000 on 11/3/2017.
 */

public class RatePeriod {

    private Date arrivalDate;
    private Date leaveDate;

    private int minimumStay;

    private int weeklyRate;
    private String currency;

    public RatePeriod() {

    }

    public RatePeriod(Date arrivalDate, Date leaveDate,
                      int minimumStay, int weeklyRate,
                      String currency) {
        this.arrivalDate = arrivalDate;
        this.leaveDate = leaveDate;
        this.minimumStay = minimumStay;
        this.weeklyRate = weeklyRate;
        this.currency = currency;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public int getMinimumStay() {
        return minimumStay;
    }

    public void setMinimumStay(int minimumStay) {
        this.minimumStay = minimumStay;
    }

    public int getWeeklyRate() {
        return weeklyRate;
    }

    public void setWeeklyRate(int weeklyRate) {
        this.weeklyRate = weeklyRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
