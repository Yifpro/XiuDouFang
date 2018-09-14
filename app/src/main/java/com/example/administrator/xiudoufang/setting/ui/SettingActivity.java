package com.example.administrator.xiudoufang.setting.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.LoginStore;
import com.example.administrator.xiudoufang.bean.StringPair;
import com.example.administrator.xiudoufang.common.utils.CacheDataManager;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.login.ui.LoginActivity;
import com.example.administrator.xiudoufang.setting.adapter.SettingAdapter;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_SORT_LIST = 101;
    public static final String STORE_LIST = "store_list";
    public static final String SELECTED_INDEX = "selected_index";

    private RecyclerView mRecyclerView;

    private SettingAdapter mAdapter;
    private ArrayList<StringPair> mList;
    private ArrayList<LoginStore> mLoginStores;
    private int mIndex; //******** 当前店的下标 ********
    private boolean mIsOpen = PreferencesUtils.getPreferences().getBoolean(PreferencesUtils.SEQUENTIAL_SCAN, false); //******** 是否开启连续扫描 ********

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
    public void onBackPressed() {
        PreferencesUtils.save(PreferencesUtils.SEQUENTIAL_SCAN, mIsOpen);
        super.onBackPressed();
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
                mIndex = i;
                mLoginStores.add(new LoginStore(id, mjname, true));
            } else {
                mLoginStores.add(new LoginStore(id, mjname, false));
            }
        }
        mList = new ArrayList<>();
        mList.add(new StringPair("账户名", jsonObject.getString("username")).setShowLine(true));
        mList.add(new StringPair("姓名", jsonObject.getString("xm")));
        mList.add(new StringPair("部门", jsonObject.getString("bumen")));
        mList.add(new StringPair("店ID", jsonObject.getString("dianid")));
        mList.add(new StringPair("版本号", "1.0.9"));
        mList.add(new StringPair("服务器地址", StringUtils.BASE_URL));
        mList.add(new StringPair("当前店", mLoginStores.get(mIndex).getName()).setShowNext(true).setShowLine(true));
        mList.add(new StringPair("清除缓存", CacheDataManager.getTotalCacheSize(this)).setShowNext(true));
        mList.add(new StringPair("连续扫描模式", "").setToogleButton(true));
        mAdapter = new SettingAdapter(R.layout.layout_list_item_setting, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnCheckedChangeListener(new InnerCheckChangeListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_SORT_LIST && data != null) {
            int shopIdIndex = mList.indexOf(new StringPair("店ID"));
            JSONObject loginInfo = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
            mList.get(shopIdIndex).setValue(loginInfo.getString("dianid"));
            int currentShopIndex = mList.indexOf(new StringPair("当前店"));
            mLoginStores.get(mIndex).setSelected(false);
            mIndex = data.getIntExtra("index", 0);
            mLoginStores.get(mIndex).setSelected(true);
            String storeName = mLoginStores.get(mIndex).getName();
            mList.get(currentShopIndex).setValue(storeName);
            mAdapter.notifyItemChanged(shopIdIndex);
            mAdapter.notifyItemChanged(currentShopIndex);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class InnerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            LoginActivity.start(SettingActivity.this);
            SettingActivity.this.finish();
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            switch (position) {
                case 6:
                    //******** 切换店 ********
                    Intent intent = new Intent(SettingActivity.this, ShopListActivity.class);
                    intent.putParcelableArrayListExtra(STORE_LIST, mLoginStores);
                    intent.putExtra(SELECTED_INDEX, mIndex);
                    startActivityForResult(intent, RESULT_SORT_LIST);
                    break;
                case 7:
                    //******** 清除缓存 ********
                    CacheDataManager.clearAllCache(SettingActivity.this);
                    int index = mList.indexOf(new StringPair("清除缓存"));
                    mList.get(index).setValue(CacheDataManager.getTotalCacheSize(SettingActivity.this));
                    adapter.notifyItemChanged(index);
                    ToastUtils.show(SettingActivity.this, "清除缓存完毕");
                    break;
            }
        }
    }

    private class InnerCheckChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            mIsOpen = b;
        }
    }
}
