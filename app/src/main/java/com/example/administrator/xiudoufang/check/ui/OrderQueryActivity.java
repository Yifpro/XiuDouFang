package com.example.administrator.xiudoufang.check.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.OrderFilter;
import com.example.administrator.xiudoufang.bean.OrderQueryBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.common.widget.SingleLineTextDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderQueryActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String ORDER_FILTER = "order_filter";

    private SearchInfoView mSivNo;
    private SearchInfoView mSivCustomer;
    private SearchInfoView mSivStartTime;
    private SearchInfoView mSivEndTime;
    private SearchInfoView mSivOrderType;
    private SearchInfoView mSivTransportType;
    private SearchInfoView mSivProxyOrder;
    private OrderQueryDialog mOrderTypeDialog;
    private OrderQueryDialog mTransportTypeDialog;
    private SingleLineTextDialog mProxyOrderDialog;
    private TimePickerView mStartTimePickerView;
    private TimePickerView mEndTimePickerView;

    private ArrayList<OrderQueryBean> mOrderTypeList;
    private ArrayList<OrderQueryBean> mTransportTypeList;
    private ArrayList<String> mProxyOrderList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_query;
    }

    @Override
    public void initView() {
        setTitle("订单查询");
        mSivNo = findViewById(R.id.siv_no);
        mSivCustomer = findViewById(R.id.siv_customer);
        mSivStartTime = findViewById(R.id.siv_start_time);
        mSivEndTime = findViewById(R.id.siv_end_time);
        mSivOrderType = findViewById(R.id.siv_order_type);
        mSivTransportType = findViewById(R.id.siv_transport_type);
        mSivProxyOrder = findViewById(R.id.siv_proxy_order);

        findViewById(R.id.tv_query).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
        mSivStartTime.setOnClickListener(this);
        mSivEndTime.setOnClickListener(this);
        mSivOrderType.setOnClickListener(this);
        mSivTransportType.setOnClickListener(this);
        mSivProxyOrder.setOnClickListener(this);
    }

    @Override
    public void initData() {
        OrderFilter filter = getIntent().getParcelableExtra(OrderListActivity.ORDER_FILTER);
        if (filter != null) {
            mSivNo.setValue(filter.getNo());
            mSivCustomer.setValue(filter.getCustomer());
            mSivStartTime.setValue(TextUtils.isEmpty(filter.getStartTime()) ? StringUtils.getCurrentTime() : filter.getStartTime());
            mSivEndTime.setValue(TextUtils.isEmpty(filter.getStartTime()) ? StringUtils.getCurrentTime() : filter.getEndTime());
            mSivOrderType.setValue(filter.getOrderType());
            mSivTransportType.setValue(filter.getTransportType());
            mSivProxyOrder.setValue(filter.getProxyOrder());
        } else {
            resetTime();
        }
        mOrderTypeList = new ArrayList<>();
        mOrderTypeList.add(new OrderQueryBean("全部"));
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        List<OrderQueryBean> ordertype = JSONArray.parseArray(jsonObject.getString("ordertype"), OrderQueryBean.class);
        for (int i = 0; i < ordertype.size(); i++) {
            if ("1".equals(ordertype.get(i).getMoren()) && TextUtils.isEmpty(mSivProxyOrder.getValue())) {
                mSivOrderType.setValue(ordertype.get(i).getName());
            }
            mOrderTypeList.add(ordertype.get(i));
        }

        mTransportTypeList = new ArrayList<>();
        mTransportTypeList.add(new OrderQueryBean("全部"));
        List<OrderQueryBean> huoyun = JSONArray.parseArray(jsonObject.getString("huoyun"), OrderQueryBean.class);
        for (int i = 0; i < huoyun.size(); i++) {
            if ("1".equals(huoyun.get(i).getMoren()) && TextUtils.isEmpty(mSivTransportType.getValue())) {
                mSivTransportType.setValue(huoyun.get(i).getName());
            }
            mTransportTypeList.add(huoyun.get(i));
        }

        mProxyOrderList = new ArrayList<>();
        mProxyOrderList.add("全部");
        mProxyOrderList.add("是");
        mProxyOrderList.add("否");
        if (TextUtils.isEmpty(mSivProxyOrder.getValue()))
            mSivProxyOrder.setValue(mProxyOrderList.get(0));
    }

    private void resetTime() {
        mSivStartTime.setValue(StringUtils.getCurrentTime());
        mSivEndTime.setValue(StringUtils.getCurrentTime());
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
            case R.id.siv_order_type:
                showOrderTypeDialog();
                break;
            case R.id.siv_transport_type:
                showTransportTypeDialog();
                break;
            case R.id.siv_proxy_order:
                showProxyOrderDialog();
                break;
            case R.id.tv_query:
                Intent intent = new Intent();
                OrderFilter filter = new OrderFilter();
                filter.setNo(mSivNo.getValue());
                filter.setCustomer(mSivCustomer.getValue());
                filter.setStartTime(mSivStartTime.getValue());
                filter.setEndTime(mSivEndTime.getValue());
                filter.setOrderType(mSivOrderType.getValue());
                filter.setTransportType(mSivTransportType.getValue());
                filter.setProxyOrder(mSivProxyOrder.getValue());
                intent.putExtra(ORDER_FILTER, filter);
                setResult(Activity.RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_reset:
                mSivNo.setValue("");
                mSivCustomer.setValue("");
                resetTime();
                break;
        }
    }

    private void showProxyOrderDialog() {
        if (mProxyOrderDialog == null) {
            mProxyOrderDialog = SingleLineTextDialog.newInstance(mProxyOrderList);
            mProxyOrderDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSivProxyOrder.setValue(mProxyOrderList.get(position));
                }
            });
        }
        mProxyOrderDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
    }

    private void showTransportTypeDialog() {
        if (mTransportTypeDialog == null) {
            mTransportTypeDialog = OrderQueryDialog.newInstance(mTransportTypeList);
            mTransportTypeDialog.setOnItemChangedListener(new OrderQueryDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSivTransportType.setValue(mTransportTypeList.get(position).getName());
                }
            });
        }
        mTransportTypeDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
    }

    private void showOrderTypeDialog() {
        if (mOrderTypeDialog == null) {
            mOrderTypeDialog = OrderQueryDialog.newInstance(mOrderTypeList);
            mOrderTypeDialog.setOnItemChangedListener(new OrderQueryDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSivOrderType.setValue(mOrderTypeList.get(position).getName());
                }
            });
        }
        mOrderTypeDialog.show(getSupportFragmentManager(), "SingleLineTextDialog");
    }

    //******** 结束日期选择器 ********
    private void showEndTimePickerDialog() {
        if (mEndTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(OrderQueryActivity.this, new OnTimeSelectListener() {
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
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEndTimePickerView.returnData();
                                    mEndTimePickerView.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mEndTimePickerView.dismiss();
                                }
                            });
                        }
                    });
            mEndTimePickerView = setBuilder(builder);
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivEndTime.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mEndTimePickerView.setDate(calendar);
        mEndTimePickerView.show();
    }

    //******** 开始日期选择器 ********
    private void showStartTimePickerDialog() {
        if (mStartTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(OrderQueryActivity.this, new OnTimeSelectListener() {
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
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mStartTimePickerView.returnData();
                                    mStartTimePickerView.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mStartTimePickerView.dismiss();
                                }
                            });
                        }
                    });
            mStartTimePickerView = setBuilder(builder);
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivStartTime.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mStartTimePickerView.setDate(calendar);
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
}
