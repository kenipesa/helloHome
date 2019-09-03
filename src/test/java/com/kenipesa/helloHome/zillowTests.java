package com.kenipesa.helloHome;

import com.kenipesa.helloHome.libraries.ZillowAPILib;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.util.Assert;

public class zillowTests {

    @Test public void zillowTestGetRegChildren() {
        JSONObject test = ZillowAPILib.getNeighborhood("wa", "seattle");
        try {
            System.out.println(test.get("RegionChildren:regionchildren"));
            JSONObject testResults = test.getJSONObject("RegionChildren:regionchildren")
                            .getJSONObject("response")
                            .getJSONObject("list")
                            .getJSONArray("region")
                            .getJSONObject(0);
            System.out.println(testResults);
        }
        catch(Exception e) {
            System.err.println(e);
        }
        // Test for ok status code
        Assert.hasText("<200", test.toString());
    }

    @Test public void zillowTestFilterList() {
        JSONObject test = ZillowAPILib.getNeighborhood("wa", "seattle");
        ZillowAPILib.getFilteredResults(test);
    }

}
