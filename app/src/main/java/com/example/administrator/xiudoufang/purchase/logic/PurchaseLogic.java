package com.example.administrator.xiudoufang.purchase.logic;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ProductItem;
import com.example.administrator.xiudoufang.bean.PurchaseListBean;
import com.example.administrator.xiudoufang.bean.ReloadPriceListBean;
import com.example.administrator.xiudoufang.common.callback.JsonCallback;
import com.example.administrator.xiudoufang.common.utils.LogUtils;
import com.example.administrator.xiudoufang.common.utils.PreferencesUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PurchaseLogic {

    //******** 获取采购单列表 ********
    public void requestPurchaseList(Context context, HashMap<String, String> params, JsonCallback<PurchaseListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("采购单列表 -> " + json);
        OkGo.<PurchaseListBean>post(StringUtils.BASE_URL + "/api/products/Getpuomstrlistsdata?Getpuomstrlistsdata=0")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 获取采购单明细 ********
    public void requestPurchaseDetails(Context context, String orderId, JsonCallback<String> callback) {
        HashMap<String, String> map = new HashMap<>();
        map.put("iid", orderId);
        map.put("dianid", PreferencesUtils.getPreferences().getString(PreferencesUtils.DIAN_ID, ""));
        map.put("userid", PreferencesUtils.getPreferences().getString(PreferencesUtils.USER_ID, ""));
        String json = JSONObject.toJSONString(map);
        LogUtils.e("采购单明细 -> "+json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/GetSinglepuomstrdata?iid=0")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 对应动作更改订单 ********
    public void requestActionForOrder(Context context, HashMap<String, String> params, JsonCallback<String> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("动作 -> " + json);
        OkGo.<String>post(StringUtils.BASE_URL + "/Api/products/Actionfor_poorder?Actionfor_poorder=0")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 重新加载历史价格 ********
    public void reloadHistoryPrice(Context context, HashMap<String, String> params, JsonCallback<ReloadPriceListBean> callback) {
        String json = JSONObject.toJSONString(params);
        LogUtils.e("重载 -> " + json);
        OkGo.<ReloadPriceListBean>post(StringUtils.BASE_URL + "/Api/products/requset_supppricedata?requset_supppricedata=")
                .tag(context)
                .headers("Content-Type", "application/json")
                .upJson(json)
                .execute(callback);
    }

    //******** 解析采购单返回的产品json ********
    public List<ProductItem> parsePurchaseDetailsJson(JSONObject result) {
        List<ProductItem> list = new ArrayList<>();
        JSONArray puiasm = result.getJSONArray("puiasm");
        for (int i = 0; i < puiasm.size(); i++) {
            JSONObject object = puiasm.getJSONObject(i);
            ProductItem item = new ProductItem();
            item.setDianid(object.getString("dianid"));
            item.setClassname(object.getString("classname")); //******** 产品类别 ********
            item.setCpid(object.getString("cpid")); //******** 产品id ********
            item.setStyleno(object.getString("styleno")); //******** 产品编号 ********
            item.setStylename(object.getString("stylename")); //******** 产品名称 ********
            item.setPounitname(object.getString("pounitname")); //******** 单个的单位 ********
            item.setBarcode(object.getString("barcode")); //******** 条形码 ********
            item.setPhotourl(object.getString("photourl")); //******** 图片 ********
            item.setXinghao(object.getString("xinghao")); //******** 型号 ********
            item.setPinpai(object.getString("pinpai")); //******** 品牌 ********
            item.setDetail(object.getString("detail")); //******** 明细 ********
            item.setStop_produce(object.getString("stop_produce")); //******** 停产 ********
            item.setStop_sales(object.getString("stop_sales")); //******** 停售 ********
            item.setKucunqty(object.getString("kucunqty")); //******** 库存数 ********
            item.setZiyouqty(object.getString("ziyouqty")); //******** 自由数 ********
            item.setColorlist(JSONObject.parseArray(object.getJSONArray("colorlist").toJSONString(), ProductItem.ColorlistBean.class));
            item.setPacklist(JSONObject.parseArray(object.getJSONArray("packlist").toJSONString(), ProductItem.PacklistBean.class));
            item.setSizxlist(JSONObject.parseArray(object.getJSONArray("sizxlist").toJSONString(), ProductItem.SizxlistBean.class));
            item.setPiclist(JSONObject.parseArray(object.getJSONArray("piclist").toJSONString(), ProductItem.PiclistBean.class));
            item.setLishijialist(JSONObject.parseArray(object.getJSONArray("lishijialist").toJSONString(), ProductItem.LishijialistBean.class));
            item.setChengbenjialist(JSONObject.parseArray(object.getJSONArray("chengbenjialist").toJSONString(), ProductItem.ChengbenjialistBean.class));
            item.setCankaoshoujialist(JSONObject.parseArray(object.getJSONArray("cankaoshoujialist").toJSONString(), ProductItem.CankaoshoujialistBean.class));
            item.setChuchangjialist(JSONObject.parseArray(object.getJSONArray("chuchangjialist").toJSONString(), ProductItem.ChuchangjialistBean.class));

            //******** 设置默认显示的颜色规格 ********
            if (item.getColorlist().size() > 0 && TextUtils.isEmpty(item.getYanse())) item.setYanse("无");
            if (item.getSizxlist().size() > 0 && TextUtils.isEmpty(item.getGuige())) item.setGuige("无");

            item.setUnitname(object.getString("unitname")); //******** 单位 ********
            item.setIid(object.getString("iid")); //******** 采购单id ********
            item.setPnid(object.getString("pnid")); //******** 当前行id ********
            item.setPuOrderNo(object.getString("puOrderNo")); //******** 采购单编号 ********
            item.setYanse(object.getString("yanse")); //******** 颜色 ********
            item.setGuige(object.getString("guige")); //******** 规格 ********
            item.setOrder_prc(object.getString("order_prc")); //******** 单品价格 ********
            item.setFactor(object.getString("factor")); //******** 比率 ********
            item.setS_jiage2(object.getString("s_jiage2")); //******** 单位价格 ********
            item.setPricecode(object.getString("pricecode")); //******** 单位价价码 ********
            item.setOrderqty(object.getString("orderqty")); //******** 采购数量 ********
            item.setCp_qty(object.getString("cp_qty")); //******** 数量 ********
            item.setOrderamt(object.getString("orderamt")); //******** 金额 ********
            item.setRcvqty(object.getString("rcvqty")); //******** 收货数量 ********
            item.setRcvamt(object.getString("rcvamt")); //******** 收货金额 ********
            item.setStatusstr(object.getString("statusstr")); //******** 当前产品状态 ********
            item.setBz(object.getString("bz")); //******** 备注 ********
            item.setZhuancaigou_pnid(object.getString("zhuancaigou_pnid")); //******** 暂时没用 ********
            item.setFujian(object.getString("fujian")); //******** 附件 ********
            item.setHuohao(object.getString("huohao")); //******** 货号 ********
            item.setZengpin(object.getString("zengpin")); //******** 赠品 ********
            item.setJiagelaiyuan(object.getString("jiagelaiyuan")); //******** 价格来源 ********
            item.setButtonstatus_str(object.getString("buttonstatus_str")); //******** 按钮显示文本 ********
            list.add(item);
        }
        return list;
    }

    //******** 获取对应动作 ********
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
