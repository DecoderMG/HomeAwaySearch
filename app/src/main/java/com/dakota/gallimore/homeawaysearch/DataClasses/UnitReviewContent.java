package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class UnitReviewContent implements Serializable {

    @SerializedName("entries")
    @Expose
    private ArrayList<Review> entries;
    @SerializedName("nextPage")
    @Expose
    private String nextPage;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("pageSize")
    @Expose
    private int pageSize;
    @SerializedName("prevPage")
    @Expose
    private String prevPage;
    @SerializedName("size")
    @Expose
    private int size;

    public UnitReviewContent() {
    }

    public UnitReviewContent(ArrayList<Review> entries, String nextPage, int page, int pageSize, String prevPage, int size) {
        this.entries = entries;
        this.nextPage = nextPage;
        this.page = page;
        this.pageSize = pageSize;
        this.prevPage = prevPage;
        this.size = size;
    }

    public ArrayList<Review> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Review> entries) {
        this.entries = entries;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(String prevPage) {
        this.prevPage = prevPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
