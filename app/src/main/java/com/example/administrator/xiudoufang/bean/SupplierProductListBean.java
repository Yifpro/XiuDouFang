package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class SupplierProductListBean {


    /**
     * status : 1
     * message : null
     * po_chanpinlist : [{"cpid":"10924","styleno":"10002001","barcode":"6948154800029","stylename":"粗齿梳","classname":"秀逗粗齿钢梳","photourl":"nopic.png","xinghao":"","pinpai":"秀逗","detail":"","kucunqty":"0","ziyouqty":"0","stop_produce":"0","stop_sales":"0","pounitname":"个","colorlist":[],"packlist":[{"unit_bilv":"1","unitname":"个/个","check":"1"}],"sizxlist":[],"piclist":[{"pic":"nopic.png"}],"lishijialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}],"chengbenjialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}],"cankaoshoujialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.50","pricecode":"617780"}],"chuchangjialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.52","pricecode":"617800"}],"dianid":"45"}]
     */

    private String status;
    private Object message;
    private List<SupplierProductBean> po_chanpinlist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<SupplierProductBean> getPo_chanpinlist() {
        return po_chanpinlist;
    }

    public void setPo_chanpinlist(List<SupplierProductBean> po_chanpinlist) {
        this.po_chanpinlist = po_chanpinlist;
    }

    public static class SupplierProductBean implements Parcelable {

        /**
         * cpid : 10888
         * styleno : 10008001
         * barcode : 6948154800081
         * stylename : 粗齿梳
         * classname : 秀逗粗齿钢梳
         * photourl : 9ee2705f-7ba9-45dd-b4af-4242690a22db.jpg
         * xinghao :
         * pinpai : 秀逗
         * detail :
         * kucunqty : 10001
         * ziyouqty : 10001
         * stop_produce : 0
         * stop_sales : 0
         * pounitname : 个
         * colorlist : [{"color":"红色"},{"color":"绿色"}]
         * packlist : [{"unit_bilv":"24","unitname":"个/包","check":"0"},{"unit_bilv":"1","unitname":"个/个","check":"1"}]
         * sizxlist : [{"sizx":"XL"},{"sizx":"XXl"}]
         * piclist : [{"pic":"9ee2705f-7ba9-45dd-b4af-4242690a22db.jpg"},{"pic":"5ddd8296-da67-492e-b7c2-a36a162f3a72.jpg"},{"pic":"ac93f543-fbf1-4ad6-a0ff-13c257cbc92b.jpg"},{"pic":"60e5f327-d938-4d4a-a42c-2359de6ce96c.JPG"}]
         * lishijialist : [{"unit_bilv":"24","unitname":"个/包","price":"0.00","pricecode":"617280"},{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}]
         * chengbenjialist : [{"unit_bilv":"24","unitname":"个/包","price":"16.39","pricecode":"633670"},{"unit_bilv":"1","unitname":"个/个","price":"0.68","pricecode":"617960"}]
         * cankaoshoujialist : [{"unit_bilv":"24","unitname":"个/包","price":"15.36","pricecode":"632640"},{"unit_bilv":"1","unitname":"个/个","price":"0.64","pricecode":"617920"}]
         * chuchangjialist : [{"unit_bilv":"24","unitname":"个/包","price":"21.12","pricecode":"638400"},{"unit_bilv":"1","unitname":"个/个","price":"0.88","pricecode":"618160"}]
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
        private String kucunqty;
        private String ziyouqty;
        private String stop_produce;
        private String stop_sales;
        private String pounitname;
        private String dianid;
        private List<ColorlistBean> colorlist;
        private List<PacklistBean> packlist;
        private List<SizxlistBean> sizxlist;
        private List<PiclistBean> piclist;
        private List<LishijialistBean> lishijialist;
        private List<ChengbenjialistBean> chengbenjialist;
        private List<CankaoshoujialistBean> cankaoshoujialist;
        private List<ChuchangjialistBean> chuchangjialist;
        private boolean isShowSelect;
        private boolean isSelected;
        private String unitname;
        private String iid;
        private String pnid;
        private String puOrderNo;
        private String yanse;
        private String guige;
        private String order_prc;
        private String factor;
        private String s_jiage2;
        private String pricecode;
        private String orderqty;
        private String cp_qty;
        private String orderamt;
        private String rcvqty;
        private String rcvamt;
        private String statusstr;
        private String bz;
        private String zhuancaigou_pnid;
        private String fujian;
        private String huohao;
        private String zengpin;
        private String jiagelaiyuan;
        private String buttonstatus_str;

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public String getPnid() {
            return pnid;
        }

        public void setPnid(String pnid) {
            this.pnid = pnid;
        }

        public String getPuOrderNo() {
            return puOrderNo;
        }

        public void setPuOrderNo(String puOrderNo) {
            this.puOrderNo = puOrderNo;
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

        public String getOrder_prc() {
            return order_prc;
        }

        public void setOrder_prc(String order_prc) {
            this.order_prc = order_prc;
        }

        public String getFactor() {
            return factor;
        }

        public void setFactor(String factor) {
            this.factor = factor;
        }

        public String getS_jiage2() {
            return s_jiage2;
        }

        public void setS_jiage2(String s_jiage2) {
            this.s_jiage2 = s_jiage2;
        }

        public String getPricecode() {
            return pricecode;
        }

        public void setPricecode(String pricecode) {
            this.pricecode = pricecode;
        }

        public String getOrderqty() {
            return orderqty;
        }

        public void setOrderqty(String orderqty) {
            this.orderqty = orderqty;
        }

        public String getCp_qty() {
            return cp_qty;
        }

        public void setCp_qty(String cp_qty) {
            this.cp_qty = cp_qty;
        }

        public String getOrderamt() {
            return orderamt;
        }

        public void setOrderamt(String orderamt) {
            this.orderamt = orderamt;
        }

        public String getRcvqty() {
            return rcvqty;
        }

        public void setRcvqty(String rcvqty) {
            this.rcvqty = rcvqty;
        }

        public String getRcvamt() {
            return rcvamt;
        }

        public void setRcvamt(String rcvamt) {
            this.rcvamt = rcvamt;
        }

        public String getStatusstr() {
            return statusstr;
        }

        public void setStatusstr(String statusstr) {
            this.statusstr = statusstr;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getZhuancaigou_pnid() {
            return zhuancaigou_pnid;
        }

        public void setZhuancaigou_pnid(String zhuancaigou_pnid) {
            this.zhuancaigou_pnid = zhuancaigou_pnid;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }

        public String getHuohao() {
            return huohao;
        }

        public void setHuohao(String huohao) {
            this.huohao = huohao;
        }

        public String getZengpin() {
            return zengpin;
        }

        public void setZengpin(String zengpin) {
            this.zengpin = zengpin;
        }

        public String getJiagelaiyuan() {
            return jiagelaiyuan;
        }

        public void setJiagelaiyuan(String jiagelaiyuan) {
            this.jiagelaiyuan = jiagelaiyuan;
        }

        public String getButtonstatus_str() {
            return buttonstatus_str;
        }

        public void setButtonstatus_str(String buttonstatus_str) {
            this.buttonstatus_str = buttonstatus_str;
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

        public String getKucunqty() {
            return kucunqty;
        }

        public void setKucunqty(String kucunqty) {
            this.kucunqty = kucunqty;
        }

        public String getZiyouqty() {
            return ziyouqty;
        }

        public void setZiyouqty(String ziyouqty) {
            this.ziyouqty = ziyouqty;
        }

        public String getStop_produce() {
            return stop_produce;
        }

        public void setStop_produce(String stop_produce) {
            this.stop_produce = stop_produce;
        }

        public String getStop_sales() {
            return stop_sales;
        }

        public void setStop_sales(String stop_sales) {
            this.stop_sales = stop_sales;
        }

        public String getPounitname() {
            return pounitname;
        }

        public void setPounitname(String pounitname) {
            this.pounitname = pounitname;
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

        public List<PacklistBean> getPacklist() {
            return packlist;
        }

        public void setPacklist(List<PacklistBean> packlist) {
            this.packlist = packlist;
        }

        public List<SizxlistBean> getSizxlist() {
            return sizxlist;
        }

        public void setSizxlist(List<SizxlistBean> sizxlist) {
            this.sizxlist = sizxlist;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public List<LishijialistBean> getLishijialist() {
            return lishijialist;
        }

        public void setLishijialist(List<LishijialistBean> lishijialist) {
            this.lishijialist = lishijialist;
        }

        public List<ChengbenjialistBean> getChengbenjialist() {
            return chengbenjialist;
        }

        public void setChengbenjialist(List<ChengbenjialistBean> chengbenjialist) {
            this.chengbenjialist = chengbenjialist;
        }

        public List<CankaoshoujialistBean> getCankaoshoujialist() {
            return cankaoshoujialist;
        }

        public void setCankaoshoujialist(List<CankaoshoujialistBean> cankaoshoujialist) {
            this.cankaoshoujialist = cankaoshoujialist;
        }

        public List<ChuchangjialistBean> getChuchangjialist() {
            return chuchangjialist;
        }

        public void setChuchangjialist(List<ChuchangjialistBean> chuchangjialist) {
            this.chuchangjialist = chuchangjialist;
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

        public static class PacklistBean implements Parcelable {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * check : 0
             */

            private String unit_bilv;
            private String unitname;
            private String check;

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

            public PacklistBean() {
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
            }

            protected PacklistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.check = in.readString();
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

        public static class PiclistBean implements Parcelable {
            /**
             * pic : 9ee2705f-7ba9-45dd-b4af-4242690a22db.jpg
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

        public static class LishijialistBean implements Parcelable {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * price : 0.00
             * pricecode : 617280
             */

            private String unit_bilv;
            private String unitname;
            private String price;
            private String pricecode;

            public LishijialistBean(String unit_bilv, String unitname) {
                this.unit_bilv = unit_bilv;
                this.unitname = unitname;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof LishijialistBean) {
                    return unit_bilv.equals(((LishijialistBean) obj).unit_bilv) && unitname.equals(((LishijialistBean) obj).unitname);
                }
                return false;
            }

            @Override
            public int hashCode() {
                int result = 37;
                result += 17 * unit_bilv.hashCode();
                result += 17 * unitname.hashCode();
                return result;
            }

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPricecode() {
                return pricecode;
            }

            public void setPricecode(String pricecode) {
                this.pricecode = pricecode;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.unit_bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.price);
                dest.writeString(this.pricecode);
            }

            public LishijialistBean() {
            }

            protected LishijialistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.price = in.readString();
                this.pricecode = in.readString();
            }

            public static final Creator<LishijialistBean> CREATOR = new Creator<LishijialistBean>() {
                @Override
                public LishijialistBean createFromParcel(Parcel source) {
                    return new LishijialistBean(source);
                }

                @Override
                public LishijialistBean[] newArray(int size) {
                    return new LishijialistBean[size];
                }
            };
        }

        public static class ChengbenjialistBean implements Parcelable {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * price : 16.39
             * pricecode : 633670
             */

            private String unit_bilv;
            private String unitname;
            private String price;
            private String pricecode;

            public ChengbenjialistBean(String unit_bilv, String unitname) {
                this.unit_bilv = unit_bilv;
                this.unitname = unitname;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof LishijialistBean) {
                    return unit_bilv.equals(((LishijialistBean) obj).unit_bilv) && unitname.equals(((LishijialistBean) obj).unitname);
                }
                return false;
            }

            @Override
            public int hashCode() {
                int result = 37;
                result += 17 * unit_bilv.hashCode();
                result += 17 * unitname.hashCode();
                return result;
            }

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPricecode() {
                return pricecode;
            }

            public void setPricecode(String pricecode) {
                this.pricecode = pricecode;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.unit_bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.price);
                dest.writeString(this.pricecode);
            }

            public ChengbenjialistBean() {
            }

            protected ChengbenjialistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.price = in.readString();
                this.pricecode = in.readString();
            }

            public static final Creator<ChengbenjialistBean> CREATOR = new Creator<ChengbenjialistBean>() {
                @Override
                public ChengbenjialistBean createFromParcel(Parcel source) {
                    return new ChengbenjialistBean(source);
                }

                @Override
                public ChengbenjialistBean[] newArray(int size) {
                    return new ChengbenjialistBean[size];
                }
            };
        }

        public static class CankaoshoujialistBean implements Parcelable {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * price : 15.36
             * pricecode : 632640
             */

            private String unit_bilv;
            private String unitname;
            private String price;
            private String pricecode;

            public CankaoshoujialistBean(String unit_bilv, String unitname) {
                this.unit_bilv = unit_bilv;
                this.unitname = unitname;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof LishijialistBean) {
                    return unit_bilv.equals(((LishijialistBean) obj).unit_bilv) && unitname.equals(((LishijialistBean) obj).unitname);
                }
                return false;
            }

            @Override
            public int hashCode() {
                int result = 37;
                result += 17 * unit_bilv.hashCode();
                result += 17 * unitname.hashCode();
                return result;
            }

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPricecode() {
                return pricecode;
            }

            public void setPricecode(String pricecode) {
                this.pricecode = pricecode;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.unit_bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.price);
                dest.writeString(this.pricecode);
            }

            public CankaoshoujialistBean() {
            }

            protected CankaoshoujialistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.price = in.readString();
                this.pricecode = in.readString();
            }

            public static final Creator<CankaoshoujialistBean> CREATOR = new Creator<CankaoshoujialistBean>() {
                @Override
                public CankaoshoujialistBean createFromParcel(Parcel source) {
                    return new CankaoshoujialistBean(source);
                }

                @Override
                public CankaoshoujialistBean[] newArray(int size) {
                    return new CankaoshoujialistBean[size];
                }
            };
        }

        public static class ChuchangjialistBean implements Parcelable {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * price : 21.12
             * pricecode : 638400
             */

            private String unit_bilv;
            private String unitname;
            private String price;
            private String pricecode;

            public ChuchangjialistBean(String unit_bilv, String unitname) {
                this.unit_bilv = unit_bilv;
                this.unitname = unitname;
            }

            @Override
            public boolean equals(Object obj) {
                if (obj instanceof LishijialistBean) {
                    return unit_bilv.equals(((LishijialistBean) obj).unit_bilv) && unitname.equals(((LishijialistBean) obj).unitname);
                }
                return false;
            }

            @Override
            public int hashCode() {
                int result = 37;
                result += 17 * unit_bilv.hashCode();
                result += 17 * unitname.hashCode();
                return result;
            }

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPricecode() {
                return pricecode;
            }

            public void setPricecode(String pricecode) {
                this.pricecode = pricecode;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.unit_bilv);
                dest.writeString(this.unitname);
                dest.writeString(this.price);
                dest.writeString(this.pricecode);
            }

            public ChuchangjialistBean() {
            }

            protected ChuchangjialistBean(Parcel in) {
                this.unit_bilv = in.readString();
                this.unitname = in.readString();
                this.price = in.readString();
                this.pricecode = in.readString();
            }

            public static final Creator<ChuchangjialistBean> CREATOR = new Creator<ChuchangjialistBean>() {
                @Override
                public ChuchangjialistBean createFromParcel(Parcel source) {
                    return new ChuchangjialistBean(source);
                }

                @Override
                public ChuchangjialistBean[] newArray(int size) {
                    return new ChuchangjialistBean[size];
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
            dest.writeString(this.kucunqty);
            dest.writeString(this.ziyouqty);
            dest.writeString(this.stop_produce);
            dest.writeString(this.stop_sales);
            dest.writeString(this.pounitname);
            dest.writeString(this.dianid);
            dest.writeTypedList(this.colorlist);
            dest.writeTypedList(this.packlist);
            dest.writeTypedList(this.sizxlist);
            dest.writeTypedList(this.piclist);
            dest.writeTypedList(this.lishijialist);
            dest.writeTypedList(this.chengbenjialist);
            dest.writeTypedList(this.cankaoshoujialist);
            dest.writeTypedList(this.chuchangjialist);
            dest.writeByte(this.isShowSelect ? (byte) 1 : (byte) 0);
            dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
            dest.writeString(this.unitname);
            dest.writeString(this.iid);
            dest.writeString(this.pnid);
            dest.writeString(this.puOrderNo);
            dest.writeString(this.yanse);
            dest.writeString(this.guige);
            dest.writeString(this.order_prc);
            dest.writeString(this.factor);
            dest.writeString(this.s_jiage2);
            dest.writeString(this.pricecode);
            dest.writeString(this.orderqty);
            dest.writeString(this.cp_qty);
            dest.writeString(this.orderamt);
            dest.writeString(this.rcvqty);
            dest.writeString(this.rcvamt);
            dest.writeString(this.statusstr);
            dest.writeString(this.bz);
            dest.writeString(this.zhuancaigou_pnid);
            dest.writeString(this.fujian);
            dest.writeString(this.huohao);
            dest.writeString(this.zengpin);
            dest.writeString(this.jiagelaiyuan);
            dest.writeString(this.buttonstatus_str);
        }

        public SupplierProductBean() {
        }

        protected SupplierProductBean(Parcel in) {
            this.cpid = in.readString();
            this.styleno = in.readString();
            this.barcode = in.readString();
            this.stylename = in.readString();
            this.classname = in.readString();
            this.photourl = in.readString();
            this.xinghao = in.readString();
            this.pinpai = in.readString();
            this.detail = in.readString();
            this.kucunqty = in.readString();
            this.ziyouqty = in.readString();
            this.stop_produce = in.readString();
            this.stop_sales = in.readString();
            this.pounitname = in.readString();
            this.dianid = in.readString();
            this.colorlist = in.createTypedArrayList(ColorlistBean.CREATOR);
            this.packlist = in.createTypedArrayList(PacklistBean.CREATOR);
            this.sizxlist = in.createTypedArrayList(SizxlistBean.CREATOR);
            this.piclist = in.createTypedArrayList(PiclistBean.CREATOR);
            this.lishijialist = in.createTypedArrayList(LishijialistBean.CREATOR);
            this.chengbenjialist = in.createTypedArrayList(ChengbenjialistBean.CREATOR);
            this.cankaoshoujialist = in.createTypedArrayList(CankaoshoujialistBean.CREATOR);
            this.chuchangjialist = in.createTypedArrayList(ChuchangjialistBean.CREATOR);
            this.isShowSelect = in.readByte() != 0;
            this.isSelected = in.readByte() != 0;
            this.unitname = in.readString();
            this.iid = in.readString();
            this.pnid = in.readString();
            this.puOrderNo = in.readString();
            this.yanse = in.readString();
            this.guige = in.readString();
            this.order_prc = in.readString();
            this.factor = in.readString();
            this.s_jiage2 = in.readString();
            this.pricecode = in.readString();
            this.orderqty = in.readString();
            this.cp_qty = in.readString();
            this.orderamt = in.readString();
            this.rcvqty = in.readString();
            this.rcvamt = in.readString();
            this.statusstr = in.readString();
            this.bz = in.readString();
            this.zhuancaigou_pnid = in.readString();
            this.fujian = in.readString();
            this.huohao = in.readString();
            this.zengpin = in.readString();
            this.jiagelaiyuan = in.readString();
            this.buttonstatus_str = in.readString();
        }

        public static final Creator<SupplierProductBean> CREATOR = new Creator<SupplierProductBean>() {
            @Override
            public SupplierProductBean createFromParcel(Parcel source) {
                return new SupplierProductBean(source);
            }

            @Override
            public SupplierProductBean[] newArray(int size) {
                return new SupplierProductBean[size];
            }
        };
    }
}
