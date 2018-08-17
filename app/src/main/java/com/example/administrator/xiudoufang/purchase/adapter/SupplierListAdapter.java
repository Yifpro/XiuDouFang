package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SupplierListBean;

import java.util.List;

public class SupplierListAdapter extends BaseQuickAdapter<SupplierListBean.SupplierBean, BaseViewHolder> {

    private StringBuilder mBuilder;

    public SupplierListAdapter(int layoutResId, @Nullable List<SupplierListBean.SupplierBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupplierListBean.SupplierBean item) {
        helper.setText(R.id.tv_name, item.getCustomername());
        helper.setText(R.id.tv_id, item.getCustomerno());
        if (mBuilder == null) {
            mBuilder = new StringBuilder();
        } else {
            mBuilder.setLength(0);
        }
        helper.setText(R.id.tv_address, mBuilder.append(item.getQuyuno()).append(" ").append(item.getQuyu()).toString());
    }
}
