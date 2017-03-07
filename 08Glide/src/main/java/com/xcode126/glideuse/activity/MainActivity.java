package com.xcode126.glideuse.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xcode126.glideuse.R;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 作用：Glide 的图片加载库
 * jcenter地址:需要依赖于V4
 * compile 'com.github.bumptech.glide:glide:3.5.2'
 * compile 'com.android.support:support-v4:22.0.0'
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bt_glide_base_click(View view) {
        Intent intent = new Intent(MainActivity.this, GlideBaseActivity.class);
        startActivity(intent);
    }

    public void bt_glide_recyclerview_click(View view) {
        Intent intent = new Intent(MainActivity.this, GlideRecyclerviewActivity.class);
        startActivity(intent);
    }

    public void bt_glide_tranfromations_click(View view) {
        Intent intent = new Intent(MainActivity.this, GlideTranformationsActivity.class);
        startActivity(intent);
    }
}
