package com.xcode126.asyncclient.util;

import android.view.View;
import android.view.ViewGroup;

public interface IConstant {

    int SIZE = 10;
    int GONE = View.GONE;
    int VISIBLE = View.VISIBLE;
    int INVISIBLE = View.INVISIBLE;
    @SuppressWarnings("deprecation")
    int FILL = ViewGroup.LayoutParams.FILL_PARENT;
    int MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
    int WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;

    String BROADCAST_LOGIN_SUCCESS = "cn.stlc.app.login.success";

    long SECOND = 1000;
    long MINUTE = 60 * SECOND;
    long HOUR = 60 * MINUTE;
    long DAY = 24 * HOUR;
    long MONTH = 30 * DAY;
    long YEAR = 365 * DAY;

    int REQUEST_LOGIN = 0x00008000;
    int REQUEST_ADD_CARD = 0x00000001;
    int REQUEST_PAY = 0x00000002;
    int REQUEST_MODIFY = 0x00000003;
    int REQUEST_REMODIFY = 0x00000004;
    int REQUEST_SET = 0x00000005;
    int REQUEST_PAY_VALIDATE_PASSWORD = 0x00000006;
    int REQUEST_SUCCESS = 0x00000007;
    int REQUEST_WAIT = 0x00000008;
    int REQUEST_REFRESH = 0x00000009;
}