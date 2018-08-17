package com.example.administrator.xiudoufang.receipt.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.receipt.SoftKeyBoardListener;
import com.example.administrator.xiudoufang.receipt.adapter.CustomerListAdapter;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private EditText mEtFilter;
    private ImageView mIvClose;
    private TextView mTvCancel;

    private CustomerListLogic mLogic;
    private CustomerListAdapter mAdapter;
    private List<CustomerBean.CustomerlistBean> mList;
    private String mFilterText;
    private boolean mIsShowSoftInput;
    private int mCurrentPage = 1;
    private boolean isFiltering = false;
    private List<CustomerBean.CustomerlistBean> mFilterList;

    public static void start(Context context) {
        Intent intent = new Intent(context, CustomerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_list;
    }

    @Override
    public void initView() {
        setTitle("客户列表");
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRecyclerView = findViewById(R.id.recycler_view);
        mEtFilter = findViewById(R.id.et_filter);
        mIvClose = findViewById(R.id.iv_close);
        mTvCancel = findViewById(R.id.tv_cancel);
        mEtFilter.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mEtFilter.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtFilter.addTextChangedListener(new OnInnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new OnInnerEditActionListener());
        SoftKeyBoardListener.setListener(CustomerListActivity.this, new OnInnerSoftKeyBoardChangeListener());
    }

    @Override
    public void initData() {
        mLogic = new CustomerListLogic();
        mAdapter = new CustomerListAdapter(R.layout.layout_list_item_receivables, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(CustomerListActivity.this, PaymentActivity.class);
                intent.putExtra("selected_item", isFiltering ? mFilterList.get(position) : mList.get(position));
                CustomerListActivity.this.startActivity(intent);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadCustomerList(true);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                mRefreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadCustomerList(false);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        LoadingViewDialog.getInstance().show(this);
        loadCustomerList(false);
    }

    private void loadCustomerList(final boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        }
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        String dianid = jsonObject.getString("dianid");
        String dengjiValue = jsonObject.getString("dengji_value");
        String userid = jsonObject.getString("userid");
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", dianid);
        map.put("userdengji", dengjiValue);
        map.put("dqc_id", "0");
        map.put("search", "");
        map.put("pagenum", String.valueOf(mCurrentPage++));
        map.put("count", String.valueOf(COUNT));
        map.put("userid", userid);
        mLogic.requestCustomerList(map, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                CustomerBean customerBean = JSONObject.parseObject(response.body(), CustomerBean.class);
                mList = customerBean.getCustomerlist();
                if (isRefresh) {
                    mAdapter.setNewData(mList);
                    mRefreshLayout.finishRefresh();
                    mRefreshLayout.setNoMoreData(false);
                } else {
                    if (mList == null)
                        mList = new ArrayList<>();
                    mAdapter.addData(mList);
                    if (mList.size() < COUNT) {
                        mRefreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        mRefreshLayout.finishLoadMore();
                    }
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
                    showSoftInput();
                break;
            case R.id.tv_cancel:
                isFiltering = false;
                mRefreshLayout.setEnableRefresh(true);
                mRefreshLayout.setEnableLoadMore(true);
                mEtFilter.setText("");
                mEtFilter.setCursorVisible(false);
                mIvClose.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                hideSoftInput();
                mAdapter.setNewData(mList);
                break;
        }
    }

    private void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mEtFilter.requestFocus();
        imm.showSoftInput(mEtFilter, 0);
    }

    private void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }

    private class OnInnerSoftKeyBoardChangeListener implements SoftKeyBoardListener.OnSoftKeyBoardChangeListener {

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

    private class OnInnerEditActionListener implements TextView.OnEditorActionListener {

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                if (mFilterText.length() != 0) {
                    boolean isAccord = false;
                    mFilterList = new ArrayList<>();
                    for (int i = 0; i < mList.size(); i++) {
                        CustomerBean.CustomerlistBean bean = mList.get(i);
                        String temp = mFilterText.toUpperCase();
                        if (bean.getCustomername().toUpperCase().contains(temp)
                                || bean.getCustomerno().toUpperCase().contains(temp)
                                || bean.getQuyu().toUpperCase().contains(temp)) {
                            LogUtils.e(bean.getCustomerno() + ", " + bean.getCustomername() + ", " + bean.getQuyu() + ", " + bean.isSelected());
                            mFilterList.add(bean);
                            //bean.setSelected(true);
                           // isAccord = true;
                        //} else {
                           // bean.setSelected(false);
                        }
                    }
                    isFiltering = true;
                    mAdapter.setNewData(mFilterList);
                    //mAdapter.setNewData(isAccord ? mList : new ArrayList<CustomerBean.CustomerlistBean>());
                    mRefreshLayout.setEnableRefresh(false);
                    mRefreshLayout.setEnableLoadMore(false);
                    return true;
                }
            }
            return false;
        }
    }

    private class OnInnerTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mFilterText = editable.toString().trim();
            int length = editable.toString().trim().length();
            mIvClose.setVisibility(length > 0 ? View.VISIBLE : View.GONE);
        }
    }
}
