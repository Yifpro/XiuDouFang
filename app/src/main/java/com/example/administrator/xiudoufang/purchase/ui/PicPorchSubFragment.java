package com.example.administrator.xiudoufang.purchase.ui;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/14
 */

public class PicPorchSubFragment extends BaseFragment implements View.OnClickListener {

    private ImageView mIvClose;
    private String mPhotoUrl;

    public static PicPorchSubFragment newInstance(String photoUrl) {
        PicPorchSubFragment fragment = new PicPorchSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString("photo_url", photoUrl);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic_porch;
    }

    @Override
    public void initView(View view) {
        mIvClose = view.findViewById(R.id.iv_close);
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        mPhotoUrl = getArguments().getString("photo_url");
        GlideApp.with(getActivity()).load(mPhotoUrl.contains("/") ? mPhotoUrl : StringUtils.PIC_URL + mPhotoUrl).error(R.mipmap.ic_error).into(ivIcon);

        mIvClose.setOnClickListener(this);
        view.findViewById(R.id.constraint_layout).setOnClickListener(this);
    }

    @Override
    public void initData() {
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        mIvClose.animate().alpha(0).setDuration(800).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.constraint_layout:
                mIvClose.animate().alpha(mIvClose.getAlpha() == 1 ? 0 : 1).setDuration(400).start();
                break;
            case R.id.iv_close:
                getActivity().finish();
                break;
        }
    }
}
