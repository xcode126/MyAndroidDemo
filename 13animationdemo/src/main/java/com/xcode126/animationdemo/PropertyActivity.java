package com.xcode126.animationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.xcode126.animationdemo.property.AnimatorSetActivity;
import com.xcode126.animationdemo.property.ObjectAnimatorActivity;
import com.xcode126.animationdemo.property.ValueAnimatorActivity;

/**
 * 属性动画，即，动画的执行类来设置动画操作的对象的属性、持续时间，开始和结束的属性值，时间差值等，然后系统会根据设置的参数动态的变化对象的属性
 * 1常用属性：
 * -Property Animation故名思议就是通过动画的方式改变对象的属性了，我们首先需要了解几个属性：
 * -Duration动画的持续时间，默认300ms。
 * -Time interpolation：时间差值，乍一看不知道是什么，类似LinearInterpolator、AccelerateDecelerateInterpolator，
 * -用来定义动画的变化率。
 * -Repeat count and behavior：重复次数、以及重复模式；可以定义重复多少次；重复时从头开始，还是反向。
 * -Animator sets: 动画集合，你可以定义一组动画，一起执行或者顺序执行。
 * -Frame refresh delay：帧刷新延迟，对于你的动画，多久刷新一次帧；默认为10ms，但最终依赖系统的当前状态；基本不用管。
 * 2.相关的类
 * -ObjectAnimator  动画的执行类，后面详细介绍
 * -ValueAnimator 动画的执行类，后面详细介绍
 * -AnimatorSet 用于控制一组动画的执行：线性，一起，每个动画的先后执行等。
 * -AnimatorInflater 用户加载属性动画的xml文件
 * -TypeEvaluator  类型估值，主要用于设置动画操作属性的值。
 * -TimeInterpolator 时间插值，上面已经介绍
 */
public class PropertyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_property);
        TextView tv = (TextView) findViewById(R.id.title_top);
        tv.setText("属性动画");
    }

    /**
     * ObjectAnimator
     *
     * @param view
     */
    public void ObjectAnimatorClick(View view) {
        startActivity(new Intent(PropertyActivity.this, ObjectAnimatorActivity.class));
    }

    /**
     * ValueAnimator
     * @param view
     */
    public void ValueAnimatorClick(View view){
        startActivity(new Intent(PropertyActivity.this, ValueAnimatorActivity.class));
    }

    /**
     * AnimatorSet
     * @param view
     */
    public void AnimatorSetClick(View view){
        startActivity(new Intent(PropertyActivity.this, AnimatorSetActivity.class));
    }
}
