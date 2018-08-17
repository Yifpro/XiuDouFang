package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.WarehouseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class WarehouseListActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;

    private NewPurchaseOrderLogic mLogic;
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
        final WarehouseListAdapter adapter = new WarehouseListAdapter(R.layout.layout_list_item_warehouse_list, mList);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mLastIndex != position) {
                    mList.get(position).setSelcted(true);
                    mList.get(mLastIndex).setSelcted(false);
                    adapter.notifyItemChanged(mLastIndex);
                    adapter.notifyItemChanged(position);
                    mLastIndex = position;
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoadingViewDialog.getInstance().show(this);
        mLogic.requestWarehouseList(new JsonCallback<WarehouseListBean>() {
            @Override
            public void onSuccess(Response<WarehouseListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mList = response.body().getHouselists();
                mList.get(getIntent().getIntExtra("index", 0)).setSelcted(true);
                adapter.setNewData(mList);
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("warehouse_id", mList.get(mLastIndex).getId());
        intent.putExtra("warehouse_name", mList.get(mLastIndex).getSn());
        setResult(Activity.RESULT_OK, intent);
    }
}
