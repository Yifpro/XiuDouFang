package com.example.administrator.xiudoufang.open.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/9/1
 */

public class SalesOrderLogic {

    //******** 获取产品列表 ********
    public void requestProductList(HashMap<String, String> map, JsonCallback<SalesProductListBean> callback) {
        OkGo.<SalesProductListBean>get(StringUtils.getUrl("/Api/products/requset_cplist?", map))
                .execute(callback);
    }

    //******** 提交订单 ********
    public void saveOrCreateOrder(HashMap<String, String> params, String path, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("提交订单 -> " + json);
//        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/postorderall?postiorder=0")
//                .headers("Content-Type", "application/json")
//                .upJson(json)
//                .params("fujian", new File(path))
//                .execute(callback);
    }
}
