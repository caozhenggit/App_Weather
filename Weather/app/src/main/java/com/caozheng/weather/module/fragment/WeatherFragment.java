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
import com.caozheng.weather.bean.WeatherBean;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.presenter.WeatherPresenter;
import com.caozheng.weather.module.view.WeatherView;
import com.caozheng.weather.util.WeatherCodeCheckTable;
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

    }

    @Override
    public void getWeatherDone(WeatherBean weatherBean) {
        mTvTemperature.setText(weatherBean.getResult().getTemp() + "°");
        mTvWeather.setText(weatherBean.getResult().getWeather());

        addDailyWeather(weatherBean);
    }

    private void addDailyWeather(WeatherBean weatherBean) {
        List<WeatherBean.ResultBean.DailyBean> list = weatherBean.getResult().getDaily();
        for(int i = 0; i < 4; i++){
            LayoutInflater mInflater = LayoutInflater.from(getActivity());
            View itemView = mInflater.inflate(R.layout.item_daily_weather_view, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1);
            itemView.setLayoutParams(layoutParams);

            WeatherBean.ResultBean.DailyBean dailyBean = list.get(i);
            ((TextView)itemView.findViewById(R.id.tv_day)).setText(dailyBean.getWeek());
            ((TextView)itemView.findViewById(R.id.tv_weather_text)).setText(dailyBean.getDay().getWeather());

            int icon = WeatherCodeCheckTable.getInstance().getWeatherIcon(dailyBean.getDay().getImg());
            ((ImageView)itemView.findViewById(R.id.imv_weather_icon)).setImageResource(icon);

            //最底温度
            String tempLow = dailyBean.getNight().getTemplow();
            //最高温度
            String tempHigh = dailyBean.getDay().getTemphigh();
            ((TextView)itemView.findViewById(R.id.tv_range)).setText(tempLow + "°" + "~" + tempHigh + "°");

            mLlFuture.addView(itemView);
        }
    }

    public void setCity(SaveCityModel model){
        mPresenter.getWeather(model.getCityCode());

        mTvCity.setText(model.getCity());
    }
}
