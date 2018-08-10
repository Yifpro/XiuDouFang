package com.example.administrator.xiudoufang.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.SettingItem;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements IActivityBase {

    public static void start(Context context) {
        context.startActivity(new Intent(context, SettingActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setTitle("设置");
        ArrayList<SettingItem> settingItems = new ArrayList<>();
        settingItems.add(new SettingItem("账户名", "LL").setShowLine(true));
        settingItems.add(new SettingItem("姓名", "莉莉"));
        settingItems.add(new SettingItem("部门", "总经理办公室"));
        settingItems.add(new SettingItem("店ID", "45"));
        settingItems.add(new SettingItem("版本号", "1.0.9"));
        settingItems.add(new SettingItem("服务器地址", "http://aoxing.pro:83"));
        settingItems.add(new SettingItem("当前店", "雅秀").setShowNext(true).setShowLine(true));
        settingItems.add(new SettingItem("清除缓存", "0.75M").setShowNext(true));
        settingItems.add(new SettingItem("连续扫描模式", "").setToogleButton(true));
        SettingAdapter adapter = new SettingAdapter(R.layout.layout_list_item_setting, settingItems);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initData() {
    }
}
