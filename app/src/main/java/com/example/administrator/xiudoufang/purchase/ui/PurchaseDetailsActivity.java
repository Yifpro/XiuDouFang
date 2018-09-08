package com.example.administrator.xiudoufang.purchase.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.bean.Supplier;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.adapter.SelectedProductListAdapter;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.example.administrator.xiudoufang.receipt.ui.ReceiptSelectorDialog;
import com.example.administrator.xiudoufang.receipt.ui.SubjectSelectorDialog;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.callback.StringCallback;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.example.administrator.xiudoufang.purchase.ui.NewPurchaseOrderActivity.RESULT_PRODUCT_LIST;
import static com.example.administrator.xiudoufang.purchase.ui.NewPurchaseOrderActivity.SELECTED_PRODUCT_LIST;

public class PurchaseDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final int RESULT_WAREHOUSE = 104;
    public static final String TAG = PurchaseDetailsActivity.class.getSimpleName();

    private SearchInfoView mSivOrderId;
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
    private LinearLayout mBottomLayout;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;
    private TimePickerView mPvSetupOrderTime;
    private TimePickerView mPvArrivalTime;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mPaymentDialog;

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
    private JSONObject mJsonObject;

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
        mSivBillingDate = findViewById(R.id.siv_billing_date);
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
        mBottomLayout = findViewById(R.id.bottom_layout);
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
                findViewById(R.id.tv_add_product).setOnClickListener(this);
                findViewById(R.id.tv_scan_product).setOnClickListener(this);
                findViewById(R.id.tv_bottom_left).setOnClickListener(this);
                findViewById(R.id.tv_bottom_right).setOnClickListener(this);
                mSivSupplier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PurchaseDetailsActivity.this, SupplierListActivity.class);
                        intent.putExtra(SupplierListActivity.FROM_CLASS, TAG);
                        startActivity(intent);
                    }
                });
                mSivBillingDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showBillingDatePickerDialog();
                    }
                });
                mSivArrivalDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showArrivalDatePickerDialog();
                    }
                });
                mSivWarehourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PurchaseDetailsActivity.this, WarehouseListActivity.class);
                        intent.putExtra(WarehouseListActivity.WAREHOUSE_ID, mWarehouseId);
                        startActivityForResult(intent, RESULT_WAREHOUSE);
                    }
                });
                mSivSubject.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSubjectDialog();
                    }
                });
                mSivPaymentType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showPaymentDialog();
                    }
                });
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
                mBottomLayout.setVisibility(View.GONE);
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

    private void changeActionForOrder(int viewId) {
        if (mActionParams == null) {
            SharedPreferences preferences = PreferencesUtils.getPreferences();
            mActionParams = new HashMap<>();
            mActionParams.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
            mActionParams.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
            mActionParams.put("iid", getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID));
        }
        mActionParams.put("action", mPurchaseLogic.getAction(viewId, getIntent().getStringExtra(PurchaseSubFragment.ITEM_STATUS)));
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestActionForOrder(mActionParams, new JsonCallback<String>() {
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
        mSupplier = getIntent().getParcelableExtra(SupplierDetailsActivity.SELECTED_SUPPLIER);
        ProductItem item = getIntent().getParcelableExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT);
        if (mSupplier != null) {
            mSivSupplier.setValue(mSupplier.getName());
            mSivDebt.setValue(mSupplier.getDebt());
        } else if (item != null) {
            if (mList == null)
                mList = new ArrayList<>();
            mList.add(item);
            mAdapter.setNewData(mList);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            caculateTotalPrice();
        }
    }

    @Override
    public void initData() {
        mPurchaseLogic = new PurchaseLogic();
        mCustomerListLogic = new CustomerListLogic();
        mAdapter = new SelectedProductListAdapter(R.layout.layout_list_item_selected_product, mList, mStatus);
        View footerView = View.inflate(this, R.layout.layout_list_footer_purchase_details, null);
        mAdapter.addFooterView(footerView);
        mRecyclerView.setSwipeMenuCreator(new InnerSwipeMenuCreator());
        mRecyclerView.setSwipeMenuItemClickListener(new InnerSwipeMenuItemClickListener());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.getFooterLayout().setVisibility(View.GONE);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        loadPurchaseDetails();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_WAREHOUSE && data != null) {
            mWarehouseId = data.getStringExtra(WarehouseListActivity.WAREHOUSE_ID);
            mSivWarehourse.setValue(data.getStringExtra(WarehouseListActivity.WAREHOUSE_NAME));
        } else if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) { //******** 附件返回结果 ********
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mImgPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mImgPath).into(mIvExtra);
            mIvClear.setVisibility(View.VISIBLE);
        } else if (requestCode == RESULT_PRODUCT_LIST && data != null) {
            ArrayList<ProductItem> items = data.getParcelableArrayListExtra(SELECTED_PRODUCT_LIST);
            if (mList == null) {
                mList = new ArrayList<>();
            } else {
                mList.clear();
            }
            mList.addAll(items);
            mAdapter.setNewData(items);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            caculateTotalPrice();
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

    private void showArrivalDatePickerDialog() {
        if (mPvArrivalTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

    private void showBillingDatePickerDialog() {
        if (mPvSetupOrderTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
            double totalPrice = Double.parseDouble(mList.get(i).getUnitPrice()) * Double.parseDouble(mList.get(i).getAmount());
            result += totalPrice;
        }
        DecimalFormat mFormat = new DecimalFormat("0.00");
        ((TextView) mAdapter.getFooterLayout().findViewById(R.id.tv)).setText(mFormat.format(result));
    }

    private void loadPurchaseDetails() {
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestPurchaseDetails(getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mJsonObject = JSONObject.parseObject(response.body());
                JSONObject result = mJsonObject.getJSONObject("results");
                mSupplier = new Supplier();
                mSupplier.setId(result.getString("c_id"));
                mSupplier.setCustomerNo(result.getString("customerno"));
                mSupplier.setName(result.getString("customername"));
                mSupplier.setNewPhoneNum(result.getString("telephone"));
                mSupplier.setNewTelephoneNum(result.getString("tel"));
                mSupplier.setNewContact(result.getString("lianxiren"));
                mSupplier.setAreaNo(result.getString("quyuno"));
                mSupplier.setAreaName(result.getString("quyu"));
                mSupplier.setFendianid(result.getString("fendianid"));

                mSivOrderId.setValue(result.getString("iid"));
                mSivSupplier.setValue(result.getString("customername"));
                mSivDebt.setValue(result.getString("debt"));
                mSivBillingDate.setValue(result.getString("issDate").substring(0, 9));
                String etadate = result.getString("etadate");
                mSivArrivalDate.setValue(etadate.length() > 8 ? etadate.substring(0, 9) : etadate);
                mSivPaymentAmount.setValue(result.getString("benci_amt"));
                mSivDiscountAmount.setValue(result.getString("youhuijine"));
                mEtTip.setText(result.getString("remark"));
                mWarehouseId = result.getString("warehouseid");
                mPayId = result.getString("bankid");
                mSubjectId = result.getString("accountid");
                mSivWarehourse.setValue(result.getString("warehouse"));
                if (!TextUtils.isEmpty(result.getString("fujian")))
                    GlideApp.with(PurchaseDetailsActivity.this).load(StringUtils.FILE_URL + result.getString("fujian")).error(R.mipmap.ic_icon).into(mIvExtra);
                mList = mPurchaseLogic.parseProductListJson(mJsonObject);
                mAdapter.setNewData(mList);
                if (mList.size() == 0) {
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
        mCustomerListLogic.requestSubjectList("5", new JsonCallback<SubjectListBean>() {
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
            case R.id.tv_add_product:
                break;
            case R.id.tv_scan_product:
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

    private void saveAsDraft() {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        HashMap<String, String> params = new HashMap<>();
        params.put("iid", mJsonObject.getJSONObject("result").getString("iid"));
        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("c_id", StringUtils.checkEmpty(mSupplier.getId(), "0"));
        params.put("customerno", StringUtils.checkEmpty(mSupplier.getCustomerNo(), "0"));
        params.put("customername", StringUtils.checkEmpty(mSupplier.getName(), ""));
        params.put("telephone", StringUtils.checkEmpty(mSupplier.getNewPhoneNum(), ""));
        params.put("tel", StringUtils.checkEmpty(mSupplier.getNewTelephoneNum(), ""));
        params.put("lianxiren", StringUtils.checkEmpty(mSupplier.getNewContact(), ""));
        params.put("remark", mEtTip.getText().toString());
        params.put("quyuno", StringUtils.checkEmpty(mSupplier.getAreaNo(), ""));
        params.put("quyu", StringUtils.checkEmpty(mSupplier.getAreaName(), ""));
        params.put("action", "0");
        params.put("IssDate", mSivBillingDate.getValue());
        params.put("yuji_jiaohuoriqi", mSivArrivalDate.getValue());
        params.put("confirm", "0");
        params.put("fendianid", StringUtils.checkEmpty(mSupplier.getFendianid(), ""));
        params.put("poprice_mode", jsonObject.getString("poprice_mode"));
        params.put("youhuijine", mSivDiscountAmount.getValue());
        ArrayList<HashMap<String, String>> maps = new ArrayList<>();
        if (mList != null && mList.size() > 0) {
            HashMap<String, String> map;
            for (ProductItem item : mList) {
                map = new HashMap<>();
                map.put("pnid", "0");
                map.put("cpid", item.getId());
                map.put("yanse", item.getColor());
                map.put("guige", item.getSize());
                map.put("factor", item.getFactor());
                map.put("unitname", item.getUnit());
                map.put("cp_qty", item.getAmount());
                map.put("order_prc", item.getSinglePrice());
                map.put("s_jiage2", item.getUnitPrice());
                map.put("zengpin", item.isGift() ? "1" : "0");
                map.put("bz", item.getTip());
                map.put("huohao", item.getGoodsNo());
                map.put("pricecode", item.getPriceCode());
                map.put("jiagelaiyuan", item.getPriceSource());
                maps.add(map);
            }
        }
        params.put("cpjsonstr", JSONObject.toJSONString(maps));
        params.put("warehouseid", mWarehouseId);
        params.put("benci_amt", mSivPaymentAmount.getValue());
        params.put("bankid", mPayId);
        params.put("accountid", mSubjectId);
        LoadingViewDialog.getInstance().show(this);
        new NewPurchaseOrderLogic().requestPostPurchaseOrder(params, mImgPath, new JsonCallback<String>() {
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
            item.setTextSize(16);
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
            mAdapter.notifyItemChanged(adapterPosition);
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
            intent.putExtra(SupplierProductDetailsActivity.FROM_CLASS, TAG);
            intent.putExtra(SupplierProductDetailsActivity.SELECTED_PRODUCT_ITEM, mList.get(position));
            startActivity(intent);
        }
    }

    private class InnerItemChildClickListener implements BaseQuickAdapter.OnItemChildClickListener {

        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
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
            item.setAmount(String.valueOf(i));
            double totalPrice = Double.parseDouble(item.getUnitPrice()) * Double.parseDouble(mList.get(position).getAmount());
            DecimalFormat mFormat = new DecimalFormat("0.00");
            tvTotalPrice.setText(mFormat.format(totalPrice));
            tvAmount.setText(String.valueOf(i));
            caculateTotalPrice();
        }
    }
}
