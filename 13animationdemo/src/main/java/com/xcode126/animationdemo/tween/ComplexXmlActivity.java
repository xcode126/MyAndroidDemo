package com.xcode126.animationdemo.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.R;

/**
 * 复合动画（使用xml实现）
 * 1.AnimationSet是Animation的子类；
 * 2.一个AnimationSet包含了一系列的Animation；
 * 3.针对AnimationSet设置一些Animation的常见属性（如startOffset，duration等），可以被包含在AnimationSet当中的Animation集成；
 */
public class ComplexXmlActivity extends Activity {
    private ImageView iv_complex;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        iv_complex = (ImageView) findViewById(R.id.iv_complex);
        tv = (TextView) findViewById(R.id.title_top);
        tv.setText("补间动画-复合动画-xml");
    }

    /**
     * Xml复合动画: 透明度从透明到不透明, 持续2s, 接着进行旋转360度的动画, 持续2s
     *
     * @param v
     */
    public void btnComplexClick(View v) {
        //1. 定义动画文件
        //2. 加载动画文件得到动画对象
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.set_test);
        //3. 启动动画
        iv_complex.startAnimation(animation);
    }
}
