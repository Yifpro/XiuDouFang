package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.WarehouseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.adapter.WarehouseListAdapter;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class WarehouseListActivity extends AppCompatActivity implements IActivityBase {

    public static final String WAREHOUSE_ID = "warehouse_id";
    public static final String WAREHOUSE_NAME = "warehouse_name";

    private RecyclerView mRecyclerView;

    private NewPurchaseOrderLogic mLogic;
    private WarehouseListAdapter mAdapter;
    private List<WarehouseListBean.WarehouseBean> mList;
    private int mLastIndex;

    public static void start(Context context) {
        Intent intent = new Intent(context, WarehouseListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_warehouse_list;
    }

    @Override
    public void initView() {
        setTitle("仓位");
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mLogic = new NewPurchaseOrderLogic();
        mAdapter = new WarehouseListAdapter(R.layout.item_warehouse_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        LoadingViewDialog.getInstance().show(this);
        loadWarehouseList();
    }

    //******** 加载仓位列表 ********
    private void loadWarehouseList() {
        mLogic.requestWarehouseList(this, new JsonCallback<WarehouseListBean>() {
            @Override
            public void onSuccess(Response<WarehouseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mList = response.body().getHouselists();
                String warehouseId = getIntent().getStringExtra(WAREHOUSE_ID);
                if (!TextUtils.isEmpty(warehouseId)) {
                    mLastIndex = mList.indexOf(new WarehouseListBean.WarehouseBean(warehouseId));
                    mList.get(mLastIndex).setSelected(true);
                } else {
                    mList.get(0).setSelected(true);
                }
                mAdapter.setNewData(mList);
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra(WAREHOUSE_ID, mList.get(mLastIndex).getId());
        intent.putExtra(WAREHOUSE_NAME, mList.get(mLastIndex).getSn());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (mLastIndex != position) {
                mList.get(position).setSelected(true);
                mList.get(mLastIndex).setSelected(false);
                adapter.notifyItemChanged(mLastIndex);
                adapter.notifyItemChanged(position);
                mLastIndex = position;
            }
        }
    }
}
