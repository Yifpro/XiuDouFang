package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class TypeListBean {

    /**
     * status : 1
     * producttypes : [{"id":"601","code":"LB17050001","name":"达人秀带不锈餐具盒","fuji":"0"}]
     */

    private String status;
    private List<TypeBean> producttypes;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TypeBean> getProducttypes() {
        return producttypes;
    }

    public void setProducttypes(List<TypeBean> producttypes) {
        this.producttypes = producttypes;
    }

    public static class TypeBean implements Parcelable {
        /**
         * id : 601
         * code : LB17050001
         * name : 达人秀带不锈餐具盒
         * fuji : 0
         */

        private String id;
        private String code;
        private String name;
        private String fuji;
        private boolean isSelected;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFuji() {
            return fuji;
        }

        public void setFuji(String fuji) {
            this.fuji = fuji;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.code);
            dest.writeString(this.name);
            dest.writeString(this.fuji);
            dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
        }

        public TypeBean() {
        }

        protected TypeBean(Parcel in) {
            this.id = in.readString();
            this.code = in.readString();
            this.name = in.readString();
            this.fuji = in.readString();
            this.isSelected = in.readByte() != 0;
        }

        public static final Creator<TypeBean> CREATOR = new Creator<TypeBean>() {
            @Override
            public TypeBean createFromParcel(Parcel source) {
                return new TypeBean(source);
            }

            @Override
            public TypeBean[] newArray(int size) {
                return new TypeBean[size];
            }
        };
    }
}
