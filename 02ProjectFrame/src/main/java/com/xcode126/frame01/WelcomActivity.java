package com.xcode126.frame01;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by xcode126 on 2017/1/9.
 * 欢迎界面
 */

public class WelcomActivity extends ComSuperActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}
