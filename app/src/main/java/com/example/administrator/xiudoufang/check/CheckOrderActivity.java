package com.example.administrator.xiudoufang.check;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.common.widget.CustomViewPager;

import java.util.ArrayList;

public class CheckOrderActivity extends AppCompatActivity implements IActivityBase {

    private TabLayout mTabLayout;
    private CustomViewPager mViewPager;

    public static void start(Context context) {
        Intent intent = new Intent(context, CheckOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_check_order;
    }

    @Override
    public void initView() {
        setTitle("订单查询");
        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        String[] tabTitles = {"草稿", "未配货", "配货中", "已完成", "全部"};
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabTitles.length; i++) {
            fragments.add(CheckOrderSubFragment.newInstance(i));
        }
        CheckOrderTabAdapter mAdapter = new CheckOrderTabAdapter(getSupportFragmentManager(), fragments, tabTitles);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
