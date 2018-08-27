package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseSubFragment extends BaseFragment {

    private final int COUNT = 20;
    public static final String ORDER_ID = "order_id";
    public static final String ITEM_STATUS = "item_status";
    public static final int RESULT_FOR_PURCHASE_DETAILS = 111;

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private PurchaseLogic mLogic;
    private PurchaseSubAdapter mAdapter;
    private List<PurchaseListBean.PurchaseBean> mList;
    private HashMap<String, String> mPurchaseListParams;
    private HashMap<String, String> mActionParams;
    private int mCurrentPage = 1;
    private String mType;

    public static PurchaseSubFragment newInstance(String type) {
        PurchaseSubFragment fragment = new PurchaseSubFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onEvent() {
        LoadingViewDialog.getInstance().show(getActivity());
        loadPurchaseList(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_PURCHASE_DETAILS && resultCode == Activity.RESULT_OK) {
            onEvent();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        HashMap<String, String> params = ((PurchaseActivity) getActivity()).mParams;
        if (params != null) {
            mType = getArguments().getString("type");
            mLogic = new PurchaseLogic();
            mList = new ArrayList<>();
            initParams();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                mPurchaseListParams.put(entry.getKey(), entry.getValue());
            }
            mAdapter = new PurchaseSubAdapter(R.layout.layout_list_item_purchase_sub, mList);
            mAdapter.bindToRecyclerView(mRecyclerView);
            mAdapter.setOnItemClickListener(new InnerItemClickListener());
            mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
            LoadingViewDialog.getInstance().show(getActivity());
            loadPurchaseList(true);
        }
    }

    @Override
    protected void lazyLoad() {
        mLogic = new PurchaseLogic();
        assert getArguments() != null;
        mType = getArguments().getString("type");
        mRefreshLayout.setOnRefreshListener(new InnerRefreshListener());
        mRefreshLayout.setOnLoadMoreListener(new InnerLoadMoreListener());
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new PurchaseSubAdapter(R.layout.layout_list_item_purchase_sub, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        if (((PurchaseActivity) getActivity()).mParams == null) {
            LoadingViewDialog.getInstance().show(getActivity());
            loadPurchaseList(false);
        }
    }

    private void loadPurchaseList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        if (mPurchaseListParams == null) initParams();
        mPurchaseListParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestPurchaseList(mPurchaseListParams, new JsonCallback<PurchaseListBean>() {
            @Override
            public void onSuccess(Response<PurchaseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<PurchaseListBean.PurchaseBean> temp = response.body().getResults();
                if (isRefresh) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(mList.size() < COUNT);
                } else {
                    if (mList == null)
                        mList = new ArrayList<>();
                    mList.addAll(temp);
                    mAdapter.addData(temp);
                    if (mList.size() < COUNT) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        mRefreshLayout.finishLoadMore();
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initParams() {
        mPurchaseListParams = new HashMap();
        mPurchaseListParams.put("iid", "");
        mPurchaseListParams.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        mPurchaseListParams.put("PuOrderNo", "");
        mPurchaseListParams.put("suppno", "");
        mPurchaseListParams.put("Suppname", "");
        mPurchaseListParams.put("starttime", "");
        mPurchaseListParams.put("endtime", "");
        mPurchaseListParams.put("etadate", "");
        mPurchaseListParams.put("crman", "");
        mPurchaseListParams.put("queren_man", "");
        mPurchaseListParams.put("quyuno", "");
        mPurchaseListParams.put("quyu", "");
        mPurchaseListParams.put("fujia_memo", "");
        mPurchaseListParams.put("remark", "");
        mPurchaseListParams.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        mPurchaseListParams.put("status_str", mType);
        mPurchaseListParams.put("fromorder", "");
        mPurchaseListParams.put("count", String.valueOf(COUNT));
    }

    @Override
    public void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_purchase_sub;
    }

    private class InnerRefreshListener implements OnRefreshListener {

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mRefreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadPurchaseList(true);
                }
            }, 2000);
        }
    }

    private class InnerLoadMoreListener implements OnLoadMoreListener {

        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mRefreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadPurchaseList(false);
                }
            }, 2000);
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(getActivity(), PurchaseDetailsActivity.class);
            intent.putExtra(ORDER_ID, mList.get(position).getIid());
            intent.putExtra(ITEM_STATUS, mList.get(position).getStatus_str());
            assert getActivity() != null;
            startActivityForResult(intent, RESULT_FOR_PURCHASE_DETAILS);
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            if (mActionParams == null) {
                SharedPreferences preferences = PreferencesUtils.getPreferences();
                mActionParams = new HashMap<>();
                mActionParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                mActionParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            }
            mActionParams.put("iid", mList.get(position).getIid());
            mActionParams.put("action", mLogic.getAction(view.getId(), mType));
            mLogic.requestActionForOrder(mActionParams, new JsonCallback<String>() {
                @Override
                public void onSuccess(Response<String> response) {
                    JSONObject jsonObject = JSONObject.parseObject(response.body());
                    if (!"1".equals(jsonObject.getString("messages"))) {
                        Toast.makeText(mActivity, jsonObject.getString("messages"), Toast.LENGTH_SHORT).show();
                    } else {
                        LoadingViewDialog.getInstance().show(getActivity());
                        loadPurchaseList(true);
                    }
                }
            });
        }
    }

}
