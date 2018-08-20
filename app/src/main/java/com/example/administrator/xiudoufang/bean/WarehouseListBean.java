package com.example.administrator.xiudoufang.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/8/17
 */

public class WarehouseListBean {

    /**
     * status : 1
     * message : 请求成功
     * houselists : [{"id":"43","sn":"1号仓","dianid":"45","fuji":"42"},{"id":"44","sn":"2号仓","dianid":"45","fuji":"42"},{"id":"40","sn":"仓库","dianid":"45","fuji":"35"},{"id":"35","sn":"仓库汇总","dianid":"45","fuji":"0"},{"id":"42","sn":"成品总仓","dianid":"45","fuji":"40"},{"id":"36","sn":"废品仓","dianid":"45","fuji":"35"},{"id":"39","sn":"广州储备仓","dianid":"45","fuji":"35"},{"id":"41","sn":"散盒仓","dianid":"45","fuji":"35"},{"id":"38","sn":"样品仓","dianid":"45","fuji":"35"},{"id":"37","sn":"样品室","dianid":"45","fuji":"35"}]
     */

    private String status;
    private String message;
    private List<WarehouseBean> houselists;

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

    public List<WarehouseBean> getHouselists() {
        return houselists;
    }

    public void setHouselists(List<WarehouseBean> houselists) {
        this.houselists = houselists;
    }

    public static class WarehouseBean {
        /**
         * id : 43
         * sn : 1号仓
         * dianid : 45
         * fuji : 42
         */

        private String id;
        private String sn;
        private String dianid;
        private String fuji;
        private boolean isSelcted;

        public WarehouseBean() {
        }

        public WarehouseBean(String id) {
            this.id = id;
        }

        public boolean isSelcted() {
            return isSelcted;
        }

        public void setSelcted(boolean selcted) {
            isSelcted = selcted;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getDianid() {
            return dianid;
        }

        public void setDianid(String dianid) {
            this.dianid = dianid;
        }

        public String getFuji() {
            return fuji;
        }

        public void setFuji(String fuji) {
            this.fuji = fuji;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof WarehouseBean) {
                return ((WarehouseBean) obj).getId().equals(getId());
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = 37;
            result += 17 * id.hashCode();
            result += 17 * sn.hashCode();
            return result;
        }
    }
}
