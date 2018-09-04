package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_2 implements MultiItemEntity {
    private String title;
    private List<String> content;

    public OtherSeting_2(String title, List<String> content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return OtherSettingAdapter.LAYOUT_SECOND;
    }
}
