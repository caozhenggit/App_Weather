package com.caozheng.weather.module.presenter;

import com.caozheng.weather.App;
import com.caozheng.weather.db.CityModel;
import com.caozheng.weather.db.SaveCityModel;
import com.caozheng.weather.module.view.ManageCityView;
import com.caozheng.xfastmvp.mvp.BasePresenter;

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
        Realm mRealm = App.getRealm();

        RealmResults<SaveCityModel> cityList = mRealm.where(SaveCityModel.class).findAll();

        mView.selectAllSaveCityDone(cityList);
    }

    public void selectAllCity(){
        Realm mRealm = App.getRealm();

        RealmResults<CityModel> cityList = mRealm.where(CityModel.class).findAll();

        mView.selectAllCityDone(cityList);
    }

    public void addCity(CityModel model){
        Realm mRealm = App.getRealm();

        mRealm.beginTransaction();
        SaveCityModel saveCityModel = mRealm.createObject(SaveCityModel.class);
        saveCityModel.setCity(model.getCity());
        saveCityModel.setCityCode(model.getCityCode());
        saveCityModel.setCityId(model.getCityId());
        saveCityModel.setType(0);
        mRealm.commitTransaction();

        mView.addCityDone();
    }

    public void deleteCity(String cityId){
        Realm mRealm = App.getRealm();

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
}
