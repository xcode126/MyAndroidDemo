package com.xcode126.glideuse.activity;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.xcode126.glideuse.R;

import java.io.File;

/**
 * Created by sky on 2017/1/23.
 */

public class GlideBaseActivity extends Activity {
    //    private TextView tvTitle;
    private TextView tvGlide1;
    private ImageView ivGlide1;
    private TextView tvGlide2;
    private ImageView ivGlide2;
    private TextView tvGlide3;
    private ImageView ivGlide3;
    private TextView tvGlide4;
    private ImageView ivGlide4;
    private TextView tvGlide5;
    private ImageView ivGlide5;
    private TextView tvGlide6;
    private ImageView ivGlide6;
    private TextView tvGlide7;
    private ImageView ivGlide7;
    private TextView tvGlide8;
    private ImageView ivGlide8;
    private TextView tvGlide9;
    private ImageView ivGlide9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glidebase);
        initView();
        initEvent();
    }

    private void initView() {
        tvGlide1 = (TextView) findViewById(R.id.tv_glide_1);
        ivGlide1 = (ImageView) findViewById(R.id.iv_glide_1);
        tvGlide2 = (TextView) findViewById(R.id.tv_glide_2);
        ivGlide2 = (ImageView) findViewById(R.id.iv_glide_2);
        tvGlide3 = (TextView) findViewById(R.id.tv_glide_3);
        ivGlide3 = (ImageView) findViewById(R.id.iv_glide_3);
        tvGlide4 = (TextView) findViewById(R.id.tv_glide_4);
        ivGlide4 = (ImageView) findViewById(R.id.iv_glide_4);
        tvGlide5 = (TextView) findViewById(R.id.tv_glide_5);
        ivGlide5 = (ImageView) findViewById(R.id.iv_glide_5);
        tvGlide6 = (TextView) findViewById(R.id.tv_glide_6);
        ivGlide6 = (ImageView) findViewById(R.id.iv_glide_6);
        tvGlide7 = (TextView) findViewById(R.id.tv_glide_7);
        ivGlide7 = (ImageView) findViewById(R.id.iv_glide_7);
        tvGlide8 = (TextView) findViewById(R.id.tv_glide_8);
        ivGlide8 = (ImageView) findViewById(R.id.iv_glide_8);
        tvGlide9 = (TextView) findViewById(R.id.tv_glide_9);
        ivGlide9 = (ImageView) findViewById(R.id.iv_glide_9);
    }

    private void initEvent() {
        //（1）加载网络图片
        tvGlide1.setText("（1）加载网络图片");
        Glide.with(this).load("http://img1.imgtn.bdimg.com/it/u=2615772929,948758168&fm=21&gp=0.jpg").into(ivGlide1);
        //（2）加载资源图片
        tvGlide2.setText("（2）加载资源图片");
        Glide.with(this).load(R.mipmap.ic_launcher).into(ivGlide2);
        //（3）加载本地图片
        tvGlide3.setText("（3）加载本地图片");
        String path = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(this).load(uri).into(ivGlide3);
        // （4）加载网络gif
        tvGlide4.setText("（4）加载网络gif");
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.mipmap.ic_launcher).into(ivGlide4);
        // （5）加载资源gif
        tvGlide5.setText("（5）加载资源gif");
        Glide.with(this).load(R.mipmap.loading).asGif().placeholder(R.mipmap.ic_launcher).into(ivGlide5);
        //（6）加载本地gif
        tvGlide6.setText("（6）加载本地gif");
        String gifPath = Environment.getExternalStorageDirectory() + "/meinv2.jpg";
        File gifFile = new File(gifPath);
        Glide.with(this).load(gifFile).placeholder(R.mipmap.ic_launcher).into(ivGlide6);
        //（7）加载本地小视频和快照
        tvGlide7.setText("（7）加载本地小视频和快照");
        String videoPath = Environment.getExternalStorageDirectory() + "/video.mp4";
        File videoFile = new File(videoPath);
        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.mipmap.ic_launcher).into(ivGlide7);
        //（8）设置缩略图比例,然后，先加载缩略图，再加载原图
        tvGlide8.setText("（8）设置缩略图比例,然后，先加载缩略图，再加载原图");
        String urlPath = Environment.getExternalStorageDirectory() + "/meinv1.jpg";
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide8);

        //（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图
        tvGlide9.setText("（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图");
        DrawableRequestBuilder thumbnailRequest = Glide.with(this).load(new File(urlPath));
        Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide9);
    }
}
