package com.example.administrator.xiudoufang.open.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.bean.OrderInfo;
import com.example.administrator.xiudoufang.bean.OrderInfo_1;
import com.example.administrator.xiudoufang.bean.OrderInfo_2;
import com.example.administrator.xiudoufang.bean.SettingItem;
import com.example.administrator.xiudoufang.common.utils.LogUtils;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class ConfirmOrderInfoAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int LAYOUT_TEXT = 0;
    public static final int LAYOUT_EDIT = 1;

    private List<MultiItemEntity> data;
    private DecimalFormat mFormat = new DecimalFormat("0.00");
    private boolean isInputable; //******** 余额支付是否可输入 ********

    public ConfirmOrderInfoAdapter(@Nullable List<MultiItemEntity> data) {
        super(data);
        this.data = data;
        addItemType(LAYOUT_TEXT, R.layout.layout_list_item_confirm_order_info_1);
        addItemType(LAYOUT_EDIT, R.layout.layout_list_item_confirm_order_info_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LAYOUT_TEXT:
                OrderInfo_1 info_1 = (OrderInfo_1) item;
                helper.setText(R.id.tv_key, info_1.getKey());
                helper.setText(R.id.tv_value, info_1.getValue());
                break;
            case LAYOUT_EDIT:
                final OrderInfo_2 info_2 = (OrderInfo_2) item;
                helper.setText(R.id.tv_key, info_2.getKey());
                final EditText etValue = helper.getView(R.id.et_value);
                if (((OrderInfo_1) data.get(data.size() - 2)).getKey().equals(info_2.getKey())) {
                    isInputable = Double.parseDouble(info_2.getValue()) > 0;
                }
                if (((OrderInfo_2) data.get(data.size() - 1)).getKey().equals(info_2.getKey())) {
                    if (!isInputable) {
                        etValue.setFocusable(false);
                        etValue.setFocusableInTouchMode(false);
                    }
                } else {
                    etValue.setFocusable(true);
                    etValue.setFocusableInTouchMode(true);
                }
                etValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etValue.setCursorVisible(true);
                    }
                });
                etValue.addTextChangedListener(new BaseTextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                        String value = editable.toString().trim();
                        info_2.setValue(value);
                        OrderInfo_2 otherFee = (OrderInfo_2) data.get(3);
                        if (otherFee.getKey().equals(info_2.getKey())) {
                            OrderInfo_1 yingshou = (OrderInfo_1) data.get(4);
                            double a = Double.parseDouble(yingshou.getValue()) + Double.parseDouble(value);
                            yingshou.setValue(mFormat.format(a));
                            OrderInfo_1 leiji = (OrderInfo_1) data.get(5);
                            double b = Double.parseDouble(leiji.getValue()) + Double.parseDouble(value);
                            leiji.setValue(mFormat.format(b));
                            notifyItemChanged(4);
                            notifyItemChanged(5);
                        }
                        OrderInfo_2 balancePayment = (OrderInfo_2) data.get(data.size() - 1);
                        if (balancePayment.getKey().equals(info_2.getKey())) {
                            balancePayment.setValue(value);
                        }
                    }
                });
                break;
        }
    }
}
