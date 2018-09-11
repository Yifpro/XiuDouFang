package com.example.administrator.xiudoufang.open.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.administrator.xiudoufang.open.adapter.PriceSourcePopupAdapter;
import com.example.administrator.xiudoufang.open.adapter.SalesOrderAdapter;
import com.example.administrator.xiudoufang.receipt.ui.CustomerListActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class SalesOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String TAG = SalesOrderActivity.class.getSimpleName();
    public static final String C_ID = "c_id"; //******** 获取当前客户 id ********
    public static final String SEARCH_ITEM = "search_item"; //******** 获取过滤条件 ********
    public static final String CUSTOMER_DETAILS = "customer_details"; //******** 获取选择客户 ********
    public static final String RESULT_CUSTOMER = "submit_customer"; //******** 获取选定客户 ********
    public static final String RESULT_PRODUCT_LIST = "submit_product_list"; //******** 获取选定产品 ********
    private static final int RESULT_FOR_SALES_PRODUCT_LIST = 117; //******** 产品列表请求码 ********
    private static final int RESULT_FOR_PRODUCT_INFO_CHANGE = 121;
    private static final int RESULT_FOR_CREATE_ORDER = 122;

    private CustomPopWindow mPriceTypePopup;
    private TextView mTvCustomer;
    private EditText mEtFilter;
    private SwipeMenuRecyclerView mRecyclerView;
    private TextView mTvTotalAmount;
    private TextView mTvTotalPrice;
    private TextView mTvDebt;

    private SalesOrderAdapter mAdapter;
    private ArrayList<SalesProductListBean.SalesProductBean> mList;
    private CustomerListBean.CustomerBean mCustomerBean;
    private int mLastIndex;

    public static void start(Context context) {
        Intent intent = new Intent(context, SalesOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sales_order;
    }

    @Override
    public void initView() {
        setTitle("销售单");
        mTvCustomer = findViewById(R.id.tv_customer);
        mEtFilter = findViewById(R.id.et_filter);
        mTvTotalAmount = findViewById(R.id.tv_total_amount);
        mTvTotalPrice = findViewById(R.id.tv_total_price);
        mRecyclerView = findViewById(R.id.recycler_view);
        mTvDebt = findViewById(R.id.tv_debt);

        mEtFilter.setOnClickListener(this);
        mTvCustomer.setOnClickListener(this);
        findViewById(R.id.iv_scan).setOnClickListener(this);
        findViewById(R.id.tv_search).setOnClickListener(this);
        findViewById(R.id.tv_reopen).setOnClickListener(this);
        findViewById(R.id.tv_create_order).setOnClickListener(this);
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_SALES_PRODUCT_LIST) {
            if (data != null) {
                //******** 返回产品列表选择结果 ********
                ArrayList<SalesProductListBean.SalesProductBean> items = data.getParcelableArrayListExtra(SalesProductListActivity.SELECTED_PRODUCT_LIST);
                if (mList == null) {
                    mList = new ArrayList<>();
                } else {
                    mList.clear();
                }
                mList.addAll(items);
                mAdapter.setNewData(items);
                calculateAmountAndSums();
            } else {
                mEtFilter.setText("");
            }
        } else if (requestCode == RESULT_FOR_PRODUCT_INFO_CHANGE && data != null) {
            //******** 更改信息后的产品 ********
            SalesProductListBean.SalesProductBean bean = data.getParcelableExtra(SalesProductDetailsActivity.SELECTED_ITEM);
            mList.remove(mLastIndex);
            mList.add(mLastIndex, bean);
            mAdapter.setNewData(mList);
            calculateAmountAndSums();
        } else if (requestCode == RESULT_FOR_CREATE_ORDER && data != null && mList != null) {
            mList.clear();
            ArrayList<SalesProductListBean.SalesProductBean> temp = data.getParcelableArrayListExtra(RESULT_PRODUCT_LIST);
            mList.addAll(temp);
            mAdapter.setNewData(mList);
            calculateAmountAndSums();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        CustomerListBean.CustomerBean customerBean = getIntent().getParcelableExtra(CUSTOMER_DETAILS);
        SalesProductListBean.SalesProductBean salesProductBean = getIntent().getParcelableExtra(SalesProductDetailsActivity.SELECTED_ITEM);
        if (customerBean != null) {
            //******** 返回选择的客户 ********
            mTvCustomer.setText(customerBean.getCustomername());
            mTvDebt.setText(String.format(getString(R.string.debt_format), customerBean.getDebt()));
            mCustomerBean = customerBean;
        } else if (salesProductBean != null) {
            //******** 返回选中的单个产品 ********
            if (mList == null)
                mList = new ArrayList<>();
            mList.add(salesProductBean);
            mAdapter.setNewData(mList);
            calculateAmountAndSums();
        }
    }

    @Override
    public void initData() {
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
                calculateAmountAndSums();
            }
        };
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
    }

    //******** 计算当前选中产品的数量和金额 ********
    private void calculateAmountAndSums() {
        int amount = 0;
        double result = 0;
        for (int i = 0; i < mList.size(); i++) {
            double totalPrice = Double.parseDouble(mList.get(i).getS_jiage2()) * Double.parseDouble(mList.get(i).getCp_qty()) * Double.parseDouble(mList.get(i).getZhekou());
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
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_type:
                if (mPriceTypePopup == null) {
                    View view = LayoutInflater.from(SalesOrderActivity.this).inflate(R.layout.layout_menu_open_bill, null);
                    RecyclerView priceSourceRecyclerView = view.findViewById(R.id.price_source_recycler_view);
                    ArrayList<String> list = new ArrayList<>();
                    final String[] arr = {"手动", "等级", "批发", "参考", "历史"};
                    Collections.addAll(list, arr);
                    PriceSourcePopupAdapter adapter = new PriceSourcePopupAdapter(R.layout.layout_list_item_price_source_popup, list);
                    adapter.bindToRecyclerView(priceSourceRecyclerView);
                    priceSourceRecyclerView.setAdapter(adapter);
                    priceSourceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            if (mList != null && mList.size() > 0) {
                                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                                for (SalesProductListBean.SalesProductBean productBean : mList) {
                                    SalesProductListBean.SalesProductBean.PacklistBean packlistBean = null;
                                    for (SalesProductListBean.SalesProductBean.PacklistBean bean : productBean.getPacklist()) {
                                        if (productBean.getFactor().equals(bean.getUnit_bilv()))
                                            packlistBean = bean;
                                    }
                                    LogUtils.e("packlistBean->"+packlistBean.getUnit_bilv()+", "+packlistBean.getUnitname()
                                            +", 等级->"+packlistBean.getDengji_price()
                                            +", 参考->"+packlistBean.getCankao_price()
                                            +", 历史->"+packlistBean.getLishi_price());
                                    String price = "0.00";
                                    switch (position) {
                                        case 0: //******** 手动 ********
                                            break;
                                        case 1: //******** 等级 ********
                                            price = packlistBean.getDengji_price();
                                            break;
                                        case 2: //******** 批发 ********
                                            price = productBean.getSupp_prc();
                                            break;
                                        case 3: //******** 参考 ********
                                            price = packlistBean.getCankao_price();
                                            break;
                                        case 4: //******** 历史 ********
                                            price = packlistBean.getLishi_price();
                                            break;
                                    }
                                    LogUtils.e("单位价->"+Double.parseDouble(price)+", "+Double.parseDouble(packlistBean.getUnit_bilv()));
                                    productBean.setOrder_prc(decimalFormat.format(Double.parseDouble(price)));
                                    productBean.setS_jiage2(decimalFormat.format(Double.parseDouble(price) * Double.parseDouble(packlistBean.getUnit_bilv())));
                                }
                                mAdapter.setNewData(mList);
                            }
                            item.setTitle(arr[position] + "▼");
                            mPriceTypePopup.dissmiss();
                        }
                    });
                    mPriceTypePopup = new CustomPopWindow.PopupWindowBuilder(this)
                            .setView(view)
                            .setFocusable(true)
                            .setOutsideTouchable(true)
                            .create();
                }
                mPriceTypePopup.showAsDropDown(findViewById(R.id.toolbar_type), -80, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_customer:
                startActivity(new Intent(this, CustomerListActivity.class)
                        .putExtra(CustomerListActivity.FROM_CLASS, TAG));
                break;
            case R.id.iv_scan:
                break;
            case R.id.et_filter:
                if (!mEtFilter.isCursorVisible())
                    mEtFilter.setCursorVisible(true);
                break;
            case R.id.tv_search:
                if (mCustomerBean == null) {
                    ToastUtils.show(this, "请先选择购买产品的客户");
                } else {
                    Intent intent = new Intent(this, SalesProductListActivity.class);
                    intent.putExtra(SEARCH_ITEM, mEtFilter.getText().toString());
                    intent.putExtra(C_ID, TextUtils.isEmpty(mCustomerBean.getC_id()) ? "0" : mCustomerBean.getC_id());
                    startActivityForResult(intent, RESULT_FOR_SALES_PRODUCT_LIST);
                }
                break;
            case R.id.tv_reopen:
                mCustomerBean = null;
                mList.clear();
                mTvDebt.setText(String.format(getString(R.string.debt_format), "0.00"));
                mTvTotalAmount.setText("0");
                mTvTotalPrice.setText("0.00");
                break;
            case R.id.tv_create_order:
                if (mCustomerBean == null) {
                    ToastUtils.show(this, "请选择购买产品的客户");
                } else if (mList == null || mList.size() == 0) {
                    ToastUtils.show(this, "请选择需要购买的产品");
                } else {
                    Intent intent_2 = new Intent(this, CreateOrderActivity.class);
                    intent_2.putExtra(RESULT_CUSTOMER, mCustomerBean);
                    intent_2.putParcelableArrayListExtra(RESULT_PRODUCT_LIST, mList);
                    startActivityForResult(intent_2, RESULT_FOR_CREATE_ORDER);
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
                if (mCustomerBean == null) {
                    ToastUtils.show(SalesOrderActivity.this, "请先选择购买产品的客户");
                    return true;
                } else {
                    Intent i = new Intent(SalesOrderActivity.this, SalesProductListActivity.class);
                    i.putExtra(SEARCH_ITEM, mEtFilter.getText().toString());
                    i.putExtra(C_ID, TextUtils.isEmpty(mCustomerBean.getC_id()) ? "0" : mCustomerBean.getC_id());
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
                    if (i > 1) {
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
            double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(item.getZhekou()) * Double.parseDouble(mList.get(position).getCp_qty());
            DecimalFormat mFormat = new DecimalFormat("0.00");
            tvSums.setText(mFormat.format(totalPrice));
            etAmount.setText(String.valueOf(i));
            calculateAmountAndSums();
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(SalesOrderActivity.this, SalesProductDetailsActivity.class);
            intent.putExtra(SalesProductListActivity.SELECTED_ITEM, mList.get(position));
            intent.putExtra(SalesProductDetailsActivity.FROM_CLASS, TAG);
            startActivityForResult(intent, RESULT_FOR_PRODUCT_INFO_CHANGE);
            mLastIndex = position;
        }
    }
}
