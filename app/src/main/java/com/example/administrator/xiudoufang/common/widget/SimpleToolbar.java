package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;

public class SimpleToolbar extends LinearLayout {

    private TextView mTitle;
    private ImageView mRightIcon;
    private ImageView mLeftIcon;
    private TextView mRightText;

    public SimpleToolbar(Context context) {
        this(context, null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_simple_toolbar, this);
        mTitle = findViewById(R.id.tv_toolbar_title);
        mLeftIcon = findViewById(R.id.iv_toolbar_left_icon);
        mRightIcon = findViewById(R.id.iv_toolbar_right_icon);
        mRightText = findViewById(R.id.iv_toolbar_right_text);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SimpleToolbar);
        mTitle.setText(ta.getString(R.styleable.SimpleToolbar_toolbar_title));
        mLeftIcon.setImageResource(ta.getResourceId(R.styleable.SimpleToolbar_left_icon, 0));
        mRightIcon.setImageResource(ta.getResourceId(R.styleable.SimpleToolbar_right_icon, 0));
        mRightText.setText(ta.getString(R.styleable.SimpleToolbar_right_text));
        ta.recycle();
    }

    public SimpleToolbar setTextR(String text) {
        mRightText.setText(text);
        return this;
    }

    public SimpleToolbar setTitle(String titleName) {
        mTitle.setText(titleName);
        return this;
    }

    public SimpleToolbar setIconR(int imgRes) {
        mRightIcon.setImageResource(imgRes);
        return this;
    }

    public SimpleToolbar setIconL(int imgRes) {
        mLeftIcon.setImageResource(imgRes);
        return this;
    }

    public SimpleToolbar setOnTextRClickListener(OnClickListener listener) {
        mRightText.setOnClickListener(listener);
        return this;
    }

    public SimpleToolbar setOnIconRClickListener(OnClickListener listener) {
        mRightIcon.setOnClickListener(listener);
        return this;
    }

    public SimpleToolbar setOnIconLClickListener(OnClickListener listener) {
        mLeftIcon.setOnClickListener(listener);
        return this;
    }
}
