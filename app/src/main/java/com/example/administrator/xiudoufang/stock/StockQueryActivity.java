package com.example.administrator.xiudoufang.stock;

import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class StockQueryActivity extends AppCompatActivity implements IActivityBase {
    @Override
    public int getLayoutId() {
        return R.layout.activity_stock_query;
    }

    @Override
    public void initView() {
        setTitle("库存查询");
    }

    @Override
    public void initData() {

    }
}
