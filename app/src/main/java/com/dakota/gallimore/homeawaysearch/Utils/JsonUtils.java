package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;
import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Feature;
import com.dakota.gallimore.homeawaysearch.DataClasses.Listing;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingMedia;
import com.dakota.gallimore.homeawaysearch.DataClasses.Location;
import com.dakota.gallimore.homeawaysearch.DataClasses.PriceQuote;
import com.dakota.gallimore.homeawaysearch.DataClasses.PriceRange;
import com.dakota.gallimore.homeawaysearch.DataClasses.RatePeriod;
import com.dakota.gallimore.homeawaysearch.DataClasses.Review;
import com.dakota.gallimore.homeawaysearch.DataClasses.Room;
import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
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
     * @param jsonObject - Json object returned from HomeAway servers.
     * @return User object containing json data
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */

    // TODO: Finish up User parsing once Profile Image API end is in place.
    public static User parseUserJson(JSONObject jsonObject) throws JSONException {
        String firstName;
        String lastName;
        String email;
        String id;
        JSONArray accountsArray;
        String homeSite = "";
        String[] allAccounts = new String[]{""};

        try {

            firstName = jsonObject.getString(Constants.JSON_FIRST_NAME);
            lastName = jsonObject.getString(Constants.JSON_LAST_NAME);
            email = jsonObject.getString(Constants.JSON_EMAIL);
            id = jsonObject.getString(Constants.JSON_ID);

            if (jsonObject.has(Constants.JSON_ACCOUNTS)) {
                accountsArray = jsonObject.getJSONArray(Constants.JSON_ACCOUNTS);

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
        int count;
        String category;
        String description;
        String localizedName;

        try {
            count = checkNullAndReturnInt(jsonObject, Constants.JSON_COUNT);
            category = checkNullAndReturnString(jsonObject, Constants.JSON_CATEGORY);
            description = checkNullAndReturnString(jsonObject, Constants.JSON_DESCRIPTION);
            localizedName = checkNullAndReturnString(jsonObject, Constants.JSON_LOCALIZED_NAME);
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
        String roomName;
        String roomSubType;

        try {
            if (jsonObject.has(Constants.JSON_AMENITIES)) {
                JSONArray amenitiesJson = jsonObject.getJSONArray(Constants.JSON_AMENITIES);
                for (int i = 0; i < amenitiesJson.length(); i++) {
                    Amenities amenity = parseAmenityJson(amenitiesJson.getJSONObject(i));
                    amenities.add(amenity);
                }
            }
            roomName = checkNullAndReturnString(jsonObject, Constants.JSON_NAME);
            roomSubType = checkNullAndReturnString(jsonObject, Constants.JSON_ROOM_SUBTYPE);
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
        String reviewDate;
        String reviewerName;
        String body;
        String headline;
        int helpfulCount;
        int unhelpfulCount;
        double rating = 0.0;
        String reviewLocale;

        try {
            reviewDate = checkNullAndReturnString(jsonObject, Constants.JSON_ARRIVAL_DATE);
            reviewerName = checkNullAndReturnString(jsonObject, Constants.JSON_REVIEWER_NAME);
            body = checkNullAndReturnString(jsonObject, Constants.JSON_BODY);
            headline = checkNullAndReturnString(jsonObject, Constants.JSON_HEADLINE);
            helpfulCount = checkNullAndReturnInt(jsonObject, Constants.JSON_HELPFUL_COUNT);
            unhelpfulCount = checkNullAndReturnInt(jsonObject, Constants.JSON_UNHELPFUL_COUNT);
            reviewLocale = checkNullAndReturnString(jsonObject, Constants.JSON_REVIEW_LOCALE);
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
            count = checkNullAndReturnInt(jsonObject, Constants.JSON_COUNT);
            category = checkNullAndReturnString(jsonObject, Constants.JSON_CATEGORY);
            description = checkNullAndReturnString(jsonObject, Constants.JSON_DESCRIPTION);
            localizedName = checkNullAndReturnString(jsonObject, Constants.JSON_LOCALIZED_NAME);
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
            if (jsonObject.has(Constants.JSON_DATE_RANGE)) {
                JSONObject dateRange = jsonObject.getJSONObject(Constants.JSON_DATE_RANGE);
                arrivalDate = new SimpleDateFormat("YYYY-MM-DD").parse(
                        checkNullAndReturnString(dateRange, Constants.JSON_BEGIN_DATE));
                leaveDate = new SimpleDateFormat("YYYY-MM-DD").parse(
                        checkNullAndReturnString(dateRange, Constants.JSON_END_DATE));
            }
            minimumStay = checkNullAndReturnInt(jsonObject, Constants.JSON_MIN_STAY);
            if (jsonObject.has(Constants.JSON_RATES)) {
                JSONObject weeklyObject = jsonObject.getJSONObject(Constants.JSON_RATES).getJSONObject(Constants.JSON_WEEKLY);
                weeklyRate = checkNullAndReturnDouble(weeklyObject, Constants.JSON_AMOUNT);
                currency = checkNullAndReturnString(weeklyObject, Constants.JSON_CURRENCY);
            }
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
        int unitNumber;
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
            unitNumber = checkNullAndReturnInt(jsonObject, Constants.JSON_UNIT_NUMBER);
            if (jsonObject.has(Constants.JSON_UNIT_CONTENT)) {

                JSONObject unitContent = jsonObject.getJSONObject(Constants.JSON_UNIT_CONTENT);

                unitArea = checkNullAndReturnInt(unitContent, Constants.JSON_AREA);
                areaUnit = checkNullAndReturnString(unitContent, Constants.JSON_AREA_UNIT);

                if (unitContent.has(Constants.JSON_ROOM_BATHROOMS)) {
                    JSONArray bathroomArray = unitContent.getJSONArray(Constants.JSON_ROOM_BATHROOMS);
                    for (int i = 0; i < bathroomArray.length(); i++) {
                        rooms.add(parseRoomJson(bathroomArray.getJSONObject(i), "bathroom"));
                    }
                    numOfBathrooms = bathroomArray.length();
                } else {
                    numOfBathrooms = 0;
                }

                if (unitContent.has(Constants.JSON_ROOM_BEDROOMS)) {
                    JSONArray bedroomArray = unitContent.getJSONArray(Constants.JSON_ROOM_BEDROOMS);
                    for (int i = 0; i < bedroomArray.length(); i++) {
                        rooms.add(parseRoomJson(bedroomArray.getJSONObject(i), "bedroom"));
                    }
                    numOfBedrooms = bedroomArray.length();
                } else {
                    numOfBedrooms = 0;
                }
                maxSleep = checkNullAndReturnInt(unitContent, Constants.JSON_MAX_SLEEP);
                maxSleepInBeds = checkNullAndReturnInt(unitContent, Constants.JSON_MAX_SLEEP_IN_BEDS);
                propertyType = checkNullAndReturnString(unitContent, Constants.JSON_PROPERTY_TYPE);

                if (unitContent.has(Constants.JSON_FEATURES)) {
                    JSONArray featureArray = unitContent.getJSONArray(Constants.JSON_FEATURES);
                    for (int i = 0; i < featureArray.length(); i++) {
                        features.add(parseFeatureJson(featureArray.getJSONObject(i)));
                    }
                }
            }

            if (jsonObject.has(Constants.JSON_UNIT_REVIEW_CONTENT)) {
                JSONArray reviewContent = jsonObject.getJSONObject(Constants.JSON_UNIT_REVIEW_CONTENT)
                        .getJSONArray(Constants.JSON_ENTRIES);
                for (int i = 0; i < reviewContent.length(); i++) {
                    reviews.add(parseReviewJson(reviewContent.getJSONObject(i)));
                }
            }

            if (jsonObject.has(Constants.JSON_RATE_PERIODS)) {
                JSONArray ratePeriodArray = jsonObject.getJSONArray(Constants.JSON_RATE_PERIODS);
                for (int i = 0; i < ratePeriodArray.length(); i++) {
                    ratePeriods.add(parseRatePeriodJson(ratePeriodArray.getJSONObject(i)));
                }
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
        String caption;
        int height = 0;
        int width = 0;
        URL url = null;
        int unitNumber = 0;

        try {
            if (!imageType.equals(Constants.JSON_PHOTO_IMAGE_TYPE)) {
                caption = checkNullAndReturnString(jsonObject.getJSONObject(Constants.JSON_PHOTO_IMAGE_TYPE),
                        Constants.JSON_CAPTION);
                unitNumber = checkNullAndReturnInt(jsonObject, Constants.JSON_UNIT_NUMBER);
                jsonObject = jsonObject.getJSONObject(Constants.JSON_PHOTO_IMAGE_TYPE);
            } else {
                caption = checkNullAndReturnString(jsonObject, Constants.JSON_CAPTION);
            }

            if (jsonObject.has(Constants.JSON_IMAGE_LARGE_SIZE)) {
                JSONObject imageSpecs = jsonObject.getJSONObject(Constants.JSON_IMAGE_LARGE_SIZE);
                height = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_HEIGHT);
                width = imageSpecs.getJSONObject(Constants.JSON_DIMENSION).getInt(Constants.JSON_WIDTH);
                url = new URL(imageSpecs.getString(Constants.JSON_URL));
            }

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
        String href;
        String rel;

        try {

            href = checkNullAndReturnString(jsonObject, Constants.JSON_HREF);
            rel = checkNullAndReturnString(jsonObject, Constants.JSON_REL);

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
        double lat;
        double lng;
        String city;
        String state;
        String counrty;

        try {

            lat = checkNullAndReturnDouble(jsonObject, Constants.JSON_LATITUDE);
            lng = checkNullAndReturnDouble(jsonObject, Constants.JSON_LONGITUDE);
            city = checkNullAndReturnString(jsonObject, Constants.JSON_CITY);
            state = checkNullAndReturnString(jsonObject, Constants.JSON_STATE);
            counrty = checkNullAndReturnString(jsonObject, Constants.JSON_COUNTRY);

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
        String listingId;
        String listingUrl;
        String sourceLocale;
        String sourceLocaleName;
        String description = "";
        String headline = "";
        ArrayList<Feature> features = new ArrayList<>();
        Location location = new Location();
        ArrayList<Site> sites = new ArrayList<>();
        ArrayList<ListingMedia> photos = new ArrayList<>();
        ArrayList<Unit> units = new ArrayList<>();

        try {
            listingId = checkNullAndReturnString(jsonObject, Constants.JSON_LISTING_ID);
            listingUrl = checkNullAndReturnString(jsonObject, Constants.JSON_LISTING_URL);
            sourceLocale = checkNullAndReturnString(jsonObject, Constants.JSON_SOURCE_LOCALE);
            sourceLocaleName = checkNullAndReturnString(jsonObject, Constants.JSON_SOURCE_LOCALE_NAME);

            if (jsonObject.has(Constants.JSON_AD_CONTENT)) {
                JSONObject adContent = jsonObject.getJSONObject(Constants.JSON_AD_CONTENT);
                description = checkNullAndReturnString(adContent, Constants.JSON_DESCRIPTION);
                headline = checkNullAndReturnString(adContent, Constants.JSON_HEADLINE);
            }

            if (jsonObject.has(Constants.JSON_FEATURES)) {
                JSONArray featuresJsonArray = jsonObject.getJSONArray(Constants.JSON_FEATURES);
                for (int i = 0; i < featuresJsonArray.length(); i++) {
                    features.add(parseFeatureJson(featuresJsonArray.getJSONObject(i)));
                }
            }

            if (jsonObject.has(Constants.JSON_LOCATION)) {
                location = parseLocationJson(jsonObject.getJSONObject(Constants.JSON_LOCATION));
            }

            if (jsonObject.has(Constants.JSON_SITES)) {
                JSONArray sitesJsonArray = jsonObject.getJSONArray(Constants.JSON_SITES);
                for (int i = 0; i < sitesJsonArray.length(); i++) {
                    sites.add(parseSiteJson(sitesJsonArray.getJSONObject(i)));
                }
            }

            if (jsonObject.has(Constants.JSON_PHOTOS)) {
                JSONObject photosJson = jsonObject.getJSONObject(Constants.JSON_PHOTOS);

                if (photosJson.has(Constants.JSON_PHOTOS)) {
                    JSONArray photosJsonArray = photosJson.getJSONArray(Constants.JSON_PHOTOS);
                    for (int i = 0; i < photosJsonArray.length(); i++) {
                        photos.add(parseMediaJson(photosJsonArray.getJSONObject(i), "photo"));
                    }
                }

                if (photosJson.has(Constants.JSON_THUMBNAILS)) {
                    JSONArray thumbnailJsonArray = photosJson.getJSONArray(Constants.JSON_THUMBNAILS);
                    for (int i = 0; i < thumbnailJsonArray.length(); i++) {
                        photos.add(parseMediaJson(thumbnailJsonArray.getJSONObject(i), "thumbnail"));
                    }
                }
            }

            if (jsonObject.has(Constants.JSON_UNITS)) {
                JSONArray unitsJsonArray = jsonObject.getJSONArray(Constants.JSON_UNITS);
                for (int i = 0; i < unitsJsonArray.length(); i++) {
                    units.add(parseUnitJson(unitsJsonArray.getJSONObject(i)));
                }
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
        String currencyUnits;
        double amount;
        double other;
        double tax;
        double averageNightly;
        double fullyLoadedPriceQuote;
        double rent;
        double fees;
        double travelersFee;

        try {

            currencyUnits = checkNullAndReturnString(jsonObject, Constants.JSON_CURRENCY_UNITS);
            amount = checkNullAndReturnDouble(jsonObject, Constants.JSON_AMOUNT);
            other = checkNullAndReturnDouble(jsonObject, Constants.JSON_OTHER);
            tax = checkNullAndReturnDouble(jsonObject, Constants.JSON_TAX);
            averageNightly = checkNullAndReturnDouble(jsonObject, Constants.JSON_AVERAGE_NIGHTLY);
            fullyLoadedPriceQuote = checkNullAndReturnDouble(jsonObject, Constants.JSON_FULLY_LOADED_PRICE_QUOTE);
            rent = checkNullAndReturnDouble(jsonObject, Constants.JSON_RENT);
            fees = checkNullAndReturnDouble(jsonObject, Constants.JSON_FEES);
            travelersFee = checkNullAndReturnDouble(jsonObject, Constants.JSON_TRAVELER_FEE);

            return new PriceQuote(currencyUnits, amount, other, tax, averageNightly, fullyLoadedPriceQuote,
                    rent, fees, travelersFee);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for PriceQuote type with error: " + e.getMessage());
        }
    }

    public static PriceRange parsePriceRangeJson(JSONObject jsonObject) throws JSONException {
        double to;
        String currencyUnits;
        String periodType;
        double from;

        try {

            to = checkNullAndReturnDouble(jsonObject, Constants.JSON_TO);
            currencyUnits = checkNullAndReturnString(jsonObject, Constants.JSON_CURRENCY_UNITS);
            periodType = checkNullAndReturnString(jsonObject, Constants.JSON_PERIOD_TYPE);
            from = checkNullAndReturnDouble(jsonObject, Constants.JSON_FROM);

            return new PriceRange(to, currencyUnits, periodType, from);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Listing type with error: " + e.getMessage());
        }
    }

    public static SearchListing parseSearchListingJson(JSONObject jsonObject) throws JSONException, MalformedURLException {
        String headline;
        String accommodations;
        Location location = new Location();
        double bathrooms;
        double bedrooms;
        URL detailsUrl;
        boolean bookWithConfidence = false;
        String listingId;
        String thumbnailUrl;
        String description;
        int reviewCount;
        String listingSource;
        String listingUrl;
        double reviewAverage;

        ArrayList<PriceRange> priceRanges = new ArrayList<>();

        PriceQuote priceQuote = new PriceQuote();

        try {
            headline = checkNullAndReturnString(jsonObject, Constants.JSON_HEADLINE);
            accommodations = checkNullAndReturnString(jsonObject, Constants.JSON_ACCOMMODATIONS);
            location = parseLocationJson(jsonObject.getJSONObject(Constants.JSON_LOCATION));
            bathrooms = checkNullAndReturnDouble(jsonObject, Constants.JSON_ROOM_BATHROOMS);
            bedrooms = checkNullAndReturnDouble(jsonObject, Constants.JSON_ROOM_BEDROOMS);
            detailsUrl = new URL(checkNullAndReturnString(jsonObject, Constants.JSON_DETAILS_URL));

            if (!jsonObject.isNull(Constants.JSON_BOOK_WITH_CONFIDENCE)) {
                bookWithConfidence = jsonObject.getBoolean(Constants.JSON_BOOK_WITH_CONFIDENCE);
            }
            listingId = checkNullAndReturnString(jsonObject, Constants.JSON_LISTING_ID);
            description = checkNullAndReturnString(jsonObject, Constants.JSON_DESCRIPTION);
            reviewCount = checkNullAndReturnInt(jsonObject, Constants.JSON_REVIEW_COUNT);
            listingSource = checkNullAndReturnString(jsonObject, Constants.JSON_LISTING_SOURCE);
            listingUrl = checkNullAndReturnString(jsonObject, Constants.JSON_LISTING_SOURCE_URL);
            reviewAverage = checkNullAndReturnDouble(jsonObject, Constants.JSON_REVIEW_AVERAGE);

            JSONObject thumbnailObject = jsonObject.getJSONObject(Constants.JSON_THUMBNAIL);
            thumbnailUrl = checkNullAndReturnString(thumbnailObject, Constants.JSON_URL);

            JSONArray priceRangeJsonArray = jsonObject.getJSONArray(Constants.JSON_PRICE_RANGES);
            for (int i = 0; i < priceRangeJsonArray.length(); i++) {
                priceRanges.add(parsePriceRangeJson(priceRangeJsonArray.getJSONObject(i)));
            }

            if (!jsonObject.isNull(Constants.JSON_PRICE_QUOTE)) {
                priceQuote = parsePriceQuoteJson(jsonObject.getJSONObject(Constants.JSON_PRICE_QUOTE));
            }

            return new SearchListing(headline, accommodations, location, bathrooms, bedrooms, detailsUrl, bookWithConfidence, listingId, thumbnailUrl,
                    description, reviewCount, listingSource, listingUrl, reviewAverage, priceRanges, priceQuote);

        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for SearchListing type with error: " + e.getMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException("Malformed URL in the detailsURL JSON reply");
        }
    }

    private static int checkNullAndReturnInt(JSONObject jsonObject, String jsonKey) throws JSONException {
        if (jsonObject.has(jsonKey) && !jsonObject.isNull(jsonKey)) {
            return jsonObject.getInt(jsonKey);
        } else {
            return 0;
        }
    }

    private static String checkNullAndReturnString(JSONObject jsonObject, String jsonKey) throws JSONException {
        if (jsonObject.has(jsonKey) && !jsonObject.isNull(jsonKey)) {
            return jsonObject.getString(jsonKey);
        } else {
            return "";
        }
    }

    private static double checkNullAndReturnDouble(JSONObject jsonObject, String jsonKey) throws JSONException {
        if (jsonObject.has(jsonKey) && !jsonObject.isNull(jsonKey)) {
            return jsonObject.getDouble(jsonKey);
        } else {
            return 0;
        }
    }
}