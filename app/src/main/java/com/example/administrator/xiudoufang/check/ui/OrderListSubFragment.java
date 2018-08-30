package com.example.administrator.xiudoufang.check.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.bean.OrderFilter;
import com.example.administrator.xiudoufang.bean.OrderListBean;
import com.example.administrator.xiudoufang.check.adapter.OrderListAdapter;
import com.example.administrator.xiudoufang.check.logic.OrderListLogic;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderListSubFragment extends BaseFragment {

    private final int COUNT = 20;

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView mTvTotalValue;

    private OrderListLogic mOrderListLogic;
    private PurchaseLogic mPurchaseLogic;
    private HashMap<String, String> mParams;
    private HashMap<String, String> mActionParams;
    private OrderListAdapter mAdapter;
    private List<OrderListBean.OrderBean> mList;
    private int mCurrentPage = 1;
    private int mType;

    public static OrderListSubFragment newInstance(int type) {
        OrderListSubFragment fragment = new OrderListSubFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_check_order_sub;
    }

    @Override
    public void initView(View view) {
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mTvTotalValue = view.findViewById(R.id.tv_total_value);
    }

    @Override
    public void onRefresh() {
        OrderFilter filter = ((OrderListActivity) getActivity()).mOrderFilter;
        mType = getArguments().getInt("type");
        mList = new ArrayList<>();
        mOrderListLogic = new OrderListLogic();
        mPurchaseLogic = new PurchaseLogic();
        initParams();
        mParams.put("iid", TextUtils.isEmpty(filter.getNo()) ? "0" : filter.getNo());
        mParams.put("customername", filter.getCustomer());
        mParams.put("starttime", filter.getStartTime());
        mParams.put("endtime", filter.getEndTime());
        mParams.put("dingdanleixing", filter.getOrderType());
        mParams.put("huoyunleixing", "无".equals(filter.getTransportType()) ? "" : filter.getTransportType());
        mParams.put("daifa", filter.getProxyOrder());
        mAdapter = new OrderListAdapter(R.layout.layout_list_item_purchase_sub, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        LoadingViewDialog.getInstance().show(getActivity());
        loadOrderList(true);
    }

    @Override
    protected void lazyLoad() {
        mType = getArguments().getInt("type");
        mOrderListLogic = new OrderListLogic();
        mPurchaseLogic = new PurchaseLogic();
        mRefreshLayout.setOnRefreshListener(new InnerRefreshListener());
        mRefreshLayout.setOnLoadMoreListener(new InnerLoadMoreListener());
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new OrderListAdapter(R.layout.layout_list_item_purchase_sub, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mList = new ArrayList<>();
        if (((OrderListActivity) getActivity()).mOrderFilter == null) {
            LoadingViewDialog.getInstance().show(getActivity());
            loadOrderList(true);
        }
    }

    private void loadOrderList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        if (mParams == null) initParams();
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mOrderListLogic.requestOrderList(mParams, new JsonCallback<OrderListBean>() {
            @Override
            public void onSuccess(Response<OrderListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<OrderListBean.OrderBean> temp = response.body().getFx_ordermainlists();
                if (isRefresh) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(mList.size() < COUNT);
                } else {
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

    private void initParams() {
        mParams = new HashMap<>();
        mParams.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        mParams.put("c_id", "0");
        mParams.put("iid", "0");
        mParams.put("customername", "");
        mParams.put("starttime", StringUtils.getCurrentTime());
        mParams.put("endtime", StringUtils.getCurrentTime());
        mParams.put("dingdanleixing", "");
        mParams.put("huoyunleixing", "");
        mParams.put("daifa", "");
        mParams.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        mParams.put("queren_status", mType == 0 ? "0" : mType == 4 ? "" : "1");
        mParams.put("shipstatus", mType == 3 ? "1" : "");
        mParams.put("peihuo_status", mType == 1 ? "0" : mType == 2 ? "1" : "");
        mParams.put("count", String.valueOf(COUNT));
    }

    private class InnerRefreshListener implements OnRefreshListener {

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mRefreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadOrderList(true);
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
                    loadOrderList(false);
                }
            }, 2000);
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(getActivity(), OrderDetailsActivity.class);
            startActivity(intent);
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            LogUtils.e("list size -> " + mList.size());
            /*if (mActionParams == null) {
                SharedPreferences preferences = PreferencesUtils.getPreferences();
                mActionParams = new HashMap<>();
                mActionParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                mActionParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            }
            mActionParams.put("iid", mList.get(position).getIid());
            mActionParams.put("action", mType == 0 ? "1" : "2");
            mPurchaseLogic.requestActionForOrder(mActionParams, new JsonCallback<String>() {
                @Override
                public void onSuccess(Response<String> response) {
                    JSONObject jsonObject = JSONObject.parseObject(response.body());
                    if (!"1".equals(jsonObject.getString("messages"))) {
                        Toast.makeText(mActivity, jsonObject.getString("messages"), Toast.LENGTH_SHORT).show();
                    } else {
                        LoadingViewDialog.getInstance().show(getActivity());
                        loadOrderList(true);
                    }
                }
            });*/
        }
    }
}
