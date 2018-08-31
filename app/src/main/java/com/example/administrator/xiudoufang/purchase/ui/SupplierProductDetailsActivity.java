package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.SupplierProductListBean;
import com.example.administrator.xiudoufang.common.utils.ExpressionUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;
import com.example.administrator.xiudoufang.common.widget.SingleLineTextDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2018/8/21
 */

public class SupplierProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String FROM_CLASS = "from_class";
    public static final String SELECTED_PRODUCT_ITEM = "selected_product_item";
    public static final String SELECTED_PRODUCT_BEAN = "selected_product_bean";

    private ImageView mIvIcon;
    private TextView mTvExpand;
    private EditText mEtTip;
    private LinearLayout mLinearLayout;
    private SearchInfoView mSivProductNo;
    private SearchInfoView mSivProductName;
    private SearchInfoView mSivStockAmount;
    private SearchInfoView mSivFreeAmount;
    private SearchInfoView mSivType;
    private SearchInfoView mSivModel;
    private SearchInfoView mSivBrand;
    private SearchInfoView mSivBarCode;
    private SearchInfoView mSivDetails;
    private SearchInfoView mSivPurchaseUnit;
    private SearchInfoView mSivPriceSource;
    private SearchInfoView mSivSinglePrice;
    private SearchInfoView mSivUnitPrice;
    private SearchInfoView mSivProcutColor;
    private SearchInfoView mSivSize;
    private SearchInfoView mSivPurchaseAmount;
    private SearchInfoView mSivGift;
    private SearchInfoView mSivGoodsNo;
    private MoreRateDialog mMoreRateDialog;
    private SingleLineTextDialog mPurchaseUnitDialog;
    private SingleLineTextDialog mPriceSourceDialog;
    private SingleLineTextDialog mSpecDialog;
    private SingleLineTextDialog mColorDialog;
    private StopProduceDialog mStopProduceDialog;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;

    private String mFactor;
    private String mUnit;
    private String mHistoryPrice;
    private SupplierProductListBean.SupplierProductBean mSupplierProductBean;
    private ProductItem mProductItem;
    private ArrayList<String> mPurchaseUnitList;
    private ArrayList<String> mPriceSourceList;
    private ArrayList<String> mColorList;
    private ArrayList<String> mSpecList;
    private int mPurchaseUnitPosition;
    private String mColor;
    private String mSize;
    private boolean mIsLeft;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_product_details;
    }

    @Override
    public void initView() {
        setTitle("产品详情");
        mIvIcon = findViewById(R.id.iv_icon);
        mEtTip = findViewById(R.id.et_tip);
        mTvExpand = findViewById(R.id.tv_expand);
        mLinearLayout = findViewById(R.id.linear_layout);
        mSivProductNo = findViewById(R.id.siv_product_no);
        mSivProductName = findViewById(R.id.siv_product_name);
        mSivStockAmount = findViewById(R.id.siv_stock_amount);
        mSivFreeAmount = findViewById(R.id.siv_free_amount);
        mSivType = findViewById(R.id.siv_type);
        mSivModel = findViewById(R.id.siv_model);
        mSivBrand = findViewById(R.id.siv_brand);
        mSivBarCode = findViewById(R.id.siv_bar_code);
        mSivDetails = findViewById(R.id.siv_details);
        mSivPurchaseUnit = findViewById(R.id.siv_purchase_unit);
        mSivPriceSource = findViewById(R.id.siv_price_source);
        mSivSinglePrice = findViewById(R.id.siv_single_price);
        mSivUnitPrice = findViewById(R.id.siv_unit_price);
        mSivProcutColor = findViewById(R.id.siv_product_color);
        mSivSize = findViewById(R.id.siv_size);
        mSivPurchaseAmount = findViewById(R.id.siv_purchase_amount);
        mSivGift = findViewById(R.id.siv_gift);
        mSivGoodsNo = findViewById(R.id.siv_goods_no);
        mTvBottomLeft = findViewById(R.id.tv_bottom_left);
        mTvBottomRight = findViewById(R.id.tv_bottom_right);

        mSivGift.setStatus(true);
        mSivUnitPrice.setKey(StringUtils.getSpannableString("单位价*", 3));
        mSivPurchaseAmount.setKey(StringUtils.getSpannableString("采购数量*", 4));
        boolean isFromProductList = SupplierProductListActivity.TAG.equals(getIntent().getStringExtra(FROM_CLASS));
        mSivPriceSource.setValue("历史价");
        mTvBottomLeft.setText(isFromProductList ? "添加" : "完成编辑");
        mTvBottomRight.setVisibility(isFromProductList ? View.GONE : View.VISIBLE);

        findViewById(R.id.tv_expand).setOnClickListener(this);
        findViewById(R.id.tv_collapse).setOnClickListener(this);
        mTvBottomLeft.setOnClickListener(this);
        mTvBottomRight.setOnClickListener(this);
        mSivPurchaseUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPurchaseUnitDialog();
            }
        });
        mSivPriceSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPriceSourceDialog();
            }
        });
        mSivProcutColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showColorDialog();
            }
        });
        mSivSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSpecDialog();
            }
        });
        mSivGift.setOnItemChangeListener(new SearchInfoView.OnItemChangeListener() {
            @Override
            public void onItemchange(boolean isLeft) {
                mIsLeft = isLeft;
            }
        });
    }

    private void showSpecDialog() {
        if (mSpecDialog == null) {
            mSpecDialog = SingleLineTextDialog.newInstance(mSpecList);
            mSpecDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSize = mSpecList.get(position);
                    mSivModel.setValue(mSize);
                    mPurchaseUnitDialog.dismiss();
                }
            });
        }
        mSpecDialog.show(getSupportFragmentManager(), "SizeDialog");
    }

    private void showColorDialog() {
        if (mColorDialog == null) {
            mColorDialog = SingleLineTextDialog.newInstance(mColorList);
            mColorDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mColor = mColorList.get(position);
                    mSivProcutColor.setValue(mColor);
                    mColorDialog.dismiss();
                }
            });
        }
        mColorDialog.show(getSupportFragmentManager(), "ColorDialog");
    }

    private void showPriceSourceDialog() {
        if (mPriceSourceDialog == null) {
            mPriceSourceList = new ArrayList<>();
            mPriceSourceList.add("历史价");
            mPriceSourceList.add("成本价");
            mPriceSourceList.add("出厂价");
            mPriceSourceList.add("参考价");
            mPriceSourceList.add("手动");
            mPriceSourceDialog = SingleLineTextDialog.newInstance(mPriceSourceList);
            mPriceSourceDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSivPriceSource.setValue(mPriceSourceList.get(position));
                    String singlePrice = null;
                    switch (position) {
                        case 0:
                            singlePrice = mProductItem == null ? mSupplierProductBean.getLishijialist().get(mPurchaseUnitPosition).getPrice() :
                                    mProductItem.getLishijialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 1:
                            singlePrice = mProductItem == null ? mSupplierProductBean.getChengbenjialist().get(mPurchaseUnitPosition).getPrice() :
                                    mProductItem.getChengbenjialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 2:
                            singlePrice = mProductItem == null ? mSupplierProductBean.getChuchangjialist().get(mPurchaseUnitPosition).getPrice() :
                                    mProductItem.getChuchangjialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 3:
                            singlePrice = mProductItem == null ? mSupplierProductBean.getCankaoshoujialist().get(mPurchaseUnitPosition).getPrice() :
                                    mProductItem.getCankaoshoujialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 4:
                            singlePrice = "";
                            break;
                    }
                    mSivSinglePrice.setValue(singlePrice);
                    mSivUnitPrice.setValue(singlePrice);
                }
            });
        }
        mPriceSourceDialog.show(getSupportFragmentManager(), "PriceSourceDialog");
    }

    private void showPurchaseUnitDialog() {
        if (mPurchaseUnitDialog == null) {
            mPurchaseUnitDialog = SingleLineTextDialog.newInstance(mPurchaseUnitList);
            mPurchaseUnitDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mPurchaseUnitPosition = position;
                    mSivPurchaseUnit.setValue(mPurchaseUnitList.get(position));
                    mPurchaseUnitDialog.dismiss();
                }
            });
        }
        mPurchaseUnitDialog.show(getSupportFragmentManager(), "PurchaseUnitDialog");
    }

    @Override
    public void initData() {
        if (getIntent() != null) {
            String photoUrl, productNo, productName, stockAmount, freeAmount, type, brand, model, barCode, details,
                    singlePrice = "", amount = "", goodsNo = "", tip = "";
            boolean isNotGift = true;
            mPurchaseUnitList = new ArrayList<>();
            Parcelable extra = getIntent().getParcelableExtra(SELECTED_PRODUCT_ITEM);
            if (extra instanceof ProductItem) {
                mProductItem = (ProductItem) extra;
                photoUrl = mProductItem.getPhotourl();
                productNo = mProductItem.getProductNo();
                productName = mProductItem.getStylename();
                stockAmount = mProductItem.getStockAmount();
                freeAmount = mProductItem.getFreeAmount();
                type = mProductItem.getType();
                brand = mProductItem.getBrand();
                model = mProductItem.getModel();
                barCode = mProductItem.getPriceCode();
                details = mProductItem.getDetails();
                amount = mProductItem.getAmount();
                isNotGift = !mProductItem.isGift();
                goodsNo = mProductItem.getGoodsNo();
                tip = mProductItem.getTip();
                if (mProductItem.getPacklist() != null) {
                    for (int i = 0; i < mProductItem.getPacklist().size(); i++) {
                        ProductItem.PacklistBean bean = mProductItem.getPacklist().get(i);
                        if ("1".equals(bean.getCheck())) {
                            mFactor = bean.getUnit_bilv();
                            mUnit = bean.getUnitname();
                            mPurchaseUnitPosition = i;
                        }
                        mPurchaseUnitList.add(bean.getUnit_bilv() + bean.getUnitname());
                    }
                }
                if (mProductItem.getLishijialist() != null) {
                    for (ProductItem.LishijialistBean bean : mProductItem.getLishijialist()) {
                        if (mFactor.equals(bean.getUnit_bilv()) && mUnit.equals(bean.getUnitname()))
                            singlePrice = bean.getPrice();
                        if ("1".equals(bean.getUnit_bilv())) mHistoryPrice = bean.getPrice();
                    }
                }
                if (mProductItem.getSizxlist() == null || mProductItem.getSizxlist().size() == 0) {
                    mSivSize.setVisibility(View.GONE);
                } else {
                    mSpecList = new ArrayList<>();
                    for (ProductItem.SizxlistBean bean : mProductItem.getSizxlist()) {
                        mSpecList.add(bean.getSizx());
                    }
                }
                if (mProductItem.getColorlist() == null || mProductItem.getColorlist().size() == 0) {
                    mSivProcutColor.setVisibility(View.GONE);
                } else {
                    mColorList = new ArrayList<>();
                    mColorList.add("无");
                    for (ProductItem.ColorlistBean bean : mProductItem.getColorlist()) {
                        mColorList.add(bean.getColor());
                    }
                }
            } else {
                mSupplierProductBean = getIntent().getParcelableExtra(SELECTED_PRODUCT_BEAN);
                photoUrl = StringUtils.PIC_URL + mSupplierProductBean.getPhotourl();
                productNo = mSupplierProductBean.getStyleno();
                productName = mSupplierProductBean.getStylename();
                stockAmount = mSupplierProductBean.getKucunqty();
                freeAmount = mSupplierProductBean.getZiyouqty();
                type = mSupplierProductBean.getClassname();
                brand = mSupplierProductBean.getPinpai();
                model = mSupplierProductBean.getXinghao();
                barCode = mSupplierProductBean.getBarcode();
                details = mSupplierProductBean.getDetail();
                if (mSupplierProductBean.getPacklist() != null) {
                    for (int i = 0; i < mSupplierProductBean.getPacklist().size(); i++) {
                        SupplierProductListBean.SupplierProductBean.PacklistBean bean = mSupplierProductBean.getPacklist().get(i);
                        if ("1".equals(bean.getCheck())) {
                            mFactor = bean.getUnit_bilv();
                            mUnit = bean.getUnitname();
                            mPurchaseUnitPosition = i;
                        }
                        mPurchaseUnitList.add(bean.getUnit_bilv() + bean.getUnitname());
                    }
                }
                if (mSupplierProductBean.getLishijialist() != null) {
                    for (SupplierProductListBean.SupplierProductBean.LishijialistBean bean : mSupplierProductBean.getLishijialist()) {
                        if (mFactor.equals(bean.getUnit_bilv()) && mUnit.equals(bean.getUnitname())) {
                            singlePrice = bean.getPrice();
                        }
                        if ("1".equals(bean.getUnit_bilv())) {
                            mHistoryPrice = bean.getPrice();
                        }
                    }
                }

                if (mSupplierProductBean.getSizxlist() != null && mSupplierProductBean.getSizxlist().size() == 0) {
                    mSivSize.setVisibility(View.GONE);
                } else {
                    mSpecList = new ArrayList<>();
                    for (SupplierProductListBean.SupplierProductBean.SizxlistBean bean : mSupplierProductBean.getSizxlist()) {
                        mSpecList.add(bean.getSizx());
                    }
                }
                if (mSupplierProductBean.getColorlist() != null && mSupplierProductBean.getColorlist().size() == 0) {
                    mSivProcutColor.setVisibility(View.GONE);
                } else {
                    mColorList = new ArrayList<>();
                    mColorList.add("无");
                    for (SupplierProductListBean.SupplierProductBean.ColorlistBean bean : mSupplierProductBean.getColorlist()) {
                        mColorList.add(bean.getColor());
                    }
                }
            }
            GlideApp.with(this).load(photoUrl).error(R.mipmap.ic_icon).into(mIvIcon);
            mSivProductNo.setValue(productNo);
            mSivProductName.setValue(productName);
            mSivStockAmount.setValue(stockAmount);
            mSivFreeAmount.setValue(freeAmount);
            mSivType.setValue(type);
            mSivBrand.setValue(brand);
            mSivModel.setValue(model);
            mSivBarCode.setValue(barCode);
            mSivDetails.setValue(details);
            mSivPurchaseUnit.setValue(mFactor + mUnit);
            mSivSinglePrice.setValue(singlePrice);
            mSivUnitPrice.setValue(singlePrice);
            mSivPurchaseAmount.setValue(amount);
            mSivGift.setStatus(isNotGift);
            mSivGoodsNo.setValue(goodsNo);
            mEtTip.setText(tip);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_expand:
                mLinearLayout.setVisibility(View.VISIBLE);
                mTvExpand.setVisibility(View.GONE);
                break;
            case R.id.tv_collapse:
                mLinearLayout.setVisibility(View.GONE);
                mTvExpand.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_bottom_right:
                break;
            case R.id.tv_bottom_left:
                if (TextUtils.isEmpty(mSivUnitPrice.getValue())) {
                    mSivUnitPrice.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                } else if (TextUtils.isEmpty(mSivPurchaseAmount.getValue())) {
                    mSivPurchaseAmount.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                } else if ("0".equals(mSivPurchaseAmount.getValue()) || TextUtils.isEmpty(mSivPurchaseAmount.getValue())) {
                    ToastUtils.show(this, "商品数量不能小于 1");
                } else if (checkStopProduce()) {
                    if (mStopProduceDialog == null)
                        mStopProduceDialog = new StopProduceDialog();
                    mStopProduceDialog.show(getSupportFragmentManager(), "StopProduceDialog");
                } else {
                    double historyPrice = Double.parseDouble(mHistoryPrice);
                    if (historyPrice > 0) {
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        double unitPrice = Double.parseDouble(mSivUnitPrice.getValue());
                        double v = (unitPrice - historyPrice) / historyPrice * 100;
                        if (v > 0) {
                            String format = decimalFormat.format(v);
                            if (mMoreRateDialog == null) {
                                mMoreRateDialog = MoreRateDialog.newInstance(format);
                                mMoreRateDialog.setOnSubmitClickListener(new MoreRateDialog.OnSumbitClickListener() {
                                    @Override
                                    public void onClick() {
                                        addProduct();
                                    }
                                });
                            }
                            mMoreRateDialog.show(getSupportFragmentManager(), "MoreRateDialog");
                        } else {
                            addProduct();
                        }
                    } else {
                        addProduct();
                    }
                }
                break;
        }
    }

    private boolean checkStopProduce() {
        if (mProductItem == null && "1".equals(mSupplierProductBean.getStop_produce())) return true;
        if (mSupplierProductBean == null && mProductItem.isStopProduce()) return true;
        return false;
    }

    private void addProduct() {
        ProductItem item = new ProductItem();
        item.setId(mProductItem == null ? mSupplierProductBean.getCpid() : mProductItem.getId());
        item.setPhotourl(mProductItem == null ? mSupplierProductBean.getPhotourl() : mProductItem.getPhotourl());
        item.setProductNo(mSivProductNo.getValue());
        item.setStylename(mSivProductName.getValue());
        item.setStockAmount(mSivStockAmount.getValue());
        item.setFreeAmount(mSivFreeAmount.getValue());
        item.setType(mSivType.getValue());
        item.setBrand(mSivBrand.getValue());
        item.setModel(mSivModel.getValue());
        item.setDetails(mSivDetails.getValue());
        char[] chars = mSivPurchaseUnit.getValue().toCharArray();
        StringBuilder sb = new StringBuilder();
        int unitIndex = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                sb.append(c);
                unitIndex = i;
            }
        }
        item.setFactor(sb.toString());
        item.setUnit(mSivPurchaseUnit.getValue().substring(unitIndex + 1));
        item.setSinglePrice(mSivSinglePrice.getValue());
        item.setUnitPrice(mSivUnitPrice.getValue());
        item.setSize(mSivSize.getValue());
        item.setColor(mSivProcutColor.getValue());
        item.setAmount(mSivPurchaseAmount.getValue());
        item.setGift(!mIsLeft);
        item.setGoodsNo(mSivGoodsNo.getValue());
        item.setTip(mEtTip.getText().toString());
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        String fomula = jsonObject.getString("po_jiamae_gongshi").replace("价格", mSivUnitPrice.getValue());
        StringTokenizer tokenizer = new StringTokenizer(fomula, ",");
        StringBuilder builder = new StringBuilder();
        while (tokenizer.hasMoreTokens()) {
            builder.append(tokenizer.nextToken());
        }
        item.setPriceCode(ExpressionUtils.getInstance().caculate(builder.toString()));
        item.setPriceSource(mSivPriceSource.getValue());
        Intent intent = new Intent(SupplierProductDetailsActivity.this, NewPurchaseOrderActivity.class);
        intent.putExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT, item);
        startActivity(intent);
    }
}
