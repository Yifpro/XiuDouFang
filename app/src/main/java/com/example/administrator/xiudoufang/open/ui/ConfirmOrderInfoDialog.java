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

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OrderInfo;
import com.example.administrator.xiudoufang.bean.OrderInfo_1;
import com.example.administrator.xiudoufang.bean.OrderInfo_2;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.open.BaseOrderInfo;
import com.example.administrator.xiudoufang.open.adapter.ConfirmOrderInfoAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/4
 */

public class ConfirmOrderInfoDialog extends DialogFragment implements View.OnClickListener {

    private ArrayList<MultiItemEntity> mList;
    private OnItemClickListener mListener;

    public static ConfirmOrderInfoDialog newInstance(ArrayList<String> list) {
        ConfirmOrderInfoDialog f = new ConfirmOrderInfoDialog();
        Bundle args = new Bundle();
        args.putStringArrayList("list", list);
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
        View view = inflater.inflate(R.layout.fragment_confirm_order_info, container);
        view.findViewById(R.id.tv_save_order).setOnClickListener(this);
        view.findViewById(R.id.tv_confirm_order).setOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        ArrayList<String> valueList = getArguments().getStringArrayList("list");
        mList = new ArrayList<>();
        Boolean[] isText = {true, true, true, false, true, true, false, false, true, false};
        String[] keys = {"本单金额", "前结欠", "累计欠", "其他费用", "本单应收", "累计金额", "本次收款", "优惠金额", "账户金额", "余额支付"};
        for (int i = 0; i < keys.length; i++) {
            if (isText[i]) {
                mList.add(new OrderInfo_1(keys[i], valueList.get(i)));
            } else {
                mList.add(new OrderInfo_2(keys[i], valueList.get(i)));
            }
        }
        ConfirmOrderInfoAdapter adapter = new ConfirmOrderInfoAdapter(mList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_save_order:
                if (mListener != null) mListener.onSave(getInfo());
                break;
            case R.id.tv_confirm_order:
                if (mListener != null) mListener.onConfirm(getInfo());
                break;
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onSave(OrderInfo info);
        void onConfirm(OrderInfo info);
    }

    private OrderInfo getInfo() {
        OrderInfo item = new OrderInfo();
        item.setOrderSums(((BaseOrderInfo) mList.get(0)).getValue());
        item.setQianjieqian(((BaseOrderInfo) mList.get(1)).getValue());
        item.setLeijiqian(((BaseOrderInfo) mList.get(2)).getValue());
        item.setOtherFree(((BaseOrderInfo) mList.get(3)).getValue());
        item.setYingshou(((BaseOrderInfo) mList.get(4)).getValue());
        item.setLeijijine(((BaseOrderInfo) mList.get(5)).getValue());
        item.setBencishoukuan(((BaseOrderInfo) mList.get(6)).getValue());
        item.setDiscount(((BaseOrderInfo) mList.get(7)).getValue());
        item.setBalance(((BaseOrderInfo) mList.get(8)).getValue());
        item.setBalancePayment(((BaseOrderInfo) mList.get(9)).getValue());
        return item;
    }
}

