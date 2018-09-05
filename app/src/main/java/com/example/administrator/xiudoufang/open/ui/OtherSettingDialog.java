package com.example.administrator.xiudoufang.open.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OtherSeting_1;
import com.example.administrator.xiudoufang.bean.OtherSeting_2;
import com.example.administrator.xiudoufang.bean.OtherSeting_3;
import com.example.administrator.xiudoufang.bean.OtherSeting_4;
import com.example.administrator.xiudoufang.bean.OtherSetting;
import com.example.administrator.xiudoufang.bean.OtherSettingItem;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.open.adapter.OtherSettingAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSettingDialog extends DialogFragment {

    private OnSureClickListener listener;

    public static OtherSettingDialog newInstance(OtherSetting o) {
        OtherSettingDialog f = new OtherSettingDialog();
        Bundle args = new Bundle();
        args.putParcelable("other_setting", o);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(SizeUtils.dp2px(300), SizeUtils.dp2px(320));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getDialog().getWindow() != null;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_other_setting, container);
        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) listener.onSure(getOtherSetting());
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        OtherSetting otherSetting = getArguments().getParcelable("other_setting");
        JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
        List<OtherSettingItem> fahuodianList = new ArrayList<>();
        List<OtherSettingItem> ordertypeList = new ArrayList<>();
        List<OtherSettingItem> huoyunList = new ArrayList<>();
        List<OtherSettingItem> wuliuList = new ArrayList<>();
        JSONArray fahuodian = jsonObject.getJSONArray("fahuodian");
        JSONArray ordertype = jsonObject.getJSONArray("ordertype");
        JSONArray huoyun = jsonObject.getJSONArray("huoyun");
        JSONArray wuliu = jsonObject.getJSONArray("wuliu");
        int fahuoIndex = 0;
        int orderTypeIndex = 0;
        int huoyunIndex = 0;
        int wuliuIndex = 0;
        for (int i = 0; i < fahuodian.size(); i++) {
            JSONObject dianItem = fahuodian.getJSONObject(i);
            boolean isSelected = otherSetting == null ? "1".equals(dianItem.getString("moren")) : dianItem.getString("id").equals(otherSetting.getFahuodianid());
            fahuodianList.add(new OtherSettingItem(dianItem.getString("name"), dianItem.getString("id"), isSelected));
            if (isSelected) fahuoIndex = i;
        }
        for (int i = 0; i < ordertype.size(); i++) {
            JSONObject typeItem = ordertype.getJSONObject(i);
            boolean isSelected = otherSetting == null ? "1".equals(typeItem.getString("moren")) : typeItem.getString("id").equals(otherSetting.getOrderType());
            ordertypeList.add(new OtherSettingItem(typeItem.getString("name"), typeItem.getString("id"), isSelected));
            if (isSelected) orderTypeIndex = i;
        }
        for (int i = 0; i < huoyun.size(); i++) {
            JSONObject huoyunItem = huoyun.getJSONObject(i);
            boolean isSelected = otherSetting == null ? "1".equals(huoyunItem.getString("moren")) : huoyunItem.getString("id").equals(otherSetting.getHuoyunType());
            huoyunList.add(new OtherSettingItem(huoyunItem.getString("name"), huoyunItem.getString("id"), isSelected));
            if (isSelected) huoyunIndex = i;
        }
        for (int i = 0; i < wuliu.size(); i++) {
            JSONObject wuliuItem = wuliu.getJSONObject(i);
            boolean isSelected = otherSetting == null ? "1".equals(wuliuItem.getString("moren")) : wuliuItem.getString("id").equals(otherSetting.getLogistics());
            wuliuList.add(new OtherSettingItem(wuliuItem.getString("name"), wuliuItem.getString("id"), isSelected));
            if (isSelected) wuliuIndex = i;
        }
        ArrayList<MultiItemEntity> list = new ArrayList<>();
        list.add(new OtherSeting_1("发货店", fahuodianList, fahuodianList.get(fahuoIndex).getValue()));
        list.add(new OtherSeting_1("单据类型", ordertypeList, ordertypeList.get(orderTypeIndex).getValue()));
        list.add(new OtherSeting_1("货运类型", huoyunList, huoyunList.get(huoyunIndex).getValue()));
        list.add(new OtherSeting_1("物流", wuliuList, wuliuList.get(wuliuIndex).getValue()));
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("否");
        orderList.add("是");
        list.add(new OtherSeting_2("特殊订单", orderList, otherSetting == null ? "否" : otherSetting.getSpecialOrder()));
        list.add(new OtherSeting_3("附加说明", otherSetting == null ? "" : otherSetting.getAdditionalInstructions(), "不超过120字"));
        list.add(new OtherSeting_4("预计交货日期", "未设置", otherSetting == null ? null : otherSetting.getForcastDate(), false));
        list.add(new OtherSeting_4("附件", "查看或上传", otherSetting == null ? null : otherSetting.getExtra(), true));
        list.add(new OtherSeting_3("客户合同", otherSetting == null ? null : otherSetting.getCustomerContract(), "请输入客户合同"));
        OtherSettingAdapter adapter = new OtherSettingAdapter(list);
        adapter.bindToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        return view;
    }

    private OtherSetting getOtherSetting() {
        OtherSetting otherSetting = new OtherSetting();
        return null;
    }

    public void setOnSureChangedListener(OnSureClickListener listener) {
        this.listener = listener;
    }

    public interface OnSureClickListener {
        void onSure(OtherSetting o);
    }
}

