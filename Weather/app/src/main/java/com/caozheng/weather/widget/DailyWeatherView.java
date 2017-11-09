package com.caozheng.weather.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.caozheng.weather.R;

/**
 * @author caozheng
 * Created time on 2017/11/9
 *
 * description:
 */

public class DailyWeatherView extends LinearLayout {

    private TextView mTvDay;
    private TextView mTvWeatherText;
    private TextView mTvWeatherRange;
    private ImageView mImvWeatherIcon;

    public DailyWeatherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_daily_weather_view, this, true);

        mTvDay = (TextView) findViewById(R.id.tv_day);
        mTvWeatherText = (TextView) findViewById(R.id.tv_weather_text);
        mTvWeatherRange = (TextView) findViewById(R.id.tv_range);
        mImvWeatherIcon = (ImageView) findViewById(R.id.imv_weather_icon);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DailyWeatherView);
        if(attributes != null){
            String day = attributes.getString(R.styleable.DailyWeatherView_day_text);
            String weatherText = attributes.getString(R.styleable.DailyWeatherView_weather_text);
            String weatherRange = attributes.getString(R.styleable.DailyWeatherView_weather_range);
            int weatherIcon = attributes.getResourceId(R.styleable.DailyWeatherView_weather_icon, -1);

            if (!TextUtils.isEmpty(day)) {
                mTvDay.setText(day);
            }

            if (!TextUtils.isEmpty(weatherText)) {
                mTvWeatherText.setText(weatherText);
            }

            if (!TextUtils.isEmpty(weatherRange)) {
                mTvWeatherRange.setText(weatherRange);
            }

            if(weatherIcon != -1){
                mImvWeatherIcon.setImageResource(weatherIcon);
            }
        }
    }

    public void setDayText(String dayText){
        mTvDay.setText(dayText);
    }

    public void setWeatherText(String weatherText){
        mTvWeatherText.setText(weatherText);
    }

    public void setWeatherRange(String weatherRange){
        mTvWeatherRange.setText(weatherRange);
    }

    public void setWeatherIcon(int iconId){
        mImvWeatherIcon.setImageResource(iconId);
    }
}
