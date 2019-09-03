import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
public class JsoupExample {

    public static void main( String[] args ) throws IOException {
        //Initial example provided in tutorial.
        Document doc = Jsoup.connect("http://www.javatpoint.com").get();
        String title = doc.title();
        System.out.println("title is: " + title);


        // PROJECT SPECIFIC TEST FOR ZILLOW WEB SCRAPING
        String url = "https://www.zillow.com/capitol-hill-seattle-wa/home-values/";

        // Parse given html code into document
        Document document = Jsoup.parse(Jsoup.connect(url).get().toString());

        // Get html elements containing string "$"
        Elements elements = document.getElementsContainingOwnText("$");

        // grab element that contains Median Zestimate valuation.
        // NOTE: this should always be the first occurance/element.
        Element element = elements.first();
        System.out.println("this is the result: " + element);


//        Elements elements = document.getElementsByClass("region-info-item");
//        Element result = document.select("<div").first();
//        String body = document.body().toString();
//        select("<div class=\"region-info-item\"");

    }
}
