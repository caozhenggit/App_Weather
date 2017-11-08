package com.caozheng.weather.util;

import com.caozheng.weather.R;

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

    public int getWeatherIcon(int img){

        int weatherIcon = R.mipmap.weather_code_99;

        switch (img){
            case 0:
                weatherIcon = R.mipmap.weather_code_0;
                break;

            case 1:
                weatherIcon = R.mipmap.weather_code_1;
                break;

            case 2:
                weatherIcon = R.mipmap.weather_code_2;
                break;

            case 3:
                weatherIcon = R.mipmap.weather_code_3;
                break;

            case 4:
                weatherIcon = R.mipmap.weather_code_4;
                break;

            case 5:
                weatherIcon = R.mipmap.weather_code_5;
                break;

            case 6:
                weatherIcon = R.mipmap.weather_code_6;
                break;

            case 7:
                weatherIcon = R.mipmap.weather_code_7;
                break;

            case 8:
                weatherIcon = R.mipmap.weather_code_8;
                break;

            case 9:
                weatherIcon = R.mipmap.weather_code_9;
                break;

            case 10:
                weatherIcon = R.mipmap.weather_code_10;
                break;

            case 11:
                weatherIcon = R.mipmap.weather_code_11;
                break;

            case 12:
                weatherIcon = R.mipmap.weather_code_12;
                break;

            case 13:
                weatherIcon = R.mipmap.weather_code_13;
                break;

            case 14:
                weatherIcon = R.mipmap.weather_code_14;
                break;

            case 15:
                weatherIcon = R.mipmap.weather_code_15;
                break;

            case 16:
                weatherIcon = R.mipmap.weather_code_16;
                break;

            case 17:
                weatherIcon = R.mipmap.weather_code_17;
                break;

            case 18:
                weatherIcon = R.mipmap.weather_code_18;
                break;

            case 19:
                weatherIcon = R.mipmap.weather_code_19;
                break;

            case 20:
                weatherIcon = R.mipmap.weather_code_20;
                break;

            case 21:
                weatherIcon = R.mipmap.weather_code_21;
                break;

            case 22:
                weatherIcon = R.mipmap.weather_code_22;
                break;

            case 23:
                weatherIcon = R.mipmap.weather_code_23;
                break;

            case 24:
                weatherIcon = R.mipmap.weather_code_24;
                break;

            case 25:
                weatherIcon = R.mipmap.weather_code_25;
                break;

            case 26:
                weatherIcon = R.mipmap.weather_code_26;
                break;

            case 27:
                weatherIcon = R.mipmap.weather_code_27;
                break;

            case 28:
                weatherIcon = R.mipmap.weather_code_28;
                break;

            case 29:
                weatherIcon = R.mipmap.weather_code_29;
                break;

            case 30:
                weatherIcon = R.mipmap.weather_code_30;
                break;

            case 31:
                weatherIcon = R.mipmap.weather_code_31;
                break;

            case 32:
                weatherIcon = R.mipmap.weather_code_32;
                break;

            case 49:
                weatherIcon = R.mipmap.weather_code_49;
                break;

            case 53:
                weatherIcon = R.mipmap.weather_code_53;
                break;

            case 54:
                weatherIcon = R.mipmap.weather_code_54;
                break;

            case 55:
                weatherIcon = R.mipmap.weather_code_55;
                break;

            case 56:
                weatherIcon = R.mipmap.weather_code_56;
                break;

            case 57:
                weatherIcon = R.mipmap.weather_code_57;
                break;

            case 58:
                weatherIcon = R.mipmap.weather_code_58;
                break;

            case 99:
                weatherIcon = R.mipmap.weather_code_99;
                break;

            case 301:
                weatherIcon = R.mipmap.weather_code_301;
                break;

            case 302:
                weatherIcon = R.mipmap.weather_code_302;
                break;

            default:
                break;
        }

        return weatherIcon;
    }

}
