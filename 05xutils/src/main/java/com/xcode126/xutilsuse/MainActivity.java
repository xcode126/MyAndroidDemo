package com.xcode126.xutilsuse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 作用：xutils3的使用
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_annotation, R.id.btn_net, R.id.btn_image, R.id.btn_image_list})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_annotation:
                startActivity(new Intent(MainActivity.this, FragmentXUtils3Activity.class));
                break;
            case R.id.btn_net:
                startActivity(new Intent(MainActivity.this, XUtils3NetActivity.class));
                break;
            case R.id.btn_image:
                break;
            case R.id.btn_image_list:
                break;

        }
    }
}
