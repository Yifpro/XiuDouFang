package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.stock.adapter.StockDetailsAdapter;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockDetailsItem implements MultiItemEntity {

    private String key;
    private String value;

    public StockDetailsItem(String key, String value) {
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
        return StockDetailsAdapter.TYPE_LEVEL_1;
    }
}
