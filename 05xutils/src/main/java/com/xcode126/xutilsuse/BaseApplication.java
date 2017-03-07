package com.xcode126.xutilsuse;

import android.app.Application;

import org.xutils.x;

/**
 * Created by sky on 2017/1/10.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//xutils初始化
    }
}
