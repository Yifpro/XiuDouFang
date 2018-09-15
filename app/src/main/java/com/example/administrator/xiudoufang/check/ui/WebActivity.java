package com.example.administrator.xiudoufang.check.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.base.XiuDouFangApplication;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.MediaScanner;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.ProgressWebView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/8/31
 */

public class WebActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String URL = "url";

    private ProgressWebView mWebView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void initView() {
        setTitle("");
        mWebView = findViewById(R.id.web_view);
        mWebView.enableSlowWholeDocumentDraw();
        findViewById(R.id.iv_back_off).setOnClickListener(this);
        findViewById(R.id.iv_forward).setOnClickListener(this);
    }

    @Override
    public void initData() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(StringUtils.WEB_URL + getIntent().getStringExtra(URL) + "&userid=" + PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_screenshot:
                new RxPermissions(this)
                        .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    saveImageToGallery(shotScreen());
                                } else {
                                    ToastUtils.show(WebActivity.this, "请开启权限后重新尝试");
                                }
                            }
                        });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap shotScreen() {
//        View view = getWindow().getDecorView();
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        Bitmap bitmap = android.graphics.Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
//                view.getMeasuredHeight());
//        view.setDrawingCacheEnabled(false);
//        view.destroyDrawingCache();

        float scale = mWebView.getScale();
        int webViewHeight = (int) (mWebView.getContentHeight() * scale);
        Bitmap bitmap = Bitmap.createBitmap(mWebView.getWidth(), webViewHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mWebView.draw(canvas);
        return bitmap;
    }

    private void saveImageToGallery(Bitmap bitmap) {
        //******** 品牌兼容 ********
//        if(Build.BRAND .equals("Xiaomi") ){
//
//        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss", new Locale("zh", "CN"));
        String path = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/";
        String imgName =  "IMG_" + format.format(new Date()) + ".jpg";
        File file = new File(path, imgName);
        LogUtils.e("Brand->" + Build.BRAND + ", FilePath->" + file.getAbsolutePath());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
//        MediaScanner mediaScanner = new MediaScanner(this);
//        mediaScanner.scanFile(file, "image/jpeg");
        // 其次把文件插入到系统图库
//        try {
//            String path = MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), fileName, null);
//            LogUtils.e("相册 -》 "+path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 最后通知图库更新
        //String path = Environment.getExternalStorageDirectory().getPath();
        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_off:
                if (mWebView.canGoBack()) mWebView.goBack();
                break;
            case R.id.iv_forward:
                if (mWebView.canGoForward()) mWebView.goForward();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }
}
