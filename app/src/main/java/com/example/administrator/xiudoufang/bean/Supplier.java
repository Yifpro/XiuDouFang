package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/18
 */

public class Supplier implements Parcelable {

    private String id;
    private String customerNo;
    private String name;
    private String totalName;
    private String debt;
    private String phoneNum;
    private String newPhoneNum;
    private String newTelephoneNum;
    private String newContact;
    private String areaNo;
    private String areaName;
    private String fendianid = "0";

    public String getFendianid() {
        return fendianid;
    }

    public void setFendianid(String fendianid) {
        this.fendianid = fendianid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotalName() {
        return totalName;
    }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNewPhoneNum() {
        return newPhoneNum;
    }

    public void setNewPhoneNum(String newPhoneNum) {
        this.newPhoneNum = newPhoneNum;
    }

    public String getNewTelephoneNum() {
        return newTelephoneNum;
    }

    public void setNewTelephoneNum(String newTelephoneNum) {
        this.newTelephoneNum = newTelephoneNum;
    }

    public String getNewContact() {
        return newContact;
    }

    public void setNewContact(String newContact) {
        this.newContact = newContact;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Supplier() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.customerNo);
        dest.writeString(this.name);
        dest.writeString(this.totalName);
        dest.writeString(this.debt);
        dest.writeString(this.phoneNum);
        dest.writeString(this.newPhoneNum);
        dest.writeString(this.newTelephoneNum);
        dest.writeString(this.newContact);
        dest.writeString(this.areaNo);
        dest.writeString(this.areaName);
        dest.writeString(this.fendianid);
    }

    protected Supplier(Parcel in) {
        this.id = in.readString();
        this.customerNo = in.readString();
        this.name = in.readString();
        this.totalName = in.readString();
        this.debt = in.readString();
        this.phoneNum = in.readString();
        this.newPhoneNum = in.readString();
        this.newTelephoneNum = in.readString();
        this.newContact = in.readString();
        this.areaNo = in.readString();
        this.areaName = in.readString();
        this.fendianid = in.readString();
    }

    public static final Creator<Supplier> CREATOR = new Creator<Supplier>() {
        @Override
        public Supplier createFromParcel(Parcel source) {
            return new Supplier(source);
        }

        @Override
        public Supplier[] newArray(int size) {
            return new Supplier[size];
        }
    };
}
