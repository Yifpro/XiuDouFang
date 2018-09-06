package com.example.administrator.xiudoufang.open.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_0;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_1;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_2;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_3;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_4;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.SingleLineTextDialog;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String SELECTED_ITEM = "selected_item"; //******** 获取选中产品的标识 ********
    public static final String FROM_CLASS = "from_class";

    private RecyclerView mRecyclerView;
    private SingleLineTextDialog mUnitDialog;
    private SingleLineTextDialog mColorDialog;
    private SingleLineTextDialog mSizeDialog;

    private SalesProductListBean.SalesProductBean mProductBean;
    private SalesProductDetailsAdapter mAdapter;
    private ArrayList<MultiItemEntity> mList;
    private ArrayList<String> mUnits;
    private ArrayList<String> mColors;
    private ArrayList<String> mSizes;
    private boolean isHaveColor;
    private boolean isHaveSize;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sales_product_details;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        if (SalesProductListActivity.TAG.equals(getIntent().getStringExtra(SalesProductDetailsActivity.FROM_CLASS))) {
            findViewById(R.id.bottom_layout).setVisibility(View.GONE);
        } else {
            findViewById(R.id.bottom_layout).setVisibility(View.VISIBLE);
            TextView tvBottomLeft = findViewById(R.id.tv_bottom_left);
            TextView tvBottomRight = findViewById(R.id.tv_bottom_right);
            tvBottomLeft.setText("取消");
            tvBottomRight.setText("确认");
            tvBottomLeft.setOnClickListener(this);
            tvBottomRight.setOnClickListener(this);
        }
        mProductBean = getIntent().getParcelableExtra(SalesProductListActivity.SELECTED_ITEM);
        mList = new ArrayList<>();
        mList.add(new SalesProductDetailsItem_0(mProductBean.getPhotourl()));
        mList.add(new SalesProductDetailsItem_1("产品编号", mProductBean.getStyleno()));
        mList.add(new SalesProductDetailsItem_1("产品名称", mProductBean.getStylename()));
        mList.add(new SalesProductDetailsItem_1("类别", mProductBean.getClassname()));
        mList.add(new SalesProductDetailsItem_1("详述", mProductBean.getDetail()));
        mList.add(new SalesProductDetailsItem_1("型号", mProductBean.getXinghao()));
        mList.add(new SalesProductDetailsItem_1("品牌", mProductBean.getPinpai()));
        mList.add(new SalesProductDetailsItem_1("条形码", mProductBean.getBarcode()));
        mUnits = new ArrayList<>();
        for (int i = 0; i < mProductBean.getPacklist().size(); i++) {
            SalesProductListBean.SalesProductBean.PacklistBean bean = mProductBean.getPacklist().get(i);
            mUnits.add(bean.getUnit_bilv() + bean.getUnitname());
        }
        mList.add(new SalesProductDetailsItem_2("销售单位", mProductBean.getFactor() + mProductBean.getUnitname(), mUnits, true, new InnerUnitClickListener()));
        if (mProductBean.getColorlist() != null && mProductBean.getColorlist().size() > 0) {
            mColors = new ArrayList<>();
            mColors.add("无");
            for (SalesProductListBean.SalesProductBean.ColorlistBean bean : mProductBean.getColorlist()) {
                mColors.add(bean.getColor());
            }
            mList.add(new SalesProductDetailsItem_2("产品颜色", TextUtils.isEmpty(mProductBean.getYanse()) ? mColors.get(0) : mProductBean.getYanse(), mColors, true, new InnerColorClickListener()));
            isHaveColor = true;
        }
        if (mProductBean.getSizxlist() != null && mProductBean.getSizxlist().size() > 0) {
            mSizes = new ArrayList<>();
            mSizes.add("无");
            for (SalesProductListBean.SalesProductBean.SizxlistBean bean : mProductBean.getSizxlist()) {
                mSizes.add(bean.getSizx());
            }
            mList.add(new SalesProductDetailsItem_2("产品规格", TextUtils.isEmpty(mProductBean.getGuige()) ? mSizes.get(0) : mProductBean.getGuige(), mSizes, true, new InnerSizeClickListener()));
            isHaveSize = true;
        }
        mList.add(new SalesProductDetailsItem_2("购买数量", mProductBean.getCp_qty(), false));
        mList.add(new SalesProductDetailsItem_2("单位价", mProductBean.getOrder_prc(), false));
        mList.add(new SalesProductDetailsItem_2("单品价格", mProductBean.getS_jiage2(), true));
        mList.add(new SalesProductDetailsItem_2("折扣", mProductBean.getZhekou(), false));
        ArrayList<String> gift = new ArrayList<>();
        gift.add("否");
        gift.add("是");
        mList.add(new SalesProductDetailsItem_3("是否为赠品", "1".equals(mProductBean.getZengpin()) ? gift.get(1) : gift.get(0), gift));
        ArrayList<String> priceSources = new ArrayList<>();
        priceSources.add("手动");
        priceSources.add("等级");
        priceSources.add("批发");
        priceSources.add("参考");
        priceSources.add("历史");
        mList.add(new SalesProductDetailsItem_3("价格来源", TextUtils.isEmpty(mProductBean.getJiagelaiyuan()) ? priceSources.get(1) : mProductBean.getJiagelaiyuan(), priceSources));
        mList.add(new SalesProductDetailsItem_4("备注", mProductBean.getBz()));
        mList.add(new SalesProductDetailsItem_4("客户货号", mProductBean.getHuohao()));
        mAdapter = new SalesProductDetailsAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!SalesOrderActivity.TAG.equals(getIntent().getStringExtra(SalesProductDetailsActivity.FROM_CLASS)))
            getMenuInflater().inflate(R.menu.menu_sales_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_sure:
                submitProduct(false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bottom_left:
                finish();
                break;
            case R.id.tv_bottom_right:
                submitProduct(true);
                break;
        }
    }

    private void submitProduct(boolean isChanged) {
        SalesProductDetailsItem_2 discountItem = (SalesProductDetailsItem_2) mList.get(14);
        double discount = Double.parseDouble(discountItem.getValue());
        if (discountItem.getValue().length() > 4) {
            ToastUtils.show(this, "仅允许保留小数点后两位");
        } else if (discount < 0 || discount > 1) {
            ToastUtils.show(this, "折扣输入不合法，请输入0-1数字");
        }
        SalesProductDetailsItem_2 colorItem = (SalesProductDetailsItem_2) mList.get(9);
        if (isHaveColor && !"无".equals(colorItem.getValue())) {
            mProductBean.setYanse(colorItem.getValue());
        }
        SalesProductDetailsItem_2 sizeItem = (SalesProductDetailsItem_2) mList.get(10);
        if (isHaveSize && !"无".equals(sizeItem.getValue())) {
            mProductBean.setGuige(sizeItem.getValue());
        }
        StringBuilder factorBuilder = new StringBuilder();
        StringBuilder unitBuilder = new StringBuilder();
        SalesProductDetailsItem_2 unitItem = (SalesProductDetailsItem_2) mList.get(8);
        for (Character c : unitItem.getValue().toCharArray()) {
            if (c >= '0' && c <= '9') {
                factorBuilder.append(c);
            } else {
                unitBuilder.append(c);
            }
        }
        mProductBean.setFactor(factorBuilder.toString());
        mProductBean.setUnitname(unitBuilder.toString());
        SalesProductDetailsItem_2 cpQtyItem = (SalesProductDetailsItem_2) mList.get(11);
        mProductBean.setCp_qty(cpQtyItem.getValue());
        SalesProductDetailsItem_2 unitPriceItem = (SalesProductDetailsItem_2) mList.get(12);
        mProductBean.setS_jiage2(unitPriceItem.getValue());
        mProductBean.setZhekou(discountItem.getValue());
        SalesProductDetailsItem_3 giftItem = (SalesProductDetailsItem_3) mList.get(15);
        mProductBean.setZengpin("否".equals(giftItem.getValue()) ? "0" : "1");
        SalesProductDetailsItem_3 priceSourceItem = (SalesProductDetailsItem_3) mList.get(16);
        mProductBean.setJiagelaiyuan(priceSourceItem.getValue());
        SalesProductDetailsItem_4 bzItem = (SalesProductDetailsItem_4) mList.get(17);
        mProductBean.setBz(bzItem.getValue());
        SalesProductDetailsItem_4 huohaoItem = (SalesProductDetailsItem_4) mList.get(18);
        mProductBean.setHuohao(huohaoItem.getValue());
        Intent intent = new Intent(this, SalesOrderActivity.class);
        intent.putExtra(SELECTED_ITEM, mProductBean);
        if (isChanged) {
            setResult(Activity.RESULT_OK, intent);
            finish();
        } else {
            startActivity(intent);
        }
    }

    private class InnerUnitClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mUnitDialog == null) {
                mUnitDialog = SingleLineTextDialog.newInstance(mUnits);
                mUnitDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ((SalesProductDetailsItem_2) mList.get(8)).setValue(mUnits.get(position));
                        mAdapter.notifyItemChanged(8);
                    }
                });
            }
            mUnitDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
        }
    }

    private class InnerColorClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mColorDialog == null) {
                mColorDialog = SingleLineTextDialog.newInstance(mColors);
                mColorDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ((SalesProductDetailsItem_2) mList.get(9)).setValue(mColors.get(position));
                        mAdapter.notifyItemChanged(9);

                    }
                });
            }
            mColorDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
        }
    }

    private class InnerSizeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mSizeDialog == null) {
                mSizeDialog = SingleLineTextDialog.newInstance(mSizes);
                mSizeDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        ((SalesProductDetailsItem_2) mList.get(10)).setValue(mSizes.get(position));
                        mAdapter.notifyItemChanged(10);
                    }
                });
            }
            mSizeDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
        }
    }
}
