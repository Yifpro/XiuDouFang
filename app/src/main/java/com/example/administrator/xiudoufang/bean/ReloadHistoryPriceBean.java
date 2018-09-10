package com.example.administrator.xiudoufang.bean;

/**
 * Created by Administrator on 2018/9/10
 */

public class ReloadHistoryPriceBean {

    private String unit_bilv;
    private String cpid;

    public ReloadHistoryPriceBean(String unit_bilv, String cpid) {
        this.unit_bilv = unit_bilv;
        this.cpid = cpid;
    }

    public String getUnit_bilv() {
        return unit_bilv;
    }

    public void setUnit_bilv(String unit_bilv) {
        this.unit_bilv = unit_bilv;
    }

    public String getCpid() {
        return cpid;
    }

    public void setCpid(String cpid) {
        this.cpid = cpid;
    }
}
