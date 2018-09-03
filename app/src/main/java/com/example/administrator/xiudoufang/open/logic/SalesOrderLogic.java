package com.example.administrator.xiudoufang.open.logic;

import com.example.administrator.xiudoufang.bean.SalesProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;

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
}
