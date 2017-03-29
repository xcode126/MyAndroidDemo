package com.xcode126.a16imageproces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xcode126.a16imageproces.image.DrawTestActivity;
import com.xcode126.a16imageproces.image.TuPianTestActivity;
import com.xcode126.a16imageproces.other.GraphicsActivity;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ:1397028339
 * 公众号：走近程序员
 * 作用：安卓图像的处理
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testTuPian(View view) {
        startActivity(new Intent(this, TuPianTestActivity.class));
    }

    public void testDraw(View view) {
        startActivity(new Intent(this, DrawTestActivity.class));
    }

    public void graphicsClick(View view) {
        startActivity(new Intent(this, GraphicsActivity.class));
    }
}
