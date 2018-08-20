package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.common.utils.TintUtils;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * Created by Administrator on 2018/8/20
 */

public class ScanActivity extends AppCompatActivity implements IActivityBase, QRCodeView.Delegate, View.OnClickListener {

    private QRCodeView mQRCodeView;
    private TextView mTvFlash;

    private SensorManager sensorManager;
    private boolean mIsOpen;

    public static void start(Context context) {
        Intent intent = new Intent(context, ScanActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView() {
        setTitle("扫一扫");
        mQRCodeView = findViewById(R.id.zxingview);
        mTvFlash = findViewById(R.id.tv_flash);
        mQRCodeView.setDelegate(this);
        mTvFlash.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mQRCodeView.startSpot();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);

        mTvFlash.setText("轻点打开");
        mTvFlash.setTextColor(Color.WHITE);
        mTvFlash.setCompoundDrawablesWithIntrinsicBounds(null, TintUtils.tintDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_flash),
                ColorStateList.valueOf(getResources().getColor(R.color.white))), null, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_flash:
                if (mIsOpen) {
                    mQRCodeView.closeFlashlight();
                    mTvFlash.setText("轻点打开");
                    mTvFlash.setTextColor(Color.WHITE);
                    mTvFlash.setCompoundDrawablesWithIntrinsicBounds(null, TintUtils.tintDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_flash),
                            ColorStateList.valueOf(getResources().getColor(R.color.white))), null, null);
                } else {
                    mQRCodeView.openFlashlight();
                    mTvFlash.setText("轻点关闭");
                    mTvFlash.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mTvFlash.setCompoundDrawablesWithIntrinsicBounds(null, TintUtils.tintDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_flash),
                            ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary))), null, null);
                }
                mIsOpen = !mIsOpen;
                break;
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
        mQRCodeView.showScanRect();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
        super.onDestroy();
    }

    private SensorEventListener listener = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            if (value < 10) {
                mTvFlash.setVisibility(View.VISIBLE);
            }
        }
    };

}
