package com.xcode126.recyclerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 公众号：程序教科书
 * 作用：RecycleView的使用:
 * 1.ListView的功能
 * 2.GridView的功能
 * 3.横向ListView的功能，参考Android 自定义RecyclerView 实现真正的Gallery效果
 * 4.横向ScrollView的功能
 * 5.瀑布流效果
 * 便于添加Item增加和移除动画
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    /**
     * 跳转
     *
     * @param view
     */
    public void btnClick(View view) {
        String tag = (String) view.getTag();
        if (tag.equals("1")) {
            startActivity(new Intent(MainActivity.this, SampleActivity.class));
        } else if (tag.equals("2")) {
            startActivity(new Intent(MainActivity.this, ComplexActivity.class));
        }
    }
}
