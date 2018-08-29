package com.example.administrator.xiudoufang.product.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.xiudoufang.common.utils.LogUtils;

public class ProductWeightDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) % 6 != 5) {
            outRect.set(0, 0, 2, 0);
        }
    }
}

