package com.example.administrator.xiudoufang.setting.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.LoginStore;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.login.logic.LoginLogic;
import com.example.administrator.xiudoufang.setting.adapter.StoreSwitchAdapter;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopListActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;

    private ArrayList<LoginStore> mList;
    private int mIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_store_switch;
    }

    @Override
    public void initView() {
        setTitle("店铺切换");
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mList = getIntent().getParcelableArrayListExtra(SettingActivity.STORE_LIST);
        mIndex = getIntent().getIntExtra(SettingActivity.SELECTED_INDEX, 0);
        StoreSwitchAdapter adapter = new StoreSwitchAdapter(R.layout.item_store_switch, mList);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        LoadingViewDialog.getInstance().show(this);
        LoginLogic logic = new LoginLogic();
        HashMap<String, String> map = new HashMap<>();
        map.put("username", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_NAME, ""));
        map.put("password", PreferencesUtils.getPreferences().getString(PreferencesUtils.PASSWORD, ""));
        map.put("logdianid", mList.get(mIndex).getId());
        map.put("phonecode", "");
        map.put("changedian", "1");
        logic.requestLogin(this, map, new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                LogUtils.e("新信息->" + response.body());
                StringUtils.cacheInfoToFile(response.body(), StringUtils.LOGIN_INFO);
                setResult(Activity.RESULT_OK, new Intent().putExtra("index", mIndex));
                ShopListActivity.super.onBackPressed();
            }
        });
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (mIndex != position) {
                mList.get(mIndex).setSelected(false);
                mList.get(position).setSelected(true);
                adapter.notifyItemChanged(mIndex);
                adapter.notifyItemChanged(position);
                mIndex = position;
            }
        }
    }
}
