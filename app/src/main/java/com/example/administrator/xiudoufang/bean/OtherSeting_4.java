package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_4 implements MultiItemEntity {
    private String title;
    private String content;

    public OtherSeting_4(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return OtherSettingAdapter.LAYOUT_FOUTH;
    }
}
