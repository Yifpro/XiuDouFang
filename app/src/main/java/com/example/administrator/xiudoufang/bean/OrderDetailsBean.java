package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/31
 */

public class OrderDetailsBean {


    /**
     * status : 1
     * fx_orderlist : [{"iid":"30856","c_id":"1974","customername":"黄先生","quancheng":"黄先生","sno":"DRXXS180314-0008","s_dizhi":"粤西北路68号 喜特领","fahuodizhi":"","huoyunzhan":"","lianxiren":"黄先生","s_tel":"","telephone":"13566714848","s_amt":"18554.4000","yingshou_amt":"18554.4000","yishou_amt":"0.0000","youhuijine":"0.0000","benci_amt":"0.0000","remark":"","xiadanriqi":"2018/3/14 17:16:25","jiaohuoriqi":"2018/3/14 17:16:25","yuji_jiaohuoriqi":"","crtime":"2018/3/14 17:16:25","crman":"gj","status":"0","shipstatus":"0","bankid":"0","bankname":"无","bankno":"","kuaidiid":"0","kuaidiname":"","yewuyuans":"601","debt":"0.00","customerno":"E000559","qq":"","weixinhao":"","country":"1","quyu":"外贸","quyuno":"00000","ordertype":"1","huoyunleixing":"7","fahuodian":"45","fahuodianname":"雅秀","teshu":"0","fujia_memo":"","dengji":"五级","fujian":"","dengji_value":"5","feiyong":"0.0000","dianid":"2132","songhuo_time":"","cust_orderno":"","pay_yueamt":"0.00","yue_amt":"0.00","urlstr":"/admin/appweb/sale_html.aspx?iid=30856"}]
     * fx_orderproduct : [{"iid":"30856","pnid":"156993","cpid":"35923","styleno":"12058106","stylename":"直板梳.卡通B图","classname":"直板简装梳","pic":"zp-81568-2560812058106.png","s_qty":"2880.0000","s_jiage":"0.6500","s_jiage2":"0.6500","cp_qty":"2880.0000","color":"","sizx":"","bilv":"1","unitname":"个/个","phstatus":"0","ph_qty":"0.00","zhekou":"1.0000","tijiao_erpstatus":"0","stopsales_status":"0","zengpin":"0","dianid":"2132","jiagelaiyuan":"1","bz":"","huohao":"EG1803-2041","dengjilist":[{"dengji":"一级","dengji_value":"5","prc":"0.46"},{"dengji":"二级","dengji_value":"5","prc":"0.47"},{"dengji":"三级","dengji_value":"5","prc":"0.49"},{"dengji":"四级","dengji_value":"5","prc":"0.50"},{"dengji":"五级","dengji_value":"5","prc":"0.55"}],"pifajia":[],"packlist":[{"unit_bilv":"24","unitname":"个/包","check":"0","dengji_price":"13.2000","cankao_price":"12.0000","lishi_price":"0"},{"unit_bilv":"1","unitname":"个/个","check":"1","dengji_price":"0.5500","cankao_price":"0.5000","lishi_price":"0"},{"unit_bilv":"240","unitname":"个/盒","check":"0","dengji_price":"132.0000","cankao_price":"120.0000","lishi_price":"0"},{"unit_bilv":"2400","unitname":"个/件","check":"0","dengji_price":"1320.0000","cankao_price":"1200.0000","lishi_price":"0"}],"supp_prc":"0.500","xs_prc":"0.500","pf_prc":null,"lishijia":"0","xinghao":"","tiaoxingma":"6948154820584","detail":"","pinpai":"秀逗","status":"0","buttonText":"关闭","iscx":"0"}]
     */

    private String status;
    private List<OrderInfoBean> fx_orderlist;
    private List<OrderProductBean> fx_orderproduct;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderInfoBean> getFx_orderlist() {
        return fx_orderlist;
    }

    public void setFx_orderlist(List<OrderInfoBean> fx_orderlist) {
        this.fx_orderlist = fx_orderlist;
    }

    public List<OrderProductBean> getFx_orderproduct() {
        return fx_orderproduct;
    }

    public void setFx_orderproduct(List<OrderProductBean> fx_orderproduct) {
        this.fx_orderproduct = fx_orderproduct;
    }

