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

    private static final int RESULT_GET_AREA = 2;

    private SearchInfoView mSivId;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_details;
    }

    @Override
    public void initView() {
        setTitle("供应商");
        mSivId = findViewById(R.id.siv_id);
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
        findViewById(R.id.siv_custom_area).setOnClickListener(new InnerClickListener());
        findViewById(R.id.tv_sure).setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            SupplierListBean.SupplierBean bean = getIntent().getParcelableExtra(SupplierListActivity.SELECTED_ITEM);
            mSivId.setValue(bean.getCustomerno());
            mSivName.setValue(bean.getCustomername());
            mSivTotalName.setValue(bean.getQuancheng());
            mSivDebt.setValue(bean.getDebt());
            mSivPhoneNum.setValue(bean.getTelephone());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_GET_AREA && data != null) {
            mSivAreaNo.setValue(data.getStringExtra("area_code"));
            mSivAreaName.setValue(data.getStringExtra("area_name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sure:
                Intent intent = new Intent(this, NewPurchaseOrderActivity.class);
                SupplierDetails details = new SupplierDetails();
                details.setId(mSivId.getValue());
                details.setName(mSivName.getValue());
                details.setTotalName(mSivTotalName.getValue());
                details.setDebt(mSivDebt.getValue());
                details.setPhoneNum(mSivPhoneNum.getValue());
                details.setNewPhoneNum(mSivNewPhoneNum.getValue());
                details.setNewTelephoneNum(mSivNewTelephoneNum.getValue());
                details.setNewContact(mSivNewContact.getValue());
                details.setAreaNo(mSivAreaNo.getValue());
                details.setAreaName(mSivAreaName.getValue());
                intent.putExtra(NewPurchaseOrderActivity.SELECTED_SUPPLIER, details);
                startActivity(intent);
                break;
        }
    }

    private class InnerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(SupplierDetailsActivity.this, AreaListActivity.class);
            startActivityForResult(intent, RESULT_GET_AREA);
        }
    }
}
