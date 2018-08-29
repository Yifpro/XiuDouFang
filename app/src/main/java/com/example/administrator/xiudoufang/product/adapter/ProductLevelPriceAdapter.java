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

public class ProductLevelPriceAdapter extends BaseQuickAdapter<ProductDetailsBean.CpLevelpriceBean, BaseViewHolder> {

    public ProductLevelPriceAdapter(@LayoutRes int layoutResId, @Nullable List<ProductDetailsBean.CpLevelpriceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductDetailsBean.CpLevelpriceBean item) {
        helper.setText(R.id.tv_level, item.getDengji());
        helper.setText(R.id.tv_price, item.getDengjiprice());
    }
}
