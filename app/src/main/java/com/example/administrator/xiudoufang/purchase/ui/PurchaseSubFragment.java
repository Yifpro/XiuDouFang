package com.example.administrator.xiudoufang.purchase.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseSubAdapter;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class PurchaseSubFragment extends BaseFragment {

    private final int PAGE_NUM = 20;

    private RecyclerView mRecyclerView;

    private PurchaseLogic mLogic;
    private PurchaseSubAdapter mAdapter;
    private ArrayList<PurchaseListBean.PurchaseBean> mList;
    private int currentPage = 1;
    private String mType;
    private HashMap<String, String> mParams;

    public static PurchaseSubFragment newInstance(String type) {
        PurchaseSubFragment fragment = new PurchaseSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoad() {
        mLogic = new PurchaseLogic();
        mType = getArguments().getString("type");
        mAdapter = new PurchaseSubAdapter(R.layout.layout_list_item_purchase_sub, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadPurchaseList(false, false);
            }
        }, mRecyclerView);
        mAdapter.disableLoadMoreIfNotFullPage();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        loadPurchaseList(true, false);
    }

    private void loadPurchaseList(final boolean isFirst, final boolean isRefresh) {
        if (mParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mParams = new HashMap();
            mParams.put("iid", "");
            mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            mParams.put("PuOrderNo", "");
            mParams.put("suppno", "");
            mParams.put("Suppname", "");
            mParams.put("starttime", "");
            mParams.put("endtime", "");
            mParams.put("etadate", "");
            mParams.put("crman", "");
            mParams.put("queren_man", "");
            mParams.put("quyuno", "");
            mParams.put("quyu", "");
            mParams.put("fujia_memo", "");
            mParams.put("remark", "");
            mParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            mParams.put("status_str", mType);
            mParams.put("fromorder", "");
        }
        mParams.put("pagenum", String.valueOf(currentPage++));
        mParams.put("count", String.valueOf(PAGE_NUM));
        LoadingViewDialog.getInstance().show(getActivity());
        mLogic.requestPurchaseList(mParams, new JsonCallback<PurchaseListBean>() {
            @Override
            public void onSuccess(Response<PurchaseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                if (mList == null)
                    mList = new ArrayList<>();
                if (isRefresh) {
                    mAdapter.setNewData(response.body().getResults());
                } else {
                    mAdapter.addData(response.body().getResults());
                }
                if (!isFirst && mList.size() < PAGE_NUM) {
                    mAdapter.loadMoreEnd();
                } else {
                    mAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onError(Response<PurchaseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                if (!isFirst)
                    mAdapter.loadMoreFail();
                super.onError(response);
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
