package com.xcode126.badgernumber;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.View;

import com.jauker.widget.BadgeView;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * 作者：sky
 * 邮箱：xcode126@126.com
 * Q Q：1397028339
 * 公众号：程序教科书
 * 作用：桌面角标（消息未读数量）
 * 第三方：
 * 1.https://github.com/leolin310148/ShortcutBadger
 * 2.https://github.com/stefanjauker/BadgeView
 * 3.自己实现
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shortcutBadgerMethod();
//        badgeViewMethod();
//        myCustomerNumber();
    }

    /**
     * 自定义实现，目前仅支持三星，华为，小米
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void myCustomerNumber() {
        Notification.Builder builder = new Notification.Builder(this).setContentTitle("title")
                .setContentText("text").setSmallIcon(R.mipmap.ic_launcher);
        Notification notification = builder.build();
        BadgeUtil.setBadgeCount(notification, MainActivity.this, 10);
    }

    /**
     * 常用的方法
     * 1. setTargetView(View) --> 设置哪个控件显示数字提醒，参数就是一个view对象
     * 2. setBadgeCount(int) --> 设置提醒的数字
     * 3. setBadgeGravity(Gravity) --> 设置badgeview的显示位置
     * 4. setBackgroundColor() --> 设置badgeview的背景色，当然还可以设置背景图片
     * 5. setBackgroundResource() --> 设置背景图片
     * 6. setTypeface() --> 设置显示的字体
     * 7. setShadowLayer() --> 设置字体的阴影
     */
    private void badgeViewMethod(View view) {
        //简单的设置方式
        BadgeView badgeView = new com.jauker.widget.BadgeView(this);
        badgeView.setTargetView(view);
        badgeView.setBadgeCount(3);
        //自定义显示样式1
        badgeView = new BadgeView(this);
        badgeView.setTargetView(view);
        badgeView.setBackground(12, Color.parseColor("#9b2eef"));
        badgeView.setText("提示");
        //自定义显示样式2
        badgeView = new BadgeView(this);
        badgeView.setTargetView(view);
        badgeView.setBadgeGravity(Gravity.BOTTOM | Gravity.CENTER);
        badgeView.setBadgeCount(4);
        //自定义显示样式3
        badgeView = new BadgeView(this);
        badgeView.setTargetView(view);
        badgeView.setBadgeGravity(Gravity.TOP | Gravity.LEFT);
        badgeView.setTypeface(Typeface.create(Typeface.SANS_SERIF,
                Typeface.ITALIC));
        badgeView.setShadowLayer(2, -1, -1, Color.GREEN);
        badgeView.setBadgeCount(2);
    }

    /**
     * The ShortcutBadger makes your Android App show the count of unread messages as a badge on your App shortcut!
     * 在项目中引用：compile "me.leolin:ShortcutBadger:1.1.13@aar"
     * 目前仅支持：sony,Samsung,LG,HTC,XiaoMi,ASUS,ADW,APEX,NOVA,HUAWei,ZUK,OPPO
     */
    private void shortcutBadgerMethod() {
        //添加
        ShortcutBadger.applyCount(MainActivity.this, 10); //for 1.1.4+
//        ShortcutBadger.with(getApplicationContext()).count(10); //for 1.1.3
        //移除
//        ShortcutBadger.applyCount(MainActivity.this, 0); //for 1.1.4+
//        ShortcutBadger.with(getApplicationContext()).count(0); //for 1.1.3
    }

}
