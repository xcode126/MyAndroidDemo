package com.xcode126.eventbususe;

import android.app.Activity;
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
 * Created by sky on 2017/1/11.
 */

public class EventBusSendActivity extends Activity {
    private Button bt_eventbus_send_main;
    private Button bt_eventbus_send_sticky;
    private TextView tv_eventbus_send_result;
    boolean isFirstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbussend);
        initView();
        initListener();
    }

    private void initView() {
        bt_eventbus_send_main = (Button) findViewById(R.id.bt_eventbus_send_main);
        bt_eventbus_send_sticky = (Button) findViewById(R.id.bt_eventbus_send_sticky);
        tv_eventbus_send_result = (TextView) findViewById(R.id.tv_eventbus_send_result);
    }

    private void initListener() {
        // 主线程发送数据按钮点击事件处理
        bt_eventbus_send_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 4 发送消息
                EventBus.getDefault().post(new MessageEvent("主线程发送过来的数据"));
                // 结束当前页面
                finish();
            }
        });
        // 接收粘性事件数据按钮的点击事件处理
        bt_eventbus_send_sticky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFirstFlag) {
                    isFirstFlag = false;
                    // 4 注册
                    EventBus.getDefault().register(EventBusSendActivity.this);
                }

            }
        });
    }

    // 3 接收粘性事件
    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void StickyEventBus(StickyEvent event) {
        // 显示接收的数据
        tv_eventbus_send_result.setText(event.msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 5 解注册
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(EventBusSendActivity.this);
    }
}
