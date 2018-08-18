package com.example.administrator.xiudoufang.purchase.adapter;


import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.bean.ProductItem;

import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SelectedProductAdapter extends BaseItemDraggableAdapter<ProductItem, BaseViewHolder> {

    public SelectedProductAdapter(int layoutResId, List<ProductItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductItem item) {

    }
}
