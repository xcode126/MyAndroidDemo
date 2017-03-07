package com.xcode126.webviewandh5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by sky on 2017/2/21.
 * JS调用Android原生的播放器播放视频
 */

public class JSCallAndroidActivity extends Activity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_jscallandroid);
        webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);
        //支持双击-前提是页面要支持才显示
//        webSettings.setUseWideViewPort(true);
        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(true);
        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());
        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "android");
        //加载网络资源
//        webView.loadUrl("http://atguigu.com/teacher.shtml");
        webView.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
//        webView.loadUrl("http://10.0.2.2:8080/assets/RealNetJSCallJavaActivity.htm");
//        webView.loadUrl("https://v.qq.com/x/page/i0376xk9dx0.html");
    }

    /**
     * WebView的这个功能在Android 4.2（API 17）一下存在高危的漏洞。这个漏洞的原理就是
     * Android系统通过 WebView.addJavascriptInterface(Object o, String interface)
     * 方法注册可供js调用的Java对象，但是系统并没有对注册的Java对象方法调用做限制。
     * 导致攻击者可以利用反射调用未注册的其他任何Java对象，攻击者可以根据客户端的能力做任何事情。
     * 出于安全考虑，Android 4.2以后的系统规定允许被js调用的Java方法必须以 @JavascriptInterface 进行注解
     */
    class AndroidAndJSInterface {
        /**
         * 该方法将被js调用
         *
         * @param id
         * @param videoUrl
         * @param tile
         */
        @JavascriptInterface
        public void playVideo(int id, String videoUrl, String tile) {
            Log.i("TAG", videoUrl);
            //调起系统所有播放器
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videoUrl), "video/*");
            startActivity(intent);
        }
    }
}
