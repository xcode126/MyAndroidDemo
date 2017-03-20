package com.example.myanimation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

/**
 * Created by sky on 2017/3/20.
 */

public class MainActivity2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
