package com.dakota.gallimore.homeawaysearch.DataClasses;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/3/2017.
 */

public class Room {

    private ArrayList<Amenities> amenities;
    private String name;
    private String roomSubType;
    private String roomType;

    public Room() {
    }

    public Room(ArrayList<Amenities> amenities, String name,
                String roomSubType, String roomType) {
        this.amenities = amenities;
        this.name = name;
        this.roomSubType = roomSubType;
        this.roomType = roomType;
    }

    public ArrayList<Amenities> getAmenities() {
        return amenities;
    }

    public void setAmenities(ArrayList<Amenities> amenities) {
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
