package com.example.administrator.xiudoufang.open.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.example.administrator.xiudoufang.common.widget.CustomPopWindow;
import com.example.administrator.xiudoufang.common.widget.SingleLineTextDialog;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String SELECTED_ITEM = "selected_item";
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
        if (!SalesOrderActivity.TAG.equals(getIntent().getStringExtra(SalesProductDetailsActivity.FROM_CLASS))) {
            findViewById(R.id.bottom_layout).setVisibility(View.GONE);
        } else {
            TextView tvBottomLeft = findViewById(R.id.tv_bottom_left);
            TextView tvBottomRight = findViewById(R.id.tv_bottom_right);
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
        int unitIndex = 0;
        for (int i = 0; i < mProductBean.getPacklist().size(); i++) {
            SalesProductListBean.SalesProductBean.PacklistBean bean = mProductBean.getPacklist().get(i);
            if ("1".equals(bean.getCheck())) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                String price = decimalFormat.format(Double.parseDouble(bean.getDengji_price()));
                mProductBean.setOrder_prc(price);
                mProductBean.setS_jiage2(price);
                unitIndex = i;
            }
            mUnits.add(bean.getUnit_bilv() + bean.getUnitname());
        }
        mList.add(new SalesProductDetailsItem_2("销售单位", mUnits.get(unitIndex), mUnits, true, new InnerUnitClickListener()));
        if (mProductBean.getColorlist() != null && mProductBean.getColorlist().size() > 0) {
            mColors = new ArrayList<>();
            mColors.add("无");
            for (SalesProductListBean.SalesProductBean.ColorlistBean bean : mProductBean.getColorlist()) {
                mColors.add(bean.getColor());
            }
            mList.add(new SalesProductDetailsItem_2("产品颜色", mColors.get(0), mColors, true, new InnerColorClickListener()));
            isHaveColor = true;
        }
        if (mProductBean.getSizxlist() != null && mProductBean.getSizxlist().size() > 0) {
            mSizes = new ArrayList<>();
            mSizes.add("无");
            for (SalesProductListBean.SalesProductBean.SizxlistBean bean : mProductBean.getSizxlist()) {
                mSizes.add(bean.getSizx());
            }
            mList.add(new SalesProductDetailsItem_2("产品规格", mSizes.get(0), mSizes, true, new InnerSizeClickListener()));
            isHaveSize = true;
        }
        mList.add(new SalesProductDetailsItem_2("购买数量", mProductBean.getCp_qty(), false));
        mList.add(new SalesProductDetailsItem_2("单位价", mProductBean.getOrder_prc(), false));
        mList.add(new SalesProductDetailsItem_2("单品价格", mProductBean.getOrder_prc(), true));
        mList.add(new SalesProductDetailsItem_2("折扣", mProductBean.getZhekou(), false));
        ArrayList<String> gift = new ArrayList<>();
        gift.add("否");
        gift.add("是");
        mList.add(new SalesProductDetailsItem_3("是否为赠品", gift.get(0), gift));
        ArrayList<String> priceSources = new ArrayList<>();
        priceSources.add("手动");
        priceSources.add("等级");
        priceSources.add("批发");
        priceSources.add("参考");
        priceSources.add("历史");
        mList.add(new SalesProductDetailsItem_3("价格来源", priceSources.get(1), priceSources));
        mList.add(new SalesProductDetailsItem_4("备注", ""));
        mList.add(new SalesProductDetailsItem_4("客户货号", ""));
        mAdapter = new SalesProductDetailsAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sales_product_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_sure:
                submitProduct();
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
                submitProduct();
                break;
        }
    }

    private void submitProduct() {
        SalesProductDetailsItem_2 colorItem = (SalesProductDetailsItem_2) mList.get(9);
        mProductBean.setYanse(isHaveColor ? "无".equals(colorItem.getValue()) ? "" : colorItem.getValue() : "");
        SalesProductDetailsItem_2 sizeItem = (SalesProductDetailsItem_2) mList.get(10);
        mProductBean.setGuige(isHaveSize ? "无".equals(sizeItem.getValue()) ? "" : sizeItem.getValue() : "");
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
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        mProductBean.setS_jiage2(decimalFormat.format(Double.parseDouble(unitPriceItem.getValue())));
        SalesProductDetailsItem_2 discountItem = (SalesProductDetailsItem_2) mList.get(14);
        mProductBean.setZhekou(discountItem.getValue());
        SalesProductDetailsItem_3 giftItem = (SalesProductDetailsItem_3) mList.get(15);
        mProductBean.setZengpin(giftItem.getValue());
        SalesProductDetailsItem_3 priceSourceItem = (SalesProductDetailsItem_3) mList.get(16);
        mProductBean.setJiagelaiyuan(priceSourceItem.getValue());
        SalesProductDetailsItem_4 bzItem = (SalesProductDetailsItem_4) mList.get(17);
        mProductBean.setBz(bzItem.getValue());
        SalesProductDetailsItem_4 huohaoItem = (SalesProductDetailsItem_4) mList.get(18);
        mProductBean.setHuohao(huohaoItem.getValue());
        Intent intent = new Intent(this, SalesOrderActivity.class);
        intent.putExtra(SELECTED_ITEM, mProductBean);
        startActivity(intent);
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
