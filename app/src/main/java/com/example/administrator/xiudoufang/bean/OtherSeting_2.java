package com.example.administrator.xiudoufang.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSeting_2 implements MultiItemEntity {
    private String title;
    private List<String> list;
    private String value;

    public OtherSeting_2(String title, List<String> list, String value) {
        this.title = title;
        this.list = list;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public int getItemType() {
        return OtherSettingAdapter.TYPE_SECOND;
    }
}
