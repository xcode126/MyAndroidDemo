package com.xcode126.baidumapdemo.model;

import java.io.Serializable;

/**
 * Created by sky on 2017/3/28.
 * 地理位置Model
 */

public class LocationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double longitude;
    private Double latitude;

    public LocationModel(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationModel() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
