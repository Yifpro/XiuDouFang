package com.example.administrator.xiudoufang.product.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductDetailsBean;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.bean.ProductWeightBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.product.adapter.ProductLevelPriceAdapter;
import com.example.administrator.xiudoufang.product.adapter.ProductSupplierAdapter;
import com.example.administrator.xiudoufang.product.decoration.ProductInfoDecoration;
import com.example.administrator.xiudoufang.product.decoration.ProductSupplierDecoration;
import com.example.administrator.xiudoufang.product.decoration.ProductWeightDecoration;
import com.example.administrator.xiudoufang.product.adapter.ProductInfoAdapter;
import com.example.administrator.xiudoufang.product.adapter.ProductWeightAdapter;
import com.example.administrator.xiudoufang.product.logic.ProductLogic;
import com.example.administrator.xiudoufang.purchase.ui.PicPorchActivity;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String CPID = "cpid";
    public static final String PRODUCT_ID = "product_id";
    public static final String PIC_LIST = "pic_list";
    private static final int RESULT_FOR_PRODUCT_EDIT = 125;

    private ProductLogic mLogic;
    private ProductListBean.ChanpinpicBean mProductBean;
    private RecyclerView mRvBaseInfo;
    private RecyclerView mRvWeight;
    private RecyclerView mRvSupplier;
    private RecyclerView mRvLevelPrice;
    private ImageView mIvIcon;
    private TextView mTvShow;

    private ProductSupplierAdapter mSuppilerAdapter;
    private ProductLevelPriceAdapter mLevelPriceAdapter;
    private boolean mIsShow;

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_details;
    }

    @Override
    public void initView() {
        setTitle("产品详情");
        mRvBaseInfo = findViewById(R.id.rv_base_info);
        mRvWeight = findViewById(R.id.rv_weight);
        mRvSupplier = findViewById(R.id.rv_supplier);
        mRvLevelPrice = findViewById(R.id.rv_level_price);
        mIvIcon = findViewById(R.id.iv_icon);
        mTvShow = findViewById(R.id.tv_show);

        mIvIcon.setOnClickListener(this);
        mTvShow.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(ProductEditActivity.PRODUCT_ICON, mProductBean.getPhotourl());
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_PRODUCT_EDIT && data != null) { //******** 返回更改后的图片 ********
            ArrayList<String> tempList = data.getStringArrayListExtra(ProductEditActivity.PRODUCT_ICON);
            ArrayList<ProductListBean.ChanpinpicBean.PiclistBean> list = new ArrayList<>();
            for (String s : tempList) {
                list.add(new ProductListBean.ChanpinpicBean.PiclistBean(s));
            }
            mProductBean.setPhotourl(tempList.get(0));
            mProductBean.setPiclist(list);
            GlideApp.with(this)
                    .load(mProductBean.getPhotourl().contains("/") ? mProductBean.getPhotourl() : StringUtils.PIC_SMALL_URL + mProductBean.getPhotourl())
                    .error(R.mipmap.ic_error)
                    .into(mIvIcon);
        }
    }

    @Override
    public void initData() {
        mLogic = new ProductLogic();
        mProductBean = getIntent().getParcelableExtra(ProductListActivity.PRODUCT_ITEM);
        mRvSupplier.setVisibility(View.GONE);
        mRvLevelPrice.setVisibility(View.GONE);
        GlideApp.with(this)
                .load(mProductBean.getPhotourl().contains("/") ? mProductBean.getPhotourl() : StringUtils.PIC_SMALL_URL + mProductBean.getPhotourl())
                .error(R.mipmap.ic_error)
                .into(mIvIcon);
        //******** 基础信息列表 ********
        String[] infoKey = {"编号", "类名", "产品名"};
        String[] infoValue = {mProductBean.getStyleno(), mProductBean.getClassname(), mProductBean.getStylename()};
        ArrayList<String> infoList = new ArrayList<>();
        for (int i = 0; i < infoKey.length; i++) {
            infoList.add(infoKey[i]);
            infoList.add(infoValue[i]);
        }
        ProductInfoAdapter infoAdapter = new ProductInfoAdapter(R.layout.layout_list_item_product_base_info, infoList);
        mRvBaseInfo.setAdapter(infoAdapter);
        mRvBaseInfo.setLayoutManager(new GridLayoutManager(this, 2));
        mRvBaseInfo.addItemDecoration(new ProductInfoDecoration());
        //******** 重量列表 ********
        ArrayList<ProductWeightBean> weightList = new ArrayList<>();
        String[] weightArr = {"单位", "比率", "默认单位", "毛重", "净重", "长宽高"};
        for (String s : weightArr) {
            weightList.add(new ProductWeightBean(s));
        }
        for (ProductListBean.ChanpinpicBean.FxUnitlistsBean bean : mProductBean.getFx_unitlists()) {
            ArrayList<String> temp = new ArrayList<>();
            temp.add(bean.getBelongunit());
            temp.add(bean.getBilv()+bean.getUnitname());
            temp.add("1".equals(bean.getIscheck()) ? "是" : "否");
            temp.add(bean.getMaozhong());
            temp.add(bean.getJingzhong());
            temp.add(bean.getWaixiang());
            for (int i = 0; i < weightList.size(); i++) {
                weightList.get(i).getSubItem().add(temp.get(i));
            }
        }
        ProductWeightAdapter weightAdapter = new ProductWeightAdapter(R.layout.layout_list_item_product_weight, weightList);
        mRvWeight.setAdapter(weightAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvWeight.setLayoutManager(layoutManager);
        mRvWeight.addItemDecoration(new ProductWeightDecoration());
        //******** 供应商列表 ********
        mSuppilerAdapter = new ProductSupplierAdapter(R.layout.layout_list_item_product_supplier, null);
        View suppilerHeader = View.inflate(this, R.layout.layout_list_header_product_supplier, null);
        mSuppilerAdapter.addHeaderView(suppilerHeader);
        mRvSupplier.setAdapter(mSuppilerAdapter);
        mRvSupplier.setLayoutManager(new LinearLayoutManager(this));
        mRvSupplier.addItemDecoration(new ProductSupplierDecoration());
        //******** 价格列表 ********
        mLevelPriceAdapter = new ProductLevelPriceAdapter(R.layout.layout_list_item_product_level_price, null);
        View levelPriceHeader = View.inflate(this, R.layout.layout_list_header_product_level_price, null);
        mLevelPriceAdapter.addHeaderView(levelPriceHeader);
        mRvLevelPrice.setAdapter(mLevelPriceAdapter);
        mRvLevelPrice.setLayoutManager(new LinearLayoutManager(this));
        mRvLevelPrice.addItemDecoration(new ProductSupplierDecoration());
        LoadingViewDialog.getInstance().show(this);
        loadProductDetails();
    }

    private void loadProductDetails() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> params = new HashMap<>();
        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("dqcpid", mProductBean.getCpid()); //******** 产品id ********
        params.put("searchitem", "");
        params.put("pagenum", "1");
        params.put("count", "20");
        mLogic.requestProductDetails(this, params, new JsonCallback<ProductDetailsBean>() {
            @Override
            public void onSuccess(Response<ProductDetailsBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                mSuppilerAdapter.setNewData(response.body().getCp_trxprc());
                mLevelPriceAdapter.setNewData(response.body().getCp_levelprice());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_edit:
                ArrayList<String> list = new ArrayList<>();
                for (ProductListBean.ChanpinpicBean.PiclistBean b : mProductBean.getPiclist()) {
                    list.add(b.getPic());
                }
                Intent intent = new Intent(this, ProductEditActivity.class);
                intent.putExtra(CPID, mProductBean.getCpid());
                intent.putExtra(PRODUCT_ID, mProductBean.getStyleno());
                intent.putStringArrayListExtra(PIC_LIST, list);
                startActivityForResult(intent, RESULT_FOR_PRODUCT_EDIT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                ArrayList<String> list = new ArrayList<>();
                for (ProductListBean.ChanpinpicBean.PiclistBean bean : mProductBean.getPiclist()) {
                    list.add(bean.getPic());
                }
                startActivity(new Intent(ProductDetailsActivity.this, PicPorchActivity.class)
                        .putStringArrayListExtra(PicPorchActivity.PIC_LIST, list));
                break;
            case R.id.tv_show:
                mIsShow = !mIsShow;
                mTvShow.setText(mIsShow ? "拓展信息∧" : "拓展信息∨");
                mRvSupplier.setVisibility(mIsShow ? View.VISIBLE : View.GONE);
                mRvLevelPrice.setVisibility(mIsShow ? View.VISIBLE : View.GONE);
                break;
        }
    }
}
