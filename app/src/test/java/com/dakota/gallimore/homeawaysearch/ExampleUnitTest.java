package com.dakota.gallimore.homeawaysearch;

import com.dakota.gallimore.homeawaysearch.DataClasses.Amenities;
import com.dakota.gallimore.homeawaysearch.DataClasses.Room;
import com.dakota.gallimore.homeawaysearch.Utils.JsonUtils;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        String RoomJson = "{\"amenities\": [{" +
                "                \"count\": 1," +
                "                \"category\": \"AMENITY\"," +
                "                \"description\": \"Toilet\"," +
                "                \"localizedName\": \"toilet\"" +
                "              }" +
                "            ]," +
                "            \"name\": \"Toilet 1\"," +
                "            \"roomSubType\": \"NO_TUB_OR_SHOWER\"" +
                "          }";
        JSONObject jsonObject = new JSONObject(RoomJson);
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
}