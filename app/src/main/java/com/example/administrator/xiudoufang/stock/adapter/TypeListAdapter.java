package com.example.administrator.xiudoufang.stock.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.TypeListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class TypeListAdapter extends BaseQuickAdapter<TypeListBean.TypeBean, BaseViewHolder> {

    public TypeListAdapter(@LayoutRes int layoutResId, @Nullable List<TypeListBean.TypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TypeListBean.TypeBean item) {
        helper.getView(R.id.iv_select).setSelected(item.isSelected());
        helper.setText(R.id.tv_title, item.getName());

        helper.addOnClickListener(R.id.iv_select);
    }
}
