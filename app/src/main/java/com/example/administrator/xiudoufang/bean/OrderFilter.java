package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderFilter implements Parcelable {
    private String no;
    private String customer;
    private String startTime;
    private String endTime;
    private String orderType;
    private String transportType;
    private String proxyOrder;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getProxyOrder() {
        return proxyOrder;
    }

    public void setProxyOrder(String proxyOrder) {
        this.proxyOrder = proxyOrder;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no);
        dest.writeString(this.customer);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.orderType);
        dest.writeString(this.transportType);
        dest.writeString(this.proxyOrder);
    }

    public OrderFilter() {
    }

    protected OrderFilter(Parcel in) {
        this.no = in.readString();
        this.customer = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.orderType = in.readString();
        this.transportType = in.readString();
        this.proxyOrder = in.readString();
    }

    public static final Creator<OrderFilter> CREATOR = new Creator<OrderFilter>() {
        @Override
        public OrderFilter createFromParcel(Parcel source) {
            return new OrderFilter(source);
        }

        @Override
        public OrderFilter[] newArray(int size) {
            return new OrderFilter[size];
        }
    };
}
