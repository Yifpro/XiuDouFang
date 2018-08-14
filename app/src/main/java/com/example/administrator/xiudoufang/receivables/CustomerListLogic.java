package com.example.administrator.xiudoufang.receivables;

import com.example.administrator.xiudoufang.bean.CustomerBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;

import java.util.HashMap;

public class CustomerListLogic {

    public void requestCustomerList(String[] parameters, Callback<CustomerBean> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", parameters[0]);
        map.put("userdengji", parameters[1]);
        map.put("dqc_id", parameters[2]);
        map.put("search", parameters[3]);
        map.put("pagenum", parameters[4]);
        map.put("count", parameters[5]);
        map.put("userid", parameters[6]);
        OkGo.<CustomerBean>get(StringUtils.getUrl("/Api/products/requset_custlist?", map))
                .execute(callback);
    }
}
