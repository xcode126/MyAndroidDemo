package com.xcode126.animationdemo.property;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.xcode126.animationdemo.R;

/**
 * ValueAnimator是Property Animation系统的核心类，它包含了配置Property Animation属性的大部分方法，
 * 要实现一个Property Animation,都需要直接或间接使用ValueAnimator类。
 */
public class ValueAnimatorActivity extends Activity {

    private ImageView mBlueBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        mBlueBall = (ImageView) findViewById(R.id.id_ball);
    }

    /**
     * 自由落体
     *
     * @param view
     */
    public void verticalRun(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, getScreenHeight()
                - mBlueBall.getHeight());
        animator.setTarget(mBlueBall);
        animator.setDuration(1000).start();
//      animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBlueBall.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
    }

    /**
     * 抛物线
     *
     * @param view
     */
    public void paowuxian(View view) {

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(3000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                Log.e("TAG", fraction * 3 + "");
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                mBlueBall.setX(point.x);
                mBlueBall.setY(point.y);

            }
        });
    }

    public void fadeOut(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(mBlueBall, "alpha", 0.5f);

        anim.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("TAG", "onAnimationStart");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // TODO Auto-generated method stub
                Log.e("TAG", "onAnimationRepeat");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("TAG", "onAnimationEnd");
                ViewGroup parent = (ViewGroup) mBlueBall.getParent();
                if (parent != null)
                    parent.removeView(mBlueBall);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // TODO Auto-generated method stub
                Log.e("TAG", "onAnimationCancel");
            }
        });
        anim.start();
    }


    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public int getScreenHeight() {
        WindowManager manager = (WindowManager) ValueAnimatorActivity.this
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        int Height = display.getHeight();
        return Height;
    }

    /**
     * 一般使用ValueAnimator实现动画分为以下七个步骤：
     * 1. 调用ValueAnimation类中的ofInt(int...values)、ofFloat(String propertyName,float...values)
     *    等静态方法实例化ValueAnimator对象，并设置目标属性的属性名、初始值或结束值等值;
     * 2.调用addUpdateListener(AnimatorUpdateListener mListener)方法为ValueAnimator对象设置属性变化的监听器;
     * 3.创建自定义的Interpolator，调用setInterpolator(TimeInterpolator value)为ValueAniamtor设置自定义的Interpolator;(可选，不设置默认为缺省值)
     * 4.创建自定义的TypeEvaluator,调用setEvaluator(TypeEvaluator value)为ValueAnimator设置自定义的TypeEvaluator;(可选，不设置默认为缺省值)
     * 5.在AnimatorUpdateListener 中的实现方法为目标对象的属性设置计算好的属性值。
     * 6.设置动画的持续时间、是否重复及重复次数等属性;
     * 7.为ValueAnimator设置目标对象并开始执行动画。
     */

}
