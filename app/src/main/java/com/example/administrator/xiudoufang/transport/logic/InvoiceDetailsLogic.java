package com.example.administrator.xiudoufang.transport.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/29
 */

public class InvoiceDetailsLogic {

    //******** 删除发货单运号 ********
    public void deleteTransportNum(String invoiceId, StringCallback callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("fahuodianid", invoiceId);
        params.put("tempd", "0");
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        OkGo.<String>get(StringUtils.getUrl("/Api/products/del_fahuoyunhao?", params))
                .execute(callback);
    }

    //******** 修改发货单运号 ********
    public void changeTransportNum(String invoiceId, String transportNum, String path, StringCallback callback) {
        HttpParams params = new HttpParams();
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params.put("fahuodanid", invoiceId);
        params.put("yunhao", transportNum);
        if (path != null)
            params.put("fujian", new File(path));
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/updateyunhao_action?tempactiona=0&tempactionb=0&tempactionc=0")
                .params(params)
                .execute(callback);
    }
}
