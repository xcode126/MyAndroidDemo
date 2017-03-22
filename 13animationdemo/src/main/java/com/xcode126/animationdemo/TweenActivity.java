package com.xcode126.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xcode126.animationdemo.tween.AnimationListenerActivity;
import com.xcode126.animationdemo.tween.ComplexCodeActivity;
import com.xcode126.animationdemo.tween.ComplexXmlActivity;
import com.xcode126.animationdemo.tween.LayoutAnimationsControllerActivity;
import com.xcode126.animationdemo.tween.SingleCodeActivity;
import com.xcode126.animationdemo.tween.SingleXmlActivity;

/**
 * 补间动画
 * Animation的四个子类：
 * AlphaAnimation、TranslateAnimation、ScaleAnimation、RotateAnimation
 * 四个子类可以单独使用（单一动画），也可以混合使用（复合动画）
 */
public class TweenActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween);
        TextView title = (TextView) findViewById(R.id.title_top);
        title.setText("补间动画（TweenAnimation）");
    }

    /**
     * 单一动画（代码实现）
     *
     * @param view
     */
    public void singleCodeClick(View view) {
        startActivity(new Intent(TweenActivity.this, SingleCodeActivity.class));
    }

    /**
     * 单一动画（xml实现）
     *
     * @param view
     */
    public void singleXmlClick(View view) {
        startActivity(new Intent(TweenActivity.this, SingleXmlActivity.class));
    }

    /**
     * 复合动画-code
     *
     * @param view
     */
    public void complexCodeClick(View view) {
        startActivity(new Intent(TweenActivity.this, ComplexCodeActivity.class));
    }

    /**
     * 复合动画-xml
     *
     * @param view
     */
    public void complexXmlClick(View view) {
        startActivity(new Intent(TweenActivity.this, ComplexXmlActivity.class));
    }

    /**
     * 动画监听
     *
     * @param view
     */
    public void AnimationListenerClick(View view) {
        startActivity(new Intent(TweenActivity.this, AnimationListenerActivity.class));
    }

    /**
     * 可以用于实现使多个控件按顺序一个一个的显示。
     *
     * @param view
     */
    public void LayoutAnimationsControllerClick(View view) {
        startActivity(new Intent(TweenActivity.this, LayoutAnimationsControllerActivity.class));
    }
}
