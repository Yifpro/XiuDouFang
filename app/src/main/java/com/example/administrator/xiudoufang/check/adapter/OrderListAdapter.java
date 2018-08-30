package com.example.administrator.xiudoufang.check.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OrderListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderListAdapter extends BaseQuickAdapter<OrderListBean.OrderBean, BaseViewHolder> {

    public OrderListAdapter(@LayoutRes int layoutResId, @Nullable List<OrderListBean.OrderBean> data) {
       super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListBean.OrderBean item) {
        int imgRes;
        if ("0".equals(item.getQueren_status())) {
            helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
            helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_bottom_right, "确认订单");
            imgRes = R.mipmap.ic_note;
        } else if ("0".equals(item.getShipstatus()) && "0".equals(item.getPeihuo_status())) {
            helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
            helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_bottom_right, "反确认");
            imgRes = R.mipmap.ic_undistribution;
        } else if ("0".equals(item.getShipstatus()) && "1".equals(item.getPeihuo_status())) {
            helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
            helper.getView(R.id.tv_bottom_right).setVisibility(View.GONE);
            imgRes = R.mipmap.ic_distribution;
        } else {
            helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
            helper.getView(R.id.tv_bottom_right).setVisibility(View.GONE);
            imgRes = R.mipmap.ic_selected;
        }
        helper.setImageResource(R.id.iv_icon, imgRes);
        helper.setText(R.id.tv_supplier, item.getCustomername());
        helper.setText(R.id.tv_order_num, item.getSno());
        helper.setText(R.id.tv_date, item.getXiadanriqi());
        helper.setText(R.id.tv_name, String.format(mContext.getString(R.string.amt_format), item.getYingshou_amt()));

        helper.addOnClickListener(R.id.tv_bottom_left);
        helper.addOnClickListener(R.id.tv_bottom_right);
    }
}
