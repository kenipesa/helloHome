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
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        // Build URi and add query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(zillowURL)
                .queryParam("zws-id", System.getenv("API_KEY"))
                .queryParam("state", state)
                .queryParam("city", city)
                .queryParam("childtype", "neighborhood");

        //Code Review: Removed unused variable

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
        int ourBudget = -1;
        int urlPrice = -1;
        String marketType = "Cold";
        
        int count = unFiltered.getInt("count");
    
        if (count > 1) {
            JSONArray tempApiArr = unFiltered.getJSONArray("region");
            for(int i = 0; i < tempApiArr.length(); i++) {
                //Code Review: removed package name from method call
                String gMap = getGMap(tempApiArr.getJSONObject(i).get("latitude").toString(), tempApiArr.getJSONObject(i).get("longitude").toString());
                if(urlPrice <= ourBudget) {
                    ResultObj temp = new ResultObj(
                     urlPrice,
                     marketType,
                     tempApiArr.getJSONObject(i).get("name").toString(),
                     tempApiArr.getJSONObject(i).get("latitude").toString(),
                     tempApiArr.getJSONObject(i).get("longitude").toString(),
                     tempApiArr.getJSONObject(i).get("url").toString(),
                     gMap
                    );
                    resList.add(temp);
                }
            }
        } else if (count == 1) {
            //Code Review: removed package name from method call
            String gMap = getGMap(unFiltered.getJSONObject("region").get("latitude").toString(),
             unFiltered.getJSONObject("region").get("longitude").toString());
            if(urlPrice <= ourBudget) {
                ResultObj temp = new ResultObj(
                 urlPrice,
                 marketType,
                 unFiltered.getJSONObject("region").get("name").toString(),
                 unFiltered.getJSONObject("region").get("latitude").toString(),
                 unFiltered.getJSONObject("region").get("longitude").toString(),
                 unFiltered.getJSONObject("region").get("url").toString(),
                 gMap
                );
                resList.add(temp);
            }
        }
        return resList;
    }

    public static String getGMap(String lat, String lng) {
        StringBuilder URL = new StringBuilder();
        //Code Review: changed latLng to a string builder to reduce Space Complexity
        StringBuilder latLng = new StringBuilder();
        latLng.append(lat).append(",").append(lng);
        URL.append("https://maps.googleapis.com/maps/api/staticmap?key=").append(System.getenv("GMAP_KEY"));
        URL.append("&center=").append(latLng);
        URL.append("&zoom=12");
        URL.append("&size=250x250");
        URL.append("&markers=").append(latLng);
        return URL.toString();
    }

}
