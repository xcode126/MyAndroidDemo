package com.xcode126.animationdemo.property;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xcode126.animationdemo.R;

/**
 * ObjectAnimator继承ValueAnimator
 * 1、提供了ofInt、ofFloat、ofObject，这几个方法都是设置动画作用的元素、作用的属性、动画开始、结束、以及中间的任意个属性值。
 * 当对于属性值，只设置一个的时候，会认为当然对象该属性的值为开始（getPropName反射获取），然后设置的值为终点。如果设置两个，则一个为开始、一个为结束~~~
 * 动画更新的过程中，会不断调用setPropName更新元素的属性，所有使用ObjectAnimator更新某个属性，必须得有getter（设置一个属性值的时候）和setter方法~
 * 2、如果你操作对象的该属性方法里面，比如setRotationX如果内部没有调用view的重绘，则你需要自己手动调用。
 * 3、设置的操作的属性只有一个，那么如果我希望一个动画能够让View既可以缩小、又能够淡出（3个属性scaleX,scaleY,alpha）
 */
public class ObjectAnimatorActivity extends Activity {

    private ImageView id_ball;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        id_ball = (ImageView) findViewById(R.id.id_ball);
    }

    /**
     * View 类中新增的便于实现 property 动画的属性包括：
     * (1) translationX 和 translationY：这两个属性控制着 View 的屏幕位置坐标变化量，以 layout 容器的左上角为坐标原点;
     * (2) rotation、rotationX 和 rotationY：这三个属性控制着 2D 旋转角度（rotation属性）和围绕某枢轴点的 3D 旋转角度;
     * (3) scaleX、scaleY：这两个属性控制着 View 围绕某枢轴点的 2D 缩放比例;
     * (4) pivotX 和 pivotY: 这两个属性控制着枢轴点的位置，前述的旋转和缩放都是以此点为中心展开的,缺省的枢轴点是 View 对象的中心点;
     * (5) x 和 y：这是指 View 在容器内的最终位置，等于 View 左上角相对于容器的坐标加上 translationX 和 translationY 后的值;
     * (6)alpha：表示 View 的 alpha 透明度。缺省值为 1 （不透明），为 0 则表示完全透明（看不见）;
     * 要动画显示 View 对象的某个属性，比如颜色或旋转值，我们所有要做的事情就是创建一个 Property animation，并设定对应的 View 属性。
     * 使用ObjectAnimator实现动画的几个步骤，如下：
     * 1.通过调用ofFloat()、ofInt()等方法创建ObjectAnimator对象，并设置目标对象、需要改变的目标属性名、初始值和结束值；
     * 2.设置动画的持续时间、是否重复及重复次数等属性；
     * 3.启动动画。
     */

    /**
     * 设置单一属性
     *
     * @param view
     */
    public void rotateyAnimRun1(View view) {
        ObjectAnimator.ofFloat(id_ball, "rotationX", 0.0F, 360.0F).setDuration(500).start();
        ObjectAnimator.ofFloat(id_ball, "rotationY", 0.0F, 360.0F).setDuration(1000).start();
    }

    /**
     * 设置多个属性
     *
     * @param view
     */
    public void rotateyAnimRun(View view) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "zhy", 1.0F, 0.0F).setDuration(500);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                id_ball.setAlpha(cVal);
                id_ball.setScaleX(cVal);
                id_ball.setScaleY(cVal);
            }
        });
    }

    /**
     * 设置多个属性-propertyValuesHolder
     *
     * @param view
     */
    public void propertyValuesHolder(View view) {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f,
                0f, 1f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f,
                0, 1f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,
                0, 1f);
        ObjectAnimator.ofPropertyValuesHolder(id_ball, pvhX, pvhY, pvhZ).setDuration(1000).start();
    }
}
