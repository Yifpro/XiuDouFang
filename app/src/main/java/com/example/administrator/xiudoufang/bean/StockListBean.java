package com.example.administrator.xiudoufang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockListBean {


    /**
     * status : 1
     * message : 请求成功
     * invlists : [{"cpid":"8386","styleno":"10007001","stylename":"直板鱼型密齿梳卡通","pic":"1000700101.png","invnum":"1","alcnum":"0","usenum":"1","useunitnum":"1","unitname":"个","piclist":[{"pic":"1000700101.png"},{"pic":"10007001侧面.png"},{"pic":"10007001正面.png"},{"pic":"10007001正面规格.png"}]}]
     */

    private String status;
    private String message;
    private List<StockBean> invlists;

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

    public List<StockBean> getInvlists() {
        return invlists;
    }

    public void setInvlists(List<StockBean> invlists) {
        this.invlists = invlists;
    }

    public static class StockBean {
        /**
         * cpid : 8386
         * styleno : 10007001
         * stylename : 直板鱼型密齿梳卡通
         * pic : 1000700101.png
         * invnum : 1
         * alcnum : 0
         * usenum : 1
         * useunitnum : 1
         * unitname : 个
         * piclist : [{"pic":"1000700101.png"},{"pic":"10007001侧面.png"},{"pic":"10007001正面.png"},{"pic":"10007001正面规格.png"}]
         */

        private String cpid;
        private String styleno;
        private String stylename;
        private String pic;
        private String invnum;
        private String alcnum;
        private String usenum;
        private String useunitnum;
        private String unitname;
        private List<PiclistBean> piclist;

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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getInvnum() {
            return invnum;
        }

        public void setInvnum(String invnum) {
            this.invnum = invnum;
        }

        public String getAlcnum() {
            return alcnum;
        }

        public void setAlcnum(String alcnum) {
            this.alcnum = alcnum;
        }

        public String getUsenum() {
            return usenum;
        }

        public void setUsenum(String usenum) {
            this.usenum = usenum;
        }

        public String getUseunitnum() {
            return useunitnum;
        }

        public void setUseunitnum(String useunitnum) {
            this.useunitnum = useunitnum;
        }

        public String getUnitname() {
            return unitname;
        }

        public void setUnitname(String unitname) {
            this.unitname = unitname;
        }

        public List<PiclistBean> getPiclist() {
            return piclist;
        }

        public void setPiclist(List<PiclistBean> piclist) {
            this.piclist = piclist;
        }

        public static class PiclistBean {
            /**
             * pic : 1000700101.png
             */

            private String pic;

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }
    }
}
