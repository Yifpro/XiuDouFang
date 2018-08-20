package com.example.administrator.xiudoufang.purchase.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
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
        Glide.with(mContext)
                .load(StringUtils.PIC_SMALL_URL + item.getPhotourl())
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_company_amount, item.getFactor() + item.getUnitname());
        helper.setText(R.id.tv_unit_price, item.getOrder_prc());
        helper.setText(R.id.tv_company_price, item.getS_jiage2());
        final TextView tvAmount = helper.getView(R.id.tv_amount);
        tvAmount.setText(item.getCp_qty());
        setTotalPrice(helper, item);
        helper.getView(R.id.tv_reduce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(tvAmount.getText().toString());
                if (i > 0) {
                    i--;
                }
                tvAmount.setText(String.valueOf(i));
                item.setCp_qty(tvAmount.getText().toString());
                setTotalPrice(helper, item);
            }
        });
        helper.getView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Integer.parseInt(tvAmount.getText().toString());
                i++;
                tvAmount.setText(String.valueOf(i));
                item.setCp_qty(tvAmount.getText().toString());
                setTotalPrice(helper, item);
            }
        });
    }

    private void setTotalPrice(BaseViewHolder helper, ProductItem item) {
        double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(item.getCp_qty());
        helper.setText(R.id.tv_total_price, mFormat.format(totalPrice));
    }
}
