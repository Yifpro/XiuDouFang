package com.example.administrator.xiudoufang.transport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class TransportOrderQueryActivity extends AppCompatActivity implements IActivityBase {

    public static void start(Context context) {
        Intent intent = new Intent(context, TransportOrderQueryActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_transport_order_query;
    }

    @Override
    public void initView() {
        setTitle("库存查询");
    }

    @Override
    public void initData() {

    }
}
