package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginStore implements Parcelable{

    private String id;
    private String mjname;
    private boolean isSelected;

    public LoginStore() {
    }

    public LoginStore(String id) {
        this.id = id;
    }

    public LoginStore(String id, String mjname) {
        this.id = id;
        this.mjname = mjname;
    }

    public LoginStore(String id, String mjname, boolean isSelected) {
        this.id = id;
        this.mjname = mjname;
        this.isSelected = isSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return mjname;
    }

    public void setName(String mjname) {
        this.mjname = mjname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.mjname);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected LoginStore(Parcel in) {
        this.id = in.readString();
        this.mjname = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<LoginStore> CREATOR = new Creator<LoginStore>() {
        @Override
        public LoginStore createFromParcel(Parcel source) {
            return new LoginStore(source);
        }

        @Override
        public LoginStore[] newArray(int size) {
            return new LoginStore[size];
        }
    };
}
