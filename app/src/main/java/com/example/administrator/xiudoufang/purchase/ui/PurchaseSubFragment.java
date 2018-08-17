package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseSubFragment extends BaseFragment {

    private final int COUNT = 20;

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private PurchaseLogic mLogic;
    private PurchaseSubAdapter mAdapter;
    private List<PurchaseListBean.PurchaseBean> mList;
    private HashMap<String, String> mParams;
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
    protected void lazyLoad() {
        mLogic = new PurchaseLogic();
        assert getArguments() != null;
        mType = getArguments().getString("type");
        initParameters();
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
                intent.putExtra("order_id", mList.get(position).getIid());
                assert getActivity() != null;
                getActivity().startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        LoadingViewDialog.getInstance().show(getActivity());
        loadPurchaseList(false);
    }

    private void loadPurchaseList(final boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        }
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
