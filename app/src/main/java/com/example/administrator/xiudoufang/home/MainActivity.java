package com.example.administrator.xiudoufang.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.IntegerStringPair;
import com.example.administrator.xiudoufang.check.ui.OrderListActivity;
import com.example.administrator.xiudoufang.open.ui.SalesOrderActivity;
import com.example.administrator.xiudoufang.product.ui.ProductListActivity;
import com.example.administrator.xiudoufang.receipt.ui.CustomerListActivity;
import com.example.administrator.xiudoufang.transport.ui.InvoiceListActivity;
import com.example.administrator.xiudoufang.purchase.ui.PurchaseActivity;
import com.example.administrator.xiudoufang.setting.ui.SettingActivity;
import com.example.administrator.xiudoufang.stock.ui.StockActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IActivityBase {

    private RecyclerView mRecyclerView;

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        ArrayList<IntegerStringPair> pairs = new ArrayList<>();
        pairs.add(new IntegerStringPair(R.mipmap.ic_open_bill, "开单"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_query_blue, "查单"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_receipt, "收款"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_product, "产品"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_transport_num, "运号"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_stock, "库存"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_purchase, "采购"));
        pairs.add(new IntegerStringPair(R.mipmap.ic_setting, "设置"));
        pairs.add(new IntegerStringPair(0, ""));
        MainAdapter adapter = new MainAdapter(R.layout.layout_list_item_main, pairs);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        SalesOrderActivity.start(MainActivity.this);
                        break;
                    case 1:
                        OrderListActivity.start(MainActivity.this);
                        break;
                    case 2:
                        CustomerListActivity.start(MainActivity.this);
                        break;
                    case 3:
                        ProductListActivity.start(MainActivity.this);
                        break;
                    case 4:
                        InvoiceListActivity.start(MainActivity.this);
                        break;
                    case 5:
                        StockActivity.start(MainActivity.this);
                        break;
                    case 6:
                        PurchaseActivity.start(MainActivity.this);
                        break;
                    case 7:
                        SettingActivity.start(MainActivity.this);
                        break;
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.addItemDecoration(new MainDecoration());
    }
}
