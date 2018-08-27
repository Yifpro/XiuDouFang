package com.example.administrator.xiudoufang.stock.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.TypeListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.stock.adapter.TypeListAdapter;
import com.example.administrator.xiudoufang.stock.logic.StockLogic;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class TypeListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String INCLUDE_SUBCLASS = "include_subclass";
    public static final String TYPE_LIST = "type_list";

    private RecyclerView mRecyclerView;
    private Switch mToggle;

    private StockLogic mLogic;
    private TypeListAdapter mAdapter;
    private ArrayList<TypeListBean.TypeBean> mList;
    private String mFilterText = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_type_list;
    }

    @Override
    public void initView() {
        setTitle("类别选择");
        mRecyclerView = findViewById(R.id.recycler_view);
        mToggle = findViewById(R.id.toggle);
        TextView tvBottomLeft = findViewById(R.id.tv_bottom_left);
        TextView tvBottomRight = findViewById(R.id.tv_bottom_right);

        tvBottomLeft.setText("清除");
        tvBottomRight.setText(R.string.sure);

        tvBottomLeft.setOnClickListener(this);
        tvBottomRight.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mLogic = new StockLogic();
        mAdapter = new TypeListAdapter(R.layout.layout_list_item_warehouse_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        loadTypeList();
    }

    private void loadTypeList() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> params = new HashMap<>();
        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("searchitem", mFilterText);
        params.put("classid", "0");
        params.put("action", "0");
        LoadingViewDialog.getInstance().show(this);
        mLogic.requestTypeList(params, new JsonCallback<TypeListBean>() {
            @Override
            public void onSuccess(Response<TypeListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mList.addAll(response.body().getProducttypes());
                mAdapter.setNewData(mList);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bottom_left:
                for (TypeListBean.TypeBean bean : mList) {
                    bean.setSelected(false);
                }
                mAdapter.setNewData(mList);
                break;
            case R.id.tv_bottom_right:
                Intent intent = new Intent();
                ArrayList<TypeListBean.TypeBean> list = new ArrayList<>();
                for (TypeListBean.TypeBean bean : mList) {
                    if (bean.isSelected()) {
                        list.add(bean);
                    }
                }
                LogUtils.e("toggle status -> "+mToggle.isChecked());
                intent.putParcelableArrayListExtra(TYPE_LIST, list);
                intent.putExtra(INCLUDE_SUBCLASS, mToggle.isChecked());
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    private class InnerItemChildListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            mList.get(position).setSelected(!mList.get(position).isSelected());
            adapter.notifyItemChanged(position);
        }
    }
}
