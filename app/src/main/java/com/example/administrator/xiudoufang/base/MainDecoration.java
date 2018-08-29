package com.example.administrator.xiudoufang.base;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view) % 3;
        if (position == 2) {
            outRect.set(0, 2, 0, 0);
        } else {
            outRect.set(0, 2, 2, 0);
        }
    }
}

