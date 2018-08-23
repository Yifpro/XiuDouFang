package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
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
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.utils.ExpressionUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2018/8/21
 */

public class ProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    public static final String FROM_CLASS = "from_class";
    public static final String SELECTED_PRODUCT_ITEM = "selected_product_item";

    private ImageView mIvIcon;
    private TextView mTvExpand;
    private TextView mTvCollapse;
    private EditText mEtTip;
    private LinearLayout mLinearLayout;
    private SearchInfoView mSivProductNo;
    private SearchInfoView mSivProductName;
    private SearchInfoView mSivStockAmount;
    private SearchInfoView mSivFreeAmount;
    private SearchInfoView mSivType;
    private SearchInfoView mSivSize;
    private SearchInfoView mSivBrand;
    private SearchInfoView mSivBarCode;
    private SearchInfoView mSivDetails;
    private SearchInfoView mSivPurchaseUnit;
    private SearchInfoView mSivPriceSource;
    private SearchInfoView mSivSinglePrice;
    private SearchInfoView mSivUnitPrice;
    private SearchInfoView mSivProcutColor;
    private SearchInfoView mSivProductSpec;
    private SearchInfoView mSivPurchaseAmount;
    private SearchInfoView mSivGift;
    private SearchInfoView mSivGoodsNo;
    private MoreRateDialog mMoreRateDialog;
    private SingleLineTextDialog mPurchaseUnitDialog;
    private SingleLineTextDialog mPriceSourceDialog;
    private SingleLineTextDialog mSpecDialog;
    private SingleLineTextDialog mColorDialog;
    private TextView mTvBottomLeft;
    private TextView mTvBottomRight;

    private String mCurrentFactor;
    private String mCurrentUnit;
    private String mHistorySinglePrice;
    private ProductListBean.ProductBean mProductBean;
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
        return R.layout.activity_product_details;
    }

    @Override
    public void initView() {
        setTitle("产品详情");
        mIvIcon = findViewById(R.id.iv_icon);
        mTvExpand = findViewById(R.id.tv_expand);
        mTvCollapse = findViewById(R.id.tv_collapse);
        mEtTip = findViewById(R.id.et_tip);
        mLinearLayout = findViewById(R.id.linear_layout);
        mSivProductNo = findViewById(R.id.siv_product_no);
        mSivProductName = findViewById(R.id.siv_product_name);
        mSivStockAmount = findViewById(R.id.siv_stock_amount);
        mSivFreeAmount = findViewById(R.id.siv_free_amount);
        mSivType = findViewById(R.id.siv_type);
        mSivSize = findViewById(R.id.siv_size);
        mSivBrand = findViewById(R.id.siv_brand);
        mSivBarCode = findViewById(R.id.siv_bar_code);
        mSivDetails = findViewById(R.id.siv_details);
        mSivPurchaseUnit = findViewById(R.id.siv_purchase_unit);
        mSivPriceSource = findViewById(R.id.siv_price_source);
        mSivSinglePrice = findViewById(R.id.siv_single_price);
        mSivUnitPrice = findViewById(R.id.siv_unit_price);
        mSivProcutColor = findViewById(R.id.siv_product_color);
        mSivProductSpec = findViewById(R.id.siv_product_spec);
        mSivPurchaseAmount = findViewById(R.id.siv_purchase_amount);
        mSivGift = findViewById(R.id.siv_gift);
        mSivGoodsNo = findViewById(R.id.siv_goods_no);
        mTvBottomLeft = findViewById(R.id.tv_bottom_left);
        mTvBottomRight = findViewById(R.id.tv_bottom_right);

        mSivGift.setStatus(true);
        mSivUnitPrice.setKey(StringUtils.getSpannableString("单位价*", 3));
        mSivPurchaseAmount.setKey(StringUtils.getSpannableString("采购数量*", 4));
        boolean isFromProductList = getIntent().getStringExtra(FROM_CLASS).equals(ProductListActivity.TAG);
        mSivPriceSource.setValue("历史价");
        mTvBottomLeft.setText(isFromProductList ? "添加" : "完成编辑");
        mTvBottomRight.setVisibility(isFromProductList ? View.GONE : View.VISIBLE);

        mTvExpand.setOnClickListener(this);
        mTvCollapse.setOnClickListener(this);
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
        mSivProductSpec.setOnClickListener(new View.OnClickListener() {
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
            mSpecList = new ArrayList<>();
            for (ProductListBean.ProductBean.SizxlistBean bean : mProductBean.getSizxlist()) {
                mSpecList.add(bean.getSizx());
            }
            mSpecDialog = SingleLineTextDialog.newInstance(mSpecList);
            mSpecDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSize = mSpecList.get(position);
                    mSivSize.setValue(mSize);
                    mPurchaseUnitDialog.dismiss();
                }
            });
        }
        mSpecDialog.show(getSupportFragmentManager(), "SizeDialog");
    }

    private void showColorDialog() {
        if (mColorDialog == null) {
            mColorList = new ArrayList<>();
            mColorList.add("无");
            for (ProductListBean.ProductBean.ColorlistBean bean : mProductBean.getColorlist()) {
                mColorList.add(bean.getColor());
            }
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
                            singlePrice = mProductBean.getLishijialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 1:
                            singlePrice = mProductBean.getChengbenjialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 2:
                            singlePrice = mProductBean.getChuchangjialist().get(mPurchaseUnitPosition).getPrice();
                            break;
                        case 3:
                            singlePrice = mProductBean.getCankaoshoujialist().get(mPurchaseUnitPosition).getPrice();
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
            mPurchaseUnitList = new ArrayList<>();
            mProductBean = getIntent().getParcelableExtra(ProductDetailsActivity.SELECTED_PRODUCT_ITEM);
            GlideApp.with(this).load(StringUtils.PIC_URL + mProductBean.getPhotourl()).into(mIvIcon);
            mSivProductNo.setValue(mProductBean.getStyleno());
            mSivProductName.setValue(mProductBean.getStylename());
            mSivStockAmount.setValue(mProductBean.getKucunqty());
            mSivFreeAmount.setValue(mProductBean.getZiyouqty());
            mSivType.setValue(mProductBean.getClassname());
            mSivBrand.setValue(mProductBean.getPinpai());
            mSivSize.setValue(mProductBean.getXinghao());
            mSivBarCode.setValue(mProductBean.getBarcode());
            mSivDetails.setValue(mProductBean.getDetail());
            for (int i = 0; i < mProductBean.getPacklist().size(); i++) {
                ProductListBean.ProductBean.PacklistBean bean = mProductBean.getPacklist().get(i);
                if ("1".equals(bean.getCheck())) {
                    mCurrentFactor = bean.getUnit_bilv();
                    mCurrentUnit = bean.getUnitname();
                    mSivPurchaseUnit.setValue(bean.getUnit_bilv() + bean.getUnitname());
                    mPurchaseUnitPosition = i;
                }
                mPurchaseUnitList.add(bean.getUnit_bilv() + bean.getUnitname());
            }
            ProductListBean.ProductBean.LishijialistBean b = null;
            for (ProductListBean.ProductBean.LishijialistBean bean : mProductBean.getLishijialist()) {
                if (mCurrentFactor.equals(bean.getUnit_bilv()) && mCurrentUnit.equals(bean.getUnitname()))
                    b = bean;
                if ("1".equals(bean.getUnit_bilv()))
                    mHistorySinglePrice = bean.getPrice();
            }
            mSivSinglePrice.setValue(b.getPrice());
            mSivUnitPrice.setValue(b.getPrice());

            if (mProductBean.getSizxlist().size() == 0) mSivProductSpec.setVisibility(View.GONE);
            if (mProductBean.getColorlist().size() == 0) mSivProcutColor.setVisibility(View.GONE);
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
                    Toast.makeText(this, "商品数量不能小于 1", Toast.LENGTH_SHORT).show();
                }else {
                    double historyPrice = Double.parseDouble(mHistorySinglePrice);
                    if (historyPrice > 0) {
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        double unitPrice = Double.parseDouble(mSivUnitPrice.getValue());
                        double v = (unitPrice - historyPrice) / historyPrice;
                        String format = decimalFormat.format(v);
                        if (mMoreRateDialog == null) {
                            mMoreRateDialog = MoreRateDialog.newInstance(format);
                            mMoreRateDialog.setOnSubmitClickListener(new MoreRateDialog.OnSumbitClickListener() {
                                @Override
                                public void onClick() {

                                }
                            });
                        }
                        mMoreRateDialog.show(getSupportFragmentManager(), "MoreRateDialog");
                    }
                    ProductItem item = new ProductItem();
                    item.setPhotourl(mProductBean.getPhotourl());
                    item.setId(mProductBean.getCpid());
                    item.setProductNo(mProductBean.getStyleno());
                    item.setStylename(mProductBean.getStylename());
                    item.setColor(mSivProcutColor.getValue());
                    item.setSize(mSivSize.getValue());
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
                    item.setAmount(mSivPurchaseAmount.getValue());
                    item.setSinglePrice(mSivSinglePrice.getValue());
                    item.setUnitPrice(mSivUnitPrice.getValue());
                    item.setGift(!mIsLeft);
                    item.setTip(mEtTip.getText().toString());
                    item.setGoodsNo(mSivGoodsNo.getValue());
                    JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
                    String fomula = jsonObject.getString("po_jiamae_gongshi").replace("价格", mSivUnitPrice.getValue());
                    StringTokenizer tokenizer = new StringTokenizer(fomula, ",");
                    StringBuilder builder = new StringBuilder();
                    while (tokenizer.hasMoreTokens()) {
                        builder.append(tokenizer.nextToken());
                    }
                    item.setPriceCode(ExpressionUtils.getInstance().caculate(builder.toString()));
                    item.setPriceSource(mSivPriceSource.getValue());
                    Intent intent = new Intent(ProductDetailsActivity.this, NewPurchaseOrderActivity.class);
                    intent.putExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT, item);
                    startActivity(intent);
                }
                break;
        }
    }
}
