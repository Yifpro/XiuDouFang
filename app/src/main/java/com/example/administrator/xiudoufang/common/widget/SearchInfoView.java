package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
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
        inflate(context, R.layout.layout_search_info_view, this);
        mTvKey = findViewById(R.id.tv_key);
        mTvValue = findViewById(R.id.tv_value);
        mRelativeLayout = findViewById(R.id.relative_layout);
        LinearLayout segmentLayout = findViewById(R.id.segment_layout);
        EditText etInput = findViewById(R.id.et_input);
        ImageView ivNext = findViewById(R.id.iv_next);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SearchInfoView);
        int type = ta.getInt(R.styleable.SearchInfoView_siv_type, 0);
        if (type == 0) {
            etInput.setVisibility(VISIBLE);
            mRelativeLayout.setVisibility(INVISIBLE);
            segmentLayout.setVisibility(GONE);
        } else if (type == 1) {
            etInput.setVisibility(INVISIBLE);
            mRelativeLayout.setVisibility(VISIBLE);
            segmentLayout.setVisibility(GONE);
        } else if (type == 2) {
            etInput.setVisibility(INVISIBLE);
            mRelativeLayout.setVisibility(INVISIBLE);
            final TextView tvLeftSegment = findViewById(R.id.tv_left_segment);
            final TextView tvRightSegment = findViewById(R.id.tv_right_segment);
            tvLeftSegment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvLeftSegment.setSelected(true);
                    tvRightSegment.setSelected(false);
                }
            });
            tvRightSegment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvLeftSegment.setSelected(false);
                    tvRightSegment.setSelected(true);
                }
            });
        }
        mTvValue.setText(ta.getString(R.styleable.SearchInfoView_siv_value));
        etInput.setHint(ta.getString(R.styleable.SearchInfoView_siv_hint));
        if (ta.hasValue(R.styleable.SearchInfoView_siv_isShowNext))
            ivNext.setVisibility(ta.getBoolean(R.styleable.SearchInfoView_siv_isShowNext, true) ? VISIBLE : INVISIBLE);
        mTvKey.setText(ta.getString(R.styleable.SearchInfoView_siv_key));
        ta.recycle();
    }

    public void setKey(CharSequence key) {
        mTvKey.setText(key);
    }

    public void setValue(CharSequence value) {
        mTvValue.setText(value);
    }

    public void setOnClickListener(OnClickListener listener) {
        if (listener == null)
            return;
        mRelativeLayout.setOnClickListener(listener);
    }
}
