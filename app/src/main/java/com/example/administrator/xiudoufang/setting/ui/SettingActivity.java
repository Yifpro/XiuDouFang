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
import com.example.administrator.xiudoufang.login.ui.LoginActivity;
import com.example.administrator.xiudoufang.setting.adapter.SettingAdapter;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_SORT_LIST = 101;
    public static final String STORE_LIST = "store_list";
    public static final String SELECTED_INDEX = "selected_index";

    private RecyclerView mRecyclerView;

    private SettingAdapter mAdapter;
    private ArrayList<SettingItem> mItemList;
    private ArrayList<LoginStore> mLoginStores;
    private int mLogindianIndex;

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
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        JSONArray logindians = jsonObject.getJSONArray("logindian");
        mLoginStores = new ArrayList<>();
        for (int i = 0; i < logindians.size(); i++) {
            JSONObject object = logindians.getJSONObject(i);
            String id = object.getString("id");
            String mjname = object.getString("mjname");
            if (id.equals(jsonObject.getString("dianid"))) {
                mLogindianIndex = i;
                mLoginStores.add(new LoginStore(id, mjname, true));
            } else {
                mLoginStores.add(new LoginStore(id, mjname, false));
            }
        }
        mItemList = new ArrayList<>();
        mItemList.add(new SettingItem("账户名", jsonObject.getString("username")).setShowLine(true));
        mItemList.add(new SettingItem("姓名", jsonObject.getString("xm")));
        mItemList.add(new SettingItem("部门", jsonObject.getString("bumen")));
        mItemList.add(new SettingItem("店ID", jsonObject.getString("dianid")));
        mItemList.add(new SettingItem("版本号", "1.0.9"));
        mItemList.add(new SettingItem("服务器地址", StringUtils.BASE_URL));
        mItemList.add(new SettingItem("当前店", mLoginStores.get(mLogindianIndex).getName()).setShowNext(true).setShowLine(true));
        CacheDiskUtils instance = CacheDiskUtils.getInstance();
        LogUtils.e("cache clear before->"+instance.getCacheSize());
        mItemList.add(new SettingItem("清除缓存", instance.formatCacheSize(instance.getCacheSize())).setShowNext(true));
        mItemList.add(new SettingItem("连续扫描模式", "").setToogleButton(true));
        mAdapter = new SettingAdapter(R.layout.layout_list_item_setting, mItemList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 6:
                        Intent intent = new Intent(SettingActivity.this, StoreSwitchActivity.class);
                        intent.putParcelableArrayListExtra(STORE_LIST, mLoginStores);
                        intent.putExtra(SELECTED_INDEX, mLogindianIndex);
                        startActivityForResult(intent, RESULT_SORT_LIST);
                        break;
                    case 7:
                        CacheDiskUtils.getInstance().clear();
                        int index = mItemList.indexOf(new SettingItem("清除缓存"));
                        CacheDiskUtils instance = CacheDiskUtils.getInstance();
                        LogUtils.e("cache clear after->"+instance.getCacheSize());
                        mItemList.get(index).setValue(instance.formatCacheSize(instance.getCacheSize()));
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
        if (requestCode == RESULT_SORT_LIST) {
            int index = mItemList.indexOf(new SettingItem("当前店"));
            assert data != null;
            mLoginStores.get(mLogindianIndex).setSelected(false);
            mLogindianIndex = data.getIntExtra("index", 0);
            mLoginStores.get(mLogindianIndex).setSelected(true);
            String storeName = mLoginStores.get(mLogindianIndex).getName();
            mItemList.get(index).setValue(storeName);
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
