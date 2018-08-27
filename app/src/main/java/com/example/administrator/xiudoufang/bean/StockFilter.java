package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockFilter implements Parcelable {

    private String no;
    private String name;
    private String type;
    private String supplier;
    private String model;
    private String barCode;
    private String brand;
    private String details;
    private String unit;
    private String amount;
    private String isIncludeSubclass;

    public String getIsIncludeSubclass() {
        return isIncludeSubclass;
    }

    public void setIsIncludeSubclass(String isIncludeSubclass) {
        this.isIncludeSubclass = isIncludeSubclass;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.supplier);
        dest.writeString(this.model);
        dest.writeString(this.barCode);
        dest.writeString(this.brand);
        dest.writeString(this.details);
        dest.writeString(this.unit);
        dest.writeString(this.amount);
        dest.writeString(this.isIncludeSubclass);
    }

    public StockFilter() {
    }

    protected StockFilter(Parcel in) {
        this.no = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.supplier = in.readString();
        this.model = in.readString();
        this.barCode = in.readString();
        this.brand = in.readString();
        this.details = in.readString();
        this.unit = in.readString();
        this.amount = in.readString();
        this.isIncludeSubclass = in.readString();
    }

    public static final Creator<StockFilter> CREATOR = new Creator<StockFilter>() {
        @Override
        public StockFilter createFromParcel(Parcel source) {
            return new StockFilter(source);
        }

        @Override
        public StockFilter[] newArray(int size) {
            return new StockFilter[size];
        }
    };
}
