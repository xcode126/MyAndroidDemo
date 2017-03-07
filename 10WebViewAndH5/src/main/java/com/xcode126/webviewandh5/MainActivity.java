package com.xcode126.webviewandh5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 作用：JS和Android原生互相调用
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    //Android调用JS
    public void AndroidCallJsClick(View view) {
        startActivity(new Intent(MainActivity.this, AndroidCallJsActivity.class));
    }

    //Js调用原生
    public void jsCallAndroidClick(View view) {
        startActivity(new Intent(MainActivity.this, JSCallAndroidActivity.class));
    }

}
