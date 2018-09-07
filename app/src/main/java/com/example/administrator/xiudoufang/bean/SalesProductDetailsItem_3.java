package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/3
 */

public class SalesProductDetailsItem_3 implements MultiItemEntity {

    private String key;
    private String value;
    private ArrayList<String> list;

    public SalesProductDetailsItem_3(String key, String value, ArrayList<String> list) {
        this.key = key;
        this.value = value;
        this.list = list;
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

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public int getItemType() {
        return SalesProductDetailsAdapter.TYPE_THIRD;
    }
}
