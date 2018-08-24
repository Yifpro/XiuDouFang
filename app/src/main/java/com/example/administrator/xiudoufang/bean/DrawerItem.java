package com.example.administrator.xiudoufang.bean;

/**
 * Created by Administrator on 2018/8/20
 */

public class DrawerItem {

    private String title;
    private boolean isSelected;

    public DrawerItem() {
    }

    public DrawerItem(String title, boolean isSelected) {
        this.title = title;
        this.isSelected = isSelected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
