package com.example.administrator.xiudoufang.purchase.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.utils.SoftKeyBoardListener;
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

public class ProductListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String TAG = ProductListActivity.class.getSimpleName();
    private final int COUNT = 20;

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
    private List<ProductListBean.ProductBean> mList;
    private HashMap<String, String> mParams;
    private String mFilterText = "";
    private boolean mIsShowSoftInput;
    private int mCurrentPage = 1;
    private boolean mIsShowMenu;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_list;
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
        SoftKeyBoardListener.setListener(ProductListActivity.this, new InnerSoftKeyBoardChangeListener());
    }

    @Override
    public void initData() {
        mLogic = new NewPurchaseOrderLogic();
        menuAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fab_menu_anim);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadProductList(false);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new ProductListAdapter(R.layout.layout_list_item_product_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mList.get(position).setSelected(!mList.get(position).isSelected());
                mAdapter.notifyItemChanged(position);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailsActivity.class);
                intent.putExtra(ProductDetailsActivity.FROM_CLASS, TAG);
                intent.putExtra(ProductDetailsActivity.SELECTED_PRODUCT_BEAN, mList.get(position));
                startActivity(intent);
            }
        });
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoadingViewDialog.getInstance().show(this);
        loadProductList(false);
    }

    private void loadProductList(final boolean isFiltering) {
        if (mParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mParams = new HashMap<>();
            mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            mParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            mParams.put("c_id", "0");
            mParams.put("count", String.valueOf(COUNT));
            mParams.put("saomiao", "0");
            mParams.put("dqcpid", "0");
        }
        mParams.put("serchitem", mFilterText);
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestProductList(mParams, new JsonCallback<ProductListBean>() {
            @Override
            public void onSuccess(Response<ProductListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<ProductListBean.ProductBean> temp = response.body().getPo_chanpinlist();
                if (isFiltering) {
                    mList.clear();
                    mList.addAll(temp);
                    mAdapter.setNewData(mList);
                } else {
                    if (mList == null)
                        mList = new ArrayList<>();
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
                mFabAction.setImageResource(mIsShowMenu ? R.mipmap.ic_close_white : R.mipmap.ic_menu);
                mIsShowMenu = !mIsShowMenu;
                if (mIsShowMenu) {
                    mFabComplete.setVisibility(View.VISIBLE);
                    menuAnim.setStartDelay(350);
                    menuAnim.start();
                    for (ProductListBean.ProductBean bean : mList) {
                        bean.setShowSelect(true);
                    }
                    mAdapter.setNewData(mList);
                } else {
                    mFabComplete.setVisibility(View.GONE);
                    for (ProductListBean.ProductBean bean : mList) {
                        bean.setShowSelect(false);
                    }
                    mAdapter.setNewData(mList);
                }
                break;
            case R.id.fab_complete:
                Intent intent = new Intent();
                ArrayList<ProductItem> list = new ArrayList<>();
                for (ProductListBean.ProductBean bean : mList) {
                    if (bean.isSelected()) {
                        ProductItem item = new ProductItem();
                        item.setPhotourl(bean.getPhotourl());
                        item.setId(bean.getCpid());
                        item.setProductNo(bean.getStyleno());
                        item.setStylename(bean.getStylename());
                        item.setColor("");
                        item.setSize("");
                        String factor = "", unit = "";
                        for (ProductListBean.ProductBean.PacklistBean b : bean.getPacklist()) {
                            if ("1".equals(b.getCheck())) {
                                factor = b.getUnit_bilv();
                                unit = b.getUnitname();
                            }
                        }
                        ProductListBean.ProductBean.LishijialistBean historyBean = bean.getLishijialist().get(bean.getLishijialist().indexOf(new ProductListBean.ProductBean.LishijialistBean(factor, unit)));
                        item.setFactor(factor);
                        item.setUnit(unit);
                        item.setAmount("1");
                        item.setSinglePrice(historyBean.getPrice());
                        item.setUnitPrice(historyBean.getPrice());
                        item.setTip("");
                        item.setGoodsNo("");
                        item.setPriceCode(historyBean.getPricecode());
                        item.setPriceSource("历史价");
                        list.add(item);
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
                    LoadingViewDialog.getInstance().show(ProductListActivity.this);
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
}
