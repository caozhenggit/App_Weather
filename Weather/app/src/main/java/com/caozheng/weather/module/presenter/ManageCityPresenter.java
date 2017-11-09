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
    }

    public void deleteCity(String cityCode){
        Realm mRealm = App.getRealm();

        final RealmResults<SaveCityModel> cityList = mRealm.where(SaveCityModel.class).findAll();
        for (int i = 0; i < cityList.size(); i++) {
            final int index = i;
            if(cityList.get(i).getCityCode().equals(cityCode)){
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
