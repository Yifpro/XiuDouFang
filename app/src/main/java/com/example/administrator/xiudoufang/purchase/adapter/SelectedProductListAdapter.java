package com.example.administrator.xiudoufang.purchase.adapter;


import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
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

public class SelectedProductListAdapter extends BaseItemDraggableAdapter<ProductItem, BaseViewHolder> {

    private String mStatus;
    private DecimalFormat mFormat = new DecimalFormat("0.00");

    public SelectedProductListAdapter(int layoutResId, List<ProductItem> data, String status) {
        super(layoutResId, data);
        mStatus = status;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ProductItem item) {
        GlideApp.with(mContext).load(StringUtils.PIC_URL + item.getPhotourl()).error(R.mipmap.ic_error).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_company_amount, item.getFactor() + item.getUnitname());
        helper.setText(R.id.tv_single_price, item.getOrder_prc());
        helper.setText(R.id.tv_unit_price, item.getS_jiage2());
        helper.setText(R.id.tv_amount, item.getCp_qty());
        helper.setText(R.id.tv_total_price, mFormat.format(Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(item.getCp_qty())));

        if (!"1".equals(mStatus)) {
            String statusstr = item.getStatusstr();
            if (!TextUtils.isEmpty(statusstr)) {
                helper.getView(R.id.tv_discount_sums).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_discount_sums, statusstr);
                if ("未收货".equals(statusstr)) {
                    helper.setTextColor(R.id.tv_discount_sums, ContextCompat.getColor(mContext, R.color.red));
                } else if ("已收货".equals(statusstr)) {
                    helper.setTextColor(R.id.tv_discount_sums, ContextCompat.getColor(mContext, R.color.orange_ffae33));
                } else if ("关闭".equals(statusstr)) {
                    helper.setTextColor(R.id.tv_discount_sums, ContextCompat.getColor(mContext, R.color.green_40ff00));
                }
            } else {
                helper.getView(R.id.tv_discount_sums).setVisibility(View.GONE);
            }
            helper.setText(R.id.tv_receive_amount, String.format(mContext.getResources().getString(R.string.already_receive), item.getRcvamt()));
        } else {
            helper.getView(R.id.tv_discount_sums).setVisibility(View.GONE);
            helper.getView(R.id.tv_receive_amount).setVisibility(View.GONE);
        }

        helper.addOnClickListener(R.id.iv_icon);
        helper.addOnClickListener(R.id.tv_reduce);
        helper.addOnClickListener(R.id.tv_add);
    }
}
