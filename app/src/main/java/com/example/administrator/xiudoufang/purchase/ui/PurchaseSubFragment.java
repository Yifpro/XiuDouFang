package com.example.administrator.xiudoufang.purchase.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.bean.PurchaseItem;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseSubAdapter;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

public class PurchaseSubFragment extends BaseFragment {

    private RecyclerView mRecyclerView;

    private PurchaseLogic mLogic;
    private int currentPage = 1;
    private String mType;

    public static PurchaseSubFragment newInstance(String type) {
        PurchaseSubFragment fragment = new PurchaseSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
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
        mLogic = new PurchaseLogic();
        mType = getArguments().getString("type");
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HttpParams params = new HttpParams();
        params.put("iid ", "");
        params.put("dianid ", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("PuOrderNo ", "");
        params.put("suppno ", "");
        params.put("Suppname ", "");
        params.put("starttime ", "");
        params.put("endtime ", "");
        params.put("etadate ", "");
        params.put("crman ", "");
        params.put("queren_man", "");
        params.put("quyuno ", "");
        params.put("quyu ", "");
        params.put("fujia_memo", "");
        params.put("remark ", "");
        params.put("userid ", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("pagenum ", String.valueOf(currentPage++));
        params.put("count ", "20");
        params.put("status_str", getArguments().getString("type"));
        params.put("fromorder", "");
        LogUtils.e("params -> "+params.toString());
        mLogic.requestPurchaseList(params, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("body -> " + response.body());
            }
        });
    }

    @Override
    public void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_purchase_sub;
    }
}
