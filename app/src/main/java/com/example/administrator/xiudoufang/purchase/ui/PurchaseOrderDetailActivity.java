package com.example.administrator.xiudoufang.purchase.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class PurchaseOrderDetailActivity extends AppCompatActivity implements IActivityBase {

    private ExitEditDialog mExitEditDialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, PurchaseOrderDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_purchase_order_detail;
    }

    @Override
    public void initView() {
    }

    @Override
    public void onBackPressed() {
        showExitEditDialog();
    }

    @Override
    public void initData() {
        ((Toolbar) findViewById(R.id.tool_bar)).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitEditDialog();
            }
        });
    }

    private void showExitEditDialog() {
        if (mExitEditDialog == null) {
            mExitEditDialog = new ExitEditDialog();
            mExitEditDialog.setOnSubmitClickListener(new ExitEditDialog.OnSumbitClickListener() {
                @Override
                public void onClick() {
                    mExitEditDialog.dismiss();
                    PurchaseOrderDetailActivity.this.finish();
                }
            });
        }
        mExitEditDialog.show(getSupportFragmentManager(), "ExitEditDialog");
    }
}
