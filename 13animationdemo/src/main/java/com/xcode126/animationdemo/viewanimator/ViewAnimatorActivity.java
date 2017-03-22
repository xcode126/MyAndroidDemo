package com.xcode126.animationdemo.viewanimator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.xcode126.animationdemo.R;

/**
 * 用于做视图切换
 */
public class ViewAnimatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_animator);
        // 定义ViewFlipper
        final ViewFlipper vf = (ViewFlipper) findViewById(R.id.viewFlipper1);
        // 设置点击监听器
        vf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击读取下一个视图元素
                vf.showNext();
            }
        });
        // 设置切入动画
        vf.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
        // 设置切出动画
        vf.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_out_right));
    }
}
