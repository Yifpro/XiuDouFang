package com.example.administrator.xiudoufang.bean;

public class SettingItem {

    private String key;
    private String value;
    private boolean isShowLine;
    private boolean isShowNext;
    private boolean isToogleButton;

    public SettingItem(String key, String value) {
        this.key = key;
        this.value = value;
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

    public boolean isShowLine() {
        return isShowLine;
    }

    public SettingItem setShowLine(boolean showLine) {
        isShowLine = showLine;
        return this;
    }

    public boolean isShowNext() {
        return isShowNext;
    }

    public SettingItem setShowNext(boolean showNext) {
        isShowNext = showNext;
        return this;
    }

    public boolean isToogleButton() {
        return isToogleButton;
    }

    public SettingItem setToogleButton(boolean toogleButton) {
        isToogleButton = toogleButton;
        return this;
    }
}
