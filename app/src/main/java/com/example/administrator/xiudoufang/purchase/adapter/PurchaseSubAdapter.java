package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;

import java.util.List;

public class PurchaseSubAdapter extends BaseQuickAdapter<PurchaseListBean.PurchaseBean, BaseViewHolder> {

    public PurchaseSubAdapter(int layoutResId, @Nullable List<PurchaseListBean.PurchaseBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PurchaseListBean.PurchaseBean item) {
        int iconRes = 0;
        switch (item.getStatus_str()) {
            case "1":
                iconRes = R.mipmap.ic_note;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_bottom_right, "确认单据");
                break;
            case "2":
                iconRes = R.mipmap.ic_confirm_order;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_bottom_left, "反确认");
                helper.setText(R.id.tv_bottom_right, "确认收货");
                break;
            case "3":
                iconRes = R.mipmap.ic_goods_waiting;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.GONE);
                break;
            case "4":
                iconRes = R.mipmap.ic_unsubmit;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.VISIBLE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_bottom_left, "反确认");
                helper.setText(R.id.tv_bottom_right, "提交分店");
                break;
            case "5":
                iconRes = R.mipmap.ic_submit_order;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_bottom_right, "取消提交");
                break;
            case "6":
                iconRes = R.mipmap.ic_selected;
                helper.getView(R.id.tv_bottom_left).setVisibility(View.GONE);
                helper.getView(R.id.tv_bottom_right).setVisibility(View.VISIBLE);
                helper.setText(R.id.tv_bottom_right, "取消收货");
                break;
        }
        helper.setBackgroundRes(R.id.iv_icon, iconRes);
        helper.setText(R.id.tv_supplier, item.getCustomername());
        helper.setText(R.id.tv_order_num, item.getPuOrderNo());
        helper.setText(R.id.tv_date, item.getIssDate());
        helper.setText(R.id.tv_name, item.getCrman());
        helper.setText(R.id.tv_type, item.getWarehouse());
        helper.addOnClickListener(R.id.tv_bottom_left);
        helper.addOnClickListener(R.id.tv_bottom_right);
    }
}
