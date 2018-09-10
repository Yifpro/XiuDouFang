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

public class MoreRateDialog extends DialogFragment implements View.OnClickListener {

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
        assert getDialog().getWindow() != null;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_more_rate, container);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(String.format(getResources().getString(R.string.high_price_tip), getArguments().getString("rate")));
        view.findViewById(R.id.tv_cancel).setOnClickListener(this);
        view.findViewById(R.id.tv_continue).setOnClickListener(this);
        return view;
    }

    public void setOnSubmitClickListener(OnSumbitClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_continue:
                if (listener != null)
                    listener.onClick();
                break;
        }
    }

    interface OnSumbitClickListener {
        void onClick();
    }
}
