package com.example.administrator.xiudoufang.product.ui;

import android.Manifest;
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
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.product.adapter.ProductListAdapter;
import com.example.administrator.xiudoufang.product.logic.ProductLogic;
import com.example.administrator.xiudoufang.purchase.ui.ScanActivity;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.functions.Consumer;

public class ProductListActivity extends AppCompatActivity implements IActivityBase {

    private final int COUNT = 20;
    private static final int RESULT_FOR_PRODUCT_QUERY = 114;
    public static final String PRODUCT_ITEM = "product_item";
    private static final int RESULT_FOR_PRODUCT_DETAILS = 126;
    private static final int RESULT_FOR_SCAN_BAR_CODE = 128; //******** 扫描条形码 ********

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ProductLogic mLogic;
    private ProductListAdapter mAdapter;
    private ArrayList<ProductListBean.ChanpinpicBean> mList;
    private int mCurrentPage = 1;
    private HashMap<String, String> mParams;
    private int mIndex;

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
        if (requestCode == RESULT_FOR_PRODUCT_QUERY && data != null) { //******** 返回产品过滤条件 ********
            ProductFilter filter = data.getParcelableExtra(ProductQueryActivity.PRODUCT_FILTER);
            mParams.put("searchitem", filter.getName());
            mParams.put("classid", filter.getTypeId());
            mParams.put("action", filter.getAction());
            mParams.put("nopic", filter.getIsIncludePic());
            mParams.put("c_id", filter.getSupplierId());
            LoadingViewDialog.getInstance().show(this);
            loadProductList(true);
        } else if (requestCode == RESULT_FOR_PRODUCT_DETAILS && data != null) { //******** 返回更改的产品图片 ********
            mList.get(mIndex).setPhotourl(data.getStringExtra(ProductEditActivity.PRODUCT_ICON));
            mAdapter.notifyItemChanged(mIndex);
        } else if (requestCode == ScanActivity.PRODUCT_LIST && data != null) { //******** 返回扫码产品 ********

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
        initParams();
        loadProductList(true);
    }

    //******** 加载产品列表 ********
    private void loadProductList(final boolean isRefresh) {
        if (isRefresh) mCurrentPage = 1;
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mParams.put("count", String.valueOf(COUNT));
        mLogic.requestProductList(this, mParams, new JsonCallback<ProductListBean>() {
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

    private void initParams() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        mParams = new HashMap<>();
        mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        mParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        mParams.put("dqcpid", ""); //******** 产品id ********
        mParams.put("tiaoxingma", ""); //******** 条形码 ********
        mParams.put("searchitem", "");
        mParams.put("classid", ""); //******** 类别id ********
        mParams.put("action", ""); //******** 是否包含子级 ********
        mParams.put("nopic", ""); //******** 是否有图片 ********
        mParams.put("c_id", ""); //******** 供应商id ********
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
                new RxPermissions(this)
                        .requestEach(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    startActivityForResult(new Intent(ProductListActivity.this, ScanActivity.class)
                                            .putExtra(ScanActivity.FROM_CLASS, ScanActivity.PRODUCT_LIST), RESULT_FOR_SCAN_BAR_CODE);
                                } else {
                                    ToastUtils.show(ProductListActivity.this, "请开启权限后重新尝试");
                                }
                            }
                        });
                break;
            case R.id.toolbar_filter:
                startActivityForResult(new Intent(this, ProductQueryActivity.class), RESULT_FOR_PRODUCT_QUERY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
            intent.putExtra(PRODUCT_ITEM, mList.get(position));
            startActivityForResult(intent, RESULT_FOR_PRODUCT_DETAILS);
            mIndex = position;
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
