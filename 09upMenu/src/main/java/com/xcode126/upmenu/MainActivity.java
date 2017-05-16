package com.xcode126.upmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：程序教科书
 * 作用：上弹视图风格介绍
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void Btn1Click(View view) {
        startActivity(new Intent(MainActivity.this, ShareMenuActivity.class));
    }
    public void Btn2Click(View view) {
        startActivity(new Intent(MainActivity.this, TakePhotoActivity.class));
    }
}
