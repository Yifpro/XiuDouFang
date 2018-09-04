package com.example.administrator.xiudoufang.open.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;

import java.util.List;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSettingAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int LAYOUT_FIRST = 1;
    public static final int LAYOUT_SECOND = 2;
    public static final int LAYOUT_THIRD = 3;
    public static final int LAYOUT_FOUTH = 4;

    public OtherSettingAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LAYOUT_FIRST, R.layout.layout_list_item_other_setting_1);
        addItemType(LAYOUT_SECOND, R.layout.layout_list_item_other_setting_2);
        addItemType(LAYOUT_THIRD, R.layout.layout_list_item_other_setting_3);
        addItemType(LAYOUT_FOUTH, R.layout.layout_list_item_other_setting_4);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        //OtherSettingItemAdapter
    }
}
