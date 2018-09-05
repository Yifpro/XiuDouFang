package com.example.administrator.xiudoufang.open.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.bean.OrderInfo;
import com.example.administrator.xiudoufang.bean.OtherSetting;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.bean.SettingItem;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.open.adapter.SalesOrderAdapter;
import com.example.administrator.xiudoufang.open.logic.SalesOrderLogic;
import com.example.administrator.xiudoufang.R;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by Administrator on 2018/9/4
 */

public class CreateOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String TAG = CreateOrderActivity.class.getSimpleName();
    private static final int RESULT_FOR_PRODUCT_INFO_CHANGE = 121;

    private TextView mTvNo;
    private TextView mTvName;
    private TextView mTvDebt;
    private TextView mTvCreateOrderDate;
    private TextView mTvDeliveryDate;
    private TextView mTvTotalAmount;
    private TextView mTvTotalPrice;
    private TextView mTvSalesman;
    private TextView mTvReceiptType;
    private EditText mEtTip;
    private SwipeMenuRecyclerView mRecyclerView;
    private TimePickerView mStartTimePickerView;
    private TimePickerView mEndTimePickerView;
    private ConfirmOrderInfoDialog mConfirmOrderInfoDialog;
    private SimpleTextDialog mSalesSelectorDialog;
    private SimpleTextDialog mReceiptTypeDialog;

    private SalesOrderLogic mLogic;
    private SalesOrderAdapter mAdapter;
    private ArrayList<SettingItem> mSalesmanList;
    private ArrayList<SettingItem> mReceiptTypeList;
    private ArrayList<SalesProductListBean.SalesProductBean> mList;
    private CustomerListBean.CustomerBean mCustomerBean;
    private OtherSetting mOtherSetting;
    private int mLastIndex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    public void initView() {
        setTitle("");
        mTvNo = findViewById(R.id.tv_no);
        mTvName = findViewById(R.id.tv_name);
        mTvDebt = findViewById(R.id.tv_debt);
        mTvCreateOrderDate = findViewById(R.id.tv_create_order_date);
        mTvDeliveryDate = findViewById(R.id.tv_delivery_date);
        mTvTotalAmount = findViewById(R.id.tv_total_amount);
        mTvTotalPrice = findViewById(R.id.tv_total_price);
        mTvSalesman = findViewById(R.id.tv_salesman);
        mTvReceiptType = findViewById(R.id.tv_receipt_type);
        mEtTip = findViewById(R.id.et_tip);
        mRecyclerView = findViewById(R.id.recycler_view);

        mTvCreateOrderDate.setOnClickListener(this);
        mTvDeliveryDate.setOnClickListener(this);
        mTvSalesman.setOnClickListener(this);
        mTvReceiptType.setOnClickListener(this);
        mEtTip.setOnClickListener(this);
        findViewById(R.id.tv_submit_order).setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(SalesOrderActivity.RESULT_PRODUCT_LIST, mList);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_FOR_PRODUCT_INFO_CHANGE && data != null) {
            SalesProductListBean.SalesProductBean bean = data.getParcelableExtra(SalesProductDetailsActivity.SELECTED_ITEM);
            mList.remove(mLastIndex);
            mList.add(mLastIndex, bean);
            mAdapter.setNewData(mList);
            calculateAmountAndSums();
        }
    }

    @Override
    public void initData() {
        mCustomerBean = getIntent().getParcelableExtra(SalesOrderActivity.RESULT_CUSTOMER);
        mList = getIntent().getParcelableArrayListExtra(SalesOrderActivity.RESULT_PRODUCT_LIST);
        mLogic = new SalesOrderLogic();
        mSalesmanList = new ArrayList<>();
        mReceiptTypeList = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        JSONArray user = jsonObject.getJSONArray("user");
        for (int i = 0; i < user.size(); i++) {
            String userid = user.getJSONObject(i).getString("userid");
            String name = user.getJSONObject(i).getString("name");
            mSalesmanList.add(new SettingItem(name, userid));
        }
        if (mSalesmanList.size() > 0) mTvSalesman.setText(mSalesmanList.get(0).getKey());
        JSONArray pay = jsonObject.getJSONArray("pay");
        for (int i = 0; i < pay.size(); i++) {
            String id = pay.getJSONObject(i).getString("id");
            String payname = pay.getJSONObject(i).getString("payname");
            if ("1".equals(pay.getJSONObject(i).getString("moren")))
                mTvReceiptType.setText(payname);
            mReceiptTypeList.add(new SettingItem(payname, id));
        }

        mTvNo.setText(mCustomerBean.getCustomerno());
        mTvName.setText(mCustomerBean.getCustomername());
        mTvDebt.setText(mCustomerBean.getDebt());
        mTvCreateOrderDate.setText(StringUtils.getCurrentTime());
        mTvDeliveryDate.setText(StringUtils.getCurrentTime());
        mAdapter = new SalesOrderAdapter(R.layout.layout_list_item_sales_order, mList);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem item = new SwipeMenuItem(CreateOrderActivity.this);
                item.setText("删除");
                item.setTextSize(16);
                item.setWidth(SizeUtils.dp2px(48));
                item.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                item.setBackgroundColor(Color.parseColor("#FF0000"));
                item.setTextColor(Color.parseColor("#FFFFFF"));
                rightMenu.addMenuItem(item);
            }
        };
        SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                mList.remove(adapterPosition);
                mAdapter.notifyItemChanged(adapterPosition);
                calculateAmountAndSums();
            }
        };
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        calculateAmountAndSums();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_other:
                OtherSettingDialog otherSettingDialog = OtherSettingDialog.newInstance(mOtherSetting);
                otherSettingDialog.show(getSupportFragmentManager(), "OtherSettingDialog");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveOrCreateOrder(boolean isConfirm, OrderInfo info) {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> params = new HashMap<>();
        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, "")); //店id
        params.put("iid", "0"); //订单id
        params.put("c_id", mCustomerBean.getC_id()); //客户id
        params.put("customerno", mCustomerBean.getCustomerno()); //客户编号
        params.put("customername", mCustomerBean.getCustomername()); //客户名称
        params.put("telephone", mCustomerBean.getTelephone().get(0).getTelephone()); //客户手机
        params.put("tel", mCustomerBean.getDianhua().get(0).getDianhua()); //客户电话
        params.put("QQ", mCustomerBean.getQq().get(0).getQq()); //客户qq
        params.put("lianxiren", mCustomerBean.getLianxiren().get(0).getLianxiren()); //联系人
        params.put("weixinhao", mCustomerBean.getWeixinhao().get(0).getWeixinhao()); //微信
        params.put("fahuodizhi", mCustomerBean.getFahuodizhi().get(0).getFahuodizhi()); //发货地址
        params.put("shouhuodizhi", mCustomerBean.getShouhuodizhi().get(0).getShouhuodizhi()); //收货地址

        params.put("shishou_amt", info.getBencishoukuan()); //本次收款
        params.put("yingshou_amt", info.getYingshou()); //应收 金额+其他费用
        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, "")); //用户id
        params.put("remark", mEtTip.getText().toString()); //备注
        params.put("free", ""); //优惠金额
        params.put("allname", mCustomerBean.getQuancheng()); //全称
        params.put("freight", mCustomerBean.getFreight().get(0).getFreight()); //货运站
        int receiptTypeIndex = mReceiptTypeList.indexOf(new SettingItem(mTvReceiptType.getText().toString()));
        params.put("shoukuanid", mReceiptTypeList.size() == 0 ? "" : mReceiptTypeList.get(receiptTypeIndex).getValue()); //收款id
        int salesmanIndex = mSalesmanList.indexOf(new SettingItem(mTvSalesman.getText().toString()));
        params.put("operatorid", mSalesmanList.size() == 0 ? "" : mSalesmanList.get(salesmanIndex).getValue()); //经办人
        params.put("country", mCustomerBean.getCountry()); //区域类型
        params.put("quyu", mCustomerBean.getQuyu()); //区域
        params.put("quyuno", mCustomerBean.getQuyuno()); //区域id
        params.put("action", "0"); //动作
        params.put("ordertype", mOtherSetting.getOrderType()); //订单类型
        params.put("huoyunleixing", mOtherSetting.getHuoyunType()); //货运类型
        params.put("fahuodian", mOtherSetting.getFahuodianid()); //发货店
        params.put("xiadanriqi", mTvCreateOrderDate.getText().toString()); //下单日期
        params.put("jiaohuoriqi", mTvDeliveryDate.getText().toString()); //交货日期
        params.put("yuji_jiaohuoriqi", mOtherSetting.getForcastDate()); //预计交货日期
        params.put("teshu", mOtherSetting.getSpecialOrder()); //特殊订单
        params.put("fujia_memo", mOtherSetting.getAdditionalInstructions()); //附加说明
        params.put("kuaidiid", mOtherSetting.getLogistics()); //快递id
        params.put("feiyong", info.getOtherFree()); //费用
        params.put("songhuo_time", ""); //最佳送货
        params.put("cust_orderno", mOtherSetting.getCustomerContract()); //客户合同
        params.put("confirm", isConfirm ? "1" : "0"); //是否确认订单
        params.put("pay_yueamt", info.getBalancePayment()); //余额支付金额
        params.put("cpjsonstr", JSONObject.toJSONString(mList)); //产品json
        mLogic.saveOrCreateOrder(params, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_create_order_date:
                showCreateOrderTimePickerDialog();
                break;
            case R.id.tv_delivery_date:
                showDeliveryTimePickerDialog();
                break;
            case R.id.tv_salesman:
                if (mSalesmanList.size() > 0) {
                    if (mSalesSelectorDialog == null) {
                        mSalesSelectorDialog = SimpleTextDialog.newInstance(mSalesmanList);
                        mSalesSelectorDialog.setOnItemChangedListener(new SimpleTextDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, String key) {
                                mTvSalesman.setText(key);
                            }
                        });
                    }
                    mSalesSelectorDialog.show(getSupportFragmentManager(), "SimpleTextDialog");
                } else {
                    ToastUtils.show(this, "暂无其他业务员可选择");
                }
                break;
            case R.id.tv_receipt_type:
                if (mReceiptTypeList.size() > 0) {
                    if (mReceiptTypeDialog == null) {
                        mReceiptTypeDialog = SimpleTextDialog.newInstance(mReceiptTypeList);
                        mReceiptTypeDialog.setOnItemChangedListener(new SimpleTextDialog.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position, String key) {
                                mTvReceiptType.setText(key);
                            }
                        });
                    }
                    mReceiptTypeDialog.show(getSupportFragmentManager(), "SimpleTextDialog");
                } else {
                    ToastUtils.show(this, "暂无其他收款方式可选择");
                }
                break;
            case R.id.et_tip:
                mEtTip.setCursorVisible(true);
                break;
            case R.id.tv_submit_order:
                if (mConfirmOrderInfoDialog == null) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(mTvTotalPrice.getText().toString()); //本单金额
                    list.add(mCustomerBean.getDebt()); //前结欠
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    double sums = Double.parseDouble(mTvTotalPrice.getText().toString());
                    double debt = Double.parseDouble(mCustomerBean.getDebt());
                    String leijiqian = decimalFormat.format(sums + debt);
                    list.add(leijiqian); //累计欠
                    list.add(""); //其他费用
                    list.add(mTvTotalPrice.getText().toString()); //本单应收
                    list.add(leijiqian); //累计金额
                    list.add(""); //本次收款
                    list.add(""); //优惠金额
                    list.add(mCustomerBean.getYue_amt()); //账户余额
                    list.add(""); //余额支付
                    mConfirmOrderInfoDialog = ConfirmOrderInfoDialog.newInstance(list);
                    mConfirmOrderInfoDialog.setOnItemClickListener(new InnerConfirmOrderInfoListener());
                }
                mConfirmOrderInfoDialog.show(getSupportFragmentManager(), "ConfirmOrderInfoDialog");
                break;
        }
    }

    //******** 交货日期选择器 ********
    private void showDeliveryTimePickerDialog() {
        if (mEndTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mTvDeliveryDate.setText(format.format(date));
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
        mEndTimePickerView.show();
    }

    //******** 下单日期选择器 ********
    private void showCreateOrderTimePickerDialog() {
        if (mStartTimePickerView == null) {
            TimePickerBuilder builder = new TimePickerBuilder(this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mTvCreateOrderDate.setText(format.format(date));
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

    private void calculateAmountAndSums() {
        if (mList != null) {
            int amount = 0;
            double result = 0;
            for (int i = 0; i < mList.size(); i++) {
                double totalPrice = Double.parseDouble(mList.get(i).getS_jiage2()) * Double.parseDouble(mList.get(i).getCp_qty()) * Double.parseDouble(mList.get(i).getZhekou());
                result += totalPrice;
                amount += Integer.valueOf(mList.get(i).getCp_qty());
            }
            DecimalFormat mFormat = new DecimalFormat("0.00");
            mTvTotalAmount.setText(String.valueOf(amount));
            mTvTotalPrice.setText(mFormat.format(result));
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            EditText etAmount = (EditText) adapter.getViewByPosition(position, R.id.et_amount);
            if (view.getId() == R.id.et_amount) {
                etAmount.setCursorVisible(true);
                return;
            }
            TextView tvSums = (TextView) adapter.getViewByPosition(position, R.id.tv_sums);
            assert etAmount != null;
            int i = Integer.parseInt(etAmount.getText().toString());
            switch (view.getId()) {
                case R.id.tv_reduce:
                    if (i > 1) {
                        i--;
                    }
                    break;
                case R.id.tv_add:
                    i++;
                    break;
            }
            etAmount.setText(String.valueOf(i));
            SalesProductListBean.SalesProductBean item = mList.get(position);
            item.setCp_qty(String.valueOf(i));
            double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(mList.get(position).getCp_qty());
            DecimalFormat mFormat = new DecimalFormat("0.00");
            assert tvSums != null;
            tvSums.setText(mFormat.format(totalPrice));
            etAmount.setText(String.valueOf(i));
            calculateAmountAndSums();
        }
    }

    private class InnerConfirmOrderInfoListener implements ConfirmOrderInfoDialog.OnItemClickListener {

        @Override
        public void onSave(OrderInfo info) {
            saveOrCreateOrder(false, info);
        }

        @Override
        public void onConfirm(OrderInfo info) {
            saveOrCreateOrder(true, info);
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(CreateOrderActivity.this, SalesProductDetailsActivity.class);
            intent.putExtra(SalesProductListActivity.SELECTED_ITEM, mList.get(position));
            intent.putExtra(SalesProductDetailsActivity.FROM_CLASS, TAG);
            startActivityForResult(intent, RESULT_FOR_PRODUCT_INFO_CHANGE);
            mLastIndex = position;
        }
    }
}
