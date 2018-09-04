package com.example.administrator.xiudoufang.open.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.OtherSettingItem;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSettingItemAdapter extends BaseQuickAdapter<OtherSettingItem, BaseViewHolder> {

    public OtherSettingItemAdapter(@LayoutRes int layoutResId, @Nullable List<OtherSettingItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OtherSettingItem item) {
        helper.setText(R.id.tv, item.getKey());
        helper.getView(R.id.tv).setSelected(item.isSelected());
        //R.layout.layout_list_item_other_setting_item;
    }
}
