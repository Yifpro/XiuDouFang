package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;

public class SearchInfoView extends LinearLayout {

    private EditText mEtInput;
    private TextView mTvValue;
    private TextView mTvKey;
    private RelativeLayout mRelativeLayout;
    private TextView mTvLeftSegment;
    private LinearLayout mSegmentLayout;
    private TextView mTvRightSegment;
    private ImageView mIvNext;

    private int mType = -1;
    private OnItemChangeListener mListener;

    public SearchInfoView(Context context) {
        this(context, null);
    }

    public SearchInfoView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_search_info, this);
        mTvKey = findViewById(R.id.tv_key);
        mTvValue = findViewById(R.id.tv_value);
        mRelativeLayout = findViewById(R.id.relative_layout);
        mSegmentLayout = findViewById(R.id.segment_layout);
        mEtInput = findViewById(R.id.et_input);
        mIvNext = findViewById(R.id.iv_next);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SearchInfoView);
        mType = ta.getInt(R.styleable.SearchInfoView_siv_type, 0);
        updateType();
        mTvValue.setText(ta.getString(R.styleable.SearchInfoView_siv_value));
        mEtInput.setHint(ta.getString(R.styleable.SearchInfoView_siv_hint));
        if (ta.hasValue(R.styleable.SearchInfoView_siv_isShowNext))
            mIvNext.setVisibility(ta.getBoolean(R.styleable.SearchInfoView_siv_isShowNext, true) ? VISIBLE : INVISIBLE);
        mTvKey.setText(ta.getString(R.styleable.SearchInfoView_siv_key));
        if (ta.hasValue(R.styleable.SearchInfoView_siv_input_type)) {
            if (ta.getInt(R.styleable.SearchInfoView_siv_input_type, 0) == 0) {
                mEtInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            }
        }
        if (ta.hasValue(R.styleable.SearchInfoView_siv_leftTitle)) {
            mTvLeftSegment.setText(ta.getString(R.styleable.SearchInfoView_siv_leftTitle));
            mTvRightSegment.setText(ta.getString(R.styleable.SearchInfoView_siv_rightTitle));
        }
        ta.recycle();
    }

    public void setStatus(boolean isLeft) {
        mTvLeftSegment.setSelected(isLeft);
        mTvRightSegment.setSelected(!isLeft);
    }

    public void setShowNext(boolean isShow) {
        mIvNext.setVisibility(isShow ? VISIBLE : GONE);
    }

    private void updateType() {
        if (mType == 0) {
            mEtInput.setVisibility(VISIBLE);
            mRelativeLayout.setVisibility(INVISIBLE);
            mSegmentLayout.setVisibility(GONE);

            mEtInput.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEtInput.setCursorVisible(true);
                }
            });
        } else if (mType == 1) {
            mEtInput.setVisibility(INVISIBLE);
            mRelativeLayout.setVisibility(VISIBLE);
            mSegmentLayout.setVisibility(GONE);
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
                    if (mListener != null)
                        mListener.onItemchange(true);
                }
            });
            mTvRightSegment.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mTvLeftSegment.setSelected(false);
                    mTvRightSegment.setSelected(true);
                    if (mListener != null)
                        mListener.onItemchange(false);
                }
            });
        }
    }

    public void setOnItemChangeListener(OnItemChangeListener listener) {
        mListener = listener;
    }

    public interface OnItemChangeListener {
        void onItemchange(boolean isLeft);
    }

    public void setType(@ViewType.Type int type) {
        mType = type;
        updateType();
    }

    public void setKey(CharSequence key) {
        mTvKey.setText(key);
    }

    public void setValue(CharSequence value) {
        if (mType == 0) {
            mEtInput.setText(value);
        } else if (mType == 1) {
            mTvValue.setText(value);
        }
    }

    public String getValue() {
        if (mType == 0) {
            return mEtInput.getText().toString();
        } else if (mType == 1) {
            return mTvValue.getText().toString();
        }
        return null;
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

    public void setOnTextChangeListener(TextWatcher textWatcher) {
        mEtInput.addTextChangedListener(textWatcher);
    }

    public static class ViewType {
        public static final int TYPE_EDIT = 0;
        public static final int TYPE_TEXT = 1;
        public static final int TYPE_SEGMENT = 2;

        @IntDef({TYPE_EDIT, TYPE_TEXT, TYPE_SEGMENT})
        public @interface Type {
        }
    }
}
