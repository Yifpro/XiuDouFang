package com.example.administrator.xiudoufang.login.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.home.MainActivity;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.login.logic.LoginLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {


    private EditText mEtAccount;
    private EditText mEtPassword;
    private ImageView mIvStatus;
    private SelectServerDialog mServerDialog;
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
        mEtAccount = findViewById(R.id.et_account);
        mEtPassword = findViewById(R.id.et_password);
        mIvStatus = findViewById(R.id.iv_status);

        mIvStatus.setOnClickListener(this);
        findViewById(R.id.tv_server).setOnClickListener(this);
        findViewById(R.id.tv_status).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void initData() {
        mLogic = new LoginLogic();
        boolean isSaved = PreferencesUtils.getPreferences().getBoolean(PreferencesUtils.IS_SAVED_ACCOUNT, false);
        mIvStatus.setSelected(isSaved);
        mEtAccount.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_NAME, ""));
        if (isSaved) {
            mEtAccount.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_NAME, ""));
            mEtPassword.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.PASSWORD, ""));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_server:
                if (mServerDialog == null)
                    mServerDialog = new SelectServerDialog();
                mServerDialog.show(getSupportFragmentManager(), "SelectServerDialog");
            case R.id.iv_status:
            case R.id.tv_status:
                mIvStatus.setSelected(!mIvStatus.isSelected());
                break;
            case R.id.tv_login:
                login();
                break;
        }
    }

    //******** 检查是否需要验证码 ********
    private void login() {
        LoadingViewDialog.getInstance().show(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", mEtAccount.getText().toString()); //******** 用户名 ********
        httpParams.put("password", mEtPassword.getText().toString()); //******** 密码 ********
        httpParams.put("shoujino", "");
        mLogic.checkVerificationCode(this, httpParams, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("是否需要验证-> " + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String msg = jsonObject.getString("mesage");
                if ("请求成功".equals(msg)) {
                    String needcheck = jsonObject.getString("needcheck");
                    if ("0".equals(needcheck)) {
                        //******** 不需要验证 ********
                        requestLogin("");
                    } else {
                        //******** 需要 ********
                        LoadingViewDialog.getInstance().dismiss();
                        showVerificationCodeDialog(jsonObject.getString("userid"));
                    }
                } else {
                    ToastUtils.show(LoginActivity.this, msg);
                }
            }
        });
    }

    //******** 请求登录 ********
    private void requestLogin(String phoneCode) {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", mEtAccount.getText().toString()); //******** 用户名 ********
        map.put("password", mEtPassword.getText().toString()); //******** 密码 ********
        map.put("logdianid", ""); //登陆店
        map.put("phonecode", phoneCode); //验证码
        map.put("changedian", "0"); //0：普通登录 1：切换分店
        mLogic.requestLogin(LoginActivity.this, map, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("登录 -> " + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String messagestr = jsonObject.getString("messagestr");
                if (!TextUtils.isEmpty(messagestr)) {
                    LoadingViewDialog.getInstance().dismiss();
                    mVerificateCodeDialog.setEmpty();
                    ToastUtils.show(LoginActivity.this, messagestr);
                } else {
                    //******** 登录信息缓存至本地 ********
                    StringUtils.cacheInfoToFile(response.body(), StringUtils.LOGIN_INFO);
                    //******** 是否记住密码 ********
                    if (mIvStatus.isSelected()) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put(PreferencesUtils.IS_SAVED_ACCOUNT, true);
                        map.put(PreferencesUtils.USER_NAME, mEtAccount.getText().toString());
                        map.put(PreferencesUtils.PASSWORD, mEtPassword.getText().toString());
                        map.put(PreferencesUtils.USER_ID, jsonObject.getString("userid"));
                        map.put(PreferencesUtils.DIAN_ID, jsonObject.getString("dianid"));
                        PreferencesUtils.save(map);
                    } else {
                        PreferencesUtils.save(PreferencesUtils.IS_SAVED_ACCOUNT, false);
                        PreferencesUtils.save(PreferencesUtils.USER_NAME, mEtAccount.getText().toString());
                    }
                    LoadingViewDialog.getInstance().dismiss();
                    MainActivity.start(LoginActivity.this);
                    finish();
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
                        ToastUtils.show(LoginActivity.this, "验证码不能为空");
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
