package com.example.administrator.xiudoufang.bean;

public class ServerItem {

    private int textColorRes;
    private int backgroundRes;
    private String text;

    public ServerItem(int textColorRes, int backgroundRes, String text) {
        this.textColorRes = textColorRes;
        this.backgroundRes = backgroundRes;
        this.text = text;
    }

    public int getTextColorRes() {
        return textColorRes;
    }

    public void setTextColorRes(int textColorRes) {
        this.textColorRes = textColorRes;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
