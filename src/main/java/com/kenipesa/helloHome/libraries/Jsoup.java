package com.kenipesa.helloHome.libraries;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Jsoup {

    // Since this code isn't used in your application, it should either be removed
    // or clearly stated that this is dead code.
    public static String getNeighborhoodMedianPrice(String url) throws IOException {
        String medianPrice = null;

//      Parse given html code into document
        Document document = org.jsoup.Jsoup.parse(org.jsoup.Jsoup.connect(url).get().toString());

//      Get html elements containing string "$"
        Elements elements = document.getElementsContainingOwnText("$");

//      grab element that contains Median Zestimate valuation.
//      NOTE: this should always be the first occurance/element.
        String htmlString = elements.first().toString();
        medianPrice = htmlString.substring(5, htmlString.length() - 5).replace(",", "");

        return medianPrice;
    }
}
