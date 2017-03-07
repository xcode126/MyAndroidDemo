package com.xcode126.binnerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xcode126.binnerview.model.ApartmentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sky on 2017/3/1.
 * 使用ViewPager
 */

public class ViewPagerActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    private int index = 0;// Fragment的size
    private int preSelImgIndex = 0;//当前页面的索引值
    // 滑动点所在的布局
    private LinearLayout ll_focus_indicator_container = null;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;

    private ArrayList<ImageView> portImg;// 滑动点数组
    private List<Fragment> mDatas;// 装载Fragment的数组
    private ArrayList<ApartmentBean> listdata = new ArrayList<ApartmentBean>();//需要传输的数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_viewpager);
        initViews();
        initData();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        ImageView apartmentBack = (ImageView) findViewById(R.id.act_apartment_back);
        ll_focus_indicator_container = (LinearLayout) findViewById(R.id.ll_focus_indicator_container);
        apartmentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPagerActivity.this.finish();
                overridePendingTransition(R.anim.slide_left_in,
                        R.anim.slide_right_out);
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        ApartmentBean bean;
        for (int i = 0; i < 3; i++) {
            bean = new ApartmentBean();
            if (i == 0) {
                bean.setImageUrl("http://img1.525j.com.cn/files/Content/201412111612022500271.jpg");
                bean = new ApartmentBean("这是标题", "10000", "200", "小户型", "朝西", "低价");
            } else if (i == 1) {
                bean.setImageUrl("http://img2010.fccs.com/second/2014-05/29/140132929579338090.jpg");
                bean = new ApartmentBean("这是标题", "20000", "10", "小户型", "朝南", "高价");
            } else {
                bean.setImageUrl("http://i2.f.itc.cn/upload/suzhou/8349/a_83486255.jpg");
                bean = new ApartmentBean("这是标题", "30000", "220", "大户型", "朝北", "低价");
            }
            listdata.add(bean);
        }
        InitFocusIndicatorContainer();
        initFragment(index);
    }

    /**
     * 初始化滚动点
     */
    private void InitFocusIndicatorContainer() {
        portImg = new ArrayList<ImageView>();
        for (int i = 0; i < listdata.size(); i++) {
            ImageView localImageView = new ImageView(
                    ViewPagerActivity.this);
            localImageView.setId(i);
            ImageView.ScaleType localScaleType = ImageView.ScaleType.FIT_XY;
            localImageView.setScaleType(localScaleType);
            LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(
                    24, 24);
            localImageView.setLayoutParams(localLayoutParams);
            localImageView.setPadding(5, 5, 5, 5);
            if (i == 0) {
                localImageView.setImageResource(R.mipmap.binner_grey_circle);
            } else {
                localImageView.setImageResource(R.mipmap.binner_white_circle);
            }
            portImg.add(localImageView);
            this.ll_focus_indicator_container.addView(localImageView);
        }
    }

    // 初始化碎片
    private void initFragment(int fragement_page) {
        mViewPager = (ViewPager) findViewById(R.id.apartment_mViewpager);
        mDatas = new ArrayList<Fragment>();

        ChildFragment firstFragment = null;
        for (int i = 0; i < fragement_page; i++) {
            firstFragment = new ChildFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("apartment_list", listdata);
            bundle.putInt("apartment_index", i);
            firstFragment.setArguments(bundle);
            mDatas.add(firstFragment);
        }
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return mDatas == null ? 0 : mDatas.size();
            }

            @Override
            public Fragment getItem(int position) {
                // TODO Auto-generated method stub
                return mDatas.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
// 页面跳转完后得到调用，position是你当前选中的页面的Position（位置编号）
        arg0 = arg0 % listdata.size();
        // 修改上一次选中项的背景
        portImg.get(preSelImgIndex).setImageResource(
                R.mipmap.binner_white_circle);
        // 修改当前选中项的背景
        portImg.get(arg0).setImageResource(R.mipmap.binner_grey_circle);
        preSelImgIndex = arg0;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
