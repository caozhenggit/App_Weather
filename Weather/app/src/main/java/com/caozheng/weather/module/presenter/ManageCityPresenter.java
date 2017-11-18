package com.caozheng.weather.module.presenter;

import com.caozheng.weather.App;
import com.caozheng.weather.bean.WeatherBean;
import com.caozheng.weather.db.AppRealm;
import com.caozheng.weather.db.CityModel;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.view.ManageCityView;
import com.caozheng.weather.util.Api;
import com.caozheng.weather.util.Field;
import com.caozheng.xfastmvp.cache.SharedPref;
import com.caozheng.xfastmvp.mvp.BasePresenter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author 10744
 * @date 2017/11/9
 * <p>
 * describe:
 */

public class ManageCityPresenter extends BasePresenter<ManageCityView> {

    public ManageCityPresenter(ManageCityView view){
        attachView(view);
    }

    public void selectAllSaveCity(){
        Realm mRealm = AppRealm.getInstance().getRealm();

        RealmResults<SaveCityModel> cityList = mRealm.where(SaveCityModel.class).findAll();

        mView.selectAllSaveCityDone(cityList);
    }

    public void selectAllCity(){
        Realm mRealm = AppRealm.getInstance().getRealm();

        RealmResults<CityModel> cityList = mRealm.where(CityModel.class).findAll();

        mView.selectAllCityDone(cityList);
    }

    public void addCity(CityModel model){
        Realm mRealm = AppRealm.getInstance().getRealm();

        mRealm.beginTransaction();
        SaveCityModel saveCityModel = mRealm.createObject(SaveCityModel.class);
        saveCityModel.setCity(model.getCity());
        saveCityModel.setCityCode(model.getCityCode());
        saveCityModel.setCityId(model.getCityId());
        saveCityModel.setType(0);
        mRealm.commitTransaction();

        getWeather(model.getCityId());

        mView.addCityDone();
    }

    public void deleteCity(String cityId){
        Realm mRealm = AppRealm.getInstance().getRealm();

        final RealmResults<SaveCityModel> cityList = mRealm.where(SaveCityModel.class).findAll();
        for (int i = 0; i < cityList.size(); i++) {
            final int index = i;
            if(cityList.get(i).getCityId().equals(cityId)){
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        cityList.deleteFromRealm(index);

                        mView.deleteCityDone();
                    }
                });
            }
        }
    }

    /**
     * 获取天气
     * @param cityId
     */
    private void getWeather(final String cityId){
        Map<String, String> querys = new HashMap<String, String>();
        querys.put(Field.FIELD_CITY_ID, cityId);

        OkGo.<String>get(Api.WEATHER_API_QUERY)
                .headers(Field.FIELD_AUTHORIZATION, Field.FIELD_APPCODE + " " + Api.APP_CODE)
                .params(querys, false)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        WeatherBean weatherBean = gson.fromJson(response.body(), WeatherBean.class);

                        if(weatherBean.getStatus() == 0){
                            SharedPref.getInstance(App.getAppContext()).putString(cityId, response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }
}
