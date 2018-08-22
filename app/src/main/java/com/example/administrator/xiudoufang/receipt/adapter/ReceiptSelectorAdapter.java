package com.example.administrator.xiudoufang.receipt.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.PayBean;

import java.util.List;

public class ReceiptSelectorAdapter extends BaseQuickAdapter<PayBean, BaseViewHolder> {

    private StringBuilder mBuilder;

    public ReceiptSelectorAdapter(int layoutResId, @Nullable List<PayBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PayBean item) {
        if (mBuilder == null)
            mBuilder = new StringBuilder();
        mBuilder.setLength(0);

        switch (item.getPayname()) {
            case "支付宝":
                mBuilder.append(item.getPayname().substring(0, 1)).append(" ").append(item.getName());
                break;
            case "现金支付":
                mBuilder.append(item.getName());
                break;
            default:
                String cardNum = "", bankName, payName = "";
                if (item.getNumber().length() > 4)
                    cardNum = item.getNumber().substring(item.getNumber().length() - 4, item.getNumber().length());
                if (item.getName().length() > 2) {
                    bankName = item.getName().substring(0, 1) + item.getName().substring(item.getName().length() - 1, item.getName().length());
                } else {
                    bankName = item.getName();
                }
                if (item.getPayname().length() > 0)
                    payName = item.getPayname().substring(0, 1);
                mBuilder.append(payName).append(" ").append(bankName).append(cardNum);
                break;
        }
        helper.setText(R.id.tv, mBuilder.toString());

        helper.addOnClickListener(R.id.tv);
    }
}
