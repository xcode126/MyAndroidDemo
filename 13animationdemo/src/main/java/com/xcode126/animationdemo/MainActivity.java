package com.xcode126.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.xcode126.animationdemo.other.ViewAnimatorActivity;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 作用：Android动画（Animation）
 * 1.补间动画（TweenAnimations）：弥补空间的动画即通过旋转，位移，缩放，透明等效果让视图改变空间位置的变化
 * 2.逐帧动画（FrameAnimations）：在“连续的关键帧”中分解动画动作
 * 3.属性动画（PropertyAnimation）：通过动画的方式改变对象的属性，例如，你希望View有一个颜色的切换动画；你希望可以使用3D旋转动画
 * 4.ViewAnimator，用于视图切换的动画
 */
public class MainActivity extends CommonSuperActivity {
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.title_top);
        title.setText("Android动画（Animation）");
    }

    /**
     * 补间动画
     *
     * @param view
     */
    public void btn1Click(View view) {
        startActivity(new Intent(MainActivity.this, TweenActivity.class));
    }

    /**
     * 逐帧动画
     * <p>
     * param view
     */
    public void btn2Click(View view) {
        startActivity(new Intent(MainActivity.this, FrameActivity.class));

    }

    /**
     * 属性动画
     *
     * @param view
     */
    public void btn3Click(View view) {
        startActivity(new Intent(MainActivity.this, PropertyActivity.class));
    }

    /**
     * ViewAnimator，用于视图切换的动画
     *
     * @param view
     */
    public void btn4Click(View view) {
        startActivity(new Intent(MainActivity.this, ViewAnimatorActivity.class));
    }
}
