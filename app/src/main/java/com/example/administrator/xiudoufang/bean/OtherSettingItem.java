package com.example.administrator.xiudoufang.bean;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSettingItem {

    private String key;
    private String value;
    private boolean isSelected;

    public OtherSettingItem(String key, String value, boolean isSelected) {
        this.key = key;
        this.value = value;
        this.isSelected = isSelected;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
