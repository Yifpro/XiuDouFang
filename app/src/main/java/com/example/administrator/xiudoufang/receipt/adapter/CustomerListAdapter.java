package com.example.administrator.xiudoufang.receipt.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.CustomerBean;

import java.util.List;

public class CustomerListAdapter extends BaseQuickAdapter<CustomerBean.CustomerlistBean, BaseViewHolder> {

    public CustomerListAdapter(int layoutResId, @Nullable List<CustomerBean.CustomerlistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerBean.CustomerlistBean item) {
        helper.setText(R.id.tv_name, item.getCustomername());
        helper.setText(R.id.tv_id, item.getCustomerno());
        helper.setText(R.id.tv_address, item.getQuyu());
    }
}
