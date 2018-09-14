package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.StringPair;
import com.example.administrator.xiudoufang.common.utils.ExpressionUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.SearchInfoView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Administrator on 2018/8/21
 */

public class SupplierProductDetailsActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

    private static final String DEFAULT_FACTOR = "1";
    public static final String FROM_CLASS = "from_class";
    public static final String SELECTED_PRODUCT_ITEM = "selected_product_item";

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
    private SearchInfoView mSivUnitPricecode;
    private SearchInfoView mSivColor;
    private SearchInfoView mSivSize;
    private SearchInfoView mSivPurchaseAmount;
    private SearchInfoView mSivGift;
    private SearchInfoView mSivGoodsNo;
    private MoreRateDialog mMoreRateDialog;
    private PurchaseUnitDialog mPurchaseUnitDialog;
    private SingleLineTextDialog mPriceSourceDialog;
    private SingleLineTextDialog mSizxDialog;
    private SingleLineTextDialog mColorDialog;
    private StopProduceDialog mStopProduceDialog;

    private ProductItem mProductItem;
    private ArrayList<StringPair> mPurchaseUnitList;
    private ArrayList<String> mPriceSourceList;
    private ArrayList<String> mColorList;
    private ArrayList<String> mSizxList;
    private String mBilv; //******** 当前比率 ********
    private String mColor; //******** 当前颜色 ********
    private String mSize; //******** 当前规格 ********
    private int mPriceSourceIndex; //******** 当前价格来源 ********
    private boolean mIsNotGift; //******** true: 当前产品非赠品 ********
    private String mPriceMode; //******** 价格显示模式 ********
    private JSONObject mLoginInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_supplier_product_details;
    }

    @Override
    public void initView() {
        setTitle("产品详情");
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
        mSivUnitPricecode = findViewById(R.id.siv_unit_pricecode);
        mSivColor = findViewById(R.id.siv_product_color);
        mSivSize = findViewById(R.id.siv_size);
        mSivPurchaseAmount = findViewById(R.id.siv_purchase_amount);
        mSivGift = findViewById(R.id.siv_gift);
        mSivGoodsNo = findViewById(R.id.siv_goods_no);
        TextView tvBottomLeft = findViewById(R.id.tv_bottom_left);
        TextView tvBottomRight = findViewById(R.id.tv_bottom_right);

        mLoginInfo = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        mPriceMode = mLoginInfo.getString("poprice_mode");
        if ("0".equals(mPriceMode)) { //******** 只看见价格 ********
            mSivUnitPricecode.setVisibility(View.GONE);
        } else if ("1".equals(mPriceMode)) { //******** 只看见价码 ********
            mSivSinglePrice.setVisibility(View.GONE);
            mSivUnitPrice.setVisibility(View.GONE);
        } else if ("3".equals(mPriceMode)) { //******** 都不能看到 ********
            mSivSinglePrice.setVisibility(View.GONE);
            mSivUnitPrice.setVisibility(View.GONE);
            mSivUnitPricecode.setVisibility(View.GONE);
        }
        mSivUnitPrice.setKey(StringUtils.getSpannableString("单位价*", 3));
        mSivPurchaseAmount.setKey(StringUtils.getSpannableString("采购数量*", 4));
        mSivPriceSource.setValue("历史价");
        mSivGift.setStatus(true);
        tvBottomLeft.setText(SupplierProductListActivity.TAG.equals(getIntent().getStringExtra(FROM_CLASS)) ? "添加" : "完成编辑");
        tvBottomRight.setVisibility(View.GONE);

        mSivSize.setOnClickListener(this);
        mSivPurchaseUnit.setOnClickListener(this);
        mSivPriceSource.setOnClickListener(this);
        mSivColor.setOnClickListener(this);
        tvBottomLeft.setOnClickListener(this);
        tvBottomRight.setOnClickListener(this);
        findViewById(R.id.tv_expand).setOnClickListener(this);
        findViewById(R.id.tv_collapse).setOnClickListener(this);
        mSivUnitPrice.setOnTextChangeListener(new InnerUnitPriceTextChangeListener());
        mSivGift.setOnItemChangeListener(new InnerItemChangeListener());
    }

    @Override
    public void initData() {
        mProductItem = getIntent().getParcelableExtra(SELECTED_PRODUCT_ITEM);
        if (TextUtils.isEmpty(mProductItem.getYanse())) mSivColor.setVisibility(View.GONE);
        if (TextUtils.isEmpty(mProductItem.getGuige())) mSivSize.setVisibility(View.GONE);

        mBilv = mProductItem.getFactor();
        GlideApp.with(this).load(StringUtils.PIC_URL + mProductItem.getPhotourl()).error(R.mipmap.ic_error).into((ImageView) findViewById(R.id.iv_icon));
        mSivProductNo.setValue(mProductItem.getStyleno());
        mSivProductName.setValue(mProductItem.getStylename());
        mSivStockAmount.setValue(mProductItem.getKucunqty());
        mSivFreeAmount.setValue(mProductItem.getZiyouqty());
        mSivType.setValue(mProductItem.getClassname());
        mSivBrand.setValue(mProductItem.getPinpai());
        mSivModel.setValue(mProductItem.getXinghao());
        mSivBarCode.setValue(mProductItem.getBarcode());
        mSivDetails.setValue(mProductItem.getDetail());
        mSivPurchaseUnit.setValue(mProductItem.getFactor() + mProductItem.getUnitname());
        mSivSinglePrice.setValue(mProductItem.getOrder_prc());
        mSivUnitPrice.setValue(mProductItem.getS_jiage2());
        mSivPurchaseAmount.setValue(mProductItem.getCp_qty());
        mSivGift.setStatus("0".equals(mProductItem.getZengpin()));
        mSivGoodsNo.setValue(mProductItem.getHuohao());
        mEtTip.setText(mProductItem.getBz());
    }

    //******** 规格选择框 ********
    private void showSizeDialog() {
        if (mSizxDialog == null) {
             mSizxList = new ArrayList<>();
            for(ProductItem.SizxlistBean bean : mProductItem.getSizxlist()) {
                mSizxList.add(bean.getSizx());
            }
            for (ProductItem.ColorlistBean bean : mProductItem.getColorlist()) {
                mColorList.add(bean.getColor());
            }
            mSizxDialog = SingleLineTextDialog.newInstance(mSizxList);
            mSizxDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mSize = mSizxList.get(position);
                    mSivModel.setValue(mSize);
                    mPurchaseUnitDialog.dismiss();
                }
            });
        }
        mSizxDialog.show(getSupportFragmentManager(), "SizxDialog");
    }

    //******** 产品颜色选择框 ********
    private void showProductColorDialog() {
        if (mColorDialog == null) {
            mColorList = new ArrayList<>();
            for (ProductItem.ColorlistBean bean : mProductItem.getColorlist()) {
                mColorList.add(bean.getColor());
            }
            mColorDialog = SingleLineTextDialog.newInstance(mColorList);
            mColorDialog.setOnItemChangedListener(new SingleLineTextDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    mColor = mColorList.get(position);
                    mSivColor.setValue(mColor);
                    mColorDialog.dismiss();
                }
            });
        }
        mColorDialog.show(getSupportFragmentManager(), "ColorDialog");
    }

    //******** 价格来源选择框 ********
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
                    String singlePrice = "", unitPrice = "", priceCode = "";
                    switch (position) {
                        case 0:
                            int lishiSingleIndex = mProductItem.getLishijialist().indexOf(new ProductItem.LishijialistBean(DEFAULT_FACTOR));
                            singlePrice = mProductItem.getLishijialist().get(lishiSingleIndex).getPrice();
                            int lishiUnitIndex = mProductItem.getLishijialist().indexOf(new ProductItem.LishijialistBean(mBilv));
                            unitPrice = mProductItem.getLishijialist().get(lishiUnitIndex).getPrice();
                            priceCode = mProductItem.getLishijialist().get(lishiUnitIndex).getPricecode();
                            break;
                        case 1:
                            int chengbenSingleIndex = mProductItem.getChengbenjialist().indexOf(new ProductItem.ChengbenjialistBean(DEFAULT_FACTOR));
                            singlePrice = mProductItem.getChengbenjialist().get(chengbenSingleIndex).getPrice();
                            int chengbenUnitIndex = mProductItem.getChengbenjialist().indexOf(new ProductItem.ChengbenjialistBean(mBilv));
                            unitPrice = mProductItem.getChengbenjialist().get(chengbenUnitIndex).getPrice();
                            priceCode = mProductItem.getChengbenjialist().get(chengbenUnitIndex).getPricecode();
                            break;
                        case 2:
                            int chuchangSingleIndex = mProductItem.getChuchangjialist().indexOf(new ProductItem.ChuchangjialistBean(DEFAULT_FACTOR));
                            singlePrice = mProductItem.getChuchangjialist().get(chuchangSingleIndex).getPrice();
                            int chuchangUnitIndex = mProductItem.getChuchangjialist().indexOf(new ProductItem.ChuchangjialistBean(mBilv));
                            unitPrice = mProductItem.getChuchangjialist().get(chuchangUnitIndex).getPrice();
                            priceCode = mProductItem.getChuchangjialist().get(chuchangUnitIndex).getPricecode();
                            break;
                        case 3:
                            int cankaoSingleIndex = mProductItem.getCankaoshoujialist().indexOf(new ProductItem.CankaoshoujialistBean(DEFAULT_FACTOR));
                            singlePrice = mProductItem.getCankaoshoujialist().get(cankaoSingleIndex).getPrice();
                            int cankaoUnitIndex = mProductItem.getCankaoshoujialist().indexOf(new ProductItem.CankaoshoujialistBean(mBilv));
                            unitPrice = mProductItem.getCankaoshoujialist().get(cankaoUnitIndex).getPrice();
                            priceCode = mProductItem.getCankaoshoujialist().get(cankaoUnitIndex).getPricecode();
                            break;
                        case 4:
                            break;
                    }
                    mSivPriceSource.setValue(mPriceSourceList.get(position));
                    mSivSinglePrice.setValue(singlePrice);
                    mSivUnitPrice.setValue(unitPrice);
                    mSivUnitPricecode.setValue(priceCode);
                    mPriceSourceIndex = position;
                }
            });
        }
        mPriceSourceDialog.show(getSupportFragmentManager(), "PriceSourceDialog");
    }

    //******** 采购单位选择框 ********
    private void showPurchaseUnitDialog() {
        if (mPurchaseUnitDialog == null) {
            mPurchaseUnitList = new ArrayList<>();
            for (ProductItem.PacklistBean bean : mProductItem.getPacklist()) {
                mPurchaseUnitList.add(new StringPair(bean.getUnit_bilv(), bean.getUnitname()));
            }
            mPurchaseUnitDialog = PurchaseUnitDialog.newInstance(mPurchaseUnitList);
            mPurchaseUnitDialog.setOnItemChangedListener(new PurchaseUnitDialog.OnItemClickListener() {
                @Override
                public void onItemClick(int position, String bilv) {
                    StringPair pair = mPurchaseUnitList.get(position);
                    String unitPrice= "", priceCode = "";
                    switch (mPriceSourceIndex) {
                        case 0:
                            int lishiUnitIndex = mProductItem.getLishijialist().indexOf(new ProductItem.LishijialistBean(bilv));
                            unitPrice = mProductItem.getLishijialist().get(lishiUnitIndex).getPrice();
                            priceCode = mProductItem.getLishijialist().get(lishiUnitIndex).getPricecode();
                            break;
                        case 1:
                            int chengbenUnitIndex = mProductItem.getChengbenjialist().indexOf(new ProductItem.ChengbenjialistBean(mBilv));
                            unitPrice = mProductItem.getChengbenjialist().get(chengbenUnitIndex).getPrice();
                            priceCode = mProductItem.getChengbenjialist().get(chengbenUnitIndex).getPricecode();
                            break;
                        case 2:
                            int chuchangUnitIndex = mProductItem.getChuchangjialist().indexOf(new ProductItem.ChuchangjialistBean(mBilv));
                            unitPrice = mProductItem.getChuchangjialist().get(chuchangUnitIndex).getPrice();
                            priceCode = mProductItem.getChuchangjialist().get(chuchangUnitIndex).getPricecode();
                            break;
                        case 3:
                            int cankaoUnitIndex = mProductItem.getCankaoshoujialist().indexOf(new ProductItem.CankaoshoujialistBean(mBilv));
                            unitPrice = mProductItem.getCankaoshoujialist().get(cankaoUnitIndex).getPrice();
                            priceCode = mProductItem.getCankaoshoujialist().get(cankaoUnitIndex).getPricecode();
                            break;
                    }
                    mSivPurchaseUnit.setValue(pair.getKey() + pair.getValue());
                    mSivUnitPrice.setValue(unitPrice);
                    mSivUnitPricecode.setValue(priceCode);
                    mBilv = bilv;
                }
            });
        }
        mPurchaseUnitDialog.show(getSupportFragmentManager(), "PurchaseUnitDialog");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.siv_purchase_unit:
                showPurchaseUnitDialog();
                break;
            case R.id.siv_price_source:
                showPriceSourceDialog();
                break;
            case R.id.siv_product_color:
                showProductColorDialog();
                break;
            case R.id.siv_size:
                showSizeDialog();
                break;
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
                if (TextUtils.isEmpty(mSivUnitPrice.getValue())) { //******** 单位价是否为空 ********
                    mSivUnitPrice.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                } else if (TextUtils.isEmpty(mSivPurchaseAmount.getValue())) { //******** 采购数量是否为空 ********
                    mSivPurchaseAmount.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                } else if ("0".equals(mSivPurchaseAmount.getValue()) || TextUtils.isEmpty(mSivPurchaseAmount.getValue())) { //******** 产品数量是否小于1 ********
                    ToastUtils.show(this, "商品数量不能小于 1");
                } else if (checkStopProduce()) { //******** 是否为停产产品 ********
                    if (mStopProduceDialog == null)
                        mStopProduceDialog = new StopProduceDialog();
                    mStopProduceDialog.show(getSupportFragmentManager(), "StopProduceDialog");
                } else {
                    //******** 历史价大于0时，弹出价格变动提示框 ********
                    int index = mProductItem.getLishijialist().indexOf(new ProductItem.LishijialistBean(mProductItem.getFactor()));
                    double historyPrice = Double.parseDouble(mProductItem.getLishijialist().get(index).getPrice());
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

    //******** 检查产品是否停产 ********
    private boolean checkStopProduce() {
        return "1".equals(mProductItem.getStop_produce());
    }

    private void addProduct() {
        mProductItem.setYanse(mSivColor.getValue());
        mProductItem.setGuige(mSivSize.getValue());
        mProductItem.setFactor(mBilv);
        mProductItem.setUnitname(mPurchaseUnitList.get(mPurchaseUnitList.indexOf(new StringPair(mBilv))).getValue());
        mProductItem.setJiagelaiyuan(mSivPriceSource.getValue());
        mProductItem.setOrder_prc(mSivSinglePrice.getValue());
        mProductItem.setS_jiage2(mSivUnitPrice.getValue());
        mProductItem.setPricecode(mSivUnitPricecode.getValue());
        mProductItem.setCp_qty(mSivPurchaseAmount.getValue());
        mProductItem.setZengpin(mIsNotGift ? "0" : "1");
        mProductItem.setHuohao(mSivGoodsNo.getValue());
        mProductItem.setBz(mEtTip.getText().toString());
        Intent intent = new Intent(SupplierProductDetailsActivity.this, NewPurchaseOrderActivity.class);
        intent.putExtra(NewPurchaseOrderActivity.SELECTED_PRODUCT, mProductItem);
        startActivity(intent);
    }

    private class InnerItemChangeListener implements SearchInfoView.OnItemChangeListener {

        @Override
        public void onItemchange(boolean isLeft) {
            mIsNotGift = isLeft;
        }
    }

    private class InnerUnitPriceTextChangeListener extends BaseTextWatcher {

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0) {
                char[] chars = editable.toString().toCharArray();
                char c = chars[chars.length - 1];
                if (c > '0' && c < '9') {
                    String fomula = mLoginInfo.getString("po_jiamae_gongshi").replace("价格", editable.toString());
                    StringTokenizer tokenizer = new StringTokenizer(fomula, ",");
                    StringBuilder builder = new StringBuilder();
                    while (tokenizer.hasMoreTokens()) {
                        builder.append(tokenizer.nextToken());
                    }
                    mSivUnitPricecode.setValue(ExpressionUtils.getInstance().caculate(builder.toString()));
                }
            }
        }
    }
}
