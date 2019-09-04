package com.kenipesa.helloHome.libraries;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class ZillowAPILib {


    public static JSONObject getNeighborhood(String state, String city) {
        // Variables
        JSONObject xmlJSONObj = null;
        String childType = "neighborhood";

        // Set up for api request
        RestTemplate restTemplate = new RestTemplate();
        String zillowURL = "http://www.zillow.com/webservice/GetRegionChildren.htm";
        System.out.println(System.getenv("api.key"));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        // Build URi and add query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(zillowURL)
//                .queryParam("zws-id", System.getenv("api.key"))
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
//            String jsonPrettyPrintString = xmlJSONObj.toString(4);
//            System.out.println(jsonPrettyPrintString);
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
        int neighborhoodCount = (int) unFiltered.get("count");
        //TODO: Stretch: Calculate affordability
        int ourBudget = -1;
        int urlPrice = -1;
        String marketType = "Cold";
        String url;
        for(int i = 0; i < neighborhoodCount; i++) {
//            url = unFiltered.getJSONArray("region").getJSONObject(i).get("url").toString();
            // TODO: Stretch: Scrape site, compare median price to budget.
            //urlPrice = scrapeURL(url);
            if(urlPrice <= ourBudget) {
//                System.out.println("In range.");
                ResultObj temp = new ResultObj(
                        urlPrice,
                        marketType,
                        unFiltered.getJSONArray("region").getJSONObject(i).get("name").toString(),
                        unFiltered.getJSONArray("region").getJSONObject(i).get("latitude").toString(),
                        unFiltered.getJSONArray("region").getJSONObject(i).get("longitude").toString(),
                        unFiltered.getJSONArray("region").getJSONObject(i).get("url").toString()
                );
                resList.add(temp);
            }
//            System.out.println(url);
        }
        return resList;
    }

}
