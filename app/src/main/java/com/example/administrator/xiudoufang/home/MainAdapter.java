package com.example.administrator.xiudoufang.home;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.IntegerStringPair;

import java.util.List;

public class MainAdapter extends BaseQuickAdapter<IntegerStringPair, BaseViewHolder> {

    public MainAdapter(int layoutResId, @Nullable List<IntegerStringPair> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegerStringPair item) {
        helper.setBackgroundRes(R.id.iv_icon, item.getIcon());
        helper.setText(R.id.tv, item.getTitle());
    }
}
