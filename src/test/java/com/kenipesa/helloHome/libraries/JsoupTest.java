package com.kenipesa.helloHome.libraries;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class JsoupTest {

    @Test
    public void getNeighborhoodMedianPrice() {
        String result = null;
        try {
            result = Jsoup.getNeighborhoodMedianPrice("https://www.zillow.com/capitol-hill-seattle-wa/home-values/");
        } catch (IOException e) {
            System.out.println("error occurred");
        }
        // WOAH so you're testing that the median price of Capitol Hill never changes?
        // These tests will break so quickly!
        // A more reasonable test would be to save an example Zillow HTML file,
        // read that in, and assert that your Jsoup parsing can read from that correctly.
        assertTrue(result.equals("655200"));

    }
}