package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;

public class SearchInfoView extends LinearLayout {

    private RelativeLayout mRelativeLayout;
    private TextView mTvValue;
    private TextView mTvKey;

    public SearchInfoView(Context context) {
        this(context, null);
    }

    public SearchInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_search_info_view,this);
        mTvKey = findViewById(R.id.tv_key);
        mTvValue = findViewById(R.id.tv_value);
        mRelativeLayout = findViewById(R.id.relative_layout);
        EditText etInput = findViewById(R.id.et_input);
        ImageView ivNext = findViewById(R.id.iv_next);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.SearchInfoView);
        boolean isEditable = ta.getBoolean(R.styleable.SearchInfoView_siv_isEditable, false);
        etInput.setVisibility(isEditable ? VISIBLE : INVISIBLE);
        mRelativeLayout.setVisibility(isEditable ? INVISIBLE : VISIBLE);
        mTvKey.setText(ta.getString(R.styleable.SearchInfoView_siv_key));
        mTvValue.setText(ta.getString(R.styleable.SearchInfoView_siv_value));
        etInput.setHint(ta.getString(R.styleable.SearchInfoView_siv_hint));
        ivNext.setVisibility(ta.getBoolean(R.styleable.SearchInfoView_siv_isShowNext, true) ? VISIBLE : INVISIBLE);
        ta.recycle();
    }

    public void setKey(String key) {mTvKey.setText(key);}

    public void setValue(String value) {
        mTvValue.setText(value);
    }

    public void setOnClickListener(OnClickListener listener) {
        if (listener == null)
            return;
        mRelativeLayout.setOnClickListener(listener);
    }
}
