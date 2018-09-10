package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PurchaseQueryActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private final int COUNT = 20;

    private TransferPurchaseDialog mTransferPurchaseDialog;
    private SearchInfoView mSivTransferPurchase;
    private SearchInfoView mSivStartTime;
    private SearchInfoView mSivEndTime;
    private TimePickerView mPvStartTime;
    private TimePickerView mPvEndTime;
    private SearchInfoView mSivOrderNum;
    private SearchInfoView mSivCustomer;
    private SearchInfoView mSivOperator;
    private SearchInfoView mSivTip;

    private PurchaseLogic mLogic;
    private ArrayList<String> mList;
    private String mTransferPurchase;

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase_query;
    }

    @Override
    public void initView() {
        setTitle("采购查询");
        mSivOrderNum = findViewById(R.id.siv_order_num);
        mSivCustomer = findViewById(R.id.siv_customer);
        mSivStartTime = findViewById(R.id.siv_start_time);
        mSivEndTime = findViewById(R.id.siv_end_time);
        mSivOperator = findViewById(R.id.siv_operator);
        mSivTip = findViewById(R.id.siv_tip);
        mSivTransferPurchase = findViewById(R.id.siv_transfer_purchase);

        mTransferPurchase = "全部";
        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
        mSivStartTime.setOnClickListener(this);
        mSivEndTime.setOnClickListener(this);
        mSivTransferPurchase.setOnClickListener(this);
    }

    //******** 订单转采购选择框 ********
    private void showTransferPurchaseDialog() {
        if (mTransferPurchaseDialog == null) {
            mTransferPurchaseDialog = TransferPurchaseDialog.newInstance(mList);
            mTransferPurchaseDialog.setOnItemChangedListener(new TransferPurchaseDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(int position) {
                    mTransferPurchase = mList.get(position);
                    mSivTransferPurchase.setValue(mTransferPurchase);
                    mTransferPurchaseDialog.dismiss();
                }
            });
        }
        mTransferPurchaseDialog.show(getSupportFragmentManager(), "TransferPurchaseDialog");
    }

    private void showEndTimePickerDialog() {
        if (mPvEndTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseQueryActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    mSivEndTime.setValue(format.format(date));
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
                                    mPvEndTime.returnData();
                                    mPvEndTime.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvEndTime.dismiss();
                                }
                            });
                        }
                    });
            mPvEndTime = setBuilder(builder);
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivEndTime.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvEndTime.setDate(calendar);
        mPvEndTime.show();
    }

    private void showStartTimePickerDialog() {
        if (mPvStartTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseQueryActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    mSivStartTime.setValue(format.format(date));
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
                                    mPvStartTime.returnData();
                                    mPvStartTime.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mPvStartTime.dismiss();
                                }
                            });
                        }
                    });
            mPvStartTime = setBuilder(builder);
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivStartTime.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvStartTime.setDate(calendar);
        mPvStartTime.show();
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
        mList = new ArrayList<>();
        mList.add("全部");
        mList.add("是");
        mList.add("否");
        initTime();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.siv_start_time:
                showStartTimePickerDialog();
                break;
            case R.id.siv_end_time:
                showEndTimePickerDialog();
                break;
            case R.id.siv_transfer_purchase:
                showTransferPurchaseDialog();
                break;
            case R.id.tv_query:
                queryOrder();
                break;
            case R.id.tv_reset:
                reset();
                break;
        }
    }

    private void queryOrder() {
        ArrayList<String> list = new ArrayList<>();
        list.add(mSivOrderNum.getValue());
        list.add(mSivCustomer.getValue());
        list.add(mSivStartTime.getValue());
        list.add(mSivEndTime.getValue());
        list.add(mSivOperator.getValue());
        list.add(mSivTip.getValue());
        list.add("全部".equals(mTransferPurchase) ? "" : "不是".equals(mTransferPurchase) ? "0" : "1");
        Intent intent = new Intent();
        intent.putStringArrayListExtra(PurchaseActivity.FILTER_LIST, list);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void reset() {
        mSivOrderNum.setValue("");
        mSivCustomer.setValue("");
        initTime();
        mSivOperator.setValue("");
        mSivTip.setValue("");
        mSivTransferPurchase.setValue(mList.get(0));
    }

    private void initTime() {
        mSivStartTime.setValue(StringUtils.getCurrentTime());
        mSivEndTime.setValue(StringUtils.getCurrentTime());
    }
}
