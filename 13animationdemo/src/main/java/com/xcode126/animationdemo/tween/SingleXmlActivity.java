package com.xcode126.animationdemo.tween;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xcode126.animationdemo.R;

/**
 * 单一动画（使用XML实现）
 */
public class SingleXmlActivity extends Activity {
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
        tv.setText("补间动画-单一动画-XML");
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
     * 透明动画
     */
    class AlphaButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            // 使用AnimationUtils装载动画配置文件
            Animation animation = AnimationUtils.loadAnimation(
                    SingleXmlActivity.this, R.anim.alpha);
            // 启动动画
            image.startAnimation(animation);
        }
    }

    /**
     * 旋转动画
     */
    class RotateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    SingleXmlActivity.this, R.anim.rotate);
            image.startAnimation(animation);
        }
    }

    /**
     * 缩放动画
     */
    class ScaleButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(
                    SingleXmlActivity.this, R.anim.scale);
            image.startAnimation(animation);
        }
    }

    /**
     * 位移动画
     */
    class TranslateButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(SingleXmlActivity.this, R.anim.translate);
            image.startAnimation(animation);
        }
    }
}
