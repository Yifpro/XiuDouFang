package com.example.administrator.xiudoufang.login.logic;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.xiudoufang.Login;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import org.w3c.dom.Text;

import java.util.HashMap;

public class LoginLogic {

    public void requestLogin(Context context, String username, String password, String logdianid, String phonecode, String changedian, Callback<Login> callback) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(context, "请输入用户名", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
        } else {
            HashMap<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            map.put("logdianid", logdianid);
            map.put("phonecode", phonecode);
            map.put("changedian", changedian);
            OkGo.<Login>get(StringUtils.getUrl("/Api/products/login_worker?", map))
                    .execute(callback);
        }
    }

    public void requestVerificationCode(String userid, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", userid);
        OkGo.<String>get(StringUtils.getUrl("/Api/products/LoginGetphonecode?", map))
                .execute(callback);
    }

    public void checkVerificationCode(String username, String password, String shoujino, Callback<String> callback) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("username", username);
        httpParams.put("password", password);
        httpParams.put("shoujino", shoujino);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/UserLogincheck?loginaction=0")
                .params(httpParams)
                .execute(callback);
    }
}
