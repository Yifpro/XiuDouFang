package com.example.administrator.xiudoufang.purchase.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.PayBean;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.ReloadHistoryPriceBean;
import com.example.administrator.xiudoufang.bean.ReloadPriceListBean;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.bean.Supplier;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.CustomLinearLayoutManager;
import com.example.administrator.xiudoufang.purchase.adapter.SelectedProductListAdapter;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.example.administrator.xiudoufang.receipt.ui.ReceiptSelectorDialog;
import com.example.administrator.xiudoufang.receipt.ui.SubjectSelectorDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.model.Response;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import io.reactivex.functions.Consumer;

import static com.example.administrator.xiudoufang.purchase.ui.NewPurchaseOrderActivity.RESULT_PRODUCT_LIST;
import static com.example.administrator.xiudoufang.purchase.ui.NewPurchaseOrderActivity.SELECTED_PRODUCT_LIST;

public class PurchaseDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final int RESULT_WAREHOUSE = 104;
    private static final int RESULT_FOR_SCAN_BAR_CODE = 131; //******** 扫描条形码 ********
    private static final int RESULT_FOR_INFO_CHANGE = 133; //******** 修改产品信息 ********
    public static final String TAG = PurchaseDetailsActivity.class.getSimpleName();

    private SearchInfoView mSivOrderNo;
    private SearchInfoView mSivSupplier;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivBillingDate;
    private SearchInfoView mSivArrivalDate;
    private SearchInfoView mSivWarehourse;
    private SwipeMenuRecyclerView mRecyclerView;
    private LinearLayout mAddProductLayout;
    private SearchInfoView mSivPaymentAmount;
    private SearchInfoView mSivDiscountAmount;
    private SearchInfoView mSivSubject;
    private SearchInfoView mSivPaymentType;
    private SearchInfoView mSivAccountOpening;
    private SearchInfoView mSivPaymentAccount;
    private ImageView mIvExtra;
    private ImageView mIvClear;
    private EditText mEtTip;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;
    private TimePickerView mPvSetupOrderTime;
    private TimePickerView mPvArrivalTime;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mPaymentDialog;
    private ReloadHistoryPriceDialog mReloadHistoryPriceDialog;

    private PurchaseLogic mPurchaseLogic;
    private CustomerListLogic mCustomerListLogic;
    private ArrayList<SubjectListBean.AccounttypesBean> mSubjectList;
    private HashMap<String, String> mActionParams;
    private List<ProductItem> mList;
    private String mStatus;
    private String mWarehouseId;
    private String mSubjectId;
    private String mPayId;
    private Supplier mSupplier;
    private SelectedProductListAdapter mAdapter;
    private String mImgPath;
    private String mIid;
    private int mPosition;

    @Override
    public int getLayoutId() {
        return R.layout.layout_activity_purchase_detail;
    }

    @Override
    public void initView() {
        setTitle("采购单详情");
        mSivOrderNo = findViewById(R.id.siv_order_no);
        mSivSupplier = findViewById(R.id.siv_supplier);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivBillingDate = findViewById(R.id.siv_setup_order_date);
        mSivArrivalDate = findViewById(R.id.siv_arrival_date);
        mSivWarehourse = findViewById(R.id.siv_warehourse);
        mRecyclerView = findViewById(R.id.recycler_view);
        mAddProductLayout = findViewById(R.id.add_product_layout);
        mSivPaymentAmount = findViewById(R.id.siv_payment_amount);
        mSivDiscountAmount = findViewById(R.id.siv_discount_amount);
        mSivSubject = findViewById(R.id.siv_subject);
        mSivPaymentType = findViewById(R.id.siv_payment_type);
        mSivAccountOpening = findViewById(R.id.siv_account_opening);
        mSivPaymentAccount = findViewById(R.id.siv_payment_account);
        mIvExtra = findViewById(R.id.iv_extra);
        mEtTip = findViewById(R.id.et_tip);
        mTvBottomLeft = findViewById(R.id.tv_bottom_left);
        mTvBottomRight = findViewById(R.id.tv_bottom_right);
        mIvClear = findViewById(R.id.iv_clear);

        initStatus();
    }

    private void initStatus() {
        mStatus = getIntent().getStringExtra(PurchaseSubFragment.ITEM_STATUS);
        switch (mStatus) {
            case "1":
                mSivSupplier.setShowNext(true);
                mSivBillingDate.setShowNext(true);
                mSivArrivalDate.setShowNext(true);
                mSivWarehourse.setShowNext(true);
                mSivSubject.setShowNext(true);
                mSivPaymentType.setShowNext(true);
                mTvBottomLeft.setText("存为草稿");
                mTvBottomRight.setText("确认单据");
                mAddProductLayout.setVisibility(View.VISIBLE);

                mSivSupplier.setOnClickListener(this);
                mSivBillingDate.setOnClickListener(this);
                mSivArrivalDate.setOnClickListener(this);
                mSivWarehourse.setOnClickListener(this);
                mSivSubject.setOnClickListener(this);
                mSivPaymentType.setOnClickListener(this);
                findViewById(R.id.tv_add_product).setOnClickListener(this);
                findViewById(R.id.tv_scan_product).setOnClickListener(this);
                findViewById(R.id.tv_bottom_left).setOnClickListener(this);
                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                break;
            case "2":
                setStatus();
                mTvBottomLeft.setText("反确认");
                mTvBottomRight.setText("确认单据");
                mAddProductLayout.setVisibility(View.GONE);

                findViewById(R.id.tv_bottom_left).setOnClickListener(this);
                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                break;
            case "3":
                setStatus();
                mAddProductLayout.setVisibility(View.GONE);
                findViewById(R.id.bottom_layout).setVisibility(View.GONE);
                break;
            case "4":
                setStatus();
                mTvBottomLeft.setText("反确认");
                mTvBottomRight.setText("提交分店");
                mAddProductLayout.setVisibility(View.GONE);

                findViewById(R.id.tv_bottom_left).setOnClickListener(this);
                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                break;
            case "5":
                setStatus();
                mTvBottomRight.setText("取消提交");
                mAddProductLayout.setVisibility(View.GONE);
                mTvBottomLeft.setVisibility(View.GONE);

                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                break;
            case "6":
                setStatus();
                mTvBottomRight.setText("取消收货");
                mAddProductLayout.setVisibility(View.GONE);
                mTvBottomLeft.setVisibility(View.GONE);

                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                break;
        }
    }

    //******** 更改订单动作 ********
    private void changeActionForOrder(int viewId) {
        if (mActionParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mActionParams = new HashMap<>();
            mActionParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            mActionParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            mActionParams.put("iid", getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID)); //******** 采购单id ********
        }
        mActionParams.put("action", mPurchaseLogic.getAction(viewId, getIntent().getStringExtra(PurchaseSubFragment.ITEM_STATUS))); //******** 动作区分 ********
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestActionForOrder(this, mActionParams, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                if (!"1".equals(jsonObject.getString("messages"))) {
                    ToastUtils.show(PurchaseDetailsActivity.this, jsonObject.getString("messages"));
                }
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        final Supplier supplier = getIntent().getParcelableExtra(SupplierDetailsActivity.SELECTED_SUPPLIER);
        ProductItem item = getIntent().getParcelableExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT);
        if (supplier != null) { //******** 返回选择的供应商 ********
            if (mList.size() > 0) {
                if (mReloadHistoryPriceDialog == null) {
                    mReloadHistoryPriceDialog = new ReloadHistoryPriceDialog();
                }
                mReloadHistoryPriceDialog.setOnReplaceListener(new ReloadHistoryPriceDialog.OnReplaceListener() {
                    @Override
                    public void onReplace() {
                        reloadHistoryPrice();
                        mSivSupplier.setValue(supplier.getCustomername());
                        mSivDebt.setValue(supplier.getDebt());
                        mSupplier = supplier;
                    }
                });
                mReloadHistoryPriceDialog.show(getSupportFragmentManager(), "ReloadHistoryPriceDialog");
            } else {
                mSivSupplier.setValue(supplier.getCustomername());
                mSivDebt.setValue(supplier.getDebt());
                mSupplier = supplier;
            }
        } else if (item != null) { //******** 返回选择的单个产品 ********
            mList.add(item);
            mAdapter.notifyItemInserted(mList.size() - 1);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            mTvBottomRight.setBackgroundResource(R.drawable.rect_4_blue);
            caculateTotalPrice();
        }
    }

    //******** 重新加载历史价格 ********
    private void reloadHistoryPrice() {
        LoadingViewDialog.getInstance().show(this);
        ArrayList<ReloadHistoryPriceBean> list = new ArrayList<>();
        for (ProductItem item : mList) {
            list.add(new ReloadHistoryPriceBean(item.getFactor(), item.getCpid()));
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("iid", mIid); //******** 采购单id ********
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params.put("c_id", mSupplier.getC_id()); //******** 供应商id ********
        params.put("cpjsonstr", JSONArray.toJSONString(list)); //******** 产品json ********
        mPurchaseLogic.reloadHistoryPrice(this, params, new JsonCallback<ReloadPriceListBean>() {
            @Override
            public void onSuccess(Response<ReloadPriceListBean> response) {
                List<ReloadPriceListBean.ReloadPriceBean> temp = response.body().getSuppprice_lists();
                for (ReloadPriceListBean.ReloadPriceBean bean : temp) {
                    int index = mList.indexOf(new ProductItem(bean.getCpid()));
                    int lishiIndex = mList.get(index).getLishijialist().indexOf(new ProductItem.LishijialistBean(bean.getUnit_bilv()));
                    mList.get(index).getLishijialist().get(lishiIndex).setPrice(bean.getPrice());
                    mAdapter.setNewData(mList);
                }
                LoadingViewDialog.getInstance().dismiss();
            }
        });
    }

    @Override
    public void initData() {
        mPurchaseLogic = new PurchaseLogic();
        mCustomerListLogic = new CustomerListLogic();
        mList = new ArrayList<>();
        mAdapter = new SelectedProductListAdapter(R.layout.item_selected_product, mList, mStatus);
        View footerView = View.inflate(this, R.layout.footer_purchase_details, null);
        mAdapter.addFooterView(footerView);
        mRecyclerView.setSwipeMenuCreator(new InnerSwipeMenuCreator());
        mRecyclerView.setSwipeMenuItemClickListener(new InnerSwipeMenuItemClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new CustomLinearLayoutManager(this));
        mAdapter.getFooterLayout().setVisibility(View.GONE);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        loadPurchaseDetails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_WAREHOUSE && data != null) { //******** 返回选择的货仓 ********
            mWarehouseId = data.getStringExtra(WarehouseListActivity.WAREHOUSE_ID);
            mSivWarehourse.setValue(data.getStringExtra(WarehouseListActivity.WAREHOUSE_NAME));
        } else if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) { //******** 返回选择的附件 ********
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mImgPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mImgPath).into(mIvExtra);
            mIvClear.setVisibility(View.VISIBLE);
        } else if ((requestCode == RESULT_PRODUCT_LIST || requestCode == RESULT_FOR_SCAN_BAR_CODE) && data != null) {
            //******** 返回选择或扫描的多个产品 ********
            ArrayList<ProductItem> items = data.getParcelableArrayListExtra(SELECTED_PRODUCT_LIST);
            mList.addAll(items);
            mAdapter.setNewData(mList);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            mTvBottomRight.setBackgroundResource(R.drawable.rect_4_blue);
            caculateTotalPrice();
        } else if (requestCode == RESULT_FOR_INFO_CHANGE && data != null) {
            ProductItem temp = data.getParcelableExtra(SupplierProductDetailsActivity.SELECTED_PRODUCT_ITEM);
            mList.remove(mPosition);
            mAdapter.notifyItemRemoved(mPosition);
            mList.add(mPosition, temp);
            mAdapter.notifyItemInserted(mPosition);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showPaymentDialog() {
        if (mPaymentDialog == null) {
            mPaymentDialog = new ReceiptSelectorDialog();
            mPaymentDialog.setOnItemChangedListener(new ReceiptSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(PayBean item, String content) {
                    mPayId = item.getId();
                    String number = item.getNumber();
                    if ("现金支付".equals(item.getPayname()))
                        number = "现金支付";
                    mSivPaymentType.setValue(content);
                    mSivAccountOpening.setValue(item.getPayname());
                    mSivPaymentAccount.setValue(number);
                    mPaymentDialog.dismiss();
                }
            });
        }
        mPaymentDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    private void showSubjectDialog() {
        if (mSubjectList == null) {
            ToastUtils.show(this, "暂无数据");
            return;
        }
        if (mSubjectDialog == null) {
            mSubjectDialog = SubjectSelectorDialog.newInstance(mSubjectList);
            mSubjectDialog.setOnItemChangedListener(new SubjectSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(String subjectId, String direction, String showName) {
                    mSubjectId = subjectId;
                    mSivSubject.setValue(showName);
                }
            });
        }
        mSubjectDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    //******** 预计到货日期选择框 ********
    private void showArrivalDatePickerDialog() {
        if (mPvArrivalTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mSivArrivalDate.setValue(format.format(date));
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
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivArrivalDate.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvArrivalTime.setDate(calendar);
        mPvArrivalTime.show();
    }

    //******** 发单日期选择框 ********
    private void showBillingDatePickerDialog() {
        if (mPvSetupOrderTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    mSivBillingDate.setValue(format.format(date));
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
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivBillingDate.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvSetupOrderTime.setDate(calendar);
        mPvSetupOrderTime.show();
    }

    private void setStatus() {
        mSivSupplier.setShowNext(false);
        mSivBillingDate.setShowNext(false);
        mSivArrivalDate.setShowNext(false);
        mSivWarehourse.setShowNext(false);
        mSivSubject.setShowNext(false);
        mSivPaymentType.setShowNext(false);
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

    private void caculateTotalPrice() {
        double result = 0;
        for (int i = 0; i < mList.size(); i++) {
            if ("0".equals(mList.get(i).getZengpin())) {
                double totalPrice = Double.parseDouble(mList.get(i).getS_jiage2()) * Double.parseDouble(mList.get(i).getCp_qty());
                result += totalPrice;
            }
        }
        DecimalFormat mFormat = new DecimalFormat("0.00");
        ((TextView) mAdapter.getFooterLayout().findViewById(R.id.tv)).setText(mFormat.format(result));
    }

    //******** 加载采购单明细 ********
    private void loadPurchaseDetails() {
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestPurchaseDetails(this, getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID), new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                JSONObject jsonObject = JSONObject.parseObject(response.body());
                JSONObject result = jsonObject.getJSONObject("results");
                mSupplier = new Supplier();
                mIid = result.getString("iid"); //******** 采购单id ********
                mSivOrderNo.setValue(result.getString("puOrderNo")); //******** 采购单编号 ********
                mSupplier.setC_id(result.getString("c_id")); //******** 供应商id ********
                mSupplier.setCustomerno(result.getString("customerno")); //******** 供应商编号 ********
                mSupplier.setCustomername(result.getString("customername")); //******** 供应商名称 ********
                mSupplier.setLianxiren(result.getString("lianxiren")); //******** 联系人 ********
                mSupplier.setDianhua(result.getString("tel")); //******** 电话 ********
                mSupplier.setTelephone(result.getString("telephone")); //******** 手机号 ********
                mSivBillingDate.setValue(result.getString("issDate").substring(0, 9)); //******** 发单日期 ********
                String etadate = result.getString("etadate"); //******** 预计到货 ********
                mSivArrivalDate.setValue(etadate.length() > 8 ? etadate.substring(0, 9) : etadate);
                mWarehouseId = result.getString("warehouseid"); //******** 仓位id ********
                mSivWarehourse.setValue(result.getString("warehouse")); //******** 仓位名称 ********
                mSupplier.setQuyuno(result.getString("quyuno")); //******** 区域编号 ********
                mSupplier.setQuyu(result.getString("quyu")); //******** 区域 ********
                mSupplier.setFendianid(result.getString("fendianid")); //******** 分店id ********
                mSivSupplier.setValue(result.getString("customername")); //******** 供应商名称 ********
                mEtTip.setText(result.getString("remark")); //******** 备注 ********
                if (Double.parseDouble(result.getString("benci_amt")) > 0) //******** 付款金额 ********
                    mSivPaymentAmount.setValue(result.getString("benci_amt"));
                mPayId = result.getString("bankid"); //******** 支付方式id ********
                mSubjectId = result.getString("accountid"); //******** 会计科目id ********
                mSivDebt.setValue(result.getString("debt")); //******** 前结欠 ********
                if (Double.parseDouble(result.getString("youhuijine")) > 0) //******** 优惠金额 ********
                    mSivDiscountAmount.setValue(result.getString("youhuijine"));
                if (!TextUtils.isEmpty(result.getString("fujian"))) //******** 附件 ********
                    GlideApp.with(PurchaseDetailsActivity.this).load(StringUtils.FILE_URL + result.getString("fujian")).error(R.mipmap.ic_error).into(mIvExtra);
                mList.addAll(mPurchaseLogic.parsePurchaseDetailsJson(jsonObject)); //******** 解析产品json ********
                if (mList.size() == 0) { //******** 产品数组为0时，不可确认订单 ********
                    mTvBottomRight.setClickable(false);
                    mTvBottomRight.setBackgroundResource(R.drawable.rect_4_gray_888888);
                } else {
                    mAdapter.setNewData(mList);
                }
                loadSubject(result.getString("accountid"));
                loadPaymentInfo(result.getString("bankid"));
            }
        });
    }

    private void loadPaymentInfo(String bankid) {
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        JSONArray pay = jsonObject.getJSONArray("pay");
        LogUtils.e("支付方式->" + bankid + ", " + pay.toString());
        for (int i = 0; i < pay.size(); i++) {
            JSONObject object = pay.getJSONObject(i);
            if (object.getString("id").equals(bankid)) {
                mSivPaymentType.setValue(object.getString("name"));
                mSivAccountOpening.setValue(object.getString("payname"));
                mSivPaymentAccount.setValue(object.getString("number"));
            }
        }
    }

    private void loadSubject(final String accountid) {
        mCustomerListLogic.requestSubjectList(this, "5", new JsonCallback<SubjectListBean>() {
            @Override
            public void onSuccess(Response<SubjectListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<SubjectListBean.AccounttypesBean> accounttypes = response.body().getAccounttypes();
                for (SubjectListBean.AccounttypesBean bean : accounttypes) {
                    if (accountid.equals(bean.getAccountid()))
                        mSivSubject.setValue(bean.getShow_name());
                }
                if ("1".equals(mStatus)) {
                    mSubjectList = new ArrayList<>();
                    mSubjectList = (ArrayList) accounttypes;
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.siv_supplier:
                startActivity(new Intent(PurchaseDetailsActivity.this, SupplierListActivity.class)
                        .putExtra(SupplierListActivity.FROM_CLASS, TAG));
                break;
            case R.id.siv_setup_order_date:
                showBillingDatePickerDialog();
                break;
            case R.id.siv_arrival_date:
                showArrivalDatePickerDialog();
                break;
            case R.id.siv_warehourse:
                startActivityForResult(new Intent(PurchaseDetailsActivity.this, WarehouseListActivity.class)
                        .putExtra(WarehouseListActivity.WAREHOUSE_ID, mWarehouseId), RESULT_WAREHOUSE);
                break;
            case R.id.siv_subject:
                showSubjectDialog();
                break;
            case R.id.siv_payment_type:
                showPaymentDialog();
                break;
            case R.id.tv_add_product:
                startActivityForResult(new Intent(this, SupplierProductListActivity.class)
                        .putExtra(SupplierProductListActivity.FROM_CLASS, TAG)
                        .putExtra(SupplierProductListActivity.SUPPLIER_ID, mSupplier.getC_id()), RESULT_PRODUCT_LIST);
                break;
            case R.id.tv_scan_product:
                new RxPermissions(this)
                        .requestEach(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    startActivityForResult(new Intent(PurchaseDetailsActivity.this, ScanActivity.class)
                                            .putExtra(ScanActivity.FROM_CLASS, ScanActivity.CREATE_PURCHASE_ORDER), RESULT_FOR_SCAN_BAR_CODE);
                                } else {
                                    ToastUtils.show(PurchaseDetailsActivity.this, "请开启权限后重新尝试");
                                }
                            }
                        });
                break;
            case R.id.tv_bottom_left:
            case R.id.tv_bottom_right:
                if ("1".equals(getIntent().getStringExtra(PurchaseSubFragment.ITEM_STATUS)) && view.getId() == R.id.tv_bottom_left) {
                    saveAsDraft();
                    return;
                }
                changeActionForOrder(view.getId());
                break;
        }
    }

    //******** 存为草稿 ********
    private void saveAsDraft() {
        HashMap<String, String> params = new HashMap<>();
        params.put("iid", mIid); //******** 采购单id，新单为0 ********
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params.put("c_id", mSupplier.getC_id()); //******** 供应商id ********
        params.put("customerno", mSupplier.getCustomerno()); //******** 供应商编号 ********
        params.put("customername", mSupplier.getCustomername()); //******** 供应商名称 ********
        params.put("telephone", mSupplier.getTelephone()); //******** 手机号 ********
        params.put("tel", mSupplier.getDianhua()); //******** 电话 ********
        params.put("lianxiren", mSupplier.getLianxiren()); //******** 联系人 ********
        params.put("remark", mEtTip.getText().toString()); //******** 备注 ********
        params.put("quyuno", mSupplier.getQuyuno()); //******** 区域编号 ********
        params.put("quyu", mSupplier.getQuyu()); //******** 区域名称 ********
        params.put("action", "0"); //******** 0：默认 1：改单 ********
        params.put("IssDate", mSivBillingDate.getValue()); //******** 发单日期 ********
        params.put("yuji_jiaohuoriqi", mSivArrivalDate.getValue()); //******** 预计交货日期 ********
        params.put("confirm", "0"); //******** 确认订单 ********
        params.put("fendianid", mSupplier.getFendianid()); //******** 分店id ********
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        params.put("poprice_mode", jsonObject.getString("poprice_mode")); //******** 用户显示的是价格还是价码 ********
        params.put("youhuijine", mSivDiscountAmount.getValue()); //******** 优惠金额 ********
        ArrayList<HashMap<String, String>> maps = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            HashMap<String, String> map;
            for (ProductItem item : mList) {
                map = new HashMap<>();
                map.put("pnid", item.getPnid()); //******** 当前id ********
                map.put("cpid", item.getCpid()); //******** 产品id ********
                map.put("yanse", item.getYanse()); //******** 颜色 ********
                map.put("guige", item.getGuige()); //******** 规格 ********
                map.put("factor", item.getFactor()); //******** 比率 ********
                map.put("unitname", item.getUnitname()); //******** 单位 ********
                map.put("cp_qty", item.getCp_qty()); //******** 数量 ********
                map.put("order_prc", item.getOrder_prc()); //******** 单品价格 ********
                map.put("s_jiage2", item.getS_jiage2()); //******** 单位价格 ********
                map.put("zengpin", item.getZengpin()); //******** 赠品 ********
                map.put("bz", item.getBz()); //******** 备注 ********
                map.put("huohao", item.getHuohao()); //******** 货号 ********
                map.put("pricecode", item.getPricecode()); //******** 价码 ********
                map.put("jiagelaiyuan", item.getJiagelaiyuan()); //******** 价格来源 ********
                maps.add(map);
            }
        }
        params.put("cpjsonstr", JSONObject.toJSONString(maps)); //******** 产品明细json ********
        params.put("warehouseid", mWarehouseId); //******** 仓位id ********
        params.put("benci_amt", mSivPaymentAmount.getValue()); //******** 付款金额 ********
        params.put("bankid", mPayId); //******** 支付方式id ********
        params.put("accountid", mSubjectId); //******** 会计科目id ********
        LoadingViewDialog.getInstance().show(this);
        new NewPurchaseOrderLogic().requestPostPurchaseOrder(this, params, mImgPath, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    private class InnerSwipeMenuCreator implements SwipeMenuCreator {

        @Override
        public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
            SwipeMenuItem item = new SwipeMenuItem(PurchaseDetailsActivity.this);
            item.setText("删除");
            item.setTextSize(14);
            item.setWidth(SizeUtils.dp2px(48));
            item.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            item.setBackgroundColor(Color.parseColor("#FF0000"));
            item.setTextColor(Color.parseColor("#FFFFFF"));
            rightMenu.addMenuItem(item);
        }
    }

    private class InnerSwipeMenuItemClickListener implements SwipeMenuItemClickListener {

        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();
            int adapterPosition = menuBridge.getAdapterPosition();
            mList.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
            caculateTotalPrice();
            if (mAdapter.getItemCount() == 1) {
                mAdapter.getFooterLayout().setVisibility(View.GONE);
            }
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(PurchaseDetailsActivity.this, SupplierProductDetailsActivity.class);
            intent.putExtra(SupplierProductDetailsActivity.TAG, SupplierProductDetailsActivity.INFO_CHANGE_FOR_PURCHASE_DETAILS);
            intent.putExtra(SupplierProductDetailsActivity.SELECTED_PRODUCT_ITEM, mList.get(position));
            startActivityForResult(intent, RESULT_FOR_INFO_CHANGE);
            mPosition = position;
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            if (view.getId() == R.id.iv_icon) {
                ArrayList<String> list = new ArrayList<>();
                for (ProductItem.PiclistBean bean : mList.get(position).getPiclist()) {
                    list.add(bean.getPic());
                }
                startActivity(new Intent(PurchaseDetailsActivity.this, PicPorchActivity.class)
                        .putStringArrayListExtra(PicPorchActivity.PIC_LIST, list));
            } else {
                TextView tvAmount = (TextView) adapter.getViewByPosition(position, R.id.tv_amount);
                TextView tvTotalPrice = (TextView) adapter.getViewByPosition(position, R.id.tv_total_price);
                int i = Integer.parseInt(tvAmount.getText().toString());
                switch (view.getId()) {
                    case R.id.tv_reduce:
                        if (i > 0) {
                            i--;
                        }
                        break;
                    case R.id.tv_add:
                        i++;
                        break;
                }
                tvAmount.setText(String.valueOf(i));
                ProductItem item = mList.get(position);
                item.setCp_qty(String.valueOf(i));
                double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(mList.get(position).getCp_qty());
                DecimalFormat mFormat = new DecimalFormat("0.00");
                tvTotalPrice.setText(mFormat.format(totalPrice));
                tvAmount.setText(String.valueOf(i));
                caculateTotalPrice();
            }
        }
    }
}
