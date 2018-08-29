package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductListBean {


    /**
     * status : 1
     * chanpinpic : [{"cpid":"36481","styleno":"4933300103","barcode":"6956428021063","stylename":"健康麦秆大号单层杯粉","classname":"达人秀麦秸秆单层杯","photourl":"59122c74-47d1-435d-bf8a-59491ea670a0.png","xinghao":"","pinpai":"达人秀","detail":"原9024款式","piclist":[{"pic":"59122c74-47d1-435d-bf8a-59491ea670a0.png"},{"pic":"f9f744da-ff18-4c34-9cad-ac1ad3893777.png"},{"pic":"116d25ff-b93a-4438-9401-c0648bf62242.png"}],"dianid":"45","fx_unitlists":[{"id":"142851","bilv":"1","unitname":"个","maozhong":"88.10g","jingzhong":"0.00g","waixiang":"8*8*17","ischeck":"1","belongunit":"单位1"},{"id":"142852","bilv":"24","unitname":"盒","maozhong":"0.00kg","jingzhong":"0.00kg","waixiang":"","ischeck":"0","belongunit":"单位3"},{"id":"142853","bilv":"288","unitname":"件","maozhong":"35.00kg","jingzhong":"33.10kg","waixiang":"92*47*76","ischeck":"0","belongunit":"单位4"}]}]
     */

    private String status;
    private List<ChanpinpicBean> chanpinpic;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ChanpinpicBean> getChanpinpic() {
        return chanpinpic;
    }

    public void setChanpinpic(List<ChanpinpicBean> chanpinpic) {
        this.chanpinpic = chanpinpic;
    }

    public static class ChanpinpicBean implements Parcelable {
        /**
         * cpid : 36481
         * styleno : 4933300103
         * barcode : 6956428021063
         * stylename : 健康麦秆大号单层杯粉
         * classname : 达人秀麦秸秆单层杯
         * photourl : 59122c74-47d1-435d-bf8a-59491ea670a0.png
         * xinghao :
         * pinpai : 达人秀
         * detail : 原9024款式
         * piclist : [{"pic":"59122c74-47d1-435d-bf8a-59491ea670a0.png"},{"pic":"f9f744da-ff18-4c34-9cad-ac1ad3893777.png"},{"pic":"116d25ff-b93a-4438-9401-c0648bf62242.png"}]
         * dianid : 45
         * fx_unitlists : [{"id":"142851","bilv":"1","unitname":"个","maozhong":"88.10g","jingzhong":"0.00g","waixiang":"8*8*17","ischeck":"1","belongunit":"单位1"},{"id":"142852","bilv":"24","unitname":"盒","maozhong":"0.00kg","jingzhong":"0.00kg","waixiang":"","ischeck":"0","belongunit":"单位3"},{"id":"142853","bilv":"288","unitname":"件","maozhong":"35.00kg","jingzhong":"33.10kg","waixiang":"92*47*76","ischeck":"0","belongunit":"单位4"}]
         */

        private String cpid;
        private String styleno;
        private String barcode;
        private String stylename;
        private String classname;
        private String photourl;
        private String xinghao;
        private String pinpai;
        private String detail;
        private String dianid;
        private List<PiclistBean> piclist;
        private List<FxUnitlistsBean> fx_unitlists;

        public String getCpid() {
            return cpid;
        }

        public void setCpid(String cpid) {
            this.cpid = cpid;
        }

        public String getStyleno() {
            return styleno;
        }

        public void setStyleno(String styleno) {
            this.styleno = styleno;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }

        public String getStylename() {
            return stylename;
        }

        public void setStylename(String stylename) {
            this.stylename = stylename;
        }

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getXinghao() {
            return xinghao;
        }

        public void setXinghao(String xinghao) {
            this.xinghao = xinghao;
        }

        public String getPinpai() {
            return pinpai;
        }

        public void setPinpai(String pinpai) {
            this.pinpai = pinpai;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public List<FxUnitlistsBean> getFx_unitlists() {
            return fx_unitlists;
        }

        public void setFx_unitlists(List<FxUnitlistsBean> fx_unitlists) {
            this.fx_unitlists = fx_unitlists;
        }

        public static class PiclistBean implements Parcelable {
            /**
             * pic : 59122c74-47d1-435d-bf8a-59491ea670a0.png
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.pic);
            }

            public PiclistBean() {
            }

            protected PiclistBean(Parcel in) {
                this.pic = in.readString();
            }

            public static final Creator<PiclistBean> CREATOR = new Creator<PiclistBean>() {
                @Override
                public PiclistBean createFromParcel(Parcel source) {
                    return new PiclistBean(source);
                }

                @Override
                public PiclistBean[] newArray(int size) {
                    return new PiclistBean[size];
                }
            };
        }

        public static class FxUnitlistsBean implements Parcelable {
            /**
             * id : 142851
             * bilv : 1
             * unitname : 个
             * maozhong : 88.10g
             * jingzhong : 0.00g
             * waixiang : 8*8*17
             * ischeck : 1
             * belongunit : 单位1
             */

            private String id;
            private String bilv;
            private String unitname;
            private String maozhong;
            private String jingzhong;
            private String waixiang;
            private String ischeck;
            private String belongunit;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBilv() {
                return bilv;
            }

            public void setBilv(String bilv) {
                this.bilv = bilv;
            }

            public String getUnitname() {
                return unitname;
            }

            public void setUnitname(String unitname) {
                this.unitname = unitname;
            }

            public String getMaozhong() {
                return maozhong;
            }

            public void setMaozhong(String maozhong) {
                this.maozhong = maozhong;
            }

            public String getJingzhong() {
                return jingzhong;
            }

            public void setJingzhong(String jingzhong) {
                this.jingzhong = jingzhong;
            }

            public String getWaixiang() {
                return waixiang;
            }

            public void setWaixiang(String waixiang) {
                this.waixiang = waixiang;
            }

            public String getIscheck() {
                return ischeck;
            }

            public void setIscheck(String ischeck) {
                this.ischeck = ischeck;
            }

            public String getBelongunit() {
                return belongunit;
            }

            public void setBelongunit(String belongunit) {
                this.belongunit = belongunit;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.maozhong);
                dest.writeString(this.jingzhong);
                dest.writeString(this.waixiang);
                dest.writeString(this.ischeck);
                dest.writeString(this.belongunit);
            }

            public FxUnitlistsBean() {
            }

            protected FxUnitlistsBean(Parcel in) {
                this.id = in.readString();
                this.bilv = in.readString();
                this.unitname = in.readString();
                this.maozhong = in.readString();
                this.jingzhong = in.readString();
                this.waixiang = in.readString();
                this.ischeck = in.readString();
                this.belongunit = in.readString();
            }

            public static final Creator<FxUnitlistsBean> CREATOR = new Creator<FxUnitlistsBean>() {
                @Override
                public FxUnitlistsBean createFromParcel(Parcel source) {
                    return new FxUnitlistsBean(source);
                }

                @Override
                public FxUnitlistsBean[] newArray(int size) {
                    return new FxUnitlistsBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.cpid);
            dest.writeString(this.styleno);
            dest.writeString(this.barcode);
            dest.writeString(this.stylename);
            dest.writeString(this.classname);
            dest.writeString(this.photourl);
            dest.writeString(this.xinghao);
            dest.writeString(this.pinpai);
            dest.writeString(this.detail);
            dest.writeString(this.dianid);
            dest.writeTypedList(this.piclist);
            dest.writeList(this.fx_unitlists);
        }

        public ChanpinpicBean() {
        }

        protected ChanpinpicBean(Parcel in) {
            this.cpid = in.readString();
            this.styleno = in.readString();
            this.barcode = in.readString();
            this.stylename = in.readString();
            this.classname = in.readString();
            this.photourl = in.readString();
            this.xinghao = in.readString();
            this.pinpai = in.readString();
            this.detail = in.readString();
            this.dianid = in.readString();
            this.piclist = in.createTypedArrayList(PiclistBean.CREATOR);
            this.fx_unitlists = new ArrayList<FxUnitlistsBean>();
            in.readList(this.fx_unitlists, FxUnitlistsBean.class.getClassLoader());
        }

        public static final Creator<ChanpinpicBean> CREATOR = new Creator<ChanpinpicBean>() {
            @Override
            public ChanpinpicBean createFromParcel(Parcel source) {
                return new ChanpinpicBean(source);
            }

            @Override
            public ChanpinpicBean[] newArray(int size) {
                return new ChanpinpicBean[size];
            }
        };
    }
}
