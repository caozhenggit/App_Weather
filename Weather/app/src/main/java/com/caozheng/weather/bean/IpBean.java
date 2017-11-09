package com.caozheng.weather.bean;

/**
 * @author caozheng
 * @date 2017/11/7
 * <p>
 * describe:
 */

public class IpBean {


    /**
     * cip : 112.65.12.248
     * cid : 310000
     * cname : 上海市
     */

    private String cip;
    private String cid;
    private String cname;

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
