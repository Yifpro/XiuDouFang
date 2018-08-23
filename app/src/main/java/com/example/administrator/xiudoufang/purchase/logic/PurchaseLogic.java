package com.example.administrator.xiudoufang.purchase.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.PostRequest;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(HashMap<String, String> params, JsonCallback<PurchaseListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("采购单列表 -> " + json);
        OkGo.<PurchaseListBean>post(StringUtils.BASE_URL + "/api/products/Getpuomstrlistsdata?Getpuomstrlistsdata=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取采购单明细 ********
    public void requestPurchaseDetails(String orderId, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("iid", orderId);
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        String json = JSONObject.toJSONString(map);
        LogUtils.e("json - > "+json);
        OkGo.<String>post(StringUtils.getUrl("/Api/products/GetSinglepuomstrdata?iid=0", map))
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    public void requestActionForOrder(HashMap<String, String> params, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Actionfor_poorder?Actionfor_poorder=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }
}
