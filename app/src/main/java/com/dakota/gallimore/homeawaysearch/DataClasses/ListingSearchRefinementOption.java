package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingSearchRefinementOption {

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;

    public ListingSearchRefinementOption() {
    }

    public ListingSearchRefinementOption(int count, String title, String url) {
        this.count = count;
        this.title = title;
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
