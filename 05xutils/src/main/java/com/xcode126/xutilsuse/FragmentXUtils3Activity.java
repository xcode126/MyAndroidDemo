package com.xcode126.xutilsuse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * Created by sky on 2017/1/10.
 */
@ContentView(R.layout.activity_fragment_xutils3)
public class FragmentXUtils3Activity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //1.得到FragmentManger
        FragmentManager fm = getSupportFragmentManager();
        //2.开启事物
        FragmentTransaction tt = fm.beginTransaction();
        //3.替换Fragment
        tt.replace(R.id.fl_content, new DemoFragment());
        //4.提交
        tt.commit();
    }
}
