package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by galli_000 on 12/13/2017.
 */

public class ImageFileBean implements Serializable {

    @SerializedName("height")
    @Expose
    private int height;
    @SerializedName("imageSize")
    @Expose
    private String imageSize;
    @SerializedName("secureUri")
    @Expose
    private String secureUri;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("width")
    @Expose
    private int width;

    public ImageFileBean() {
    }

    public ImageFileBean(int height, String imageSize, String secureUri, String uri, int width) {
        this.height = height;
        this.imageSize = imageSize;
        this.secureUri = secureUri;
        this.uri = uri;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageSize() {
        return imageSize;
    }

    public void setImageSize(String imageSize) {
        this.imageSize = imageSize;
    }

    public String getSecureUri() {
        return secureUri;
    }

    public void setSecureUri(String secureUri) {
        this.secureUri = secureUri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
