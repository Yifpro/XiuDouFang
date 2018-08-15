package com.example.administrator.xiudoufang.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.LoginStore;

import java.util.List;

public class StoreSwitchAdapter extends BaseQuickAdapter<LoginStore, BaseViewHolder> {

    public StoreSwitchAdapter(int layoutResId, @Nullable List<LoginStore> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, LoginStore item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setBackgroundRes(R.id.iv_icon, item.isSelected() ? R.mipmap.ic_hook : 0);
    }
}
