package com.example.administrator.xiudoufang.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SettingItem;

import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<SettingItem, BaseViewHolder> {

    public SettingAdapter(int layoutResId, @Nullable List<SettingItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SettingItem item) {
        helper.setText(R.id.tv_key, item.getKey());
        helper.setText(R.id.tv_value, item.getValue());
        helper.getView(R.id.line).setVisibility(item.isShowLine() ? View.VISIBLE : View.GONE);
        helper.getView(R.id.iv_next).setVisibility(item.isShowNext() ? View.VISIBLE : View.GONE);
        if (item.isToogleButton()) {
            helper.getView(R.id.toggle).setVisibility(View.VISIBLE);
            helper.getView(R.id.value_layout).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.toggle).setVisibility(View.GONE);
            helper.getView(R.id.value_layout).setVisibility(View.VISIBLE);
        }
    }
}
