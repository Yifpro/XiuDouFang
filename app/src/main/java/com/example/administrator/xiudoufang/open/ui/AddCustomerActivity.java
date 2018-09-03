package com.example.administrator.xiudoufang.open.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.common.widget.CustomerInfoView;
import com.example.administrator.xiudoufang.purchase.ui.AddSupplierActivity;
import com.example.administrator.xiudoufang.purchase.ui.AreaListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class AddCustomerActivity extends AppCompatActivity implements IActivityBase {

    private static final int RESULT_SELECT_AREA = 105;

    private CustomerInfoView mCivName;
    private CustomerInfoView mCivMobilePhoneNum;
    private CustomerInfoView mCivTotalName;
    private CustomerInfoView mCivNo;
    private CustomerInfoView mCivContact;
    private CustomerInfoView mCivPhoneNum;
    private CustomerInfoView mCivQQ;
    private CustomerInfoView mCivWeChat;
    private CustomerInfoView mCivDeliverGoodsAddress;
    private CustomerInfoView mCivCollectGoodsAddress;
    private CustomerInfoView mCivCargoTerminal;
    private CustomerInfoView mCivAreaType;
    private CustomerInfoView mCivCustomDefineArea;
    private CustomerInfoView mCivAreaNo;
    private CustomerInfoView mCivAreaName;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddCustomerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_customer;
    }

    @Override
    public void initView() {
        setTitle("新增客户");
        mCivName = findViewById(R.id.civ_name);
        mCivMobilePhoneNum = findViewById(R.id.civ_mobile_phone_num);
        mCivTotalName = findViewById(R.id.civ_total_name);
        mCivNo = findViewById(R.id.civ_no);
        mCivContact = findViewById(R.id.civ_contact);
        mCivPhoneNum = findViewById(R.id.civ_phone_num);
        mCivQQ = findViewById(R.id.civ_qq);
        mCivWeChat = findViewById(R.id.civ_wechat);
        mCivDeliverGoodsAddress = findViewById(R.id.civ_deliver_goods_address);
        mCivCollectGoodsAddress = findViewById(R.id.civ_collect_goods_address);
        mCivCargoTerminal = findViewById(R.id.civ_cargo_terminal);
        mCivAreaType = findViewById(R.id.civ_area_type);
        mCivCustomDefineArea = findViewById(R.id.civ_custom_define_area);
        mCivAreaNo = findViewById(R.id.civ_area_no);
        mCivAreaName = findViewById(R.id.civ_area_name);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //******** 区域返回结果 ********
        if (requestCode == RESULT_SELECT_AREA && data != null) {
            mCivAreaNo.setValue(data.getStringExtra("area_code"));
            mCivAreaName.setValue(data.getStringExtra("area_name"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void initData() {

    }

    public void onChoose(View view) {
        Intent intent = new Intent(this, AreaListActivity.class);
        startActivityForResult(intent, RESULT_SELECT_AREA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_confirm:
                if (TextUtils.isEmpty(mCivName.getValue())) {
                    mCivName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
                } else {
                    CustomerListBean.CustomerBean bean = new CustomerListBean.CustomerBean();
                    bean.setC_id("0");
                    bean.setCustomername(mCivName.getValue());
                    bean.setNewMobilePhoneNum(mCivMobilePhoneNum.getValue());
                    bean.setQuancheng(mCivTotalName.getValue());
                    bean.setCustomerno(mCivNo.getValue());
                    bean.setAddContact(mCivContact.getValue());
                    bean.setNewPhoneNum(mCivPhoneNum.getValue());
                    List<CustomerListBean.CustomerBean.QqBean> qqBeanList = new ArrayList<>();
                    qqBeanList.add(new CustomerListBean.CustomerBean.QqBean(mCivQQ.getValue()));
                    bean.setQq(qqBeanList);
                    List<CustomerListBean.CustomerBean.WeixinhaoBean> weixinhaoBeanList = new ArrayList<>();
                    weixinhaoBeanList.add(new CustomerListBean.CustomerBean.WeixinhaoBean(mCivWeChat.getValue()));
                    bean.setWeixinhao(weixinhaoBeanList);
                    List<CustomerListBean.CustomerBean.FahuodizhiBean> fahuodizhiBeanList = new ArrayList<>();
                    fahuodizhiBeanList.add(new CustomerListBean.CustomerBean.FahuodizhiBean(mCivDeliverGoodsAddress.getValue()));
                    bean.setFahuodizhi(fahuodizhiBeanList);
                    List<CustomerListBean.CustomerBean.ShouhuodizhiBean> shouhuodizhiBeanList = new ArrayList<>();
                    shouhuodizhiBeanList.add(new CustomerListBean.CustomerBean.ShouhuodizhiBean(mCivCollectGoodsAddress.getValue()));
                    bean.setShouhuodizhi(shouhuodizhiBeanList);
                    bean.setAddCargoTerminal(mCivCargoTerminal.getValue());
                    bean.setCountry(mCivAreaType.getValue());
                    bean.setQuyuno(mCivAreaNo.getValue());
                    bean.setQuyu(mCivAreaName.getValue());
                    Intent intent = new Intent(this, SalesOrderActivity.class);
                    intent.putExtra(SalesOrderActivity.CUSTOMER_DETAILS, bean);
                    startActivity(intent);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
