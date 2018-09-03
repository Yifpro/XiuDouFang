package com.example.administrator.xiudoufang.open.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.utils.SoftKeyBoardListener;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.open.adapter.SalesProductListAdapter;
import com.example.administrator.xiudoufang.open.logic.SalesOrderLogic;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SalesProductListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;
    public static final String TAG = SalesProductListActivity.class.getSimpleName();
    public static final String SELECTED_PRODUCT_LIST = "selected_product_list";
    public static final String SELECTED_ITEM = "selected_item";

    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFabComplete;
    private FloatingActionButton mFabAction;
    private EditText mEtFilter;
    private ImageView mIvClose;
    private TextView mTvCancel;

    private AnimatorSet menuAnim;
    private SalesOrderLogic mLogic;
    private SalesProductListAdapter mAdapter;
    private List<SalesProductListBean.SalesProductBean> mList;
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
        mEtFilter.addTextChangedListener(new SalesProductListActivity.InnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new SalesProductListActivity.InnerEditActionListener());
        SoftKeyBoardListener.setListener(SalesProductListActivity.this, new InnerSoftKeyBoardChangeListener());
    }

    @Override
    public void initData() {
        mLogic = new SalesOrderLogic();
        mEtFilter.setText(mFilterText = getIntent().getStringExtra(SalesOrderActivity.SEARCH_ITEM));
        menuAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fab_menu_anim);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnLoadMoreListener(new InnerLoadMoreListener());
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mAdapter = new SalesProductListAdapter(R.layout.layout_list_item_product_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        LoadingViewDialog.getInstance().show(this);
        loadProductList(true);
    }

    private void loadProductList(final boolean isFiltering) {
        if (mParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mParams = new HashMap<>();
            mParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
            mParams.put("userdengji", jsonObject.getString("dengji_value"));
            mParams.put("dqcpid", "0");
            mParams.put("count", String.valueOf(COUNT));
            mParams.put("saomiao", "0");
            mParams.put("c_id", getIntent().getStringExtra(SalesOrderActivity.C_ID));
        }
        mParams.put("searchitem", mFilterText);
        mParams.put("pagenum", String.valueOf(mCurrentPage++));
        mLogic.requestProductList(mParams, new JsonCallback<SalesProductListBean>() {
            @Override
            public void onSuccess(Response<SalesProductListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<SalesProductListBean.SalesProductBean> temp = response.body().getChanpinlist();
                for (SalesProductListBean.SalesProductBean bean : temp) {
                    for (SalesProductListBean.SalesProductBean.PacklistBean b: bean.getPacklist()) {
                        if ("1".equals(b.getCheck())) {
                            bean.setFactor(b.getUnit_bilv());
                            bean.setUnitname(b.getUnitname());
                        }
                    }
                }
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

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_filter:
                if (!mEtFilter.isCursorVisible())
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
                mFabAction.setImageResource(mIsShowMenu ? R.mipmap.ic_close_white : R.mipmap.ic_menu_white);
                mIsShowMenu = !mIsShowMenu;
                if (mIsShowMenu) {
                    mFabComplete.setVisibility(View.VISIBLE);
                    menuAnim.setStartDelay(350);
                    menuAnim.start();
                    for (SalesProductListBean.SalesProductBean bean : mList) {
                        bean.setShowSelect(true);
                    }
                    mAdapter.setNewData(mList);
                } else {
                    mFabComplete.setVisibility(View.GONE);
                    for (SalesProductListBean.SalesProductBean bean : mList) {
                        bean.setShowSelect(false);
                    }
                    mAdapter.setNewData(mList);
                }
                break;
            case R.id.fab_complete:
                Intent intent = new Intent();
                ArrayList<SalesProductListBean.SalesProductBean> list = new ArrayList<>();
                for (SalesProductListBean.SalesProductBean bean : mList) {
                    if (bean.isSelected()) {
                        list.add(bean);
                    }
                }
                intent.putParcelableArrayListExtra(SELECTED_PRODUCT_LIST, list);
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
                    LoadingViewDialog.getInstance().show(SalesProductListActivity.this);
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
            Intent intent = new Intent(SalesProductListActivity.this, SalesProductDetailsActivity.class);
            intent.putExtra(SELECTED_ITEM, mList.get(position));
            intent.putExtra(SalesProductDetailsActivity.FROM_CLASS, TAG);
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

