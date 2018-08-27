package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.WarehouseListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class WarehouseListAdapter extends BaseQuickAdapter<WarehouseListBean.WarehouseBean, BaseViewHolder> {

    public WarehouseListAdapter(@LayoutRes int layoutResId, @Nullable List<WarehouseListBean.WarehouseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WarehouseListBean.WarehouseBean item) {
        helper.getView(R.id.iv_select).setSelected(item.isSelected());
        helper.setText(R.id.tv_title, item.getSn());
    }
}
