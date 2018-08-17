package com.example.administrator.xiudoufang.receipt.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.CustomerListBean;

import java.util.List;

public class CustomerListAdapter extends BaseQuickAdapter<CustomerListBean.CustomerBean, BaseViewHolder> {

    public CustomerListAdapter(int layoutResId, @Nullable List<CustomerListBean.CustomerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerListBean.CustomerBean item) {
        if (!item.isSelected()) {
            helper.getView(R.id.relative_layout).setVisibility(View.GONE);
            return;
        }
        helper.setText(R.id.tv_name, item.getCustomername());
        helper.setText(R.id.tv_id, item.getCustomerno());
        helper.setText(R.id.tv_address, item.getQuyu());
    }
}
