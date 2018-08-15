package com.example.administrator.xiudoufang.open.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;

public class OpenBillActivity extends AppCompatActivity implements IActivityBase {

    private PopupWindow mPopupWindow;
    private TextView mTvSelectCustomer;

    public static void start(Context context) {
        Intent intent = new Intent(context, OpenBillActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_open_bill;
    }

    @Override
    public void initView() {
        setTitle("销售单");
        mTvSelectCustomer = findViewById(R.id.tv_select_customer);
    }

    @Override
    public void initData() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_open_bill, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_type:
                if (mPopupWindow == null) {
                    View contentView = LayoutInflater.from(OpenBillActivity.this).inflate(R.layout.layout_menu_open_bill, null);
                    mPopupWindow = new PopupWindow(contentView,
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    mPopupWindow.setContentView(contentView);
                    mPopupWindow.setFocusable(true);
                    mPopupWindow.setOutsideTouchable(true);
                    mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                }
                if (mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                } else {
                    mPopupWindow.showAsDropDown(findViewById(R.id.toolbar_type), -80, 0);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
