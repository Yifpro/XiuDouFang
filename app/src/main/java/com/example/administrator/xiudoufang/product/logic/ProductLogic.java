package com.example.administrator.xiudoufang.product.logic;

import android.app.Activity;

import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.ProductDetailsBean;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.PostRequest;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductLogic {

    //******** 获取产品列表 ********
    public void requestProductList(HashMap<String, String> params, JsonCallback<ProductListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("产品列表 -> " + json);
        OkGo.<ProductListBean>post(StringUtils.BASE_URL + "/Api/products/request_Postcppic?request_Postcppic=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取产品明细 ********
    public void requestProductDetails(HashMap<String, String> params, JsonCallback<ProductDetailsBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("产品明细 -> " + json);
        OkGo.<ProductDetailsBean>get(StringUtils.getUrl("/Api/products/request_cpprcice?", params))
                .execute(callback);
    }

    //******** 上传产品图片 ********
    public void uploadProductPic(String cpid, List<File> files, StringCallback callback) {
        HttpParams params = new HttpParams();
        params.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        params.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        params.put("cpid", cpid);
        params.put("zongdianid", JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO)).getString("zongdianid"));
        params.put("dianidstr", "");
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/upload_productsimage?dqdianid=0&dqcpid=0&dquserid=0")
                .params(params)
                .addFileParams("fujian", files)
                .execute(callback);
    }
}
