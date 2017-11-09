package com.caozheng.weather.module.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caozheng.weather.R;
import com.caozheng.weather.bean.WeatherBean;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.presenter.WeatherPresenter;
import com.caozheng.weather.module.view.WeatherView;
import com.caozheng.weather.util.WeatherCodeCheckTable;
import com.caozheng.weather.widget.DailyWeatherView;
import com.caozheng.xfastmvp.mvp.AppFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;

/**
 * @author caozheng
 *         Created time on 2017/11/7
 *         description:
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
    @BindView(R.id.daily_weather1)
    DailyWeatherView mDailyWeather1;
    @BindView(R.id.daily_weather2)
    DailyWeatherView mDailyWeather2;
    @BindView(R.id.daily_weather3)
    DailyWeatherView mDailyWeather3;
    @BindView(R.id.daily_weather4)
    DailyWeatherView mDailyWeather4;

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

    }

    @Override
    public void getWeatherDone(WeatherBean weatherBean) {
        mTvTemperature.setText(weatherBean.getResult().getTemp() + "°");
        mTvWeather.setText(weatherBean.getResult().getWeather());

        addDailyWeather(weatherBean);
    }

    private void addDailyWeather(WeatherBean weatherBean) {
        DailyWeatherView[] viewArray = new DailyWeatherView[]{mDailyWeather1, mDailyWeather2, mDailyWeather3, mDailyWeather4};
        List<WeatherBean.ResultBean.DailyBean> list = weatherBean.getResult().getDaily();
        for (int i = 0; i < 4; i++) {

            WeatherBean.ResultBean.DailyBean dailyBean = list.get(i);
            int icon = WeatherCodeCheckTable.getInstance().getWeatherIcon(dailyBean.getDay().getImg());
            //最底温度
            String tempLow = dailyBean.getNight().getTemplow();
            //最高温度
            String tempHigh = dailyBean.getDay().getTemphigh();

            DailyWeatherView mDailyWeather = viewArray[i];
            mDailyWeather.setDayText(dailyBean.getWeek());
            mDailyWeather.setWeatherText(dailyBean.getDay().getWeather());
            mDailyWeather.setWeatherRange(tempHigh + "°" + "/" + tempLow + "°");
            mDailyWeather.setWeatherIcon(icon);
        }
    }

    public void setCity(SaveCityModel model) {
        mPresenter.getWeather(getActivity(), model.getCityCode());

        mTvCity.setText(model.getCity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
