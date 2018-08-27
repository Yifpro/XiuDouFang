package com.example.administrator.xiudoufang.stock.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.StockDetailsBean;
import com.example.administrator.xiudoufang.bean.StockListBean;
import com.example.administrator.xiudoufang.bean.TypeListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockLogic {

    //******** 获取库存列表 ********
    public void requestStockList(HashMap<String, String> params, JsonCallback<StockListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("库存列表 -> " + json);
        OkGo.<StockListBean>post(StringUtils.BASE_URL + "/Api/products/Getinvdata?Getinvdata=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取库存详情 ********
    public void requestStockDetails(HashMap<String, String> params, JsonCallback<StockDetailsBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("库存详情 -> " + json);
        OkGo.<StockDetailsBean>post(StringUtils.BASE_URL + "/Api/products/Getsingleproinvdata?Getsingleproinvdata=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取类别列表 ********
    public void requestTypeList(HashMap<String, String> params, JsonCallback<TypeListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("类别列表 -> " + json);
        OkGo.<TypeListBean>get(StringUtils.getUrl("/Api/products/request_producttype?", params))
                .execute(callback);
    }
}
