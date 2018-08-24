package com.example.administrator.xiudoufang.bean;

import java.util.List;

public class PurchaseListBean {


    /**
     * status : 1
     * message : 1
     * results : [{"dianid":"45","iid":"22279","puOrderNo":"PO1808-0003","c_id":null,"customerno":"KH17-0007","customername":"义乌秀逗总店","lianxiren":null,"tel":null,"telephone":null,"status_str":"1","issDate":"2018/8/11 11:44:54","etadate":"2018/8/11 11:44:54","orderAmt":"0.0000","crman":"莉莉","crtime":"2018/8/11 11:44:54","queren_status":"0","queren_man":"","warehouseid":"0","warehouse":"","shipstatus":"0","fuji_iid":"0","quyuno":"","quyu":"","fujian":"","fujia_memo":"","fendianid":"2143","remark":"","tijiao_status":"0","anxutuihuo":"0","benci_amt":null,"bankid":null,"accountid":null,"fromorder_url":null,"debt":null,"pricecode":null,"youhuijine":null}]
     */

    private String status;
    private String message;
    private List<PurchaseBean> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PurchaseBean> getResults() {
        return results;
    }

    public void setResults(List<PurchaseBean> results) {
        this.results = results;
    }

    public static class PurchaseBean {
        /**
         * dianid : 45
         * iid : 22279
         * puOrderNo : PO1808-0003
         * c_id : null
         * customerno : KH17-0007
         * customername : 义乌秀逗总店
         * lianxiren : null
         * tel : null
         * telephone : null
         * status_str : 1
         * issDate : 2018/8/11 11:44:54
         * etadate : 2018/8/11 11:44:54
         * orderAmt : 0.0000
         * crman : 莉莉
         * crtime : 2018/8/11 11:44:54
         * queren_status : 0
         * queren_man :
         * warehouseid : 0
         * warehouse :
         * shipstatus : 0
         * fuji_iid : 0
         * quyuno :
         * quyu :
         * fujian :
         * fujia_memo :
         * fendianid : 2143
         * remark :
         * tijiao_status : 0
         * anxutuihuo : 0
         * benci_amt : null
         * bankid : null
         * accountid : null
         * fromorder_url : null
         * debt : null
         * pricecode : null
         * youhuijine : null
         */

        private String dianid;
        private String iid;
        private String puOrderNo;
        private Object c_id;
        private String customerno;
        private String customername;
        private Object lianxiren;
        private Object tel;
        private Object telephone;
        private String status_str;
        private String issDate;
        private String etadate;
        private String orderAmt;
        private String crman;
        private String crtime;
        private String queren_status;
        private String queren_man;
        private String warehouseid;
        private String warehouse;
        private String shipstatus;
        private String fuji_iid;
        private String quyuno;
        private String quyu;
        private String fujian;
        private String fujia_memo;
        private String fendianid;
        private String remark;
        private String tijiao_status;
        private String anxutuihuo;
        private String benci_amt;
        private String bankid;
        private String accountid;
        private String fromorder_url;
        private String debt;
        private String pricecode;
        private String youhuijine;

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public String getPuOrderNo() {
            return puOrderNo;
        }

        public void setPuOrderNo(String puOrderNo) {
            this.puOrderNo = puOrderNo;
        }

        public Object getC_id() {
            return c_id;
        }

        public void setC_id(Object c_id) {
            this.c_id = c_id;
        }

        public String getCustomerno() {
            return customerno;
        }

        public void setCustomerno(String customerno) {
            this.customerno = customerno;
        }

        public String getCustomername() {
            return customername;
        }

        public void setCustomername(String customername) {
            this.customername = customername;
        }

        public Object getLianxiren() {
            return lianxiren;
        }

        public void setLianxiren(Object lianxiren) {
            this.lianxiren = lianxiren;
        }

        public Object getTel() {
            return tel;
        }

        public void setTel(Object tel) {
            this.tel = tel;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }

        public String getStatus_str() {
            return status_str;
        }

        public void setStatus_str(String status_str) {
            this.status_str = status_str;
        }

        public String getIssDate() {
            return issDate;
        }

        public void setIssDate(String issDate) {
            this.issDate = issDate;
        }

        public String getEtadate() {
            return etadate;
        }

        public void setEtadate(String etadate) {
            this.etadate = etadate;
        }

        public String getOrderAmt() {
            return orderAmt;
        }

        public void setOrderAmt(String orderAmt) {
            this.orderAmt = orderAmt;
        }

        public String getCrman() {
            return crman;
        }

        public void setCrman(String crman) {
            this.crman = crman;
        }

        public String getCrtime() {
            return crtime;
        }

        public void setCrtime(String crtime) {
            this.crtime = crtime;
        }

        public String getQueren_status() {
            return queren_status;
        }

        public void setQueren_status(String queren_status) {
            this.queren_status = queren_status;
        }

        public String getQueren_man() {
            return queren_man;
        }

        public void setQueren_man(String queren_man) {
            this.queren_man = queren_man;
        }

        public String getWarehouseid() {
            return warehouseid;
        }

        public void setWarehouseid(String warehouseid) {
            this.warehouseid = warehouseid;
        }

        public String getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(String warehouse) {
            this.warehouse = warehouse;
        }

        public String getShipstatus() {
            return shipstatus;
        }

        public void setShipstatus(String shipstatus) {
            this.shipstatus = shipstatus;
        }

        public String getFuji_iid() {
            return fuji_iid;
        }

        public void setFuji_iid(String fuji_iid) {
            this.fuji_iid = fuji_iid;
        }

        public String getQuyuno() {
            return quyuno;
        }

        public void setQuyuno(String quyuno) {
            this.quyuno = quyuno;
        }

        public String getQuyu() {
            return quyu;
        }

        public void setQuyu(String quyu) {
            this.quyu = quyu;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }

        public String getFujia_memo() {
            return fujia_memo;
        }

        public void setFujia_memo(String fujia_memo) {
            this.fujia_memo = fujia_memo;
        }

        public String getFendianid() {
            return fendianid;
        }

        public void setFendianid(String fendianid) {
            this.fendianid = fendianid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getTijiao_status() {
            return tijiao_status;
        }

        public void setTijiao_status(String tijiao_status) {
            this.tijiao_status = tijiao_status;
        }

        public String getAnxutuihuo() {
            return anxutuihuo;
        }

        public void setAnxutuihuo(String anxutuihuo) {
            this.anxutuihuo = anxutuihuo;
        }

        public String getBenci_amt() {
            return benci_amt;
        }

        public void setBenci_amt(String benci_amt) {
            this.benci_amt = benci_amt;
        }

        public String getBankid() {
            return bankid;
        }

        public void setBankid(String bankid) {
            this.bankid = bankid;
        }

        public String getAccountid() {
            return accountid;
        }

        public void setAccountid(String accountid) {
            this.accountid = accountid;
        }

        public String getFromorder_url() {
            return fromorder_url;
        }

        public void setFromorder_url(String fromorder_url) {
            this.fromorder_url = fromorder_url;
        }

        public String getDebt() {
            return debt;
        }

        public void setDebt(String debt) {
            this.debt = debt;
        }

        public String getPricecode() {
            return pricecode;
        }

        public void setPricecode(String pricecode) {
            this.pricecode = pricecode;
        }

        public String getYouhuijine() {
            return youhuijine;
        }

        public void setYouhuijine(String youhuijine) {
            this.youhuijine = youhuijine;
        }
    }
}
