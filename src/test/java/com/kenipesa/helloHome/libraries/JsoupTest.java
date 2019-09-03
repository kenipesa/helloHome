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
        assertTrue(result.equals("655200"));

    }
}