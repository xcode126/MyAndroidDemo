package com.xcode126.gatewaydemo;

import android.app.Activity;
import android.os.Bundle;

/**
 * 网关demo
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    class ConnectLanAllAsyncTask extends AsyncTask<Void, Integer, Integer> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            customProgressDialog = new CustomProgressDialog(LoginActivity.this, "寻找中..");
//            customProgressDialog.setCancelable(true);
//            customProgressDialog.show();
//        }
//
//        @Override
//        protected Integer doInBackground(Void... params) {
//            return null;
//        }
//    }
}