    public static class OrderInfoBean implements Parcelable {
        /**
         * iid : 30856
         * c_id : 1974
         * customername : 黄先生
         * quancheng : 黄先生
         * sno : DRXXS180314-0008
         * s_dizhi : 粤西北路68号 喜特领
         * fahuodizhi :
         * huoyunzhan :
         * lianxiren : 黄先生
         * s_tel :
         * telephone : 13566714848
         * s_amt : 18554.4000
         * yingshou_amt : 18554.4000
         * yishou_amt : 0.0000
         * youhuijine : 0.0000
         * benci_amt : 0.0000
         * remark :
         * xiadanriqi : 2018/3/14 17:16:25
         * jiaohuoriqi : 2018/3/14 17:16:25
         * yuji_jiaohuoriqi :
         * crtime : 2018/3/14 17:16:25
         * crman : gj
         * status : 0
         * shipstatus : 0
         * bankid : 0
         * bankname : 无
         * bankno :
         * kuaidiid : 0
         * kuaidiname :
         * yewuyuans : 601
         * debt : 0.00
         * customerno : E000559
         * qq :
         * weixinhao :
         * country : 1
         * quyu : 外贸
         * quyuno : 00000
         * ordertype : 1
         * huoyunleixing : 7
         * fahuodian : 45
         * fahuodianname : 雅秀
         * teshu : 0
         * fujia_memo :
         * dengji : 五级
         * fujian :
         * dengji_value : 5
         * feiyong : 0.0000
         * dianid : 2132
         * songhuo_time :
         * cust_orderno :
         * pay_yueamt : 0.00
         * yue_amt : 0.00
         * urlstr : /admin/appweb/sale_html.aspx?iid=30856
         */

        private String iid;
        private String c_id;
        private String customername;
        private String quancheng;
        private String sno;
        private String s_dizhi;
        private String fahuodizhi;
        private String huoyunzhan;
        private String lianxiren;
        private String s_tel;
        private String telephone;
        private String s_amt;
        private String yingshou_amt;
        private String yishou_amt;
        private String youhuijine;
        private String benci_amt;
        private String remark;
        private String xiadanriqi;
        private String jiaohuoriqi;
        private String yuji_jiaohuoriqi;
        private String crtime;
        private String crman;
        private String status;
        private String shipstatus;
        private String bankid;
        private String bankname;
        private String bankno;
        private String kuaidiid;
        private String kuaidiname;
        private String yewuyuans;
        private String debt;
        private String customerno;
        private String qq;
        private String weixinhao;
        private String country;
        private String quyu;
        private String quyuno;
        private String ordertype;
        private String huoyunleixing;
        private String fahuodian;
        private String fahuodianname;
        private String teshu;
        private String fujia_memo;
        private String dengji;
        private String fujian;
        private String dengji_value;
        private String feiyong;
        private String dianid;
        private String songhuo_time;
        private String cust_orderno;
        private String pay_yueamt;
        private String yue_amt;
        private String urlstr;

        public String getIid() {
            return iid;
        }

        public void setIid(String iid) {
            this.iid = iid;
        }

        public String getC_id() {
            return c_id;
        }

