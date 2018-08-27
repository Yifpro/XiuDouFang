package com.example.administrator.xiudoufang.stock.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class StockDetailsDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position == 0) {
            outRect.set(0, 1, 0, 1);
        } else {
            outRect.set(0, 0, 0, 1);
        }
    }
}

