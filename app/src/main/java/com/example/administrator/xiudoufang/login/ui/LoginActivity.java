package com.example.administrator.xiudoufang.login.ui;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.MainActivity;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SimpleEditTextView;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.login.logic.LoginLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {


    private SimpleEditTextView mSetvAccount;
    private SimpleEditTextView mSetvPassword;
    private ImageView mIvPsdStatus;
    private ServerSelectorDialogFragment mDialogFragment;
    private VerificatieDialogFragment mFragment;

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
                if (mDialogFragment == null)
                    mDialogFragment = new ServerSelectorDialogFragment();
                mDialogFragment.show(getSupportFragmentManager(), "");
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

    private void login() {
        LoadingViewDialog.getInstance().show(this);
        //******** 检查是否需要验证码 ********
        mLogic.checkVerificationCode(mSetvAccount.getText(), mSetvPassword.getText(), "", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("检查验证码->" + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String msg = jsonObject.getString("mesage");
                if ("请求成功".equals(msg)) {
                    String needcheck = jsonObject.getString("needcheck");
                    if ("0".equals(needcheck)) {
                        requestLogin("");
                    } else {
                        //******** 发送验证码 ********
                        LoadingViewDialog.getInstance().dismiss();
                        showVerificationCodeDialog(jsonObject.getString("userid"));
                    }
                } else {
                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showVerificationCodeDialog(final String userid) {
        if (mFragment == null) {
            mFragment = new VerificatieDialogFragment();
            mFragment.setUserid(userid);
            mFragment.setLogic(mLogic);
            mFragment.setOnSubmitClickListener(new VerificatieDialogFragment.OnSubmitClickListener() {
                @Override
                public void onSubmitClick(String phoneCode) {
                    LoadingViewDialog.getInstance().show(LoginActivity.this);
                    requestLogin(phoneCode);
                }
            });
        }
        mFragment.show(getSupportFragmentManager(), "VerificatieDialogFragment");
    }

    private void requestLogin(String phoneCode) {
        mLogic.requestLogin(LoginActivity.this, mSetvAccount.getText(), mSetvPassword.getText(), "", phoneCode, "0", new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("登录->" + response.body());
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                String messagestr = jsonObject.getString("messagestr");
                if (!TextUtils.isEmpty(messagestr)) {
                    mFragment.setEmpty();
                    LoadingViewDialog.getInstance().dismiss();
                    Toast.makeText(LoginActivity.this, messagestr, Toast.LENGTH_SHORT).show();
                } else {
                    mLogic.cacheLoginInfo(LoginActivity.this, response.body());
                    if (mIvPsdStatus.isSelected()) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put(PreferencesUtils.USER_NAME, mSetvAccount.getText());
                        map.put(PreferencesUtils.PASSWORD, mSetvPassword.getText());
                        map.put(PreferencesUtils.USER_ID, jsonObject.getString("userid"));
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

    //******** 点击外部区域回缩软键盘 ********
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
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

    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
