package com.example.administrator.xiudoufang.receivables;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;

    private CustomerListLogic mLogic;
    private List<CustomerBean.CustomerlistBean> mList;
    private CustomerListAdapter mAdapter;
    private RefreshLayout mRefreshLayout;

    public static void start(Context context) {
        Intent intent = new Intent(context, CustomerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_list;
    }

    @Override
    public void initView() {
        setTitle("客户列表");
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mLogic = new CustomerListLogic();
        mAdapter = newInstanceAdapter();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                }, 2000);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readLoginInfo(StringUtils.LOGIN_INFO));
        String dianid = jsonObject.getString("dianid");
        String dengjiValue = jsonObject.getString("dengji_value");
        String userid = jsonObject.getString("userid");
        String[] arr = {dianid, dengjiValue, "0", "", "0", "20", userid};
        LoadingViewDialog.getInstance().show(this);
        mLogic.requestCustomerList(arr, new JsonCallback<CustomerBean>() {
            @Override
            public void onSuccess(Response<CustomerBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mList = new ArrayList<>();
                mList.addAll(response.body().getCustomerlist());
                mAdapter = newInstanceAdapter();
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private CustomerListAdapter newInstanceAdapter() {
        CustomerListAdapter adapter = new CustomerListAdapter(R.layout.layout_list_item_receivables, mList);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("selected_item", mList.get(position));
                PaymentActivity.start(CustomerListActivity.this, bundle);
            }
        });
        return adapter;
    }
}
