package com.xcode126.upmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by sky on 2017/3/2.
 * 拍照，选择相册上弹菜单
 */

public class TakePhotoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_takephoto);
    }
}
