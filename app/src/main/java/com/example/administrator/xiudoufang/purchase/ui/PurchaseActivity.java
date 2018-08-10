package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
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
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.purchase.adapter.PurchasePagerAdapter;
import com.example.administrator.xiudoufang.purchase.adapter.PurchaseTabAdapter;
import com.example.administrator.xiudoufang.common.widget.CustomViewPager;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PurchaseActivity extends AppCompatActivity implements IActivityBase {

    private DrawerLayout mDrawerLayout;
    private CustomViewPager mViewPager;
    private LinearLayout mLeftMenu;
    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, PurchaseActivity.class));
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase;
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
        String[] tabTitles = {"草稿", "已确认", "收货中", "未提交", "已提交", "已关闭", "全部"};
        List<String> tabs = Arrays.asList(tabTitles);
        PurchaseTabAdapter adapter = new PurchaseTabAdapter(R.layout.layout_list_item_purchase_tab, tabs);
        adapter.bindToRecyclerView(mRecyclerView);
        View header = View.inflate(this, R.layout.layout_list_header_server_selector, null);
        TextView tvHeader = header.findViewById(R.id.tv);
        tvHeader.setHeight(SizeUtils.dp2px(72));
        tvHeader.setTextSize(24);
        tvHeader.setText("筛选");
        adapter.addHeaderView(header);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mViewPager.setCurrentItem(position);
                mDrawerLayout.closeDrawer(mLeftMenu);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            fragments.add(PurchaseSubFragment.newInstance(i));
        }
        PurchasePagerAdapter mAdapter = new PurchasePagerAdapter(getSupportFragmentManager(), fragments, tabTitles);
        mViewPager.setAdapter(mAdapter);
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
                PurchaseQueryActivity.start(this);
                break;
            case R.id.toolbar_menu:
                PurchaseOrderDetailActivity.start(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
