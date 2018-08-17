package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.QuYuBean;
import com.example.administrator.xiudoufang.bean.SupplierListBean;

import java.util.List;

public class AreaListAdapter extends BaseQuickAdapter<QuYuBean, BaseViewHolder> {

    public AreaListAdapter(int layoutResId, @Nullable List<QuYuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuYuBean item) {
        helper.setText(R.id.tv_code, item.getCode());
        helper.setText(R.id.tv_name, item.getName());
    }
}
