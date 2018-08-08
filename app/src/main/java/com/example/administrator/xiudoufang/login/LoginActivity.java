package com.example.administrator.xiudoufang.login;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.main.MainActivity;
import com.example.administrator.xiudoufang.widget.SimpleEditTextView;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class LoginActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {


    private SimpleEditTextView mSetvAccount;
    private SimpleEditTextView mSetvPassword;
    private ImageView mIvPsdStatus;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mSetvAccount = findViewById(R.id.setv_account);
        mSetvPassword = findViewById(R.id.setv_password);
        mIvPsdStatus = findViewById(R.id.iv_psd_status);

        findViewById(R.id.ll_remember_psd).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_remember_psd:
                mIvPsdStatus.setSelected(!mIvPsdStatus.isSelected());
                break;
            case R.id.tv_login:
                final ServerSelectorDialogFragment dialogFragment = new ServerSelectorDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "");
                break;
        }
    }

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
