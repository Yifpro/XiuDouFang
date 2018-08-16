package com.example.administrator.xiudoufang.purchase.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import java.util.HashMap;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(HashMap<String, String> params, Callback<PurchaseListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("json -> "+json);
        OkGo.<PurchaseListBean>post(StringUtils.BASE_URL + "/api/products/Getpuomstrlistsdata?Getpuomstrlistsdata=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }
}
