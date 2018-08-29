package com.example.administrator.xiudoufang.transport.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.InvoiceFilter;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.ui.NewPurchaseOrderActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvoiceQueryActivity extends AppCompatActivity implements IActivityBase, OnClickListener {

    public static final String INVOICE_FILTER = "invoice_filter";

    private SearchInfoView mSivInvoiceNo;
    private SearchInfoView mSivCustomer;
    private SearchInfoView mSivStartTime;
    private SearchInfoView mSivEndTime;
    private SearchInfoView mSivTransportNum;
    private TimePickerView mStartTimePickerView;
    private TimePickerView mEndTimePickerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transport_order_query;
    }

    @Override
    public void initView() {
        setTitle("库存查询");
        mSivInvoiceNo = findViewById(R.id.siv_invoice_no);
        mSivCustomer = findViewById(R.id.siv_customer);
        mSivStartTime = findViewById(R.id.siv_start_time);
        mSivEndTime = findViewById(R.id.siv_end_time);
        mSivTransportNum = findViewById(R.id.siv_transport_num);

        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
        mSivStartTime.setOnClickListener(new InnerStartTimeClickListener());
        mSivEndTime.setOnClickListener(new InnerEndTimeClickListener());
    }

    private void showEndTimePickerDialog() {
        if (mEndTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(InvoiceQueryActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mSivEndTime.setValue(format.format(date));
                }
            })
                    .setLayoutRes(R.layout.layout_pickerview_custom_time, new CustomListener() {

                        @Override
                        public void customLayout(View v) {
                            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView tvCancel = v.findViewById(R.id.tv_cancel);
                            tvSubmit.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEndTimePickerView.returnData();
                                    mEndTimePickerView.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEndTimePickerView.dismiss();
                                }
                            });
                        }
                    });
            mEndTimePickerView = setBuilder(builder);
        }
        mEndTimePickerView.show();
    }

    private void showStartTimePickerDialog() {
        if (mStartTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(InvoiceQueryActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mSivStartTime.setValue(format.format(date));
                }
            })
                    .setLayoutRes(R.layout.layout_pickerview_custom_time, new CustomListener() {

                        @Override
                        public void customLayout(View v) {
                            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView tvCancel = v.findViewById(R.id.tv_cancel);
                            tvSubmit.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mStartTimePickerView.returnData();
                                    mStartTimePickerView.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mStartTimePickerView.dismiss();
                                }
                            });
                        }
                    });
            mStartTimePickerView = setBuilder(builder);
        }
        mStartTimePickerView.show();
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
    public void initData() {
        InvoiceFilter filter = getIntent().getParcelableExtra(INVOICE_FILTER);
        if (filter != null) {
            mSivInvoiceNo.setValue(filter.getNo());
            mSivCustomer.setValue(filter.getCustomer());
            mSivStartTime.setValue(filter.getStartTime());
            mSivEndTime.setValue(filter.getEndTime());
            mSivTransportNum.setValue(filter.getTransportNum());
        } else {
            resetTime();
        }
    }

    private void resetTime() {
        mSivStartTime.setValue(StringUtils.getCurrentTime());
        mSivEndTime.setValue(StringUtils.getCurrentTime());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_query:
                Intent intent = new Intent();
                InvoiceFilter filter = new InvoiceFilter();
                filter.setNo(mSivInvoiceNo.getValue());
                filter.setCustomer(mSivCustomer.getValue());
                filter.setStartTime(mSivStartTime.getValue());
                filter.setEndTime(mSivEndTime.getValue());
                filter.setTransportNum(mSivTransportNum.getValue());
                intent.putExtra(INVOICE_FILTER, filter);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_reset:
                mSivInvoiceNo.setValue("");
                mSivCustomer.setValue("");
                resetTime();
                mSivTransportNum.setValue("");
                break;
        }
    }

    private class InnerEndTimeClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            showEndTimePickerDialog();
        }
    }

    private class InnerStartTimeClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            showStartTimePickerDialog();
        }
    }
}
