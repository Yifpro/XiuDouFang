package com.example.administrator.xiudoufang.stock.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ImgBean;
import com.example.administrator.xiudoufang.bean.StockDetailsBean;
import com.example.administrator.xiudoufang.bean.StockDetailsItem;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.ui.PicPorchActivity;
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

    private RecyclerView mRecyclerView;

    private StockLogic mLogic;
    private List<MultiItemEntity> mList;
    private StockDetailsAdapter mAdapter;
    private StockDetailsBean mStockDetailsBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_details;
    }

    @Override
    public void initView() {
        setTitle("库存");
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mLogic = new StockLogic();
        mAdapter = new StockDetailsAdapter(mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.addItemDecoration(new StockDetailsDecoration());
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        loadStockDetails();
    }

    //******** 加载库存详情 ********
    private void loadStockDetails() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        map.put("cpid", getIntent().getStringExtra(StockActivity.PRODUCT_NO)); //产品id
        String unitvalue = getIntent().getStringExtra(StockActivity.UNITVALUE);
        map.put("unitvalue", TextUtils.isEmpty(unitvalue) ? "4" : unitvalue); //所属单位
        mLogic.requestStockDetails(this, map, new JsonCallback<StockDetailsBean>() {
            @Override
            public void onSuccess(Response<StockDetailsBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mStockDetailsBean = response.body();
                mList.add(new ImgBean(mStockDetailsBean.getPics().get(0).getPic()));
                mList.add(new StockDetailsItem("产品编号", mStockDetailsBean.getStyleno()));
                mList.add(new StockDetailsItem("产品名", mStockDetailsBean.getStylename()));
                mList.add(new StockDetailsItem("类别", mStockDetailsBean.getClassname()));
                mList.add(new StockDetailsItem("型号", mStockDetailsBean.getXinghao()));
                mList.add(new StockDetailsItem("条形码", mStockDetailsBean.getTiaoxingma()));
                mList.add(new StockDetailsItem("产品描述", mStockDetailsBean.getDetail()));
                mList.add(new StockDetailsItem("品牌", mStockDetailsBean.getPinpai()));
                mList.add(new StockDetailsItem("供应商", mStockDetailsBean.getSuppstr()));
                mList.addAll(mStockDetailsBean.getDianinvproducts());
                mAdapter.setNewData(mList);
            }
        });
    }

    private class InnerItemClickListener implements com.chad.library.adapter.base.BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            ArrayList<String> list = new ArrayList<>();
            for (StockDetailsBean.PicsBean bean : mStockDetailsBean.getPics()) {
                list.add(bean.getPic());
            }
            startActivity(new Intent(StockDetailsActivity.this, PicPorchActivity.class)
                    .putStringArrayListExtra(PicPorchActivity.PIC_LIST, list));
        }
    }
}
