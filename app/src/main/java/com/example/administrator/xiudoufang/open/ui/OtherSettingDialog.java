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
        for (int i = 0; i < fahuodian.size(); i++) {
            JSONObject dianItem = fahuodian.getJSONObject(i);
            fahuodianList.add(new OtherSettingItem(dianItem.getString("name"), dianItem.getString("id"), "1".equals(dianItem.getString("moren"))));
        }
        for (int i = 0; i < ordertype.size(); i++) {
            JSONObject typeItem = ordertype.getJSONObject(i);
            ordertypeList.add(new OtherSettingItem(typeItem.getString("name"), typeItem.getString("id"), "1".equals(typeItem.getString("moren"))));
        }
        for (int i = 0; i < huoyun.size(); i++) {
            JSONObject huoyunItem = huoyun.getJSONObject(i);
            huoyunList.add(new OtherSettingItem(huoyunItem.getString("name"), huoyunItem.getString("id"), "1".equals(huoyunItem.getString("moren"))));
        }
        for (int i = 0; i < wuliu.size(); i++) {
            JSONObject wuliuItem = wuliu.getJSONObject(i);
            wuliuList.add(new OtherSettingItem(wuliuItem.getString("name"), wuliuItem.getString("id"), "1".equals(wuliuItem.getString("moren"))));
        }
        ArrayList<MultiItemEntity> list = new ArrayList<>();
        list.add(new OtherSeting_1("发货店", fahuodianList));
        list.add(new OtherSeting_1("单据类型", ordertypeList));
        list.add(new OtherSeting_1("货运类型", huoyunList));
        list.add(new OtherSeting_1("物流", wuliuList));
        ArrayList<String> orderList = new ArrayList<>();
        orderList.add("否");
        orderList.add("是");
        list.add(new OtherSeting_2("特殊订单", orderList));
        list.add(new OtherSeting_3("附加说明", "", "不超过120字"));
        list.add(new OtherSeting_4("预计交货日期", StringUtils.getCurrentTime()));
        list.add(new OtherSeting_4("附件", ""));
        list.add(new OtherSeting_3("客户合同", "", "请输入客户合同"));
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

