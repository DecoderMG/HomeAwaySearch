package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingAdFeature;
import com.dakota.gallimore.homeawaysearch.DataClasses.ListingLocation;
import com.dakota.gallimore.homeawaysearch.DataClasses.PriceQuote;
import com.dakota.gallimore.homeawaysearch.DataClasses.PriceRangeBean;
import com.dakota.gallimore.homeawaysearch.DataClasses.RatePeriod;
import com.dakota.gallimore.homeawaysearch.DataClasses.Review;
import com.dakota.gallimore.homeawaysearch.DataClasses.SearchListing;
import com.dakota.gallimore.homeawaysearch.DataClasses.Site;

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
     * Parses HomeAway ListingAdFeature JSON into a ListingAdFeature object.
     * @param jsonObject - JSONObject containing a single HomeAway ListingAdFeature.
     * @return - New ListingAdFeature object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
    public static ListingAdFeature parseFeatureJson(JSONObject jsonObject) throws JSONException {
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
            throw new JSONException("Invalid Json data for ListingAdFeature type with error: " + e.getMessage());
        }

        return new ListingAdFeature(count, category, description, localizedName);
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
     * Parses HomeAway ListingLocation JSON into a ListingLocation object.
     * @param jsonObject - JSONObject containing a single HomeAway ListingLocation.
     * @return - New ListingLocation object populated with provided JSON data.
     * @throws JSONException - When inputted Json object does not match HomeAway documented JSON.
     */
    public static ListingLocation parseLocationJson(JSONObject jsonObject) throws JSONException {
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

            return new ListingLocation(lat, lng, city, state, counrty);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for ListingLocation type with error: " + e.getMessage());
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

    public static PriceRangeBean parsePriceRangeJson(JSONObject jsonObject) throws JSONException {
        double to;
        String currencyUnits;
        String periodType;
        double from;

        try {

            to = checkNullAndReturnDouble(jsonObject, Constants.JSON_TO);
            currencyUnits = checkNullAndReturnString(jsonObject, Constants.JSON_CURRENCY_UNITS);
            periodType = checkNullAndReturnString(jsonObject, Constants.JSON_PERIOD_TYPE);
            from = checkNullAndReturnDouble(jsonObject, Constants.JSON_FROM);

            return new PriceRangeBean(to, currencyUnits, periodType, from);
        } catch (JSONException e) {
            Log.d(Constants.JSON_LOG_TAG, e.getMessage());
            throw new JSONException("Invalid JSON data for Listing type with error: " + e.getMessage());
        }
    }

    public static SearchListing parseSearchListingJson(JSONObject jsonObject) throws JSONException, MalformedURLException {
        String headline;
        String accommodations;
        ListingLocation listingLocation = new ListingLocation();
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

        ArrayList<PriceRangeBean> priceRangeBeans = new ArrayList<>();

        PriceQuote priceQuote = new PriceQuote();

        try {
            headline = checkNullAndReturnString(jsonObject, Constants.JSON_HEADLINE);
            accommodations = checkNullAndReturnString(jsonObject, Constants.JSON_ACCOMMODATIONS);
            listingLocation = parseLocationJson(jsonObject.getJSONObject(Constants.JSON_LOCATION));
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
                priceRangeBeans.add(parsePriceRangeJson(priceRangeJsonArray.getJSONObject(i)));
            }

            if (!jsonObject.isNull(Constants.JSON_PRICE_QUOTE)) {
                priceQuote = parsePriceQuoteJson(jsonObject.getJSONObject(Constants.JSON_PRICE_QUOTE));
            }

            return new SearchListing(headline, accommodations, listingLocation, bathrooms, bedrooms, detailsUrl, bookWithConfidence, listingId, thumbnailUrl,
                    description, reviewCount, listingSource, listingUrl, reviewAverage, priceRangeBeans, priceQuote);

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