package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.stock.adapter.StockDetailsAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockDetailsBean {


    /**
     * status : 1
     * message : 请求成功
     * cpid : 8386
     * styleno : 10007001
     * stylename : 直板鱼型密齿梳卡通
     * classname : 秀逗密齿梳
     * xinghao :
     * tiaoxingma : 6948154800074
     * pinpai : 秀逗
     * detail :
     * suppstr : 谢奕高
     * dianinvproducts : [{"dianid":"45","dianname":"广州分部","cpid":"8386","styleno":"10007001","invnum":"0","alcnum":"0","usenum":"0","useunitnum":"0","unitname":"个","warehousestr":"成品总仓"}]
     * pics : [{"pic":"1000700101.png"},{"pic":"10007001侧面.png"},{"pic":"10007001正面.png"},{"pic":"10007001正面规格.png"}]
     */

    private String status;
    private String message;
    private String cpid;
    private String styleno;
    private String stylename;
    private String classname;
    private String xinghao;
    private String tiaoxingma;
    private String pinpai;
    private String detail;
    private String suppstr;
    private List<DianinvproductsBean> dianinvproducts;
    private List<PicsBean> pics;

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

    public String getSuppstr() {
        return suppstr;
    }

    public void setSuppstr(String suppstr) {
        this.suppstr = suppstr;
    }

    public List<DianinvproductsBean> getDianinvproducts() {
        return dianinvproducts;
    }

    public void setDianinvproducts(List<DianinvproductsBean> dianinvproducts) {
        this.dianinvproducts = dianinvproducts;
    }

    public List<PicsBean> getPics() {
        return pics;
    }

    public void setPics(List<PicsBean> pics) {
        this.pics = pics;
    }

    public static class DianinvproductsBean implements MultiItemEntity {
        /**
         * dianid : 45
         * dianname : 广州分部
         * cpid : 8386
         * styleno : 10007001
         * invnum : 0
         * alcnum : 0
         * usenum : 0
         * useunitnum : 0
         * unitname : 个
         * warehousestr : 成品总仓
         */

        private String dianid;
        private String dianname;
        private String cpid;
        private String styleno;
        private String invnum;
        private String alcnum;
        private String usenum;
        private String useunitnum;
        private String unitname;
        private String warehousestr;

        @Override
        public int getItemType() {
            return StockDetailsAdapter.TYPE_LEVEL_1;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getDianname() {
            return dianname;
        }

        public void setDianname(String dianname) {
            this.dianname = dianname;
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

        public String getWarehousestr() {
            return warehousestr;
        }

        public void setWarehousestr(String warehousestr) {
            this.warehousestr = warehousestr;
        }
    }

    public static class PicsBean {
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
