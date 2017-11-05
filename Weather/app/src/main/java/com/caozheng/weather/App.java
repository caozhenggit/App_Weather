package com.caozheng.weather;

import android.app.Application;
import android.content.Context;

/**
 * @author caozheng
 * Created time on 2017/11/5
 *
 * description:
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }

    public static Context getAppContext(){
        return mContext;
    }
}
