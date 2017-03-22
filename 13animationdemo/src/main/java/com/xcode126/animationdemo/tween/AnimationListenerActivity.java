package com.xcode126.animationdemo.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.R;

/**
 * 1).AnimationListener是一个监听器，该监听器在动画执行的各个阶段会得到通知，从而调用相应的方法；
 * r主要包括如下三个方法：
 * ·onAnimationEnd(Animation animation) - 当动画结束时调用
 * ·onAnimationRepeat(Animation animation) - 当动画重复时调用
 * ·onAniamtionStart(Animation animation) - 当动画启动时调用
 */
public class AnimationListenerActivity extends Activity {
    private TextView tv;
    private Button addButton = null;
    private Button deleteButton = null;
    private ImageView imageView = null;
    private ViewGroup viewGroup = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_animation_listener);
        tv = (TextView) findViewById(R.id.title_top);
        tv.setText("动画监听-AnimationListener");
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        imageView = (ImageView) findViewById(R.id.image);
        //LinearLayout下的一组控件
        viewGroup = (ViewGroup) findViewById(R.id.layout);
        addButton.setOnClickListener(new AddButtonListener());
        deleteButton.setOnClickListener(new DeleteButtonListener());
    }

    private class AddButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            //淡入
            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(1000);
            animation.setStartOffset(500);
            //创建一个新的ImageView
            ImageView newImageView = new ImageView(
                    AnimationListenerActivity.this);
            newImageView.setImageResource(R.mipmap.ic_bg_image);
            viewGroup.addView(newImageView,
                    new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.FILL_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            newImageView.startAnimation(animation);
        }
    }

    private class DeleteButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            //淡出
            AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
            animation.setDuration(1000);
            animation.setStartOffset(500);
            //为Aniamtion对象设置监听器
            animation.setAnimationListener(
                    new RemoveAnimationListener());
            imageView.startAnimation(animation);
        }
    }

    private class RemoveAnimationListener implements Animation.AnimationListener {
        //动画效果执行完时remove
        public void onAnimationEnd(Animation animation) {
            System.out.println("onAnimationEnd");
            viewGroup.removeView(imageView);
        }

        public void onAnimationRepeat(Animation animation) {
            System.out.println("onAnimationRepeat");
        }

        public void onAnimationStart(Animation animation) {
            System.out.println("onAnimationStart");
        }
    }
}
