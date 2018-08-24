package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/21
 */

public class ProductItem implements Parcelable {

    private String id;
    private String productNo;
    private String color;
    private String size;
    private String factor;
    private String unit;
    private String amount;
    private String singlePrice;
    private String unitPrice;
    private boolean isGift;
    private String tip;
    private String goodsNo;
    private String priceCode;
    private String priceSource;
    private String photourl;
    private String stylename;

    private String dianid;
    private String classname;
    private String pounitname;
    private String barcode;
    private String xinghao;
    private String pinpai;
    private String detail;
    private String stop_produce;
    private String stop_sales;
    private String iid;
    private String pnid;
    private String puOrderNo;
    private String pricecode;
    private String orderqty;
    private String orderamt;
    private String rcvqty;
    private String zhuancaigou_pnid;
    private String fujian;
    private String kucunqty;
    private String ziyouqty;
    private String buttonstatus_str;
    private String status;
    private String rcvamt;
    private List<ColorlistBean> colorlist;
    private List<PacklistBean> packlist;
    private List<SizxlistBean> sizxlist;
    private List<PiclistBean> piclist;
    private List<LishijialistBean> lishijialist;
    private List<ChengbenjialistBean> chengbenjialist;
    private List<CankaoshoujialistBean> cankaoshoujialist;
    private List<ChuchangjialistBean> chuchangjialist;

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

        public SizxlistBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sizx);
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

        public PiclistBean() {
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.pic);
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

        public LishijialistBean() {
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
            if (obj instanceof ChengbenjialistBean) {
                return unit_bilv.equals(((ChengbenjialistBean) obj).unit_bilv) && unitname.equals(((ChengbenjialistBean) obj).unitname);
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

        public ChengbenjialistBean() {
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
            if (obj instanceof CankaoshoujialistBean) {
                return unit_bilv.equals(((CankaoshoujialistBean) obj).unit_bilv) && unitname.equals(((CankaoshoujialistBean) obj).unitname);
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

        public CankaoshoujialistBean() {
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
            if (obj instanceof ChuchangjialistBean) {
                return unit_bilv.equals(((ChuchangjialistBean) obj).unit_bilv) && unitname.equals(((ChuchangjialistBean) obj).unitname);
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

        public ChuchangjialistBean() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(String singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isGift() {
        return isGift;
    }

    public void setGift(boolean gift) {
        isGift = gift;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(String priceCode) {
        this.priceCode = priceCode;
    }

    public String getPriceSource() {
        return priceSource;
    }

    public void setPriceSource(String priceSource) {
        this.priceSource = priceSource;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getStylename() {
        return stylename;
    }

    public void setStylename(String stylename) {
        this.stylename = stylename;
    }

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

    public String getButtonstatus_str() {
        return buttonstatus_str;
    }

    public void setButtonstatus_str(String buttonstatus_str) {
        this.buttonstatus_str = buttonstatus_str;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRcvamt() {
        return rcvamt;
    }

    public void setRcvamt(String rcvamt) {
        this.rcvamt = rcvamt;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.productNo);
        dest.writeString(this.color);
        dest.writeString(this.size);
        dest.writeString(this.factor);
        dest.writeString(this.unit);
        dest.writeString(this.amount);
        dest.writeString(this.singlePrice);
        dest.writeString(this.unitPrice);
        dest.writeByte(this.isGift ? (byte) 1 : (byte) 0);
        dest.writeString(this.tip);
        dest.writeString(this.goodsNo);
        dest.writeString(this.priceCode);
        dest.writeString(this.priceSource);
        dest.writeString(this.photourl);
        dest.writeString(this.stylename);
        dest.writeString(this.dianid);
        dest.writeString(this.classname);
        dest.writeString(this.pounitname);
        dest.writeString(this.barcode);
        dest.writeString(this.xinghao);
        dest.writeString(this.pinpai);
        dest.writeString(this.detail);
        dest.writeString(this.stop_produce);
        dest.writeString(this.stop_sales);
        dest.writeString(this.iid);
        dest.writeString(this.pnid);
        dest.writeString(this.puOrderNo);
        dest.writeString(this.pricecode);
        dest.writeString(this.orderqty);
        dest.writeString(this.orderamt);
        dest.writeString(this.rcvqty);
        dest.writeString(this.zhuancaigou_pnid);
        dest.writeString(this.fujian);
        dest.writeString(this.kucunqty);
        dest.writeString(this.ziyouqty);
        dest.writeString(this.buttonstatus_str);
        dest.writeString(this.status);
        dest.writeString(this.rcvamt);
        dest.writeTypedList(this.colorlist);
        dest.writeTypedList(this.packlist);
        dest.writeTypedList(this.sizxlist);
        dest.writeTypedList(this.piclist);
        dest.writeTypedList(this.lishijialist);
        dest.writeTypedList(this.chengbenjialist);
        dest.writeTypedList(this.cankaoshoujialist);
        dest.writeTypedList(this.chuchangjialist);
    }

    public ProductItem() {
    }

    protected ProductItem(Parcel in) {
        this.id = in.readString();
        this.productNo = in.readString();
        this.color = in.readString();
        this.size = in.readString();
        this.factor = in.readString();
        this.unit = in.readString();
        this.amount = in.readString();
        this.singlePrice = in.readString();
        this.unitPrice = in.readString();
        this.isGift = in.readByte() != 0;
        this.tip = in.readString();
        this.goodsNo = in.readString();
        this.priceCode = in.readString();
        this.priceSource = in.readString();
        this.photourl = in.readString();
        this.stylename = in.readString();
        this.dianid = in.readString();
        this.classname = in.readString();
        this.pounitname = in.readString();
        this.barcode = in.readString();
        this.xinghao = in.readString();
        this.pinpai = in.readString();
        this.detail = in.readString();
        this.stop_produce = in.readString();
        this.stop_sales = in.readString();
        this.iid = in.readString();
        this.pnid = in.readString();
        this.puOrderNo = in.readString();
        this.pricecode = in.readString();
        this.orderqty = in.readString();
        this.orderamt = in.readString();
        this.rcvqty = in.readString();
        this.zhuancaigou_pnid = in.readString();
        this.fujian = in.readString();
        this.kucunqty = in.readString();
        this.ziyouqty = in.readString();
        this.buttonstatus_str = in.readString();
        this.status = in.readString();
        this.rcvamt = in.readString();
        this.colorlist = in.createTypedArrayList(ColorlistBean.CREATOR);
        this.packlist = in.createTypedArrayList(PacklistBean.CREATOR);
        this.sizxlist = in.createTypedArrayList(SizxlistBean.CREATOR);
        this.piclist = in.createTypedArrayList(PiclistBean.CREATOR);
        this.lishijialist = in.createTypedArrayList(LishijialistBean.CREATOR);
        this.chengbenjialist = in.createTypedArrayList(ChengbenjialistBean.CREATOR);
        this.cankaoshoujialist = in.createTypedArrayList(CankaoshoujialistBean.CREATOR);
        this.chuchangjialist = in.createTypedArrayList(ChuchangjialistBean.CREATOR);
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
