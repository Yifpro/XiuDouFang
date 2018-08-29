package com.example.administrator.xiudoufang.product.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ProductSupplierDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0, 2, 0, 2);
        } else {
            outRect.set(0, 0, 0, 2);
        }
    }
}

