package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;

/**
 * Created by Administrator on 2018/9/1
 */

public class CustomerInfoView extends LinearLayout {

    private TextView mTvKey;
    private TextView mTvValue;
    private EditText mEtValue;

    private int mType;

    public CustomerInfoView(Context context) {
        this(context, null);
    }

    public CustomerInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomerInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_customer_info, this);

        ImageView ivNext = findViewById(R.id.iv_next);
        mTvKey = findViewById(R.id.tv_key);
        mTvValue = findViewById(R.id.tv_value);
        mEtValue = findViewById(R.id.et_value);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomerInfoView);
        String key = ta.getString(R.styleable.CustomerInfoView_civ_key);
        mTvKey.setText(key);
        mType = ta.getInt(R.styleable.CustomerInfoView_civ_type, 0);
        switch (mType) {
            case 0:
                mEtValue.setHint(ta.getString(R.styleable.CustomerInfoView_civ_hint));
                mTvValue.setVisibility(GONE);
                break;
            case 1:
                mEtValue.setVisibility(GONE);
                break;
        }
        ivNext.setVisibility(ta.getBoolean(R.styleable.CustomerInfoView_civ_isShowNext, false) ? VISIBLE : GONE);
        if (ta.hasValue(R.styleable.CustomerInfoView_civ_keyColor))
            mTvKey.setTextColor(ta.getColor(R.styleable.CustomerInfoView_civ_keyColor, getResources().getColor(R.color.black)));
        ta.recycle();
    }

    public void setValue(CharSequence text) {
        switch (mType) {
            case 0:
                mEtValue.setText(text);
                break;
            case 1:
                mTvValue.setText(text);
                break;
        }
    }

    public String getValue() {
        return mType == 0 ? mEtValue.getText().toString() : mEtValue.getText().toString();
    }

}
