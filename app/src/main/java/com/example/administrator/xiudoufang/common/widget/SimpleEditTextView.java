package com.example.administrator.xiudoufang.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.xiudoufang.R;

public class SimpleEditTextView extends LinearLayout {

    private EditText mEtInput;

    public SimpleEditTextView(Context context) {
        this(context, null);
    }

    public SimpleEditTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleEditTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.layout_simple_edit_text_view,this);
        ImageView ivIcon = findViewById(R.id.iv_icon);
        mEtInput = findViewById(R.id.et_input);
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.SimpleEditTextView);
        ivIcon.setBackgroundResource(ta.getResourceId(R.styleable.SimpleEditTextView_setv_icon, 0));
        mEtInput.setHint(ta.getString(R.styleable.SimpleEditTextView_setv_hint));
        if (ta.hasValue(R.styleable.SimpleEditTextView_setv_value)) {
            mEtInput.setText(ta.getString(R.styleable.SimpleEditTextView_setv_value));
        }
        if (ta.hasValue(R.styleable.SimpleEditTextView_setv_input_type)) {
            mEtInput.setInputType(ta.getInt(R.styleable.SimpleEditTextView_setv_input_type, 0));
        }
        if (ta.getBoolean(R.styleable.SimpleEditTextView_setv_is_password, false))
            mEtInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ta.recycle();
    }

    public void setCursorVisible(boolean isVisible) {
        mEtInput.setCursorVisible(isVisible);
    }

    public String getText() {
        return mEtInput.getText().toString();
    }
}
