package com.xcode126.webviewandh5;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by sky on 2017/2/21.
 * Android原生调用JS，并传值
 */

public class AndroidCallJsActivity extends Activity implements View.OnClickListener {
    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;
    /**
     * 加载网页或者说H5页面
     */
    private WebView webView;

    private void findViews() {
        setContentView(R.layout.activity_androidcalljs);
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        initWebView();
    }

    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            // Handle clicks for btnLogin
            login();
        }
    }

    private void login() {
        String numebr = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(numebr) || TextUtils.isEmpty(password)) {
            Toast.makeText(AndroidCallJsActivity.this, "账号或者密码为空", Toast.LENGTH_SHORT).show();
        } else {
            //登录
            login(numebr);
        }
    }

    /**
     * Java调用javaScript
     *
     * @param numebr
     */
    private void login(String numebr) {
        webView.loadUrl("javascript:javaCallJs(" + "'" + numebr + "'" + ")");
        setContentView(webView);
    }

    private void initWebView() {
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        //设置支持javaScript脚步语言
        webSettings.setJavaScriptEnabled(true);
        //支持双击-前提是页面要支持才显示
//        webSettings.setUseWideViewPort(true);
        //支持缩放按钮-前提是页面要支持才显示
        webSettings.setBuiltInZoomControls(false);
        //设置客户端-不跳转到默认浏览器中
        webView.setWebViewClient(new WebViewClient());
        //设置支持js调用java
        webView.addJavascriptInterface(new AndroidAndJSInterface(), "Android");
        //加载网络资源
//        webView.loadUrl("http://10.0.2.2:8080/assets/JavaAndJavaScriptCall.html");
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
        //显示页面
//        setContentView(webView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
    }

    class AndroidAndJSInterface {
        @JavascriptInterface
        public void showToast() {
            Toast.makeText(AndroidCallJsActivity.this, "我在Android原生中被打印", Toast.LENGTH_SHORT).show();
        }
    }

}
