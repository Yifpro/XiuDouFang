package com.example.administrator.xiudoufang.check.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.OrderDetailsBean;
import com.example.administrator.xiudoufang.bean.OrderListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/30
 */

public class OrderListLogic {

    //******** 获取订单列表 ********
    public void requestOrderList(HashMap<String, String> params, JsonCallback<OrderListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("订单列表 -> " + json);
        OkGo.<OrderListBean>post(StringUtils.BASE_URL + "/Api/products/requset_orderlists?ordertemp=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 对应动作更改订单 ********
    public void requestActionForOrder(HashMap<String, String> params, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("动作 -> " + json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Actionfor_order?Actionfor_order=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取订单明细 ********
    public void requestOrderDetails(String iid, String c_id, JsonCallback<OrderDetailsBean> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params.put("iid", iid);
        params.put("c_id", c_id);
        String json = JSONObject.toJSONString(params);
        LogUtils.e("订单明细 -> " + json);
        OkGo.<OrderDetailsBean>post(StringUtils.BASE_URL + "/Api/products/requset_orderdetail?requset_orderdetail=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }
}
