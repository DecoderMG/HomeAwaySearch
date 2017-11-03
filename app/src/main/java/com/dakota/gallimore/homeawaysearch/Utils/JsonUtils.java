package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

}