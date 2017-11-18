package com.caozheng.weather;

import android.app.Application;
import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;

/**
 * @author caozheng
 * Created time on 2017/11/5
 *
 * description:
 */

public class App extends Application {

    private static Context mContext;

    /** 是否打印Log */
    private static final boolean LOG_DEBUG = true;

    /** 天气数据缓存过期时间 */
    public static final int WEATHER_CACHE_TIMEOUT_TIME = 5 * 60 * 60 * 1000;

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

        if(LOG_DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
            //log打印级别，决定了log显示的详细程度
            loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            //log颜色级别，决定了log在控制台显示的颜色
            loggingInterceptor.setColorLevel(Level.INFO);
            builder.addInterceptor(loggingInterceptor);
        }

        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(RETRY_COUNT);
    }
}
