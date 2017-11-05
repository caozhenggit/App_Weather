package com.caozheng.weather.util;

/**
 * @author caozheng
 * @date 2017/11/5
 *
 * describe:
 */
public class WeatherCodeCheckTable {

    private volatile static WeatherCodeCheckTable singleton;

    private WeatherCodeCheckTable() {
    }

    public static WeatherCodeCheckTable getInstance() {
        if (singleton == null) {
            synchronized (WeatherCodeCheckTable.class) {
                if (singleton == null) {
                    singleton = new WeatherCodeCheckTable();
                }
            }
        }
        return singleton;
    }

}
