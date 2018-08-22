package com.example.administrator.xiudoufang.check;

import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class OrderQueryActivity extends AppCompatActivity implements IActivityBase {

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_query;
    }

    @Override
    public void initView() {
        setTitle("订单查询");
    }

    @Override
    public void initData() {

    }
}
