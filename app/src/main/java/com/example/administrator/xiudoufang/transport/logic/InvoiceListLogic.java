package com.example.administrator.xiudoufang.transport.logic;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.InvoiceListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/29
 */

public class InvoiceListLogic {

    //******** 获取库存列表 ********
    public void requestInvoiceList(HashMap<String, String> params, JsonCallback<InvoiceListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("发货单列表 -> " + json);
        OkGo.<InvoiceListBean>get(StringUtils.getUrl("/Api/products/requset_fahuodan?", params))
                .execute(callback);
    }
}
