package com.example.administrator.xiudoufang.stock.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.StockListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/27
 */

public class StockListAdapter extends BaseQuickAdapter<StockListBean.StockBean, BaseViewHolder> {

    public StockListAdapter(@LayoutRes int layoutResId, @Nullable List<StockListBean.StockBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StockListBean.StockBean item) {
        GlideApp.with(mContext).load(StringUtils.PIC_SMALL_URL + item.getPic()).error(R.mipmap.ic_error).into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_id, item.getStyleno());
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_stock_num, item.getInvnum());
        helper.setText(R.id.tv_free_num, item.getUsenum());
        helper.setText(R.id.tv_allocate_num, item.getAlcnum());
        helper.setText(R.id.tv_freedom_num, item.getUseunitnum() + item.getUnitname());

        helper.addOnClickListener(R.id.iv_icon);
    }
}
