package com.xcode126.recyclerviewdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.xcode126.recyclerviewdemo.util.MyOkhttp;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new GetData().execute("http://clothing.tigonetwork.com/clo_api/index.php?s=login");
//        http://clothing.tigonetwork.com/clo_api/index.php?s=login
    }

    private class GetData extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (!TextUtils.isEmpty(result)) {
//                UserModel userModel = MyOkhttp.parseJsonWithGson(result, UserModel.class);
                UserModel userModel = JSON.parseObject(result,UserModel.class);
                Toast.makeText(TestActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();
            }

        }
    }


//    /**
//     * 封装的GSON解析工具类，提供泛型参数
//     */
//    class GsonUtil {
//        //将Json数据解析成相应的映射对象
//        public <T> T parseJsonWithGson(String jsonData, Class<T> type) {
//            Gson gson = new Gson();
//            T result = gson.fromJson(jsonData, type);
//            return result;
//        }
//
//    }
}

