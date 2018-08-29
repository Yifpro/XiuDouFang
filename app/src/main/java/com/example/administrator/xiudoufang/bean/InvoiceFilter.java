package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/29
 */

public class InvoiceFilter implements Parcelable {

    private String no;
    private String customer;
    private String startTime;
    private String endTime;
    private String transportNum;

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

    public String getTransportNum() {
        return transportNum;
    }

    public void setTransportNum(String transportNum) {
        this.transportNum = transportNum;
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
        dest.writeString(this.transportNum);
    }

    public InvoiceFilter() {
    }

    protected InvoiceFilter(Parcel in) {
        this.no = in.readString();
        this.customer = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.transportNum = in.readString();
    }

    public static final Creator<InvoiceFilter> CREATOR = new Creator<InvoiceFilter>() {
        @Override
        public InvoiceFilter createFromParcel(Parcel source) {
            return new InvoiceFilter(source);
        }

        @Override
        public InvoiceFilter[] newArray(int size) {
            return new InvoiceFilter[size];
        }
    };
}
