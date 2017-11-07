package com.caozheng.weather.db;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * @author 10744
 * @date 2017/11/6
 *
 * describe:
 */

@RealmClass
public class CityModel implements RealmModel{

    private String cityId;
    private String parentId;
    private String cityCode;
    private String city;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
