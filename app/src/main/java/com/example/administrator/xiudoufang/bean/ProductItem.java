package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/21
 */

public class ProductItem implements Parcelable {

    private String dianid; //******** 店id ********
    private String classname; //******** 产品类别 ********
    private String cpid; //******** 产品id ********
    private String styleno; //******** 产品编号 ********
    private String stylename; //******** 产品名称 ********
    private String pounitname; //******** 单个的单位 ********
    private String barcode; //******** 条形码 ********
    private String photourl; //******** 图片 ********
    private String xinghao; //******** 型号 ********
    private String pinpai; //******** 品牌 ********
    private String detail; //******** 详述 ********
    private String stop_produce; //******** 停产 ********
    private String stop_sales; //******** 停售 ********
    private String kucunqty; //******** 库存数 ********
    private String ziyouqty; //******** 自由数 ********
    private List<ColorlistBean> colorlist; //******** 颜色列表 ********
    private List<PacklistBean> packlist; //******** 包装列表 ********
    private List<SizxlistBean> sizxlist; //******** 规格列表 ********
    private List<PiclistBean> piclist; //******** 图片列表 ********
    private List<LishijialistBean> lishijialist; //******** 历史价列表 ********
    private List<ChengbenjialistBean> chengbenjialist; //******** 成本价列表 ********
    private List<CankaoshoujialistBean> cankaoshoujialist; //******** 参考价列表 ********
    private List<ChuchangjialistBean> chuchangjialist; //******** 出厂价列表 ********

    private String unitname; //******** 单位 ********
    private String iid; //******** 采购单id ********
    private String pnid; //******** 当前行id ********
    private String puOrderNo; //******** 采购单编号 ********
    private String yanse; //******** 颜色 ********
    private String guige; //******** 规格 ********
    private String order_prc; //******** 单品价格 ********
    private String factor; //******** 比率 ********
    private String s_jiage2; //******** 单位价格 ********
    private String pricecode; //******** 单位价价码 ********
    private String orderqty; //******** 采购数量 ********
    private String cp_qty; //******** 数量 ********
    private String orderamt; //******** 金额 ********
    private String rcvqty; //******** 收货数量 ********
    private String rcvamt; //******** 收货金额 ********
    private String statusstr; //******** 当前产品状态 ********
    private String bz; //******** 备注 ********
    private String zhuancaigou_pnid; //******** 暂时没用 ********
    private String fujian; //******** 附件 ********
    private String zengpin; //******** 赠品 ********
    private String jiagelaiyuan; //******** 价格来源 ********
    private String buttonstatus_str; //******** 按钮显示的文本 ********

    public String getDianid() {
        return dianid;
    }

    public void setDianid(String dianid) {
        this.dianid = dianid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
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

    public String getStylename() {
        return stylename;
    }

    public void setStylename(String stylename) {
        this.stylename = stylename;
    }

    public String getPounitname() {
        return pounitname;
    }

    public void setPounitname(String pounitname) {
        this.pounitname = pounitname;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public static Creator<ProductItem> getCREATOR() {
        return CREATOR;
    }

    public static class ColorlistBean implements Parcelable {

        private String color;

        public ColorlistBean(String color) {
            this.color = color;
        }

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

        private String unit_bilv;
        private String unitname;
        private String check;

        public PacklistBean() {
        }

        public PacklistBean(String unit_bilv, String unitname, String check) {
            this.unit_bilv = unit_bilv;
            this.unitname = unitname;
            this.check = check;
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

        public String getCheck() {
            return check;
        }

        public void setCheck(String check) {
            this.check = check;
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

        private String sizx;

        public SizxlistBean(String sizx) {
            this.sizx = sizx;
        }

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

        private String pic;

        public PiclistBean(String pic) {
            this.pic = pic;
        }

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

        private String unit_bilv;
        private String unitname;
        private String price;
        private String pricecode;

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

        private String unit_bilv;
        private String unitname;
        private String price;
        private String pricecode;

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

        private String unit_bilv;
        private String unitname;
        private String price;
        private String pricecode;

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

        private String unit_bilv;
        private String unitname;
        private String price;
        private String pricecode;

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
        dest.writeString(this.dianid);
        dest.writeString(this.classname);
        dest.writeString(this.cpid);
        dest.writeString(this.styleno);
        dest.writeString(this.stylename);
        dest.writeString(this.pounitname);
        dest.writeString(this.barcode);
        dest.writeString(this.photourl);
        dest.writeString(this.xinghao);
        dest.writeString(this.pinpai);
        dest.writeString(this.detail);
        dest.writeString(this.stop_produce);
        dest.writeString(this.stop_sales);
        dest.writeString(this.kucunqty);
        dest.writeString(this.ziyouqty);
        dest.writeTypedList(this.colorlist);
        dest.writeTypedList(this.packlist);
        dest.writeTypedList(this.sizxlist);
        dest.writeTypedList(this.piclist);
        dest.writeTypedList(this.lishijialist);
        dest.writeTypedList(this.chengbenjialist);
        dest.writeTypedList(this.cankaoshoujialist);
        dest.writeTypedList(this.chuchangjialist);
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
        dest.writeString(this.zengpin);
        dest.writeString(this.jiagelaiyuan);
        dest.writeString(this.buttonstatus_str);
    }

    public ProductItem() {
    }

    protected ProductItem(Parcel in) {
        this.dianid = in.readString();
        this.classname = in.readString();
        this.cpid = in.readString();
        this.styleno = in.readString();
        this.stylename = in.readString();
        this.pounitname = in.readString();
        this.barcode = in.readString();
        this.photourl = in.readString();
        this.xinghao = in.readString();
        this.pinpai = in.readString();
        this.detail = in.readString();
        this.stop_produce = in.readString();
        this.stop_sales = in.readString();
        this.kucunqty = in.readString();
        this.ziyouqty = in.readString();
        this.colorlist = in.createTypedArrayList(ColorlistBean.CREATOR);
        this.packlist = in.createTypedArrayList(PacklistBean.CREATOR);
        this.sizxlist = in.createTypedArrayList(SizxlistBean.CREATOR);
        this.piclist = in.createTypedArrayList(PiclistBean.CREATOR);
        this.lishijialist = in.createTypedArrayList(LishijialistBean.CREATOR);
        this.chengbenjialist = in.createTypedArrayList(ChengbenjialistBean.CREATOR);
        this.cankaoshoujialist = in.createTypedArrayList(CankaoshoujialistBean.CREATOR);
        this.chuchangjialist = in.createTypedArrayList(ChuchangjialistBean.CREATOR);
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
        this.zengpin = in.readString();
        this.jiagelaiyuan = in.readString();
        this.buttonstatus_str = in.readString();
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel source) {
            return new ProductItem(source);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };
}
