package com.example.administrator.xiudoufang.check.ui;

import android.content.Intent;
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

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OrderDetailsBean;
import com.example.administrator.xiudoufang.bean.StringPair;
import com.example.administrator.xiudoufang.check.adapter.OrderInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/31
 */

public class OrderInfoDialog extends DialogFragment implements View.OnClickListener {

    private OrderDetailsBean.OrderInfoBean mOrderInfoBean;

    public static OrderInfoDialog newInstance(OrderDetailsBean.OrderInfoBean bean) {
        OrderInfoDialog f = new OrderInfoDialog();
        Bundle args = new Bundle();
        args.putParcelable("info", bean);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        assert getDialog().getWindow() != null;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_order_info, container);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mOrderInfoBean = getArguments().getParcelable("info");
        List<StringPair> list = new ArrayList<>();
        list.add(new StringPair("手机", mOrderInfoBean.getTelephone(), false));
        list.add(new StringPair("联系人", mOrderInfoBean.getLianxiren(), false));
        list.add(new StringPair("区域", mOrderInfoBean.getQuyu(), false));
        list.add(new StringPair("发货地址", mOrderInfoBean.getFahuodizhi(), false));
        list.add(new StringPair("收货地址", mOrderInfoBean.getS_dizhi(), false));
        list.add(new StringPair("货运站", mOrderInfoBean.getHuoyunzhan(), true));
        list.add(new StringPair("操作人", mOrderInfoBean.getCrman(), false));
        list.add(new StringPair("业务员", mOrderInfoBean.getYewuyuans(), false));
        list.add(new StringPair("收款方式", mOrderInfoBean.getBankname(), false));
        list.add(new StringPair("收款账号", mOrderInfoBean.getBankno(), true));
        list.add(new StringPair("发货店", mOrderInfoBean.getFahuodianname(), false));
        list.add(new StringPair("单据类型", "0".equals(mOrderInfoBean.getCountry()) ? "国内" : "1".equals(mOrderInfoBean.getCountry()) ? "外贸" : "电商", false));
        list.add(new StringPair("货运类型", mOrderInfoBean.getHuoyunleixing(), true));
        list.add(new StringPair("备注", mOrderInfoBean.getFujia_memo(), false));
        list.add(new StringPair("物流", mOrderInfoBean.getKuaidiname(), false));
        OrderInfoAdapter adapter = new OrderInfoAdapter(R.layout.item_order_info, list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        view.findViewById(R.id.iv_close).setOnClickListener(this);
        view.findViewById(R.id.tv_print_format).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_print_format:
                startActivity(new Intent(getActivity(), WebActivity.class).putExtra(WebActivity.URL, mOrderInfoBean.getUrlstr()));
                break;
        }
    }
}
