package com.example.administrator.xiudoufang.product.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.PicBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/8/28
 */

public class ProductEditAdapter extends BaseQuickAdapter<PicBean, BaseViewHolder> {

    public ProductEditAdapter(@LayoutRes int layoutResId, @Nullable List<PicBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicBean item) {
        if (item.getPic() instanceof  String) {
            GlideApp.with(mContext)
                    .load(item.isLocal() ? item.getPic() : StringUtils.PIC_SMALL_URL + item.getPic())
                    .error(R.mipmap.ic_error)
                    .into((ImageView) helper.getView(R.id.iv_extra));
            helper.getView(R.id.iv_clear).setVisibility(View.VISIBLE);
        } else {
            helper.setImageResource(R.id.iv_extra, (Integer) item.getPic());
            helper.getView(R.id.iv_clear).setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.iv_clear);
    }
}
