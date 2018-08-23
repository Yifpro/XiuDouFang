package com.example.administrator.xiudoufang.purchase.ui;

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
import com.example.administrator.xiudoufang.common.utils.LogUtils;
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

public class PurchaseSubFragment extends BaseFragment {

    private final int COUNT = 20;
    public static final String ORDER_ID = "order_id";
    public static final String ITEM_STATUS = "item_status";

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private PurchaseLogic mLogic;
    private PurchaseSubAdapter mAdapter;
    private List<PurchaseListBean.PurchaseBean> mList;
    private HashMap<String, String> mParams;
    private int mCurrentPage;
    private String mType;
    private HashMap<String, String> mMap;

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
        assert getArguments() != null;
        mType = getArguments().getString("type");
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadPurchaseList(true);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadPurchaseList(false);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new PurchaseSubAdapter(R.layout.layout_list_item_purchase_sub, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), PurchaseDetailsActivity.class);
                LogUtils.e("iid -> " + mList.get(position).getIid());
                intent.putExtra(ORDER_ID, mList.get(position).getIid());
                intent.putExtra(ITEM_STATUS, mList.get(position).getStatus_str());
                assert getActivity() != null;
                getActivity().startActivity(intent);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mMap == null) {
                    SharedPreferences preferences = PreferencesUtils.getPreferences();
                    mMap = new HashMap<>();
                    mMap.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                    mMap.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
                }
                mMap.put("iid", mList.get(position).getIid());
                String action = "";
                switch (view.getId()) {
                    case R.id.tv_bottom_left:
                        switch (mType) {
                            case "1":
                                break;
                            case "2":
                                action = "2";
                                break;
                            case "3":
                                break;
                            case "4":
                                action = "2";
                                break;
                            case "5":
                                break;
                            case "6":
                                break;
                        }
                        break;
                    case R.id.tv_bottom_right:
                        switch (mType) {
                            case "1":
                                action = "1";
                                break;
                            case "2":
                                action = "3";
                                break;
                            case "3":
                                break;
                            case "4":
                                action = "5";
                                break;
                            case "5":
                                action = "6";
                                break;
                            case "6":
                                action = "4";
                                break;
                        }
                        break;
                }
                mMap.put("action", action);
                mLogic.requestActionForOrder(mMap, new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        if (!"0".equals(jsonObject.getString("status"))) {
                            Toast.makeText(mActivity, jsonObject.getString("messages"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        LoadingViewDialog.getInstance().show(getActivity());
        mCurrentPage = 1;
        loadPurchaseList(false);
    }

    private void loadPurchaseList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        if (mParams == null) initParameters();
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestPurchaseList(mParams, new JsonCallback<PurchaseListBean>() {
            @Override
            public void onSuccess(Response<PurchaseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                if (isRefresh) {
                    mAdapter.setNewData(response.body().getResults());
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(false);
                } else {
                    if (mList == null)
                        mList = new ArrayList<>();
                    mList = response.body().getResults();
                    mAdapter.addData(mList);
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
    private void initParameters() {
        mParams = new HashMap();
        mParams.put("iid", "");
        mParams.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
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
        mParams.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        mParams.put("status_str", mType);
        mParams.put("fromorder", "");
        mParams.put("count", String.valueOf(COUNT));
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
}
