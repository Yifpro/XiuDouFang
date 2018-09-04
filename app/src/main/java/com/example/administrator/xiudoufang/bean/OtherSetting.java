package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSetting implements Parcelable {

    private String fahuodianid; //发货店
    private String orderType; //订单类型
    private String huoyunType; //货运类型
    private String logistics; //物流
    private String specialOrder; //特殊订单
    private String additionalInstructions; //附加说明
    private String forcastDate; //预计交货
    private String extra; //附件
    private String customerContract; //客户合同

    public String getForcastDate() {
        return forcastDate;
    }

    public void setForcastDate(String forcastDate) {
        this.forcastDate = forcastDate;
    }

    public String getFahuodianid() {
        return fahuodianid;
    }

    public void setFahuodianid(String fahuodianid) {
        this.fahuodianid = fahuodianid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getHuoyunType() {
        return huoyunType;
    }

    public void setHuoyunType(String huoyunType) {
        this.huoyunType = huoyunType;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getSpecialOrder() {
        return specialOrder;
    }

    public void setSpecialOrder(String specialOrder) {
        this.specialOrder = specialOrder;
    }

    public String getAdditionalInstructions() {
        return additionalInstructions;
    }

    public void setAdditionalInstructions(String additionalInstructions) {
        this.additionalInstructions = additionalInstructions;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getCustomerContract() {
        return customerContract;
    }

    public void setCustomerContract(String customerContract) {
        this.customerContract = customerContract;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fahuodianid);
        dest.writeString(this.orderType);
        dest.writeString(this.huoyunType);
        dest.writeString(this.logistics);
        dest.writeString(this.specialOrder);
        dest.writeString(this.additionalInstructions);
        dest.writeString(this.extra);
        dest.writeString(this.customerContract);
    }

    public OtherSetting() {
    }

    protected OtherSetting(Parcel in) {
        this.fahuodianid = in.readString();
        this.orderType = in.readString();
        this.huoyunType = in.readString();
        this.logistics = in.readString();
        this.specialOrder = in.readString();
        this.additionalInstructions = in.readString();
        this.extra = in.readString();
        this.customerContract = in.readString();
    }

    public static final Creator<OtherSetting> CREATOR = new Creator<OtherSetting>() {
        @Override
        public OtherSetting createFromParcel(Parcel source) {
            return new OtherSetting(source);
        }

        @Override
        public OtherSetting[] newArray(int size) {
            return new OtherSetting[size];
        }
    };
}
