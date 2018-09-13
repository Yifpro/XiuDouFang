package com.example.administrator.xiudoufang.purchase.logic;

import android.content.Context;
import android.text.TextUtils;

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

    //******** 解析产品列表返回的json ********
    public ArrayList<ProductItem> parseProductListJson(JSONObject jsonObject) {
        ArrayList<ProductItem> list = new ArrayList<>();
        JSONArray po_chanpinlist = jsonObject.getJSONArray("po_chanpinlist");
        for (int i = 0; i < po_chanpinlist.size(); i++) {
            JSONObject object = po_chanpinlist.getJSONObject(i);
            ProductItem item = new ProductItem();
            item.setDianid(object.getString("dianid"));
            item.setCpid(object.getString("cpid"));
            item.setStyleno(object.getString("styleno"));
            item.setBarcode(object.getString("barcode"));
            item.setStylename(object.getString("stylename"));
            item.setClassname(object.getString("classname"));
            item.setPhotourl(object.getString("photourl"));
            item.setXinghao(object.getString("xinghao"));
            item.setPinpai(object.getString("pinpai"));
            item.setDetail(object.getString("detail"));
            item.setKucunqty(object.getString("kucunqty"));
            item.setZiyouqty(object.getString("ziyouqty"));
            item.setStop_produce(object.getString("stop_produce"));
            item.setStop_sales(object.getString("stop_sales"));
            item.setPounitname(object.getString("pounitname"));
            item.setColorlist(JSONArray.parseArray(object.getJSONArray("colorlist").toJSONString(), ProductItem.ColorlistBean.class));
            item.setPacklist(JSONArray.parseArray(object.getJSONArray("packlist").toJSONString(), ProductItem.PacklistBean.class));
            item.setSizxlist(JSONArray.parseArray(object.getJSONArray("sizxlist").toJSONString(), ProductItem.SizxlistBean.class));
            item.setPiclist(JSONArray.parseArray(object.getJSONArray("piclist").toJSONString(), ProductItem.PiclistBean.class));
            item.setLishijialist(JSONArray.parseArray(object.getJSONArray("lishijialist").toJSONString(), ProductItem.LishijialistBean.class));
            item.setChengbenjialist(JSONArray.parseArray(object.getJSONArray("chengbenjialist").toJSONString(), ProductItem.ChengbenjialistBean.class));
            item.setCankaoshoujialist(JSONArray.parseArray(object.getJSONArray("cankaoshoujialist").toJSONString(), ProductItem.CankaoshoujialistBean.class));
            item.setChuchangjialist(JSONArray.parseArray(object.getJSONArray("chuchangjialist").toJSONString(), ProductItem.ChuchangjialistBean.class));

            //******** 设置默认比率、单位、单品价、单位价、价码、颜色、规格 ********
            String unitBilv = "";
            for (ProductItem.PacklistBean bean : item.getPacklist()) {
                if ("1".equals(bean.getCheck())) {
                    unitBilv = bean.getUnit_bilv();
                    item.setFactor(unitBilv);
                    item.setUnitname(bean.getUnitname());
                }
            }
            int index = item.getLishijialist().indexOf(new ProductItem.LishijialistBean(unitBilv));
            item.setOrder_prc(item.getLishijialist().get(index).getPrice());
            item.setS_jiage2(item.getLishijialist().get(index).getPrice());
            item.setPricecode(item.getLishijialist().get(index).getPricecode());
            if (item.getColorlist().size() > 0 && TextUtils.isEmpty(item.getYanse())) item.setYanse("无");
            if (item.getSizxlist().size() > 0 && TextUtils.isEmpty(item.getGuige())) item.setGuige("无");

            list.add(item);
        }
        return list;
    }

}
