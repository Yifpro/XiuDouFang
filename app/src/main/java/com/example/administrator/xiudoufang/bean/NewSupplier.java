package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/17
 */

public class NewSupplier implements Parcelable {
    private String name;
    private String totalName;
    private String phoneNum;
    private String telephoneNum;
    private String contact;
    private String areaNo;
    private String areaName;

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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.totalName);
        dest.writeString(this.phoneNum);
        dest.writeString(this.telephoneNum);
        dest.writeString(this.contact);
        dest.writeString(this.areaNo);
        dest.writeString(this.areaName);
    }

    public NewSupplier() {
    }

    protected NewSupplier(Parcel in) {
        this.name = in.readString();
        this.totalName = in.readString();
        this.phoneNum = in.readString();
        this.telephoneNum = in.readString();
        this.contact = in.readString();
        this.areaNo = in.readString();
        this.areaName = in.readString();
    }

    public static final Creator<NewSupplier> CREATOR = new Creator<NewSupplier>() {
        @Override
        public NewSupplier createFromParcel(Parcel source) {
            return new NewSupplier(source);
        }

        @Override
        public NewSupplier[] newArray(int size) {
            return new NewSupplier[size];
        }
    };
}
