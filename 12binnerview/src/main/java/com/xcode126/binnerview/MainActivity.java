package com.xcode126.binnerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 公众号：走近程序员
 * 作用：滚动广告视图
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转到RoolViewPagerView
     *
     * @param view
     */
    public void rollPagerClick(View view) {
        Intent intent =new Intent(MainActivity.this, RoolViewPagerActivity.class);
        intent.putExtra("str","ABC");
        startActivity(intent);
    }

    public void ViewPagerClick(View view) {
        Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
