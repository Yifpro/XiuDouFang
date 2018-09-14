package com.example.administrator.xiudoufang.setting.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.StringPair;
import com.example.administrator.xiudoufang.common.utils.LogUtils;

import java.util.List;

public class SettingAdapter extends BaseQuickAdapter<StringPair, BaseViewHolder> {

    private CompoundButton.OnCheckedChangeListener listener;

    public SettingAdapter(int layoutResId, @Nullable List<StringPair> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StringPair item) {
        helper.setText(R.id.tv_key, item.getKey());
        helper.setText(R.id.tv_value, item.getValue());
        helper.getView(R.id.margin_line).setVisibility(item.isShowLine() ? View.VISIBLE : View.GONE);
        helper.getView(R.id.iv_next).setVisibility(item.isShowNext() ? View.VISIBLE : View.GONE);
        helper.getView(R.id.toggle).setVisibility(item.isToogleButton() ? View.VISIBLE : View.GONE);
        if (item.isToogleButton()) {
            Switch toggle = helper.getView(R.id.toggle);
            toggle.setOnCheckedChangeListener(listener);
        }
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.listener = listener;
    }
}
