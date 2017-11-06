package com.caozheng.weather.module.presenter;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.util.Log;

import com.caozheng.weather.App;
import com.caozheng.weather.bean.CityBean;
import com.caozheng.weather.module.view.MainView;
import com.caozheng.weather.util.Api;
import com.caozheng.xfastmvp.mvp.BasePresenter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;

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

    public void getCity(String cityName){
        new ExcelDataLoader(cityName).execute("thinkpage_cities.xls");
    }

    public void getWeather(CityBean mCityBean){

        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", mCityBean.getCityName());
//        querys.put("citycode", "citycode");
//        querys.put("cityid", "cityid");
//        querys.put("ip", "ip");
//        querys.put("location", "location");

        OkGo.<String>get(Api.WEATHER_API)
                .headers("Authorization", "APPCODE " + Api.APP_CODE)
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

    private ArrayList<CityBean> getXlsData(String xlsName, int index) {
        ArrayList<CityBean> countryList = new ArrayList<CityBean>();
        AssetManager assetManager = App.getAppContext().getAssets();

        try {
            Workbook workbook = Workbook.getWorkbook(assetManager.open(xlsName));
            Sheet sheet = workbook.getSheet(index);

            int sheetRows = sheet.getRows();

            for (int i = 0; i < sheetRows; i++) {
                CityBean cityBean = new CityBean();
                cityBean.setCityId(sheet.getCell(0, i).getContents());
                cityBean.setCityName(sheet.getCell(1, i).getContents());
                cityBean.setCityEnglishName(sheet.getCell(2, i).getContents());
                cityBean.setCountry(sheet.getCell(3, i).getContents());
                cityBean.setCountryCode(sheet.getCell(4, i).getContents());

                countryList.add(cityBean);
            }

            workbook.close();

        } catch (Exception e) {
            Log.e("getXlsData", "read error=" + e, e);
        }

        return countryList;
    }

    private class ExcelDataLoader extends AsyncTask<String, Void, ArrayList<CityBean>> {

        private String cityName;

        private ExcelDataLoader(String cityName){
            this.cityName = cityName;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<CityBean> doInBackground(String... params) {
            return getXlsData(params[0], 0);
        }

        @Override
        protected void onPostExecute(ArrayList<CityBean> cityBeen) {
            if(cityBeen != null && cityBeen.size()>0){
                //存在数据
                for (int i = 0; i < cityBeen.size(); i++) {
                    CityBean mCityBean = cityBeen.get(i);
                    if(mCityBean.getCityName().equals(cityName)){
                        mView.getCityDone(mCityBean);

                        break;
                    }
                }
            }else {
                //加载失败
                Log.e("ExcelDataLoader", "加载失败");
            }
        }
    }
}
