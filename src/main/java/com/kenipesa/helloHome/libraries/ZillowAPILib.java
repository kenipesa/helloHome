package com.kenipesa.helloHome.libraries;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ZillowAPILib {
    public static JSONObject getNeighborhood(String state, String city) {
        // Variables
        JSONObject xmlJSONObj = null;

        // Set up for api request
        RestTemplate restTemplate = new RestTemplate();
        String zillowURL = "http://www.zillow.com/webservice/GetRegionChildren.htm";
        // A few pieces of dead code in here, it seems!
        // Build URi and add query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(zillowURL)
                .queryParam("zws-id", System.getenv("API_KEY"))
                .queryParam("state", state)
                .queryParam("city", city)
                .queryParam("childtype", "neighborhood");
        // Get the response
        HttpEntity<String> response = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);

        try{ // Convert XML Response to JSON
            xmlJSONObj = XML.toJSONObject(Objects.requireNonNull(response.getBody()));
        }
        catch(Exception e) {
            System.err.println(e);
        }

        return xmlJSONObj;
    }

    public static List<ResultObj> getFilteredResults(JSONObject webResponse) {
        List<ResultObj> resList = new ArrayList<>();
        JSONObject unFiltered = webResponse.getJSONObject("RegionChildren:regionchildren")
                    .getJSONObject("response")
                    .getJSONObject("list");
        // so we have these two numbers set as -1 and then compare them against each other in that loop?
        // this smells like leftover code from scraping that wasn't rebuilt well
        // after realizing that scraping wouldn't work.

        // I think you can get rid of these vars and the conditionals.
        int ourBudget = -1;
        int urlPrice = -1;
        String marketType = "Cold";
        
        int count = unFiltered.getInt("count");

        // A way to DRY out this logic would be to create a helper that took in the object.
        if (count > 1) {
            JSONArray tempApiArr = unFiltered.getJSONArray("region");
            for(int i = 0; i < tempApiArr.length(); i++) {
                resList.add(toResultObj(tempApiArr.getJSONObject(i)));
            }
        } else if (count == 1) {
            resList.add(toResultObj(unFiltered.getJSONObject("region")));
        }
        return resList;
    }

    public static ResultObj toResultObj(JSONObject obj) {
        String gMap = getGMap(obj.get("latitude").toString(), obj.get("longitude").toString());
        return new ResultObj(
                obj.get("name").toString(),
                obj.get("latitude").toString(),
                obj.get("longitude").toString(),
                obj.get("url").toString(),
                gMap
        );
    }

    public static String getGMap(String lat, String lng) {
        StringBuilder URL = new StringBuilder();
        String latLng = lat + "," + lng;
        URL.append("https://maps.googleapis.com/maps/api/staticmap?key=").append(System.getenv("GMAP_KEY"));
        URL.append("&center=").append(latLng);
        URL.append("&zoom=12");
        URL.append("&size=250x250");
        URL.append("&markers=").append(latLng);
        return URL.toString();
    }

}
