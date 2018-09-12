package com.example.administrator.xiudoufang.purchase.logic;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.SupplierListBean;
import com.example.administrator.xiudoufang.bean.WarehouseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/8/17
 */

public class NewPurchaseOrderLogic {

    //******** 提交采购单 ********
    public void requestPostPurchaseOrder(Context context, HashMap<String, String> params, String path, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("采购单提交 -> " + json);
        HttpParams p = new HttpParams();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            p.put(entry.getKey(), entry.getValue());
        }
        if (path != null)
            p.put("fujian", new File(path));
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/postpoorderall?postpoorder=0")
                .tag(context)
                .params(p)
                .execute(callback);
    }


    //******** 获取供应商列表 ********
    public void requestSupplierList(Context context, HashMap<String, String> params, JsonCallback<SupplierListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("供应商列表 -> " + json);
        OkGo.<SupplierListBean>post(StringUtils.BASE_URL + "/Api/products/requset_suppdata?requset_suppdata=0")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取仓位列表 ********
    public void requestWarehouseList(Context context, JsonCallback<WarehouseListBean> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        map.put("house", "0");
        OkGo.<WarehouseListBean>get(StringUtils.getUrl("/Api/products/Gethouselists?", map))
                .tag(context)
                .execute(callback);
    }

    //******** 获取产品列表 ********
    public void requestProductList(Context context, HashMap<String, String> params, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("产品列表 -> " + json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/requset_poproductdata?requset_poproductdata=0")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    public List<ProductItem> parseProductListJson(JSONObject jsonObject) {
        ArrayList<ProductItem> list = new ArrayList<>();
        JSONArray po_chanpinlist = jsonObject.getJSONArray("po_chanpinlist");
        for (int i = 0; i < po_chanpinlist.size(); i++) {
            JSONObject object = po_chanpinlist.getJSONObject(i);
            ProductItem productItem = new ProductItem();
            productItem.setCpid(object.getString("dianid"));
            productItem.setCpid(object.getString("cpid"));
            productItem.setCpid(object.getString("styleno"));
            productItem.setCpid(object.getString("barcode"));
            productItem.setCpid(object.getString("stylename"));
            productItem.setCpid(object.getString("classname"));
            productItem.setCpid(object.getString("photourl"));
            productItem.setCpid(object.getString("xinghao"));
            productItem.setCpid(object.getString("pinpai"));
            productItem.setCpid(object.getString("detail"));
            productItem.setCpid(object.getString("kucunqty"));
            productItem.setCpid(object.getString("ziyouqty"));
            productItem.setCpid(object.getString("stop_produce"));
            productItem.setCpid(object.getString("stop_sales"));
            productItem.setCpid(object.getString("pounitname"));
            JSONArray colorlist = object.getJSONArray("colorlist");
            List<ProductItem.ColorlistBean> colorlistBeen = null;
            if (colorlist.size() > 0) {
                colorlistBeen = new ArrayList<>();
                for (int j = 0; j < colorlist.size(); j++) {
                    String color = colorlist.getJSONObject(j).getString("color");
                    colorlistBeen.add(new ProductItem.ColorlistBean(color));
                }
            }
            productItem.setColorlist(colorlistBeen);
            List<ProductItem.ColorlistBean> packlistBeen = null;
            JSONArray packlist = object.getJSONArray("packlist");
            if (packlist.size() > 0) {
                packlistBeen = new ArrayList<>();
                for (int j = 0; j < packlist.size(); j++) {
                    JSONObject pack = colorlist.getJSONObject(i);
                    ProductItem.PacklistBean packlistBean = new ProductItem.PacklistBean(pack.getString("unit_bilv"), pack.getString("unitname"), pack.getString("check"));
                    packlistBeen.add(packlistBean);
                }
            }
            productItem.setPacklist(packlistBeen);
            productItem.setSizxlist(object.getString("sizxlist"));
            productItem.setPiclist(object.getString("piclist"));
            productItem.setLishijialist(object.getString("lishijialist"));
            productItem.setChengbenjialist(object.getString("chengbenjialist"));
            productItem.setCankaoshoujialist(object.getString("cankaoshoujialist"));
            productItem.setChuchangjialist(object.getString("chuchangjialist"));
        }
        return list;
    }

}
