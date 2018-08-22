package com.example.administrator.xiudoufang.common.utils;

import android.app.Activity;
import android.widget.ImageView;

import com.example.administrator.xiudoufang.base.GlideApp;
import com.test.jcit.imagepicker.loader.ImageLoader;

/**
 * Created by Administrator on 2018/8/20
 */

public class GlideImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).load(path).into(imageView);
    }

    @Override
    public void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height) {
        GlideApp.with(activity).load(path).into(imageView);
    }

    @Override
    public void clearMemoryCache() {

    }
}
