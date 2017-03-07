package com.xcode126.eventbususe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xcode126.eventbususe.event.MessageEvent;
import com.xcode126.eventbususe.event.StickyEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * QQ号：1397028339
 * 作用：EventBus使用
 */
public class MainActivity extends Activity {
    private TextView tv_title;
    private Button bt_eventbus_send;
    private Button bt_eventbus_sticky;
    private TextView tv_eventbus_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        initData();

        initListener();
    }

    private void initView() {
//        tv_title = (TextView)findViewById(R.id.tv_title);
        bt_eventbus_send = (Button) findViewById(R.id.bt_eventbus_send);
        bt_eventbus_sticky = (Button) findViewById(R.id.bt_eventbus_sticky);
        tv_eventbus_result = (TextView) findViewById(R.id.tv_eventbus_result);
    }

    private void initData() {
        // 1注册广播
        EventBus.getDefault().register(MainActivity.this);
    }

    private void initListener() {
        //2.跳转到发送界面
        bt_eventbus_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EventBusSendActivity.class));
            }
        });
        // 发送粘性事件到发送页面
        bt_eventbus_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2 发送粘性事件
                EventBus.getDefault().postSticky(new StickyEvent("我是粘性事件"));
                // 跳转到发送数据页面
                startActivity(new Intent(MainActivity.this, EventBusSendActivity.class));
            }
        });
    }

    // 5接收消息
    @Subscribe(threadMode = ThreadMode.MainThread)
    public void MesssageEventBus(MessageEvent event) {
        // 显示接收的消息
        tv_eventbus_result.setText(event.name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 2 解注册
        EventBus.getDefault().unregister(MainActivity.this);
    }

}
