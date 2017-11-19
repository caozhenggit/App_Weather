package com.caozheng.weather.module;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.caozheng.weather.R;
import com.caozheng.weather.db.CityModel;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.presenter.ManageCityPresenter;
import com.caozheng.weather.module.view.ManageCityView;
import com.caozheng.weather.widget.SwipeListLayout;
import com.caozheng.xfastmvp.adapter.commonlistview.CommonAdapter;
import com.caozheng.xfastmvp.adapter.commonlistview.ViewHolder;
import com.caozheng.xfastmvp.mvp.AppActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.lv_search)
    ListView lvSearch;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;

    private Context mContext;
    private RealmResults<CityModel> mCityList;
    private RealmResults<SaveCityModel> mSaveCityList;

    private boolean isChangeCity = false;

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
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchItem(newText);
                return false;
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        this.mContext = mContext;
        mPresenter.selectAllSaveCity();
        mPresenter.selectAllCity();
    }

    @Override
    public void addCityDone() {
        isChangeCity = true;
        mPresenter.selectAllSaveCity();
    }

    @Override
    public void deleteCityDone() {
        isChangeCity = true;
        mPresenter.selectAllSaveCity();
    }

    @Override
    public void selectAllSaveCityDone(RealmResults<SaveCityModel> saveCityList) {
        mSaveCityList = saveCityList;
        CommonAdapter<SaveCityModel> adapter = new CommonAdapter<SaveCityModel>(mContext, R.layout.item_listview_city, saveCityList) {
            @Override
            protected void convert(ViewHolder viewHolder, SaveCityModel item, final int position) {
                viewHolder.setText(R.id.tv_city_name, item.getCity());
                viewHolder.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.deleteCity(mSaveCityList.get(position).getCityId());
                    }
                });
            }
        };

        lvCity.setAdapter(adapter);
    }

    @Override
    public void selectAllCityDone(RealmResults<CityModel> cityList) {
        mCityList = cityList;

        searchItem("");
    }

    @OnClick({R.id.imv_back, R.id.tv_title, R.id.imv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_back:
            case R.id.tv_title:
                if(isChangeCity){
                    setResult(1);
                }
                finish();
                break;

            case R.id.imv_add:
                llSearch.setVisibility(View.VISIBLE);
                searchView.setQuery("", false);
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            //当搜索框可见时
            if(llSearch.getVisibility() == View.VISIBLE){
                llSearch.setVisibility(View.GONE);

                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }

    private void searchItem(String text){
        final List<String> mSearchList = new ArrayList<String>();

        if(text.equals("")){
            for (CityModel cityModel : mCityList) {
                mSearchList.add(cityModel.getCity());
            }
        }else {
            for (CityModel cityModel : mCityList) {
                int index = cityModel.getCity().indexOf(text);
                if(index != -1){
                    mSearchList.add(cityModel.getCity());
                }
            }
        }

        CommonAdapter<String> adapter = new CommonAdapter<String>(mContext, R.layout.item_listview_search, mSearchList) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.tv_text, item);
            }
        };

        lvSearch.setAdapter(adapter);

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                llSearch.setVisibility(View.GONE);

                String city = mSearchList.get(i);

                boolean isExist = false;
                for (SaveCityModel saveCityModel : mSaveCityList) {
                    if(saveCityModel.getCity().equals(city)){
                        isExist = true;
                    }
                }

                if(!isExist){
                    for (CityModel cityModel : mCityList) {
                        if(cityModel.getCity().equals(city)){
                            mPresenter.addCity(cityModel);
                        }
                    }
                }
            }
        });
    }
}
