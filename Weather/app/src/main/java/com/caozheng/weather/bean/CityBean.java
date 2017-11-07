package com.caozheng.weather.bean;

import java.util.List;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * @author 10744
 * @date 2017/11/6
 * <p>
 * describe:
 */

public class CityBean{


    /**
     * status : 0
     * msg : ok
     * result : [{"cityid":"1","parentid":"0","citycode":"101010100","city":"北京"},{"cityid":"24","parentid":"0","citycode":"101020100","city":"上海"},{"cityid":"26","parentid":"0","citycode":"101030100","city":"天津"},{"cityid":"31","parentid":"0","citycode":"101040100","city":"重庆"},{"cityid":"32","parentid":"0","citycode":"101320101","city":"香港"}]
     */

    private int status;
    private String msg;
    private List<ResultBean> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * cityid : 1
         * parentid : 0
         * citycode : 101010100
         * city : 北京
         */

        private String cityid;
        private String parentid;
        private String citycode;
        private String city;

        public String getCityid() {
            return cityid;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}
