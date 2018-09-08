package com.example.administrator.xiudoufang.receipt.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.bean.PayBean;
import com.example.administrator.xiudoufang.bean.SingleCustomerItem;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    private ArrayList<SubjectListBean.AccounttypesBean> list;
    private Animation mShake;
    private SingleCustomerItem mSingleCustomerItem;
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
        mSivId = findViewById(R.id.siv_no);
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
                showPaymentTimePickerDialog();
            }
        });
        findViewById(R.id.tv_confirm).setOnClickListener(this);
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

        mSivSubject.setKey(StringUtils.getSpannableString("会计科目*", 4));
        mSivPayment.setKey(StringUtils.getSpannableString("收付款*", 3));
        mSivReceiptType.setKey(StringUtils.getSpannableString("收款方式*", 4));
        mSivPaymentAmount.setKey(StringUtils.getSpannableString("款项金额*", 4));
        mSivPaymentDate.setValue(StringUtils.getCurrentTime());

        loadSubjectList();
        loadCustomerList();
    }

    private void loadSubjectList() {
        mLogic.requestSubjectList("", new JsonCallback<SubjectListBean>() {
            @Override
            public void onSuccess(Response<SubjectListBean> response) {
                if (list == null)
                    list = new ArrayList<>();
                list.addAll(response.body().getAccounttypes());
                mSubjectId = list.get(0).getAccountid();
                mDirection = list.get(0).getDirection();
                setSegmentStatus(mDirection);
                mSivSubject.setValue(list.get(0).getShow_name());
            }
        });
    }

    private void loadCustomerList() {
        if (getIntent() != null) {
            CustomerListBean.CustomerBean bean = getIntent().getParcelableExtra(CustomerListActivity.SELECTED_ITEM);
            HashMap<String, String> map = new HashMap<>();
            map.put("dianid", bean.getDianid());
            map.put("userdengji", bean.getDengji_value());
            map.put("dqc_id", bean.getC_id());
            map.put("search", "");
            map.put("pagenum", "1");
            map.put("count", "20");
            map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
            LoadingViewDialog.getInstance().show(this);
            mLogic.requestCustomerList(map, new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    LoadingViewDialog.getInstance().dismiss();
                    JSONObject jsonObject = JSONObject.parseObject(response.body());
                    mSingleCustomerItem = JSONObject.parseObject(jsonObject.getJSONArray("customerlist").getJSONObject(0).toJSONString(), SingleCustomerItem.class);
                    mSivId.setValue(mSingleCustomerItem.getCustomerno());
                    mSivName.setValue(mSingleCustomerItem.getCustomername());
                    mSivTotalName.setValue(mSingleCustomerItem.getQuancheng());
                    mSivDebt.setValue(mSingleCustomerItem.getDebt());
                    mSivBalance.setValue(mSingleCustomerItem.getYue_amt());
                    String country = mSingleCustomerItem.getCountry();
                    mSivAreaType.setValue("0".equals(country) ? "国内" : "1".equals(country) ? "外销" : "网店");
                    mSivArea.setValue(mSingleCustomerItem.getQuyu());
                }
            });
        }
    }

    private void showPaymentTimePickerDialog() {
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
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivPaymentDate.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvPaymentDate.setDate(calendar);
        mPvPaymentDate.show();
    }

    private void showReceiptDialog() {
        if (mReceiptDialog == null) {
            mReceiptDialog = new ReceiptSelectorDialog();
            mReceiptDialog.setOnItemChangedListener(new ReceiptSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(PayBean item, String content) {
                    mPayId = item.getId();
                    String number = item.getNumber();
                    if ("现金支付".equals(item.getPayname()))
                        number = "现金支付";
                    mSivReceiptType.setValue(content);
                    mSivReceiptPerson.setValue(item.getPayname());
                    mSivReceiptAccount.setValue(number);
                    mReceiptDialog.dismiss();
                }
            });
        }
        mReceiptDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    private void showSubjectDialog() {
        if (list == null) {
            ToastUtils.show(this, "暂无数据");
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                submitBill();
                break;
        }
    }

    private void submitBill() {
        if (checkNotEmpty()) {
            if (mSingleCustomerItem != null) {
                SharedPreferences preferences = PreferencesUtils.getPreferences();
                HttpParams params = new HttpParams();
                params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
                params.put("c_id", mSingleCustomerItem.getC_id());
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
                ToastUtils.show(this, "服务器繁忙");
            }
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
