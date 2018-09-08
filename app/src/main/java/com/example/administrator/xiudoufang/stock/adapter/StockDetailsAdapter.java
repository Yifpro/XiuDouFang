package com.example.administrator.xiudoufang.stock.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.ImgBean;
import com.example.administrator.xiudoufang.bean.StockDetailsBean;
import com.example.administrator.xiudoufang.bean.StockDetailsItem;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockDetailsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;
    public static final int TYPE_LEVEL_2 = 2;

    public StockDetailsAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.layout_list_item_stock_details_level_0);
        addItemType(TYPE_LEVEL_1, R.layout.layout_list_item_stock_details_level_1);
        addItemType(TYPE_LEVEL_2, R.layout.layout_list_item_stock_details_level_2);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TYPE_LEVEL_0:
                ImgBean bean = (ImgBean) item;
                GlideApp.with(mContext).load(StringUtils.PIC_SMALL_URL + bean.getImg()).error(R.mipmap.ic_error).into((ImageView) helper.getView(R.id.iv_icon));
                break;
            case TYPE_LEVEL_1:
                StockDetailsItem detailsItem = (StockDetailsItem) item;
                helper.setText(R.id.tv_key, detailsItem.getKey());
                helper.setText(R.id.tv_value, detailsItem.getValue());
                break;
            case TYPE_LEVEL_2:
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
