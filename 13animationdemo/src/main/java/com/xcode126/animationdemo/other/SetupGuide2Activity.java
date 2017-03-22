package com.xcode126.animationdemo.other;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xcode126.animationdemo.R;

/**
 * 设置界面2
 */
public class SetupGuide2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_guide2);
    }

    public void next(View v) {
        startActivity(new Intent(this, SetupGuide3Activity.class));
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void pre(View v) {
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }
}
