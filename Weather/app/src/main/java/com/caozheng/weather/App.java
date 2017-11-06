package com.caozheng.weather;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author caozheng
 * Created time on 2017/11/5
 *
 * description:
 */

public class App extends Application {

    private static Context mContext;

    /** 全局的连接超时时间 */
    private static final int CONNECT_TIMEOUT_TIME = 60000;
    /** 全局的读取超时时间 */
    private static final int READ_TIMEOUT_TIME = 60000;
    /** 全局的写入超时时间 */
    private static final int WRITE_TIMEOUT_TIME = 60000;
    /** 重连次数 */
    private static final int RETRY_COUNT = 3;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        initOkGo();
    }

    public static Context getAppContext(){
        return mContext;
    }

    private void initOkGo(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(READ_TIMEOUT_TIME, TimeUnit.MILLISECONDS);
        builder.writeTimeout(WRITE_TIMEOUT_TIME, TimeUnit.MILLISECONDS);
        builder.connectTimeout(CONNECT_TIMEOUT_TIME, TimeUnit.MILLISECONDS);

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(RETRY_COUNT);
    }
}
