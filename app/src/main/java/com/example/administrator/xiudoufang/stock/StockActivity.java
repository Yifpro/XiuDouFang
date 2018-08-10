package com.example.administrator.xiudoufang.stock;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class StockActivity extends AppCompatActivity implements IActivityBase {

    public static void start(Context context) {
        Intent intent = new Intent(context, StockActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_stock;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        //R.layout.layout_list_header_stock
        //R.layout.layout_list_item_stock
    }
}
