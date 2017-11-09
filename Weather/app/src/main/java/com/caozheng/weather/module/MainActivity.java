package com.caozheng.weather.module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.caozheng.weather.App;
import com.caozheng.weather.R;
import com.caozheng.weather.adapter.MyFragmentPagerAdapter;
import com.caozheng.weather.bean.CityBean;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.fragment.WeatherFragment;
import com.caozheng.weather.module.presenter.MainPresenter;
import com.caozheng.weather.module.view.MainView;
import com.caozheng.xfastmvp.mvp.AppActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    private List<WeatherFragment> pagerList = new ArrayList<WeatherFragment>();
    private RealmResults<SaveCityModel> mSaveCityList;

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

        mPresenter.getLocalCity();
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void getLocalCityDone() {
        initPager();
    }

    @Override
    public void syncCityDone() {
    }

    @OnClick({R.id.imv_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_setting:
                startActivity(new Intent(mContext, ManageCityActivity.class));
                break;

            default:
                break;
        }
    }

    private void initPager(){
        Realm mRealm = App.getRealm();
        mSaveCityList = mRealm.where(SaveCityModel.class)
                .findAllSorted("type", Sort.DESCENDING);

        pagerList.clear();

        for(int i = 0; i < mSaveCityList.size(); i++){
            pagerList.add(new WeatherFragment());
        }

        addPoint(mSaveCityList.size());

        FragmentManager mFragmentManager = getSupportFragmentManager();
        mViewpager.setAdapter(new MyFragmentPagerAdapter(mFragmentManager, pagerList));

        viewPagerPageChangeListener();

        setPoint(0);
        pagerList.get(0).setCity(mSaveCityList.get(0));
    }

    private void viewPagerPageChangeListener(){
        mViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPoint(position);

                pagerList.get(position).setCity(mSaveCityList.get(position));
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Realm mRealm = App.getRealm();
        if (mRealm != null) {
            if(!mRealm.isClosed()) {
                mRealm.close();
            }

            mRealm = null;
        }
    }
}
