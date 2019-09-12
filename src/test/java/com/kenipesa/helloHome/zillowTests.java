package com.kenipesa.helloHome;

import com.kenipesa.helloHome.libraries.ResultObj;
import com.kenipesa.helloHome.libraries.ZillowAPILib;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.List;

// nitpick: class names should always be capitalized, and the test structure
// should match the class structure (so this should be in libraries).

// Actually, reading through them, the second method isn't a test at all, as it doesn't assert anything!
// The first one has a single assertion that doesn't match the comment above it.
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
        Assert.hasText("{\"request", test.toString());
    }

    @Test public void zillowTestFilterList() {
        JSONObject test = ZillowAPILib.getNeighborhood("wa", "seattle");
        List<ResultObj> testList = ZillowAPILib.getFilteredResults(test);
        System.out.println(testList);
    }

}
