package com.example.administrator.xiudoufang.transport.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.InvoiceListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
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
    private ImageSelectorDialog mImageDialog;

    private InvoiceDetailsLogic mLogic;
    private String mPicPath;
    private InvoiceListBean.InvoiceBean mInvoiceBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invoice_details;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mPicPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mPicPath).into(mIvIcon);
        }
    }

    @Override
    public void initView() {
        setTitle("运号查看");
        mIvIcon = findViewById(R.id.iv_icon);
        mEtTransportNum = findViewById(R.id.et_transport_num);

        findViewById(R.id.iv_clear).setOnClickListener(this);
        findViewById(R.id.tv_select_pic).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        findViewById(R.id.tv_clear_no).setOnClickListener(this);
    }

    @Override
    public void initData() {
        mLogic = new InvoiceDetailsLogic();
        mInvoiceBean = getIntent().getParcelableExtra(InvoiceListActivity.SELECTED_ITEM);
        GlideApp.with(this).load(StringUtils.PIC_URL + mInvoiceBean.getKuaidi_pic()).error(R.mipmap.ic_icon).into(mIvIcon);
        mEtTransportNum.setText(mInvoiceBean.getYunhao());
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
            case R.id.tv_confirm:
                LogUtils.e("emm -> " + mInvoiceBean.getId()+", " + mInvoiceBean.getYunhao());
                mLogic.changeTransportNum(mInvoiceBean.getId(), mEtTransportNum.getText().toString(), mPicPath, new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("修改单号 -> " + response.body());
                    }
                });
                break;
            case R.id.tv_clear_no:
                mLogic.deleteTransportNum(mInvoiceBean.getId(), new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.e("删除单号 -> " + response.body());
                    }
                });
                break;
        }
    }
}
