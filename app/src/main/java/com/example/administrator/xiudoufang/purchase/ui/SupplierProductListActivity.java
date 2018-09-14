package com.example.administrator.xiudoufang.purchase.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.utils.SoftKeyBoardListener;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.adapter.ProductListAdapter;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SupplierProductListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;
    public static final String FROM_CLASS = "from_class";
    public static final String SUPPLIER_ID = "supplier_id";

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFabComplete;
    private FloatingActionButton mFabAction;
    private EditText mEtFilter;
    private ImageView mIvClose;
    private TextView mTvCancel;

    private AnimatorSet menuAnim;
    private NewPurchaseOrderLogic mLogic;
    private ProductListAdapter mAdapter;
    private List<ProductItem> mList;
    private HashMap<String, String> mParams;
    private String mFilterText = "";
    private boolean mIsShowSoftInput;
    private int mCurrentPage = 1;
    private boolean mIsShowMenu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_product_list;
    }

    @Override
    public void initView() {
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mEtFilter = findViewById(R.id.et_filter);
        mIvClose = findViewById(R.id.iv_close);
        mTvCancel = findViewById(R.id.tv_cancel);
        mFabComplete = findViewById(R.id.fab_complete);
        mFabAction = findViewById(R.id.fab_action);

        mFabAction.setOnClickListener(this);
        mFabComplete.setOnClickListener(this);
        mEtFilter.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mEtFilter.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtFilter.addTextChangedListener(new InnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
        SoftKeyBoardListener.setListener(SupplierProductListActivity.this, new InnerSoftKeyBoardChangeListener());
    }

    @Override
    public void initData() {
        mLogic = new NewPurchaseOrderLogic();
        menuAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fab_menu_anim);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnLoadMoreListener(new InnerLoadMoreListener());
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new ProductListAdapter(R.layout.layout_list_item_product_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        initParams();
        loadProductList(true);
    }

    //******** 加载产品列表 ********
    private void loadProductList(final boolean isFiltering) {
        mParams.put("serchitem", mFilterText);
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestProductList(this, mParams, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                ArrayList<ProductItem> temp = mLogic.parseProductListJson(JSONObject.parseObject(response.body()));
                if (isFiltering) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                } else {
                    mList.addAll(temp);
                    mAdapter.addData(temp);
                }
                if (mList.size() < COUNT) {
                    mRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    mRefreshLayout.finishLoadMore();
                }
            }
        });
    }

    private void initParams() {
        mParams = new HashMap<>();
        mParams.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        mParams.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        mParams.put("c_id", getIntent().getStringExtra(SUPPLIER_ID)); //******** 供应商id ********
        mParams.put("count", String.valueOf(COUNT));
        mParams.put("saomiao", "0"); //******** 是否扫描 ********
        mParams.put("dqcpid", "0"); //******** 产品id ********
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_filter:
                mEtFilter.setCursorVisible(true);
                break;
            case R.id.iv_close:
                mEtFilter.setText("");
                if (!mIsShowSoftInput)
                    SoftInputHelper.showSoftInput(this, mEtFilter);
                break;
            case R.id.tv_cancel:
                mFilterText = "";
                mEtFilter.setText("");
                mEtFilter.setCursorVisible(false);
                mIvClose.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                SoftInputHelper.hideSoftInput(this);
                LoadingViewDialog.getInstance().show(this);
                mCurrentPage = 1;
                loadProductList(true);
                break;
            case R.id.fab_action:
                mFabAction.setImageResource(mIsShowMenu ? R.mipmap.ic_close_circle_white : R.mipmap.ic_menu_white);
                mIsShowMenu = !mIsShowMenu;
                if (mIsShowMenu) {
                    mFabComplete.setVisibility(View.VISIBLE);
                    menuAnim.setStartDelay(350);
                    menuAnim.start();
                    for (ProductItem bean : mList) {
                        bean.setShowSelect(true);
                    }
                    mAdapter.setNewData(mList);
                } else {
                    mFabComplete.setVisibility(View.GONE);
                    for (ProductItem bean : mList) {
                        bean.setShowSelect(false);
                    }
                    mAdapter.setNewData(mList);
                }
                break;
            case R.id.fab_complete:
                Intent intent = new Intent();
                ArrayList<ProductItem> list = new ArrayList<>();
                for (ProductItem bean : mList) {
                    if (bean.isSelected()) {
                        bean.setCp_qty("1");
                        list.add(bean);
                    }
                }
                intent.putParcelableArrayListExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT_LIST, list);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
        }
    }

    private class InnerSoftKeyBoardChangeListener implements SoftKeyBoardListener.OnSoftKeyBoardChangeListener {

        @Override
        public void keyBoardShow(int height) {
            mTvCancel.setVisibility(View.VISIBLE);
            mIsShowSoftInput = true;
        }

        @Override
        public void keyBoardHide(int height) {
            mIsShowSoftInput = false;
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
                    LoadingViewDialog.getInstance().show(SupplierProductListActivity.this);
                    mCurrentPage = 1;
                    loadProductList(true);
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
            int length = editable.toString().trim().length();
            mIvClose.setVisibility(length > 0 ? View.VISIBLE : View.GONE);
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

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if ("1".equals(mList.get(position).getStop_sales())) {
                ToastUtils.show(SupplierProductListActivity.this, "该产品已停售");
                return;
            }
            Intent intent = new Intent(SupplierProductListActivity.this, SupplierProductDetailsActivity.class);
            int tag = PurchaseDetailsActivity.TAG.equals(getIntent().getStringExtra(FROM_CLASS)) ? SupplierProductDetailsActivity.ADD_PRODUCT_FOR_PURCHASE_DETAILS : SupplierProductDetailsActivity.ADD_PRODUCT_FOR_NEW_ORDER;
            intent.putExtra(SupplierProductDetailsActivity.TAG, tag);
            intent.putExtra(SupplierProductDetailsActivity.SELECTED_PRODUCT_ITEM, mList.get(position));
            startActivity(intent);
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            mList.get(position).setSelected(!mList.get(position).isSelected());
            mAdapter.notifyItemChanged(position);
        }
    }
}
