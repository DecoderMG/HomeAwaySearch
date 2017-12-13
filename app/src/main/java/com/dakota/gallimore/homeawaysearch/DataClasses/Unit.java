package com.dakota.gallimore.homeawaysearch.DataClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by galli_000 on 11/3/2017.
 * Class containing all information relating to a Unit of a Homeaway listing
 */

public class Unit implements Serializable {

    @SerializedName("unitNumber")
    @Expose
    private int unitNumber;

    @SerializedName("ratePeriods")
    @Expose
    private ArrayList<ListingUnitRatePeriod> ratePeriods;

    @SerializedName("reviewSummary")
    @Expose
    private ListingUnitReviewSummary reviewSummary;

    @SerializedName("unitAvailability")
    @Expose
    private ListingUnitAvailability unitAvailability;

    @SerializedName("unitContent")
    @Expose
    private UnitContent unitContent;

    public Unit() {
    }

    public Unit(int unitNumber, ArrayList<ListingUnitRatePeriod> ratePeriods, ListingUnitReviewSummary reviewSummary, ListingUnitAvailability unitAvailability, UnitContent unitContent) {
        this.unitNumber = unitNumber;
        this.ratePeriods = ratePeriods;
        this.reviewSummary = reviewSummary;
        this.unitAvailability = unitAvailability;
        this.unitContent = unitContent;
    }

    public int getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(int unitNumber) {
        this.unitNumber = unitNumber;
    }

    public ArrayList<ListingUnitRatePeriod> getRatePeriods() {
        return ratePeriods;
    }

    public void setRatePeriods(ArrayList<ListingUnitRatePeriod> ratePeriods) {
        this.ratePeriods = ratePeriods;
    }

    public ListingUnitReviewSummary getReviewSummary() {
        return reviewSummary;
    }

