package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/9/4
 */

public class OrderInfo implements Parcelable {

    private String orderSums;
    private String qianjieqian;
    private String leijiqian;
    private String otherFree;
    private String yingshou;
    private String leijijine;
    private String bencishoukuan;
    private String discount;
    private String balance;
    private String balancePayment;

    public String getOrderSums() {
        return orderSums;
    }

    public void setOrderSums(String orderSums) {
        this.orderSums = orderSums;
    }

    public String getQianjieqian() {
        return qianjieqian;
    }

    public void setQianjieqian(String qianjieqian) {
        this.qianjieqian = qianjieqian;
    }

    public String getLeijiqian() {
        return leijiqian;
    }

    public void setLeijiqian(String leijiqian) {
        this.leijiqian = leijiqian;
    }

    public String getOtherFree() {
        return otherFree;
    }

    public void setOtherFree(String otherFree) {
        this.otherFree = otherFree;
    }

    public String getYingshou() {
        return yingshou;
    }

    public void setYingshou(String yingshou) {
        this.yingshou = yingshou;
    }

    public String getLeijijine() {
        return leijijine;
    }

    public void setLeijijine(String leijijine) {
        this.leijijine = leijijine;
    }

    public String getBencishoukuan() {
        return bencishoukuan;
    }

    public void setBencishoukuan(String bencishoukuan) {
        this.bencishoukuan = bencishoukuan;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalancePayment() {
        return balancePayment;
    }

    public void setBalancePayment(String balancePayment) {
        this.balancePayment = balancePayment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.orderSums);
        dest.writeString(this.qianjieqian);
        dest.writeString(this.leijiqian);
        dest.writeString(this.otherFree);
        dest.writeString(this.yingshou);
        dest.writeString(this.leijijine);
        dest.writeString(this.bencishoukuan);
        dest.writeString(this.discount);
        dest.writeString(this.balance);
        dest.writeString(this.balancePayment);
    }

    public OrderInfo() {
    }

    protected OrderInfo(Parcel in) {
        this.orderSums = in.readString();
        this.qianjieqian = in.readString();
        this.leijiqian = in.readString();
        this.otherFree = in.readString();
        this.yingshou = in.readString();
        this.leijijine = in.readString();
        this.bencishoukuan = in.readString();
        this.discount = in.readString();
        this.balance = in.readString();
        this.balancePayment = in.readString();
    }

    public static final Creator<OrderInfo> CREATOR = new Creator<OrderInfo>() {
        @Override
        public OrderInfo createFromParcel(Parcel source) {
            return new OrderInfo(source);
        }

        @Override
        public OrderInfo[] newArray(int size) {
            return new OrderInfo[size];
        }
    };
}
