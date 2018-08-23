package com.example.administrator.xiudoufang.purchase.adapter;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SelectedProductAdapter extends BaseItemDraggableAdapter<ProductItem, BaseViewHolder> {

    private DecimalFormat mFormat = new DecimalFormat("0.00");

    public SelectedProductAdapter(int layoutResId, List<ProductItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ProductItem item) {
        GlideApp.with(mContext)
                .load(StringUtils.PIC_SMALL_URL + item.getPhotourl())
                .placeholder(R.mipmap.ic_icon)
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getProductNo());
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_company_amount, item.getFactor() + item.getUnit());
        helper.setText(R.id.tv_unit_price, item.getSinglePrice());
        helper.setText(R.id.tv_company_price, item.getUnitPrice());
        helper.setText(R.id.tv_amount, item.getAmount());
        helper.setText(R.id.tv_total_price, mFormat.format(Double.parseDouble(item.getUnitPrice()) * Double.parseDouble(item.getAmount())));

        helper.addOnClickListener(R.id.tv_reduce);
        helper.addOnClickListener(R.id.tv_add);
    }
}
