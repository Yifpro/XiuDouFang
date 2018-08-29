package com.example.administrator.xiudoufang.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductDetailsBean {


    /**
     * status : 1
     * cp_prcmessage : [{"cpid":"36479","styleno":"4933300101","stylename":"健康麦秆大号单层杯l蓝","classname":"达人秀首饰盒","tiaoxingma":"6957303855384","photourl":"cc5e3cad-2316-4d37-b4c0-e6f471539920.png","chengbenjia":"2.026","chuchangjia":"2.500","cankaojinjia":"2.580","cankaoshoujia":"4.200","dianid":"45"}]
     * cp_trxprc : [{"cpid":"36479","suppname":"张侠辉","trxtype":"收货","trxdate":"06-26","qty":"48.0000","price":"2.5000","amt":"120.0000"}]
     * cp_levelprice : [{"cpid":"36479","dengjiprice":"3.88","dengji":"三级"},{"cpid":"36479","dengjiprice":"4.20","dengji":"四级"},{"cpid":"36479","dengjiprice":"4.80","dengji":"五级"}]
     */

    private String status;
    private List<CpPrcmessageBean> cp_prcmessage;
    private List<CpTrxprcBean> cp_trxprc;
    private List<CpLevelpriceBean> cp_levelprice;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CpPrcmessageBean> getCp_prcmessage() {
        return cp_prcmessage;
    }

    public void setCp_prcmessage(List<CpPrcmessageBean> cp_prcmessage) {
        this.cp_prcmessage = cp_prcmessage;
    }

    public List<CpTrxprcBean> getCp_trxprc() {
        return cp_trxprc;
    }

    public void setCp_trxprc(List<CpTrxprcBean> cp_trxprc) {
        this.cp_trxprc = cp_trxprc;
    }

    public List<CpLevelpriceBean> getCp_levelprice() {
        return cp_levelprice;
    }

    public void setCp_levelprice(List<CpLevelpriceBean> cp_levelprice) {
        this.cp_levelprice = cp_levelprice;
    }

    public static class CpPrcmessageBean {
        /**
         * cpid : 36479
         * styleno : 4933300101
         * stylename : 健康麦秆大号单层杯l蓝
         * classname : 达人秀首饰盒
         * tiaoxingma : 6957303855384
         * photourl : cc5e3cad-2316-4d37-b4c0-e6f471539920.png
         * chengbenjia : 2.026
         * chuchangjia : 2.500
         * cankaojinjia : 2.580
         * cankaoshoujia : 4.200
         * dianid : 45
         */

        private String cpid;
        private String styleno;
        private String stylename;
        private String classname;
        private String tiaoxingma;
        private String photourl;
        private String chengbenjia;
        private String chuchangjia;
        private String cankaojinjia;
        private String cankaoshoujia;
        private String dianid;

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

        public String getClassname() {
            return classname;
        }

        public void setClassname(String classname) {
            this.classname = classname;
        }

        public String getTiaoxingma() {
            return tiaoxingma;
        }

        public void setTiaoxingma(String tiaoxingma) {
            this.tiaoxingma = tiaoxingma;
        }

        public String getPhotourl() {
            return photourl;
        }

        public void setPhotourl(String photourl) {
            this.photourl = photourl;
        }

        public String getChengbenjia() {
            return chengbenjia;
        }

        public void setChengbenjia(String chengbenjia) {
            this.chengbenjia = chengbenjia;
        }

        public String getChuchangjia() {
            return chuchangjia;
        }

        public void setChuchangjia(String chuchangjia) {
            this.chuchangjia = chuchangjia;
        }

        public String getCankaojinjia() {
            return cankaojinjia;
        }

        public void setCankaojinjia(String cankaojinjia) {
            this.cankaojinjia = cankaojinjia;
        }

        public String getCankaoshoujia() {
            return cankaoshoujia;
        }

        public void setCankaoshoujia(String cankaoshoujia) {
            this.cankaoshoujia = cankaoshoujia;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }
    }

    public static class CpTrxprcBean {
        /**
         * cpid : 36479
         * suppname : 张侠辉
         * trxtype : 收货
         * trxdate : 06-26
         * qty : 48.0000
         * price : 2.5000
         * amt : 120.0000
         */

        private String cpid;
        private String suppname;
        private String trxtype;
        private String trxdate;
        private String qty;
        private String price;
        private String amt;

        public String getCpid() {
            return cpid;
        }

        public void setCpid(String cpid) {
            this.cpid = cpid;
        }

        public String getSuppname() {
            return suppname;
        }

        public void setSuppname(String suppname) {
            this.suppname = suppname;
        }

        public String getTrxtype() {
            return trxtype;
        }

        public void setTrxtype(String trxtype) {
            this.trxtype = trxtype;
        }

        public String getTrxdate() {
            return trxdate;
        }

        public void setTrxdate(String trxdate) {
            this.trxdate = trxdate;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getAmt() {
            return amt;
        }

        public void setAmt(String amt) {
            this.amt = amt;
        }
    }

    public static class CpLevelpriceBean {
        /**
         * cpid : 36479
         * dengjiprice : 3.88
         * dengji : 三级
         */

        private String cpid;
        private String dengjiprice;
        private String dengji;

        public String getCpid() {
            return cpid;
        }

        public void setCpid(String cpid) {
            this.cpid = cpid;
        }

        public String getDengjiprice() {
            return dengjiprice;
        }

        public void setDengjiprice(String dengjiprice) {
            this.dengjiprice = dengjiprice;
        }

        public String getDengji() {
            return dengji;
        }

        public void setDengji(String dengji) {
            this.dengji = dengji;
        }
    }
}
