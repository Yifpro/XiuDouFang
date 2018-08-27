package com.example.administrator.xiudoufang.stock.ui;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.StockDetailsBean;
import com.example.administrator.xiudoufang.bean.StockDetailsItem;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.stock.adapter.StockDetailsAdapter;
import com.example.administrator.xiudoufang.stock.decoration.StockDetailsDecoration;
import com.example.administrator.xiudoufang.stock.logic.StockLogic;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockDetailsActivity extends AppCompatActivity implements IActivityBase {

    private ImageView mIvIcon;
    private RecyclerView mRecyclerView;

    private StockLogic mLogic;
    private List<MultiItemEntity> mList;
    private StockDetailsAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_details;
    }

    @Override
    public void initView() {
        setTitle("库存");
        mIvIcon = findViewById(R.id.iv_icon);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mLogic = new StockLogic();
        mAdapter = new StockDetailsAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new StockDetailsDecoration());
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        loadStockDetails();
    }

    private void loadStockDetails() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        map.put("cpid", getIntent().getStringExtra(StockActivity.PRODUCT_NO));
        map.put("unitvalue", "4");
        mLogic.requestStockDetails(map, new JsonCallback<StockDetailsBean>() {
            @Override
            public void onSuccess(Response<StockDetailsBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                StockDetailsBean bean = response.body();
                GlideApp.with(StockDetailsActivity.this).load(StringUtils.PIC_SMALL_URL + bean.getPics().get(0)).error(R.mipmap.ic_icon).into(mIvIcon);
                mList.add(new StockDetailsItem("产品编号", bean.getStyleno()));
                mList.add(new StockDetailsItem("产品名", bean.getStylename()));
                mList.add(new StockDetailsItem("类别", bean.getClassname()));
                mList.add(new StockDetailsItem("型号", bean.getXinghao()));
                mList.add(new StockDetailsItem("条形码", bean.getTiaoxingma()));
                mList.add(new StockDetailsItem("产品描述", bean.getDetail()));
                mList.add(new StockDetailsItem("品牌", bean.getPinpai()));
                mList.add(new StockDetailsItem("供应商", bean.getSuppstr()));
                mList.addAll(bean.getDianinvproducts());
                mAdapter.setNewData(mList);
            }
        });
    }
}
