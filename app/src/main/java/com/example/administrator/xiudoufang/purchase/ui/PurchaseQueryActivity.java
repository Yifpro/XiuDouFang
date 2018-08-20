package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseQueryActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private TransferPurchaseDialog mTransferPurchaseDialog;
    private SearchInfoView mSivTransferPurchase;
    private SearchInfoView mSivStartTime;
    private SearchInfoView mSivEndTime;
    private TimePickerView mPvStartTime;
    private TimePickerView mPvEndTime;

    public static void start(Context context) {
        Intent intent = new Intent(context, PurchaseQueryActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase_query;
    }

    @Override
    public void initView() {
        setTitle("采购查询");
        SearchInfoView sivOrderNum = findViewById(R.id.siv_order_num);
        SearchInfoView sivCustomer = findViewById(R.id.siv_customer);
        mSivStartTime = findViewById(R.id.siv_start_time);
        mSivEndTime = findViewById(R.id.siv_end_time);
        SearchInfoView sivOperator = findViewById(R.id.siv_operator);
        SearchInfoView sivTip = findViewById(R.id.siv_tip);
        mSivTransferPurchase = findViewById(R.id.siv_transfer_purchase);

        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
        mSivStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimePickerDialog();
            }
        });
        mSivEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimePickerDialog();
            }
        });
        mSivTransferPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTransferPurchaseDialog();
            }
        });
    }

    private void showTransferPurchaseDialog() {
        if (mTransferPurchaseDialog == null) {
            mTransferPurchaseDialog = new TransferPurchaseDialog();
            mTransferPurchaseDialog.setOnItemChangedListener(new TransferPurchaseDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(int position) {
                    String result = null;
                    switch (position) {
                        case 0:
                            result = "全部";
                            break;
                        case 1:
                            result = "是";
                            break;
                        case 2:
                            result = "否";
                            break;
                    }
                    mSivTransferPurchase.setValue(result);
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
        mSivStartTime.setValue(StringUtils.getCurrentTime());
        mSivEndTime.setValue(StringUtils.getCurrentTime());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_query:
                break;
            case R.id.tv_reset:
                break;
        }
    }

}
