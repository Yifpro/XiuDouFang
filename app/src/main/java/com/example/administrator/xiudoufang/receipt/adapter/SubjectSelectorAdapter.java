package com.example.administrator.xiudoufang.receipt.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SubjectBean;

import java.util.List;

public class SubjectSelectorAdapter extends BaseQuickAdapter<SubjectBean.AccounttypesBean, BaseViewHolder> {

    public SubjectSelectorAdapter(int layoutResId, @Nullable List<SubjectBean.AccounttypesBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectBean.AccounttypesBean item) {
        helper.setText(R.id.tv, item.getShow_name());
    }
}
