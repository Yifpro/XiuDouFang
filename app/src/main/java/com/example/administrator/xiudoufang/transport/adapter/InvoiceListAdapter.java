package com.example.administrator.xiudoufang.transport.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.InvoiceListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class InvoiceListAdapter extends BaseQuickAdapter<InvoiceListBean.InvoiceBean, BaseViewHolder> {

    public InvoiceListAdapter(@LayoutRes int layoutResId, @Nullable List<InvoiceListBean.InvoiceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InvoiceListBean.InvoiceBean item) {
        helper.setText(R.id.tv_invoice_no, item.getIssdocref());
        helper.setText(R.id.tv_order_sums, item.getShipto());
        helper.setText(R.id.tv_this_collection, item.getIssDate());
        helper.setText(R.id.tv_transport_num, item.getYunhao());
        helper.setVisible(R.id.iv_icon, !TextUtils.isEmpty(item.getKuaidi_pic()));
    }
}
