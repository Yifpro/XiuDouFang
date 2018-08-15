package com.example.administrator.xiudoufang.check.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;

public class CheckOrderSubFragment extends BaseFragment {

    public static CheckOrderSubFragment newInstance(int type) {
        CheckOrderSubFragment fragment = new CheckOrderSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initView(View view) {
        int type = getArguments().getInt("type");
        ((TextView) view.findViewById(R.id.tv)).setText(type+"");
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_check_order_sub;
    }
}
