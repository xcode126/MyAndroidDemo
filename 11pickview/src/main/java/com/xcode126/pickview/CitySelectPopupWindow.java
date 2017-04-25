package com.xcode126.pickview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xcode126.pickview.adapter.ArrayWheelAdapter;
import com.xcode126.pickview.model.AreaModel;
import com.xcode126.pickview.model.CityModel;
import com.xcode126.pickview.model.ProvinceModel;
import com.xcode126.pickview.widght.OnWheelChangedListener;
import com.xcode126.pickview.widght.WheelView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增地址城市选择弹窗
 * Created by Administrator on 2016/10/18.
 */

public class CitySelectPopupWindow extends PopupWindow implements View.OnClickListener, OnWheelChangedListener {
    private View conentView;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewArea;
    private TextView tv_cancel;
    private TextView tv_finish;
    private Context context;

    /**
     * 数据源
     */
    private List<ProvinceModel> provinceList;
    private List<CityModel> cityList;
    private List<AreaModel> areaList;
    //所有省
    private String[] mProvinceDatas;
    //所有市
    private Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    //所有区
    private Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    //当前省名
    private String mCurrentProviceName;
    //当前市名
    private String mCurrentCityName;
    //当前区名
    private String mCurrentDistrictName = "";
    //省area_id
    private Map<String, String> provinceAreaIdMap = new HashMap<String, String>();
    //市area_id
    private Map<String, String> cityAreaIdMap = new HashMap<String, String>();
    //区area_id
    private Map<String, String> districtAreaIdMap = new HashMap<String, String>();
    //当前省area_id
    private String currentProvinceAreaId;
    //当前市area_id
    private String currentCityAreaId;
    //当前区area_id
    private String currentDistrictAreaId;

    /**
     * 声明一个点击监听接口
     */
    private OnCitySelectClickCallBackListener onCitySelectClickCallBackListener;

    public CitySelectPopupWindow(Context context) {
        super(context);
        this.context = context;
        initView();
        setView();
        //获取本地城市数据
        getCityData();
        initEvent();
    }

