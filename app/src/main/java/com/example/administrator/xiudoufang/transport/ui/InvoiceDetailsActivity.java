package com.example.administrator.xiudoufang.transport.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.InvoiceListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.purchase.ui.ImageSelectorDialog;
import com.example.administrator.xiudoufang.transport.logic.InvoiceDetailsLogic;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29
 */

public class InvoiceDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private ImageView mIvIcon;
    private EditText mEtTransportNum;
    private TextView mTvClearTransportNum;
    private ImageSelectorDialog mImageDialog;

    private InvoiceDetailsLogic mLogic;
    private String mPicPath;
    private InvoiceListBean.InvoiceBean mInvoiceBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invoice_details;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(InvoiceListActivity.SELECTED_ITEM, mInvoiceBean);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mPicPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mPicPath).into(mIvIcon);
            if (!mTvClearTransportNum.isSelected()) setClearTransportNumStatus(true);
        }
    }

    @Override
    public void initView() {
        setTitle("运号查看");
        mIvIcon = findViewById(R.id.iv_icon);
        mEtTransportNum = findViewById(R.id.et_transport_num);
        mTvClearTransportNum = findViewById(R.id.tv_clear_transport_num);

        findViewById(R.id.iv_clear).setOnClickListener(this);
        findViewById(R.id.tv_select_pic).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        mEtTransportNum.setOnClickListener(this);
        mTvClearTransportNum.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mLogic = new InvoiceDetailsLogic();
        mInvoiceBean = getIntent().getParcelableExtra(InvoiceListActivity.SELECTED_ITEM);
        GlideApp.with(this).load(StringUtils.PIC_URL + mInvoiceBean.getKuaidi_pic()).error(R.mipmap.ic_icon).into(mIvIcon);
        mEtTransportNum.setText(mInvoiceBean.getYunhao());
        //******** 返回默认图片且运号为空，则该运单已被清除过 ********
        boolean isCanNotClearTransportNum = "nopic.png".equals(mInvoiceBean.getKuaidi_pic()) && TextUtils.isEmpty(mInvoiceBean.getYunhao());
        setClearTransportNumStatus(!isCanNotClearTransportNum);
    }

    public void setClearTransportNumStatus(boolean isCanClear) {
        mTvClearTransportNum.setSelected(isCanClear);
        mTvClearTransportNum.setClickable(isCanClear);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                mEtTransportNum.setText("");
                break;
            case R.id.tv_select_pic:
                if (mImageDialog == null) {
                    mImageDialog = new ImageSelectorDialog();
                }
                mImageDialog.show(getSupportFragmentManager(), "ImageSelectorDialog");
                break;
            case R.id.et_transport_num:
                mEtTransportNum.setCursorVisible(true);
                break;
            case R.id.tv_confirm:
                if (StringUtils.DEFAULT_PIC.equals(mInvoiceBean.getKuaidi_pic()) && mPicPath == null && TextUtils.isEmpty(mEtTransportNum.getText().toString())) {
                    ToastUtils.show(this, "运号和运号图片不能同时为空");
                } else {
                    LoadingViewDialog.getInstance().show(this);
                    mLogic.changeTransportNum(mInvoiceBean.getId(), mEtTransportNum.getText().toString(), mPicPath, new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            LogUtils.e("修改运号 -> " + response.body());
                            LoadingViewDialog.getInstance().dismiss();
                            JSONObject jsonObject = JSONObject.parseObject(response.body());
                            //******** 修改成功->修改对象、恢复清除按钮 ********
                            if ("1".equals(jsonObject.getString("status"))) {
                                if (mPicPath != null) mInvoiceBean.setKuaidi_pic(mPicPath);
                                mInvoiceBean.setYunhao(mEtTransportNum.getText().toString());
                                setClearTransportNumStatus(true);
                            }
                            ToastUtils.show(InvoiceDetailsActivity.this, "1".equals(jsonObject.getString("status")) ? "提交成功" : "提交失败");
                        }
                    });
                }
                break;
            case R.id.tv_clear_transport_num:
                mLogic.deleteTransportNum(mInvoiceBean.getId(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("删除运号 -> " + response.body());
                        LoadingViewDialog.getInstance().dismiss();
                        JSONObject jsonObject = JSONObject.parseObject(response.body());
                        //******** 删除成功->修改对象、清除值、清除按钮不可再次点击 ********
                        if ("1".equals(jsonObject.getString("status"))) {
                            mInvoiceBean.setKuaidi_pic(StringUtils.DEFAULT_PIC);
                            mInvoiceBean.setYunhao("");
                            mIvIcon.setImageResource(0);
                            mEtTransportNum.setText("");
                            setClearTransportNumStatus(false);
                        }
                        ToastUtils.show(InvoiceDetailsActivity.this, "1".equals(jsonObject.getString("status")) ? "提交成功" : jsonObject.getString("messages"));
                    }
                });
                break;
        }
    }
}
