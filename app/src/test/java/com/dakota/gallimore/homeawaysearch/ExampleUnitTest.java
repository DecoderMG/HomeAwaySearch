package com.dakota.gallimore.homeawaysearch;

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
import com.dakota.gallimore.homeawaysearch.Utils.JsonUtils;

import org.json.JSONObject;
import org.junit.Test;

import java.net.URL;
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

    @Test
    public void JsonUnitTest() throws Exception {
        String unitJson = "{\"unitNumber\": 2052470," +
                "      \"unitContent\": { " +
                "        \"area\": 115, " +
                "        \"areaUnit\": \"METERS_SQUARED\", " +
                "        \"bathrooms\": [ " +
                "          { " +
                "            \"amenities\": [ " +
                "              { " +
                "                \"count\": 1, " +
                "                \"category\": \"AMENITY\", " +
                "                \"description\": \"Toilet\", " +
                "                \"localizedName\": \"toilet\" " +
                "              } " +
                "            ], " +
                "            \"name\": \"Toilet 1\", " +
                "            \"roomSubType\": \"NO_TUB_OR_SHOWER\" " +
                "          }, " +
                "          { " +
                "            \"amenities\": [ " +
                "              { " +
                "                \"count\": 1, " +
                "                \"category\": \"AMENITY\", " +
                "                \"description\": \"Shower\", " +
                "                \"localizedName\": \"shower\" " +
                "              } " +
                "            ], " +
                "            \"name\": \"Shower room 1\", " +
                "            \"roomSubType\": \"SHOWER_INDOOR_OR_OUTDOOR\" " +
                "          } " +
                "        ], " +
                "        \"bedrooms\": [ " +
                "          { " +
                "            \"amenities\": [], " +
                "            \"name\": \"Bedroom 1\", " +
                "            \"roomSubType\": \"BEDROOM\" " +
                "          }, " +
                "          { " +
                "            \"amenities\": [], " +
                "            \"name\": \"Bedroom 2\", " +
                "            \"roomSubType\": \"BEDROOM\" " +
                "          }, " +
                "          { " +
                "            \"amenities\": [], " +
                "            \"name\": \"Bedroom 3\", " +
                "            \"roomSubType\": \"BEDROOM\" " +
                "          } " +
                "        ], " +
                "        \"maxSleep\": 6, " +
                "        \"maxSleepInBeds\": 4, " +
                "        \"numberOfBathrooms\": 2, " +
                "        \"numberOfBedrooms\": 3, " +
                "        \"propertyType\": \"apartment\", " +
                "        \"features\": [ " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"GENERAL\", " +
                "            \"description\": \"air-conditioning\", " +
                "            \"localizedName\": \"Air Conditioning\" " +
                "          }, " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"KITCHEN\", " +
                "            \"description\": \"dish washer\", " +
                "            \"localizedName\": \"Dishwasher\" " +
                "          }, " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"GENERAL\", " +
                "            \"description\": \"washing machine\", " +
                "            \"localizedName\": \"Washing Machine\" " +
                "          }, " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"ENTERTAINMENT\", " +
                "            \"description\": \"tv\", " +
                "            \"localizedName\": \"Television\" " +
                "          }, " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"SUITABILITY\", " +
                "            \"description\": \"Handicapped Accessible (may have limitations)\", " +
                "            \"localizedName\": \"limited accessibility\" " +
                "          }, " +
                "          { " +
                "            \"count\": 1, " +
                "            \"category\": \"KITCHEN\", " +
                "            \"description\": \"Kettle\", " +
                "            \"localizedName\": \"Kettle\" " +
                "          } " +
                "        ] " +
                "      }, " +
                "      \"reviewSummary\": { " +
                "        \"reviewCount\": 1, " +
                "        \"averageRating\": 4, " +
                "        \"oneStarReviewCount\": 0, " +
                "        \"twoStarReviewCount\": 0, " +
                "        \"threeStarReviewCount\": 0, " +
                "        \"fourStarReviewCount\": 1, " +
                "        \"fiveStarReviewCount\": 0, " +
                "        \"guestbookReviewCount\": 0 " +
                "      }, " +
                "      \"unitReviewContent\": { " +
                "        \"size\": 1, " +
                "        \"page\": 1, " +
                "        \"entries\": [ " +
                "          { " +
                "            \"arrivalDate\": \"2015-09-24T00:00:00.000Z\", " +
                "            \"reviewerName\": \"\", " +
                "            \"body\": \"appartement agréable ,très bien situé avec une station de métro au pas de la porte.\", " +
                "            \"headline\": \"7/10\", " +
                "            \"helpfulCount\": 0, " +
                "            \"unhelpfulCount\": 0, " +
                "            \"reviewLocale\": \"fr_FR\" " +
                "          } " +
                "        ] " +
                "      }, " +
                "      \"ratePeriods\": [ " +
                "        { " +
                "          \"dateRange\": { " +
                "            \"beginDate\": \"2014-01-25\", " +
                "            \"endDate\": \"2014-03-13\" " +
                "          }, " +
                "          \"minimumStay\": 7, " +
                "          \"rates\": { " +
                "            \"weekly\": { " +
                "              \"currency\": \"EUR\", " +
                "              \"amount\": 750 " +
                "            } " +
                "          } " +
                "        }, " +
                "        { " +
                "          \"dateRange\": { " +
                "            \"beginDate\": \"2014-12-19\", " +
                "            \"endDate\": \"2015-01-05\" " +
                "          }, " +
                "          \"minimumStay\": 7, " +
                "          \"rates\": { " +
                "            \"weekly\": { " +
                "              \"currency\": \"EUR\", " +
                "              \"amount\": 1200 " +
                "            } " +
                "          } " +
                "        } " +
                "      ], " +
                "      \"unitAvailability\": { " +
                "        \"availabilityDefault\": \"Y\", " +
                "        \"changeOverDefault\": \"C\", " +
                "        \"dateRange\": { " +
                "          \"beginDate\": \"2015-10-16\", " +
                "          \"endDate\": \"2018-10-15\" " +
                "        }, " +
                "        \"maxStayDefault\": 0, " +
                "        \"minPriorNotifyDefault\": 1, " +
                "        \"minStayDefault\": 0, " +
                "        \"stayIncrementDefault\": \"D\", " +
                "        \"unitAvailabilityConfiguration\": { " +
                "          \"availability\": \"NNNNNNNNNYNNNNNNNYYYNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYNNNNNNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY\", " +
                "          \"availableUnitCount\": null, " +
                "          \"changeOver\": \"XXXXXXXXXXOXXXXXXIOOOXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCOOOOOOOXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCOOOXXXXXXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC\", " +
                "          \"maxStay\": \"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\", " +
                "          \"minPriorNotify\": \"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1\", " +
                "          \"minStay\": \"3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3\", " +
                "          \"stayIncrement\": \"DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD\" " +
                "        } " +
                "      }" +
                "    }";

        JSONObject jsonObject = new JSONObject(unitJson);
        Unit unit = JsonUtils.parseUnitJson(jsonObject);
        assertEquals(2052470, unit.getUnitNumber());
        assertEquals(115, unit.getUnitArea());
        assertEquals("METERS_SQUARED", unit.getAreaUnit());
        assertEquals("Toilet", unit.getRoom(0).getAmenities().get(0).getDescription());
        assertEquals("Toilet 1", unit.getRoom(0).getName());
        assertEquals("bathroom", unit.getRoom(0).getRoomType());
        assertEquals("bedroom", unit.getRoom(2).getRoomType());
        assertEquals("BEDROOM", unit.getRoom(2).getRoomSubType());
        assertEquals(6, unit.getMaxSleep());
        assertEquals(4, unit.getMaxSleepInBeds());
        assertEquals(2, unit.getNumOfBathrooms());
        assertEquals(3, unit.getNumOfBedrooms());
        assertEquals("apartment", unit.getPropertyType());
        assertEquals("air-conditioning", unit.getFeature(0).getDescription());
        assertEquals(7, unit.getRatePeriod(0).getMinimumStay());
        assertEquals("appartement agréable ,très bien situé avec une station de métro au pas de la porte.", unit.getReview(0).getBody());
    }

    @Test
    public void JsonListingMediaPhotoTest() throws Exception {
        String listingMediaJson = "{ " +
                "        \"caption\": \"sitting area with TV set\", " +
                "        \"small\": { " +
                "          \"dimension\": { " +
                "            \"height\": 100, " +
                "            \"width\": 134 " +
                "          }, " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.1\" " +
                "        }, " +
                "        \"medium\": { " +
                "          \"dimension\": { " +
                "            \"height\": 270, " +
                "            \"width\": 361 " +
                "          }, " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.9\" " +
                "        }, " +
                "        \"large\": { " +
                "          \"dimension\": { " +
                "            \"height\": 663, " +
                "            \"width\": 1000 " +
                "          }, " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10\" " +
                "        }, " +
                "        \"originalDimension\": { " +
                "          \"height\": 663, " +
                "          \"width\": 1000 " +
                "        } " +
                "      }";
        JSONObject jsonObject = new JSONObject(listingMediaJson);
        ListingMedia listingMedia = JsonUtils.parseMediaJson(jsonObject, "photo");
        assertEquals("sitting area with TV set", listingMedia.getCaption());
        assertEquals(663, listingMedia.getHeight());
        assertEquals(1000, listingMedia.getWidth());
        assertEquals(new URL("http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10"),
                listingMedia.getUri());
    }

    @Test
    public void JsonListingMediaThumbnailTest() throws Exception {
        String listingMediaJson = "{ " +
                "        \"photo\": { " +
                "          \"caption\": \"sitting area with TV set\", " +
                "          \"small\": { " +
                "            \"dimension\": { " +
                "              \"height\": 100, " +
                "              \"width\": 134 " +
                "            }, " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.1\" " +
                "          }, " +
                "          \"medium\": { " +
                "            \"dimension\": { " +
                "              \"height\": 270, " +
                "              \"width\": 361 " +
                "            }, " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.9\" " +
                "          }, " +
                "          \"large\": { " +
                "            \"dimension\": { " +
                "              \"height\": 663, " +
                "              \"width\": 1000 " +
                "            }, " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10\" " +
                "          }, " +
                "          \"originalDimension\": { " +
                "            \"height\": 663, " +
                "            \"width\": 1000 " +
                "          } " +
                "        }, " +
                "        \"unitNumber\": 2052470 " +
                "      }";
        JSONObject jsonObject = new JSONObject(listingMediaJson);
        ListingMedia listingMedia = JsonUtils.parseMediaJson(jsonObject, "thumbnail");
        assertEquals("sitting area with TV set", listingMedia.getCaption());
        assertEquals(663, listingMedia.getHeight());
        assertEquals(1000, listingMedia.getWidth());
        assertEquals(new URL("http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10"),
                listingMedia.getUri());
        assertEquals(2052470, listingMedia.getUnitNumber());
    }

    @Test
    public void JsonSiteTest() throws Exception {
        String siteJson = "{" +
                "      \"href\": \"https://www.homeaway.dk/feriehus/p6592159\"," +
                "      \"rel\": \"HOMEAWAY_DK\"" +
                "    }";
        JSONObject jsonObject = new JSONObject(siteJson);
        Site site = JsonUtils.parseSiteJson(jsonObject);
        assertEquals("https://www.homeaway.dk/feriehus/p6592159", site.getHref());
        assertEquals("HOMEAWAY_DK", site.getRel());
    }

    @Test
    public void JsonLocationTest() throws Exception {
        String locationJson = "{" +
                "    \"lat\": 40.4255485534668," +
                "    \"lng\": -3.7075681686401367" +
                "  }";
        JSONObject jsonObject = new JSONObject(locationJson);
        Location location = JsonUtils.parseLocationJson(jsonObject);
        assertEquals(40.4255485534668, location.getLatitude(), 0);
        assertEquals(-3.7075681686401367, location.getLongitude(), 0);
        assertEquals("", location.getCity());
        assertEquals("", location.getState());
        assertEquals("", location.getCountry());
    }

    @Test
    public void JsonListingTest() throws Exception {
        String listingJson = "{  " +
                "  \"listingId\": \"6592159\",  " +
                "  \"listingUrl\": \"https://www.homeaway.com/vacation-rental/p6592159\",  " +
                "  \"sourceLocale\": \"es\",  " +
                "  \"sourceLocaleName\": \"spanish\",  " +
                "  \"adContent\": {  " +
                "    \"description\": \"The apartment has been completely renovated with the finest materials. It is located in a typical old building in Madrid with corrala. Less than five minutes walk from the Gran Vía and tourist attractions of the city (Plaza Mayor, Puerta del Sol, Madrid de los Austrias) and with excellent communications with the city and surrounding areas.\",  " +
                "    \"headline\": \"Spacious luxury apartment close to Gran Via\"  " +
                "  },  " +
                "  \"features\": [  " +
                "    {  " +
                "      \"count\": 1,  " +
                "      \"category\": \"LOCATION_TYPE\",  " +
                "      \"description\": \"town\",  " +
                "      \"localizedName\": \"Town\"  " +
                "    }  " +
                "  ],  " +
                "  \"location\": {  " +
                "    \"lat\": 40.4255485534668,  " +
                "    \"lng\": -3.7075681686401367  " +
                "  },  " +
                "  \"sites\": [  " +
                "    {  " +
                "      \"href\": \"https://www.homeaway.dk/feriehus/p6592159\",  " +
                "      \"rel\": \"HOMEAWAY_DK\"  " +
                "    },  " +
                "    {  " +
                "      \"href\": \"https://www.homeaway.se/semesterhus/p6592159\",  " +
                "      \"rel\": \"HOMEAWAY_SE\"  " +
                "    }  " +
                "  ],  " +
                "  \"photos\": {  " +
                "    \"photos\": [  " +
                "      {  " +
                "        \"caption\": \"sitting area with TV set\",  " +
                "        \"small\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 100,  " +
                "            \"width\": 134  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.1\"  " +
                "        },  " +
                "        \"medium\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 270,  " +
                "            \"width\": 361  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.9\"  " +
                "        },  " +
                "        \"large\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 663,  " +
                "            \"width\": 1000  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10\"  " +
                "        },  " +
                "        \"originalDimension\": {  " +
                "          \"height\": 663,  " +
                "          \"width\": 1000  " +
                "        }  " +
                "      },  " +
                "      {  " +
                "        \"small\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 100,  " +
                "            \"width\": 66  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/fc3d6fa9-f8d3-49de-af8e-1f01d06ad989.1.1\"  " +
                "        },  " +
                "        \"medium\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 270,  " +
                "            \"width\": 178  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/fc3d6fa9-f8d3-49de-af8e-1f01d06ad989.1.9\"  " +
                "        },  " +
                "        \"large\": {  " +
                "          \"dimension\": {  " +
                "            \"height\": 768,  " +
                "            \"width\": 508  " +
                "          },  " +
                "          \"uri\": \"http://imagesus.homeaway.com/mda01/fc3d6fa9-f8d3-49de-af8e-1f01d06ad989.1.10\"  " +
                "        },  " +
                "        \"originalDimension\": {  " +
                "          \"height\": 1000,  " +
                "          \"width\": 662  " +
                "        }  " +
                "      }  " +
                "    ],  " +
                "    \"thumbnails\": [  " +
                "      {  " +
                "        \"photo\": {  " +
                "          \"caption\": \"sitting area with TV set\",  " +
                "          \"small\": {  " +
                "            \"dimension\": {  " +
                "              \"height\": 100,  " +
                "              \"width\": 134  " +
                "            },  " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.1\"  " +
                "          },  " +
                "          \"medium\": {  " +
                "            \"dimension\": {  " +
                "              \"height\": 270,  " +
                "              \"width\": 361  " +
                "            },  " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.9\"  " +
                "          },  " +
                "          \"large\": {  " +
                "            \"dimension\": {  " +
                "              \"height\": 663,  " +
                "              \"width\": 1000  " +
                "            },  " +
                "            \"uri\": \"http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10\"  " +
                "          },  " +
                "          \"originalDimension\": {  " +
                "            \"height\": 663,  " +
                "            \"width\": 1000  " +
                "          }  " +
                "        },  " +
                "        \"unitNumber\": 2052470  " +
                "      }  " +
                "    ]  " +
                "  },  " +
                "  \"units\": [  " +
                "    {  " +
                "      \"unitNumber\": 2052470,  " +
                "      \"unitContent\": {  " +
                "        \"area\": 115,  " +
                "        \"areaUnit\": \"METERS_SQUARED\",  " +
                "        \"bathrooms\": [  " +
                "          {  " +
                "            \"amenities\": [  " +
                "              {  " +
                "                \"count\": 1,  " +
                "                \"category\": \"AMENITY\",  " +
                "                \"description\": \"Toilet\",  " +
                "                \"localizedName\": \"toilet\"  " +
                "              }  " +
                "            ],  " +
                "            \"name\": \"Toilet 1\",  " +
                "            \"roomSubType\": \"NO_TUB_OR_SHOWER\"  " +
                "          },  " +
                "          {  " +
                "            \"amenities\": [  " +
                "              {  " +
                "                \"count\": 1,  " +
                "                \"category\": \"AMENITY\",  " +
                "                \"description\": \"Shower\",  " +
                "                \"localizedName\": \"shower\"  " +
                "              }  " +
                "            ],  " +
                "            \"name\": \"Shower room 1\",  " +
                "            \"roomSubType\": \"SHOWER_INDOOR_OR_OUTDOOR\"  " +
                "          },  " +
                "          {  " +
                "            \"amenities\": [  " +
                "              {  " +
                "                \"count\": 1,  " +
                "                \"category\": \"AMENITY\",  " +
                "                \"description\": \"Shower\",  " +
                "                \"localizedName\": \"shower\"  " +
                "              }  " +
                "            ],  " +
                "            \"name\": \"Shower room 2\",  " +
                "            \"roomSubType\": \"SHOWER_INDOOR_OR_OUTDOOR\"  " +
                "          }  " +
                "        ],  " +
                "        \"bedrooms\": [  " +
                "          {  " +
                "            \"amenities\": [],  " +
                "            \"name\": \"Bedroom 1\",  " +
                "            \"roomSubType\": \"BEDROOM\"  " +
                "          },  " +
                "          {  " +
                "            \"amenities\": [],  " +
                "            \"name\": \"Bedroom 2\",  " +
                "            \"roomSubType\": \"BEDROOM\"  " +
                "          },  " +
                "          {  " +
                "            \"amenities\": [],  " +
                "            \"name\": \"Bedroom 3\",  " +
                "            \"roomSubType\": \"BEDROOM\"  " +
                "          }  " +
                "        ],  " +
                "        \"maxSleep\": 6,  " +
                "        \"maxSleepInBeds\": 4,  " +
                "        \"numberOfBathrooms\": 3,  " +
                "        \"numberOfBedrooms\": 3,  " +
                "        \"propertyType\": \"apartment\",  " +
                "        \"features\": [  " +
                "          {  " +
                "            \"count\": 1,  " +
                "            \"category\": \"GENERAL\",  " +
                "            \"description\": \"air-conditioning\",  " +
                "            \"localizedName\": \"Air Conditioning\"  " +
                "          },  " +
                "          {  " +
                "            \"count\": 1,  " +
                "            \"category\": \"GENERAL\",  " +
                "            \"description\": \"linen provided\",  " +
                "            \"localizedName\": \"Linens Provided\"  " +
                "          },  " +
                "          {  " +
                "            \"count\": 1,  " +
                "            \"category\": \"SUITABILITY\",  " +
                "            \"description\": \"Handicapped Accessible (may have limitations)\",  " +
                "            \"localizedName\": \"limited accessibility\"  " +
                "          },  " +
                "          {  " +
                "            \"count\": 1,  " +
                "            \"category\": \"KITCHEN\",  " +
                "            \"description\": \"Kettle\",  " +
                "            \"localizedName\": \"Kettle\"  " +
                "          }  " +
                "        ]  " +
                "      },  " +
                "      \"reviewSummary\": {  " +
                "        \"reviewCount\": 1,  " +
                "        \"averageRating\": 4,  " +
                "        \"oneStarReviewCount\": 0,  " +
                "        \"twoStarReviewCount\": 0,  " +
                "        \"threeStarReviewCount\": 0,  " +
                "        \"fourStarReviewCount\": 1,  " +
                "        \"fiveStarReviewCount\": 0,  " +
                "        \"guestbookReviewCount\": 0  " +
                "      },  " +
                "      \"unitReviewContent\": {  " +
                "        \"size\": 1,  " +
                "        \"page\": 1,  " +
                "        \"entries\": [  " +
                "          {  " +
                "            \"arrivalDate\": \"2015-09-24T00:00:00.000Z\",  " +
                "            \"reviewerName\": \"\",  " +
                "            \"body\": \"appartement agréable.\",  " +
                "            \"headline\": \"7/10\",  " +
                "            \"helpfulCount\": 0,  " +
                "            \"unhelpfulCount\": 0,  " +
                "            \"reviewLocale\": \"fr_FR\"  " +
                "          }  " +
                "        ]  " +
                "      },  " +
                "      \"ratePeriods\": [  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-01-25\",  " +
                "            \"endDate\": \"2014-03-13\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 750  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-03-22\",  " +
                "            \"endDate\": \"2014-04-09\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 755  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-04-11\",  " +
                "            \"endDate\": \"2014-04-20\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 900  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-04-22\",  " +
                "            \"endDate\": \"2014-06-29\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 850  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-07-01\",  " +
                "            \"endDate\": \"2014-08-30\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 900  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-09-01\",  " +
                "            \"endDate\": \"2014-12-17\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 750  " +
                "            }  " +
                "          }  " +
                "        },  " +
                "        {  " +
                "          \"dateRange\": {  " +
                "            \"beginDate\": \"2014-12-19\",  " +
                "            \"endDate\": \"2015-01-05\"  " +
                "          },  " +
                "          \"minimumStay\": 7,  " +
                "          \"rates\": {  " +
                "            \"weekly\": {  " +
                "              \"currency\": \"EUR\",  " +
                "              \"amount\": 1200  " +
                "            }  " +
                "          }  " +
                "        }  " +
                "      ],  " +
                "      \"unitAvailability\": {  " +
                "        \"availabilityDefault\": \"Y\",  " +
                "        \"changeOverDefault\": \"C\",  " +
                "        \"dateRange\": {  " +
                "          \"beginDate\": \"2015-10-16\",  " +
                "          \"endDate\": \"2018-10-15\"  " +
                "        },  " +
                "        \"maxStayDefault\": 0,  " +
                "        \"minPriorNotifyDefault\": 1,  " +
                "        \"minStayDefault\": 0,  " +
                "        \"stayIncrementDefault\": \"D\",  " +
                "        \"unitAvailabilityConfiguration\": {  " +
                "          \"availability\": \"NNNNNNNNNYNNNNNNNYYYNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYNNNNNNNNNNNNYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY\",  " +
                "          \"availableUnitCount\": null,  " +
                "          \"changeOver\": \"XXXXXXXXXXOXXXXXXIOOOXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCOOOOOOOXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCOOOXXXXXXXXXXXICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC\",  " +
                "          \"maxStay\": \"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\",  " +
                "          \"minPriorNotify\": \"1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1\",  " +
                "          \"minStay\": \"3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3\",  " +
                "          \"stayIncrement\": \"DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD\"  " +
                "        }  " +
                "      }  " +
                "    }  " +
                "  ]  " +
                "}";

        JSONObject jsonObject = new JSONObject(listingJson);
        Listing listing = JsonUtils.parseListingJson(jsonObject);

        assertEquals("6592159", listing.getListingId());
        assertEquals("https://www.homeaway.com/vacation-rental/p6592159", listing.getListingUrl());
        assertEquals("es", listing.getSourceLocale());
        assertEquals("spanish", listing.getSourceLocaleName());

        assertEquals("The apartment has been completely renovated with the finest materials. It is located in a typical old building in Madrid with corrala. Less than five minutes walk from the Gran Vía and tourist attractions of the city (Plaza Mayor, Puerta del Sol, Madrid de los Austrias) and with excellent communications with the city and surrounding areas.", listing.getDescription());
        assertEquals("Spacious luxury apartment close to Gran Via", listing.getHeadline());

        assertEquals(1, listing.getFeature(0).getCount());
        assertEquals("town", listing.getFeature(0).getDescription());

        assertEquals(40.4255485534668, listing.getLocation().getLatitude(), 0);
        assertEquals(-3.7075681686401367, listing.getLocation().getLongitude(), 0);
        assertEquals("", listing.getLocation().getCity());

        assertEquals("https://www.homeaway.se/semesterhus/p6592159", listing.getSite(1).getHref());
        assertEquals("HOMEAWAY_SE", listing.getSite(1).getRel());

        assertEquals("photo", listing.getPhoto(1).getImageType());
        assertEquals(new URL("http://imagesus.homeaway.com/mda01/fc3d6fa9-f8d3-49de-af8e-1f01d06ad989.1.10"), listing.getPhoto(1).getUri());

        assertEquals("thumbnail", listing.getPhoto(2).getImageType());
        assertEquals(new URL("http://imagesus.homeaway.com/mda01/305eee9b-d1e5-492e-beda-6922a4e21f13.1.10"), listing.getPhoto(2).getUri());

        assertEquals(2052470, listing.getUnit(0).getUnitNumber());
        assertEquals("METERS_SQUARED", listing.getUnit(0).getAreaUnit());

        assertEquals("bathroom", listing.getUnit(0).getRoom(0).getRoomType());

        assertEquals("AMENITY", listing.getUnit(0).getRoom(0).getAmenities().get(0).getCategory());
        assertEquals("Toilet", listing.getUnit(0).getRoom(0).getAmenities().get(0).getDescription());

        assertEquals(6, listing.getUnit(0).getMaxSleep());

        assertEquals("Kettle", listing.getUnit(0).getFeature(3).getLocalizedName());

        assertEquals("7/10", listing.getUnit(0).getReview(0).getHeadline());

        assertEquals(755, listing.getUnit(0).getRatePeriod(1).getWeeklyRate(), 0);
    }

    @Test
    public void JsonPriceQuoteTest() throws Exception {
        String priceQuoteJson = "{  " +
                "            \"currencyUnits\":\"USD\"," +
                "            \"amount\":null," +
                "            \"other\":15.2," +
                "            \"tax\":null," +
                "            \"averageNightly\":139," +
                "            \"fullyLoadedPriceQuote\":null," +
                "            \"rent\":null," +
                "            \"fees\":0.59," +
                "            \"travelerFee\":null" +
                "         }";

        JSONObject jsonObject = new JSONObject(priceQuoteJson);
        PriceQuote priceQuote = JsonUtils.parsePriceQuoteJson(jsonObject);

        assertEquals("USD", priceQuote.getCurrencyUnits());
        assertEquals(0, priceQuote.getAmount(), 0);
        assertEquals(15.2, priceQuote.getOther(), 0);
        assertEquals(0, priceQuote.getTax(), 0);
        assertEquals(139, priceQuote.getAverageNightly(), 0);
        assertEquals(0, priceQuote.getFullyLoadedPriceQuote(), 0);
        assertEquals(0, priceQuote.getRent(), 0);
        assertEquals(0.59, priceQuote.getFees(), 0);
        assertEquals(0, priceQuote.getTravelerFee(), 0);
    }

    @Test
    public void JsonPriceRangeTest() throws Exception {
        String priceRangeJson = "{  " +
                "               \"to\":140," +
                "               \"currencyUnits\":\"USD\"," +
                "               \"periodType\":\"NIGHTLY-WEEKDAY\"," +
                "               \"from\":145.3" +
                "            }";
        JSONObject jsonObject = new JSONObject(priceRangeJson);
        PriceRange priceRange = JsonUtils.parsePriceRangeJson(jsonObject);

        assertEquals(140, priceRange.getToPrice(), 0);
        assertEquals("USD", priceRange.getCurrencyUnits());
        assertEquals("NIGHTLY-WEEKDAY", priceRange.getPeriodType());
        assertEquals(145.3, priceRange.getFromPrice(), 0);
    }

    @Test
    public void JsonSearchListingTest() throws Exception {
        String searchListingJson = "{   " +
                "         \"minStayRange\":{   " +
                "            \"minStayHigh\":1, " +
                "            \"minStayLow\":1 " +
                "         }, " +
                "         \"headline\":\"Charming & Comfortable Hyde Park Guest House\", " +
                "         \"priceRanges\":[   " +
                "            {   " +
                "               \"to\":140, " +
                "               \"currencyUnits\":\"USD\", " +
                "               \"periodType\":\"NIGHTLY-WEEKDAY\", " +
                "               \"from\":140 " +
                "            } " +
                "         ], " +
                "         \"accommodations\":\"1 BR, 1.0BA, Sleeps 2\", " +
                "         \"location\":{   " +
                "            \"state\":\"TX\", " +
                "            \"lng\":-97.73258226, " +
                "            \"lat\":30.29954106, " +
                "            \"country\":\"US\", " +
                "            \"city\":\"Austin\" " +
                "         }, " +
                "         \"bathrooms\":1, " +
                "         \"detailsUrl\":\"https:\\/\\/ws.homeaway.com\\/public\\/listing?id=v1183471\", " +
                "         \"bookWithConfidence\":true, " +
                "         \"regionPath\":\"Texas Hill Country > Austin Guest_house #v1183471\", " +
                "         \"bedrooms\":1, " +
                "         \"listingId\":\"v1183471\", " +
                "         \"thumbnail\":{   " +
                "            \"secureUri\":\"https:\\/\\/imagesus-ssl.homeaway.com\\/mda01\\/18844b11-187c-4a8a-ad63-006d87fca233.1.1\", " +
                "            \"imageSize\":\"SMALL\", " +
                "            \"height\":100, " +
                "            \"uri\":\"http:\\/\\/imagesus.homeaway.com\\/mda01\\/18844b11-187c-4a8a-ad63-006d87fca233.1.1\", " +
                "            \"width\":133 " +
                "         }, " +
                "         \"priceQuote\":{   " +
                "            \"currencyUnits\":\"USD\", " +
                "            \"amount\":null, " +
                "            \"other\":null, " +
                "            \"tax\":null, " +
                "            \"averageNightly\":139, " +
                "            \"fullyLoadedPriceQuote\":null, " +
                "            \"rent\":null, " +
                "            \"fees\":null, " +
                "            \"travelerFee\":null " +
                "         }, " +
                "         \"description\":\"Charming & perfectly located backyard bungalow for your next visit to beautiful Austin, TX! Located just off 38th St. in renown Hyde Park and a hair north of Univ. of Texas (just over 1 mi. from Darrell Royal Memorial Stadium for football games), ...\", " +
                "         \"reviewCount\":0, " +
                "         \"listingSource\":\"vrbo\", " +
                "         \"listingUrl\":\"https:\\/\\/www.vrbo.com\\/1183471\", " +
                "         \"reviewAverage\":0 " +
                "      }";
        JSONObject jsonObject = new JSONObject(searchListingJson);
        SearchListing searchListing = JsonUtils.parseSearchListingJson(jsonObject);

        assertEquals("Charming & Comfortable Hyde Park Guest House", searchListing.getHeadline());
        assertEquals("1 BR, 1.0BA, Sleeps 2", searchListing.getAccommodations());
        assertEquals(30.29954106, searchListing.getLocation().getLatitude(), 0);
        assertEquals(1, searchListing.getBathrooms(), 0);
        assertEquals(1, searchListing.getBedrooms(), 0);
        assertEquals(new URL("https://ws.homeaway.com/public/listing?id=v1183471"), searchListing.getDetailsUrl());
        assertTrue(searchListing.isBookWithConfidence());
        assertEquals("v1183471", searchListing.getListingId());
        assertEquals("Charming & perfectly located backyard bungalow for your next visit to beautiful Austin, TX! Located just off 38th St. in renown Hyde Park and a hair north of Univ. of Texas (just over 1 mi. from Darrell Royal Memorial Stadium for football games), ...", searchListing.getDescription());
        assertEquals(0, searchListing.getReviewCount(), 0);
        assertEquals("vrbo", searchListing.getListingSource());
        assertEquals("https://www.vrbo.com/1183471", searchListing.getListingUrl());
        assertEquals(0, searchListing.getReviewAverage(), 0);
        assertEquals("http://imagesus.homeaway.com/mda01/18844b11-187c-4a8a-ad63-006d87fca233.1.1", searchListing.getThumbnailUrl());
        assertEquals(140, searchListing.getPriceRange(0).getToPrice(), 0);
    }
}