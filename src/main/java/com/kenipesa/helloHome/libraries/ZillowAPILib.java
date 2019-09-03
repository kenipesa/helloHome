package com.kenipesa.helloHome.libraries;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/// TEMP ///
class Result{

}
////////////

public class ZillowAPILib {
    String zws_id = "X1-ZWz1hbob58fl6z_aj9j5";

    public static JSONObject getNeighborhood(String state, String city) {
        // Variables
        JSONObject xmlJSONObj = null;
        String childType = "neighborhood";

        // Set up for api request
        RestTemplate restTemplate = new RestTemplate();
        String zillowURL = "http://www.zillow.com/webservice/GetRegionChildren.htm";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        // Build URi and add query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(zillowURL)
                .queryParam("zws-id", "X1-ZWz1hbob58fl6z_aj9j5")
                .queryParam("state", state)
                .queryParam("city", city)
                .queryParam("childtype", "neighborhood");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        // Get the response
        HttpEntity<String> response = restTemplate.getForEntity(builder.build().encode().toUri(), String.class);
//        System.out.println(response.getBody());

        try{ // Convert XML Response to JSON
            xmlJSONObj = XML.toJSONObject(response.getBody().toString());
            String jsonPrettyPrintString = xmlJSONObj.toString(4);
//            System.out.println(jsonPrettyPrintString);
        }
        catch(Exception e) {
            System.err.println(e);
        }

        return xmlJSONObj;
    }

    public static List<Result> getFilteredResults(JSONObject webResponse) {
        List<Result> resList = new ArrayList<>();
        JSONObject unFiltered = webResponse.getJSONObject("RegionChildren:regionchildren")
                    .getJSONObject("response")
                    .getJSONObject("list");
        int neighborhoodCount = (int) unFiltered.get("count");
        //TODO: Calculate affordability
        String url;
        for(int i = 0; i < neighborhoodCount; i++) {
            url = unFiltered.getJSONArray("region").getJSONObject(i).get("url").toString();
            // TODO: Scrape site, compare median price to budget.
            // TODO: If less than budget, create a result object and add it to the list.
            System.out.println(url);
        }
        return resList;
    }

}
