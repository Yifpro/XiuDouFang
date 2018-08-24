package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.DrawerItem;

import java.util.List;

public class PurchaseTabAdapter extends BaseQuickAdapter<DrawerItem, BaseViewHolder> {

    public PurchaseTabAdapter(int layoutResId, @Nullable List<DrawerItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrawerItem item) {
        helper.getView(R.id.item_layout).setSelected(item.isSelected());
        helper.setText(R.id.tv, item.getTitle());
    }
}
