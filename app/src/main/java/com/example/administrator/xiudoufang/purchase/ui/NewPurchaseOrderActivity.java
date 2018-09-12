package com.example.administrator.xiudoufang.purchase.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class NewPurchaseOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final int RESULT_WAREHOUSE = 104;
    public static final int RESULT_PRODUCT_LIST = 103;
    public static final String SELECTED_PRODUCT = "selected_product";
    public static final String SELECTED_PRODUCT_LIST = "selected_product_list";
    public static final String TAG = NewPurchaseOrderActivity.class.getSimpleName();
    private static final int RESULT_FOR_SCAN_BAR_CODE = 130; //******** 扫描条形码 ********

    private SearchInfoView mSivSupplier;
    private SearchInfoView mSivDebt;
    private SearchInfoView mSivSetupOrderDate;
    private SearchInfoView mSivArrivalDate;
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
    private SwipeMenuRecyclerView mRecyclerView;
    private ImageView mIvExtra;
    private ImageSelectorDialog mImageDialog;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mPaymentDialog;
    private ImageView mIvClear;

    private NewPurchaseOrderLogic mNewPurchaseOrderLogic;
    private CustomerListLogic mCustomerListLogic;
    private ArrayList<SubjectListBean.AccounttypesBean> mSubjectList;
    private List<ProductItem> mProductItemList;
    private Supplier mSupplier;
    private String mWarehouseId = "";
    private String mSubjectId = "";
    private String mPayId = "";
    private SelectedProductListAdapter mAdapter;
    private String mImgPath;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_purchase_order;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Supplier supplier = getIntent().getParcelableExtra(SupplierDetailsActivity.SELECTED_SUPPLIER);
        ProductItem item = getIntent().getParcelableExtra(SELECTED_PRODUCT);
        if (supplier != null) { //******** 返回选择的供应商 ********
            mSivSupplier.setValue(supplier.getName());
            mSivDebt.setValue(supplier.getDebt());
            mSupplier = supplier;
        } else if (item != null) { //******** 返回选中的单个产品 ********
            if (mProductItemList == null)
                mProductItemList = new ArrayList<>();
            mProductItemList.add(item);
            mAdapter.setNewData(mProductItemList);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            caculateTotalPrice();
        }
    }

    @Override
    public void initView() {
        setTitle("采购单详情");
        mSivSupplier = findViewById(R.id.siv_supplier);
        mSivDebt = findViewById(R.id.siv_debt);
        mSivSetupOrderDate = findViewById(R.id.siv_setup_order_date);
        mSivArrivalDate = findViewById(R.id.siv_arrival_date);
        mSivWarehourse = findViewById(R.id.siv_warehourse);
        mSivPaymentAmount = findViewById(R.id.siv_payment_amount);
        mSivDiscountAmount = findViewById(R.id.siv_discount_amount);
        mSivSubject = findViewById(R.id.siv_subject);
        mSivPaymentType = findViewById(R.id.siv_payment_type);
        mSivAccountOpening = findViewById(R.id.siv_account_opening);
        mSivPaymentAccount = findViewById(R.id.siv_payment_account);
        mRecyclerView = findViewById(R.id.recycler_view);
        mIvExtra = findViewById(R.id.iv_extra);
        mEtTip = findViewById(R.id.et_tip);
        mIvClear = findViewById(R.id.iv_clear);

        mSivArrivalDate.setValue(StringUtils.getCurrentTime());
        mSivSetupOrderDate.setValue(StringUtils.getCurrentTime());
        mSivPaymentAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);
        mSivDiscountAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);
        ((TextView) findViewById(R.id.tv_bottom_left)).setText("存为草稿");
        ((TextView) findViewById(R.id.tv_bottom_right)).setText("确认单据");

        mIvExtra.setOnClickListener(this);
        mSivSupplier.setOnClickListener(this);
        mSivSetupOrderDate.setOnClickListener(this);
        mSivArrivalDate.setOnClickListener(this);
        mSivWarehourse.setOnClickListener(this);
        mSivSubject.setOnClickListener(this);
        mSivPaymentType.setOnClickListener(this);
        findViewById(R.id.tv_add_product).setOnClickListener(this);
        findViewById(R.id.tv_scan_product).setOnClickListener(this);
        findViewById(R.id.tv_bottom_left).setOnClickListener(this);
        findViewById(R.id.tv_bottom_right).setOnClickListener(this);
        findViewById(R.id.iv_clear).setOnClickListener(this);
    }

    @Override
    public void initData() {
        ((Toolbar) findViewById(R.id.tool_bar)).setNavigationOnClickListener(new NavigationClickListener());
        mNewPurchaseOrderLogic = new NewPurchaseOrderLogic();
        mCustomerListLogic = new CustomerListLogic();
        mAdapter = new SelectedProductListAdapter(R.layout.layout_list_item_selected_product, mProductItemList, "1");
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                SwipeMenuItem item = new SwipeMenuItem(NewPurchaseOrderActivity.this);
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
                mProductItemList.remove(adapterPosition);
                mAdapter.notifyItemChanged(adapterPosition);
                caculateTotalPrice();
                if (mAdapter.getItemCount() == 1) {
                    mAdapter.getFooterLayout().setVisibility(View.GONE);
                }
            }
        };
        View footerView = View.inflate(this, R.layout.layout_list_footer_purchase_details, null);
        mAdapter.addFooterView(footerView);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.getFooterLayout().setVisibility(View.GONE);
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new InnerItemChildClickListener());
        mAdapter.setOnItemClickListener(new InnerItemClickListener());
        LoadingViewDialog.getInstance().show(this);
        loadSubjectList();
    }

    private void caculateTotalPrice() {
        double result = 0;
        for (int i = 0; i < mProductItemList.size(); i++) {
            double totalPrice = Double.parseDouble(mProductItemList.get(i).getUnitPrice()) * Double.parseDouble(mProductItemList.get(i).getAmount());
            result += totalPrice;
        }
        DecimalFormat mFormat = new DecimalFormat("0.00");
        ((TextView) mAdapter.getFooterLayout().findViewById(R.id.tv)).setText(mFormat.format(result));
    }

    //******** 选择支付方式 ********
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

    //******** 选择会计科目 ********
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

    //******** 加载科目列表 ********
    @SuppressWarnings("unchecked")
    private void loadSubjectList() {
        mCustomerListLogic.requestSubjectList(this, "5", new JsonCallback<SubjectListBean>() {
            @Override
            public void onSuccess(Response<SubjectListBean> response) {
                LoadingViewDialog.getInstance().dismiss();
                List<SubjectListBean.AccounttypesBean> accounttypes = response.body().getAccounttypes();
                mSubjectList = new ArrayList<>();
                mSubjectList = (ArrayList) accounttypes;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_WAREHOUSE && data != null) { //******** 返回仓位 ********
            mWarehouseId = data.getStringExtra(WarehouseListActivity.WAREHOUSE_ID);
            mSivWarehourse.setValue(data.getStringExtra(WarehouseListActivity.WAREHOUSE_NAME));
        } else if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) { //******** 返回附件 ********
            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            mImgPath = selectList.get(0).getCompressPath();
            GlideApp.with(this).load(mImgPath).into(mIvExtra);
            mIvClear.setVisibility(View.VISIBLE);
        } else if (requestCode == RESULT_PRODUCT_LIST && data != null) { //******** 返回选择的产品列表 ********
            ArrayList<ProductItem> items = data.getParcelableArrayListExtra(SELECTED_PRODUCT_LIST);
            if (mProductItemList == null) mProductItemList = new ArrayList<>();
            mProductItemList.addAll(items);
            mAdapter.setNewData(items);
            mAdapter.getFooterLayout().setVisibility(View.VISIBLE);
            caculateTotalPrice();
        } else if (requestCode == ScanActivity.CREATE_PURCHASE_ORDER && data != null) { //******** 返回扫码产品 ********

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.siv_supplier:
                startActivity(new Intent(NewPurchaseOrderActivity.this, SupplierListActivity.class)
                        .putExtra(SupplierListActivity.FROM_CLASS, TAG));
                break;
            case R.id.siv_setup_order_date:
                showSetupOrderTimePickerDialog();
                break;
            case R.id.siv_arrival_date:
                showArrivalTimePickerDialog();
                break;
            case R.id.siv_warehourse:
                startActivityForResult(new Intent(NewPurchaseOrderActivity.this, WarehouseListActivity.class)
                        .putExtra(WarehouseListActivity.WAREHOUSE_ID, mWarehouseId), RESULT_WAREHOUSE);
                break;
            case R.id.siv_subject:
                showSubjectDialog();
                break;
            case R.id.siv_payment_type:
                showPaymentDialog();
                break;
            case R.id.tv_add_product:
                if (TextUtils.isEmpty(mSivSupplier.getValue())) {
                    ToastUtils.show(this, "请先选择供应商");
                    return;
                }
                Intent intent = new Intent(this, SupplierProductListActivity.class);
                intent.putExtra(SupplierProductListActivity.SUPPLIER_ID, mSupplier.getC_id());
                startActivityForResult(intent, RESULT_PRODUCT_LIST);
                break;
            case R.id.tv_scan_product:
                new RxPermissions(this)
                        .requestEach(Manifest.permission.CAMERA)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(Permission permission) throws Exception {
                                if (permission.granted) {
                                    startActivityForResult(new Intent(NewPurchaseOrderActivity.this, ScanActivity.class)
                                            .putExtra(ScanActivity.FROM_CLASS, ScanActivity.CREATE_PURCHASE_ORDER), RESULT_FOR_SCAN_BAR_CODE);
                                } else {
                                    ToastUtils.show(NewPurchaseOrderActivity.this, "请开启权限后重新尝试");
                                }
                            }
                        });
                break;
            case R.id.iv_clear:
                mIvExtra.setImageResource(R.mipmap.ic_extra_place);
                mIvClear.setVisibility(View.GONE);
                mImgPath = null;
                break;
            case R.id.iv_extra:
                showImageDialog();
                break;
            case R.id.tv_bottom_left:
                submitOrder(true);
                break;
            case R.id.tv_bottom_right:
                submitOrder(false);
                break;
        }
    }

    private void showImageDialog() {
        if (mImageDialog == null) {
            mImageDialog = new ImageSelectorDialog();
        }
        mImageDialog.show(getSupportFragmentManager(), "ImageSelectorDialog");
    }

    //******** 提交订单 ********
    private void submitOrder(boolean isDraft) {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        HashMap<String, String> params = new HashMap<>();
        params.put("iid", "0"); //******** 采购单id，新单为0 ********
        params.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("c_id", StringUtils.checkEmpty(mSupplier.getC_id(), "0")); //******** 供应商id ********
        params.put("customerno", StringUtils.checkEmpty(mSupplier.getCustomerNo(), "0")); //******** 供应商编号 ********
        params.put("customername", StringUtils.checkEmpty(mSupplier.getName(), "")); //******** 供应商名称 ********
        params.put("telephone", StringUtils.checkEmpty(mSupplier.getNewPhoneNum(), "")); //******** 手机号 ********
        params.put("tel", StringUtils.checkEmpty(mSupplier.getNewTelephoneNum(), "")); //******** 电话 ********
        params.put("lianxiren", StringUtils.checkEmpty(mSupplier.getNewContact(), "")); //******** 联系人 ********
        params.put("remark", mEtTip.getText().toString()); //******** 备注 ********
        params.put("quyuno", StringUtils.checkEmpty(mSupplier.getAreaNo(), "")); //******** 区域编号 ********
        params.put("quyu", StringUtils.checkEmpty(mSupplier.getAreaName(), "")); //******** 区域名称 ********
        params.put("action", "0"); //******** 0：默认 1：改单 ********
        params.put("IssDate", mSivSetupOrderDate.getValue()); //******** 发单日期 ********
        params.put("yuji_jiaohuoriqi", mSivArrivalDate.getValue()); //******** 预计交货日期 ********
        params.put("confirm", isDraft ? "0" : "1"); //******** 确认订单 ********
        params.put("fendianid", mSupplier.getFendianid()); //******** 分店id ********
        params.put("poprice_mode", jsonObject.getString("poprice_mode")); //******** 用户显示的是价格还是价码 ********
        params.put("youhuijine", mSivDiscountAmount.getValue()); //******** 优惠金额 ********
        ArrayList<HashMap<String, String>> maps = new ArrayList<>();
        if (mProductItemList != null && mProductItemList.size() > 0) {
            HashMap<String, String> map;
            for (ProductItem item : mProductItemList) {
                map = new HashMap<>();
                map.put("pnid", "0"); //******** 当前id ********
                map.put("cpid", item.getCpid()); //******** 产品id ********
                map.put("yanse", item.getColor()); //******** 颜色 ********
                map.put("guige", item.getSize()); //******** 规格 ********
                map.put("factor", item.getFactor()); //******** 比率 ********
                map.put("unitname", item.getUnit()); //******** 单位 ********
                map.put("cp_qty", item.getAmount()); //******** 数量 ********
                map.put("order_prc", item.getSinglePrice()); //******** 单品价格 ********
                map.put("s_jiage2", item.getUnitPrice()); //******** 单位价格 ********
                map.put("zengpin", item.isGift() ? "1" : "0"); //******** 赠品 ********
                map.put("bz", item.getTip()); //******** 备注 ********
                map.put("huohao", item.getGoodsNo()); //******** 货号 ********
                map.put("pricecode", item.getPriceCode()); //******** 价码 ********
                map.put("jiagelaiyuan", item.getPriceSource()); //******** 价格来源 ********
                maps.add(map);
            }
        }
        params.put("cpjsonstr", JSONObject.toJSONString(maps)); //******** 产品明细json ********
        params.put("warehouseid", mWarehouseId); //******** 仓位id ********
        params.put("benci_amt", mSivPaymentAmount.getValue()); //******** 付款金额 ********
        params.put("bankid", mPayId); //******** 支付方式id ********
        params.put("accountid", mSubjectId); //******** 会计科目id ********
        LoadingViewDialog.getInstance().show(this);
        mNewPurchaseOrderLogic.requestPostPurchaseOrder(this, params, mImgPath, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                LoadingViewDialog.getInstance().dismiss();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    //******** 预计到货日期选择框 ********
    private void showArrivalTimePickerDialog() {
        if (mPvArrivalTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(NewPurchaseOrderActivity.this, new OnTimeSelectListener() {
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
    private void showSetupOrderTimePickerDialog() {
        if (mPvSetupOrderTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(NewPurchaseOrderActivity.this, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
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
        Calendar calendar = Calendar.getInstance();
        String[] split = mSivSetupOrderDate.getValue().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mPvSetupOrderTime.setDate(calendar);
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
        if (!TextUtils.isEmpty(mSivSupplier.getValue())) {
            showExitEditDialog();
        } else {
            super.onBackPressed();
        }
    }

    //******** 退出编辑对话框 ********
    private void showExitEditDialog() {
        if (TextUtils.isEmpty(mSivSupplier.getValue())) finish();
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

    private class NavigationClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            showExitEditDialog();
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
                    if (i > 1) {
                        i--;
                    }
                    break;
                case R.id.tv_add:
                    i++;
                    break;
            }
            tvAmount.setText(String.valueOf(i));
            ProductItem item = mProductItemList.get(position);
            item.setAmount(String.valueOf(i));
            double totalPrice = Double.parseDouble(item.getUnitPrice()) * Double.parseDouble(mProductItemList.get(position).getAmount());
            DecimalFormat mFormat = new DecimalFormat("0.00");
            tvTotalPrice.setText(mFormat.format(totalPrice));
            tvAmount.setText(String.valueOf(i));
            caculateTotalPrice();
        }
    }

    private class InnerItemClickListener implements BaseQuickAdapter.OnItemClickListener {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Intent intent = new Intent(NewPurchaseOrderActivity.this, SupplierProductDetailsActivity.class);
            intent.putExtra(SupplierProductDetailsActivity.FROM_CLASS, TAG);
            intent.putExtra(SupplierProductDetailsActivity.SELECTED_PRODUCT_ITEM, mProductItemList.get(position));
            startActivity(intent);
        }
    }
}
