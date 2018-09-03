package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.SalesProductDetailsAdapter;

/**
 * Created by Administrator on 2018/9/3
 */

public class SalesProductDetailsItem_0 implements MultiItemEntity {

    private String url;

    public SalesProductDetailsItem_0(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int getItemType() {
        return SalesProductDetailsAdapter.LAYOUT_IMG;
    }
}
