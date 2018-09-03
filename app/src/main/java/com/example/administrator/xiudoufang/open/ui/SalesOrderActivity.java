package com.example.administrator.xiudoufang.open.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.CustomPopWindow;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.open.adapter.SalesOrderAdapter;
import com.example.administrator.xiudoufang.open.logic.SalesOrderLogic;
import com.example.administrator.xiudoufang.purchase.ui.SupplierProductDetailsActivity;
import com.example.administrator.xiudoufang.purchase.ui.SupplierProductListActivity;
import com.example.administrator.xiudoufang.receipt.ui.CustomerListActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SalesOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String TAG = SalesOrderActivity.class.getSimpleName();
    public static final String SEARCH_ITEM = "search_item";
    public static final String CUSTOMER_DETAILS = "customer_details";
    public static final String C_ID = "c_id";
    private static final int RESULT_FOR_SALES_PRODUCT_LIST = 117;

    private CustomPopWindow mPopupWindow;
    private TextView mTvSelectCustomer;
    private TextView mTvTotalAmount;
    private TextView mTvTotalPrice;
    private EditText mEtFilter;
    private SwipeMenuRecyclerView mRecyclerView;

    private SalesOrderLogic mLogic;
    private ArrayList<SalesProductListBean.SalesProductBean> mList;
    private CustomerListBean.CustomerBean mCustomerDetailsBean;

    public static void start(Context context) {
        Intent intent = new Intent(context, SalesOrderActivity.class);
        context.startActivity(intent);
    }

    private SalesOrderAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sales_order;
    }

    @Override
    public void initView() {
        setTitle("销售单");
        mTvSelectCustomer = findViewById(R.id.tv_select_customer);
        mTvTotalAmount = findViewById(R.id.tv_total_amount);
        mTvTotalPrice = findViewById(R.id.tv_total_price);
        mEtFilter = findViewById(R.id.et_filter);
        mRecyclerView = findViewById(R.id.recycler_view);

        mEtFilter.setOnClickListener(this);
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
        mTvSelectCustomer.setOnClickListener(this);
        findViewById(R.id.iv_scan).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
        findViewById(R.id.tv_reopen).setOnClickListener(this);
        findViewById(R.id.tv_create_order).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_SALES_PRODUCT_LIST && data != null) {
            ArrayList<SalesProductListBean.SalesProductBean> items = data.getParcelableArrayListExtra(SalesProductListActivity.SELECTED_PRODUCT_LIST);
            if (mList == null) {
                mList = new ArrayList<>();
            } else {
                mList.clear();
            }
            mList.addAll(items);
            mAdapter.setNewData(items);
            statisticsData();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        CustomerListBean.CustomerBean bean = getIntent().getParcelableExtra(CUSTOMER_DETAILS);
        SalesProductListBean.SalesProductBean item = getIntent().getParcelableExtra(SalesProductDetailsActivity.SELECTED_ITEM);
        if (bean != null) {
            mTvSelectCustomer.setText(bean.getCustomername());
            TextView tvDebt = findViewById(R.id.tv_debt);
            tvDebt.setText(String.format(getString(R.string.debt_format), bean.getDebt()));
            mCustomerDetailsBean = bean;
        } else if (item != null) {
            if (mList == null)
                mList = new ArrayList<>();
            mList.add(item);
            mAdapter.setNewData(mList);
            statisticsData();
        }
    }

    @Override
    public void initData() {
        mLogic = new SalesOrderLogic();
        mAdapter = new SalesOrderAdapter(R.layout.layout_list_item_sales_order, mList);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem item = new SwipeMenuItem(SalesOrderActivity.this);
                item.setText("删除");
                item.setTextSize(16);
                item.setWidth(SizeUtils.dp2px(48));
                item.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                item.setBackgroundColor(Color.parseColor("#FF0000"));
                item.setTextColor(Color.parseColor("#FFFFFF"));
                rightMenu.addMenuItem(item);
            }
        };
        SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                mList.remove(adapterPosition);
                mAdapter.notifyItemChanged(adapterPosition);
                statisticsData();
            }
        };
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SalesOrderActivity.this, SalesProductDetailsActivity.class);
                intent.putExtra(SalesProductListActivity.SELECTED_ITEM, mList.get(position));
                intent.putExtra(SalesProductDetailsActivity.FROM_CLASS, TAG);
                startActivity(intent);
            }
        });
    }

    private void statisticsData() {
        int amount = 0;
        double result = 0;
        for (int i = 0; i < mList.size(); i++) {
            double totalPrice = Double.parseDouble(mList.get(i).getS_jiage2()) * Double.parseDouble(mList.get(i).getCp_qty());
            result += totalPrice;
            amount += Integer.valueOf(mList.get(i).getCp_qty());
        }
        DecimalFormat mFormat = new DecimalFormat("0.00");
        mTvTotalAmount.setText(String.valueOf(amount));
        mTvTotalPrice.setText(mFormat.format(result));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sales_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_type:
                if (mPopupWindow == null) {
                    View contentView = LayoutInflater.from(SalesOrderActivity.this).inflate(R.layout.layout_menu_open_bill, null);
                    mPopupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                            .setView(contentView)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .create();
                }
                mPopupWindow.showAsDropDown(findViewById(R.id.toolbar_type), -80, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_customer:
                Intent intent = new Intent(this, CustomerListActivity.class);
                intent.putExtra(CustomerListActivity.FROM_CLASS, TAG);
                startActivity(intent);
                break;
            case R.id.iv_scan:
                break;
            case R.id.et_filter:
                if (!mEtFilter.isCursorVisible())
                    mEtFilter.setCursorVisible(true);
                break;
            case R.id.tv_search:
                if (mCustomerDetailsBean == null) {
                    ToastUtils.show(this, "请先选择购买产品的客户");
                } else {
                    Intent i = new Intent(this, SalesProductListActivity.class);
                    i.putExtra(SEARCH_ITEM, mEtFilter.getText().toString());
                    i.putExtra(C_ID, TextUtils.isEmpty(mCustomerDetailsBean.getC_id()) ? "0" : mCustomerDetailsBean.getC_id());
                    startActivityForResult(i, RESULT_FOR_SALES_PRODUCT_LIST);
                }
                break;
        }
    }

    private class InnerEditActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                if (mCustomerDetailsBean == null) {
                    ToastUtils.show(SalesOrderActivity.this, "请先选择购买产品的客户");
                    return true;
                } else {
                    Intent i = new Intent(SalesOrderActivity.this, SalesProductListActivity.class);
                    i.putExtra(SEARCH_ITEM, mEtFilter.getText().toString());
                    i.putExtra(C_ID, TextUtils.isEmpty(mCustomerDetailsBean.getC_id()) ? "0" : mCustomerDetailsBean.getC_id());
                    startActivityForResult(i, RESULT_FOR_SALES_PRODUCT_LIST);
                    return true;
                }
            }
            return false;
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            EditText etAmount = (EditText) adapter.getViewByPosition(position, R.id.et_amount);
            TextView tvSums = (TextView) adapter.getViewByPosition(position, R.id.tv_sums);
            int i = Integer.parseInt(etAmount.getText().toString());
            switch (view.getId()) {
                case R.id.tv_reduce:
                    if (i > 0) {
                        i--;
                    }
                    break;
                case R.id.tv_add:
                    i++;
                    break;
            }
            etAmount.setText(String.valueOf(i));
            SalesProductListBean.SalesProductBean item = mList.get(position);
            item.setCp_qty(String.valueOf(i));
            double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(mList.get(position).getCp_qty());
            DecimalFormat mFormat = new DecimalFormat("0.00");
            tvSums.setText(mFormat.format(totalPrice));
            etAmount.setText(String.valueOf(i));
            statisticsData();
        }
    }
}
