package com.example.administrator.xiudoufang.check.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OrderQueryBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderQueryAdapter  extends BaseQuickAdapter<OrderQueryBean, BaseViewHolder> {

    public OrderQueryAdapter(int layoutResId, @Nullable List<OrderQueryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderQueryBean item) {
        helper.setText(R.id.tv, item.getName());
    }
}
