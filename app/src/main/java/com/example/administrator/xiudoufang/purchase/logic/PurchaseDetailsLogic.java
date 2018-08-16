package com.example.administrator.xiudoufang.purchase.logic;

import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/16
 */

public class PurchaseDetailsLogic {

    //******** 获取采购单明细 ********
    public void requestPurchaseDetails(String orderId, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("iid", orderId);
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        OkGo.<String>get(StringUtils.getUrl("/Api/products/GetSinglepuomstrdata", map))
                .execute(callback);
    }
}
