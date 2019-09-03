package com.kenipesa.helloHome.libraries;

public class ResultObj {
    int medianPrice;
    String marketType;
    String neighborhood;
    String lat;
    String lng;
    String url;

    public ResultObj (int medPrice, String marketType, String neighborhood, String lat, String lng, String url) {
        this.medianPrice = medPrice;
        this.marketType = marketType;
        this.neighborhood = neighborhood;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
    }

    public String toString() {
        StringBuilder toRet = new StringBuilder();
        toRet.append("For the neighborhood " + this.neighborhood + "\n");
        toRet.append("The median home price is " + this.medianPrice + "\n");
        toRet.append("The market is currently " + this.marketType + "\n");
        return toRet.toString();
    }
}
