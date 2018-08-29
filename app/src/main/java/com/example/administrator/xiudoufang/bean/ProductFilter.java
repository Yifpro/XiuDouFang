package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductFilter implements Parcelable {

    private String name;
    private String supplierId;
    private String typeId;
    private String isIncludePic;
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getIsIncludePic() {
        return isIncludePic;
    }

    public void setIsIncludePic(String isIncludePic) {
        this.isIncludePic = isIncludePic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.supplierId);
        dest.writeString(this.typeId);
        dest.writeString(this.isIncludePic);
        dest.writeString(this.action);
    }

    public ProductFilter() {
    }

    protected ProductFilter(Parcel in) {
        this.name = in.readString();
        this.supplierId = in.readString();
        this.typeId = in.readString();
        this.isIncludePic = in.readString();
        this.action = in.readString();
    }

    public static final Creator<ProductFilter> CREATOR = new Creator<ProductFilter>() {
        @Override
        public ProductFilter createFromParcel(Parcel source) {
            return new ProductFilter(source);
        }

        @Override
        public ProductFilter[] newArray(int size) {
            return new ProductFilter[size];
        }
    };
}
