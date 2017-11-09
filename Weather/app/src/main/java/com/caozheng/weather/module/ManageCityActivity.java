package com.caozheng.weather.module;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.caozheng.weather.R;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.presenter.ManageCityPresenter;
import com.caozheng.weather.module.view.ManageCityView;
import com.caozheng.xfastmvp.adapter.commonlistview.CommonAdapter;
import com.caozheng.xfastmvp.adapter.commonlistview.ViewHolder;
import com.caozheng.xfastmvp.mvp.AppActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.RealmResults;

/**
 * @author caozheng
 * @date 2017/11/9
 * <p>
 * describe:
 */

public class ManageCityActivity extends AppActivity<ManageCityPresenter> implements ManageCityView {

    @BindView(R.id.imv_back)
    ImageView imvBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.imv_add)
    ImageView imvAdd;
    @BindView(R.id.lv_city)
    ListView lvCity;

    private Context mContext;

    @Override
    public ManageCityPresenter createPresenter() {
        return new ManageCityPresenter(this);
    }

    @Override
    public void initParams(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_city;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        this.mContext = mContext;
        mPresenter.selectAllSaveCity();
    }

    @Override
    public void addCityDone() {

    }

    @Override
    public void deleteCityDone() {

    }

    @Override
    public void selectAllCityDone(RealmResults<SaveCityModel> cityList) {
        CommonAdapter<SaveCityModel> adapter = new CommonAdapter<SaveCityModel>(mContext, R.layout.item_listview_city, cityList) {
            @Override
            protected void convert(ViewHolder viewHolder, SaveCityModel item, int position) {
                viewHolder.setText(R.id.tv_city_name, item.getCity());
            }
        };

        lvCity.setAdapter(adapter);
    }

    @OnClick({R.id.imv_back, R.id.tv_title, R.id.imv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
            case R.id.tv_title:
                finish();
                break;

            case R.id.imv_add:
                break;

            default:
                break;
        }
    }
}