    /**
     * init view
     */
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.pop_window_city_select, null);
        mViewProvince = (WheelView) conentView.findViewById(R.id.id_province);
        mViewCity = (WheelView) conentView.findViewById(R.id.id_city);
        mViewArea = (WheelView) conentView.findViewById(R.id.id_district);
        tv_cancel = (TextView) conentView.findViewById(R.id.tv_city_select_cancel);
        tv_finish = (TextView) conentView.findViewById(R.id.tv_city_select_finish);
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewArea.addChangingListener(this);
        tv_cancel.setOnClickListener(this);
        tv_finish.setOnClickListener(this);
    }

    /**
     * set view
     */
    private void setView() {
        //设置SharePopupWindow的View
        this.setContentView(conentView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        conentView.setOnTouchListener(new MyTouchListener());
    }

    /**
     * set touch limit
     */
    private class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            int height = conentView.findViewById(R.id.ll_popu_city).getTop();
            int y = (int) motionEvent.getY();
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        }
    }

    /**
     * 获取本地城市数据
     */
    private void getCityData() {
        try {
            InputStream is = context.getResources().getAssets().open("city-json1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String result = null;
            while ((result = br.readLine()) != null) {
                stringBuffer.append(result);

            }
            String str = stringBuffer.toString();
            parseCityJson(str);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析本地读取的json数据
     */
    private void parseCityJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray provinceArray = jsonObject.optJSONArray("data");
            provinceList = new ArrayList<>();
            for (int i = 0; i < provinceArray.length(); i++) {
                JSONObject provinceObject = provinceArray.getJSONObject(i);
                ProvinceModel provinceModel = new ProvinceModel();
                provinceModel.setAreas_key(provinceObject.getString("areas_key"));
                provinceModel.setAreas_sort(provinceObject.getString("areas_sort"));
                provinceModel.setAreas_type(provinceObject.getString("areas_type"));
                provinceModel.setAreas_name(provinceObject.getString("areas_name"));
                provinceModel.setAreas_id(provinceObject.getString("areas_id"));
                provinceModel.setAreas_parent_id(provinceObject.getString("areas_parent_id"));
                JSONArray cityArray = provinceObject.optJSONArray("child");
                cityList = new ArrayList<>();
                for (int j = 0; j < cityArray.length(); j++) {
                    JSONObject cityObject = cityArray.getJSONObject(j);
                    CityModel cityModel = new CityModel();
                    cityModel.setAreas_id(cityObject.getString("areas_id"));
                    cityModel.setAreas_key(cityObject.getString("areas_key"));
                    cityModel.setAreas_name(cityObject.getString("areas_name"));
                    cityModel.setAreas_parent_id(cityObject.getString("areas_parent_id"));
                    cityModel.setAreas_type(cityObject.getString("areas_type"));
                    cityModel.setAreas_sort(cityObject.getString("areas_sort"));
                    JSONArray areaArray = cityObject.optJSONArray("child");
                    areaList = new ArrayList<>();
                    for (int k = 0; k < areaArray.length(); k++) {
                        JSONObject areaObject = areaArray.getJSONObject(k);
                        AreaModel areaModel = new AreaModel();
                        areaModel.setAreas_key(areaObject.getString("areas_key"));
                        areaModel.setAreas_name(areaObject.getString("areas_name"));
                        areaModel.setAreas_parent_id(areaObject.getString("areas_parent_id"));
                        areaModel.setAreas_id(areaObject.getString("areas_id"));
                        areaModel.setAreas_sort(areaObject.getString("areas_sort"));
                        areaModel.setAreas_type(areaObject.getString("areas_type"));
                        areaList.add(areaModel);
                    }
                    cityModel.setChild(areaList);
                    cityList.add(cityModel);
                }
                provinceModel.setChild(cityList);
                provinceList.add(provinceModel);
            }

            //*/ 初始化默认选中的省、市、区
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getAreas_name();
                currentProvinceAreaId = provinceList.get(0).getAreas_id();
                List<CityModel> cityList = provinceList.get(0).getChild();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getAreas_name();
                    currentCityAreaId = cityList.get(0).getAreas_id();
                    String name = cityList.get(0).getAreas_name();
                    List<AreaModel> districtList = cityList.get(0).getChild();
                    mCurrentDistrictName = districtList.get(0).getAreas_name();
                    currentDistrictAreaId = districtList.get(0).getAreas_id();

                }
            }
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getAreas_name();
                List<CityModel> cityList = provinceList.get(i).getChild();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getAreas_name();
                    List<AreaModel> districtList = cityList.get(j).getChild();
                    String[] distrinctNameArray = new String[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        districtAreaIdMap.put(districtList.get(k).getAreas_name(), districtList.get(k).getAreas_id());
                        distrinctNameArray[k] = districtList.get(k).getAreas_name();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                    cityAreaIdMap.put(cityNames[j], cityList.get(j).getAreas_id());
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getAreas_name(), cityNames);
                provinceAreaIdMap.put(provinceList.get(i).getAreas_name(), provinceList.get(i).getAreas_id());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * init evetn
     */
    private void initEvent() {
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(context, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewArea.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        currentProvinceAreaId = provinceAreaIdMap.get(mCurrentProviceName);
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        currentCityAreaId = cityAreaIdMap.get(mCurrentCityName);
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewArea.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
        mViewArea.setCurrentItem(0);
        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
        currentDistrictAreaId = districtAreaIdMap.get(mCurrentDistrictName);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city_select_cancel:
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
                this.dismiss();
                break;
            case R.id.tv_city_select_finish:

                if (onCitySelectClickCallBackListener != null) {
                    onCitySelectClickCallBackListener.OnFinishClickButton(view, mCurrentProviceName, mCurrentCityName, mCurrentDistrictName
                            , currentProvinceAreaId, currentCityAreaId, currentDistrictAreaId);
                }
                this.dismiss();
                break;
        }
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
// TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewArea) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            currentDistrictAreaId = districtAreaIdMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 创建条件选择点击回调接口
     */
    public static interface OnCitySelectClickCallBackListener {
        public void OnFinishClickButton(View v, String address_area_id1_name, String address_area_id2_name, String address_area_id3_name
                , String address_area_id1, String address_area_id2, String address_area_id3);
    }

    public void setCitySelectCallBackListener(CitySelectPopupWindow.OnCitySelectClickCallBackListener onCitySelectClickCallBackListener) {
        this.onCitySelectClickCallBackListener = onCitySelectClickCallBackListener;
    }
}
