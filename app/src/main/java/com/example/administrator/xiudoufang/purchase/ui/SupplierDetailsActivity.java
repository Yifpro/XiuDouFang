package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.SupplierDetails;
import com.example.administrator.xiudoufang.bean.SupplierListBean;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;

/**
 * Created by Administrator on 2018/8/18
 */

public class SupplierDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final int RESULT_SELECT_AREA = 102;

    private SearchInfoView mSivCustomerNo;
    private SearchInfoView mSivName;
    private SearchInfoView mSivTotalName;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivPhoneNum;
    private SearchInfoView mSivNewPhoneNum;
    private SearchInfoView mSivNewTelephoneNum;
    private SearchInfoView mSivNewContact;
    private SearchInfoView mSivCustomArea;
    private SearchInfoView mSivAreaNo;
    private SearchInfoView mSivAreaName;
    private SupplierListBean.SupplierBean mBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_details;
    }

    @Override
    public void initView() {
        setTitle("供应商");
        mSivCustomerNo = findViewById(R.id.siv_customer_no);
        mSivName = findViewById(R.id.siv_name);
        mSivTotalName = findViewById(R.id.siv_total_name);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivPhoneNum = findViewById(R.id.siv_phone_num);
        mSivNewPhoneNum = findViewById(R.id.siv_new_phone_num);
        mSivNewTelephoneNum = findViewById(R.id.siv_new_telephone_num);
        mSivNewContact = findViewById(R.id.siv_new_contact);
        mSivCustomArea = findViewById(R.id.siv_custom_area);
        mSivAreaNo = findViewById(R.id.siv_area_no);
        mSivAreaName = findViewById(R.id.siv_area_name);

        findViewById(R.id.tv_sure).setOnClickListener(this);
        findViewById(R.id.siv_custom_area).setOnClickListener(new InnerClickListener());
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            mBean = getIntent().getParcelableExtra(SupplierListActivity.SELECTED_ITEM);
            mSivCustomerNo.setValue(mBean.getCustomerno());
            mSivName.setValue(mBean.getCustomername());
            mSivTotalName.setValue(mBean.getQuancheng());
            mSivDebt.setValue(mBean.getDebt());
            mSivPhoneNum.setValue(mBean.getTelephone());
            mSivAreaNo.setValue(mBean.getQuyuno());
            mSivAreaName.setValue(mBean.getQuyu());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_SELECT_AREA && data != null) {
            mSivAreaNo.setValue(data.getStringExtra("area_code"));
            mSivAreaName.setValue(data.getStringExtra("area_name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sure:
                Class clazz;
                if (getIntent().getStringExtra(SupplierListActivity.FROM_CLASS).equals(NewPurchaseOrderActivity.TAG)) {
                    clazz = NewPurchaseOrderActivity.class;
                } else {
                    clazz = PurchaseDetailsActivity.class;
                }
                Intent intent = new Intent(this, clazz);
                SupplierDetails details = new SupplierDetails();
                details.setId(mBean.getC_id());
                details.setCustomerNo(mBean.getCustomerno());
                details.setName(mBean.getCustomername());
                details.setTotalName(mBean.getQuancheng());
                details.setDebt(mBean.getDebt());
                details.setPhoneNum(mBean.getTelephone());
                details.setNewPhoneNum(mSivNewPhoneNum.getValue());
                details.setNewTelephoneNum(mSivNewTelephoneNum.getValue());
                details.setNewContact(mSivNewContact.getValue());
                details.setAreaNo(mSivAreaNo.getValue());
                details.setAreaName(mSivAreaName.getValue());
                details.setFendianid(mBean.getFendianid());
                intent.putExtra(NewPurchaseOrderActivity.SELECTED_SUPPLIER, details);
                startActivity(intent);
                break;
        }
    }

    private class InnerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SupplierDetailsActivity.this, AreaListActivity.class);
            startActivityForResult(intent, RESULT_SELECT_AREA);
        }
    }
}
