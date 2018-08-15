package com.example.administrator.xiudoufang.product.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class ProductActivity extends AppCompatActivity implements IActivityBase {

    public static void start(Context context) {
        Intent intent = new Intent(context, ProductActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public void initView() {
        setTitle("产品");
    }

    @Override
    public void initData() {
        //R.layout.layout_list_item_product
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_scan:
                break;
            case R.id.toolbar_filter:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
