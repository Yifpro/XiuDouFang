package com.example.administrator.xiudoufang.check.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.OrderFilter;
import com.example.administrator.xiudoufang.check.adapter.OrderListTabAdapter;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.widget.CustomViewPager;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_FOR_ORDER_FILTER = 116;
    public static final String ORDER_FILTER = "order_filter";

    private TabLayout mTabLayout;
    public CustomViewPager mViewPager;

    public OrderFilter mOrderFilter;

    public static void start(Context context) {
        Intent intent = new Intent(context, OrderListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void initView() {
        setTitle("订单查询");
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_ORDER_FILTER && data != null) {
            OrderFilter filter = data.getParcelableExtra(OrderQueryActivity.ORDER_FILTER);
            if (mOrderFilter == null)
                mOrderFilter = new OrderFilter();
            mOrderFilter.setNo(filter.getNo());
            mOrderFilter.setCustomer(filter.getCustomer());
            mOrderFilter.setStartTime(filter.getStartTime());
            mOrderFilter.setEndTime(filter.getEndTime());
            mOrderFilter.setOrderType(filter.getOrderType());
            mOrderFilter.setTransportType(filter.getTransportType());
            mOrderFilter.setProxyOrder(filter.getProxyOrder());
        }
    }

    @Override
    public void initData() {
        String[] tabTitles = {"草稿", "未配货", "配货中", "已完成", "全部"};
        final ArrayList<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            fragments.add(OrderListSubFragment.newInstance(i));
        }
        OrderListTabAdapter mAdapter = new OrderListTabAdapter(getSupportFragmentManager(), fragments, tabTitles);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_filter:
                Intent i = new Intent(this, OrderQueryActivity.class);
                i.putExtra(ORDER_FILTER, mOrderFilter);
                startActivityForResult(i, RESULT_FOR_ORDER_FILTER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
