package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.base.BasePageChangeListener;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.base.OnEventListener;
import com.example.administrator.xiudoufang.bean.DrawerItem;
import com.example.administrator.xiudoufang.purchase.adapter.PurchasePagerAdapter;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseTabAdapter;
import com.example.administrator.xiudoufang.common.widget.CustomViewPager;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_FOR_NEW_PURCHASE_ORDER = 110;
    private static final int RESULT_FOR_FILTER_LIST = 111;
    public static final String FILTER_LIST = "filter_list";

    private DrawerLayout mDrawerLayout;
    private CustomViewPager mViewPager;
    private LinearLayout mLeftMenu;
    private RecyclerView mRecyclerView;

    private List<DrawerItem> mTabs;
    private int mLastIndex = -1;
    private List<BaseFragment> mFragments;
    public HashMap<String, String> mParams;

    public static void start(Context context) {
        context.startActivity(new Intent(context, PurchaseActivity.class));
    }


    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_purchase;
    }

    @Override
    public void initView() {
        setTitle("采购历史");
        mViewPager = findViewById(R.id.view_pager);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mLeftMenu = findViewById(R.id.left_menu);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        String[] titles = {"草稿", "已确认", "收货中", "未提交", "已提交", "已关闭", "全部"};
        mTabs = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            mTabs.add(new DrawerItem(titles[i], i == 0));
        }
        PurchaseTabAdapter adapter = new PurchaseTabAdapter(R.layout.layout_list_item_purchase_tab, mTabs);
        adapter.bindToRecyclerView(mRecyclerView);
        View header = View.inflate(this, R.layout.layout_list_header_server_selector, null);
        TextView tvHeader = header.findViewById(R.id.tv);
        tvHeader.setHeight(SizeUtils.dp2px(72));
        tvHeader.setTextSize(22);
        tvHeader.setText("筛选");
        adapter.addHeaderView(header);
        adapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFragments = new ArrayList<>();
        String[] types = {"1", "2", "3", "4", "5", "6", "0"};
        for (String type : types) {
            mFragments.add(PurchaseSubFragment.newInstance(type));
        }
        PurchasePagerAdapter mAdapter = new PurchasePagerAdapter(getSupportFragmentManager(), mFragments, titles);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new InnerPageChangeListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_NEW_PURCHASE_ORDER && resultCode == Activity.RESULT_OK) {
            int currentItem = mViewPager.getCurrentItem();
            ((OnEventListener) mFragments.get(currentItem)).onEvent();
        } else if (requestCode == RESULT_FOR_FILTER_LIST && resultCode == Activity.RESULT_OK) {
            String[] keys = {"PuOrderNo", "Suppname", "starttime", "endtime", "crman", "remark", "fromorder"};
            if (mParams == null) {
                mParams = new HashMap<>();
                ArrayList<String> list = data.getStringArrayListExtra(FILTER_LIST);
                for (int i = 0; i < list.size(); i++) {
                    mParams.put(keys[i], list.get(i));
                }
            }
            mFragments.get(mViewPager.getCurrentItem()).onRefresh();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_purchase, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_search:
                Intent i = new Intent(this, PurchaseQueryActivity.class);
                startActivityForResult(i, RESULT_FOR_FILTER_LIST);
                break;
            case R.id.toolbar_menu:
                Intent intent = new Intent(this, NewPurchaseOrderActivity.class);
                startActivityForResult(intent, RESULT_FOR_NEW_PURCHASE_ORDER);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @SuppressWarnings("unchecked")
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            mViewPager.setCurrentItem(position);
            mTabs.get(mLastIndex == -1 ? 0 : mLastIndex).setSelected(false);
            mTabs.get(position).setSelected(true);
            adapter.setNewData(mTabs);
            mDrawerLayout.closeDrawer(mLeftMenu);
            mLastIndex = position;
        }
    }

    private class InnerPageChangeListener extends BasePageChangeListener {

        @Override
        public void onPageSelected(int i) {
            if (mParams != null) {
                mFragments.get(i).onRefresh();
            }
        }
    }
}
