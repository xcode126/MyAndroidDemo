package com.xcode126.pickview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 公众号：走近程序员
 * 作用：省市区三级联动
 */
public class MainActivity extends Activity implements CitySelectPopupWindow.OnCitySelectClickCallBackListener {
    private CitySelectPopupWindow citySelectPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    /**
     * 按钮的点击事件
     *
     * @param view
     */
    public void btnClick(View view) {
        if (citySelectPopupWindow == null) {
            citySelectPopupWindow = new CitySelectPopupWindow(MainActivity.this);
        }
        citySelectPopupWindow.setCitySelectCallBackListener(this);
        citySelectPopupWindow.showAtLocation(MainActivity.this.findViewById(R.id.activity_main), Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void OnFinishClickButton(View v, String address_area_id1_name, String address_area_id2_name, String address_area_id3_name, String address_area_id1, String address_area_id2, String address_area_id3) {
        String address_area_name = address_area_id1_name + address_area_id2_name + address_area_id3_name;
//        address_area_id1=area_id1;
//        address_area_id2=area_id2;
//        address_area_id3=area_id3;
        Toast.makeText(MainActivity.this, address_area_name, Toast.LENGTH_LONG).show();
    }
}
