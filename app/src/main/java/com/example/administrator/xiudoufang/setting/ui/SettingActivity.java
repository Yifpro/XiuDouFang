package com.example.administrator.xiudoufang.setting.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.LoginStore;
import com.example.administrator.xiudoufang.bean.SettingItem;
import com.example.administrator.xiudoufang.common.utils.CacheDiskUtils;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.login.ui.LoginActivity;
import com.example.administrator.xiudoufang.setting.adapter.SettingAdapter;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;
    private ArrayList<SettingItem> mList;
    private ArrayList<LoginStore> mLoginStores;
    private SettingAdapter mAdapter;
    private int mIndex;

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
        mRecyclerView = findViewById(R.id.recycler_view);

        findViewById(R.id.tv_exit_login).setOnClickListener(new InnerClickListener());
    }

    @Override
    public void initData() {
        LoadingViewDialog.getInstance().show(this);
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readLoginInfo(StringUtils.LOGIN_INFO));
        JSONArray logindians = jsonObject.getJSONArray("logindian");
        mLoginStores = new ArrayList<>();
        for (int i = 0; i < logindians.size(); i++) {
            JSONObject object = logindians.getJSONObject(i);
            String id = object.getString("id");
            String mjname = object.getString("mjname");
            if (id.equals(jsonObject.getString("dianid"))) {
                mIndex = i;
                mLoginStores.add(new LoginStore(id, mjname, true));
            } else {
                mLoginStores.add(new LoginStore(id, mjname, false));
            }
        }
        LoadingViewDialog.getInstance().dismiss();
        mList = new ArrayList<>();
        mList.add(new SettingItem("账户名", jsonObject.getString("username")).setShowLine(true));
        mList.add(new SettingItem("姓名", jsonObject.getString("xm")));
        mList.add(new SettingItem("部门", jsonObject.getString("bumen")));
        mList.add(new SettingItem("店ID", jsonObject.getString("dianid")));
        mList.add(new SettingItem("版本号", "1.0.9"));
        mList.add(new SettingItem("服务器地址", StringUtils.BASE_URL));
        mList.add(new SettingItem("当前店", mLoginStores.get(mIndex).getName()).setShowNext(true).setShowLine(true));
        CacheDiskUtils instance = CacheDiskUtils.getInstance();
        LogUtils.e("before->"+instance.getCacheSize());
        mList.add(new SettingItem("清除缓存", instance.formatCacheSize(instance.getCacheSize())).setShowNext(true));
        mList.add(new SettingItem("连续扫描模式", "").setToogleButton(true));
        mAdapter = new SettingAdapter(R.layout.layout_list_item_setting, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 6:
                        Intent intent = new Intent(SettingActivity.this, StoreSwitchActivity.class);
                        intent.putParcelableArrayListExtra("store_list", mLoginStores);
                        intent.putExtra("index", mIndex);
                        startActivityForResult(intent, 2);
                        break;
                    case 7:
                        CacheDiskUtils.getInstance().clear();
                        int index = mList.indexOf(new SettingItem("清除缓存"));
                        CacheDiskUtils instance = CacheDiskUtils.getInstance();
                        LogUtils.e("after->"+instance.getCacheSize());
                        mList.get(index).setValue(instance.formatCacheSize(instance.getCacheSize()));
                        break;
                    case 8:
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2) {
            int index = mList.indexOf(new SettingItem("当前店"));
            assert data != null;
            mLoginStores.get(mIndex).setSelected(false);
            mIndex = data.getIntExtra("index", 0);
            mLoginStores.get(mIndex).setSelected(true);
            String storeName = mLoginStores.get(mIndex).getName();
            mList.get(index).setValue(storeName);
            mAdapter.notifyItemChanged(index);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class InnerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            SettingActivity.this.finish();
            LoginActivity.start(SettingActivity.this);
        }
    }
}
