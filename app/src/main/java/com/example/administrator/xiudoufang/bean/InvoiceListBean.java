package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Administrator on 2018/8/29
 */

public class InvoiceListBean {


    /**
     * status : 1
     * fx_fahuodanlist : [{"id":"34215","issdocref":"FH180414-0001","shiptoid":"3155","shipto":"义乌总店","c_id":"3155","customername":"义乌总店","shouhuodizhi":"工商学院路162号","huoyunzhan":"","packet_man":"莉莉","yunhao":"Asdfasdf","kuaidi_pic":"676495180707092752.png","issDate":"04-14","tel":"0579-85201232","telephone":"18257830102","daifa":"0","daifa_dian":"","crtime":"2018/4/14 10:04:07","crman":"莉莉","realdianid":"45"}]
     */

    private String status;
    private List<InvoiceBean> fx_fahuodanlist;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InvoiceBean> getFx_fahuodanlist() {
        return fx_fahuodanlist;
    }

    public void setFx_fahuodanlist(List<InvoiceBean> fx_fahuodanlist) {
        this.fx_fahuodanlist = fx_fahuodanlist;
    }

    public static class InvoiceBean implements Parcelable {
        /**
         * id : 34215
         * issdocref : FH180414-0001
         * shiptoid : 3155
         * shipto : 义乌总店
         * c_id : 3155
         * customername : 义乌总店
         * shouhuodizhi : 工商学院路162号
         * huoyunzhan :
         * packet_man : 莉莉
         * yunhao : Asdfasdf
         * kuaidi_pic : 676495180707092752.png
         * issDate : 04-14
         * tel : 0579-85201232
         * telephone : 18257830102
         * daifa : 0
         * daifa_dian :
         * crtime : 2018/4/14 10:04:07
         * crman : 莉莉
         * realdianid : 45
         */

        private String id;
        private String issdocref;
        private String shiptoid;
        private String shipto;
        private String c_id;
        private String customername;
        private String shouhuodizhi;
        private String huoyunzhan;
        private String packet_man;
        private String yunhao;
        private String kuaidi_pic;
        private String issDate;
        private String tel;
        private String telephone;
        private String daifa;
        private String daifa_dian;
        private String crtime;
        private String crman;
        private String realdianid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIssdocref() {
            return issdocref;
        }

        public void setIssdocref(String issdocref) {
            this.issdocref = issdocref;
        }

        public String getShiptoid() {
            return shiptoid;
        }

        public void setShiptoid(String shiptoid) {
            this.shiptoid = shiptoid;
        }

        public String getShipto() {
            return shipto;
        }

        public void setShipto(String shipto) {
            this.shipto = shipto;
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

        public String getShouhuodizhi() {
            return shouhuodizhi;
        }

        public void setShouhuodizhi(String shouhuodizhi) {
            this.shouhuodizhi = shouhuodizhi;
        }

        public String getHuoyunzhan() {
            return huoyunzhan;
        }

        public void setHuoyunzhan(String huoyunzhan) {
            this.huoyunzhan = huoyunzhan;
        }

        public String getPacket_man() {
            return packet_man;
        }

        public void setPacket_man(String packet_man) {
            this.packet_man = packet_man;
        }

        public String getYunhao() {
            return yunhao;
        }

        public void setYunhao(String yunhao) {
            this.yunhao = yunhao;
        }

        public String getKuaidi_pic() {
            return kuaidi_pic;
        }

        public void setKuaidi_pic(String kuaidi_pic) {
            this.kuaidi_pic = kuaidi_pic;
        }

        public String getIssDate() {
            return issDate;
        }

        public void setIssDate(String issDate) {
            this.issDate = issDate;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getDaifa() {
            return daifa;
        }

        public void setDaifa(String daifa) {
            this.daifa = daifa;
        }

        public String getDaifa_dian() {
            return daifa_dian;
        }

        public void setDaifa_dian(String daifa_dian) {
            this.daifa_dian = daifa_dian;
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

        public String getRealdianid() {
            return realdianid;
        }

        public void setRealdianid(String realdianid) {
            this.realdianid = realdianid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.issdocref);
            dest.writeString(this.shiptoid);
            dest.writeString(this.shipto);
            dest.writeString(this.c_id);
            dest.writeString(this.customername);
            dest.writeString(this.shouhuodizhi);
            dest.writeString(this.huoyunzhan);
            dest.writeString(this.packet_man);
            dest.writeString(this.yunhao);
            dest.writeString(this.kuaidi_pic);
            dest.writeString(this.issDate);
            dest.writeString(this.tel);
            dest.writeString(this.telephone);
            dest.writeString(this.daifa);
            dest.writeString(this.daifa_dian);
            dest.writeString(this.crtime);
            dest.writeString(this.crman);
            dest.writeString(this.realdianid);
        }

        public InvoiceBean() {
        }

        protected InvoiceBean(Parcel in) {
            this.id = in.readString();
            this.issdocref = in.readString();
            this.shiptoid = in.readString();
            this.shipto = in.readString();
            this.c_id = in.readString();
            this.customername = in.readString();
            this.shouhuodizhi = in.readString();
            this.huoyunzhan = in.readString();
            this.packet_man = in.readString();
            this.yunhao = in.readString();
            this.kuaidi_pic = in.readString();
            this.issDate = in.readString();
            this.tel = in.readString();
            this.telephone = in.readString();
            this.daifa = in.readString();
            this.daifa_dian = in.readString();
            this.crtime = in.readString();
            this.crman = in.readString();
            this.realdianid = in.readString();
        }

        public static final Creator<InvoiceBean> CREATOR = new Creator<InvoiceBean>() {
            @Override
            public InvoiceBean createFromParcel(Parcel source) {
                return new InvoiceBean(source);
            }

            @Override
            public InvoiceBean[] newArray(int size) {
                return new InvoiceBean[size];
            }
        };
    }
}
