package com.example.administrator.xiudoufang.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class SupplierListBean {

    /**
     * status : 1
     * message : 请求成功
     * supplists : [{"c_id":"3155","customerno":"KH17-0007","customername":"义乌总店","quancheng":"义乌秀逗总店","dengji":"一级","dengji_value":"1","dianid":"45","debt":"20788311.26","pricecode":"20788928540","country":"0","quyu":"义乌市","quyuno":"0579","xinyongedu":"0.00","chaoguoxinyongedushishifouyunxukaidan":"1","lianxiren":"潘先生","qq":"","email":"","weixinhao":"","dianhua":"0579-85201232","telephone":"18257830102","address":"义乌市","fax":"","fendianid":"2143"}]
     */

    private String status;
    private String message;
    private List<SupplierBean> supplists;

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

    public List<SupplierBean> getSupplists() {
        return supplists;
    }

    public void setSupplists(List<SupplierBean> supplists) {
        this.supplists = supplists;
    }

    public static class SupplierBean {
        /**
         * c_id : 3155
         * customerno : KH17-0007
         * customername : 义乌总店
         * quancheng : 义乌秀逗总店
         * dengji : 一级
         * dengji_value : 1
         * dianid : 45
         * debt : 20788311.26
         * pricecode : 20788928540
         * country : 0
         * quyu : 义乌市
         * quyuno : 0579
         * xinyongedu : 0.00
         * chaoguoxinyongedushishifouyunxukaidan : 1
         * lianxiren : 潘先生
         * qq :
         * email :
         * weixinhao :
         * dianhua : 0579-85201232
         * telephone : 18257830102
         * address : 义乌市
         * fax :
         * fendianid : 2143
         */

        private String c_id;
        private String customerno;
        private String customername;
        private String quancheng;
        private String dengji;
        private String dengji_value;
        private String dianid;
        private String debt;
        private String pricecode;
        private String country;
        private String quyu;
        private String quyuno;
        private String xinyongedu;
        private String chaoguoxinyongedushishifouyunxukaidan;
        private String lianxiren;
        private String qq;
        private String email;
        private String weixinhao;
        private String dianhua;
        private String telephone;
        private String address;
        private String fax;
        private String fendianid;

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
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

        public String getQuancheng() {
            return quancheng;
        }

        public void setQuancheng(String quancheng) {
            this.quancheng = quancheng;
        }

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

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getQuyu() {
            return quyu;
        }

        public void setQuyu(String quyu) {
            this.quyu = quyu;
        }

        public String getQuyuno() {
            return quyuno;
        }

        public void setQuyuno(String quyuno) {
            this.quyuno = quyuno;
        }

        public String getXinyongedu() {
            return xinyongedu;
        }

        public void setXinyongedu(String xinyongedu) {
            this.xinyongedu = xinyongedu;
        }

        public String getChaoguoxinyongedushishifouyunxukaidan() {
            return chaoguoxinyongedushishifouyunxukaidan;
        }

        public void setChaoguoxinyongedushishifouyunxukaidan(String chaoguoxinyongedushishifouyunxukaidan) {
            this.chaoguoxinyongedushishifouyunxukaidan = chaoguoxinyongedushishifouyunxukaidan;
        }

        public String getLianxiren() {
            return lianxiren;
        }

        public void setLianxiren(String lianxiren) {
            this.lianxiren = lianxiren;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWeixinhao() {
            return weixinhao;
        }

        public void setWeixinhao(String weixinhao) {
            this.weixinhao = weixinhao;
        }

        public String getDianhua() {
            return dianhua;
        }

        public void setDianhua(String dianhua) {
            this.dianhua = dianhua;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getFax() {
            return fax;
        }

        public void setFax(String fax) {
            this.fax = fax;
        }

        public String getFendianid() {
            return fendianid;
        }

        public void setFendianid(String fendianid) {
            this.fendianid = fendianid;
        }
    }
}
