package com.example.administrator.xiudoufang.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;


/**
 * Created by Administrator on 2018/4/8.
 */

public class LoadingViewDialog extends android.support.v4.app.DialogFragment {

    private static LoadingViewDialog mViewDialog;

    private TextView mTvTitle;

    private String mTitle;
    private boolean isShow;


    public static LoadingViewDialog getInstance() {
        if (mViewDialog == null) {
            mViewDialog = new LoadingViewDialog();
        }
        return mViewDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.dimAmount = 0.0f;
        window.setAttributes(layoutParams);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);


        View view = inflater.inflate(R.layout.layout_loading_view, null);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTitle.setText(mTitle);
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    public void setTitle(String title) {
        mTitle = title;
    }

    public void show(AppCompatActivity activity) {
        if (!isShow) {
            isShow = true;
            super.show(activity.getSupportFragmentManager(), "LoadingViewDialog");
        }
    }

    public void show(Activity activity) {
        if (!isShow) {
            isShow = true;
            super.show(((AppCompatActivity) activity).getSupportFragmentManager(), "LoadingViewDialog");
        }
    }

    public void dismiss() {
        if(isShow){
            super.dismiss();
            isShow = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
