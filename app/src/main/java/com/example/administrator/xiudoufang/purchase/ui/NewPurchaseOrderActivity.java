package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
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
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.SubjectListBean;
import com.example.administrator.xiudoufang.bean.SupplierDetails;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.purchase.adapter.SelectedProductAdapter;
import com.example.administrator.xiudoufang.purchase.logic.NewPurchaseOrderLogic;
import com.example.administrator.xiudoufang.receipt.logic.CustomerListLogic;
import com.example.administrator.xiudoufang.receipt.ui.ReceiptSelectorDialog;
import com.example.administrator.xiudoufang.receipt.ui.SubjectSelectorDialog;
import com.lzy.okgo.model.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NewPurchaseOrderActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final int RESULT_WAREHOUSE = 4;
    public static final int RESULT_PRODUCT_LIST = 3;
    public static final String SELECTED_SUPPLIER = "selected_supplier";
    public static final String TAG = NewPurchaseOrderActivity.class.getSimpleName();

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
    private SwipeMenuRecyclerView mRecyclerView;
    private TextView mTvAddProduct;
    private TextView mTvScanProduct;
    private ImageView mIvExtra;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;
    private ImageSelectorDialog mImageDialog;
    private SubjectSelectorDialog mSubjectDialog;
    private ReceiptSelectorDialog mPaymentDialog;

    private NewPurchaseOrderLogic mNewPurchaseOrderLogic;
    private CustomerListLogic mCustomerListLogic;
    private List<ProductItem> mList;
    private String mLastWarehouse;
    private String mLastSubjectId;
    private SupplierDetails mNewSupplier;
    private ArrayList<SubjectListBean.AccounttypesBean> mSubjectList;
    private String mPayId;

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
        mNewSupplier = getIntent().getParcelableExtra(SELECTED_SUPPLIER);
        if (mNewSupplier != null) {
            mSivSupplier.setValue(mNewSupplier.getName());
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
        mRecyclerView = findViewById(R.id.recycler_view);
        mTvAddProduct = findViewById(R.id.tv_add_product);
        mTvScanProduct = findViewById(R.id.tv_scan_product);
        mIvExtra = findViewById(R.id.iv_extra);
        mEtTip = findViewById(R.id.et_tip);
        mTvBottomLeft = findViewById(R.id.tv_bottom_left);
        mTvBottomRight = findViewById(R.id.tv_bottom_right);

        mSivPaymentAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);
        mSivDiscountAmount.setType(SearchInfoView.ViewType.TYPE_EDIT);
        mTvBottomLeft.setText("存为草稿");
        mTvBottomRight.setText("确认单据");

        mTvAddProduct.setOnClickListener(this);
        mTvScanProduct.setOnClickListener(this);
        mIvExtra.setOnClickListener(this);
        mTvBottomLeft.setOnClickListener(this);
        mTvBottomRight.setOnClickListener(this);
        mSivSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewPurchaseOrderActivity.this, SupplierListActivity.class);
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
                Intent intent = new Intent(NewPurchaseOrderActivity.this, WarehouseListActivity.class);
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
    }

    private void showPaymentDialog() {
        if (mPaymentDialog == null) {
            mPaymentDialog = new ReceiptSelectorDialog();
            mPaymentDialog.setOnItemChangedListener(new ReceiptSelectorDialog.OnItemChangedListener() {
                @Override
                public void onItemChanged(String payId, String payName, String number, String content) {
                    mPayId = payId;
                    if ("现金支付".equals(payName))
                        number = "现金支付";
                    mSivPaymentType.setValue(content);
                    mSivAccountOpening.setValue(payName);
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
                    mLastSubjectId = subjectId;
                    mSivSubject.setValue(showName);
                }
            });
        }
        mSubjectDialog.show(getSupportFragmentManager(), "SubjectSelectorDialog");
    }

    @Override
    public void initData() {
        ((Toolbar) findViewById(R.id.tool_bar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitEditDialog();
            }
        });
        mNewPurchaseOrderLogic = new NewPurchaseOrderLogic();
        mCustomerListLogic = new CustomerListLogic();
        mSivSetupOrderDate.setValue(StringUtils.getCurrentTime());
        mSivArrival.setValue(StringUtils.getCurrentTime());
        final SelectedProductAdapter mAdapter = new SelectedProductAdapter(R.layout.layout_list_item_selected_product, mList);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
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
        mRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        SwipeMenuItemClickListener swipeMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                menuBridge.closeMenu();
                int adapterPosition = menuBridge.getAdapterPosition();
                mList.remove(adapterPosition);
                mAdapter.notifyDataSetChanged();
            }
        };
        mRecyclerView.setSwipeMenuItemClickListener(swipeMenuItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadSubjectList();
    }

    private void loadSubjectList() {
        mCustomerListLogic.requestSubjectList("5", new JsonCallback<SubjectListBean>() {
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
        if (requestCode == RESULT_WAREHOUSE && data != null) {
            mLastWarehouse = data.getStringExtra(WarehouseListActivity.WAREHOUSE_ID);
            mSivWarehourse.setValue(data.getStringExtra(WarehouseListActivity.WAREHOUSE_NAME));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_add_product:
                if (TextUtils.isEmpty(mSivSupplier.getValue())) {
                    Toast.makeText(this, "请先选择供应商", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(this, ProductListActivity.class);
                startActivityForResult(intent, RESULT_PRODUCT_LIST);
                break;
            case R.id.tv_scan_product:
                ScanActivity.start(this);
                break;
            case R.id.iv_extra:
                if (mImageDialog == null)
                    mImageDialog = new ImageSelectorDialog();
                mImageDialog.show(getSupportFragmentManager(), "ImageSelectorDialog");
                break;
            case R.id.tv_bottom_left:
                submitOrder(true);
                break;
            case R.id.tv_bottom_right:
                submitOrder(false);
                break;
        }
    }

    private void submitOrder(boolean isDraft) {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", preferences.getString(PreferencesUtils.USER_ID, ""));
        map.put("c_id", StringUtils.checkEmpty(mNewSupplier.getId(), "0"));
        map.put("customerno", StringUtils.checkEmpty(mNewSupplier.getCustomerNo(), "0"));
        map.put("customername", StringUtils.checkEmpty(mNewSupplier.getName(), ""));
        map.put("telephone", StringUtils.checkEmpty(mNewSupplier.getNewPhoneNum(), ""));
        map.put("tel", StringUtils.checkEmpty(mNewSupplier.getNewTelephoneNum(), ""));
        map.put("lianxiren", StringUtils.checkEmpty(mNewSupplier.getNewContact(), ""));
        map.put("remark", mEtTip.getText().toString());
        map.put("quyuno", StringUtils.checkEmpty(mNewSupplier.getAreaNo(), ""));
        map.put("quyu", StringUtils.checkEmpty(mNewSupplier.getAreaName(), ""));
        map.put("action", "0");
        map.put("IssDate", mSivSetupOrderDate.getValue());
        map.put("yuji_jiaohuoriqi", mSivArrival.getValue());
//        map.put("confirm", );
//        map.put("fendianid", );
//        map.put("poprice_mode", );
//        map.put("cpjsonstr", );
        map.put("warehouseid", StringUtils.checkEmpty(mLastWarehouse, ""));
        map.put("benci_amt", mSivPaymentAmount.getValue());
        map.put("bankid", mPayId);
        map.put("accountid", mLastSubjectId);
        mNewPurchaseOrderLogic.requestSubmitOrder(map, new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
            }
        });
    }

    private void showArrivalTimePickerDialog() {
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

    private void showSetupOrderTimePickerDialog() {
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
        if (!TextUtils.isEmpty(mSivSupplier.getValue())) {
            showExitEditDialog();
        } else {
            super.onBackPressed();
        }
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
}
