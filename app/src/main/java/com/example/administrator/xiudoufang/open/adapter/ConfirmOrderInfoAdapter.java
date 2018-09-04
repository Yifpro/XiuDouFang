package com.example.administrator.xiudoufang.open.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.bean.OrderInfo_1;
import com.example.administrator.xiudoufang.bean.OrderInfo_2;
import com.example.administrator.xiudoufang.bean.SettingItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class ConfirmOrderInfoAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int LAYOUT_TEXT = 0;
    public static final int LAYOUT_EDIT = 1;

    public ConfirmOrderInfoAdapter(@Nullable List<MultiItemEntity> data) {
        super(data);
        addItemType(LAYOUT_TEXT, R.layout.layout_list_item_confirm_order_info_1);
        addItemType(LAYOUT_EDIT, R.layout.layout_list_item_confirm_order_info_2);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LAYOUT_TEXT:
                OrderInfo_1 info_1 = (OrderInfo_1) item;
                helper.setText(R.id.tv_key, info_1.getKey());
                helper.setText(R.id.tv_value, info_1.getValue());
                break;
            case LAYOUT_EDIT:
                final OrderInfo_2 info_2 = (OrderInfo_2) item;
                helper.setText(R.id.tv_key, info_2.getKey());
                final EditText etValue = helper.getView(R.id.et_value);
                etValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etValue.setCursorVisible(true);
                    }
                });
                etValue.addTextChangedListener(new BaseTextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                        info_2.setValue(editable.toString().trim());
                    }
                });
                break;
        }
    }
}
