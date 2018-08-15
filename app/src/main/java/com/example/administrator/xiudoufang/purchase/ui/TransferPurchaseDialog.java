package com.example.administrator.xiudoufang.purchase.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.purchase.adapter.TransferPurchaseAdapter;

import java.util.ArrayList;

public class TransferPurchaseDialog extends DialogFragment {

    private OnItemChangedListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_transfer_purchase_selector, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ArrayList<String> items = new ArrayList<>();
        items.add("全部");
        items.add("是");
        items.add("否");
        TransferPurchaseAdapter adapter = new TransferPurchaseAdapter(R.layout.layout_list_item_transfer_purchase, items);
        adapter.bindToRecyclerView(recyclerView);
        View header = View.inflate(getActivity(), R.layout.layout_list_header_transfer_purchase, null);
        ((TextView) header.findViewById(R.id.tv)).setText("请选择");
        adapter.addHeaderView(header);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (listener != null)
                    listener.onItemChanged(position);
            }
        });
        return view;
    }

    public void setOnItemChangedListener(OnItemChangedListener listener) {
        this.listener = listener;
    }

    interface OnItemChangedListener {
        void onItemChanged(int position);
    }
}
