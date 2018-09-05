package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_3 implements MultiItemEntity {
    private String title;
    private String value;
    private String hint;

    public OtherSeting_3(String title, String value, String hint) {
        this.title = title;
        this.value = value;
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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
        return OtherSettingAdapter.LAYOUT_THIRD;
    }
}
