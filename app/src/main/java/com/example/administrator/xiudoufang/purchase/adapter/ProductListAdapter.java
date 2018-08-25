package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class ProductListAdapter extends BaseQuickAdapter<ProductListBean.ProductBean, BaseViewHolder> {

    public ProductListAdapter(@LayoutRes int layoutResId, @Nullable List<ProductListBean.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductListBean.ProductBean item) {
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
