package com.example.administrator.xiudoufang.receipt.logic;

import android.content.SharedPreferences;

import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.bean.SubjectBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import java.util.HashMap;

import okhttp3.OkHttpClient;

public class CustomerListLogic {

    //******** 提交收付款 ********
    public void requestReceipt(HttpParams params, Callback<String> callback) {
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/requset_custhuokuan?tempaction=0")
                .params(params)
                .execute(callback);
    }

    //******** 获取客户列表 ********
    public void requestSubjectList(String action, Callback<SubjectBean> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("action", action);
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        OkGo.<SubjectBean>get(StringUtils.getUrl("/Api/products/requset_accounttype_data?", map))
                .execute(callback);
    }

    public void requestCustomerList(HashMap<String, String> map, Callback<String> callback) {
        OkGo.<String>get(StringUtils.getUrl("/Api/products/requset_custlist?", map))
                .execute(callback);
    }
    //******** 获取会计科目列表 ********
}
