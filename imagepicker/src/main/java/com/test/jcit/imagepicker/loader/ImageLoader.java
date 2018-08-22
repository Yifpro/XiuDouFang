package com.test.jcit.imagepicker.loader;

import android.app.Activity;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * @author WYF on 2018/8/22/022.
 */

public interface ImageLoader extends Serializable {

    void displayImage(Activity activity, String path, ImageView imageView, int width, int height);

    void displayImagePreview(Activity activity, String path, ImageView imageView, int width, int height);

    void clearMemoryCache();
}
