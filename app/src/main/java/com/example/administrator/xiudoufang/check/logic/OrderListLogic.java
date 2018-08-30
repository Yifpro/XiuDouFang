package com.example.administrator.xiudoufang.check.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.OrderListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
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
}
