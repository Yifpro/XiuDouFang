package com.example.administrator.xiudoufang.open.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesProductListAdapter  extends BaseQuickAdapter<SalesProductListBean.SalesProductBean, BaseViewHolder> {

    public SalesProductListAdapter(@LayoutRes int layoutResId, @Nullable List<SalesProductListBean.SalesProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesProductListBean.SalesProductBean item) {
        if (item.isShowSelect()) {
            helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
            if (item.isSelected()) {
                helper.getView(R.id.iv_select).setSelected(true);
                helper.getView(R.id.item_layout).setSelected(true);
            } else {
                helper.getView(R.id.iv_select).setSelected(false);
                helper.getView(R.id.item_layout).setSelected(false);
            }
        } else {
            helper.getView(R.id.iv_select).setVisibility(View.GONE);
        }
        GlideApp.with(mContext).load(StringUtils.PIC_SMALL_URL + item.getPhotourl()).error(R.mipmap.ic_icon).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_name, item.getStylename());

        helper.addOnClickListener(R.id.iv_select);
    }
}
