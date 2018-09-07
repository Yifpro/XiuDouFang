package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.stock.adapter.StockDetailsAdapter;

/**
 * Created by Administrator on 2018/9/7
 */

public class ImgBean implements MultiItemEntity {

    private String img;

    public ImgBean(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int getItemType() {
        return StockDetailsAdapter.TYPE_LEVEL_0;
    }
}
