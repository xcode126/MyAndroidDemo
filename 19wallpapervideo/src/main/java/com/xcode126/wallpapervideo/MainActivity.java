package com.xcode126.wallpapervideo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * 设置动态视频壁纸
 */
public class MainActivity extends Activity {
    private CheckBox mCbVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCbVoice = (CheckBox) findViewById(R.id.cb_is_voice);
        mCbVoice.setOnCheckedChangeListener(new myOnCheckedChangeListener());
    }

    class myOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                // 静音
                VideoLiveWallpaper.voiceSilence(getApplicationContext());
            } else {
                VideoLiveWallpaper.voiceNormal(getApplicationContext());
            }
        }
    }

    public void setVideoToWallPaper(View view) {
        VideoLiveWallpaper.setToWallPaper(this);
    }
}
