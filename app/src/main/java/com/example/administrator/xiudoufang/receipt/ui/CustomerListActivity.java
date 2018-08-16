package com.example.administrator.xiudoufang.receipt.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.receipt.SoftKeyBoardListener;
import com.example.administrator.xiudoufang.receipt.adapter.CustomerListAdapter;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomerListActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;

    private CustomerListLogic mLogic;
    private ArrayList<CustomerBean.CustomerlistBean> mList;
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
        SoftKeyBoardListener.setListener(CustomerListActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
            }

            @Override
            public void keyBoardHide(int height) {
            }
        });
    }

    @Override
    public void initData() {
        mLogic = new CustomerListLogic();
        mAdapter  = new CustomerListAdapter(R.layout.layout_list_item_receivables, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CustomerListActivity.this, PaymentActivity.class);
                intent.putExtra("selected_item", mList.get(position));
                CustomerListActivity.this.startActivity(intent);
            }
        });
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

        loadCustomerList();
    }

    private void loadCustomerList() {
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        String dianid = jsonObject.getString("dianid");
        String dengjiValue = jsonObject.getString("dengji_value");
        String userid = jsonObject.getString("userid");
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", dianid);
        map.put("userdengji", dengjiValue);
        map.put("dqc_id", "0");
        map.put("search", "");
        map.put("pagenum", "0");
        map.put("count", "20");
        map.put("userid", userid);
        LoadingViewDialog.getInstance().show(this);
        mLogic.requestCustomerList(map, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                CustomerBean customerBean = JSONObject.parseObject(response.body(), CustomerBean.class);
                mList = new ArrayList<>();
                mAdapter.addData(customerBean.getCustomerlist());
            }
        });
    }
}
