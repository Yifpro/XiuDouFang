package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.BaseOrderInfo;
import com.example.administrator.xiudoufang.open.adapter.ConfirmOrderInfoAdapter;

/**
 * Created by Administrator on 2018/9/4
 */

public class OrderInfo_2 implements MultiItemEntity, BaseOrderInfo {

    private String key;
    private String value;

    public OrderInfo_2(String key) {
        this.key = key;
    }

    public OrderInfo_2(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemType() {
        return ConfirmOrderInfoAdapter.TYPE_EDIT;
    }
}
