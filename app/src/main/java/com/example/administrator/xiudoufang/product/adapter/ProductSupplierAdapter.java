package com.example.administrator.xiudoufang.product.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ProductDetailsBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductSupplierAdapter extends BaseQuickAdapter<ProductDetailsBean.CpTrxprcBean, BaseViewHolder> {

    public ProductSupplierAdapter(@LayoutRes int layoutResId, @Nullable List<ProductDetailsBean.CpTrxprcBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductDetailsBean.CpTrxprcBean item) {
        helper.setText(R.id.tv_supplier, item.getSuppname());
        helper.setText(R.id.tv_date, item.getTrxdate());
        helper.setText(R.id.tv_price, item.getPrice());
        helper.setText(R.id.tv_type, item.getTrxtype());
    }
}
