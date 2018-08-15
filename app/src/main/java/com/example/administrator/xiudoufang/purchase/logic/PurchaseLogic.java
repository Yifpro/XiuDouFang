package com.example.administrator.xiudoufang.purchase.logic;

import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(HttpParams params, Callback<String> callback) {
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Getpuomstrlistsdata?Getpuomstrlistsdata=0")
                .params(params)
                .execute(callback);
    }
}
