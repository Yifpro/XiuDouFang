package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
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
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.PayBean;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.bean.SupplierDetails;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.adapter.SelectedProductAdapter2;
import com.example.administrator.xiudoufang.purchase.logic.PurchaseLogic;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.example.administrator.xiudoufang.receipt.ui.ReceiptSelectorDialog;
import com.example.administrator.xiudoufang.receipt.ui.SubjectSelectorDialog;
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
import java.util.Date;
import java.util.List;

public class PurchaseDetailsActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_WAREHOUSE = 104;
    public static final String TAG = PurchaseDetailsActivity.class.getSimpleName();

    private SearchInfoView mSivOrderId;
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
    private TextView mTvAddProduct;
    private TextView mTvScanProduct;
    private ImageView mIvExtra;
    private EditText mEtTip;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;
    private LinearLayout mBottomLayout;
    private TimePickerView mPvSetupOrderTime;
    private TimePickerView mPvArrivalTime;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mPaymentDialog;
    private SwipeMenuRecyclerView mRecyclerView;

    private PurchaseLogic mPurchaseLogic;
    private CustomerListLogic mCustomerListLogic;
    private ArrayList<SubjectListBean.AccounttypesBean> mSubjectList;
    private List<ProductListBean.ProductBean> mList;
    private String mStatus;
    private String mLastWarehouse;
    private String mDirection;
    private String mSubjectId;
    private String mPayId;
    private SelectedProductAdapter2 mAdapter;

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
        mSivSetupOrderDate = findViewById(R.id.siv_setup_order_date);
        mSivArrival = findViewById(R.id.siv_arrival);
        mSivWarehourse = findViewById(R.id.siv_warehourse);
        mSivPaymentAmount = findViewById(R.id.siv_payment_amount);
        mSivDiscountAmount = findViewById(R.id.siv_discount_amount);
        mSivSubject = findViewById(R.id.siv_subject);
        mSivPaymentType = findViewById(R.id.siv_payment_type);
        mSivAccountOpening = findViewById(R.id.siv_account_opening);
        mSivPaymentAccount = findViewById(R.id.siv_payment_account);
        mTvAddProduct = findViewById(R.id.tv_add_product);
        mTvScanProduct = findViewById(R.id.tv_scan_product);
        mIvExtra = findViewById(R.id.iv_extra);
        mEtTip = findViewById(R.id.et_tip);
        mTvBottomLeft = findViewById(R.id.tv_bottom_left);
        mTvBottomRight = findViewById(R.id.tv_bottom_right);
        mBottomLayout = findViewById(R.id.bottom_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        initStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RESULT_WAREHOUSE && data != null) {
            mLastWarehouse = data.getStringExtra(WarehouseListActivity.WAREHOUSE_ID);
            mSivWarehourse.setValue(data.getStringExtra(WarehouseListActivity.WAREHOUSE_NAME));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        SupplierDetails newSupplier = getIntent().getParcelableExtra(NewPurchaseOrderActivity.SELECTED_SUPPLIER);
        if (newSupplier != null) {
            mSivSupplier.setValue(newSupplier.getName());
        }
    }

    private void initStatus() {
        mStatus = getIntent().getStringExtra(PurchaseSubFragment.ITEM_STATUS);
        switch (mStatus) {
            case "1":
                mSivSupplier.setShowNext(true);
                mSivSetupOrderDate.setShowNext(true);
                mSivArrival.setShowNext(true);
                mSivWarehourse.setShowNext(true);
                mSivSubject.setShowNext(true);
                mSivPaymentType.setShowNext(true);
                mTvBottomLeft.setText("存为草稿");
                mTvBottomRight.setText("确认单据");
                mSivSupplier.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PurchaseDetailsActivity.this, SupplierListActivity.class);
                        intent.putExtra(SupplierListActivity.FROM_CLASS, TAG);
                        startActivity(intent);
                    }
                });
                mSivSetupOrderDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSetupOrderTimePickerDialog();
                    }
                });
                mSivArrival.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showArrivalTimePickerDialog();
                    }
                });
                mSivWarehourse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(PurchaseDetailsActivity.this, WarehouseListActivity.class);
                        intent.putExtra(WarehouseListActivity.WAREHOUSE_ID, mLastWarehouse);
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
                mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                mTvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case "2":
                setStatus();
                mTvBottomLeft.setText("反确认");
                mTvBottomRight.setText("确认单据");
                mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                mTvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case "3":
                setStatus();
                mBottomLayout.setVisibility(View.GONE);
                break;
            case "4":
                setStatus();
                mTvBottomLeft.setText("反确认");
                mTvBottomRight.setText("提交分店");
                mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                mTvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case "5":
                setStatus();
                mTvBottomLeft.setText("取消提交");
                mTvBottomRight.setVisibility(View.GONE);
                mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case "6":
                setStatus();
                mTvBottomLeft.setText("取消收货");
                mTvBottomRight.setVisibility(View.GONE);
                mTvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
        }
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
            Toast.makeText(this, "暂无数据", Toast.LENGTH_SHORT).show();
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

    private void showArrivalTimePickerDialog() {
        if (mPvArrivalTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
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

    private void showSetupOrderTimePickerDialog() {
        if (mPvSetupOrderTime == null) {
            TimePickerBuilder builder = new TimePickerBuilder(PurchaseDetailsActivity.this, new OnTimeSelectListener() {
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

    private void setStatus() {
        mSivSupplier.setShowNext(false);
        mSivSetupOrderDate.setShowNext(false);
        mSivArrival.setShowNext(false);
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

    @Override
    public void initData() {
        mPurchaseLogic = new PurchaseLogic();
        mCustomerListLogic = new CustomerListLogic();
        mAdapter = new SelectedProductAdapter2(R.layout.layout_list_item_selected_product, mList);
        SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
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
        };
        SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                mList.remove(adapterPosition);
                mAdapter.notifyDataSetChanged();
            }
        };
        View footerView = View.inflate(this, R.layout.layout_list_footer_purchase_details, null);
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.addFooterView(footerView);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
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
                ProductListBean.ProductBean item = mList.get(position);
                item.setCp_qty(String.valueOf(i));
                double totalPrice = Double.parseDouble(item.getS_jiage2()) * Double.parseDouble(mList.get(position).getCp_qty());
                DecimalFormat mFormat = new DecimalFormat("0.00");
                tvTotalPrice.setText(mFormat.format(totalPrice));
                tvAmount.setText(String.valueOf(i));
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(PurchaseDetailsActivity.this, ProductDetailsActivity.class);
                intent.putExtra(ProductDetailsActivity.SELECTED_PRODUCT_ITEM, mList.get(position));
                startActivity(intent);
            }
        });
        loadPurchaseDetails();
    }

    private void loadPurchaseDetails() {
        LoadingViewDialog.getInstance().show(this);
        mPurchaseLogic.requestPurchaseDetails(getIntent().getStringExtra(PurchaseSubFragment.ORDER_ID), new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                JSONObject result = JSONObject.parseObject(response.body());
                JSONObject jsonObject = result.getJSONObject("results");
                mSivOrderId.setValue(jsonObject.getString("iid"));
                mSivSupplier.setValue(jsonObject.getString("customername"));
                mSivDebt.setValue(jsonObject.getString("debt"));
                mSivSetupOrderDate.setValue(jsonObject.getString("issDate"));
                mSivArrival.setValue(jsonObject.getString("etadate"));
                mSivPaymentAmount.setValue(jsonObject.getString("benci_amt"));
                mSivDiscountAmount.setValue(jsonObject.getString("youhuijine"));
                loadSubject(jsonObject.getString("accountid"));
                loadPaymentInfo(jsonObject.getString("bankid"));
                if (!TextUtils.isEmpty(jsonObject.getString("remark")))
                    mEtTip.setText(jsonObject.getString("remark"));
                mLastWarehouse = jsonObject.getString("warehouseid");
                mList = JSONObject.parseArray(result.getJSONArray("puiasm").toJSONString(), ProductListBean.ProductBean.class);
                mAdapter.setNewData(mList);
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
}
