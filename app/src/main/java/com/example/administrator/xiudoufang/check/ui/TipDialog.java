package com.example.administrator.xiudoufang.check.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.receipt.ui.ReceiptSuccessDialog;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/15
 */

public class TipDialog extends DialogFragment {

    public static TipDialog newInstance(ArrayList<String> list) {
        TipDialog dialog = new TipDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("list", list);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(SizeUtils.dp2px(320), SizeUtils.dp2px(90));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_tip, container);
        TextView tvTip = view.findViewById(R.id.tv_tip);
        TextView tvGoodsNo = view.findViewById(R.id.tv_goods_no);
        ArrayList<String> list = getArguments().getStringArrayList("list");
        if (!TextUtils.isEmpty(list.get(0))) tvTip.setText(list.get(0));
        if (!TextUtils.isEmpty(list.get(1))) tvGoodsNo.setText(list.get(1));
        return view;
    }
}
