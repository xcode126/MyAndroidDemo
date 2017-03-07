package com.xcode126.xutilsuse;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.common.task.PriorityExecutor;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

import static com.xcode126.xutilsuse.R.id.progressbar;

/**
 * Created by sky on 2017/1/10.
 */

@ContentView(R.layout.activity_xutils3_net)
public class XUtils3NetActivity extends Activity {
    @ViewInject(R.id.tv_result)
    private TextView textView;
    @ViewInject(progressbar)
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Event(value = {R.id.btn_get_post, R.id.btn_downloadfile, R.id.btn_uploadfile})
    private void getEvent(View view) {
        switch (view.getId()) {
            case R.id.btn_get_post:
                getAndPostNet();
                break;
            case R.id.btn_downloadfile:
                downloadFile();
                break;
            case R.id.btn_uploadfile:
                uploadFile();
                break;
        }
    }

    private void getAndPostNet() {
        //1.get请求
        //2.POST请求
        RequestParams requestParams = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                textView.setText("Post请求的结果——————" + result);
                Log.e("TAG", "xUtis3联网请求成功==" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                textView.setText("xUtis3联网请求失败==" + ex.getMessage());
                Log.e("TAG", "xUtis3联网请求失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }
        });
    }

    /**
     * 文件下载
     */
    private void downloadFile() {
        RequestParams requestParams = new RequestParams("http://vfx.mtime.cn/Video/2016/09/15/mp4/160915092608935956_480.mp4");
        //设置保存数据
        requestParams.setSaveFilePath(Environment.getExternalStorageDirectory() + "/xcode126/480.mp4");
        //设置是否可以立即取消下载
        requestParams.setCancelFast(true);
        //设置是否可以根据头信息命名
        requestParams.setAutoRename(false);
        //设置断点续传
        requestParams.setAutoResume(true);
        ////自定义线程池,有效的值范围[1, 3], 设置为3时, 可能阻塞图片加载.
        requestParams.setExecutor(new PriorityExecutor(3, true));

        x.http().get(requestParams, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "onSuccess==" + file.toString());
                Toast.makeText(XUtils3NetActivity.this, "onSuccess==" + file.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG", "onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG", "onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
                Log.e("TAG", "onLoading==" + current + "/" + total + ",isDownloading==" + isDownloading);
            }
        });
    }

    /**
     * 文件上传
     */
    private void uploadFile() {
        RequestParams requestParams = new RequestParams("http://192.168.1.16:8080/FileUpload/FileUploadServlet");
        //设置以表单方式上传
        requestParams.setMultipart(true);
        //设置上传文件的路径
        requestParams.addBodyParameter("File", new File(Environment.getExternalStorageDirectory() + "/xcode126/480.mp4"), null, "opop.mp4");
        x.http().post(requestParams, new Callback.ProgressCallback<File>() {
            /**
             * 当下载成功的时候回调这个方法，并且把下载到哪个路径回传过来
             * @param file
             */
            @Override
            public void onSuccess(File file) {
                Log.e("TAG", "onSuccess==" + file.toString());
                Toast.makeText(XUtils3NetActivity.this, "onSuccess==" + file.toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("TAG", "onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("TAG", "onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("TAG", "onFinished==");
            }

            @Override
            public void onWaiting() {
                Log.e("TAG", "onWaiting==");
            }

            @Override
            public void onStarted() {
                Log.e("TAG", "onStarted==");
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                progressBar.setMax((int) total);
                progressBar.setProgress((int) current);
                Log.e("TAG", "onLoading==" + current + "/" + total + ",isDownloading==" + isDownloading);

            }
        });
    }
}
