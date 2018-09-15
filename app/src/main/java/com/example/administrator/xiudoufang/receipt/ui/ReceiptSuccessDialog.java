package com.example.administrator.xiudoufang.receipt.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/14
 */

public class ReceiptSuccessDialog extends DialogFragment {

    public static ReceiptSuccessDialog newInstance(ArrayList<String> list) {
        ReceiptSuccessDialog dialog = new ReceiptSuccessDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", list);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(SizeUtils.dp2px(180), SizeUtils.dp2px(106));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_receipt_success, container);
        TextView tvReceiptAmount = view.findViewById(R.id.tv_receipt_amount);
        TextView tvDiscountAmount = view.findViewById(R.id.tv_discount_amount);
        ArrayList<String> list = getArguments().getStringArrayList("list");
        tvReceiptAmount.setText(String.format(getString(R.string.format_receipt_amount), list.get(0)));
        tvDiscountAmount.setText(String.format(getString(R.string.format_discount_amount), list.get(1)));
        return view;
    }

    public void onConfirm(View view) {
        dismiss();
    }
}
