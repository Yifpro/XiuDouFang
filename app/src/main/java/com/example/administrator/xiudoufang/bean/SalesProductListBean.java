package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesProductListBean {

    /**
     * status : 1
     * chanpinlist : [{"cpid":"10924","styleno":"10002001","barcode":"6948154800029","stylename":"粗齿梳","classname":"秀逗粗齿钢梳","photourl":"nopic.png","xinghao":"","pinpai":"秀逗","detail":"","ty":"0","stop_sales":"0","colorlist":[{"color":"红色"},{"color":"绿色"}],"sizxlist":[{"sizx":"XL"},{"sizx":"XXl"}],"packlist":[{"unit_bilv":"1","unitname":"个/个","check":"1","dengji_price":"0.5000","cankao_price":"0.5000","lishi_price":"0"}],"dengjilist":[{"dengji":"六级","dengji_value":"6","prc":"0.00"},{"dengji":"七级","dengji_value":"7","prc":"0.00"},{"dengji":"八级","dengji_value":"8","prc":"0.00"},{"dengji":"九级","dengji_value":"9","prc":"0.00"},{"dengji":"十级（零售价）","dengji_value":"10","prc":"0.00"},{"dengji":"四级","dengji_value":"4","prc":"0.50"},{"dengji":"五级","dengji_value":"5","prc":"0.50"},{"dengji":"三级","dengji_value":"3","prc":"0.51"},{"dengji":"二级","dengji_value":"2","prc":"0.52"},{"dengji":"一级","dengji_value":"1","prc":"0.53"}],"piclist":[{"pic":"nopic.png"}],"pifajia":[],"supp_prc":"0.500","xs_prc":"0.500","pf_prc":"0.500","lishijia":"0","dianid":"45"}]
     */

    private String status;
    private List<SalesProductBean> chanpinlist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SalesProductBean> getChanpinlist() {
        return chanpinlist;
    }

    public void setChanpinlist(List<SalesProductBean> chanpinlist) {
        this.chanpinlist = chanpinlist;
    }

    public static class SalesProductBean implements Parcelable {
        /**
         * cpid : 10924
         * styleno : 10002001
         * barcode : 6948154800029
         * stylename : 粗齿梳
         * classname : 秀逗粗齿钢梳
         * photourl : nopic.png
         * xinghao :
         * pinpai : 秀逗
         * detail :
         * ty : 0
         * stop_sales : 0
         * colorlist : [{"color":"红色"},{"color":"绿色"}]
         * sizxlist : [{"sizx":"XL"},{"sizx":"XXl"}]
         * packlist : [{"unit_bilv":"1","unitname":"个/个","check":"1","dengji_price":"0.5000","cankao_price":"0.5000","lishi_price":"0"}]
         * dengjilist : [{"dengji":"六级","dengji_value":"6","prc":"0.00"},{"dengji":"七级","dengji_value":"7","prc":"0.00"},{"dengji":"八级","dengji_value":"8","prc":"0.00"},{"dengji":"九级","dengji_value":"9","prc":"0.00"},{"dengji":"十级（零售价）","dengji_value":"10","prc":"0.00"},{"dengji":"四级","dengji_value":"4","prc":"0.50"},{"dengji":"五级","dengji_value":"5","prc":"0.50"},{"dengji":"三级","dengji_value":"3","prc":"0.51"},{"dengji":"二级","dengji_value":"2","prc":"0.52"},{"dengji":"一级","dengji_value":"1","prc":"0.53"}]
         * piclist : [{"pic":"nopic.png"}]
         * pifajia : []
         * supp_prc : 0.500
         * xs_prc : 0.500
         * pf_prc : 0.500
         * lishijia : 0
         * dianid : 45
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
        private String ty;
        private String stop_sales;
        private String supp_prc;
        private String xs_prc;
        private String pf_prc;
        private String lishijia;
        private String dianid;
        private List<ColorlistBean> colorlist;
        private List<SizxlistBean> sizxlist;
        private List<PacklistBean> packlist;
        private List<DengjilistBean> dengjilist;
        private List<PiclistBean> piclist;
        private List<PifajiaBean> pifajia;
        private boolean isSelected;
        private boolean isShowSelect;
        private String pnid = "0";
        private String yanse;
        private String guige;
        private String factor;
        private String unitname;
        private String cp_qty = "1";
        private String order_prc;
        private String s_jiage2;
        private String zengpin;
        private String zhekou = "1";
        private String bz;
        private String action;
        private String jiagelaiyuan;
        private String huohao;
        private String iscx = "0";

        public String getIscx() {
            return iscx;
        }

        public void setIscx(String iscx) {
            this.iscx = iscx;
        }

        public String getYanse() {
            return yanse;
        }

        public void setYanse(String yanse) {
            this.yanse = yanse;
        }

        public String getGuige() {
            return guige;
        }

        public void setGuige(String guige) {
            this.guige = guige;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getCp_qty() {
            return cp_qty;
        }

        public void setCp_qty(String cp_qty) {
            this.cp_qty = cp_qty;
        }

        public String getOrder_prc() {
            return order_prc;
        }

        public void setOrder_prc(String order_prc) {
            this.order_prc = order_prc;
        }

        public String getS_jiage2() {
            return s_jiage2;
        }

        public void setS_jiage2(String s_jiage2) {
            this.s_jiage2 = s_jiage2;
        }

        public String getZengpin() {
            return zengpin;
        }

        public void setZengpin(String zengpin) {
            this.zengpin = zengpin;
        }

        public String getZhekou() {
            return zhekou;
        }

        public void setZhekou(String zhekou) {
            this.zhekou = zhekou;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getPnid() {
            return pnid;
        }

        public void setPnid(String pnid) {
            this.pnid = pnid;
        }

        public String getJiagelaiyuan() {
            return jiagelaiyuan;
        }

        public void setJiagelaiyuan(String jiagelaiyuan) {
            this.jiagelaiyuan = jiagelaiyuan;
        }

        public String getHuohao() {
            return huohao;
        }

        public void setHuohao(String huohao) {
            this.huohao = huohao;
        }

        public boolean isShowSelect() {
            return isShowSelect;
        }

        public void setShowSelect(boolean showSelect) {
            isShowSelect = showSelect;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

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

        public String getTy() {
            return ty;
        }

        public void setTy(String ty) {
            this.ty = ty;
        }

        public String getStop_sales() {
            return stop_sales;
        }

        public void setStop_sales(String stop_sales) {
            this.stop_sales = stop_sales;
        }

        public String getSupp_prc() {
            return supp_prc;
        }

        public void setSupp_prc(String supp_prc) {
            this.supp_prc = supp_prc;
        }

        public String getXs_prc() {
            return xs_prc;
        }

        public void setXs_prc(String xs_prc) {
            this.xs_prc = xs_prc;
        }

        public String getPf_prc() {
            return pf_prc;
        }

        public void setPf_prc(String pf_prc) {
            this.pf_prc = pf_prc;
        }

        public String getLishijia() {
            return lishijia;
        }

        public void setLishijia(String lishijia) {
            this.lishijia = lishijia;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public List<ColorlistBean> getColorlist() {
            return colorlist;
        }

        public void setColorlist(List<ColorlistBean> colorlist) {
            this.colorlist = colorlist;
        }

        public List<SizxlistBean> getSizxlist() {
            return sizxlist;
        }

        public void setSizxlist(List<SizxlistBean> sizxlist) {
            this.sizxlist = sizxlist;
        }

        public List<PacklistBean> getPacklist() {
            return packlist;
        }

        public void setPacklist(List<PacklistBean> packlist) {
            this.packlist = packlist;
        }

        public List<DengjilistBean> getDengjilist() {
            return dengjilist;
        }

        public void setDengjilist(List<DengjilistBean> dengjilist) {
            this.dengjilist = dengjilist;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public List<PifajiaBean> getPifajia() {
            return pifajia;
        }

        public void setPifajia(List<PifajiaBean> pifajia) {
            this.pifajia = pifajia;
        }

        public static class ColorlistBean implements Parcelable {
            /**
             * color : 红色
             */

            private String color;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.color);
            }

            public ColorlistBean() {
            }

            protected ColorlistBean(Parcel in) {
                this.color = in.readString();
            }

            public static final Creator<ColorlistBean> CREATOR = new Creator<ColorlistBean>() {
                @Override
                public ColorlistBean createFromParcel(Parcel source) {
                    return new ColorlistBean(source);
                }

                @Override
                public ColorlistBean[] newArray(int size) {
                    return new ColorlistBean[size];
                }
            };
        }

        public static class PifajiaBean implements Parcelable{

            private String pifajia;

            public String getPifajia() {
                return pifajia;
            }

            public void setPifajia(String pifajia) {
                this.pifajia = pifajia;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.pifajia);
            }

            public PifajiaBean() {
            }

            protected PifajiaBean(Parcel in) {
                this.pifajia = in.readString();
            }

            public static final Creator<PifajiaBean> CREATOR = new Creator<PifajiaBean>() {
                @Override
                public PifajiaBean createFromParcel(Parcel source) {
                    return new PifajiaBean(source);
                }

                @Override
                public PifajiaBean[] newArray(int size) {
                    return new PifajiaBean[size];
                }
            };
        }

        public static class SizxlistBean implements Parcelable {
            /**
             * sizx : XL
             */

            private String sizx;

            public String getSizx() {
                return sizx;
            }

            public void setSizx(String sizx) {
                this.sizx = sizx;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.sizx);
            }

            public SizxlistBean() {
            }

            protected SizxlistBean(Parcel in) {
                this.sizx = in.readString();
            }

            public static final Creator<SizxlistBean> CREATOR = new Creator<SizxlistBean>() {
                @Override
                public SizxlistBean createFromParcel(Parcel source) {
                    return new SizxlistBean(source);
                }

                @Override
                public SizxlistBean[] newArray(int size) {
                    return new SizxlistBean[size];
                }
            };
        }

        public static class PacklistBean implements Parcelable{
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * check : 1
             * dengji_price : 0.5000
             * cankao_price : 0.5000
             * lishi_price : 0
             */

            private String unit_bilv;
            private String unitname;
            private String check;
            private String dengji_price;
            private String cankao_price;
            private String lishi_price;

            public String getUnit_bilv() {
                return unit_bilv;
            }

            public void setUnit_bilv(String unit_bilv) {
                this.unit_bilv = unit_bilv;
            }

            public String getUnitname() {
                return unitname;
            }

            public void setUnitname(String unitname) {
                this.unitname = unitname;
            }

            public String getCheck() {
                return check;
            }

            public void setCheck(String check) {
                this.check = check;
            }

            public String getDengji_price() {
                return dengji_price;
            }

            public void setDengji_price(String dengji_price) {
                this.dengji_price = dengji_price;
            }

            public String getCankao_price() {
                return cankao_price;
            }

            public void setCankao_price(String cankao_price) {
                this.cankao_price = cankao_price;
            }

            public String getLishi_price() {
                return lishi_price;
            }

            public void setLishi_price(String lishi_price) {
                this.lishi_price = lishi_price;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.unit_bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.check);
                dest.writeString(this.dengji_price);
                dest.writeString(this.cankao_price);
                dest.writeString(this.lishi_price);
            }

            public PacklistBean() {
            }

            protected PacklistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.check = in.readString();
                this.dengji_price = in.readString();
                this.cankao_price = in.readString();
                this.lishi_price = in.readString();
            }

            public static final Creator<PacklistBean> CREATOR = new Creator<PacklistBean>() {
                @Override
                public PacklistBean createFromParcel(Parcel source) {
                    return new PacklistBean(source);
                }

                @Override
                public PacklistBean[] newArray(int size) {
                    return new PacklistBean[size];
                }
            };
        }

        public static class DengjilistBean implements Parcelable{
            /**
             * dengji : 六级
             * dengji_value : 6
             * prc : 0.00
             */

            private String dengji;
            private String dengji_value;
            private String prc;

            public String getDengji() {
                return dengji;
            }

            public void setDengji(String dengji) {
                this.dengji = dengji;
            }

            public String getDengji_value() {
                return dengji_value;
            }

            public void setDengji_value(String dengji_value) {
                this.dengji_value = dengji_value;
            }

            public String getPrc() {
                return prc;
            }

            public void setPrc(String prc) {
                this.prc = prc;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.dengji);
                dest.writeString(this.dengji_value);
                dest.writeString(this.prc);
            }

            public DengjilistBean() {
            }

            protected DengjilistBean(Parcel in) {
                this.dengji = in.readString();
                this.dengji_value = in.readString();
                this.prc = in.readString();
            }

            public static final Creator<DengjilistBean> CREATOR = new Creator<DengjilistBean>() {
                @Override
                public DengjilistBean createFromParcel(Parcel source) {
                    return new DengjilistBean(source);
                }

                @Override
                public DengjilistBean[] newArray(int size) {
                    return new DengjilistBean[size];
                }
            };
        }

        public static class PiclistBean implements Parcelable{
            /**
             * pic : nopic.png
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
            dest.writeString(this.ty);
            dest.writeString(this.stop_sales);
            dest.writeString(this.supp_prc);
            dest.writeString(this.xs_prc);
            dest.writeString(this.pf_prc);
            dest.writeString(this.lishijia);
            dest.writeString(this.dianid);
            dest.writeTypedList(this.colorlist);
            dest.writeTypedList(this.sizxlist);
            dest.writeTypedList(this.packlist);
            dest.writeTypedList(this.dengjilist);
            dest.writeTypedList(this.piclist);
            dest.writeTypedList(this.pifajia);
            dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isShowSelect ? (byte) 1 : (byte) 0);
            dest.writeString(this.pnid);
            dest.writeString(this.yanse);
            dest.writeString(this.guige);
            dest.writeString(this.factor);
            dest.writeString(this.unitname);
            dest.writeString(this.cp_qty);
            dest.writeString(this.order_prc);
            dest.writeString(this.s_jiage2);
            dest.writeString(this.zengpin);
            dest.writeString(this.zhekou);
            dest.writeString(this.bz);
            dest.writeString(this.action);
            dest.writeString(this.jiagelaiyuan);
            dest.writeString(this.huohao);
            dest.writeString(this.iscx);
        }

        public SalesProductBean() {
        }

        protected SalesProductBean(Parcel in) {
            this.cpid = in.readString();
            this.styleno = in.readString();
            this.barcode = in.readString();
            this.stylename = in.readString();
            this.classname = in.readString();
            this.photourl = in.readString();
            this.xinghao = in.readString();
            this.pinpai = in.readString();
            this.detail = in.readString();
            this.ty = in.readString();
            this.stop_sales = in.readString();
            this.supp_prc = in.readString();
            this.xs_prc = in.readString();
            this.pf_prc = in.readString();
            this.lishijia = in.readString();
            this.dianid = in.readString();
            this.colorlist = in.createTypedArrayList(ColorlistBean.CREATOR);
            this.sizxlist = in.createTypedArrayList(SizxlistBean.CREATOR);
            this.packlist = in.createTypedArrayList(PacklistBean.CREATOR);
            this.dengjilist = in.createTypedArrayList(DengjilistBean.CREATOR);
            this.piclist = in.createTypedArrayList(PiclistBean.CREATOR);
            this.pifajia = in.createTypedArrayList(PifajiaBean.CREATOR);
            this.isSelected = in.readByte() != 0;
            this.isShowSelect = in.readByte() != 0;
            this.pnid = in.readString();
            this.yanse = in.readString();
            this.guige = in.readString();
            this.factor = in.readString();
            this.unitname = in.readString();
            this.cp_qty = in.readString();
            this.order_prc = in.readString();
            this.s_jiage2 = in.readString();
            this.zengpin = in.readString();
            this.zhekou = in.readString();
            this.bz = in.readString();
            this.action = in.readString();
            this.jiagelaiyuan = in.readString();
            this.huohao = in.readString();
            this.iscx = in.readString();
        }

        public static final Creator<SalesProductBean> CREATOR = new Creator<SalesProductBean>() {
            @Override
            public SalesProductBean createFromParcel(Parcel source) {
                return new SalesProductBean(source);
            }

            @Override
            public SalesProductBean[] newArray(int size) {
                return new SalesProductBean[size];
            }
        };
    }
}