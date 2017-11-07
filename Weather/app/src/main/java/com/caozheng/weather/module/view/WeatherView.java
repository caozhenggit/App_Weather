package com.caozheng.weather.module.view;

import com.caozheng.weather.bean.WeatherBean;
import com.caozheng.xfastmvp.mvp.BaseView;

/**
 * @author caozheng
 * Created time on 2017/11/7
 * description:
 */

public interface WeatherView extends BaseView {

    void getWeatherDone(WeatherBean weatherBean);

}
