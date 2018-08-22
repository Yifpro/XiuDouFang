package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/21
 */

public class ProductItem implements Parcelable {

    private String id;
    private String productNo;
    private String color;
    private String size;
    private String factor;
    private String unit;
    private String amount;
    private String singlePrice;
    private String unitPrice;
    private boolean isGift;
    private String tip;
    private String goodsNo;
    private String priceCode;
    private String priceSource;
    private String photourl;
    private String stylename;

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getStylename() {
        return stylename;
    }

    public void setStylename(String stylename) {
        this.stylename = stylename;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    public String getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.productNo);
        dest.writeString(this.color);
        dest.writeString(this.size);
        dest.writeString(this.factor);
        dest.writeString(this.unit);
        dest.writeString(this.amount);
        dest.writeString(this.singlePrice);
        dest.writeString(this.unitPrice);
        dest.writeByte(this.isGift ? (byte) 1 : (byte) 0);
        dest.writeString(this.tip);
        dest.writeString(this.goodsNo);
        dest.writeString(this.priceCode);
        dest.writeString(this.priceSource);
        dest.writeString(this.photourl);
        dest.writeString(this.stylename);
    }

    public ProductItem() {
    }

    protected ProductItem(Parcel in) {
        this.id = in.readString();
        this.productNo = in.readString();
        this.color = in.readString();
        this.size = in.readString();
        this.factor = in.readString();
        this.unit = in.readString();
        this.amount = in.readString();
        this.singlePrice = in.readString();
        this.unitPrice = in.readString();
        this.isGift = in.readByte() != 0;
        this.tip = in.readString();
        this.goodsNo = in.readString();
        this.priceCode = in.readString();
        this.priceSource = in.readString();
        this.photourl = in.readString();
        this.stylename = in.readString();
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel source) {
            return new ProductItem(source);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };
}
