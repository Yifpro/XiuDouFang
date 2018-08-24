package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.Supplier;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;

/**
 * Created by Administrator on 2018/8/17
 */

public class AddSupplierActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_SELECT_AREA = 105;

    private SearchInfoView mSivName;
    private SearchInfoView mSivTotalName;
    private SearchInfoView mSivPhoneNum;
    private SearchInfoView mSivTelephoneNum;
    private SearchInfoView mSivContact;
    private SearchInfoView mSivAreaNo;
    private SearchInfoView mSivAreaName;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddSupplierActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_supplier;
    }

    @Override
    public void initView() {
        setTitle("新增供应商");
        mSivName = findViewById(R.id.siv_name);
        mSivTotalName = findViewById(R.id.siv_total_name);
        mSivPhoneNum = findViewById(R.id.siv_phone_num);
        mSivTelephoneNum = findViewById(R.id.siv_telephone_num);
        mSivContact = findViewById(R.id.siv_contact);
        mSivAreaNo = findViewById(R.id.siv_area_no);
        mSivAreaName = findViewById(R.id.siv_area_name);

        mSivName.setKey(StringUtils.getSpannableString("供应商名称*", 5));
        findViewById(R.id.siv_custom_area).setOnClickListener(new InnerClickListener());
    }

    @Override
    public void initData() {

    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(mSivName.getValue())) {
            mSivName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
        } else {
            Intent intent = new Intent(this, NewPurchaseOrderActivity.class);
            Supplier supplier = new Supplier();
            supplier.setName(mSivName.getValue());
            supplier.setTotalName(mSivTotalName.getValue());
            supplier.setNewPhoneNum(mSivPhoneNum.getValue());
            supplier.setNewTelephoneNum(mSivTelephoneNum.getValue());
            supplier.setNewContact(mSivContact.getValue());
            supplier.setAreaNo(mSivAreaNo.getValue());
            supplier.setAreaName(mSivAreaName.getValue());
            intent.putExtra(NewPurchaseOrderActivity.SELECTED_SUPPLIER, supplier);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //******** 区域返回结果 ********
        if (requestCode == RESULT_SELECT_AREA && data != null) {
            mSivAreaNo.setValue(data.getStringExtra("area_code"));
            mSivAreaName.setValue(data.getStringExtra("area_name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class InnerClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(AddSupplierActivity.this, AreaListActivity.class);
            startActivityForResult(intent, RESULT_SELECT_AREA);
        }
    }
}
