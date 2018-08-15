package com.example.administrator.xiudoufang.login.logic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.utils.ThreadUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import java.util.HashMap;

public class LoginLogic {

    //******** 登录请求 ********
    public void requestLogin(Context context, HashMap<String, String> map, Callback<String> callback) {
        if (TextUtils.isEmpty(map.get("username"))) {
            Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(map.get("password"))) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            OkGo.<String>get(StringUtils.getUrl("/Api/products/login_worker?", map))
                    .execute(callback);
        }
    }

    //******** 获取验证码 ********
    public void requestVerificationCode(String userid, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", userid);
        OkGo.<String>get(StringUtils.getUrl("/Api/products/LoginGetphonecode?", map))
                .execute(callback);
    }

    //******** 检查是否需要验证码 ********
    public void checkVerificationCode(HttpParams httpParams, Callback<String> callback) {
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/UserLogincheck?loginaction=0")
                .params(httpParams)
                .execute(callback);
    }

    public void cacheLoginInfo(Context context, String response) {
        StringUtils.cacheInfoToFile(response, StringUtils.LOGIN_INFO);
    }

    private static class InnerTask extends ThreadUtils.SimpleTask {

        private Context mContext;
        private String mResponse;

        InnerTask(Context context, String response) {
            mContext = context;
            mResponse = response;
        }

        @Nullable
        @Override
        public Void doInBackground() throws Throwable {
            StringUtils.cacheInfoToFile(mResponse, StringUtils.LOGIN_INFO);
            return null;
        }

        @Override
        public void onSuccess(@Nullable Object result) {

        }
    }
}
