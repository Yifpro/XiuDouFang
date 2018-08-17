package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.example.administrator.xiudoufang.bean.QuYuBean;
import com.example.administrator.xiudoufang.common.utils.SoftInputHelper;
import com.example.administrator.xiudoufang.common.utils.SoftKeyBoardListener;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.purchase.adapter.AreaListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class AreaListActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private EditText mEtFilter;
    private ImageView mIvClose;
    private TextView mTvCancel;

    private AreaListAdapter mAdapter;
    private List<QuYuBean> mList;
    private List<QuYuBean> mTempList;
    private boolean mIsShowSoftInput;
    private boolean mIsFiltering;
    private String mFilterText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_area_list;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        mEtFilter = findViewById(R.id.et_filter);
        mIvClose = findViewById(R.id.iv_close);
        mTvCancel = findViewById(R.id.tv_cancel);
        mEtFilter.setOnClickListener(this);
        mIvClose.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        mEtFilter.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEtFilter.addTextChangedListener(new InnerTextWatcher());
        mEtFilter.setOnEditorActionListener(new InnerEditActionListener());
        SoftKeyBoardListener.setListener(AreaListActivity.this, new InnerSoftKeyBoardChangeListener());
    }

    @Override
    public void initData() {
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        mList = JSONObject.parseArray(jsonObject.getJSONArray("quyu").toJSONString(), QuYuBean.class);
        mAdapter = new AreaListAdapter(R.layout.layout_list_item_are_list, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AreaListActivity.this, AddSupplierActivity.class);
                QuYuBean quYuBean = mIsFiltering ? mTempList.get(position) : mList.get(position);
                intent.putExtra("area_code", quYuBean.getCode());
                intent.putExtra("area_name", quYuBean.getName());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                mIsFiltering = false;
                mFilterText = "";
                mEtFilter.setText("");
                mEtFilter.setCursorVisible(false);
                mIvClose.setVisibility(View.GONE);
                mTvCancel.setVisibility(View.GONE);
                SoftInputHelper.hideSoftInput(this);
                mAdapter.setNewData(mList);
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
                    mIsFiltering = true;
                    if (mTempList == null) {
                        mTempList = new ArrayList<>();
                    } else {
                        mTempList.clear();
                    }
                    for (QuYuBean bean : mList) {
                        if (bean.getCode().contains(mFilterText) || bean.getName().contains(mFilterText)) {
                            mTempList.add(bean);
                        }
                    }
                    mAdapter.setNewData(mTempList);
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
