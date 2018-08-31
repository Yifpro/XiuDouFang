package com.example.administrator.xiudoufang.check.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31
 */

public class PopupOperateAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PopupOperateAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
