package com.example.administrator.xiudoufang.purchase.ui;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

public class PurchaseDetailsActivity extends AppCompatActivity implements IActivityBase {

    private SearchInfoView mSivOrderId;
    private SearchInfoView mSivSupplier;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivSetupOrderDate;
    private SearchInfoView mSivArrival;
    private SearchInfoView mSivWarehourse;
    private SearchInfoView mSivPaymentAmount;
    private SearchInfoView mSivDiscountAmount;
    private SearchInfoView mSivSubject;
    private SearchInfoView mSivPaymentType;
    private SearchInfoView mSivAccountOpening;
    private SearchInfoView mSivPaymentAccount;

    private PurchaseLogic mPurchaseLogic;
    private CustomerListLogic mCustomerListLogic;
    private EditText mEtTip;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_purchase_detail;
    }

    @Override
    public void initView() {
        setTitle("采购单详情");
        mSivOrderId = findViewById(R.id.siv_order_id);
        mSivSupplier = findViewById(R.id.siv_supplier);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivSetupOrderDate = findViewById(R.id.siv_setup_order_date);
        mSivArrival = findViewById(R.id.siv_arrival);
        mSivWarehourse = findViewById(R.id.siv_warehourse);
        mSivPaymentAmount = findViewById(R.id.siv_payment_amount);
        mSivDiscountAmount = findViewById(R.id.siv_discount_amount);
        mSivSubject = findViewById(R.id.siv_subject);
        mSivPaymentType = findViewById(R.id.siv_payment_type);
        mSivAccountOpening = findViewById(R.id.siv_account_opening);
        mSivPaymentAccount = findViewById(R.id.siv_payment_account);
        findViewById(R.id.iv_extra);
        mEtTip = findViewById(R.id.et_tip);
        mSivPaymentAmount.setType(SearchInfoView.ViewType.TYPE_TEXT);
        mSivDiscountAmount.setType(SearchInfoView.ViewType.TYPE_TEXT);
    }

    @Override
    public void initData() {
        mPurchaseLogic = new PurchaseLogic();
        mCustomerListLogic = new CustomerListLogic();
        loadPurchaseDetails();
    }

    private void loadPurchaseDetails() {
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestPurchaseDetails(getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                JSONObject result = JSONObject.parseObject(response.body());
                JSONObject jsonObject = result.getJSONObject("results");
                mSivOrderId.setValue(jsonObject.getString("iid"));
                mSivSupplier.setValue(jsonObject.getString("customername"));
                mSivDebt.setValue(jsonObject.getString("debt"));
                mSivSetupOrderDate.setValue(jsonObject.getString("issDate"));
                mSivArrival.setValue(jsonObject.getString("etadate"));
                mSivPaymentAmount.setValue(jsonObject.getString("benci_amt"));
                mSivDiscountAmount.setValue(jsonObject.getString("youhuijine"));
                loadSubject(jsonObject.getString("accountid"));
                loadPaymentInfo(jsonObject.getString("bankid"));
                if (!TextUtils.isEmpty(jsonObject.getString("remark")))
                    mEtTip.setText(jsonObject.getShort("remark"));
            }
        });
    }

    private void loadPaymentInfo(String bankid) {
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        JSONArray pay = jsonObject.getJSONArray("pay");
        LogUtils.e("支付方式 -> " + pay.toJSONString());
        for (int i = 0; i < pay.size(); i++) {
            JSONObject object = pay.getJSONObject(i);
            LogUtils.e("id: "+object.getString("id")+", "+bankid);
            if (object.getString("id").equals(bankid)) {
                LogUtils.e(object.getString("name")+", "+object.getString("payname")+", "+object.getString("number"));
                mSivPaymentType.setValue(object.getString("name"));
                mSivAccountOpening.setValue(object.getString("payname"));
                mSivPaymentAccount.setValue(object.getString("number"));
            }
        }
    }

    private void loadSubject(final String accountid) {
        mCustomerListLogic.requestSubjectList("5", new JsonCallback<SubjectListBean>() {
            @Override
            public void onSuccess(Response<SubjectListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<SubjectListBean.AccounttypesBean> accounttypes = response.body().getAccounttypes();
                for (SubjectListBean.AccounttypesBean bean : accounttypes) {
                    if (accountid.equals(bean.getAccountid()))
                        mSivSubject.setValue(bean.getShow_name());
                }
            }
        });
    }
}
