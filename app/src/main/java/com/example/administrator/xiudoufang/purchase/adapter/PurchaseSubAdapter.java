package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.bean.PurchaseItem;

import java.util.List;

public class PurchaseSubAdapter extends BaseQuickAdapter<PurchaseItem, BaseViewHolder> {
    public PurchaseSubAdapter(int layoutResId, @Nullable List<PurchaseItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PurchaseItem item) {

    }
}
