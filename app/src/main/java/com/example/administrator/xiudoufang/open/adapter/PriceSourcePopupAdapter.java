package com.example.administrator.xiudoufang.open.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class PriceSourcePopupAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PriceSourcePopupAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
    }
}
