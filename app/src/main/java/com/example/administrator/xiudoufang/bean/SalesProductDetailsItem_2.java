package com.example.administrator.xiudoufang.bean;

import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/3
 */

public class SalesProductDetailsItem_2 implements MultiItemEntity {

    private String key;
    private String value;
    private boolean isText;
    private ArrayList<String> list;
    private View.OnClickListener listener;


    public SalesProductDetailsItem_2() {
    }

    public SalesProductDetailsItem_2(String key, String value, boolean isText) {
        this.key = key;
        this.value = value;
        this.isText = isText;
    }

    public SalesProductDetailsItem_2(String key, String value, boolean isText, View.OnClickListener listener) {
        this.key = key;
        this.value = value;
        this.isText = isText;
        this.listener = listener;
    }

    public SalesProductDetailsItem_2(String key, String value, ArrayList<String> list, boolean isText) {
        this.key = key;
        this.value = value;
        this.list = list;
        this.isText = isText;
    }

    public SalesProductDetailsItem_2(String key, String value, ArrayList<String> list, boolean isText, View.OnClickListener listener) {
        this.key = key;
        this.value = value;
        this.isText = isText;
        this.list = list;
        this.listener = listener;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public boolean isText() {
        return isText;
    }

    public void setText(boolean text) {
        isText = text;
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
        return SalesProductDetailsAdapter.TYPE_SECOND;
    }
}
