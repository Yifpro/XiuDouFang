package com.example.administrator.xiudoufang.open.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.OtherSetting;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.purchase.ui.ImageSelectorDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by Administrator on 2018/9/6
 */

public class ExtraActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String SELECT_PIC = "select_pic";

    private ImageView mIvIcon;
    private ImageSelectorDialog mImageDialog;
    private TextView mTvClearExtra;

    private String mPath;
    private OtherSetting mOtherSetting;

    @Override
    public int getLayoutId() {
        return R.layout.activity_extra;
    }

    @Override
    public void initView() {
        setTitle("");
        mIvIcon = findViewById(R.id.iv_icon);
        mTvClearExtra = findViewById(R.id.tv_clear_extra);

        findViewById(R.id.tv_select_pic).setOnClickListener(this);
        findViewById(R.id.tv_sure).setOnClickListener(this);
        mTvClearExtra.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) { //******** 附件返回结果 ********
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mPath).into(mIvIcon);
            mTvClearExtra.setClickable(true);
            mTvClearExtra.setSelected(true);
        }
    }

    @Override
    public void initData() {
        mOtherSetting = getIntent().getParcelableExtra(OtherSettingDialog.OTHER_SETTING);
        if (!TextUtils.isEmpty(mOtherSetting.getExtra())) {
            GlideApp.with(this).load(mOtherSetting.getExtra()).into(mIvIcon);
            mTvClearExtra.setSelected(true);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_select_pic:
                if (mImageDialog == null) {
                    mImageDialog = new ImageSelectorDialog();
                }
                mImageDialog.show(getSupportFragmentManager(), "ImageSelectorDialog");
                break;
            case R.id.tv_sure:
                Intent intent = new Intent(this, CreateOrderActivity.class);
                mOtherSetting.setExtra(mPath);
                intent.putExtra(OtherSettingDialog.OTHER_SETTING, mOtherSetting);
                startActivity(intent);
                break;
            case R.id.tv_clear_extra:
                mPath = null;
                mIvIcon.setImageResource(0);
                mTvClearExtra.setClickable(false);
                mTvClearExtra.setSelected(false);
                break;
        }
    }
}
