package com.caozheng.weather.module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caozheng.weather.R;
import com.caozheng.weather.module.presenter.WeatherPresenter;
import com.caozheng.weather.module.view.WeatherView;
import com.caozheng.xfastmvp.adapter.commonlistview.CommonAdapter;
import com.caozheng.xfastmvp.mvp.AppFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author caozheng
 * Created time on 2017/11/7
 * description:
 */
public class WeatherFragment extends AppFragment<WeatherPresenter> implements WeatherView {

    @BindView(R.id.tv_temperature)
    TextView mTvTemperature;
    @BindView(R.id.tv_weather)
    TextView mTvWeather;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.ll_future)
    LinearLayout mLlFuture;

    @Override
    protected WeatherPresenter createPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_weather;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        addDailyWeather();
    }

    private void addDailyWeather() {
        for(int i = 0; i < 4; i++){
            LayoutInflater mInflater = LayoutInflater.from(getActivity());
            View itemView = mInflater.inflate(R.layout.item_daily_weather_view, null);
            itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));

            mLlFuture.addView(itemView);
        }
    }
}
