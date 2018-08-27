package com.example.administrator.xiudoufang.stock.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.StockFilter;
import com.example.administrator.xiudoufang.bean.TypeListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.common.widget.SingleLineTextDialog;

import java.util.ArrayList;
import java.util.Collections;

public class StockQueryActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int UNIT = 3;
    private final int AMOUNT = 2;
    public static final String FILTER_INFO = "filter_info";
    private static final int RESULT_FOR_TYPE_LIST = 113;

    private SearchInfoView mSivNo;
    private SearchInfoView mSivName;
    private SearchInfoView mSivType;
    private SearchInfoView mSivSupplier;
    private SearchInfoView mSivModel;
    private SearchInfoView mSivBarCode;
    private SearchInfoView mSivBrand;
    private SearchInfoView mSivDetails;
    private SearchInfoView mSivUnit;
    private SearchInfoView mSivAmount;
    private SingleLineTextDialog mUnitDialog;
    private SingleLineTextDialog mAmountDialog;

    private ArrayList<String> mUnitList;
    private ArrayList<String> mAmountList;
    private String[] amountValue = {"-1", "0", "1", ""};
    private int mUnitIndex = 3;
    private int mAmountIndex = 2;
    private String mIdType;
    private String includeSubclass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_query;
    }

    @Override
    public void initView() {
        setTitle("库存查询");
        mSivNo = findViewById(R.id.siv_no);
        mSivName = findViewById(R.id.siv_name);
        mSivType = findViewById(R.id.siv_type);
        mSivSupplier = findViewById(R.id.siv_supplier);
        mSivModel = findViewById(R.id.siv_model);
        mSivBarCode = findViewById(R.id.siv_bar_code);
        mSivBrand = findViewById(R.id.siv_brand);
        mSivDetails = findViewById(R.id.siv_details);
        mSivUnit = findViewById(R.id.siv_unit);
        mSivAmount = findViewById(R.id.siv_amount);

        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_TYPE_LIST && data != null) {
            ArrayList<TypeListBean.TypeBean> list = data.getParcelableArrayListExtra(TypeListActivity.TYPE_LIST);
            StringBuilder idBuilder = new StringBuilder();
            StringBuilder nameBuilder = new StringBuilder();
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    idBuilder.append(list.get(i).getId()).append(",");
                    nameBuilder.append(list.get(i).getName()).append(",");
                }
            }
            idBuilder.setLength(idBuilder.length() - 1);
            nameBuilder.setLength(nameBuilder.length() - 1);
            mIdType = idBuilder.toString();
            mSivType.setValue(nameBuilder);
            includeSubclass = data.getBooleanExtra(TypeListActivity.INCLUDE_SUBCLASS, false) ? "1" : "0";
        }
    }

    @Override
    public void initData() {
        String[] unitArr = {"单位1", "单位2", "单位3", "单位4", "单位5", "单位6", "单位7", "单位8", "单位9", "单位10"};
        mUnitList = new ArrayList<>();
        Collections.addAll(mUnitList, unitArr);
        mSivUnit.setValue(mUnitList.get(mUnitIndex));
        String[] amountArr = {"小于0", "等于0", "大于0", "全部"};
        mAmountList = new ArrayList<>();
        Collections.addAll(mAmountList, amountArr);
        mSivAmount.setValue(mAmountList.get(mAmountIndex));
        mSivType.setOnClickListener(new InnerTypeClickListener());
        mSivUnit.setOnClickListener(new InnerUnitClickListener());
        mSivAmount.setOnClickListener(new InnerAmountClickListener());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_query:
                StockFilter stockFilter = new StockFilter();
                stockFilter.setNo(mSivNo.getValue());
                stockFilter.setName(mSivName.getValue());
                stockFilter.setType(mIdType == null ? "" : mIdType);
                stockFilter.setSupplier(mSivSupplier.getValue());
                stockFilter.setModel(mSivModel.getValue());
                stockFilter.setBarCode(mSivBarCode.getValue());
                stockFilter.setBrand(mSivBrand.getValue());
                stockFilter.setDetails(mSivDetails.getValue());
                stockFilter.setUnit(String.valueOf(mUnitIndex + 1));
                stockFilter.setAmount(amountValue[mAmountIndex]);
                stockFilter.setIsIncludeSubclass(includeSubclass);
                LogUtils.e(mSivNo.getValue()+", "+mSivName.getValue());
                Intent intent = new Intent();
                intent.putExtra(FILTER_INFO, stockFilter);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_reset:
                mSivNo.setValue("");
                mSivName.setValue("");
                mSivType.setValue("");
                mSivSupplier.setValue("");
                mSivModel.setValue("");
                mSivBarCode.setValue("");
                mSivBrand.setValue("");
                mSivDetails.setValue("");
                mSivUnit.setValue(mUnitList.get(UNIT));
                mSivAmount.setValue(mAmountList.get(AMOUNT));
                break;
        }
    }

    private class InnerUnitClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mUnitDialog == null) {
                mUnitDialog = SingleLineTextDialog.newInstance(mUnitList);
                mUnitDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        mUnitIndex = position;
                        mSivUnit.setValue(mUnitList.get(position));
                    }
                });
            }
            mUnitDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
        }
    }

    private class InnerAmountClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (mAmountDialog == null) {
                mAmountDialog = SingleLineTextDialog.newInstance(mAmountList);
                mAmountDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        mAmountIndex = position;
                        mSivAmount.setValue(mAmountList.get(position));
                    }
                });
            }
            mAmountDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
        }
    }
    private class InnerTypeClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(StockQueryActivity.this, TypeListActivity.class);
            startActivityForResult(intent, RESULT_FOR_TYPE_LIST);
        }
    }
}
