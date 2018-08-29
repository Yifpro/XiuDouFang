package com.example.administrator.xiudoufang.product.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductFilter;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.product.adapter.ProductListAdapter;
import com.example.administrator.xiudoufang.product.logic.ProductLogic;
import com.example.administrator.xiudoufang.purchase.ui.ScanActivity;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductListActivity extends AppCompatActivity implements IActivityBase {

    private final int COUNT = 20;
    private static final int RESULT_FOR_PRODUCT_QUERY = 114;
    public static final String PRODUCT_ITEM = "product_item";

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ProductLogic mLogic;
    private ProductListAdapter mAdapter;
    private ArrayList<ProductListBean.ChanpinpicBean> mList;
    private int mCurrentPage = 1;
    private HashMap<String, String> mParams;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_PRODUCT_QUERY && data != null) {
            ProductFilter filter = data.getParcelableExtra(ProductQueryActivity.PRODUCT_FILTER);
            mParams.put("searchitem", filter.getName());
            mParams.put("classid", filter.getTypeId());
            mParams.put("action", filter.getAction());
            mParams.put("nopic", filter.getIsIncludePic());
            mParams.put("c_id", filter.getSupplierId());
            LoadingViewDialog.getInstance().show(this);
            loadProductList(true);
        }
    }

    @Override
    public void initView() {
        setTitle("产品");
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        mLogic = new ProductLogic();
        mAdapter = new ProductListAdapter(R.layout.layout_list_item_product, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRefreshLayout.setOnRefreshListener(new InnerRefreshListener());
        mRefreshLayout.setOnLoadMoreListener(new InnerLoadMoreListener());
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        loadProductList(true);
    }

    private void loadProductList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        if (mParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mParams = new HashMap<>();
            mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            mParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            mParams.put("dqcpid", "");
            mParams.put("tiaoxingma", "");
            mParams.put("searchitem", "");
            mParams.put("classid", "");
            mParams.put("action", "");
            mParams.put("nopic", "");
            mParams.put("c_id", "");
        }
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mParams.put("count", String.valueOf(COUNT));
        mLogic.requestProductList(mParams, new JsonCallback<ProductListBean>() {
            @Override
            public void onSuccess(Response<ProductListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<ProductListBean.ChanpinpicBean> temp = response.body().getChanpinpic();
                if (isRefresh) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(mList.size() < COUNT);
                } else {
                    mList.addAll(temp);
                    mAdapter.addData(temp);
                    if (temp.size() < COUNT) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        mRefreshLayout.finishLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_scan:
                Intent intent = new Intent(this, ScanActivity.class);
                startActivity(intent);
                break;
            case R.id.toolbar_filter:
                Intent i = new Intent(this, ProductQueryActivity.class);
                startActivityForResult(i, RESULT_FOR_PRODUCT_QUERY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ITEM, mList.get(position));
            startActivity(intent);
        }
    }

    private class InnerRefreshListener implements OnRefreshListener {

        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            mRefreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadProductList(true);
                }
            }, 2000);
        }
    }

    private class InnerLoadMoreListener implements OnLoadMoreListener {

        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            mRefreshLayout.getLayout().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadProductList(false);
                }
            }, 2000);
        }
    }
}