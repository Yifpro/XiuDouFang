package com.example.administrator.xiudoufang.open.adapter;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesOrderAdapter extends BaseItemDraggableAdapter<SalesProductListBean.SalesProductBean, BaseViewHolder> {

    private DecimalFormat mDecimalFormat = new DecimalFormat("0.00");

    public SalesOrderAdapter(int layoutResId, List<SalesProductListBean.SalesProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesProductListBean.SalesProductBean item) {
        GlideApp.with(mContext).load(StringUtils.PIC_SMALL_URL + item.getPhotourl()).error(R.mipmap.ic_icon).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_unit, item.getFactor() + item.getUnitname());
        helper.setText(R.id.tv_color, item.getYanse());
        if (!"1".equals(item.getZhekou())) {
            helper.setText(R.id.tv_discount, String.format(mContext.getString(R.string.discount_format), item.getZhekou()));
        } else {
            helper.setText(R.id.tv_discount, "");
        }
        double unitPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(item.getZhekou());
        TextView tvPrice = helper.getView(R.id.tv_price);
        tvPrice.setText(String.format(mContext.getString(R.string.amt_format), mDecimalFormat.format(unitPrice)));
        helper.setText(R.id.et_amount, item.getCp_qty());
        double amount = Double.valueOf(item.getCp_qty());
        helper.setText(R.id.tv_sums, String.format(mContext.getString(R.string.sum_format), mDecimalFormat.format(amount * unitPrice)));
        final EditText etValue = helper.getView(R.id.et_amount);
        etValue.addTextChangedListener(new BaseTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String result = editable.toString().trim();
                if (result.contains(".")) {
                    double d = Double.parseDouble(result) + 0.5;
                    etValue.setText(String.valueOf((int) d));
                }
            }
        });

        helper.addOnClickListener(R.id.et_amount);
        helper.addOnClickListener(R.id.tv_reduce);
        helper.addOnClickListener(R.id.tv_add);
    }
}
