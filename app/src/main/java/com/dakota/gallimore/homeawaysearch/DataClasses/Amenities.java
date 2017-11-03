package com.dakota.gallimore.homeawaysearch.DataClasses;

/**
 * Created by galli_000 on 11/3/2017.
 * Class that keeps track of room amenities
 */

public class Amenities {

    private int count;
    private String category;
    private String description;
    private String localizedName;

    public Amenities() {
    }

    public Amenities(int count, String category,
                     String description, String localizedName) {
        this.count = count;
        this.category = category;
        this.description = description;
        this.localizedName = localizedName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }
}
