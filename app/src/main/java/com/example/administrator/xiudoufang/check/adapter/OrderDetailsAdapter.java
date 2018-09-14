package com.example.administrator.xiudoufang.check.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.OrderDetailsBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/8/31
 */

public class OrderDetailsAdapter extends BaseQuickAdapter<OrderDetailsBean.OrderProductBean, BaseViewHolder> {

    private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

    public OrderDetailsAdapter(@LayoutRes int layoutResId, @Nullable List<OrderDetailsBean.OrderProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailsBean.OrderProductBean item) {
        GlideApp.with(mContext).load(StringUtils.PIC_URL + item.getPic()).error(R.mipmap.ic_error).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_unit, item.getBilv() + item.getUnitname());
        helper.setText(R.id.tv_status, "0".equals(item.getStatus()) ? "未完成":"1".equals(item.getStatus())?"已完成":"已关闭");
        helper.setText(R.id.tv_price, item.getS_jiage2());
        helper.setText(R.id.tv_amount, item.getCp_qty());
        double price = Double.valueOf(item.getS_jiage2());
        double amount = Double.valueOf(item.getCp_qty());
        helper.setText(R.id.tv_sum, String.format(mContext.getString(R.string.sum_format), mDecimalFormat.format(price * amount)));
        helper.setText(R.id.tv_distribution_amount, String.format(mContext.getString(R.string.ph_amt_format), item.getPh_qty()));

        helper.addOnClickListener(R.id.iv_icon);
    }
}
