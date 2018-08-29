package com.example.administrator.xiudoufang.bean;

/**
 * Created by Administrator on 2018/8/28
 */

public class PicBean {

    private Object pic;
    private boolean isLocal;

    public PicBean(Object pic, boolean isLocal) {
        this.pic = pic;
        this.isLocal = isLocal;
    }

    public Object getPic() {
        return pic;
    }

    public void setPic(Object pic) {
        this.pic = pic;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}
