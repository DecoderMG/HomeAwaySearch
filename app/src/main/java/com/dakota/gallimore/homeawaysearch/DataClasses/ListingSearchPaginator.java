package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingSearchPaginator implements Serializable {

    @SerializedName("entries")
    @Expose
    private ArrayList<ListingSearchHit> entries;
    @SerializedName("nextPage")
    @Expose
    private String nextPage;
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("pageCount")
    @Expose
    private int pageCount;
    @SerializedName("pageSize")
    @Expose
    private int pageSize;
    @SerializedName("prevPage")
    @Expose
    private String prevPage;
    @SerializedName("refinements")
    @Expose
    private ArrayList<ListingSearchRefinement> refinements;
    @SerializedName("size")
    @Expose
    private int size;

    public ListingSearchPaginator() {
    }

    public ListingSearchPaginator(ArrayList<ListingSearchHit> entries, String nextPage, int page, int pageCount, int pageSize, String prevPage, ArrayList<ListingSearchRefinement> refinements, int size) {
        this.entries = entries;
        this.nextPage = nextPage;
        this.page = page;
        this.pageCount = pageCount;
        this.pageSize = pageSize;
        this.prevPage = prevPage;
        this.refinements = refinements;
        this.size = size;
    }

    public ArrayList<ListingSearchHit> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<ListingSearchHit> entries) {
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

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
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

    public ArrayList<ListingSearchRefinement> getRefinements() {
        return refinements;
    }

    public void setRefinements(ArrayList<ListingSearchRefinement> refinements) {
        this.refinements = refinements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
