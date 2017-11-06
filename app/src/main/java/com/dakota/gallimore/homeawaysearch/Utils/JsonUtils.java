package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Feature;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.DataClasses.RatePeriod;
import com.dakota.gallimore.homeawaysearch.DataClasses.Review;
import com.dakota.gallimore.homeawaysearch.DataClasses.Room;
import com.dakota.gallimore.homeawaysearch.DataClasses.Unit;
import com.dakota.gallimore.homeawaysearch.DataClasses.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by galli_000 on 11/2/2017.
 * Utility class aiding in parsing Json data.
 */

public class JsonUtils {

    /**
     * Method to parse HomeAway User Json into the User class variable.
     * NOTE: Throws exception to be handled on UI thread.
     *
     * @param object - Json object returned from HomeAway servers.
     * @return User object containing json data
     */
    public static User parseUserJson(JSONObject object) throws JSONException {
        String firstName = "";
        String lastName = "";
        String email = "";
        String id = "";
        JSONArray accountsArray;
        String homeSite = "";
        String[] allAccounts = new String[]{""};

        try {

            if (!object.isNull("firstName") && !object.isNull("lastName")) {
                firstName = object.getString("firstName");
                lastName = object.getString("lastName");
            }
            if (!object.isNull("emailAddress")) {
                email = object.getString("emailAddress");
            }
            if (!object.isNull("id")) {
                id = object.getString("id");
            }

            if (!object.isNull("accounts")) {
                accountsArray = object.getJSONArray("accounts");

                allAccounts = new String[accountsArray.length()];

                for (int i = 0; i < accountsArray.length(); i++) {
                    JSONObject account = accountsArray.getJSONObject(i);
                    allAccounts[i] = account.getString("accountType");

                    if (!account.isNull("advertiser")) {
                        JSONObject advertiser = account.getJSONObject("advertiser");
                        homeSite = advertiser.getString("site");
                    }
                }
            }
        } catch (JSONException e) {
            Log.d("Network Utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for User type");

        }
        return new User(firstName, lastName, email, allAccounts, id, homeSite, 0);
    }

    public static Amenities parseAmenityJson(JSONObject jsonObject) throws JSONException {
        int count = 0;
        String category = "";
        String description = "";
        String localizedName = "";

        try {
            count = jsonObject.getInt("count");
            category = jsonObject.getString("category");
            description = jsonObject.getString("description");
            localizedName = jsonObject.getString("localizedName");
        } catch (JSONException e) {
            Log.d("Network utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for Amenities type");
        }
        return new Amenities(count, category, description, localizedName);
    }

    public static Room parseRoomJson(JSONObject jsonObject, String roomType) throws JSONException {
        ArrayList<Amenities> amenities = new ArrayList<>();
        String roomName = "";
        String roomSubType = "";

        try {
            JSONArray amenitiesJson = jsonObject.getJSONArray("amenities");
            for (int i = 0; i < amenitiesJson.length(); i++) {
                Amenities amenity = parseAmenityJson(amenitiesJson.getJSONObject(i));
                amenities.add(amenity);
            }
            roomName = jsonObject.getString("name");
            roomSubType = jsonObject.getString("roomSubType");
        } catch (JSONException e) {
            Log.d("Network utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for Room type");
        }

        return new Room(amenities, roomName, roomSubType, roomType);
    }

    public static Review parseReviewJson(JSONObject jsonObject) throws JSONException {
        String reviewDate = "";
        String reviewerName = "";
        String body = "";
        String headline = "";
        int helpfulCount = 0;
        int unhelpfulCount = 0;
        double rating = 0.0;
        String reviewLocale = "";

        try {
            reviewDate = jsonObject.getString("arrivalDate");
            reviewerName = jsonObject.getString("reviewerName");
            body = jsonObject.getString("body");
            headline = jsonObject.getString("headline");
            helpfulCount = jsonObject.getInt("helpfulCount");
            unhelpfulCount = jsonObject.getInt("unhelpfulCount");
            reviewLocale = jsonObject.getString("reviewLocale");
        } catch (JSONException e) {
            Log.d("Network Utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for Review type");
        }

        return new Review(reviewDate, reviewerName, body, headline, helpfulCount, unhelpfulCount, reviewLocale);
    }

    public static Feature parseFeatureJson(JSONObject jsonObject) throws JSONException {
        int count;
        String category;
        String description;
        String localizedName;

        try {
            count = jsonObject.getInt("count");
            category = jsonObject.getString("category");
            description = jsonObject.getString("description");
            localizedName = jsonObject.getString("localizedName");
        } catch (JSONException e) {
            Log.d("Network Utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for Feature type");
        }

        return new Feature(count, category, description, localizedName);
    }

