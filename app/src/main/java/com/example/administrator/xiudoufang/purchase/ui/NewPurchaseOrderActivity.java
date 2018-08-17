package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.NewSupplier;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPurchaseOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

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
    private EditText mEtTip;
    private TimePickerView mPvSetupOrderTime;
    private TimePickerView mPvArrivalTime;
    private ExitEditDialog mExitEditDialog;

    private NewPurchaseOrderLogic mLogic;

    public static void start(Context context) {
        Intent intent = new Intent(context, NewPurchaseOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_purchase_order;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        NewSupplier newSupplier = getIntent().getParcelableExtra("new_supplier");
        if (newSupplier != null) {
            mSivSupplier.setValue(newSupplier.getName());
        }
    }

    @Override
    public void initView() {
        setTitle("采购单详情");
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
        mSivPaymentAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);
        mSivDiscountAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);

        mSivSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SupplierListActivity.start(NewPurchaseOrderActivity.this);
            }
        });
        mSivSetupOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSetupOrderTimeDialog();
            }
        });
        mSivArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showArrivalDialog();
            }
        });
    }

    private void showArrivalDialog() {
        if (mPvArrivalTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(NewPurchaseOrderActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    mSivArrival.setValue(format.format(date));
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
                                    mPvArrivalTime.returnData();
                                    mPvArrivalTime.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvArrivalTime.dismiss();
                                }
                            });
                        }
                    });
            mPvArrivalTime = setBuilder(builder);
        }
        mPvArrivalTime.show();
    }

    private void showSetupOrderTimeDialog() {
        if (mPvSetupOrderTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(NewPurchaseOrderActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    mSivSetupOrderDate.setValue(format.format(date));
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
                                    mPvSetupOrderTime.returnData();
                                    mPvSetupOrderTime.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvSetupOrderTime.dismiss();
                                }
                            });
                        }
                    });
            mPvSetupOrderTime = setBuilder(builder);
        }
        mPvSetupOrderTime.show();
    }

    private TimePickerView setBuilder(TimePickerBuilder builder) {
        return builder.setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(20)
                .setBackgroundId(0x00000000)
                .isDialog(true)
                .build();
    }

    @Override
    public void onBackPressed() {
        showExitEditDialog();
    }

    @Override
    public void initData() {
        ((Toolbar) findViewById(R.id.tool_bar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitEditDialog();
            }
        });
        mLogic = new NewPurchaseOrderLogic();
    }

    private void showExitEditDialog() {
        if (mExitEditDialog == null) {
            mExitEditDialog = new ExitEditDialog();
            mExitEditDialog.setOnSubmitClickListener(new ExitEditDialog.OnSumbitClickListener() {
                @Override
                public void onClick() {
                    NewPurchaseOrderActivity.this.finish();
                    mExitEditDialog.dismiss();
                }
            });
        }
        mExitEditDialog.show(getSupportFragmentManager(), "ExitEditDialog");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
