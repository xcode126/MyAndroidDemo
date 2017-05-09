package com.xcode126.gatewaydemo;


import android.app.Application;

import com.fbee.zllctl.Serial;

/**
 * Created by sky on 2017/5/2.
 */

public class BaseApplication extends Application {

    private Serial serial;

    @Override
    public void onCreate() {
        super.onCreate();
        serial.getAllIRdataName();
    }
}
