package com.xcode126.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.xcode126.recyclerviewdemo.util.DividerItemDecoration;


import java.util.ArrayList;

import it.gmariotti.recyclerview.itemanimator.SlideInOutBottomItemAnimator;

/**
 * Created by sky on 2017/5/15.
 * recycleView代替listview和gridview使用
 */

public class SampleActivity extends Activity {
    private ArrayList<String> datas;
    private MyAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    //布局管理器
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sample);
        initView();
        initEvent();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("数据" + i);
        }
        adapter = new MyAdapter(SampleActivity.this, datas);
        recyclerView.setAdapter(adapter);
    }

    private void initEvent() {
        /**
         * 设置适配器-水平方向-竖直方向-网格-瀑布流
         */
        linearLayoutManager = new LinearLayoutManager(SampleActivity.this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(SampleActivity.this, 2, GridLayoutManager.VERTICAL, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /**
         * 设置分割线，没有提供默认的分割线
         * 1.自定义线性分割线
         * 2.自定义gridview分割线
         */
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //recyclerView.addItemDecoration(new DividerGridItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        /**
         * 设置item动画,系统默认就一个，可以使用github上的第三方动画样式，
         * 地址为：https://github.com/gabrielemariotti/RecyclerViewItemAnimators
         * 简单的动画：克隆defaultitemanimator由谷歌提供的自定义动画。这一部分仍在测试
         * slideinoutleftitemanimator：适用于/幻灯片/左动画
         * slideinoutrightitemanimator：适用于/幻灯片/正确的动画
         * slideinouttopitemanimator：适用于/幻灯片/顶部动画
         * slideinoutbottomitemanimator：适用于/幻灯片/底部动画
         * scaleinoutitemanimator适用规模：动画
         * slidescaleinoutrightitemanimator：适用与/幻灯片/正确的动画缩放动画
         */
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setItemAnimator(new SlideInOutBottomItemAnimator(recyclerView));

        /**
         * 监听事件
         */
        adapter.setOnItemClickListener(new myOnItemClickListener());
        adapter.setOnImageClickListener(new myOnImageClickListener());
        adapter.setOnItemLongClickListener(new myOnItemLongClickListener());
    }

    /**
     * item被点击事件
     */
    private class myOnItemClickListener implements MyAdapter.onItemClickListener {

        @Override
        public void onItemClick(View view, int postion) {
            Toast.makeText(SampleActivity.this, "item被点击位置为：" + postion, 0).show();
        }
    }

    /**
     * 图片被点击事件
     */
    private class myOnImageClickListener implements MyAdapter.onImageClickListener {

        @Override
        public void onImageClick(int position) {
            Toast.makeText(SampleActivity.this, "图片被点击位置为：" + position, 0).show();
        }
    }

    /**
     * 长按事件
     */
    private class myOnItemLongClickListener implements MyAdapter.OnItemLongClickListener {

        @Override
        public void onItemLongClick(View view, int postion) {
            Toast.makeText(SampleActivity.this, "item被长按位置为：" + postion, 0).show();
        }
    }

    /**
     * 按钮的点击事件
     *
     * @param view
     */
    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                adapter.addData(0, "新增数据");
                //定位到第0个位置
                recyclerView.scrollToPosition(0);
                break;
            case R.id.btn_remove:
                adapter.removeData(0);
                //定位到第0个位置
                recyclerView.scrollToPosition(0);
                break;
            case R.id.btn_list:
                recyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.btn_grid:
                recyclerView.setLayoutManager(gridLayoutManager);
                break;

        }
    }

}
