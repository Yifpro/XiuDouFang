package com.example.administrator.xiudoufang.login.ui;

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
import com.example.administrator.xiudoufang.bean.ServerItem;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.login.adapter.ServerSelectorAdapter;

import java.util.ArrayList;

public class ServerSelectorDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_server_selector, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ArrayList<ServerItem> serverItems = new ArrayList<>();
        serverItems.add(new ServerItem(0, R.drawable.bg_item_server_selector, "雅秀"));
        serverItems.add(new ServerItem(0, R.drawable.bg_item_server_selector, "新之款"));
        serverItems.add(new ServerItem(R.color.bg_item_cancel_text_color_selector, R.drawable.bg_item_server_selector, "自动"));
        ServerSelectorAdapter adapter = new ServerSelectorAdapter(R.layout.layout_list_item_server_selector, serverItems);
        adapter.bindToRecyclerView(recyclerView);
        View header = View.inflate(getActivity(), R.layout.layout_list_header_server_selector, null);
        ((TextView) header.findViewById(R.id.tv)).setText(R.string.login_tip);
        adapter.addHeaderView(header);
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
                    case 2:
                        break;
                }
                dismiss();
            }
        });
        return view;
    }
}
