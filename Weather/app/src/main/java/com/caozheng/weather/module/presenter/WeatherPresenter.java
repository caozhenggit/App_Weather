package com.caozheng.weather.module.presenter;

import com.caozheng.weather.module.view.WeatherView;
import com.caozheng.xfastmvp.mvp.BasePresenter;

/**
 * @author caozheng
 * Created time on 2017/11/7
 * description:
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    public WeatherPresenter(WeatherView view){
        attachView(view);
    }
}
