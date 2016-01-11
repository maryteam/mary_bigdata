package com.shellming.modules;

import com.google.gson.Gson;

/**
 * Created by ruluo1992 on 11/26/2015.
 */
public class Location {
    private String city;
    private String province;
    private String latitude;  // 纬度
    private String longitude; // 经度

    public Location(String city, String province, String latitude, String longitude) {
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
