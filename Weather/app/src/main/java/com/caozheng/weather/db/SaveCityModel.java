package com.caozheng.weather.db;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * @author caozheng
 * Created time on 2017/11/7
 *
 * description:
 */
@RealmClass
public class SaveCityModel implements RealmModel {

    private String cityId;
    private String cityCode;
    private String city;
    private int type;
    //0: 添加的城市, 1:当前城市

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
