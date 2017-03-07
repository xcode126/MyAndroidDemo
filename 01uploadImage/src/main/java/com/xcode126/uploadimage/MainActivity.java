package com.xcode126.uploadimage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView takePicture;// 拍照
    private TextView photo;// 图片

    //自定义标记变量
    public static final int REQUEST_CODE_COPY_AND_PASTE = 11;
    public static final String COPY_IMAGE = "EASEMOBIMG";// 拷贝图片和字符串使用
    public static final int REQUEST_CODE_CAMERA = 1;
    public static final int REQUEST_CODE_LOCAL = 2;
    public static final int GET_LOCATION_MESSAGE = 3;
    public static final int REQUEST_CODE_FORMWORK = 4;
    public static final int REQUEST_CODE_SELECT_FILE = 5;

    private File cameraFile;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePicture = (TextView) findViewById(R.id.chat_chatview_takepicture);
        photo = (TextView) findViewById(R.id.chat_chatview_photo);
        imageView = (ImageView) findViewById(R.id.image);
        takePicture.setOnClickListener(this);
        photo.setOnClickListener(this);
    }

    /**------------------------------图片处理start-------------------------------------------------**/
    /**
     * 照相获取图片
     */
//    public void selectPicFromCamera() {
//        if (!SDCardUtils.isSDCardEnable()) {
//            ToastUtils.show(MainActivity.this, "SD卡不存在，不能拍照");
//            return;
//        }
//        cameraFile = chattingTool.queryFileWithCamera();
//        startActivityForResult(
//                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
//                        MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
//                REQUEST_CODE_CAMERA);
//    }

    /**
     * 从图库获取图片
     */
    public void selectPicFromLocal() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
        } else {
            intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, REQUEST_CODE_LOCAL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_CAMERA) { // 发送照片
//            if (cameraFile != null && cameraFile.exists())
//                sendPicture(cameraFile.getAbsolutePath());
//        }
        if (requestCode == REQUEST_CODE_LOCAL) { // 发送本地图片
            if (data != null) {
                Uri selectedImage = data.getData();
                if (selectedImage != null) {
                    imageView.setImageURI(selectedImage);
//                    sendPicByUri(selectedImage);
                }
            }
        }
    }

    /**------------------------------图片处理end-------------------------------------------------**/

    /**
     * 获取调用系统相机拍照File
     *
     * @return
     */
//    public File queryFileWithCamera() {
//        File cameraFile = new File(PathUtil.getInstance().getImagePath(),
//                EMClient.getInstance().getCurrentUser()
//                        + System.currentTimeMillis() + ".jpg");
//        cameraFile.getParentFile().mkdirs();
//        return cameraFile;
//    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chat_chatview_takepicture:
//                selectPicFromCamera();
                break;
            case R.id.chat_chatview_photo:
                selectPicFromLocal();
                break;
        }
    }
}
