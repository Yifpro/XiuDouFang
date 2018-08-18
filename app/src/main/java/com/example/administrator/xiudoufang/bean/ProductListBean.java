package com.example.administrator.xiudoufang.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/18
 */

public class ProductListBean {


    /**
     * status : 1
     * message : null
     * po_chanpinlist : [{"cpid":"10924","styleno":"10002001","barcode":"6948154800029","stylename":"粗齿梳","classname":"秀逗粗齿钢梳","photourl":"nopic.png","xinghao":"","pinpai":"秀逗","detail":"","kucunqty":"0","ziyouqty":"0","stop_produce":"0","stop_sales":"0","pounitname":"个","colorlist":[],"packlist":[{"unit_bilv":"1","unitname":"个/个","check":"1"}],"sizxlist":[],"piclist":[{"pic":"nopic.png"}],"lishijialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}],"chengbenjialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}],"cankaoshoujialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.50","pricecode":"617780"}],"chuchangjialist":[{"unit_bilv":"1","unitname":"个/个","price":"0.52","pricecode":"617800"}],"dianid":"45"}]
     */

    private String status;
    private Object message;
    private List<ProductBean> po_chanpinlist;

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

    public List<ProductBean> getPo_chanpinlist() {
        return po_chanpinlist;
    }

    public void setPo_chanpinlist(List<ProductBean> po_chanpinlist) {
        this.po_chanpinlist = po_chanpinlist;
    }

    public static class ProductBean {
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
         * kucunqty : 0
         * ziyouqty : 0
         * stop_produce : 0
         * stop_sales : 0
         * pounitname : 个
         * colorlist : []
         * packlist : [{"unit_bilv":"1","unitname":"个/个","check":"1"}]
         * sizxlist : []
         * piclist : [{"pic":"nopic.png"}]
         * lishijialist : [{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}]
         * chengbenjialist : [{"unit_bilv":"1","unitname":"个/个","price":"0.00","pricecode":"617280"}]
         * cankaoshoujialist : [{"unit_bilv":"1","unitname":"个/个","price":"0.50","pricecode":"617780"}]
         * chuchangjialist : [{"unit_bilv":"1","unitname":"个/个","price":"0.52","pricecode":"617800"}]
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
        private List<?> colorlist;
        private List<PacklistBean> packlist;
        private List<?> sizxlist;
        private List<PiclistBean> piclist;
        private List<LishijialistBean> lishijialist;
        private List<ChengbenjialistBean> chengbenjialist;
        private List<CankaoshoujialistBean> cankaoshoujialist;
        private List<ChuchangjialistBean> chuchangjialist;
        private boolean isSelected;
        private boolean isShowSelect;

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

        public List<?> getColorlist() {
            return colorlist;
        }

        public void setColorlist(List<?> colorlist) {
            this.colorlist = colorlist;
        }

        public List<PacklistBean> getPacklist() {
            return packlist;
        }

        public void setPacklist(List<PacklistBean> packlist) {
            this.packlist = packlist;
        }

        public List<?> getSizxlist() {
            return sizxlist;
        }

        public void setSizxlist(List<?> sizxlist) {
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

        public static class PacklistBean {
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * check : 1
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
        }

        public static class PiclistBean {
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
        }

        public static class LishijialistBean {
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * price : 0.00
             * pricecode : 617280
             */

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
        }

        public static class ChengbenjialistBean {
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * price : 0.00
             * pricecode : 617280
             */

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
        }

        public static class CankaoshoujialistBean {
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * price : 0.50
             * pricecode : 617780
             */

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
        }

        public static class ChuchangjialistBean {
            /**
             * unit_bilv : 1
             * unitname : 个/个
             * price : 0.52
             * pricecode : 617800
             */

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
        }
    }
}
