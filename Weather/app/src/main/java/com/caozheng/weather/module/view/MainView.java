package com.caozheng.weather.module.view;

import com.caozheng.weather.bean.CityBean;
import com.caozheng.xfastmvp.mvp.BaseView;

/**
 * @author caozheng
 * @date 2017/11/6
 * <p>
 * describe:
 */

public interface MainView extends BaseView {

    void getCityDone(CityBean cityBean);

    void syncCityDone();
}
