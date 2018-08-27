package com.example.administrator.xiudoufang.stock.ui;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.StockFilter;
import com.example.administrator.xiudoufang.bean.StockListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.ui.ScanActivity;
import com.example.administrator.xiudoufang.stock.adapter.StockListAdapter;
import com.example.administrator.xiudoufang.stock.logic.StockLogic;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StockActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;
    public static final String PRODUCT_NO = "product_no";
    private static final int RESULT_FOR_STOCK_QUERY = 112;

    private EditText mEtFilter;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private StockLogic mLogic;
    private HashMap<String, String> mParams;
    private StockListAdapter mAdapter;
    private List<StockListBean.StockBean> mList;
    private int mCurrentPage = 1;
    private String mFilterText = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, StockActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_STOCK_QUERY && data != null) {
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
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock;
    }

    @Override
    public void initView() {
        setTitle("库存");
        mEtFilter = findViewById(R.id.et_filter);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        findViewById(R.id.iv_scan).setOnClickListener(this);
        findViewById(R.id.iv_filter).setOnClickListener(this);
        mEtFilter.addTextChangedListener(new InnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
    }

    @Override
    public void initData() {
        mLogic = new StockLogic();
        mAdapter = new StockListAdapter(R.layout.layout_list_item_stock, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(StockActivity.this, StockDetailsActivity.class);
                intent.putExtra(PRODUCT_NO, mList.get(position).getCpid());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        loadStockList(true);
    }

    private void loadStockList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        if (mParams == null) initParams();
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestStockList(mParams, new JsonCallback<StockListBean>() {
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
        mParams.put("code", "");
        mParams.put("sn", "");
        mParams.put("classname", "");
        mParams.put("supp", "");
        mParams.put("xinghao", "");
        mParams.put("tiaoxingma", "");
        mParams.put("pinpai", "");
        mParams.put("qtystr", "1");
        mParams.put("detail", "");
        mParams.put("scanner", "");
        mParams.put("unitvalue", "");
        mParams.put("searchstr", mFilterText);
        mParams.put("count", String.valueOf(COUNT));
        mParams.put("subchild", "");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_filter:
                Intent i = new Intent(this, StockQueryActivity.class);
                startActivityForResult(i, RESULT_FOR_STOCK_QUERY);
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
}
