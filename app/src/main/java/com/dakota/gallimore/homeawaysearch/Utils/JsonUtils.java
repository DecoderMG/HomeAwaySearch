package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;
import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Feature;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.DataClasses.Location;
import com.dakota.gallimore.homeawaysearch.DataClasses.RatePeriod;
import com.dakota.gallimore.homeawaysearch.DataClasses.Review;
import com.dakota.gallimore.homeawaysearch.DataClasses.Room;
import com.dakota.gallimore.homeawaysearch.DataClasses.Site;
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

            if (!object.isNull(Constants.JSON_FIRST_NAME) && !object.isNull(Constants.JSON_LAST_NAME)) {
                firstName = object.getString(Constants.JSON_FIRST_NAME);
                lastName = object.getString(Constants.JSON_LAST_NAME);
            }
            if (!object.isNull(Constants.JSON_EMAIL)) {
                email = object.getString(Constants.JSON_EMAIL);
            }
            if (!object.isNull(Constants.JSON_ID)) {
                id = object.getString(Constants.JSON_ID);
            }

            if (!object.isNull(Constants.JSON_ACCOUNTS)) {
                accountsArray = object.getJSONArray(Constants.JSON_ACCOUNTS);

                allAccounts = new String[accountsArray.length()];

                for (int i = 0; i < accountsArray.length(); i++) {
                    JSONObject account = accountsArray.getJSONObject(i);
                    allAccounts[i] = account.getString(Constants.JSON_ACCOUNT_TYPE);

                    if (!account.isNull(Constants.JSON_ADVERTISER)) {
                        JSONObject advertiser = account.getJSONObject(Constants.JSON_ADVERTISER);
                        homeSite = advertiser.getString(Constants.JSON_SITE);
                    }
                }
            }
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            count = jsonObject.getInt(Constants.JSON_COUNT);
            category = jsonObject.getString(Constants.JSON_CATEGORY);
            description = jsonObject.getString(Constants.JSON_DESCRIPTION);
            localizedName = jsonObject.getString(Constants.JSON_LOCALIZED_NAME);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid Json data for Amenities type");
        }
        return new Amenities(count, category, description, localizedName);
    }

    public static Room parseRoomJson(JSONObject jsonObject, String roomType) throws JSONException {
        ArrayList<Amenities> amenities = new ArrayList<>();
        String roomName = "";
        String roomSubType = "";

        try {
            JSONArray amenitiesJson = jsonObject.getJSONArray(Constants.JSON_AMENITIES);
            for (int i = 0; i < amenitiesJson.length(); i++) {
                Amenities amenity = parseAmenityJson(amenitiesJson.getJSONObject(i));
                amenities.add(amenity);
            }
            roomName = jsonObject.getString(Constants.JSON_NAME);
            roomSubType = jsonObject.getString(Constants.JSON_ROOM_SUBTYPE);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            reviewDate = jsonObject.getString(Constants.JSON_ARRIVAL_DATE);
            reviewerName = jsonObject.getString(Constants.JSON_REVIEWER_NAME);
            body = jsonObject.getString(Constants.JSON_BODY);
            headline = jsonObject.getString(Constants.JSON_HEADLINE);
            helpfulCount = jsonObject.getInt(Constants.JSON_HELPFUL_COUNT);
            unhelpfulCount = jsonObject.getInt(Constants.JSON_UNHELPFUL_COUNT);
            reviewLocale = jsonObject.getString(Constants.JSON_REVIEW_LOCALE);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            count = jsonObject.getInt(Constants.JSON_COUNT);
            category = jsonObject.getString(Constants.JSON_CATEGORY);
            description = jsonObject.getString(Constants.JSON_DESCRIPTION);
            localizedName = jsonObject.getString(Constants.JSON_LOCALIZED_NAME);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            JSONObject dateRange = jsonObject.getJSONObject(Constants.JSON_DATE_RANGE);
            JSONObject weeklyObject = jsonObject.getJSONObject(Constants.JSON_RATES).getJSONObject(Constants.JSON_WEEKLY);
            arrivalDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateRange.getString(Constants.JSON_BEGIN_DATE));
            leaveDate = new SimpleDateFormat("YYYY-MM-DD").parse(dateRange.getString(Constants.JSON_END_DATE));
            minimumStay = jsonObject.getInt(Constants.JSON_MIN_STAY);
            weeklyRate = weeklyObject.getDouble(Constants.JSON_AMOUNT);
            currency = weeklyObject.getString(Constants.JSON_CURRENCY);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            unitNumber = jsonObject.getInt(Constants.JSON_UNIT_NUMBER);
            JSONObject unitContent = jsonObject.getJSONObject(Constants.JSON_UNIT_CONTENT);
            unitArea = unitContent.getInt(Constants.JSON_AREA);
            areaUnit = unitContent.getString(Constants.JSON_AREA_UNIT);
            JSONArray bathroomArray = unitContent.getJSONArray(Constants.JSON_ROOM_BATHROOMS);
            for (int i = 0; i < bathroomArray.length(); i++) {
                rooms.add(parseRoomJson(bathroomArray.getJSONObject(i), "bathroom"));
            }
            JSONArray bedroomArray = unitContent.getJSONArray(Constants.JSON_ROOM_BEDROOMS);
            for (int i = 0; i < bedroomArray.length(); i++) {
                rooms.add(parseRoomJson(bedroomArray.getJSONObject(i), "bedroom"));
            }
            maxSleep = unitContent.getInt(Constants.JSON_MAX_SLEEP);
            maxSleepInBeds = unitContent.getInt(Constants.JSON_MAX_SLEEP_IN_BEDS);
            numOfBathrooms = bathroomArray.length();
            numOfBedrooms = bedroomArray.length();
            propertyType = unitContent.getString(Constants.JSON_PROPERTY_TYPE);
            JSONArray featureArray = unitContent.getJSONArray(Constants.JSON_FEATURES);
            for (int i = 0; i < featureArray.length(); i++) {
                features.add(parseFeatureJson(featureArray.getJSONObject(i)));
            }
            JSONArray reviewContent = jsonObject.getJSONObject(Constants.JSON_UNIT_REVIEW_CONTENT)
                    .getJSONArray(Constants.JSON_ENTRIES);
            for (int i = 0; i < reviewContent.length(); i++) {
                reviews.add(parseReviewJson(reviewContent.getJSONObject(i)));
            }
            JSONArray ratePeriodArray = jsonObject.getJSONArray(Constants.JSON_RATE_PERIODS);
            for (int i = 0; i < ratePeriodArray.length(); i++) {
                ratePeriods.add(parseRatePeriodJson(ratePeriodArray.getJSONObject(i)));
            }
            return new Unit(unitNumber, unitArea, areaUnit,
                    features, reviews, rooms, ratePeriods, maxSleep,
                    maxSleepInBeds, numOfBathrooms, numOfBedrooms, propertyType);
            //JSONObject unitAvailibility = unitContent.getJSONObject("unitAvailability");
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
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
            if (!imageType.equals(Constants.JSON_PHOTO_IMAGE_TYPE)) {
                caption = jsonObject.getJSONObject(Constants.JSON_PHOTO_IMAGE_TYPE).getString(Constants.JSON_CAPTION);
                unitNumber = jsonObject.getInt(Constants.JSON_UNIT_NUMBER);
                jsonObject = jsonObject.getJSONObject(Constants.JSON_PHOTO_IMAGE_TYPE);
            } else {
                caption = jsonObject.getString(Constants.JSON_CAPTION);
            }
            JSONObject imageSpecs = jsonObject.getJSONObject(Constants.JSON_IMAGE_LARGE_SIZE);
            height = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_HEIGHT);
            width = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_WIDTH);
            url = new URL(imageSpecs.getString(Constants.JSON_URL));

            return new ListingMedia(caption, height, width, imageType, url, unitNumber);

        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Media type");
        } catch (MalformedURLException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Unable to parse image URI");
        }
    }

    public static Site parseSiteJson(JSONObject jsonObject) throws JSONException {
        String href = "";
        String rel = "";

        try {
            href = jsonObject.getString(Constants.JSON_HREF);
            rel = jsonObject.getString(Constants.JSON_REL);
            return new Site(href, rel);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON date for Site type");
        }
    }

    public static Location parseLocationJson(JSONObject jsonObject) throws JSONException {
        double lat = 0;
        double lng = 0;
        String city = "";
        String state = "";
        String counrty = "";

        try {
            if (!jsonObject.isNull(Constants.JSON_LATITUDE)) {
                lat = jsonObject.getDouble(Constants.JSON_LATITUDE);
            }
            if (!jsonObject.isNull(Constants.JSON_LONGITUDE)) {
                lng = jsonObject.getDouble(Constants.JSON_LONGITUDE);
            }
            if (!jsonObject.isNull(Constants.JSON_CITY)) {
                city = jsonObject.getString(Constants.JSON_CITY);
            }
            if (!jsonObject.isNull(Constants.JSON_STATE)) {
                state = jsonObject.getString(Constants.JSON_STATE);
            }
            if (!jsonObject.isNull(Constants.JSON_COUNTRY)) {
                counrty = jsonObject.getString(Constants.JSON_COUNTRY);
            }
            return new Location(lat, lng, city, state, counrty);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Location type");
        }
    }
}