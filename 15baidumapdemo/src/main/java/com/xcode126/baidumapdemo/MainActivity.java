package com.xcode126.baidumapdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * 公众号：程序教科书
 * 作用：百度地图添加指定位置标注
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转到第一个界面
     *
     * @param view
     */
    public void firstOnclick(View view) {
        startActivity(new Intent(MainActivity.this, FirstActivity.class));
    }

    /**
     * 跳转到第二个界面
     *
     * @param view
     */
    public void secondOnclick(View view) {
        startActivity(new Intent(MainActivity.this, SecondActivity.class));
    }


}
