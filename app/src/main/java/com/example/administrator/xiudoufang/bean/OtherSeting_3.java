package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_3 implements MultiItemEntity {
    private String title;
    private String content;
    private String hint;

    public OtherSeting_3(String title, String content, String hint) {
        this.title = title;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return OtherSettingAdapter.LAYOUT_THIRD;
    }
}
