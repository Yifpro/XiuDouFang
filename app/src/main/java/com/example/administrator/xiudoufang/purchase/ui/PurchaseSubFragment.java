package com.example.administrator.xiudoufang.purchase.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.bean.PurchaseItem;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseSubAdapter;

import java.util.ArrayList;

public class PurchaseSubFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private TextView mTv;

    public static PurchaseSubFragment newInstance(int type) {
        PurchaseSubFragment fragment = new PurchaseSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    public void initData() {
        ArrayList<PurchaseItem> list = new ArrayList<>();
        PurchaseSubAdapter adapter = new PurchaseSubAdapter(R.layout.layout_list_item_purchase_sub, list);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    protected void lazyLoad() {
        LogUtils.e("lazyLoad->"+getArguments().getInt("type")+"");
        if (mTv == null) {
            mTv.setText("null");
        } else {
            mTv.setText(getArguments().getInt("type")+"");
        }
    }

    @Override
    public void initView(View view) {
        mTv = view.findViewById(R.id.tv);
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_purchase_sub;
    }
}
