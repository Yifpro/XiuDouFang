package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderQueryBean implements Parcelable {


    /**
     * id : 0
     * name : 无
     * moren : 1
     */

    private String id;
    private String name;
    private String moren;

    public OrderQueryBean(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoren() {
        return moren;
    }

    public void setMoren(String moren) {
        this.moren = moren;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.moren);
    }

    public OrderQueryBean() {
    }

    protected OrderQueryBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.moren = in.readString();
    }

    public static final Creator<OrderQueryBean> CREATOR = new Creator<OrderQueryBean>() {
        @Override
        public OrderQueryBean createFromParcel(Parcel source) {
            return new OrderQueryBean(source);
        }

        @Override
        public OrderQueryBean[] newArray(int size) {
            return new OrderQueryBean[size];
        }
    };
}
