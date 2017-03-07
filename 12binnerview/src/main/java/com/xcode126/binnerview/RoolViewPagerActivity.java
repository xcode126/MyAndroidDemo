package com.xcode126.binnerview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xcode126.binnerview.adapter.BannerAdapter;
import com.xcode126.binnerview.model.AdvertisModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2017/2/28.
 * 使用RollViewPager实现滚动广告视图
 */

public class RoolViewPagerActivity extends Activity {
    /**
     * 广告图控件
     */
    private RollPagerView mRollViewPager;
    private BannerAdapter bannerAdapter;
    private List<AdvertisModel> bannerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_roolpager);
        String getStr = getIntent().getStringExtra("str");
        Toast.makeText(RoolViewPagerActivity.this, "接受到的数据："+getStr, Toast.LENGTH_LONG).show();
        //设置轮播图控件
        mRollViewPager = (RollPagerView) findViewById(R.id.banner_gallery);
        //设置滚动间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setHintView(new ColorPointHintView(RoolViewPagerActivity.this, Color.YELLOW, Color.WHITE));

        bannerArray = new ArrayList<>();
        AdvertisModel advertisModel;
        for (int i = 0; i < 4; i++) {
            advertisModel = new AdvertisModel();
            if (i == 0) {
                advertisModel.setAd_url("http://www.446655.com/zs/upFiles/infoImg/coll/20161007/OT20161007001420108.jpg");
            } else if (i == 1) {
                advertisModel.setAd_url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=4255529986,2478532707&fm=23&gp=0.jpg");
            } else if (i == 2) {
                advertisModel.setAd_url("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1108135991,3144768506&fm=23&gp=0.jpg");
            } else {
                advertisModel.setAd_url("http://ww2.sinaimg.cn/crop.0.0.1334.749.1000.562/006epgVqgw1f634d91awej31120kuq7s.jpg");
            }
            bannerArray.add(advertisModel);
        }
        bannerAdapter = new BannerAdapter(mRollViewPager, bannerArray);
        mRollViewPager.setAdapter(bannerAdapter);
    }
}
