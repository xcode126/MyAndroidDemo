package com.example.myanimation;

import android.app.Activity;
import android.os.Bundle;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 作用：Android动画（Animation）
 * 1.补间动画（TweenAnimations）：弥补空间的动画即通过旋转，位移，缩放，透明等效果让视图改变空间位置的变化
 * 2.逐帧动画（FrameAnimations）：在“连续的关键帧”中分解动画动作
 * 3.属性动画（PropertyAnimation）：通过动画的方式改变对象的属性，例如，你希望View有一个颜色的切换动画；你希望可以使用3D旋转动画
 */
public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
