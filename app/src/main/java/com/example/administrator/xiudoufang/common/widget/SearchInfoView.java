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
import com.example.administrator.xiudoufang.common.utils.LogUtils;

public class SearchInfoView extends LinearLayout {

    private EditText mEtInput;
    private TextView mTvValue;
    private TextView mTvKey;
    private RelativeLayout mRelativeLayout;
    private TextView mTvLeftSegment;
    private TextView mTvRightSegment;

    private int mType;

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
        mEtInput = findViewById(R.id.et_input);
        ImageView ivNext = findViewById(R.id.iv_next);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SearchInfoView);
        mType = ta.getInt(R.styleable.SearchInfoView_siv_type, 0);
        if (mType == 0) {
            mEtInput.setVisibility(VISIBLE);
            mRelativeLayout.setVisibility(INVISIBLE);
            segmentLayout.setVisibility(GONE);

            mEtInput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtInput.setCursorVisible(true);
                }
            });
        } else if (mType == 1) {
            mEtInput.setVisibility(INVISIBLE);
            mRelativeLayout.setVisibility(VISIBLE);
            segmentLayout.setVisibility(GONE);
        } else if (mType == 2) {
            mEtInput.setVisibility(INVISIBLE);
            mRelativeLayout.setVisibility(INVISIBLE);
            mTvLeftSegment = findViewById(R.id.tv_left_segment);
            mTvRightSegment = findViewById(R.id.tv_right_segment);
            mTvLeftSegment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTvLeftSegment.setSelected(true);
                    mTvRightSegment.setSelected(false);
                }
            });
            mTvRightSegment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTvLeftSegment.setSelected(false);
                    mTvRightSegment.setSelected(true);
                }
            });
        }
        mTvValue.setText(ta.getString(R.styleable.SearchInfoView_siv_value));
        mEtInput.setHint(ta.getString(R.styleable.SearchInfoView_siv_hint));
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

    public String getValue() {
        if (mType == 0) {
            return mEtInput.getText().toString();
        } else if (mType == 1) {
            return mTvValue.getText().toString();
        }
        return null;
    }

    public void setOnClickListener(OnClickListener listener) {
        if (listener == null)
            return;
        mRelativeLayout.setOnClickListener(listener);
    }

    public void setLeftSegmentClickable(boolean clickable) {
        if (mTvLeftSegment != null)
            mTvLeftSegment.setClickable(clickable);
    }
    public void setRightSegmentClickable(boolean clickable) {
        if (mTvRightSegment != null)
            mTvRightSegment.setClickable(clickable);
    }

    public void setLeftSegmentSelected(boolean isSelected) {
        mTvLeftSegment.setSelected(isSelected);
    }

    public void setRightSegmentSelected(boolean isSelected) {
        mTvRightSegment.setSelected(isSelected);
    }
}
