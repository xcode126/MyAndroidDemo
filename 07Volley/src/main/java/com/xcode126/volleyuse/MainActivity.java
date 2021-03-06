package com.xcode126.volleyuse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 公众号：程序教科书
 * 作用：VVolley 框架被设计为适用于网络请求非常频繁但是数据量并不是特别大的情景
 */
public class MainActivity extends Activity {
    private TextView tv_title;
    private Button bt_volley_get;
    private Button bt_volley_post;
    private Button bt_volley_getjson;
    private Button bt_volley_imagerequest;
    private Button bt_volley_imageloader;
    private Button bt_volley_networkimageview;

    private ImageView iv_volley_result;
    private NetworkImageView iv_volley_networkimagview;
    private TextView tv_volley_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        bt_volley_get = (Button) findViewById(R.id.bt_volley_get);
        bt_volley_post = (Button) findViewById(R.id.bt_volley_post);
        bt_volley_getjson = (Button) findViewById(R.id.bt_volley_getjson);
        bt_volley_imagerequest = (Button) findViewById(R.id.bt_volley_imagerequest);
        bt_volley_imageloader = (Button) findViewById(R.id.bt_volley_imageloader);
        bt_volley_networkimageview = (Button) findViewById(R.id.bt_volley_networkimageview);

        iv_volley_result = (ImageView) findViewById(R.id.iv_volley_result);
        iv_volley_networkimagview = (NetworkImageView) findViewById(R.id.iv_volley_networkimagview);
        tv_volley_result = (TextView) findViewById(R.id.tv_volley_result);
    }

    private void initListener() {
        getMethod();
        postMethod();
        getJsonData();
        loadImageMethod();
        imageLoadMethod();
        netWorkImageMethod();
    }

    //get请求
    private void getMethod() {
        bt_volley_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //2.创建一个请求
                String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        //正确接受数据回调
                        tv_volley_result.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        // 发生异常后的监听回调
                        tv_volley_result.setText("加载失败" + volleyError);
                    }
                });
                //3.将创建的请求添加到请求队列中
                requestQueue.add(stringRequest);
            }
        });
    }

    //post请求
    private void postMethod() {
        bt_volley_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //2.创建一个请求
                String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tv_volley_result.setText(s);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tv_volley_result.setText("请求失败" + volleyError);
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<String, String>();
//                        map.put("value1","param1");
                        return map;
                    }
                };
                //3.将POST请求添加到队列中
                requestQueue.add(stringRequest);
            }
        });
    }

    //获取Json数据
    private void getJsonData() {
        bt_volley_getjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //2.创建一个请求
                String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        tv_volley_result.setText(jsonObject.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        tv_volley_result.setText("请求失败" + volleyError);
                    }
                });
                //3.将创建的请求添加到请求队列中
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

    // imagerequest加载图片
    private void loadImageMethod() {
        bt_volley_imagerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //2.创建一个图片的请求
                String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        // 正确接收到图片
                        iv_volley_result.setVisibility(View.VISIBLE);
                        iv_volley_result.setImageBitmap(bitmap);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        iv_volley_result.setImageResource(R.mipmap.ic_launcher);
                    }
                });
                //3.将请求添加到请求队列中
                requestQueue.add(imageRequest);
            }
        });
    }

    // imageloader加载图片
    private void imageLoadMethod() {
        bt_volley_imageloader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //创建一个imageLoader
//                ImageLoader imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
//                    @Override
//                    public Bitmap getBitmap(String s) {
//                        return null;
//                    }
//
//                    @Override
//                    public void putBitmap(String s, Bitmap bitmap) {
//
//                    }
//                });
                ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
                // 加载图片
                String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                iv_volley_result.setVisibility(View.VISIBLE);
                ImageLoader.ImageListener imageListener = imageLoader.getImageListener(iv_volley_result, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
                imageLoader.get(url, imageListener);
            }
        });
    }

    //networkimageview加载图片
    private void netWorkImageMethod() {
        bt_volley_networkimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //让控件显示
                iv_volley_networkimagview.setVisibility(View.VISIBLE);
                //创建一个请求队列
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                //创建一个ImageLoader
                ImageLoader imageLoader = new ImageLoader(requestQueue, new BitmapCache());
                //默认图片和异常图片设置
                iv_volley_networkimagview.setDefaultImageResId(R.mipmap.ic_launcher);
                iv_volley_networkimagview.setErrorImageResId(R.mipmap.ic_launcher);
                //加载图片
                String url = "http://img5.mtime.cn/mg/2016/10/11/160347.30270341.jpg";
                iv_volley_networkimagview.setImageUrl(url, imageLoader);
            }
        });
    }

}
