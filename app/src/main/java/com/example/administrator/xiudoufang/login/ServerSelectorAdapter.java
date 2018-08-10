package com.example.administrator.xiudoufang.login;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.bean.ServerItem;

import java.util.List;

public class ServerSelectorAdapter extends BaseQuickAdapter<ServerItem, BaseViewHolder> {

    public ServerSelectorAdapter(int layoutResId, @Nullable List<ServerItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ServerItem item) {
        if (item.getTextColorRes() != 0)
            helper.setTextColor(R.id.tv, item.getTextColorRes());
        helper.setBackgroundRes(R.id.tv, item.getBackgroundRes());
        helper.setText(R.id.tv, item.getText());
    }
}
