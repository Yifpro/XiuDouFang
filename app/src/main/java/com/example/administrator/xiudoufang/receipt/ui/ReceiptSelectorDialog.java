package com.example.administrator.xiudoufang.receipt.ui;

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

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.PayBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.receipt.adapter.ReceiptSelectorAdapter;

import java.util.List;

public class ReceiptSelectorDialog extends DialogFragment {

    private OnItemChangedListener mListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_receipt_selector, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        final List<PayBean> list = JSONObject.parseArray(jsonObject.getJSONArray("pay").toJSONString(), PayBean.class);
        ReceiptSelectorAdapter adapter = new ReceiptSelectorAdapter(R.layout.layout_list_item_receipt_selector, list);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv) {
                    if (mListener != null)
                        mListener.onItemChanged(list.get(position), ((TextView) adapter.getViewByPosition(position, R.id.tv)).getText().toString());
                }
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public void setOnItemChangedListener(OnItemChangedListener listener) {
        mListener = listener;
    }

    public interface OnItemChangedListener {
        void onItemChanged(PayBean item, String content);
    }
}
