package com.caozheng.weather.bean;

/**
 * @author caozheng
 * @date 2017/11/5
 * <p>
 * describe:
 */

public class WeatherCodeBean {

    private int code;
    private String chinese;
    private String english;
    private int icon;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
