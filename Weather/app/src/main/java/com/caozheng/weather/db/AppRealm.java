package com.caozheng.weather.db;

import com.caozheng.weather.App;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author caozheng
 * Created time on 2017/11/18
 * description:
 */

public class AppRealm {

    /** Realm 数据库名称*/
    private static final String DB_NAME = "weather.realm";
    /** Realm 数据库版本号*/
    private static final int VERSION_CODE = 0;

    private Realm mRealm;

    private static volatile AppRealm mInstance = null;

    private AppRealm(){
        initRealm();
    }

    public static AppRealm getInstance(){
        if(mInstance == null){
            synchronized (AppRealm.class) {
                if (mInstance == null) {
                    mInstance = new AppRealm();
                }
            }
        }

        return mInstance;
    }

    private void initRealm(){
        Realm.init(App.getAppContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(VERSION_CODE)
                .build();
        mRealm = Realm.getInstance(config);
    }

    public Realm getRealm(){
        return mRealm;
    }

    public void closeRealm(){
        if (mRealm != null) {
            if(!mRealm.isClosed()) {
                mRealm.close();
            }

            mRealm = null;
        }

        mInstance = null;
    }
}