    public static RatePeriod parseRatePeriodJson(JSONObject jsonObject) throws JSONException {
        Date arrivalDate = new Date();
        Date leaveDate = new Date();
        int minimumStay = 1;
        double weeklyRate = 0;
        String currency = "";

        try {
            JSONObject dateRange = jsonObject.getJSONObject("dateRange");
            JSONObject weeklyObject = jsonObject.getJSONObject("rates").getJSONObject("weekly");
            arrivalDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateRange.getString("beginDate"));
            leaveDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateRange.getString("endDate"));
            minimumStay = jsonObject.getInt("minimumStay");
            weeklyRate = weeklyObject.getDouble("amount");
            currency = weeklyObject.getString("currency");
        } catch (JSONException e) {
            Log.d("Network Utils: ", e.getMessage());
            throw new JSONException("Invalid Json data for RatePeriod type");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new RatePeriod(arrivalDate, leaveDate, minimumStay, weeklyRate, currency);
    }

    public static Unit parseUnitJson(JSONObject jsonObject) throws JSONException {
        int unitNumber = 0;
        int unitArea = 0;
        String areaUnit = "";

        ArrayList<Feature> features = new ArrayList<>();
        ArrayList<Review> reviews = new ArrayList<>();
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<RatePeriod> ratePeriods = new ArrayList<>();

        int maxSleep = 1;
        int maxSleepInBeds = 1;
        int numOfBathrooms = 0;
        int numOfBedrooms = 0;
        String propertyType = "";
        int numOfRatings = 0;
        double averageReviewScore = 0;

        try {
            unitNumber = jsonObject.getInt("unitNumber");
            JSONObject unitContent = jsonObject.getJSONObject("unitContent");
            unitArea = unitContent.getInt("area");
            areaUnit = unitContent.getString("areaUnit");
            JSONArray bathroomArray = unitContent.getJSONArray("bathrooms");
            for (int i = 0; i < bathroomArray.length(); i++) {
                rooms.add(parseRoomJson(bathroomArray.getJSONObject(i), "bathroom"));
            }
            JSONArray bedroomArray = unitContent.getJSONArray("bedrooms");
            for (int i = 0; i < bedroomArray.length(); i++) {
                rooms.add(parseRoomJson(bedroomArray.getJSONObject(i), "bedroom"));
            }
            maxSleep = unitContent.getInt("maxSleep");
            maxSleepInBeds = unitContent.getInt("maxSleepInBeds");
            numOfBathrooms = bathroomArray.length();
            numOfBedrooms = bedroomArray.length();
            propertyType = unitContent.getString("propertyType");
            JSONArray featureArray = unitContent.getJSONArray("features");
            for (int i = 0; i < featureArray.length(); i++) {
                features.add(parseFeatureJson(featureArray.getJSONObject(i)));
            }
            JSONArray reviewContent = jsonObject.getJSONObject("unitReviewContent").getJSONArray("entries");
            for (int i = 0; i < reviewContent.length(); i++) {
                reviews.add(parseReviewJson(reviewContent.getJSONObject(i)));
            }
            JSONArray ratePeriodArray = jsonObject.getJSONArray("ratePeriods");
            for (int i = 0; i < ratePeriodArray.length(); i++) {
                ratePeriods.add(parseRatePeriodJson(ratePeriodArray.getJSONObject(i)));
            }
            return new Unit(unitNumber, unitArea, areaUnit,
                    features, reviews, rooms, ratePeriods, maxSleep,
                    maxSleepInBeds, numOfBathrooms, numOfBedrooms, propertyType);
            //JSONObject unitAvailibility = unitContent.getJSONObject("unitAvailability");
        } catch (JSONException e) {
            Log.d("JSON Utils: ", e.getMessage());
            throw new JSONException("Invalid JSON data for User type");
        }
    }

    public static ListingMedia parseMediaJson(JSONObject jsonObject, String imageType) throws JSONException, MalformedURLException {
        String caption = "";
        int height = 0;
        int width = 0;
        URL url = null;
        int unitNumber = 0;

        try {
            if (!imageType.equals("photo")) {
                caption = jsonObject.getJSONObject("photo").getString("caption");
                unitNumber = jsonObject.getInt("unitNumber");
            } else {
                caption = jsonObject.getString("caption");
            }
            JSONObject imageSpecs = jsonObject.getJSONObject("large");
            height = imageSpecs.getJSONObject("dimension").getInt("height");
            width = imageSpecs.getJSONObject("dimension").getInt("width");
            url = new URL(imageSpecs.getString("uri"));

            return new ListingMedia(caption, height, width, imageType, url, unitNumber);

        } catch (JSONException e) {
            Log.d("JSON Utils: ", e.getMessage());
            throw new JSONException("Invalid JSON data for Media type");
        } catch (MalformedURLException e) {
            Log.d("JSON Utils: ", e.getMessage());
            throw new JSONException("Unable to parse image URI");
        }
    }
}