package com.dakota.gallimore.homeawaysearch;

import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Feature;
import com.dakota.gallimore.homeawaysearch.DataClasses.RatePeriod;
import com.dakota.gallimore.homeawaysearch.DataClasses.Review;
import com.dakota.gallimore.homeawaysearch.DataClasses.Room;
import com.dakota.gallimore.homeawaysearch.Utils.JsonUtils;

import org.json.JSONObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void JsonAminitiesTest() throws Exception {
        String aminityJson = "{\"count\": 1,\"category\": \"AMENITY\",\"description\": \"Shower\",\"localizedName\": \"shower\"}";
        JSONObject jsonObject = new JSONObject(aminityJson);
        Amenities amenities = JsonUtils.parseAmenityJson(jsonObject);
        assertEquals(1, amenities.getCount());
        assertEquals("AMENITY", amenities.getCategory());
        assertEquals("Shower", amenities.getDescription());
        assertEquals("shower", "shower");
    }

    @Test
    public void JsonRoomTest() throws Exception {
        String roomJson = "{\"amenities\": [{" +
                "                \"count\": 1," +
                "                \"category\": \"AMENITY\"," +
                "                \"description\": \"Toilet\"," +
                "                \"localizedName\": \"toilet\"" +
                "              }" +
                "            ]," +
                "            \"name\": \"Toilet 1\"," +
                "            \"roomSubType\": \"NO_TUB_OR_SHOWER\"" +
                "          }";
        JSONObject jsonObject = new JSONObject(roomJson);
        Room room = JsonUtils.parseRoomJson(jsonObject, "bathroom");
        Amenities amenity = new Amenities(1, "AMENITY", "Toilet", "toilet");
        assertEquals("Toilet 1", room.getName());
        assertEquals("NO_TUB_OR_SHOWER", room.getRoomSubType());
        assertEquals("bathroom", room.getRoomType());
        assertEquals(amenity.getCount(), room.getAmenities().get(0).getCount());
        assertEquals(amenity.getCategory(), room.getAmenities().get(0).getCategory());
        assertEquals(amenity.getDescription(), room.getAmenities().get(0).getDescription());
        assertEquals(amenity.getLocalizedName(), room.getAmenities().get(0).getLocalizedName());
    }

    @Test
    public void JsonReviewTest() throws Exception {
        String reviewJson = "{" +
                "            \"arrivalDate\": \"2015-09-24T00:00:00.000Z\"," +
                "            \"reviewerName\": \"John\"," +
                "            \"body\": \"appartement agréable.\"," +
                "            \"headline\": \"7/10\"," +
                "            \"helpfulCount\": 0," +
                "            \"unhelpfulCount\": 0," +
                "            \"reviewLocale\": \"fr_FR\"" +
                "          }";

        JSONObject jsonObject = new JSONObject(reviewJson);
        Review review = JsonUtils.parseReviewJson(jsonObject);
        assertEquals("2015-09-24T00:00:00.000Z", review.getArrivalDate());
        assertEquals("John", review.getReviewerName());
        assertEquals("appartement agréable.", review.getBody());
        assertEquals("7/10", review.getHeadline());
        assertEquals(0, review.getHelpfulCount());
        assertEquals(0, review.getUnhelpfulCount());
        assertEquals("fr_FR", review.getReviewLocale());
    }

    @Test
    public void JsonFeatureTest() throws Exception {
        String featureJson = "{" +
                "            \"count\": 1," +
                "            \"category\": \"GENERAL\"," +
                "            \"description\": \"air-conditioning\"," +
                "            \"localizedName\": \"Air Conditioning\"" +
                "          }";

        JSONObject jsonObject = new JSONObject(featureJson);
        Feature feature = JsonUtils.parseFeatureJson(jsonObject);
        assertEquals(1, feature.getCount());
        assertEquals("GENERAL", feature.getCategory());
        assertEquals("air-conditioning", feature.getDescription());
        assertEquals("Air Conditioning", feature.getLocalizedName());
    }

    @Test
    public void JsonRatePeriodTest() throws Exception {
        String ratePeriodJson = "{" +
                "          \"dateRange\": {" +
                "            \"beginDate\": \"2014-01-25\"," +
                "            \"endDate\": \"2014-03-13\"" +
                "          }," +
                "          \"minimumStay\": 7," +
                "          \"rates\": {" +
                "            \"weekly\": {" +
                "              \"currency\": \"EUR\"," +
                "              \"amount\": 750" +
                "            }" +
                "          }" +
                "        }";
        JSONObject jsonObject = new JSONObject(ratePeriodJson);
        RatePeriod ratePeriod = JsonUtils.parseRatePeriodJson(jsonObject);
        Date arrivalDate = new SimpleDateFormat("YYYY-MM-DD").parse("2014-01-25");
        Date endDate = new SimpleDateFormat("YYYY-MM-DD").parse("2014-03-13");
        assertTrue(ratePeriod.getArrivalDate().compareTo(arrivalDate) == 0);
        assertTrue(ratePeriod.getLeaveDate().compareTo(endDate) == 0);
        assertEquals(7, ratePeriod.getMinimumStay());
        assertEquals(750, ratePeriod.getWeeklyRate(), 0);
        assertEquals("EUR", ratePeriod.getCurrency());
    }
}