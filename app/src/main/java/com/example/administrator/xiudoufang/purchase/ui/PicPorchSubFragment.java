package com.example.administrator.xiudoufang.purchase.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/14
 */

public class PicPorchSubFragment extends BaseFragment implements View.OnClickListener {

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
        ImageView ivIcon = view.findViewById(R.id.iv_icon);
        view.findViewById(R.id.iv_close).setOnClickListener(this);
        String photoUrl = getArguments().getString("photo_url");
        GlideApp.with(getActivity()).load(photoUrl.contains("/") ? photoUrl : StringUtils.PIC_URL + photoUrl).error(R.mipmap.ic_error).into(ivIcon);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                getActivity().finish();
                break;
        }
    }
}
