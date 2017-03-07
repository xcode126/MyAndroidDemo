package com.xcode126.upmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by sky on 2017/2/21.
 * 上弹分享菜单
 */

public class ShareMenuActivity extends Activity {
    private com.xcode126.upmenu.SharePopupWindow sharePopupWindow;//分享弹窗

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_share);
    }

    public void upShareBtnClick(View view) {
        if (sharePopupWindow == null) {
            sharePopupWindow = new com.xcode126.upmenu.SharePopupWindow(ShareMenuActivity.this, itemsOnClick);
        }
        //设置弹出窗体需要软键盘
        sharePopupWindow.setSoftInputMode(com.xcode126.upmenu.SharePopupWindow.INPUT_METHOD_NEEDED);
        //设置模式，和Activity的一样，覆盖，调整大小。
        sharePopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //显示窗口
        sharePopupWindow.showAtLocation(ShareMenuActivity.this.findViewById(R.id.sharemenu_id), Gravity.BOTTOM, 0, 0);
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {

        public void onClick(View v) {
            sharePopupWindow.dismiss();
            switch (v.getId()) {
                case R.id.more_circlefriends://朋友圈
                    Toast.makeText(ShareMenuActivity.this,"朋友圈被点击",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.more_weixin://微信
                    Toast.makeText(ShareMenuActivity.this,"微信被点击",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.more_qq://QQ
                    Toast.makeText(ShareMenuActivity.this,"QQ被点击",Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
}
