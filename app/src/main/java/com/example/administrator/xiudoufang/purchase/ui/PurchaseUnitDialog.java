package com.example.administrator.xiudoufang.purchase.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.StringPair;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseUnitAdapter;
import com.example.administrator.xiudoufang.purchase.adapter.SingleLineTextAdapter;

import java.util.ArrayList;

public class PurchaseUnitDialog extends DialogFragment {

    private OnItemClickListener listener;

    public static PurchaseUnitDialog newInstance(ArrayList<StringPair> list) {
        PurchaseUnitDialog f = new PurchaseUnitDialog();
        Bundle args = new Bundle();
        args.putParcelableArrayList("list", list);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getDialog().getWindow() != null;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_transfer_purchase_selector, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        final ArrayList<StringPair> list = getArguments().getParcelableArrayList("list");
        PurchaseUnitAdapter adapter = new PurchaseUnitAdapter(R.layout.layout_list_item_transfer_purchase, list);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (listener != null)
                    listener.onItemClick(position, list.get(position).getKey());
                dismiss();
            }
        });
        return view;
    }

    public void setOnItemChangedListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, String bilv);
    }
}
