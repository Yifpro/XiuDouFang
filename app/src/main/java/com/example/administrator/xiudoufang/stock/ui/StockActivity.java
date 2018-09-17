package com.example.administrator.xiudoufang.stock.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.StockFilter;
import com.example.administrator.xiudoufang.bean.StockListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.ui.PicPorchActivity;
import com.example.administrator.xiudoufang.purchase.ui.ScanActivity;
import com.example.administrator.xiudoufang.stock.adapter.StockListAdapter;
import com.example.administrator.xiudoufang.stock.logic.StockLogic;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Consumer;

public class StockActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;
    public static final String PRODUCT_NO = "product_no";
    public static final String UNITVALUE = "unitvalue";
    private static final int RESULT_FOR_STOCK_QUERY = 112;
    private static final int RESULT_FOR_SCAN_BAR_CODE = 129; //******** 扫描条形码 ********

    private EditText mEtFilter;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private StockLogic mLogic;
    private StockListAdapter mAdapter;
    private HashMap<String, String> mParams;
    private List<StockListBean.StockBean> mList;
    private int mCurrentPage = 1;
    private String mFilterText = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, StockActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_STOCK_QUERY && data != null) { //******** 返回库存过滤条件 ********
            StockFilter filter = data.getParcelableExtra(StockQueryActivity.FILTER_INFO);
            mParams.put("code", filter.getNo());
            mParams.put("sn", filter.getName());
            mParams.put("classname", filter.getType());
            mParams.put("supp", filter.getSupplier());
            mParams.put("xinghao", filter.getModel());
            mParams.put("tiaoxingma", filter.getBarCode());
            mParams.put("pinpai", filter.getBrand());
            mParams.put("qtystr", filter.getAmount());
            mParams.put("detail", filter.getDetails());
            mParams.put("unitvalue", filter.getUnit());
            mParams.put("subchild", filter.getIsIncludeSubclass());
            LoadingViewDialog.getInstance().show(this);
            loadStockList(true);
        } else if (requestCode == RESULT_FOR_SCAN_BAR_CODE && data != null) { //******** 返回扫码得到的条形码 ********
            loadBarcodeProduct(data);
        }
    }

    //******** 加载条形码产品 ********
    private void loadBarcodeProduct(@Nullable Intent data) {
        LoadingViewDialog.getInstance().show(this);
        HashMap<String, String> params_3 = new HashMap<>();
        params_3.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params_3.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params_3.put("code", ""); //产品编号
        params_3.put("sn", ""); //产品名称
        params_3.put("classname", ""); //类别
        params_3.put("supp", ""); //供应商
        params_3.put("xinghao", ""); //型号
        params_3.put("tiaoxingma", ""); //条形码
        params_3.put("pinpai", ""); //品牌
        params_3.put("qtystr", "1"); //数量
        params_3.put("detail", ""); //详述
        params_3.put("scanner", "1"); //扫描动作
        params_3.put("unitvalue", "4"); //辅助单位
        params_3.put("searchstr", data.getStringExtra(ScanActivity.BARCODE_PRODUCT)); //检索内容
        params_3.put("count", String.valueOf(COUNT)); //个数
        params_3.put("subchild", ""); //是否包含子集
        params_3.put("pagenum", "1");
        mLogic.requestStockList(this, params_3, new JsonCallback<StockListBean>() {
            @Override
            public void onSuccess(Response<StockListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<StockListBean.StockBean> temp = response.body().getInvlists();
                if (mList == null) {
                    mList = new ArrayList<>();
                } else {
                    mList.clear();
                }
                mList.addAll(temp);
                mAdapter.setNewData(mList);
            }
        });
    }

    @Override
    public void initView() {
        setTitle("库存");
        mEtFilter = findViewById(R.id.et_filter);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        mEtFilter.setHint(R.string.search);
        mEtFilter.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        findViewById(R.id.iv_scan).setOnClickListener(this);
        findViewById(R.id.iv_filter).setOnClickListener(this);
        mEtFilter.setOnClickListener(this);
        mEtFilter.addTextChangedListener(new InnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
    }

    @Override
    public void initData() {
        mLogic = new StockLogic();
        mAdapter = new StockListAdapter(R.layout.layout_list_item_stock, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addOnScrollListener(new InnerScrollListener());
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        initParams();
        loadStockList(true);
    }

    //******** 加载库存信息 ********
    private void loadStockList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestStockList(this, mParams, new JsonCallback<StockListBean>() {
            @Override
            public void onSuccess(Response<StockListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<StockListBean.StockBean> temp = response.body().getInvlists();
                if (isRefresh) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(mList.size() < COUNT);
                } else {
                    mList.addAll(temp);
                    mAdapter.addData(temp);
                    if (mList.size() < COUNT) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        mRefreshLayout.finishLoadMore();
                    }
                }
            }
        });
    }

    private void initParams() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        mParams = new HashMap<>();
        mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        mParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        mParams.put("code", ""); //产品编号
        mParams.put("sn", ""); //产品名称
        mParams.put("classname", ""); //类别
        mParams.put("supp", ""); //供应商
        mParams.put("xinghao", ""); //型号
        mParams.put("tiaoxingma", ""); //条形码
        mParams.put("pinpai", ""); //品牌
        mParams.put("qtystr", "1"); //数量
        mParams.put("detail", ""); //详述
        mParams.put("scanner", "0"); //扫描动作
        mParams.put("unitvalue", ""); //辅助单位
        mParams.put("searchstr", mFilterText); //检索内容
        mParams.put("count", String.valueOf(COUNT)); //个数
        mParams.put("subchild", ""); //是否包含子集
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_filter:
                mEtFilter.setCursorVisible(true);
                break;
            case R.id.iv_scan:
                new RxPermissions(this)
                        .requestEach(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    startActivityForResult(new Intent(StockActivity.this, ScanActivity.class)
                                            .putExtra(ScanActivity.FROM_CLASS, ScanActivity.STOCK_LIST), RESULT_FOR_SCAN_BAR_CODE);
                                } else {
                                    ToastUtils.show(StockActivity.this, "请开启权限后重新尝试");
                                }
                            }
                        });
                break;
            case R.id.iv_filter:
                startActivityForResult(new Intent(this, StockQueryActivity.class), RESULT_FOR_STOCK_QUERY);
                break;
        }
    }

    private class InnerEditActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                if (mFilterText.length() != 0) {
                    LoadingViewDialog.getInstance().show(StockActivity.this);
                    loadStockList(true);
                    return true;
                }
            }
            return false;
        }
    }

    private class InnerTextWatcher extends BaseTextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            mFilterText = editable.toString().trim();
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(StockActivity.this, StockDetailsActivity.class);
            intent.putExtra(PRODUCT_NO, mList.get(position).getCpid());
            intent.putExtra(UNITVALUE, mParams.get("unitvalue"));
            startActivity(intent);
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            ArrayList<String> list = new ArrayList<>();
            for (StockListBean.StockBean.PiclistBean bean : mList.get(position).getPiclist()) {
                list.add(bean.getPic());
            }
            startActivity(new Intent(StockActivity.this, PicPorchActivity.class)
                    .putStringArrayListExtra(PicPorchActivity.PIC_LIST, list));
        }
    }

    private class InnerScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                Glide.with(StockActivity.this).resumeRequests();
            }else {
                Glide.with(StockActivity.this).pauseRequests();
            }
        }
    }
}
