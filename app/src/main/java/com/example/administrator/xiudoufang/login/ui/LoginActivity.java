package com.example.administrator.xiudoufang.login.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.MainActivity;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SimpleEditTextView;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.login.logic.LoginLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {


    private SimpleEditTextView mSetvAccount;
    private SimpleEditTextView mSetvPassword;
    private ImageView mIvPsdStatus;
    private ServerSelectorDialog mServerDialog;
    private VerificateCodeDialog mVerificateCodeDialog;

    private LoginLogic mLogic;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        SimpleEditTextView setvServer = findViewById(R.id.setv_server);
        mSetvAccount = findViewById(R.id.setv_account);
        mSetvPassword = findViewById(R.id.setv_password);
        mIvPsdStatus = findViewById(R.id.iv_psd_status);

        findViewById(R.id.ll_remember_psd).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);
        setvServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mServerDialog == null)
                    mServerDialog = new ServerSelectorDialog();
                mServerDialog.show(getSupportFragmentManager(), "ServerSelectorDialog");
            }
        });
    }

    @Override
    public void initData() {
        mLogic = new LoginLogic();
        String userName = PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_NAME, null);
        boolean isEmpty = TextUtils.isEmpty(userName);
        mIvPsdStatus.setSelected(!isEmpty);
        if (!isEmpty) {
            mSetvAccount.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_NAME, ""));
            mSetvPassword.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.PASSWORD, ""));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_remember_psd:
                mIvPsdStatus.setSelected(!mIvPsdStatus.isSelected());
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }

    //******** 检查是否需要验证码 ********
    private void login() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", mSetvAccount.getText());
        httpParams.put("password", mSetvPassword.getText());
        httpParams.put("shoujino", "");
        LoadingViewDialog.getInstance().show(this);
        mLogic.checkVerificationCode(httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("是否需要验证码->" + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String msg = jsonObject.getString("mesage");
                if ("请求成功".equals(msg)) {
                    String needcheck = jsonObject.getString("needcheck");
                    if ("0".equals(needcheck)) {
                        requestLogin("");
                    } else {
                        LoadingViewDialog.getInstance().dismiss();
                        showVerificationCodeDialog(jsonObject.getString("userid"));
                    }
                } else {
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //******** 发送验证码 ********
    private void showVerificationCodeDialog(final String userid) {
        if (mVerificateCodeDialog == null) {
            mVerificateCodeDialog = new VerificateCodeDialog();
            mVerificateCodeDialog.setUserid(userid);
            mVerificateCodeDialog.setLogic(mLogic);
            mVerificateCodeDialog.setOnSubmitClickListener(new VerificateCodeDialog.OnSubmitClickListener() {
                @Override
                public void onClick(String phoneCode) {
                    if (TextUtils.isEmpty(phoneCode)) {
                        Toast.makeText(LoginActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    LoadingViewDialog.getInstance().show(LoginActivity.this);
                    requestLogin(phoneCode);
                    mVerificateCodeDialog.dismiss();
                }
            });
        }
        mVerificateCodeDialog.show(getSupportFragmentManager(), "VerificateCodeDialog");
    }

    //******** 请求登录 ********
    private void requestLogin(String phoneCode) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", mSetvAccount.getText());
        map.put("password", mSetvPassword.getText());
        map.put("logdianid", "");
        map.put("phonecode", phoneCode);
        map.put("changedian", "0");
        mLogic.requestLogin(LoginActivity.this, map, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("登录->" + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String messagestr = jsonObject.getString("messagestr");
                if (!TextUtils.isEmpty(messagestr)) {
                    LoadingViewDialog.getInstance().dismiss();
                    mVerificateCodeDialog.setEmpty();
                    Toast.makeText(LoginActivity.this, messagestr, Toast.LENGTH_SHORT).show();
                } else {
                    mLogic.cacheLoginInfo(LoginActivity.this, response.body());
                    if (mIvPsdStatus.isSelected()) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put(PreferencesUtils.USER_NAME, mSetvAccount.getText());
                        map.put(PreferencesUtils.PASSWORD, mSetvPassword.getText());
                        map.put(PreferencesUtils.USER_ID, jsonObject.getString("userid"));
                        map.put(PreferencesUtils.DIAN_ID, jsonObject.getString("dianid"));
                        PreferencesUtils.save(map);
                    } else {
                        PreferencesUtils.remove(PreferencesUtils.USER_NAME);
                        PreferencesUtils.remove(PreferencesUtils.PASSWORD);
                    }
                    LoadingViewDialog.getInstance().dismiss();
                    MainActivity.start(LoginActivity.this);
                    finish();
                }
            }
        });
    }

    //******** 点击外部区域隐藏软键盘 ********
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                SoftInputHelper.hideSoftInput(this);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
