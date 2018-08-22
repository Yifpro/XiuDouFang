package com.example.administrator.xiudoufang.purchase.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;

public class MoreRateDialog extends DialogFragment {

    private OnSumbitClickListener listener;

    public static MoreRateDialog newInstance(String rate) {
        MoreRateDialog dialog = new MoreRateDialog();
        Bundle bundle = new Bundle();
        bundle.putString("rate", rate);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_more_rate, container);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(String.format(getResources().getString(R.string.high_price_tip), getArguments().getString("rate")));
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        view.findViewById(R.id.tv_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick();
            }
        });
        return view;
    }

    public void setOnSubmitClickListener(OnSumbitClickListener listener) {
        this.listener = listener;
    }

    interface OnSumbitClickListener {
        void onClick();
    }
}
