package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ListingSearchRefinement implements Serializable {

    @SerializedName("fieldName")
    @Expose
    private String fieldName;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("options")
    @Expose
    private ArrayList<ListingSearchRefinementOption> options;

    public ListingSearchRefinement() {
    }

    public ListingSearchRefinement(String fieldName, String key, ArrayList<ListingSearchRefinementOption> options) {
        this.fieldName = fieldName;
        this.key = key;
        this.options = options;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<ListingSearchRefinementOption> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<ListingSearchRefinementOption> options) {
        this.options = options;
    }
}
