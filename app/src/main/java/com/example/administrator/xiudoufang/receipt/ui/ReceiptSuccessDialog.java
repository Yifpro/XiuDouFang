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

import com.example.administrator.xiudoufang.R;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_receipt_success, container);
        return view;
    }
}
