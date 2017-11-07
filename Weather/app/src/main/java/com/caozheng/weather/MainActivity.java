package com.caozheng.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.caozheng.weather.adapter.MyFragmentPagerAdapter;
import com.caozheng.weather.bean.CityBean;
import com.caozheng.weather.module.fragment.WeatherFragment;
import com.caozheng.weather.module.presenter.MainPresenter;
import com.caozheng.weather.module.view.MainView;
import com.caozheng.xfastmvp.mvp.AppActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author caozheng
 * Created time on 2017/11/5
 * <p>
 * description:
 */
public class MainActivity extends AppActivity<MainPresenter> implements MainView {

    @BindView(R.id.imv_setting) ImageView mImvSetting;
    @BindView(R.id.viewpager) ViewPager mViewpager;
    @BindView(R.id.ll_point) LinearLayout mLlPoint;

    private Context mContext;
    private ImageView[] points;

    private List<Fragment> pagerList = new ArrayList<Fragment>();

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
        mPresenter.syncCity();
        showLoading();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getCityDone(CityBean cityBean) {
        hideLoading();

        mPresenter.getWeather();
    }

    @Override
    public void syncCityDone() {
        hideLoading();
    }

    @OnClick({R.id.imv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_setting:

                break;

            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            initPager();
        }
    }

    private void initPager(){
        pagerList.clear();
        pagerList.add(new WeatherFragment());
        pagerList.add(new WeatherFragment());
        pagerList.add(new WeatherFragment());
        pagerList.add(new WeatherFragment());
        pagerList.add(new WeatherFragment());

        addPoint(pagerList.size());

        FragmentManager mFragmentManager = getSupportFragmentManager();
        mViewpager.setAdapter(new MyFragmentPagerAdapter(mFragmentManager, pagerList));

        viewPagerPageChangeListener();
    }

    private void viewPagerPageChangeListener(){
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addPoint(int count) {
        points = new ImageView[count];
        mLlPoint.removeAllViews();
        for (int i = 0; i < count; i++) {
            points[i] = new ImageView(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(2, 0, 2, 0);
            points[i].setLayoutParams(lp);

            mLlPoint.addView(points[i]);
        }
    }

    private void setPoint(int position) {
        for (int i = 0; i < points.length; i++) {
            if (i == position) {
                points[i].setImageResource(R.mipmap.point_selected);
            } else {
                points[i].setImageResource(R.mipmap.point_normal);
            }
        }
    }
}
