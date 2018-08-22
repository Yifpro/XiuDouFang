package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.purchase.adapter.ExtraSelectorAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/8/20
 */

public class ImageSelectorDialog extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_extra_selector, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ArrayList<String> serverItems = new ArrayList<>();
        serverItems.add("拍照");
        serverItems.add("相册");
        serverItems.add("取消");
        ExtraSelectorAdapter adapter = new ExtraSelectorAdapter(R.layout.layout_list_item_extra_selector, serverItems);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                }
                dismiss();
            }
        });
        return view;
    }
}
