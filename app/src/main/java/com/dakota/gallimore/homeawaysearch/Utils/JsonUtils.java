package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;
import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Feature;
import com.dakota.gallimore.homeawaysearch.DataClasses.Listing;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.DataClasses.Location;
import com.dakota.gallimore.homeawaysearch.DataClasses.PriceQuote;
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
     *
     * @param object - Json object returned from HomeAway servers.
     * @return User object containing json data
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
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
            throw new JSONException("Invalid Json data for User type with error: " + e.getMessage());

        }
        return new User(firstName, lastName, email, allAccounts, id, homeSite, 0);
    }

    /**
     * Parses HomeAway Amenity JSON into an Amenity data class.
     *
     * @param jsonObject - JSONObject containing a single HomeAway Amenity.
     * @return - New Amenity data class populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid Json data for Amenities type with error: " + e.getMessage());
        }
        return new Amenities(count, category, description, localizedName);
    }

    /**
     * Parses HomeAway Room JSON into a Room data class. Will also call the parseAmenityJson method if
     * room has associated amenities.
     *
     * @param jsonObject - JSONObject containing a single HomeAway Room.
     * @param roomType   - Type of room to be parsed. (ie. Bathroom, Bedroom, etc.)
     * @return - New Room object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid Json data for Room type with error: " + e.getMessage());
        }

        return new Room(amenities, roomName, roomSubType, roomType);
    }

    /**
     * Parses HomeAway Review JSON into a Review object.
     * @param jsonObject - JSONObject containing a single HomeAway Review.
     * @return - New Review object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid Json data for Review type with error: " + e.getMessage());
        }

        return new Review(reviewDate, reviewerName, body, headline, helpfulCount, unhelpfulCount, reviewLocale);
    }

    /**
     * Parses HomeAway Feature JSON into a Feature object.
     * @param jsonObject - JSONObject containing a single HomeAway Feature.
     * @return - New Feature object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid Json data for Feature type with error: " + e.getMessage());
        }

        return new Feature(count, category, description, localizedName);
    }

    /**
     * Parses HomeAway RatePeriod JSON into a RatePeriod object.
     * @param jsonObject - JSONObject containing a single HomeAway RatePeriod.
     * @return - New RatePeriod object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid Json data for RatePeriod type with error: " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new RatePeriod(arrivalDate, leaveDate, minimumStay, weeklyRate, currency);
    }

    /**
     * Parses HomeAway Unit JSON into a Unit object. Will call parseRoomJson, parseFeatureJson, parseReviewJson, and
     * parseRatePeriodJson methods for any and all associated Rooms, Features, Reviews, and Rate Periods for the Unit.
     * @param jsonObject - JSONObject containing a single HomeAway Unit.
     * @return - New Unit object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid JSON data for User type with error: " + e.getMessage());
        }
    }

    /**
     * Parses HomeAway Listing Photos into a ListingMedia object.
     * @param jsonObject - JSONObject containing a single HomeAway photo object.
     * @param imageType - Type of JSONObject passed (ie. Object for thumbnail, photo, etc.).
     * @return - New ListingMedia object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     * @throws MalformedURLException - When provided photo URLs are malformed.
     */
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
                if (!jsonObject.isNull(Constants.JSON_CAPTION)) {
                    caption = jsonObject.getString(Constants.JSON_CAPTION);
                }
            }
            JSONObject imageSpecs = jsonObject.getJSONObject(Constants.JSON_IMAGE_LARGE_SIZE);
            height = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_HEIGHT);
            width = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_WIDTH);
            url = new URL(imageSpecs.getString(Constants.JSON_URL));

            return new ListingMedia(caption, height, width, imageType, url, unitNumber);

        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Media type with error: " + e.getMessage());
        } catch (MalformedURLException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Unable to parse image URI with error: " + e.getMessage());
        }
    }

    /**
     * Parses HomeAway Site JSON into a Site object.
     * @param jsonObject - JSONObject containing a single HomeAway Site.
     * @return - New Site object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
    public static Site parseSiteJson(JSONObject jsonObject) throws JSONException {
        String href = "";
        String rel = "";

        try {
            href = jsonObject.getString(Constants.JSON_HREF);
            rel = jsonObject.getString(Constants.JSON_REL);
            return new Site(href, rel);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON date for Site type with error: " + e.getMessage());
        }
    }

    /**
     * Parses HomeAway Location JSON into a Location object.
     * @param jsonObject - JSONObject containing a single HomeAway Location.
     * @return - New Location object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
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
            throw new JSONException("Invalid JSON data for Location type with error: " + e.getMessage());
        }
    }

    /**
     * Parses HomeAway Listing JSON into a Listing object. Will call Will call parseFeatureJson, parseLocationJson,
     * parseSiteJson, parseMediaJson, and parseUnitJson methods for any and all associated Features, Locations,
     * Sites, Media, and Units for the Listing.
     * @param jsonObject - JSONObject containing a single HomeAway Listing.
     * @return - New Listing object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
    public static Listing parseListingJson(JSONObject jsonObject) throws JSONException {
        String listingId = "";
        String listingUrl = "";
        String sourceLocale = "";
        String sourceLocaleName = "";
        String description = "";
        String headline = "";
        ArrayList<Feature> features = new ArrayList<>();
        Location location = new Location();
        ArrayList<Site> sites = new ArrayList<>();
        ArrayList<ListingMedia> photos = new ArrayList<>();
        ArrayList<Unit> units = new ArrayList<>();

        try {
            listingId = jsonObject.getString(Constants.JSON_LISTING_ID);
            listingUrl = jsonObject.getString(Constants.JSON_LISTING_URL);
            sourceLocale = jsonObject.getString(Constants.JSON_SOURCE_LOCALE);
            sourceLocaleName = jsonObject.getString(Constants.JSON_SOURCE_LOCALE_NAME);

            JSONObject adContent = jsonObject.getJSONObject(Constants.JSON_AD_CONTENT);
            description = adContent.getString(Constants.JSON_DESCRIPTION);
            headline = adContent.getString(Constants.JSON_HEADLINE);

            JSONArray featuresJsonArray = jsonObject.getJSONArray(Constants.JSON_FEATURES);
            for (int i = 0; i < featuresJsonArray.length(); i++) {
                features.add(parseFeatureJson(featuresJsonArray.getJSONObject(i)));
            }

            location = parseLocationJson(jsonObject.getJSONObject(Constants.JSON_LOCATION));

            JSONArray sitesJsonArray = jsonObject.getJSONArray(Constants.JSON_SITES);
            for (int i = 0; i < sitesJsonArray.length(); i++) {
                sites.add(parseSiteJson(sitesJsonArray.getJSONObject(i)));
            }

            JSONObject photosJson = jsonObject.getJSONObject(Constants.JSON_PHOTOS);
            JSONArray photosJsonArray = photosJson.getJSONArray(Constants.JSON_PHOTOS);
            for (int i = 0; i < photosJsonArray.length(); i++) {
                photos.add(parseMediaJson(photosJsonArray.getJSONObject(i), "photo"));
            }
            JSONArray thumbnailJsonArray = photosJson.getJSONArray(Constants.JSON_THUMBNAILS);
            for (int i = 0; i < thumbnailJsonArray.length(); i++) {
                photos.add(parseMediaJson(thumbnailJsonArray.getJSONObject(i), "thumbnail"));
            }

            JSONArray unitsJsonArray = jsonObject.getJSONArray(Constants.JSON_UNITS);
            for (int i = 0; i < unitsJsonArray.length(); i++) {
                units.add(parseUnitJson(unitsJsonArray.getJSONObject(i)));
            }

            return new Listing(listingId, listingUrl, sourceLocale,
                    sourceLocaleName, description, headline,
                    features, location, sites, photos, units);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Listing type with error: " + e.getMessage());
        } catch (MalformedURLException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Malformed URL in JSON data for a photo");
        }
    }

    public static PriceQuote parsePriceQuoteJson(JSONObject jsonObject) throws JSONException {
        String currencyUnits = "USD";
        double amount = 0;
        double other = 0;
        double tax = 0;
        double averageNightly = 0;
        double fullyLoadedPriceQuote = 0;
        double rent = 0;
        double fees = 0;
        double travelersFee = 0;

        try {
            if (!jsonObject.isNull(Constants.JSON_CURRENCY_UNITS)) {
                currencyUnits = jsonObject.getString(Constants.JSON_CURRENCY_UNITS);
            }
            if (!jsonObject.isNull(Constants.JSON_AMOUNT)) {
                amount = jsonObject.getDouble(Constants.JSON_AMOUNT);
            }
            if (!jsonObject.isNull(Constants.JSON_OTHER)) {
                other = jsonObject.getDouble(Constants.JSON_OTHER);
            }
            if (!jsonObject.isNull(Constants.JSON_TAX)) {
                tax = jsonObject.getDouble(Constants.JSON_TAX);
            }
            if (!jsonObject.isNull(Constants.JSON_AVERAGE_NIGHTLY)) {
                averageNightly = jsonObject.getDouble(Constants.JSON_AVERAGE_NIGHTLY);
            }
            if (!jsonObject.isNull(Constants.JSON_FULLY_LOADED_PRICE_QUOTE)) {
                fullyLoadedPriceQuote = jsonObject.getDouble(Constants.JSON_FULLY_LOADED_PRICE_QUOTE);
            }
            if (!jsonObject.isNull(Constants.JSON_RENT)) {
                rent = jsonObject.getDouble(Constants.JSON_RENT);
            }
            if (!jsonObject.isNull(Constants.JSON_FEES)) {
                fees = jsonObject.getDouble(Constants.JSON_FEES);
            }
            if (!jsonObject.isNull(Constants.JSON_TRAVELER_FEE)) {
                travelersFee = jsonObject.getDouble(Constants.JSON_TRAVELER_FEE);
            }
            return new PriceQuote(currencyUnits, amount, other, tax, averageNightly, fullyLoadedPriceQuote,
                    rent, fees, travelersFee);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for PriceQuote type with error: " + e.getMessage());
        }
    }
}