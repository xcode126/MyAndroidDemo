package com.xcode126.upmenu;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;


/**
 * 分享页面
 * Created by Administrator on 2016/8/19.
 */
public class SharePopupWindow extends PopupWindow {

    private Button  btn_cancel;
    private View mMenuView;
    private final ImageView ivCirclefriends;
    private final ImageView ivWeiXin;
    private final ImageView ivQQ;

    public SharePopupWindow(Context context, View.OnClickListener itemsOnClick) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.widght_share, null);
        ivCirclefriends = (ImageView) mMenuView.findViewById(R.id.more_circlefriends);
        ivWeiXin = (ImageView) mMenuView.findViewById(R.id.more_weixin);
        ivQQ = (ImageView) mMenuView.findViewById(R.id.more_qq);
        btn_cancel = (Button) mMenuView.findViewById(R.id.btn_cancle);
        //取消按钮
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                btn_cancel.setTextColor(Color.GREEN);
                dismiss();
            }
        });
        //朋友圈、微信、QQ设置点击监听
        ivCirclefriends.setOnClickListener(itemsOnClick);
        ivWeiXin.setOnClickListener(itemsOnClick);
        ivQQ.setOnClickListener(itemsOnClick);


        //设置SharePopupWindow的View
        this.setContentView(mMenuView);
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
        mMenuView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) motionEvent.getY();
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                return true;
            }

        });
    }
}
