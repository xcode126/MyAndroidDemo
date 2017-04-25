package com.xcode126.pickview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;

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
        initOptionData();
        initOptionPicker();
    }

    //============================================btn1==============================================

    /**
     * 按钮的点击事件
     *
     * @param view
     */
    public void btn1Click(View view) {
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

    //============================================btn2==============================================

    private OptionsPickerView pvOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    public void btn2Click(View view) {
        pvOptions.show();
    }


    private void initOptionData() {
        //选项1
        options1Items.add(new ProvinceBean(0, "今天", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "明天", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "后天", "描述部分", "其他数据"));
        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            options2Items_01.add(i+"省点");
        }
        ArrayList<String> options2Items_02 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            options2Items_02.add(i+"市点");
        }
        ArrayList<String> options2Items_03 = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            options2Items_03.add(i+"去点");
        }
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
    }

    private void initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                       /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
                Toast.makeText(MainActivity.this, tx, Toast.LENGTH_SHORT).show();
            }
        })
                .setTitleText("")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.GREEN)//设置分割线的颜色
                .setSelectOptions(0, 0)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
                .build();

        //pvOptions.setSelectOptions(1,1);

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/

    }
}