        public void setC_id(String c_id) {
            this.c_id = c_id;
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

        public String getSno() {
            return sno;
        }

        public void setSno(String sno) {
            this.sno = sno;
        }

        public String getS_dizhi() {
            return s_dizhi;
        }

        public void setS_dizhi(String s_dizhi) {
            this.s_dizhi = s_dizhi;
        }

        public String getFahuodizhi() {
            return fahuodizhi;
        }

        public void setFahuodizhi(String fahuodizhi) {
            this.fahuodizhi = fahuodizhi;
        }

        public String getHuoyunzhan() {
            return huoyunzhan;
        }

        public void setHuoyunzhan(String huoyunzhan) {
            this.huoyunzhan = huoyunzhan;
        }

        public String getLianxiren() {
            return lianxiren;
        }

        public void setLianxiren(String lianxiren) {
            this.lianxiren = lianxiren;
        }

        public String getS_tel() {
            return s_tel;
        }

        public void setS_tel(String s_tel) {
            this.s_tel = s_tel;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getS_amt() {
            return s_amt;
        }

        public void setS_amt(String s_amt) {
            this.s_amt = s_amt;
        }

        public String getYingshou_amt() {
            return yingshou_amt;
        }

        public void setYingshou_amt(String yingshou_amt) {
            this.yingshou_amt = yingshou_amt;
        }

        public String getYishou_amt() {
            return yishou_amt;
        }

        public void setYishou_amt(String yishou_amt) {
            this.yishou_amt = yishou_amt;
        }

        public String getYouhuijine() {
            return youhuijine;
        }

        public void setYouhuijine(String youhuijine) {
            this.youhuijine = youhuijine;
        }

        public String getBenci_amt() {
            return benci_amt;
        }

        public void setBenci_amt(String benci_amt) {
            this.benci_amt = benci_amt;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getXiadanriqi() {
            return xiadanriqi;
        }

        public void setXiadanriqi(String xiadanriqi) {
            this.xiadanriqi = xiadanriqi;
        }

        public String getJiaohuoriqi() {
            return jiaohuoriqi;
        }

        public void setJiaohuoriqi(String jiaohuoriqi) {
            this.jiaohuoriqi = jiaohuoriqi;
        }

        public String getYuji_jiaohuoriqi() {
            return yuji_jiaohuoriqi;
        }

        public void setYuji_jiaohuoriqi(String yuji_jiaohuoriqi) {
            this.yuji_jiaohuoriqi = yuji_jiaohuoriqi;
        }

        public String getCrtime() {
            return crtime;
        }

        public void setCrtime(String crtime) {
            this.crtime = crtime;
        }

        public String getCrman() {
            return crman;
        }

        public void setCrman(String crman) {
            this.crman = crman;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getShipstatus() {
            return shipstatus;
        }

        public void setShipstatus(String shipstatus) {
            this.shipstatus = shipstatus;
        }

        public String getBankid() {
            return bankid;
        }

        public void setBankid(String bankid) {
            this.bankid = bankid;
        }

        public String getBankname() {
            return bankname;
        }

        public void setBankname(String bankname) {
            this.bankname = bankname;
        }

        public String getBankno() {
            return bankno;
        }

        public void setBankno(String bankno) {
            this.bankno = bankno;
        }

        public String getKuaidiid() {
            return kuaidiid;
        }

        public void setKuaidiid(String kuaidiid) {
            this.kuaidiid = kuaidiid;
        }

        public String getKuaidiname() {
            return kuaidiname;
        }

        public void setKuaidiname(String kuaidiname) {
            this.kuaidiname = kuaidiname;
        }

        public String getYewuyuans() {
            return yewuyuans;
        }

        public void setYewuyuans(String yewuyuans) {
            this.yewuyuans = yewuyuans;
        }

        public String getDebt() {
            return debt;
        }

        public void setDebt(String debt) {
            this.debt = debt;
        }

        public String getCustomerno() {
            return customerno;
        }

        public void setCustomerno(String customerno) {
            this.customerno = customerno;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixinhao() {
            return weixinhao;
        }

        public void setWeixinhao(String weixinhao) {
            this.weixinhao = weixinhao;
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

        public String getOrdertype() {
            return ordertype;
        }

        public void setOrdertype(String ordertype) {
            this.ordertype = ordertype;
        }

        public String getHuoyunleixing() {
            return huoyunleixing;
        }

        public void setHuoyunleixing(String huoyunleixing) {
            this.huoyunleixing = huoyunleixing;
        }

        public String getFahuodian() {
            return fahuodian;
        }

        public void setFahuodian(String fahuodian) {
            this.fahuodian = fahuodian;
        }

        public String getFahuodianname() {
            return fahuodianname;
        }

        public void setFahuodianname(String fahuodianname) {
            this.fahuodianname = fahuodianname;
        }

        public String getTeshu() {
            return teshu;
        }

        public void setTeshu(String teshu) {
            this.teshu = teshu;
        }

        public String getFujia_memo() {
            return fujia_memo;
        }

        public void setFujia_memo(String fujia_memo) {
            this.fujia_memo = fujia_memo;
        }

        public String getDengji() {
            return dengji;
        }

        public void setDengji(String dengji) {
            this.dengji = dengji;
        }

        public String getFujian() {
            return fujian;
        }

        public void setFujian(String fujian) {
            this.fujian = fujian;
        }

        public String getDengji_value() {
            return dengji_value;
        }

        public void setDengji_value(String dengji_value) {
            this.dengji_value = dengji_value;
        }

        public String getFeiyong() {
            return feiyong;
        }

        public void setFeiyong(String feiyong) {
            this.feiyong = feiyong;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getSonghuo_time() {
            return songhuo_time;
        }

        public void setSonghuo_time(String songhuo_time) {
            this.songhuo_time = songhuo_time;
        }

        public String getCust_orderno() {
            return cust_orderno;
        }

        public void setCust_orderno(String cust_orderno) {
            this.cust_orderno = cust_orderno;
        }

        public String getPay_yueamt() {
            return pay_yueamt;
        }

        public void setPay_yueamt(String pay_yueamt) {
            this.pay_yueamt = pay_yueamt;
        }

        public String getYue_amt() {
            return yue_amt;
        }

        public void setYue_amt(String yue_amt) {
            this.yue_amt = yue_amt;
        }

        public String getUrlstr() {
            return urlstr;
        }

        public void setUrlstr(String urlstr) {
            this.urlstr = urlstr;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.iid);
            dest.writeString(this.c_id);
            dest.writeString(this.customername);
            dest.writeString(this.quancheng);
            dest.writeString(this.sno);
            dest.writeString(this.s_dizhi);
            dest.writeString(this.fahuodizhi);
            dest.writeString(this.huoyunzhan);
            dest.writeString(this.lianxiren);
            dest.writeString(this.s_tel);
            dest.writeString(this.telephone);
            dest.writeString(this.s_amt);
            dest.writeString(this.yingshou_amt);
            dest.writeString(this.yishou_amt);
            dest.writeString(this.youhuijine);
            dest.writeString(this.benci_amt);
            dest.writeString(this.remark);
            dest.writeString(this.xiadanriqi);
            dest.writeString(this.jiaohuoriqi);
            dest.writeString(this.yuji_jiaohuoriqi);
            dest.writeString(this.crtime);
            dest.writeString(this.crman);
            dest.writeString(this.status);
            dest.writeString(this.shipstatus);
            dest.writeString(this.bankid);
            dest.writeString(this.bankname);
            dest.writeString(this.bankno);
            dest.writeString(this.kuaidiid);
            dest.writeString(this.kuaidiname);
            dest.writeString(this.yewuyuans);
            dest.writeString(this.debt);
            dest.writeString(this.customerno);
            dest.writeString(this.qq);
            dest.writeString(this.weixinhao);
            dest.writeString(this.country);
            dest.writeString(this.quyu);
            dest.writeString(this.quyuno);
            dest.writeString(this.ordertype);
            dest.writeString(this.huoyunleixing);
            dest.writeString(this.fahuodian);
            dest.writeString(this.fahuodianname);
            dest.writeString(this.teshu);
            dest.writeString(this.fujia_memo);
            dest.writeString(this.dengji);
            dest.writeString(this.fujian);
            dest.writeString(this.dengji_value);
            dest.writeString(this.feiyong);
            dest.writeString(this.dianid);
            dest.writeString(this.songhuo_time);
            dest.writeString(this.cust_orderno);
            dest.writeString(this.pay_yueamt);
            dest.writeString(this.yue_amt);
            dest.writeString(this.urlstr);
        }

        public OrderInfoBean() {
        }

        protected OrderInfoBean(Parcel in) {
            this.iid = in.readString();
            this.c_id = in.readString();
            this.customername = in.readString();
            this.quancheng = in.readString();
            this.sno = in.readString();
            this.s_dizhi = in.readString();
            this.fahuodizhi = in.readString();
            this.huoyunzhan = in.readString();
            this.lianxiren = in.readString();
            this.s_tel = in.readString();
            this.telephone = in.readString();
            this.s_amt = in.readString();
            this.yingshou_amt = in.readString();
            this.yishou_amt = in.readString();
            this.youhuijine = in.readString();
            this.benci_amt = in.readString();
            this.remark = in.readString();
            this.xiadanriqi = in.readString();
            this.jiaohuoriqi = in.readString();
            this.yuji_jiaohuoriqi = in.readString();
            this.crtime = in.readString();
            this.crman = in.readString();
            this.status = in.readString();
            this.shipstatus = in.readString();
            this.bankid = in.readString();
            this.bankname = in.readString();
            this.bankno = in.readString();
            this.kuaidiid = in.readString();
            this.kuaidiname = in.readString();
            this.yewuyuans = in.readString();
            this.debt = in.readString();
            this.customerno = in.readString();
            this.qq = in.readString();
            this.weixinhao = in.readString();
            this.country = in.readString();
            this.quyu = in.readString();
            this.quyuno = in.readString();
            this.ordertype = in.readString();
            this.huoyunleixing = in.readString();
            this.fahuodian = in.readString();
            this.fahuodianname = in.readString();
            this.teshu = in.readString();
            this.fujia_memo = in.readString();
            this.dengji = in.readString();
            this.fujian = in.readString();
            this.dengji_value = in.readString();
            this.feiyong = in.readString();
            this.dianid = in.readString();
            this.songhuo_time = in.readString();
            this.cust_orderno = in.readString();
            this.pay_yueamt = in.readString();
            this.yue_amt = in.readString();
            this.urlstr = in.readString();
        }

        public static final Creator<OrderInfoBean> CREATOR = new Creator<OrderInfoBean>() {
            @Override
            public OrderInfoBean createFromParcel(Parcel source) {
                return new OrderInfoBean(source);
            }

            @Override
            public OrderInfoBean[] newArray(int size) {
                return new OrderInfoBean[size];
            }
        };
    }

    public static class OrderProductBean {
        /**
         * iid : 30856
         * pnid : 156993
         * cpid : 35923
         * styleno : 12058106
         * stylename : 直板梳.卡通B图
         * classname : 直板简装梳
         * pic : zp-81568-2560812058106.png
         * s_qty : 2880.0000
         * s_jiage : 0.6500
         * s_jiage2 : 0.6500
         * cp_qty : 2880.0000
         * color :
         * sizx :
         * bilv : 1
         * unitname : 个/个
         * phstatus : 0
         * ph_qty : 0.00
         * zhekou : 1.0000
         * tijiao_erpstatus : 0
         * stopsales_status : 0
         * zengpin : 0
         * dianid : 2132
         * jiagelaiyuan : 1
         * bz :
         * huohao : EG1803-2041
         * dengjilist : [{"dengji":"一级","dengji_value":"5","prc":"0.46"},{"dengji":"二级","dengji_value":"5","prc":"0.47"},{"dengji":"三级","dengji_value":"5","prc":"0.49"},{"dengji":"四级","dengji_value":"5","prc":"0.50"},{"dengji":"五级","dengji_value":"5","prc":"0.55"}]
         * pifajia : []
         * packlist : [{"unit_bilv":"24","unitname":"个/包","check":"0","dengji_price":"13.2000","cankao_price":"12.0000","lishi_price":"0"},{"unit_bilv":"1","unitname":"个/个","check":"1","dengji_price":"0.5500","cankao_price":"0.5000","lishi_price":"0"},{"unit_bilv":"240","unitname":"个/盒","check":"0","dengji_price":"132.0000","cankao_price":"120.0000","lishi_price":"0"},{"unit_bilv":"2400","unitname":"个/件","check":"0","dengji_price":"1320.0000","cankao_price":"1200.0000","lishi_price":"0"}]
         * supp_prc : 0.500
         * xs_prc : 0.500
         * pf_prc : null
         * lishijia : 0
         * xinghao :
         * tiaoxingma : 6948154820584
         * detail :
         * pinpai : 秀逗
         * status : 0
         * buttonText : 关闭
         * iscx : 0
         */

        private String iid;
        private String pnid;
        private String cpid;
        private String styleno;
        private String stylename;
        private String classname;
        private String pic;
        private String s_qty;
        private String s_jiage;
        private String s_jiage2;
        private String cp_qty;
        private String color;
        private String sizx;
        private String bilv;
        private String unitname;
        private String phstatus;
        private String ph_qty;
        private String zhekou;
        private String tijiao_erpstatus;
        private String stopsales_status;
        private String zengpin;
        private String dianid;
        private String jiagelaiyuan;
        private String bz;
        private String huohao;
        private String supp_prc;
        private String xs_prc;
        private Object pf_prc;
        private String lishijia;
        private String xinghao;
        private String tiaoxingma;
        private String detail;
        private String pinpai;
        private String status;
        private String buttonText;
        private String iscx;
        private List<DengjilistBean> dengjilist;
        private List<?> pifajia;
        private List<PacklistBean> packlist;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getS_qty() {
            return s_qty;
        }

        public void setS_qty(String s_qty) {
            this.s_qty = s_qty;
        }

        public String getS_jiage() {
            return s_jiage;
        }

        public void setS_jiage(String s_jiage) {
            this.s_jiage = s_jiage;
        }

        public String getS_jiage2() {
            return s_jiage2;
        }

        public void setS_jiage2(String s_jiage2) {
            this.s_jiage2 = s_jiage2;
        }

        public String getCp_qty() {
            return cp_qty;
        }

        public void setCp_qty(String cp_qty) {
            this.cp_qty = cp_qty;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getSizx() {
            return sizx;
        }

        public void setSizx(String sizx) {
            this.sizx = sizx;
        }

        public String getBilv() {
            return bilv;
        }

        public void setBilv(String bilv) {
            this.bilv = bilv;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public String getPhstatus() {
            return phstatus;
        }

        public void setPhstatus(String phstatus) {
            this.phstatus = phstatus;
        }

        public String getPh_qty() {
            return ph_qty;
        }

        public void setPh_qty(String ph_qty) {
            this.ph_qty = ph_qty;
        }

        public String getZhekou() {
            return zhekou;
        }

        public void setZhekou(String zhekou) {
            this.zhekou = zhekou;
        }

        public String getTijiao_erpstatus() {
            return tijiao_erpstatus;
        }

        public void setTijiao_erpstatus(String tijiao_erpstatus) {
            this.tijiao_erpstatus = tijiao_erpstatus;
        }

        public String getStopsales_status() {
            return stopsales_status;
        }

        public void setStopsales_status(String stopsales_status) {
            this.stopsales_status = stopsales_status;
        }

        public String getZengpin() {
            return zengpin;
        }

        public void setZengpin(String zengpin) {
            this.zengpin = zengpin;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getJiagelaiyuan() {
            return jiagelaiyuan;
        }

        public void setJiagelaiyuan(String jiagelaiyuan) {
            this.jiagelaiyuan = jiagelaiyuan;
        }

        public String getBz() {
            return bz;
        }

        public void setBz(String bz) {
            this.bz = bz;
        }

        public String getHuohao() {
            return huohao;
        }

        public void setHuohao(String huohao) {
            this.huohao = huohao;
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

        public Object getPf_prc() {
            return pf_prc;
        }

        public void setPf_prc(Object pf_prc) {
            this.pf_prc = pf_prc;
        }

        public String getLishijia() {
            return lishijia;
        }

        public void setLishijia(String lishijia) {
            this.lishijia = lishijia;
        }

        public String getXinghao() {
            return xinghao;
        }

        public void setXinghao(String xinghao) {
            this.xinghao = xinghao;
        }

        public String getTiaoxingma() {
            return tiaoxingma;
        }

        public void setTiaoxingma(String tiaoxingma) {
            this.tiaoxingma = tiaoxingma;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getPinpai() {
            return pinpai;
        }

        public void setPinpai(String pinpai) {
            this.pinpai = pinpai;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getButtonText() {
            return buttonText;
        }

        public void setButtonText(String buttonText) {
            this.buttonText = buttonText;
        }

        public String getIscx() {
            return iscx;
        }

        public void setIscx(String iscx) {
            this.iscx = iscx;
        }

        public List<DengjilistBean> getDengjilist() {
            return dengjilist;
        }

        public void setDengjilist(List<DengjilistBean> dengjilist) {
            this.dengjilist = dengjilist;
        }

        public List<?> getPifajia() {
            return pifajia;
        }

        public void setPifajia(List<?> pifajia) {
            this.pifajia = pifajia;
        }

        public List<PacklistBean> getPacklist() {
            return packlist;
        }

        public void setPacklist(List<PacklistBean> packlist) {
            this.packlist = packlist;
        }

        public static class DengjilistBean {
            /**
             * dengji : 一级
             * dengji_value : 5
             * prc : 0.46
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
        }

        public static class PacklistBean {
            /**
             * unit_bilv : 24
             * unitname : 个/包
             * check : 0
             * dengji_price : 13.2000
             * cankao_price : 12.0000
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
        }
    }
}
