package com.example.administrator.xiudoufang.purchase.logic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(HashMap<String, String> params, JsonCallback<PurchaseListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("采购单列表 -> " + json);
        OkGo.<PurchaseListBean>post(StringUtils.BASE_URL + "/api/products/Getpuomstrlistsdata?Getpuomstrlistsdata=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取采购单明细 ********
    public void requestPurchaseDetails(String orderId, Callback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("iid", orderId);
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        String json = JSONObject.toJSONString(map);
        LogUtils.e("采购单明细 -> "+json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/GetSinglepuomstrdata?iid=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 对应动作更改订单 ********
    public void requestActionForOrder(HashMap<String, String> params, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("动作 -> " + json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Actionfor_poorder?Actionfor_poorder=0")
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    public List<ProductItem> parseProductListJson(JSONObject result) {
        List<ProductItem> list = new ArrayList<>();
        JSONArray puiasm = result.getJSONArray("puiasm");
        for (int i = 0; i < puiasm.size(); i++) {
            JSONObject object = puiasm.getJSONObject(i);
            ProductItem item = new ProductItem();
            item.setPhotourl(object.getString("photourl"));
            item.setProductNo(object.getString("styleno"));
            item.setStylename(object.getString("stylename"));
            item.setStockAmount(object.getString("kucunqty"));
            item.setFreeAmount(object.getString("ziyouqty"));
            item.setType(object.getString("classname"));
            item.setBrand(object.getString("pinpai"));
            item.setModel(object.getString("xinghao"));
            item.setPriceCode(object.getString("barcode"));
            item.setDetails(object.getString("detail"));
            item.setAmount(object.getString("cp_qty"));
            item.setGift(!"0".equals(object.getString("zengpin")));
            item.setGoodsNo(object.getString("huohao"));
            item.setTip(object.getString("bz"));
            item.setStatus(object.getString("statusstr"));
            item.setId(object.getString("cpid"));
            item.setFactor(object.getString("factor"));
            item.setUnit(object.getString("unitname"));
            item.setSinglePrice(object.getString("order_prc"));
            item.setUnitPrice(object.getString("s_jiage2"));
            item.setDianid(object.getString("dianid"));
            item.setPounitname(object.getString("pounitname"));
            item.setStopProduce(!"0".equals(object.getString("stop_produce")));
            item.setStopSales(!"0".equals(object.getString("stop_sales")));
            item.setIid(object.getString("iid"));
            item.setPnid(object.getString("pnid"));
            item.setPuOrderNo(object.getString("puOrderNo"));
            item.setColor(object.getString("yanse"));
            item.setSize(object.getString("guige"));
            item.setPriceSource(object.getString("jiagelaiyuan"));
            item.setPriceCode(object.getString("pricecode"));
            item.setOrderqty(object.getString("orderqty"));
            item.setOrderamt(object.getString("orderamt"));
            item.setRcvqty(object.getString("rcvqty"));
            item.setZhuancaigou_pnid(object.getString("zhuancaigou_pnid"));
            item.setFujian(object.getString("fujian"));
            item.setButtonstatus_str(object.getString("buttonstatus_str"));
            item.setColorlist(JSONObject.parseArray(object.getJSONArray("colorlist").toJSONString(), ProductItem.ColorlistBean.class));
            item.setPacklist(JSONObject.parseArray(object.getJSONArray("packlist").toJSONString(), ProductItem.PacklistBean.class));
            item.setSizxlist(JSONObject.parseArray(object.getJSONArray("sizxlist").toJSONString(), ProductItem.SizxlistBean.class));
            item.setPiclist(JSONObject.parseArray(object.getJSONArray("piclist").toJSONString(), ProductItem.PiclistBean.class));
            item.setLishijialist(JSONObject.parseArray(object.getJSONArray("lishijialist").toJSONString(), ProductItem.LishijialistBean.class));
            item.setChengbenjialist(JSONObject.parseArray(object.getJSONArray("chengbenjialist").toJSONString(), ProductItem.ChengbenjialistBean.class));
            item.setCankaoshoujialist(JSONObject.parseArray(object.getJSONArray("cankaoshoujialist").toJSONString(), ProductItem.CankaoshoujialistBean.class));
            item.setChuchangjialist(JSONObject.parseArray(object.getJSONArray("chuchangjialist").toJSONString(), ProductItem.ChuchangjialistBean.class));
            list.add(item);
        }
        return list;
    }

    //******** 获取对应dongzuo ********
    public String getAction(int id, String type) {
        String action = "";
        switch (id) {
            case R.id.tv_bottom_left:
                switch (type) {
                    case "1":
                        break;
                    case "2":
                        action = "2";
                        break;
                    case "3":
                        break;
                    case "4":
                        action = "2";
                        break;
                    case "5":
                        break;
                    case "6":
                        break;
                }
                break;
            case R.id.tv_bottom_right:
                switch (type) {
                    case "1":
                        action = "1";
                        break;
                    case "2":
                        action = "3";
                        break;
                    case "3":
                        break;
                    case "4":
                        action = "5";
                        break;
                    case "5":
                        action = "6";
                        break;
                    case "6":
                        action = "4";
                        break;
                }
                break;
        }
        return action;
    }
}
