package com.xcode126.binnerview.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Bitmap工具类(集成于Glide)
 * Created by WebUser on 16/8/26.
 */
public class BitmapUtils {

    private static BitmapUtils bitmapUtils;

    public static synchronized BitmapUtils getInstance() {
        if (bitmapUtils == null) {
            bitmapUtils = new BitmapUtils();
        }
        return bitmapUtils;
    }

    /**
     * 加载图片(缓存全尺寸又缓存其他尺寸,加快展示图片)
     *
     * @param (objectContainer 容器:Activity、Fragment、FragmentActivity、Context)
     * @param imageView:控件
     * @param (url             -- 包含网络Url、本地路径、Uri、资源文件)
     */
    public void loadImage(Object objectContainer, ImageView imageView, Object url, int placeholderId, int errorId) {
        if (objectContainer instanceof Activity) {
            Glide.with((Activity) objectContainer).load(url).placeholder(placeholderId).error(errorId).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (objectContainer instanceof Fragment) {
            Glide.with((Fragment) objectContainer).load(url).placeholder(placeholderId).error(errorId).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else {
            Glide.with((Context) objectContainer).load(url).placeholder(placeholderId).error(errorId).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
    }

    /**
     * 加载图片(缓存全尺寸又缓存其他尺寸,加快展示图片)
     *
     * @param (objectContainer 容器:Activity、Fragment、FragmentActivity、Context)
     * @param imageView:控件
     * @param (url             -- 包含网络Url、本地路径、Uri、资源文件)
     */
    public void loadImage(Object objectContainer, ImageView imageView, Object url) {
        if (objectContainer instanceof Activity) {
            Glide.with((Activity) objectContainer).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else if (objectContainer instanceof Fragment) {
            Glide.with((Fragment) objectContainer).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        } else {
            Glide.with((Context) objectContainer).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        }
    }

    /**
     * 加载图片(缓存全尺寸又缓存其他尺寸,加快展示图片)
     *
     * @param (objectContainer 容器:Activity、Fragment、FragmentActivity、Context)
     * @param imageView:控件
     * @param (imgPath         -- 包含网络Url、本地路径、Uri、资源文件)
     * @param requestListener
     */
    public void loadImage(Object objectContainer, ImageView imageView, Object imgPath, RequestListener requestListener) {
        if (objectContainer instanceof Activity) {
            Glide.with((Activity) objectContainer).load(imgPath).diskCacheStrategy(DiskCacheStrategy.ALL).listener(requestListener).into(imageView);
        } else if (objectContainer instanceof Fragment) {
            Glide.with((Fragment) objectContainer).load(imgPath).diskCacheStrategy(DiskCacheStrategy.ALL).listener(requestListener).into(imageView);
        } else {
            Glide.with((Context) objectContainer).load(imgPath).diskCacheStrategy(DiskCacheStrategy.ALL).listener(requestListener).into(imageView);
        }
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    public long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 删除指定目录下的文件，这里用于缓存的删除
     *
     * @param filePath       filePath
     * @param deleteThisPath deleteThisPath
     */
    public void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {
                    File files[] = file.listFiles();
                    for (File file1 : files) {
                        deleteFolderFile(file1.getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {
                        file.delete();
                    } else {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(double size) {

        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 設置圖片大小，和位置
     *
     * @param textView
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param width
     * @param height
     * @param isDefault //是否采用图片本身长宽度
     */
    public void setCompoundDrawables(TextView textView, Drawable left,
                                     Drawable top, Drawable right, Drawable bottom, int width,
                                     int height, boolean isDefault) {
        // 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        int w = width, h = height;
        if (left != null) {
            if (isDefault) {
                w = left.getMinimumWidth();
                h = left.getMinimumHeight();
            }
            left.setBounds(0, 0, w, h);
        }
        if (top != null) {
            if (isDefault) {
                w = top.getMinimumWidth();
                h = top.getMinimumHeight();
            }
            top.setBounds(0, 0, w, h);
        }
        if (right != null) {
            if (isDefault) {
                w = right.getMinimumWidth();
                h = right.getMinimumHeight();
            }
            right.setBounds(0, 0, w, h);
        }
        if (bottom != null) {
            if (isDefault) {
                w = bottom.getMinimumWidth();
                h = bottom.getMinimumHeight();
            }
            bottom.setBounds(0, 0, w, h);
        }
        textView.setCompoundDrawables(left, top, right, bottom); // 设置图标
    }

    /**
     * bitmap 转 file 保存图片为JPEG
     *
     * @param bitmap
     * @param cameraFile2
     */
    public static File saveJPGE_After(Bitmap bitmap, File cameraFile2) {
        // File file = new File(cameraFile2);
        try {
            FileOutputStream out = new FileOutputStream(cameraFile2);
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cameraFile2;
    }
}