    public void setReviewSummary(ListingUnitReviewSummary reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public ListingUnitAvailability getUnitAvailability() {
        return unitAvailability;
    }

    public void setUnitAvailability(ListingUnitAvailability unitAvailability) {
        this.unitAvailability = unitAvailability;
    }

    public UnitContent getUnitContent() {
        return unitContent;
    }

    public void setUnitContent(UnitContent unitContent) {
        this.unitContent = unitContent;
    }

    /*
    private int unitArea;
    private String areaUnit;

    private ArrayList<ListingAdFeature> listingAdFeatures;
    private ArrayList<Review> reviews;
    private ArrayList<ListingAdRoom> listingAdRooms;

    private int maxSleep;
    private int maxSleepInBeds;
    private int numOfBathrooms;
    private int numOfBedrooms;
    private String propertyType;
    private int numOfRatings;
    private double averageReviewScore;
    private int[] numOfReviewRatings;

    private String availabilityDefault;
    private String changeOverDefault;
    private Date dateRangeBegin;
    private Date dateRangeEnd;
    private int maxStayDefault;
    private int minPriorNotifyDefault;
    private int minStayDefault;
    private String stayIncrementDefault;
    private String availability;
    private int availableUnitCount;
    private String changeOver;
    private int[] maxStay;
    private int[] minPriorNotify;
    private int[] minStay;
    private String stayIncrement;


    public Unit() {
        listingAdFeatures = new ArrayList<>();
        reviews = new ArrayList<>();
        listingAdRooms = new ArrayList<>();
    }

    public Unit(int unitNumber, int unitArea, String areaUnit,
                ArrayList<ListingAdFeature> listingAdFeatures, ArrayList<Review> reviews, ArrayList<ListingAdRoom> listingAdRooms,
                ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds,
                int numOfBathrooms, int numOfBedrooms, String propertyType,
                int numOfRatings, int[] numOfReviewRatings) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.listingAdFeatures = listingAdFeatures;
        this.reviews = reviews;
        this.listingAdRooms = listingAdRooms;
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
                ArrayList<ListingAdFeature> listingAdFeatures, ArrayList<Review> reviews, ArrayList<ListingAdRoom> listingAdRooms,
                ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds,
                int numOfBathrooms, int numOfBedrooms, String propertyType) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.listingAdFeatures = listingAdFeatures;
        this.reviews = reviews;
        this.listingAdRooms = listingAdRooms;
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
                ArrayList<ListingAdFeature> listingAdFeatures, ArrayList<Review> reviews, ArrayList<ListingAdRoom> listingAdRooms,
                ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds,
                String propertyType) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.listingAdFeatures = listingAdFeatures;
        this.reviews = reviews;
        this.listingAdRooms = listingAdRooms;
        this.ratePeriods = ratePeriods;
        this.maxSleep = maxSleep;
        this.maxSleepInBeds = maxSleepInBeds;
        this.propertyType = propertyType;


        this.numOfRatings = reviews.size();
        updateReviewScore();
    }

    public Unit(int unitNumber, int unitArea, String areaUnit, ArrayList<ListingAdFeature> listingAdFeatures, ArrayList<Review> reviews, ArrayList<ListingAdRoom> listingAdRooms, ArrayList<RatePeriod> ratePeriods, int maxSleep, int maxSleepInBeds, int numOfBathrooms, int numOfBedrooms, String propertyType, int numOfRatings, double averageReviewScore, int[] numOfReviewRatings, String availabilityDefault, String changeOverDefault, Date dateRangeBegin, Date dateRangeEnd, int maxStayDefault, int minPriorNotifyDefault, int minStayDefault, String stayIncrementDefault, String availability, int availableUnitCount, String changeOver, int[] maxStay, int[] minPriorNotify, int[] minStay, String stayIncrement) {
        this.unitNumber = unitNumber;
        this.unitArea = unitArea;
        this.areaUnit = areaUnit;
        this.listingAdFeatures = listingAdFeatures;
        this.reviews = reviews;
        this.listingAdRooms = listingAdRooms;
        this.ratePeriods = ratePeriods;
        this.maxSleep = maxSleep;
        this.maxSleepInBeds = maxSleepInBeds;
        this.numOfBathrooms = numOfBathrooms;
        this.numOfBedrooms = numOfBedrooms;
        this.propertyType = propertyType;
        this.numOfRatings = numOfRatings;
        this.averageReviewScore = averageReviewScore;
        this.numOfReviewRatings = numOfReviewRatings;
        this.availabilityDefault = availabilityDefault;
        this.changeOverDefault = changeOverDefault;
        this.dateRangeBegin = dateRangeBegin;
        this.dateRangeEnd = dateRangeEnd;
        this.maxStayDefault = maxStayDefault;
        this.minPriorNotifyDefault = minPriorNotifyDefault;
        this.minStayDefault = minStayDefault;
        this.stayIncrementDefault = stayIncrementDefault;
        this.availability = availability;
        this.availableUnitCount = availableUnitCount;
        this.changeOver = changeOver;
        this.maxStay = maxStay;
        this.minPriorNotify = minPriorNotify;
        this.minStay = minStay;
        this.stayIncrement = stayIncrement;
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

    public ArrayList<ListingAdFeature> getListingAdFeatures() {
        return listingAdFeatures;
    }

    public void setListingAdFeatures(ArrayList<ListingAdFeature> listingAdFeatures) {
        this.listingAdFeatures = listingAdFeatures;
    }

    public ListingAdFeature getFeature(int i) {
        if (i < this.listingAdFeatures.size()) {
            return this.listingAdFeatures.get(i);
        }

        Log.d(Constants.UNIT_CLASS_LOG_TAG, "Error: attempting to get feature outside of ListingAdFeature array");
        return null;
    }

    public void addFeature(ListingAdFeature listingAdFeature) {
        this.listingAdFeatures.add(listingAdFeature);
    }

    public void removeFeature(ListingAdFeature listingAdFeature) {
        if (this.listingAdFeatures.contains(listingAdFeature)) {
            this.listingAdFeatures.remove(listingAdFeature);
        } else {
            Log.d(Constants.UNIT_CLASS_LOG_TAG, "ListingAdFeature not associated with Unit");
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
        Log.d(Constants.UNIT_CLASS_LOG_TAG, "Error: Attempting to get review out of review index");
        return null;
    }

    public void updateReviewScore() {
        double average = 0;
        for (int i = 0; i < this.reviews.size(); i++) {
            average = average + this.reviews.get(i).getRating();
        }
        this.averageReviewScore = average / numOfRatings;
    }

    public ArrayList<ListingAdRoom> getListingAdRooms() {
        return listingAdRooms;
    }

    public void setListingAdRooms(ArrayList<ListingAdRoom> listingAdRooms) {
        this.listingAdRooms = listingAdRooms;
    }

    public void addRoom(ListingAdRoom listingAdRoom) {
        if (listingAdRoom == null) {
            return;
        }
        this.listingAdRooms.add(listingAdRoom);
        if (listingAdRoom.getRoomType().equals("bathroom")) {
            this.numOfBathrooms = this.numOfBathrooms + 1;
        }
        if (listingAdRoom.getRoomType().equals("bedroom")) {
            this.numOfBedrooms = this.numOfBedrooms + 1;
        }
    }

    public void removeRoom(ListingAdRoom listingAdRoom) {
        if (listingAdRoom == null || !this.listingAdRooms.contains(listingAdRoom)) {
            return;
        }

        if (listingAdRoom.getRoomType().equals("bedroom")) {
            this.numOfBedrooms = this.numOfBedrooms - 1;
        }
        if (listingAdRoom.getRoomType().equals("bathroom")) {
            this.numOfBathrooms = this.numOfBathrooms - 1;
        }
        this.listingAdRooms.remove(listingAdRoom);
    }

    public ListingAdRoom getRoom(int i) {
        if (i < this.listingAdRooms.size()) {
            return this.listingAdRooms.get(i);
        }
        Log.d(Constants.UNIT_CLASS_LOG_TAG, "Error: attempting to remove room outside of array index");
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
        Log.d(Constants.UNIT_CLASS_LOG_TAG, "Error: attempting to remove rate period outside of array index");
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

    public void setAverageReviewScore(double averageReviewScore) {
        this.averageReviewScore = averageReviewScore;
    }

    public int[] getNumOfReviewRatings() {
        return numOfReviewRatings;
    }

    public void setNumOfReviewRatings(int[] numOfReviewRatings) {
        this.numOfReviewRatings = numOfReviewRatings;
    }

    public String getAvailabilityDefault() {
        return availabilityDefault;
    }

    public void setAvailabilityDefault(String availabilityDefault) {
        this.availabilityDefault = availabilityDefault;
    }

    public String getChangeOverDefault() {
        return changeOverDefault;
    }

    public void setChangeOverDefault(String changeOverDefault) {
        this.changeOverDefault = changeOverDefault;
    }

    public Date getDateRangeBegin() {
        return dateRangeBegin;
    }

    public void setDateRangeBegin(Date dateRangeBegin) {
        this.dateRangeBegin = dateRangeBegin;
    }

    public Date getDateRangeEnd() {
        return dateRangeEnd;
    }

    public void setDateRangeEnd(Date dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
    }

    public int getMaxStayDefault() {
        return maxStayDefault;
    }

    public void setMaxStayDefault(int maxStayDefault) {
        this.maxStayDefault = maxStayDefault;
    }

    public int getMinPriorNotifyDefault() {
        return minPriorNotifyDefault;
    }

    public void setMinPriorNotifyDefault(int minPriorNotifyDefault) {
        this.minPriorNotifyDefault = minPriorNotifyDefault;
    }

    public int getMinStayDefault() {
        return minStayDefault;
    }

    public void setMinStayDefault(int minStayDefault) {
        this.minStayDefault = minStayDefault;
    }

    public String getStayIncrementDefault() {
        return stayIncrementDefault;
    }

    public void setStayIncrementDefault(String stayIncrementDefault) {
        this.stayIncrementDefault = stayIncrementDefault;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getAvailableUnitCount() {
        return availableUnitCount;
    }

    public void setAvailableUnitCount(int availableUnitCount) {
        this.availableUnitCount = availableUnitCount;
    }

    public String getChangeOver() {
        return changeOver;
    }

    public void setChangeOver(String changeOver) {
        this.changeOver = changeOver;
    }

    public int[] getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(int[] maxStay) {
        this.maxStay = maxStay;
    }

    public int[] getMinPriorNotify() {
        return minPriorNotify;
    }

    public void setMinPriorNotify(int[] minPriorNotify) {
        this.minPriorNotify = minPriorNotify;
    }

    public int[] getMinStay() {
        return minStay;
    }

    public void setMinStay(int[] minStay) {
        this.minStay = minStay;
    }

    public String getStayIncrement() {
        return stayIncrement;
    }

    public void setStayIncrement(String stayIncrement) {
        this.stayIncrement = stayIncrement;
    }
    */
}