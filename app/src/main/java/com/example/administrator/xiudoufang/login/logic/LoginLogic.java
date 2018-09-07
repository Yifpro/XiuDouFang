package com.example.administrator.xiudoufang.login.logic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ThreadUtils;
import com.example.administrator.xiudoufang.common.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import java.util.HashMap;

public class LoginLogic {

    //******** 登录请求 ********
    public void requestLogin(Context context, HashMap<String, String> map, Callback<String> callback) {
        if (TextUtils.isEmpty(map.get("username"))) {
            ToastUtils.show(context, "请输入用户名");
        } else if (TextUtils.isEmpty(map.get("password"))) {
            ToastUtils.show(context, "请输入密码");
        } else {
            OkGo.<String>get(StringUtils.getUrl("/Api/products/login_worker?", map))
                    .tag(context)
                    .execute(callback);
        }
    }

    //******** 获取验证码 ********
    public void requestVerificationCode(Context context, String userid, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", userid);
        OkGo.<String>get(StringUtils.getUrl("/Api/products/LoginGetphonecode?", map))
                .tag(context)
                .execute(callback);
    }

    //******** 检查是否需要验证码 ********
    public void checkVerificationCode(Context context, HttpParams httpParams, Callback<String> callback) {
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/UserLogincheck?loginaction=0")
                .tag(context)
                .params(httpParams)
                .execute(callback);
    }
}
