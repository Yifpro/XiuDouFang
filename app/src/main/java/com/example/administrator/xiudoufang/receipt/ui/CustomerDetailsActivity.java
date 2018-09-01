package com.example.administrator.xiudoufang.receipt.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.open.ui.SalesOrderActivity;

/**
 * Created by Administrator on 2018/9/1
 */

public class CustomerDetailsActivity extends AppCompatActivity implements IActivityBase {

    private SearchInfoView mSivNo;
    private SearchInfoView mSivName;
    private SearchInfoView mSivTotalName;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivBalance;
    private SearchInfoView mSivAreaType;
    private SearchInfoView mSivArea;
    private SearchInfoView mSivDefaultMobilePhone;
    private SearchInfoView mSivNewMobilePhone;
    private SearchInfoView mSivNewPhone;
    private SearchInfoView mSivAddContact;
    private SearchInfoView mSivCollectGoodsAddress;
    private SearchInfoView mSivDeliverGoodsAddress;
    private SearchInfoView mSivAddCargoTerminal;

    private CustomerListBean.CustomerBean mCustomerBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_details;
    }

    @Override
    public void initView() {
        setTitle("客户详情");
        mSivNo = findViewById(R.id.siv_no);
        mSivName = findViewById(R.id.siv_name);
        mSivTotalName = findViewById(R.id.siv_total_name);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivBalance = findViewById(R.id.siv_balance);
        mSivAreaType = findViewById(R.id.siv_area_type);
        mSivArea = findViewById(R.id.siv_area);
        mSivDefaultMobilePhone = findViewById(R.id.siv_default_mobile_phone);
        mSivNewMobilePhone = findViewById(R.id.siv_new_mobile_phone);
        mSivNewPhone = findViewById(R.id.siv_new_phone);
        mSivAddContact = findViewById(R.id.siv_add_contact);
        mSivCollectGoodsAddress = findViewById(R.id.siv_collect_goods_address);
        mSivDeliverGoodsAddress = findViewById(R.id.siv_deliver_goods_address);
        mSivAddCargoTerminal = findViewById(R.id.siv_add_cargo_terminal);
    }

    @Override
    public void initData() {
        mCustomerBean = getIntent().getParcelableExtra(CustomerListActivity.SELECTED_ITEM);
        mSivNo.setValue(mCustomerBean.getCustomerno());
        mSivName.setValue(mCustomerBean.getCustomername());
        mSivTotalName.setValue(mCustomerBean.getQuancheng());
        mSivDebt.setValue(mCustomerBean.getDebt());
        mSivBalance.setValue(mCustomerBean.getYue_amt());
        mSivAreaType.setValue("0".equals(mCustomerBean.getCountry()) ? "国内" : "1".equals(mCustomerBean.getCountry()) ? "外销" : "网店");
        mSivArea.setValue(mCustomerBean.getQuyu());
        if (mCustomerBean.getTelephone().size() > 0)
            mSivDefaultMobilePhone.setValue(mCustomerBean.getTelephone().get(0).getTelephone());
    }

    public void onSubmit(View view) {
        Intent intent = new Intent(this, SalesOrderActivity.class);
        mCustomerBean.setDefaultMobilePhoneNum(mSivDefaultMobilePhone.getValue());
        mCustomerBean.setNewMobilePhoneNum(mSivNewMobilePhone.getValue());
        mCustomerBean.setNewPhoneNum(mSivNewPhone.getValue());
        mCustomerBean.setAddContact(mSivAddContact.getValue());
        mCustomerBean.setAddCollectGoodsAddress(mSivCollectGoodsAddress.getValue());
        mCustomerBean.setAddDeliverGoodsAddress(mSivDeliverGoodsAddress.getValue());
        mCustomerBean.setAddCargoTerminal(mSivAddCargoTerminal.getValue());
        intent.putExtra(SalesOrderActivity.CUSTOMER_DETAILS, mCustomerBean);
        startActivity(intent);
    }
}
