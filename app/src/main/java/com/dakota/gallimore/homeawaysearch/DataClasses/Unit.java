package com.dakota.gallimore.homeawaysearch.DataClasses;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by galli_000 on 11/3/2017.
 */

public class Unit {

    private int unitNumber;
    private int unitArea;
    private String areaUnit;

    private ArrayList<Feature> features;
    private ArrayList<Review> reviews;
    private ArrayList<Room> rooms;
    private ArrayList<RatePeriod> ratePeriods;

    private int maxSleep;
    private int maxSleepInBeds;
    private int numOfBathrooms;
    private int numOfBedrooms;
    private String propertyType;
    private int numOfRatings;
    private double averageReviewScore;
    private int[] numOfReviewRatings;

    public Unit() {
        features = new ArrayList<>();
        reviews = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    public Unit(int unitNumber, int unitArea, String areaUnit,
                ArrayList<Feature> features, ArrayList<Review> reviews, ArrayList<Room> rooms,
                ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds,
                int numOfBathrooms, int numOfBedrooms, String propertyType,
                int numOfRatings, int[] numOfReviewRatings) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.features = features;
        this.reviews = reviews;
        this.rooms = rooms;
        this.ratePeriods = ratePeriods;
        this.maxSleep = maxSleep;
        this.maxSleepInBeds = maxSleepInBeds;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfBedrooms = numOfBedrooms;
        this.propertyType = propertyType;
        this.numOfRatings = numOfRatings;
        this.numOfReviewRatings = numOfReviewRatings;
    }

    public Unit(int unitNumber, int unitArea, String areaUnit,
                ArrayList<Feature> features, ArrayList<Review> reviews, ArrayList<Room> rooms,
                ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds,
                String propertyType) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.features = features;
        this.reviews = reviews;
        this.rooms = rooms;
        this.ratePeriods = ratePeriods;
        this.maxSleep = maxSleep;
        this.maxSleepInBeds = maxSleepInBeds;
        this.propertyType = propertyType;


        this.numOfRatings = reviews.size();
        updateReviewScore();

    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public int getUnitArea() {
        return unitArea;
    }

    public void setUnitArea(int unitArea) {
        this.unitArea = unitArea;
    }

    public String getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(String areaUnit) {
        this.areaUnit = areaUnit;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features) {
        this.features = features;
    }

    public Feature getFeature(int i) {
        if (i < this.features.size()) {
            return this.features.get(i);
        }

        Log.d("Unit Class: ", "Error: attempting to get feature outside of Feature array");
        return null;
    }

    public void addFeature(Feature feature) {
        this.features.add(feature);
    }

    public void removeFeature(Feature feature) {
        if (this.features.contains(feature)) {
            this.features.remove(feature);
        } else {
            Log.d("Unit Class: ", "Feature not associated with Unit");
        }
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        if (review == null) {
            return;
        }
        this.numOfRatings = this.numOfRatings + 1;
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        if (review == null || !this.reviews.contains(review)) {
            return;
        }
        this.reviews.remove(review);
    }

    public Review getReview(int i) {
        if (i < this.reviews.size()) {
            return this.reviews.get(i);
        }
        Log.d("Unit: ", "Error: Attempting to get review out of review index");
        return null;
    }

    public void updateReviewScore() {
        double average = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            average = average + this.reviews.get(i).getRating();
        }
        this.averageReviewScore = average / numOfRatings;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        if (room == null) {
            return;
        }
        this.rooms.add(room);
        if (room.getRoomType().equals("bathroom")) {
            this.numOfBathrooms = this.numOfBathrooms + 1;
        }
        if (room.getRoomType().equals("bedroom")) {
            this.numOfBedrooms = this.numOfBedrooms + 1;
        }
    }

    public void removeRoom(Room room) {
        if (room == null || !this.rooms.contains(room)) {
            return;
        }

        if (room.getRoomType().equals("bedroom")) {
            this.numOfBedrooms = this.numOfBedrooms - 1;
        }
        if (room.getRoomType().equals("bathroom")) {
            this.numOfBathrooms = this.numOfBathrooms - 1;
        }
        this.rooms.remove(room);
    }

    public Room getRoom(int i) {
        if (i < this.rooms.size()) {
            return this.rooms.get(i);
        }
        Log.d("Unit: ", "Error: attempting to remove room outside of array index");
        return null;
    }

    public ArrayList<RatePeriod> getRatePeriods() {
        return ratePeriods;
    }

    public void setRatePeriods(ArrayList<RatePeriod> ratePeriods) {
        this.ratePeriods = ratePeriods;
    }

    public void addRatePeriod(RatePeriod ratePeriod) {
        if (ratePeriod == null) {
            return;
        }
        this.ratePeriods.add(ratePeriod);
    }

    public void removeRatePeriod(RatePeriod ratePeriod) {
        if (ratePeriod == null || !this.ratePeriods.contains(ratePeriod)) {
            return;
        }
        this.ratePeriods.remove(ratePeriod);
    }

    public RatePeriod getRatePeriod(int i) {
        if (i < this.ratePeriods.size()) {
            return this.ratePeriods.get(i);
        }
        Log.d("Unit: ", "Error: attempting to remove rate period outside of array index");
        return null;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

    public void setMaxSleep(int maxSleep) {
        this.maxSleep = maxSleep;
    }

    public int getMaxSleepInBeds() {
        return maxSleepInBeds;
    }

    public void setMaxSleepInBeds(int maxSleepInBeds) {
        this.maxSleepInBeds = maxSleepInBeds;
    }

    public int getNumOfBathrooms() {
        return numOfBathrooms;
    }

    public void setNumOfBathrooms(int numOfBathrooms) {
        this.numOfBathrooms = numOfBathrooms;
    }

    public int getNumOfBedrooms() {
        return numOfBedrooms;
    }

    public void setNumOfBedrooms(int numOfBedrooms) {
        this.numOfBedrooms = numOfBedrooms;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public double getAverageReviewScore() {
        return averageReviewScore;
    }

    public void setAverageReviewScore(float averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public int[] getNumOfReviewRatings() {
        return numOfReviewRatings;
    }

    public void setNumOfReviewRatings(int[] numOfReviewRatings) {
        this.numOfReviewRatings = numOfReviewRatings;
    }
}
