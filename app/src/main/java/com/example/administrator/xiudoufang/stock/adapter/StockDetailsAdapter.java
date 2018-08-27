package com.example.administrator.xiudoufang.stock.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.StockDetailsBean;
import com.example.administrator.xiudoufang.bean.StockDetailsItem;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockDetailsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    public StockDetailsAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.layout_list_item_stock_details_level_0);
        addItemType(TYPE_LEVEL_1, R.layout.layout_list_item_stock_details_level_1);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                StockDetailsItem detailsItem = (StockDetailsItem) item;
                helper.setText(R.id.tv_key, detailsItem.getKey());
                helper.setText(R.id.tv_value, detailsItem.getValue());
                break;
            case TYPE_LEVEL_1:
                StockDetailsBean.DianinvproductsBean details = (StockDetailsBean.DianinvproductsBean) item;
                helper.setText(R.id.tv_title, details.getDianname());
                helper.setText(R.id.tv_warehouse, details.getWarehousestr());
                helper.setText(R.id.tv_allocate_num, details.getAlcnum());
                helper.setText(R.id.tv_free_num, details.getUsenum());
                helper.setText(R.id.tv_stock_num, details.getInvnum());
                helper.setText(R.id.tv_total_num, details.getUseunitnum());
                break;
        }
    }
}
