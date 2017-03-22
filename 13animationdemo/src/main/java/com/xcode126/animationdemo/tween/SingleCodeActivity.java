package com.xcode126.animationdemo.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.R;

/**
 * 单一动画（使用代码实现）
 * * 通用方法：
 * １、setDuration(long durationMills)设置动画持续时间（单位：毫秒）
 * ２、setFillAfter(Boolean fillAfter)如果fillAfter的值为true,则动画执行后，控件将停留在执行结束的状态
 * ３、setFillBefore(Boolean fillBefore)如果fillBefore的值为true，则动画执行后，控件将回到动画执行之前的状态
 * ４、setStartOffSet(long startOffSet)设置动画执行之前的等待时间
 * ５、setRepeatCount(int repeatCount)设置动画重复执行的次数
 */
public class SingleCodeActivity extends Activity {
    private Button rotateButton = null;
    private Button scaleButton = null;
    private Button alphaButton = null;
    private Button translateButton = null;
    private ImageView image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_single_tween);
        TextView tv = (TextView) findViewById(R.id.title_top);
        tv.setText("补间动画-单一动画-code");
        initViews();
    }

    private void initViews() {
        rotateButton = (Button) findViewById(R.id.rotateButton);
        scaleButton = (Button) findViewById(R.id.scaleButton);
        alphaButton = (Button) findViewById(R.id.alphaButton);
        translateButton =
                (Button) findViewById(R.id.translateButton);
        image = (ImageView) findViewById(R.id.image);
        rotateButton.setOnClickListener(new RotateButtonListener());
        scaleButton.setOnClickListener(new ScaleButtonListener());
        alphaButton.setOnClickListener(new AlphaButtonListener());
        translateButton.setOnClickListener(
                new TranslateButtonListener());
    }

    /**
     * 透明动画（淡入淡出效果）
     */
    class AlphaButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            //创建一个AnimationSet对象，参数为Boolean型，
            //true表示使用Animation的interpolator，false则是使用自己的
            AnimationSet animationSet = new AnimationSet(true);
            //创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
            //设置动画执行的时间
            alphaAnimation.setDuration(2000);
            //将alphaAnimation对象添加到AnimationSet当中
            animationSet.addAnimation(alphaAnimation);
            //使用ImageView的startAnimation方法执行动画
            image.startAnimation(animationSet);
        }
    }

    /**
     * 旋转动画
     */
    class RotateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：从哪个旋转角度开始
            //参数2：转到什么角度
            //后4个参数用于设置围绕着旋转的圆的圆心在哪里
            //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标
            //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数5：确定y轴坐标的类型
            //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            RotateAnimation rotateAnimation = new RotateAnimation(0,
                    360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(2000);
            animationSet.addAnimation(rotateAnimation);
            image.startAnimation(animationSet);
        }
    }

    /**
     * 缩放动画
     */
    class ScaleButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1：x轴的初始值
            //参数2：x轴收缩后的值
            //参数3：y轴的初始值
            //参数4：y轴收缩后的值
            //参数5：确定x轴坐标的类型
            //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            //参数7：确定y轴坐标的类型
            //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴
            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0, 0.1f, 0, 0.1f,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(2000);
            animationSet.addAnimation(scaleAnimation);
            image.startAnimation(animationSet);
        }
    }

    /**
     * 位移动画
     */
    class TranslateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            AnimationSet animationSet = new AnimationSet(true);
            //参数1～2：x轴的开始位置
            //参数3～4：y轴的开始位置
            //参数5～6：x轴的结束位置
            //参数7～8：x轴的结束位置
            TranslateAnimation translateAnimation =
                    new TranslateAnimation(
                            Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
            translateAnimation.setDuration(2000);
            animationSet.addAnimation(translateAnimation);
            image.startAnimation(animationSet);
        }
    }
}
