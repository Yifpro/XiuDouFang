package com.example.administrator.xiudoufang.check.ui;

import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.OrderDetailsBean;
import com.example.administrator.xiudoufang.bean.OrderListBean;
import com.example.administrator.xiudoufang.check.adapter.OrderDetailsAdapter;
import com.example.administrator.xiudoufang.check.adapter.PopupOperateAdapter;
import com.example.administrator.xiudoufang.check.logic.OrderListLogic;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.CustomPopWindow;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderDetailsActivity extends AppCompatActivity implements IActivityBase {

    private TextView mTvCustomer;
    private TextView mTvOrderNo;
    private TextView mTvConfirmStatus;
    private TextView mTvCompleteStatus;
    private TextView mTvTime;
    private RecyclerView mRecyclerView;
    private TextView mTvOrderSums;
    private TextView mTvShouldCollected;
    private TextView mTvDiscountSums;
    private TextView mTvThisColletion;
    private OrderInfoDialog mOrderInfoDialog;

    private OrderListLogic mLogic;
    private OrderDetailsAdapter mAdapter;
    private List<OrderDetailsBean.OrderProductBean> mList;
    private OrderDetailsBean.OrderInfoBean mOrderInfoBean;
    private OrderListBean.OrderBean mOrderBean;
    private CustomPopWindow mPopupWindow;
    private HashMap<String, String> mActionParams;
    private int mStatus;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public void initView() {
        setTitle("订单详情");
        mTvCustomer = findViewById(R.id.tv_customer);
        mTvOrderNo = findViewById(R.id.tv_order_no);
        mTvConfirmStatus = findViewById(R.id.tv_confirm_status);
        mTvCompleteStatus = findViewById(R.id.tv_complete_status);
        mTvTime = findViewById(R.id.tv_time);
        mRecyclerView = findViewById(R.id.recycler_view);
        mTvOrderSums = findViewById(R.id.tv_order_sums);
        mTvShouldCollected = findViewById(R.id.tv_should_collected);
        mTvDiscountSums = findViewById(R.id.tv_discount_sums);
        mTvThisColletion = findViewById(R.id.tv_this_collection);
    }

    @Override
    public void initData() {
        mOrderBean = getIntent().getParcelableExtra(OrderListSubFragment.SELECTED_ITEM);
        mLogic = new OrderListLogic();
        mAdapter = new OrderDetailsAdapter(R.layout.layout_list_item_order_details, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        LoadingViewDialog.getInstance().show(this);
        loadOrderDetails();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_operate:
                showPopupWindow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupWindow() {
        if (mPopupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.layout_overflow_menu, null, false);
            RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);
            final ArrayList<String> list = new ArrayList<>();
            if ("0".equals(mOrderBean.getQueren_status())) {
                list.add("改单");
                list.add("确认订单");
                list.add("完成订单");
                mStatus = 0;
            } else if ("0".equals(mOrderBean.getShipstatus()) && "0".equals(mOrderBean.getPeihuo_status())) {
                list.add("反确认订单");
                list.add("完成订单");
                mStatus = 1;
            } else if ("0".equals(mOrderBean.getShipstatus()) && "1".equals(mOrderBean.getPeihuo_status())) {
                list.add("反确认订单");
                list.add("完成订单");
                mStatus = 2;
            } else {
                list.add("取消完成");
                mStatus = 3;
            }
            PopupOperateAdapter adapter = new PopupOperateAdapter(R.layout.layout_list_item_popup, list);
            adapter.bindToRecyclerView(recyclerView);
            adapter.setOnItemClickListener(new InnerPopupWindowItemClickListener());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            mPopupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                    .setView(contentView)
                    .setFocusable(true)
                    .setOutsideTouchable(true)
                    .create();
        }
        mPopupWindow.showAsDropDown(findViewById(R.id.toolbar_operate), -80, 0);
    }

    //******** 加载订单详情 ********
    private void loadOrderDetails() {
        mLogic.requestOrderDetails(this, mOrderBean.getIid(), mOrderBean.getC_id(), new JsonCallback<OrderDetailsBean>() {
            @Override
            public void onSuccess(Response<OrderDetailsBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mOrderInfoBean = response.body().getFx_orderlist().get(0);
                mTvCustomer.setText(mOrderInfoBean.getCustomername());
                mTvOrderNo.setText(mOrderInfoBean.getSno());
                mTvConfirmStatus.setText("1".equals(mOrderInfoBean.getStatus()) ? "已确认" : "未确认");
                mTvConfirmStatus.setTextColor("1".equals(mOrderInfoBean.getStatus()) ? ContextCompat.getColor(OrderDetailsActivity.this, R.color.green_40ff00) :
                        ContextCompat.getColor(OrderDetailsActivity.this, R.color.red));
                mTvCompleteStatus.setText("1".equals(mOrderInfoBean.getShipstatus()) ? "已完成" : "未完成");
                mTvCompleteStatus.setTextColor("1".equals(mOrderInfoBean.getShipstatus()) ? ContextCompat.getColor(OrderDetailsActivity.this, R.color.green_40ff00) :
                        ContextCompat.getColor(OrderDetailsActivity.this, R.color.red));
                mTvTime.setText(mOrderInfoBean.getCrtime());
                mTvOrderSums.setText(mOrderInfoBean.getS_amt());
                mTvShouldCollected.setText(mOrderInfoBean.getYingshou_amt());
                mTvDiscountSums.setText(mOrderInfoBean.getYouhuijine());
                mTvThisColletion.setText(mOrderInfoBean.getBenci_amt());
                List<OrderDetailsBean.OrderProductBean> temp = response.body().getFx_orderproduct();
                if (temp.size() > 0) {
                    if (mList == null) {
                        mList = new ArrayList<>();
                    } else {
                        mList.clear();
                    }
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                }
            }
        });
    }

    public void onClick(View view) {
        if (mOrderInfoBean == null) {
            ToastUtils.show(this, "暂无信息，请稍后重试");
        } else {
            if (mOrderInfoDialog == null) {
                mOrderInfoDialog = OrderInfoDialog.newInstance(mOrderInfoBean);
            }
            mOrderInfoDialog.show(getSupportFragmentManager(), "OrderInfoDialog");
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        }
    }

    private class InnerPopupWindowItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (mActionParams == null) {
                SharedPreferences preferences = PreferencesUtils.getPreferences();
                mActionParams = new HashMap<>();
                mActionParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                mActionParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            }
            mActionParams.put("iid", mList.get(position).getIid()); //******** 订单id ********
            mActionParams.put("action", getAction(position)); //******** 动作 ********
            mLogic.requestActionForOrder(OrderDetailsActivity.this, mActionParams, new JsonCallback<String>() {
                @Override
                public void onSuccess(Response<String> response) {
                    JSONObject jsonObject = JSONObject.parseObject(response.body());
                    if (!"1".equals(jsonObject.getString("messages"))) {
                        ToastUtils.show(OrderDetailsActivity.this, jsonObject.getString("messages"));
                    } else {
                        LoadingViewDialog.getInstance().show(OrderDetailsActivity.this);
                        loadOrderDetails();
                    }
                }
            });
        }
    }

    private String getAction(int position) {
        int action = 0;
        switch (mStatus) {
            case 0:
                action = position == 0 ? 5 : position == 1 ? 1 : 3;
                break;
            case 1:
                action = position == 0 ? 2 : 3;
                break;
            case 2:
                action = position == 0 ? 2 : 3;
                break;
            case 3:
                action = 4;
                break;
        }
        return String.valueOf(action);
    }
}
