package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/12/2017.
 */

public class PhotoImage implements Serializable {

    @SerializedName("dimension")
    @Expose
    private Dimension dimensions;

    @SerializedName("uri")
    @Expose
    private String url;

    public PhotoImage() {
    }

    public PhotoImage(Dimension dimensions, String url) {
        this.dimensions = dimensions;
        this.url = url;
    }

    public Dimension getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimension dimensions) {
        this.dimensions = dimensions;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
