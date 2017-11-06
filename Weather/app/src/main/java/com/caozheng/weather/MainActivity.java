package com.caozheng.weather;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.caozheng.weather.bean.CityBean;
import com.caozheng.weather.module.presenter.MainPresenter;
import com.caozheng.weather.module.view.MainView;
import com.caozheng.xfastmvp.mvp.AppActivity;

/**
 * @author caozheng
 * Created time on 2017/11/5
 *
 * description:
 */
public class MainActivity extends AppActivity<MainPresenter> implements MainView {

    private Context mContext;

    @Override
    public void initParams(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        mContext = this;
    }

    @Override
    public void doBusiness(Context mContext) {
        mPresenter.getCity("上海");
        showLoading();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getCityDone(CityBean cityBean) {
        hideLoading();

        Log.i("CityBean", cityBean.getCityId());
        Log.i("CityBean", cityBean.getCityName());
        Log.i("CityBean", cityBean.getCityEnglishName());
        Log.i("CityBean", cityBean.getCountry());
        Log.i("CityBean", cityBean.getCountryCode());

        mPresenter.getWeather(cityBean);
    }
}
