package com.caozheng.weather.module.presenter;

import android.content.Context;

import com.caozheng.weather.bean.WeatherBean;
import com.caozheng.weather.module.view.WeatherView;
import com.caozheng.weather.util.Api;
import com.caozheng.weather.util.Field;
import com.caozheng.xfastmvp.cache.SharedPref;
import com.caozheng.xfastmvp.mvp.BasePresenter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caozheng
 * Created time on 2017/11/7
 * description:
 */

public class WeatherPresenter extends BasePresenter<WeatherView> {

    public WeatherPresenter(WeatherView view){
        attachView(view);
    }

    public void getWeather(final Context context, final String cityCode){
        String body = SharedPref.getInstance(context).getString(cityCode, "");
        if(!body.equals("")){
            Gson gson = new Gson();
            WeatherBean weatherBean = gson.fromJson(body, WeatherBean.class);

            mView.getWeatherDone(weatherBean);
        }

        Map<String, String> querys = new HashMap<String, String>();
        querys.put(Field.FIELD_CITY_CODE, cityCode);

        OkGo.<String>get(Api.WEATHER_API_QUERY)
                .headers(Field.FIELD_AUTHORIZATION, Field.FIELD_APPCODE + " " + Api.APP_CODE)
                .params(querys, false)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        WeatherBean weatherBean = gson.fromJson(response.body(), WeatherBean.class);

                        if(weatherBean.getStatus() == 0){
                            SharedPref.getInstance(context).putString(cityCode, response.body());
                        }

                        mView.getWeatherDone(weatherBean);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
