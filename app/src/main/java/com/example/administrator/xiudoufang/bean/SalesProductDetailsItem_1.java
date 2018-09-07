package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

/**
 * Created by Administrator on 2018/9/3
 */

public class SalesProductDetailsItem_1 implements MultiItemEntity {

    private String key;
    private String value;

    public SalesProductDetailsItem_1() {
    }

    public SalesProductDetailsItem_1(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemType() {
        return SalesProductDetailsAdapter.TYPE_FIRST;
    }
}
