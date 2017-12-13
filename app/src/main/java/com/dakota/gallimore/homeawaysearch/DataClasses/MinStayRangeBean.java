package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class MinStayRangeBean implements Serializable {
    @SerializedName("minStayHigh")
    @Expose
    private int minStayHigh;
    @SerializedName("minStayLow")
    @Expose
    private int minStayLow;

    public MinStayRangeBean() {
    }

    public MinStayRangeBean(int minStayHigh, int minStayLow) {
        this.minStayHigh = minStayHigh;
        this.minStayLow = minStayLow;
    }

    public int getMinStayHigh() {
        return minStayHigh;
    }

    public void setMinStayHigh(int minStayHigh) {
        this.minStayHigh = minStayHigh;
    }

    public int getMinStayLow() {
        return minStayLow;
    }

    public void setMinStayLow(int minStayLow) {
        this.minStayLow = minStayLow;
    }
}
