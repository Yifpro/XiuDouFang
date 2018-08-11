package com.example.administrator.xiudoufang.login.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.login.logic.LoginLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.lang.ref.WeakReference;

public class VerificationCodeDialogFragment extends DialogFragment implements View.OnClickListener {

    private OnSubmitClickListener listener;
    private MyHandler mHandler;
    private LoginLogic mLogic;
    private String mUserid;
    private int mCount;
    private TextView mTvCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_verification_code, container);
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_submit).setOnClickListener(this);
        mTvCode = view.findViewById(R.id.tv_code);
        mTvCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLogic.requestVerificationCode(mUserid, new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("发送验证码");
                        mTvCode.setText("60");
                        if (mHandler == null)
                            mHandler = new MyHandler(VerificationCodeDialogFragment.this);
                        mHandler.sendEmptyMessage(0);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mCount = 60;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler = null;
    }

    public void setLogic(LoginLogic logic) {
        mLogic = logic;
    }

    public void setOnSubmitClickListener(OnSubmitClickListener listener) {
        this.listener = listener;
    }

    public void setUserid(String userid) {
        mUserid = userid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_submit:
                if (listener != null)
                    listener.onSubmitClick();
                break;
            case R.id.tv_code:
                mTvCode.setText(R.string.initial_time);
                if (mHandler == null)
                    mHandler = new MyHandler(VerificationCodeDialogFragment.this);
                mHandler.sendEmptyMessage(0);
                break;
        }
    }

    interface OnSubmitClickListener {
        void onSubmitClick();
    }

    private static class MyHandler extends Handler {

        private final WeakReference<VerificationCodeDialogFragment> mWeakReference;

        @SuppressWarnings("unchecked")
        private MyHandler(VerificationCodeDialogFragment fragment) {
            mWeakReference = new WeakReference(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            VerificationCodeDialogFragment fragment = mWeakReference.get();
            if (fragment != null) {
                if (fragment.mCount >= 0) {
                    fragment.mHandler.sendEmptyMessageDelayed(0, 1000);
                    fragment.mTvCode.setText(String.valueOf(fragment.mCount));
                    fragment.mCount--;
                }
            }
        }
    }
}
