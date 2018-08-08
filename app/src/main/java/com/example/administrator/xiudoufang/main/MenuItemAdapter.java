package com.example.administrator.xiudoufang.main;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.MenuItem;

import java.util.List;

public class MenuItemAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {

    public MenuItemAdapter(int layoutResId, @Nullable List<MenuItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuItem item) {
        helper.setBackgroundRes(R.id.iv_icon, item.getIcon());
        helper.setText(R.id.tv, item.getTitle());
    }
}
