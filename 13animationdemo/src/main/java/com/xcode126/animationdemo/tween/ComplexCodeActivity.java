package com.xcode126.animationdemo.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.R;

/**
 * 复合动画（使用代码实现）
 * 1.AnimationSet是Animation的子类；
 * 2.一个AnimationSet包含了一系列的Animation；
 * 3.针对AnimationSet设置一些Animation的常见属性（如startOffset，duration等），可以被包含在AnimationSet当中的Animation集成；
 */
public class ComplexCodeActivity extends Activity {

    private ImageView iv_complex;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        iv_complex = (ImageView) findViewById(R.id.iv_complex);
        tv = (TextView) findViewById(R.id.title_top);
        tv.setText("补间动画-复合动画-code");
    }

    /**
     * Code复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续1s
     *
     * @param v
     */
    public void btnComplexClick(View v) {
        //1. 创建透明动画并设置
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        //2. 创建旋转动画并设置
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setStartOffset(2000);//延迟
        //3. 创建复合动画对象
        AnimationSet animationSet = new AnimationSet(true);
        //4. 添加两个动画
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        //5. 启动复合动画对象
        iv_complex.startAnimation(animationSet);
    }
}
