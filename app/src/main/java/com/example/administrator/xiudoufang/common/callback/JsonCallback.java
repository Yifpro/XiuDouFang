package com.example.administrator.xiudoufang.common.callback;

import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.base.XiuDouFangApplication;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.example.administrator.xiudoufang.common.widget.LoadingViewDialog;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    public JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;
        T data;
        String text = body.string();
        LogUtils.e("parse before -> " + JSONObject.parseObject(text, String.class));
        if (type != null) {
            data = JSONObject.parseObject(text, type);
        } else if (clazz != null) {
            data = JSONObject.parseObject(text, clazz);
        } else {
            Type genType = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            data = JSONObject.parseObject(text, type);
        }
        return data;
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        LoadingViewDialog.getInstance().dismiss();
        Throwable exception = response.getException();
        String error = "";
        if (exception != null) {
            error = exception.getMessage();
        }
        ToastUtils.show(XiuDouFangApplication.getContext(), "网络异常" + response.body() + error);
        super.onError(response);
    }
}
