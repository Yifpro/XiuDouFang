package com.example.administrator.xiudoufang.open.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesOrderAdapter extends BaseItemDraggableAdapter<SalesProductListBean.SalesProductBean, BaseViewHolder> {

    private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

    public SalesOrderAdapter( int layoutResId, List<SalesProductListBean.SalesProductBean> data){
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesProductListBean.SalesProductBean item) {
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_unit, item.getFactor() + item.getUnitname());
        helper.setText(R.id.tv_price, item.getS_jiage2());
        helper.setText(R.id.et_amount, item.getCp_qty());
        double amount = Double.valueOf(item.getCp_qty());
        double price = Double.valueOf(item.getS_jiage2());
        helper.setText(R.id.tv_sums, mDecimalFormat.format(amount * price));
    }
}
