package com.example.administrator.xiudoufang.product.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.PicBean;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.product.adapter.ProductEditAdapter;
import com.example.administrator.xiudoufang.product.logic.ProductLogic;
import com.example.administrator.xiudoufang.purchase.ui.ImageSelectorDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductEditActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;
    private ImageSelectorDialog mImageDialog;

    private ProductLogic mLogic;
    private List<PicBean> mList;
    private ProductEditAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_edit;
    }

    @Override
    public void initView() {
        setTitle(getIntent().getStringExtra(ProductDetailsActivity.PRODUCT_ID));
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) { //******** 附件返回结果 ********
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            String picPath = selectList.get(0).getCompressPath();
            mList.add(mList.size() - 1, new PicBean(picPath, true));
            mAdapter.setNewData(mList);
        }
    }

    @Override
    public void initData() {
        mLogic = new ProductLogic();
        ArrayList<String> picList = getIntent().getStringArrayListExtra(ProductDetailsActivity.PIC_LIST);
        mList = new ArrayList<>();
        for (String s : picList) {
            mList.add(new PicBean(s, false));
        }
        mList.add(new PicBean(R.mipmap.ic_extra_place, true));
        mAdapter = new ProductEditAdapter(R.layout.layout_list_item_product_edit, mList);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    public void onClick(View view) {
        List<File> list = new ArrayList<>();
        if (mList.size() > 0) {
            for (PicBean p : mList) {
                if (p.getPic() instanceof String && p.isLocal()) {
                    list.add(new File((String) p.getPic()));
                }
            }
        }
        LoadingViewDialog.getInstance().show(this);
        mLogic.uploadProductPic(getIntent().getStringExtra(ProductDetailsActivity.CPID), list, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LogUtils.e("上传图片 -> " + response.body());
                LoadingViewDialog.getInstance().dismiss();
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                if (!"1".equals(jsonObject.getString("messagestr"))) {
                    ToastUtils.show(ProductEditActivity.this, jsonObject.getString("messagestr"));
                }
                finish();
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                LogUtils.e("上传失败"+response.body());
            }
        });
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (position == mList.size() - 1) {
                if (mImageDialog == null) {
                    mImageDialog = new ImageSelectorDialog();
                }
                mImageDialog.show(getSupportFragmentManager(), "ImageSelectorDialog");
            }
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            mList.remove(position);
            adapter.setNewData(mList);
        }
    }
}
