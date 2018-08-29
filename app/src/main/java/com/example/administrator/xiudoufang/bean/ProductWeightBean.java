package com.example.administrator.xiudoufang.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductWeightBean {

    private String title;
    private List<String> subItem;

    public ProductWeightBean(String title) {
        this.title = title;
        subItem = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubItem() {
        return subItem;
    }

    public void setSubItem(List<String> subItem) {
        this.subItem = subItem;
    }
}
