package com.example.administrator.xiudoufang.product;

import android.support.v7.app.AppCompatActivity;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;


public class ProductQueryActivity extends AppCompatActivity implements IActivityBase {
    @Override
    public int getLayoutId() {
        return R.layout.activity_product_query;
    }

    @Override
    public void initView() {
        setTitle("产品查询");
    }

    @Override
    public void initData() {

    }
}
