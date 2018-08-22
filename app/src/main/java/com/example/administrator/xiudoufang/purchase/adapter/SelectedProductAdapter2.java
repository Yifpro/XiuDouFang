package com.example.administrator.xiudoufang.purchase.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SelectedProductAdapter2 extends BaseItemDraggableAdapter<ProductListBean.ProductBean, BaseViewHolder> {

    private DecimalFormat mFormat = new DecimalFormat("0.00");

    public SelectedProductAdapter2(int layoutResId, List<ProductListBean.ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ProductListBean.ProductBean item) {
        Glide.with(mContext)
                .load(StringUtils.PIC_SMALL_URL + item.getPhotourl())
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_company_amount, item.getFactor() + item.getUnitname());
        helper.setText(R.id.tv_unit_price, item.getOrder_prc());
        helper.setText(R.id.tv_company_price, item.getS_jiage2());
        helper.setText(R.id.tv_amount, item.getCp_qty());
        helper.setText(R.id.tv_total_price, mFormat.format(Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(item.getCp_qty())));

        helper.addOnClickListener(R.id.tv_reduce);
        helper.addOnClickListener(R.id.tv_add);
    }
}
