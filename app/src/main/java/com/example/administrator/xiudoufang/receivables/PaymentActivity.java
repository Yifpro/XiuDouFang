package com.example.administrator.xiudoufang.receivables;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.lzy.okgo.model.Response;

public class PaymentActivity extends AppCompatActivity implements IActivityBase {

    private CustomerListLogic mLogic;
    private SearchInfoView mSivId;
    private SearchInfoView mSivName;
    private SearchInfoView mSivTotalName;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivBalance;
    private SearchInfoView mSivAreaType;
    private SearchInfoView mSivArea;
    private SearchInfoView mSivSubject;
    private SearchInfoView mSivPayment;
    private SearchInfoView mSivReceiptType;
    private SearchInfoView mSivReceiptPerson;
    private SearchInfoView mSivReceiptAccount;
    private SearchInfoView mSivPaymentAmount;
    private SearchInfoView mSivDiscountAmount;
    private SearchInfoView mSivPaymentDate;
    private EditText mEtEtTip;
    private TextView mTvSubmit;

    public static void start(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("data", bundle);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {
        setTitle("款项");
        mSivId = findViewById(R.id.siv_id);
        mSivName = findViewById(R.id.siv_name);
        mSivTotalName = findViewById(R.id.siv_total_name);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivBalance = findViewById(R.id.siv_balance);
        mSivAreaType = findViewById(R.id.siv_area_type);
        mSivArea = findViewById(R.id.siv_area);
        mSivSubject = findViewById(R.id.siv_subject);
        mSivPayment = findViewById(R.id.siv_payment);
        mSivReceiptType = findViewById(R.id.siv_receipt_type);
        mSivReceiptPerson = findViewById(R.id.siv_receipt_person);
        mSivReceiptAccount = findViewById(R.id.siv_receipt_account);
        mSivPaymentAmount = findViewById(R.id.siv_payment_amount);
        mSivDiscountAmount = findViewById(R.id.siv_discount_amount);
        mSivPaymentDate = findViewById(R.id.siv_payment_date);
        mEtEtTip = findViewById(R.id.et_tip);
        mTvSubmit = findViewById(R.id.tv_submit);
        mSivSubject.setKey(getSpannableString("会计科目*", 4));
        mSivPayment.setKey(getSpannableString("收付款*", 3));
        mSivReceiptType.setKey(getSpannableString("收款方式*", 4));
        mSivPaymentAmount.setKey(getSpannableString("款项金额*", 4));
    }

    private SpannableString getSpannableString(String text, int position) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        spannableString.setSpan(colorSpan, position, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public void initData() {
        mLogic = new CustomerListLogic();
        CustomerBean.CustomerlistBean bean = getIntent().getBundleExtra("data").getParcelable("selected_item");
        String[] parameters = {bean.getDianid(), bean.getDengji_value(), bean.getC_id(), "", "1", "20", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, "")};
        mLogic.requestCustomerList(parameters, new JsonCallback<CustomerBean>() {
            @Override
            public void onSuccess(Response<CustomerBean> response) {

            }
        });
    }
}
