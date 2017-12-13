package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 11/3/2017.
 */

public class ListingAdRoom implements Serializable {

    @SerializedName("amenities")
    @Expose
    private ArrayList<ListingAdFeature> amenities;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("roomSubType")
    @Expose
    private String roomSubType;
    @SerializedName("note")
    @Expose
    private String note;

    public ListingAdRoom() {
    }

    public ListingAdRoom(ArrayList<ListingAdFeature> amenities, String name,
                         String roomSubType, String note) {
        this.amenities = amenities;
        this.name = name;
        this.roomSubType = roomSubType;
        this.note = note;
    }

    public ArrayList<ListingAdFeature> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<ListingAdFeature> amenities) {
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomSubType() {
        return roomSubType;
    }

    public void setRoomSubType(String roomSubType) {
        this.roomSubType = roomSubType;
    }

    public String getRoomNote() {
        return note;
    }

    public void setRoomNote(String note) {
        this.note = note;
    }
}
