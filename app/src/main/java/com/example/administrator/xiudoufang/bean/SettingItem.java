package com.example.administrator.xiudoufang.bean;

public class SettingItem {

    private String key;
    private String value;
    private boolean isShowLine;
    private boolean isShowNext;
    private boolean isToogleButton;

    public SettingItem(String key) {
        this.key = key;
    }

    public SettingItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public SettingItem(String key, String value, boolean isShowLine) {
        this.key = key;
        this.value = value;
        this.isShowLine = isShowLine;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SettingItem) {
            SettingItem s2 = (SettingItem) obj;
            return this.key.equals(s2.key);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + key.hashCode();
        result = 37 * result + value.hashCode();
        return result;
    }
}
