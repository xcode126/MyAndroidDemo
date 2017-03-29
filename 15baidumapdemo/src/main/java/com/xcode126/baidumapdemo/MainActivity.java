package com.xcode126.baidumapdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.xcode126.baidumapdemo.model.LocationModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * 公众号：走近程序员
 * 作用：百度地图添加指定位置标注
 */
public class MainActivity extends Activity implements BaiduMap.OnMarkerClickListener {

    private MapView mMapView = null;// 获取百度地图控件
    private BaiduMap mBaiduMap;// 百度地图对象
    private LatLng ll;
    private Marker markerOOA;
    private static final int MAKE_NUMBER = 40;

    //地图
    private GeoCoder geoCoder;
    private ArrayList<LocationModel> resultList;//转换的经纬度数据
    private ArrayList<String> addressList;//拿到的地址数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();
    }

    /**
     * 转换数据开始
     *
     * @param view
     */
    public void btnClick(View view) {
        if (addressList != null && addressList.size() > 0)
            for (int i = 0; i < MAKE_NUMBER; i++) {
                geoCoder.geocode(new GeoCodeOption().city("东莞").address(addressList.get(i)));
            }
    }

    /**
     * 视图初始化
     */
    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        // 获取百度地图
        mBaiduMap = mMapView.getMap();
        // 设置地图显示类型，普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        ll = new LatLng(23.026998, 113.758231);
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
        // 构造一个更新地图的msu对象，然后设置该对象为缩放等级14.0，最后设置地图状态。
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(12.0f);
        mBaiduMap.setMapStatus(msu);
    }

    /**
     * 事件监听
     */
    private void initEvent() {
        resultList = new ArrayList<LocationModel>();
        addressList = new ArrayList<String>();
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(listener);
        mBaiduMap.setOnMarkerClickListener(this);
    }

    /**
     * 获取需要用到的数据
     *
     * @return
     */
    private void initData() {

        String getJson = getFromAssets("data.txt");
        try {
            JSONArray jsonArray = new JSONArray(getJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String addressStr = jsonObject.getString("area") + jsonObject.getString("zDataSourName");
                addressList.add(addressStr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取asserts下的数据
     *
     * @param fileName
     * @return
     */
    public String getFromAssets(String fileName) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    /**
     * 通过地址检测经纬度的回调结果
     */
    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
                Log.i("TAG", "没有检测到结果！");
            } else {
                //获取地理编码结果
                LocationModel locationModel = new LocationModel(result.getLocation().longitude, result.getLocation().latitude);
                resultList.add(locationModel);
                Log.i("TAG", resultList.toString());
                if (resultList.size() == MAKE_NUMBER) {
                    handler.sendEmptyMessage(0x10);
                }
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x10) {
                OverlayOptions options = null;
                for (int i = 0; i < resultList.size(); i++) {
                    LocationModel model = resultList.get(i);
                    LatLng location = new LatLng(model.getLatitude(), model.getLongitude());
                    options = new MarkerOptions().position(location)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka))  //设置marker图标
                            .zIndex(9)  //设置marker所在层级
                            .draggable(true).title(addressList.get(i));  //设置手势拖拽
                    markerOOA = (Marker) (mBaiduMap.addOverlay(options));
                }
            }
        }
    };

    @Override
    public boolean onMarkerClick(Marker marker) {
        for (int i = 0; i < resultList.size(); i++) {
            if (marker.getPosition().latitude == resultList.get(i).getLatitude()) {
                // 创建InfoWindow展示的view
                Button button = new Button(this);
                button.setText(marker.getTitle());
                button.setTextSize(12);
                // button.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                button.setBackgroundResource(R.drawable.ic_map_text_backgound);
                // 创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
                InfoWindow mInfoWindow = new InfoWindow(button, marker.getPosition(), -50);
                // 显示InfoWindow
                mBaiduMap.showInfoWindow(mInfoWindow);
            }
        }
        return true;
    }
}
