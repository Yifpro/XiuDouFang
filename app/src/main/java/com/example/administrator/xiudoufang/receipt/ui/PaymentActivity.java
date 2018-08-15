package com.example.administrator.xiudoufang.receipt.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.bean.CustomerItem;
import com.example.administrator.xiudoufang.bean.SubjectBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PaymentActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

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
    private EditText mEtTip;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mReceiptDialog;
    private TimePickerView mPvPaymentDate;

    private CustomerListLogic mLogic;
    private ArrayList<SubjectBean.AccounttypesBean> list;
    private Animation mShake;
    private CustomerItem mCustomerItem;
    private String mPayId;
    private String mDirection;
    private String mSubjectId;

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
        mEtTip = findViewById(R.id.et_tip);

        mSivSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubjectDialog();
            }
        });
        mSivReceiptType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReceiptDialog();
            }
        });
        mSivPaymentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPaymentDateTimePicker();
            }
        });
        findViewById(R.id.tv_submit).setOnClickListener(this);
    }

    private void setSegmentStatus(String direction) {
        switch (direction) {
            case "0":
                mSivPayment.setLeftSegmentClickable(true);
                mSivPayment.setLeftSegmentSelected(true);
                mSivPayment.setRightSegmentClickable(true);
                mSivPayment.setRightSegmentSelected(false);
                break;
            case "1":
                mSivPayment.setLeftSegmentClickable(true);
                mSivPayment.setLeftSegmentSelected(true);
                mSivPayment.setRightSegmentClickable(false);
                mSivPayment.setRightSegmentSelected(false);
                break;
            case "-1":
                mSivPayment.setLeftSegmentClickable(false);
                mSivPayment.setLeftSegmentSelected(false);
                mSivPayment.setRightSegmentClickable(true);
                mSivPayment.setRightSegmentSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        mLogic = new CustomerListLogic();

        mSivSubject.setKey(getSpannableString("会计科目*", 4));
        mSivPayment.setKey(getSpannableString("收付款*", 3));
        mSivReceiptType.setKey(getSpannableString("收款方式*", 4));
        mSivPaymentAmount.setKey(getSpannableString("款项金额*", 4));
        mSivPaymentDate.setValue(StringUtils.getCurrentTime());

        mLogic.requestSubjectList("", new JsonCallback<SubjectBean>() {
            @Override
            public void onSuccess(Response<SubjectBean> response) {
                if (list == null)
                    list = new ArrayList<>();
                list.addAll(response.body().getAccounttypes());
                mSubjectId = list.get(0).getAccountid();
                mDirection = list.get(0).getDirection();
                setSegmentStatus(mDirection);
                mSivSubject.setValue(list.get(0).getShow_name());
            }
        });

        LoadingViewDialog.getInstance().show(this);
        CustomerBean.CustomerlistBean bean = getIntent().getParcelableExtra("selected_item");
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", bean.getDianid());
        map.put("userdengji", bean.getDengji_value());
        map.put("dqc_id", bean.getC_id());
        map.put("search", "");
        map.put("pagenum", "1");
        map.put("count", "20");
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        mLogic.requestCustomerList(map, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                mCustomerItem = JSONObject.parseObject(jsonObject.getJSONArray("customerlist").getJSONObject(0).toJSONString(), CustomerItem.class);
                mSivId.setValue(mCustomerItem.getCustomerno());
                mSivName.setValue(mCustomerItem.getCustomername());
                mSivTotalName.setValue(mCustomerItem.getQuancheng());
                mSivDebt.setValue(mCustomerItem.getDebt());
                mSivBalance.setValue(mCustomerItem.getYue_amt());
                String country = mCustomerItem.getCountry();
                mSivAreaType.setValue("0".equals(country) ? "国内" : "1".equals(country) ? "外销" : "网店");
                mSivArea.setValue(mCustomerItem.getQuyu());
            }
        });
    }

    private void showPaymentDateTimePicker() {
        if (mPvPaymentDate == null) {
            mPvPaymentDate = new TimePickerBuilder(PaymentActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    mSivPaymentDate.setValue(format.format(date));
                }
            })
                    .setLayoutRes(R.layout.layout_pickerview_custom_time, new CustomListener() {

                        @Override
                        public void customLayout(View v) {
                            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView tvCancel = v.findViewById(R.id.tv_cancel);
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvPaymentDate.returnData();
                                    mPvPaymentDate.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvPaymentDate.dismiss();
                                }
                            });
                        }
                    })
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                    .setDividerColor(Color.DKGRAY)
                    .setContentTextSize(20)
                    .setBackgroundId(0x00000000)
                    .isDialog(true)
                    .build();
        }
        mPvPaymentDate.show();
    }

    private void showReceiptDialog() {
        if (mReceiptDialog == null) {
            mReceiptDialog = new ReceiptSelectorDialog();
            mReceiptDialog.setOnItemChangedListener(new ReceiptSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(String payId, String payName, String number, String content) {
                    mPayId = payId;
                    if ("现金支付".equals(payName))
                        number = "现金支付";
                    mSivReceiptType.setValue(content);
                    mSivReceiptPerson.setValue(payName);
                    mSivReceiptAccount.setValue(number);
                    mReceiptDialog.dismiss();
                }
            });
        }
        mReceiptDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    private void showSubjectDialog() {
        if (list == null) {
            Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mSubjectDialog == null) {
            mSubjectDialog = SubjectSelectorDialog.newInstance(list);
            mSubjectDialog.setOnItemChangedListener(new SubjectSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(String subjectId, String direction, String showName) {
                    mSubjectId = subjectId;
                    mDirection = direction;
                    setSegmentStatus(direction);
                    mSivSubject.setValue(showName);
                }
            });
        }
        mSubjectDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    private SpannableString getSpannableString(String text, int position) {
        SpannableString spannableString = new SpannableString(text);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.red));
        spannableString.setSpan(colorSpan, position, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                if (checkNotEmpty()) {
                    if (mCustomerItem != null) {
                        SharedPreferences preferences = PreferencesUtils.getPreferences();
                        HttpParams params = new HttpParams();
                        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                        params.put("c_id", mCustomerItem.getC_id());
                        params.put("orderid", "0");
                        params.put("amt", mSivPaymentAmount.getValue());
                        params.put("youhui", mSivDiscountAmount.getValue());
                        params.put("shoukuanid", mPayId);
                        params.put("memo", mEtTip.getText().toString());
                        params.put("dqman", preferences.getString(PreferencesUtils.USER_NAME, ""));
                        params.put("riqi", mSivPaymentDate.getValue());
                        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
                        params.put("accountid", mSubjectId);
                        params.put("leixing", mDirection);
                        mLogic.requestReceipt(params, new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                            }
                        });
                    } else {
                        Toast.makeText(this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private boolean checkNotEmpty() {
        if (mShake == null)
            mShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        if (TextUtils.isEmpty(mSivSubject.getValue())) {
            mSivSubject.startAnimation(mShake);
            return false;
        } else if (TextUtils.isEmpty(mSivReceiptType.getValue())) {
            mSivReceiptType.startAnimation(mShake);
            return false;
        } else if (TextUtils.isEmpty(mSivPaymentAmount.getValue())) {
            mSivPaymentAmount.startAnimation(mShake);
            return false;
        }
        return true;
    }
}
