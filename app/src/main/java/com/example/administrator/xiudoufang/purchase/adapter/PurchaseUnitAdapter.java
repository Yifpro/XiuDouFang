package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.StringPair;

import java.util.List;

public class PurchaseUnitAdapter extends BaseQuickAdapter<StringPair, BaseViewHolder> {

    public PurchaseUnitAdapter(int layoutResId, @Nullable List<StringPair> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StringPair item) {
        helper.setText(R.id.tv, item.getKey() + item.getValue());
    }
}
