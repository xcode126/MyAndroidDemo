package com.xcode126.baidumapdemo;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by sky on 2017/3/28.
 * APP配置
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化地图
        SDKInitializer.initialize(getApplicationContext());
    }
}
