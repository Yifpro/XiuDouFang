package com.example.administrator.xiudoufang.product.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.ProductListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductListAdapter extends BaseQuickAdapter<ProductListBean.ChanpinpicBean, BaseViewHolder> {

    public ProductListAdapter(@LayoutRes int layoutResId, @Nullable List<ProductListBean.ChanpinpicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductListBean.ChanpinpicBean item) {
        GlideApp.with(mContext)
                .load(item.getPhotourl().contains("/") ? item.getPhotourl() : StringUtils.PIC_SMALL_URL + item.getPhotourl())
                .error(R.mipmap.ic_error)
                .into((ImageView) helper.getView(R.id.iv_icon));
        helper.setText(R.id.tv_name, item.getStylename());
        helper.setText(R.id.tv_id, item.getStyleno());
    }
}
