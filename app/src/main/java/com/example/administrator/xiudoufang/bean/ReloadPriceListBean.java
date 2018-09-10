package com.example.administrator.xiudoufang.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/9/10
 */

public class ReloadPriceListBean {


    /**
     * message : 1
     * status : 1
     * suppprice_lists : [{"cpid":"8394","price":"0.60","pricecode":"617880","stylename":"直板密齿梳带链卡通","styleno":"10024001","unit_bilv":"1"}]
     */

    private String message;
    private String status;
    private List<ReloadPriceBean> suppprice_lists;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ReloadPriceBean> getSuppprice_lists() {
        return suppprice_lists;
    }

    public void setSuppprice_lists(List<ReloadPriceBean> suppprice_lists) {
        this.suppprice_lists = suppprice_lists;
    }

    public static class ReloadPriceBean {
        /**
         * cpid : 8394
         * price : 0.60
         * pricecode : 617880
         * stylename : 直板密齿梳带链卡通
         * styleno : 10024001
         * unit_bilv : 1
         */

        private String cpid;
        private String price;
        private String pricecode;
        private String stylename;
        private String styleno;
        private String unit_bilv;

        public String getCpid() {
            return cpid;
        }

        public void setCpid(String cpid) {
            this.cpid = cpid;
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

        public String getStylename() {
            return stylename;
        }

        public void setStylename(String stylename) {
            this.stylename = stylename;
        }

        public String getStyleno() {
            return styleno;
        }

        public void setStyleno(String styleno) {
            this.styleno = styleno;
        }

        public String getUnit_bilv() {
            return unit_bilv;
        }

        public void setUnit_bilv(String unit_bilv) {
            this.unit_bilv = unit_bilv;
        }
    }
}
