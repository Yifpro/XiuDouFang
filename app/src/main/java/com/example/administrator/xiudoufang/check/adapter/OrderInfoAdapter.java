package com.example.administrator.xiudoufang.check.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.StringPair;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31
 */

public class OrderInfoAdapter extends BaseQuickAdapter<StringPair, BaseViewHolder> {

    public OrderInfoAdapter(@LayoutRes int layoutResId, @Nullable List<StringPair> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StringPair item) {
        helper.setText(R.id.tv_key, item.getKey());
        helper.setText(R.id.tv_value, item.getValue());
        helper.getView(R.id.line_layout).setVisibility(item.isShowLine() ? View.VISIBLE : View.GONE);
    }
}
