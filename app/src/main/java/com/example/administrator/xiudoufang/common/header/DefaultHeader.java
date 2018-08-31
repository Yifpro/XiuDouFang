package com.example.administrator.xiudoufang.common.header;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DefaultHeader extends LinearLayout implements RefreshHeader {

    private static String REFRESH_HEADER_FINISH = "刷新完成";
    private static String REFRESH_HEADER_FAILED = "刷新失败";

    private ImageView mIvArrow;
    private TextView mTitleText;
    private TextView mTvTime;
    private ProgressBar mProgressView;

    public DefaultHeader(Context context) {
        this(context, null);
    }

    public DefaultHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(context, R.layout.layout_default_header, this);
        mIvArrow = view.findViewById(R.id.iv_arrow);
        mProgressView = view.findViewById(R.id.progress_view);
        mTitleText = view.findViewById(R.id.tv_top);
        mTvTime = view.findViewById(R.id.tv_this_collection);
        mTvTime.setText(PreferencesUtils.getPreferences().getString(PreferencesUtils.TIME_REFRESH, "-"));
        setMinimumHeight(SizeUtils.dp2px(60));
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (success) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
            String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            mTvTime.setText(time);
            mTitleText.setText(REFRESH_HEADER_FINISH);
            PreferencesUtils.save(PreferencesUtils.TIME_REFRESH, time);
        } else {
            mTitleText.setText(REFRESH_HEADER_FAILED);
        }
        return 500;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTitleText.setText("下拉开始刷新");
                mIvArrow.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);
                mIvArrow.animate().rotation(0);
                break;
            case Refreshing:
                mTitleText.setText("正在刷新...");
                mIvArrow.setVisibility(GONE);
                mProgressView.setVisibility(VISIBLE);
                break;
            case ReleaseToRefresh:
                mTitleText.setText("释放立即刷新");
                mIvArrow.animate().rotation(180);
                break;
        }
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}
