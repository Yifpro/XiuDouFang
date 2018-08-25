package com.example.administrator.xiudoufang.purchase.ui;

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

public class StopProduceDialog extends DialogFragment {

    public static StopProduceDialog newInstance(String rate) {
        StopProduceDialog dialog = new StopProduceDialog();
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
        View view = inflater.inflate(R.layout.fragment_stop_produce, container);
        TextView tvContent = view.findViewById(R.id.tv_content);
        tvContent.setText(R.string.stop_produce_tip);
        view.findViewById(R.id.tv_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }
}
