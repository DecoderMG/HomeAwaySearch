package com.dakota.gallimore.homeawaysearch.DataClasses;

import java.net.URL;

/**
 * Created by galli_000 on 11/6/2017.
 */

public class ListingMedia {
    private String caption;
    private int height;
    private int width;
    private String imageType;
    private URL Uri;
    private int unitNumber;

    public ListingMedia() {
    }

    public ListingMedia(String caption, int height, int width, String imageType, URL uri) {
        this.caption = caption;
        this.height = height;
        this.width = width;
        this.imageType = imageType;
        Uri = uri;
    }

    public ListingMedia(String caption, int height, int width, String imageType, URL uri, int unitNumber) {
        this.caption = caption;
        this.height = height;
        this.width = width;
        this.imageType = imageType;
        Uri = uri;
        this.unitNumber = unitNumber;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public URL getUri() {
        return Uri;
    }

    public void setUri(URL uri) {
        Uri = uri;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }
}