package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_4 implements MultiItemEntity {
    private String title;
    private String subTitle;
    private String value;
    private boolean isOnlyShowSubTitle;

    public OtherSeting_4(String title, String subTitle, String value, boolean isOnlyShowSubTitle) {
        this.title = title;
        this.subTitle = subTitle;
        this.value = value;
        this.isOnlyShowSubTitle = isOnlyShowSubTitle;
    }

    public boolean isOnlyShowSubTitle() {
        return isOnlyShowSubTitle;
    }

    public void setOnlyShowSubTitle(boolean onlyShowSubTitle) {
        isOnlyShowSubTitle = onlyShowSubTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getItemType() {
        return OtherSettingAdapter.LAYOUT_FOUTH;
    }
}
