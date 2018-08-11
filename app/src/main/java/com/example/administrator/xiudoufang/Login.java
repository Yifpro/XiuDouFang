package com.example.administrator.xiudoufang;

import java.util.List;

public class Login {

    /**
     * status : 1
     * username : ll
     * xm : 莉莉
     * dianid : 45
     * zongdianid : 45
     * picheight : 800
     * picwidth : 800
     * qx : 1
     * bumen : 总经理办公室
     * email :
     * dengji : 一级
     * dengji_value : 1
     * maxdj : 1
     * userid : 495
     * pic : nopic.png
     * mindengji : 10
     * poprice_mode : 1
     * po_jiage_gongshi : (,价码,/,1000,*,2,-,1234.56,),/,2
     * po_jiamae_gongshi : (,价格,*,2,+,1234.56,),/,2,*,1000
     * po_pricerate : 30.50
     * config : [{"name":"kaidanshifoukoujiankucun","values":"0"},{"name":"morenjiagelaiyuan","values":"1"},{"name":"relogintime","values":"24"},{"name":"genneralunit","values":"4"}]
     * permission : [{"name":"cancel_finishorder","values":"1"},{"name":"change_postatus","values":"1"},{"name":"close_orderproduct","values":"1"},{"name":"confirmorder","values":"1"},{"name":"edit_poorder","values":"0"},{"name":"edit_salesorder","values":"0"},{"name":"finishorder","values":"1"},{"name":"pic_showprc","values":"1"},{"name":"re_confirm_order","values":"1"}]
     * user : [{"userid":"0","name":"无"},{"userid":"620","name":"郭奕如"},{"userid":"667","name":"林晓珠（总）"}]
     * pay : [{"id":"1","payname":"现金支付","name":"现金支付","number":"","moren":"1"},{"id":"23","payname":"林伟才","name":"工行","number":"6222022019002005559","moren":"0"},{"id":"24","payname":"zxm","name":"zxm","number":"zxm","moren":"0"},{"id":"20","payname":"林伟才","name":"交通银行","number":"6222600740000800473","moren":"0"},{"id":"0","payname":"","name":"无","number":"","moren":"0"}]
     * huoyun : [{"id":"0","name":"无","moren":"1"}]
     * ordertype : [{"id":"0","name":"国内","moren":"1"},{"id":"1","name":"外贸","moren":"0"},{"id":"2","name":"电商","moren":"0"}]
     * fahuodian : [{"id":"0","name":"本店","moren":"1"},{"id":"2132","name":"广州分部","moren":"0"},{"id":"2143","name":"义乌总店","moren":"0"},{"id":"2144","name":"义乌一期102","moren":"0"},{"id":"2145","name":"义乌二期203","moren":"0"},{"id":"2147","name":"义乌二期202","moren":"0"}]
     * logindian : [{"id":"2132","mjname":"广州分部"},{"id":"45","mjname":"雅秀"},{"id":"2143","mjname":"义乌总店"},{"id":"2144","mjname":"义乌一期102"},{"id":"2145","mjname":"义乌二期203"},{"id":"2147","mjname":"义乌二期202"}]
     * quyu : [{"id":"5331","code":"0000","name":"外贸","fuji":"0"},{"id":"5023","code":"00000","name":"外贸","fuji":"0"}]
     * wuliu : [{"id":"0","name":"无","moren":"1"}]
     * change_postatusreason : [{"id":"1","name":"不生产","mokuai":"销售单"},{"id":"6","name":"供应商没货了","mokuai":"采购单"},{"id":"7","name":"库存已经满了","mokuai":"采购单"},{"id":"2","name":"欠货时间长取消","mokuai":"销售单"},{"id":"3","name":"欠货时间长替换","mokuai":"销售单"},{"id":"4","name":"取消","mokuai":"销售单"},{"id":"5","name":"替换","mokuai":"销售单"}]
     * messagestr : null
     */

    private String status;
    private String username;
    private String xm;
    private String dianid;
    private String zongdianid;
    private String picheight;
    private String picwidth;
    private String qx;
    private String bumen;
    private String email;
    private String dengji;
    private String dengji_value;
    private String maxdj;
    private String userid;
    private String pic;
    private String mindengji;
    private String poprice_mode;
    private String po_jiage_gongshi;
    private String po_jiamae_gongshi;
    private String po_pricerate;
    private Object messagestr;
    private List<ConfigBean> config;
    private List<PermissionBean> permission;
    private List<UserBean> user;
    private List<PayBean> pay;
    private List<HuoyunBean> huoyun;
    private List<OrdertypeBean> ordertype;
    private List<FahuodianBean> fahuodian;
    private List<LogindianBean> logindian;
    private List<QuyuBean> quyu;
    private List<WuliuBean> wuliu;
    private List<ChangePostatusreasonBean> change_postatusreason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getDianid() {
        return dianid;
    }

    public void setDianid(String dianid) {
        this.dianid = dianid;
    }

    public String getZongdianid() {
        return zongdianid;
    }

    public void setZongdianid(String zongdianid) {
        this.zongdianid = zongdianid;
    }

    public String getPicheight() {
        return picheight;
    }

    public void setPicheight(String picheight) {
        this.picheight = picheight;
    }

    public String getPicwidth() {
        return picwidth;
    }

    public void setPicwidth(String picwidth) {
        this.picwidth = picwidth;
    }

    public String getQx() {
        return qx;
    }

    public void setQx(String qx) {
        this.qx = qx;
    }

    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getMaxdj() {
        return maxdj;
    }

