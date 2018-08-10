package com.example.administrator.xiudoufang.receivables;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class CustomerListActivity extends AppCompatActivity implements IActivityBase {

    public static void start(Context context) {
        Intent intent = new Intent(context, CustomerListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_list;
    }

    @Override
    public void initView() {
        setTitle("客户列表");
    }

    @Override
    public void initData() {
        //R.layout.layout_list_header_receivables
        //R.layout.layout_list_item_receivables
    }
}
