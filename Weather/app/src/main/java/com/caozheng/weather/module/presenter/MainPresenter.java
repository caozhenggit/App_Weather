package com.caozheng.weather.module.presenter;

import android.util.Log;

import com.caozheng.weather.App;
import com.caozheng.weather.bean.CityBean;
import com.caozheng.weather.db.CityModel;
import com.caozheng.weather.module.view.MainView;
import com.caozheng.weather.util.Api;
import com.caozheng.weather.util.Field;
import com.caozheng.xfastmvp.mvp.BasePresenter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * @author caozheng
 * @date 2017/11/6
 * <p>
 * describe:
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view){
        attachView(view);
    }

    /** 同步城市列表 */
    public void syncCity(){
        OkGo.<String>get(Api.WEATHER_API_CITY)
                .headers(Field.FIELD_AUTHORIZATION, Field.FIELD_APPCODE + " " + Api.APP_CODE)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        CityBean cityBean = gson.fromJson(response.body(), CityBean.class);

                        if(cityBean.getStatus() == 0){
                            insertCityToDb(cityBean.getResult());

                            mView.syncCityDone();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    public void getWeather(){
        Map<String, String> querys = new HashMap<String, String>();
        querys.put(Field.FIELD_CITY, "");

        OkGo.<String>get(Api.WEATHER_API_QUERY)
                .headers(Field.FIELD_AUTHORIZATION, Field.FIELD_APPCODE + " " + Api.APP_CODE)
                .params(querys, false)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i("getWeather", response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void insertCityToDb(List<CityBean.ResultBean> list){
        Realm mRealm = App.getRealm();
        RealmResults<CityModel> cityList = mRealm.where(CityModel.class).findAll();

        //有数据，无需同步
        if(cityList.size() == 0){
            mRealm.beginTransaction();

            for (CityBean.ResultBean bean : list) {
                CityModel cityModel = mRealm.createObject(CityModel.class);

                cityModel.setCityId(bean.getCityid());
                cityModel.setCity(bean.getCity());
                cityModel.setCityCode(bean.getCitycode());
                cityModel.setParentId(bean.getParentid());
            }

            mRealm.commitTransaction();
        }
    }

}
