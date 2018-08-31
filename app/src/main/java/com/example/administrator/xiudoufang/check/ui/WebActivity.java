package com.example.administrator.xiudoufang.check.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.base.XiuDouFangApplication;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.MediaScanner;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.ProgressWebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    @Override
    public void initView() {
        setTitle("");
        mWebView = findViewById(R.id.web_view);

        findViewById(R.id.iv_back_off).setOnClickListener(this);
        findViewById(R.id.iv_forward).setOnClickListener(this);
    }

    @Override
    public void initData() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
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
                saveImageToGallery(shotScreen());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap shotScreen() {
        View view = getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bp = android.graphics.Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();
        return bp;
    }

    private void saveImageToGallery(Bitmap bmp) {
        String localPath = XiuDouFangApplication.getContext().getCacheDir().getAbsolutePath();
        File appDir = new File(localPath, "screenshot");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaScanner mediaScanner = new MediaScanner(this);
        mediaScanner.scanFile(file, "image/jpeg");
        // 其次把文件插入到系统图库
//        try {
//            String path = MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), fileName, null);
//            LogUtils.e("相册 -》 "+path);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 最后通知图库更新
        //String path = Environment.getExternalStorageDirectory().getPath();
        //sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
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
