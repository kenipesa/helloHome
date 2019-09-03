package com.kenipesa.helloHome;

import com.kenipesa.helloHome.libraries.ZillowAPILib;
import org.json.JSONObject;
import org.junit.Test;

public class zillowTests {

    @Test public void zillowTestGetRegChildren() {
        JSONObject test = ZillowAPILib.getNeighborhood("wa", "seattle");
        try {
            System.out.println(test);
        }
        catch(Exception e) {
            System.err.println(e);
        }
//        Assert.hasText("", test);
    }

}
