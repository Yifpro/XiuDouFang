package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/18
 */

public class Supplier implements Parcelable {

    private String c_id = "0"; //******** 供应商id ********
    private String customerno = ""; //******** 供应商编号 ********
    private String customername = ""; //******** 供应商名称 ********
    private String quancheng; //******** 全称 ********
    private String debt; //******** 前结欠 ********
    private String lianxiren = ""; //******** 联系人 ********
    private String telephone = ""; //******** 手机 ********
    private String dianhua = ""; //******** 电话 ********
    private String quyuno = ""; //******** 区域编号 ********
    private String quyu = ""; //******** 区域名称 ********
    private String fendianid = "0"; //******** 分店id ********

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCustomerno() {
        return customerno;
    }

    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getQuancheng() {
        return quancheng;
    }

    public void setQuancheng(String quancheng) {
        this.quancheng = quancheng;
    }

    public String getDebt() {
        return debt;
    }

    public void setDebt(String debt) {
        this.debt = debt;
    }

    public String getLianxiren() {
        return lianxiren;
    }

    public void setLianxiren(String lianxiren) {
        this.lianxiren = lianxiren;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDianhua() {
        return dianhua;
    }

    public void setDianhua(String dianhua) {
        this.dianhua = dianhua;
    }

    public String getQuyuno() {
        return quyuno;
    }

    public void setQuyuno(String quyuno) {
        this.quyuno = quyuno;
    }

    public String getQuyu() {
        return quyu;
    }

    public void setQuyu(String quyu) {
        this.quyu = quyu;
    }

    public String getFendianid() {
        return fendianid;
    }

    public void setFendianid(String fendianid) {
        this.fendianid = fendianid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.c_id);
        dest.writeString(this.customerno);
        dest.writeString(this.customername);
        dest.writeString(this.quancheng);
        dest.writeString(this.debt);
        dest.writeString(this.lianxiren);
        dest.writeString(this.telephone);
        dest.writeString(this.dianhua);
        dest.writeString(this.quyuno);
        dest.writeString(this.quyu);
        dest.writeString(this.fendianid);
    }

    public Supplier() {
    }

    protected Supplier(Parcel in) {
        this.c_id = in.readString();
        this.customerno = in.readString();
        this.customername = in.readString();
        this.quancheng = in.readString();
        this.debt = in.readString();
        this.lianxiren = in.readString();
        this.telephone = in.readString();
        this.dianhua = in.readString();
        this.quyuno = in.readString();
        this.quyu = in.readString();
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
