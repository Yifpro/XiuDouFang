package com.example.administrator.xiudoufang.purchase.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

import java.util.HashMap;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(String[] arr, Callback<String> callback) {
        SharedPreferences preferences = PreferencesUtils.getPreferences();
        HttpParams params = new HttpParams();
        params.put("iid ", arr[0]);
        params.put("dianid ", preferences.getString(PreferencesUtils.DIAN_ID, ""));
        params.put("PuOrderNo ", arr[1]);
        params.put("suppno ", arr[2]);
        params.put("Suppname ", arr[3]);
        params.put("starttime ", arr[4]);
        params.put("endtime ", arr[5]);
        params.put("etadate ", arr[6]);
        params.put("crman ", arr[7]);
        params.put("queren_man", arr[8]);
        params.put("quyuno ", arr[9]);
        params.put("quyu ", arr[10]);
        params.put("fujia_memo", arr[11]);
        params.put("remark ", arr[12]);
        params.put("userid ", preferences.getString(PreferencesUtils.USER_ID, ""));
        params.put("pagenum ", arr[13]);
        params.put("count ", arr[14]);
        params.put("status_str", arr[15]);
        params.put("fromorder", arr[16]);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Getpuomstrlistsdata")
                .params(params)
                .execute(callback);
    }
}
