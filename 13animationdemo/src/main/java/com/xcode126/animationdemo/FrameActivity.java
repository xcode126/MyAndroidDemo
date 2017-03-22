package com.xcode126.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.frame.ChangePostionFrameActivity;

/**
 * 逐帧动画，由N张静态图片收集起来，通过控制依次显示这些图片，
 * 因为人眼"视觉残留"的原因，会让我们造成动画的"错觉"，跟放电影的原理一样！
 */
public class FrameActivity extends Activity implements View.OnClickListener {
    private ImageView iv_da_mm;
    private Button btn_da_start;
    private Button btn_da_stop;
    private Button btn_da_next;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_frame);

        tv = (TextView) findViewById(R.id.title_top);
        tv.setText("逐帧动画");
        iv_da_mm = (ImageView) findViewById(R.id.iv_da_mm);
        btn_da_start = (Button) findViewById(R.id.btn_da_start);
        btn_da_stop = (Button) findViewById(R.id.btn_da_stop);
        btn_da_next = (Button) findViewById(R.id.btn_da_next);

        btn_da_start.setOnClickListener(this);
        btn_da_stop.setOnClickListener(this);
        btn_da_next.setOnClickListener(this);
    }

    private AnimationDrawable animationDrawable;

    @Override
    public void onClick(View v) {
        if (v == btn_da_start) {
            if (animationDrawable == null) {
                //得到背景动画图片对象
                animationDrawable = (AnimationDrawable) iv_da_mm.getBackground();
                //启动
                animationDrawable.start();
            }

        } else if (v == btn_da_stop) {
            if (animationDrawable != null) {
                animationDrawable.stop();
                animationDrawable = null;
            }
        } else if (v == btn_da_next) {
            startActivity(new Intent(FrameActivity.this, ChangePostionFrameActivity.class));
        }
    }
}