    public void setMaxdj(String maxdj) {
        this.maxdj = maxdj;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getMindengji() {
        return mindengji;
    }

    public void setMindengji(String mindengji) {
        this.mindengji = mindengji;
    }

    public String getPoprice_mode() {
        return poprice_mode;
    }

    public void setPoprice_mode(String poprice_mode) {
        this.poprice_mode = poprice_mode;
    }

    public String getPo_jiage_gongshi() {
        return po_jiage_gongshi;
    }

    public void setPo_jiage_gongshi(String po_jiage_gongshi) {
        this.po_jiage_gongshi = po_jiage_gongshi;
    }

    public String getPo_jiamae_gongshi() {
        return po_jiamae_gongshi;
    }

    public void setPo_jiamae_gongshi(String po_jiamae_gongshi) {
        this.po_jiamae_gongshi = po_jiamae_gongshi;
    }

    public String getPo_pricerate() {
        return po_pricerate;
    }

    public void setPo_pricerate(String po_pricerate) {
        this.po_pricerate = po_pricerate;
    }

    public Object getMessagestr() {
        return messagestr;
    }

    public void setMessagestr(Object messagestr) {
        this.messagestr = messagestr;
    }

    public List<ConfigBean> getConfig() {
        return config;
    }

    public void setConfig(List<ConfigBean> config) {
        this.config = config;
    }

    public List<PermissionBean> getPermission() {
        return permission;
    }

    public void setPermission(List<PermissionBean> permission) {
        this.permission = permission;
    }

    public List<UserBean> getUser() {
        return user;
    }

    public void setUser(List<UserBean> user) {
        this.user = user;
    }

    public List<PayBean> getPay() {
        return pay;
    }

    public void setPay(List<PayBean> pay) {
        this.pay = pay;
    }

    public List<HuoyunBean> getHuoyun() {
        return huoyun;
    }

    public void setHuoyun(List<HuoyunBean> huoyun) {
        this.huoyun = huoyun;
    }

    public List<OrdertypeBean> getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(List<OrdertypeBean> ordertype) {
        this.ordertype = ordertype;
    }

    public List<FahuodianBean> getFahuodian() {
        return fahuodian;
    }

    public void setFahuodian(List<FahuodianBean> fahuodian) {
        this.fahuodian = fahuodian;
    }

    public List<LogindianBean> getLogindian() {
        return logindian;
    }

    public void setLogindian(List<LogindianBean> logindian) {
        this.logindian = logindian;
    }

    public List<QuyuBean> getQuyu() {
        return quyu;
    }

    public void setQuyu(List<QuyuBean> quyu) {
        this.quyu = quyu;
    }

    public List<WuliuBean> getWuliu() {
        return wuliu;
    }

    public void setWuliu(List<WuliuBean> wuliu) {
        this.wuliu = wuliu;
    }

    public List<ChangePostatusreasonBean> getChange_postatusreason() {
        return change_postatusreason;
    }

    public void setChange_postatusreason(List<ChangePostatusreasonBean> change_postatusreason) {
        this.change_postatusreason = change_postatusreason;
    }

    public static class ConfigBean {
        /**
         * name : kaidanshifoukoujiankucun
         * values : 0
         */

        private String name;
        private String values;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }
    }

    public static class PermissionBean {
        /**
         * name : cancel_finishorder
         * values : 1
         */

        private String name;
        private String values;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValues() {
            return values;
        }

        public void setValues(String values) {
            this.values = values;
        }
    }

    public static class UserBean {
        /**
         * userid : 0
         * name : 无
         */

        private String userid;
        private String name;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class PayBean {
        /**
         * id : 1
         * payname : 现金支付
         * name : 现金支付
         * number :
         * moren : 1
         */

        private String id;
        private String payname;
        private String name;
        private String number;
        private String moren;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPayname() {
            return payname;
        }

        public void setPayname(String payname) {
            this.payname = payname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }
    }

    public static class HuoyunBean {
        /**
         * id : 0
         * name : 无
         * moren : 1
         */

        private String id;
        private String name;
        private String moren;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }
    }

    public static class OrdertypeBean {
        /**
         * id : 0
         * name : 国内
         * moren : 1
         */

        private String id;
        private String name;
        private String moren;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }
    }

    public static class FahuodianBean {
        /**
         * id : 0
         * name : 本店
         * moren : 1
         */

        private String id;
        private String name;
        private String moren;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }
    }

    public static class LogindianBean {
        /**
         * id : 2132
         * mjname : 广州分部
         */

        private String id;
        private String mjname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMjname() {
            return mjname;
        }

        public void setMjname(String mjname) {
            this.mjname = mjname;
        }
    }

    public static class QuyuBean {
        /**
         * id : 5331
         * code : 0000
         * name : 外贸
         * fuji : 0
         */

        private String id;
        private String code;
        private String name;
        private String fuji;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFuji() {
            return fuji;
        }

        public void setFuji(String fuji) {
            this.fuji = fuji;
        }
    }

    public static class WuliuBean {
        /**
         * id : 0
         * name : 无
         * moren : 1
         */

        private String id;
        private String name;
        private String moren;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoren() {
            return moren;
        }

        public void setMoren(String moren) {
            this.moren = moren;
        }
    }

    public static class ChangePostatusreasonBean {
        /**
         * id : 1
         * name : 不生产
         * mokuai : 销售单
         */

        private String id;
        private String name;
        private String mokuai;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMokuai() {
            return mokuai;
        }

        public void setMokuai(String mokuai) {
            this.mokuai = mokuai;
        }
    }
}
