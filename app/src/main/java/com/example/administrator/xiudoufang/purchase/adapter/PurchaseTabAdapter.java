package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.DrawerTab;

import java.util.List;

public class PurchaseTabAdapter extends BaseQuickAdapter<DrawerTab, BaseViewHolder> {

    public PurchaseTabAdapter(int layoutResId, @Nullable List<DrawerTab> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrawerTab item) {
        helper.getView(R.id.item_layout).setSelected(item.isSelected());
        helper.setText(R.id.tv, item.getTitle());
    }
}
