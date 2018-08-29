package com.example.administrator.xiudoufang.product.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ProductWeightBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductWeightAdapter extends BaseQuickAdapter<ProductWeightBean, BaseViewHolder> {

    public ProductWeightAdapter(@LayoutRes int layoutResId, @Nullable List<ProductWeightBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductWeightBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        TextView[] tvArr = {helper.getView(R.id.textview_1), helper.getView(R.id.textview_2), helper.getView(R.id.textview_3), helper.getView(R.id.textview_4)};
        for (int i = 0; i < item.getSubItem().size(); i++) {
            tvArr[i].setText(item.getSubItem().get(i));
        }
    }
}
