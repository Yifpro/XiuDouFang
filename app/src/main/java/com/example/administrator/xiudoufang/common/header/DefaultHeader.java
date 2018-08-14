package com.example.administrator.xiudoufang.common.header;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class DefaultHeader extends LinearLayout implements RefreshHeader {

    private ImageView mIvArrow;
    private TextView mTvTop;
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
        mTvTop = view.findViewById(R.id.tv_top);
        mTvTime = view.findViewById(R.id.tv_time);
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
        return 0;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTvTop.setText("下拉开始刷新");
                mIvArrow.setVisibility(VISIBLE);
                mProgressView.setVisibility(GONE);
                mIvArrow.animate().rotation(0);
                break;
            case Refreshing:
                mTvTop.setText("正在刷新...");
                mIvArrow.setVisibility(GONE);
                mProgressView.setVisibility(VISIBLE);
                break;
            case ReleaseToRefresh:
                mTvTop.setText("释放立即刷新");
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
