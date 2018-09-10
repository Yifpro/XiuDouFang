package com.example.administrator.xiudoufang.open.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.bean.CustomerListBean;
import com.example.administrator.xiudoufang.common.utils.StringUtils;
import com.example.administrator.xiudoufang.common.widget.CustomerInfoView;
import com.example.administrator.xiudoufang.purchase.ui.AreaListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/1
 */

public class AddCustomerActivity extends AppCompatActivity implements IActivityBase, View.OnClickListener {

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
    private CustomerInfoView mCivAreaNo;
    private CustomerInfoView mCivAreaName;
    private OptionsPickerView areaTypePickerView;

    private String mAreaTypeId;

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
        mCivAreaNo = findViewById(R.id.civ_area_no);
        mCivAreaName = findViewById(R.id.civ_area_name);

        mCivAreaType.setOnClickListener(this);
        findViewById(R.id.civ_custom_define_area).setOnClickListener(this);
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
                    bean.setQuancheng(mCivTotalName.getValue());
                    List<CustomerListBean.CustomerBean.TelephoneBean> telephoneBeanList = new ArrayList<>();
                    telephoneBeanList.add(new CustomerListBean.CustomerBean.TelephoneBean(mCivMobilePhoneNum.getValue()));
                    bean.setTelephone(telephoneBeanList);
                    bean.setCustomerno(mCivNo.getValue());
                    List<CustomerListBean.CustomerBean.LianxirenBean> lianxirenBeanList = new ArrayList<>();
                    lianxirenBeanList.add(new CustomerListBean.CustomerBean.LianxirenBean(mCivContact.getValue()));
                    bean.setLianxiren(lianxirenBeanList);
                    List<CustomerListBean.CustomerBean.DianhuaBean> dianhuaBeanList = new ArrayList<>();
                    dianhuaBeanList.add(new CustomerListBean.CustomerBean.DianhuaBean(mCivPhoneNum.getValue()));
                    bean.setDianhua(dianhuaBeanList);
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
                    List<CustomerListBean.CustomerBean.FreightBean> freightBeanList = new ArrayList<>();
                    freightBeanList.add(new CustomerListBean.CustomerBean.FreightBean(mCivCargoTerminal.getValue()));
                    bean.setFreight(freightBeanList);
                    bean.setCountry(mAreaTypeId);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_area_type:
                showAreaTypeDialog();
                break;
            case R.id.civ_custom_define_area:
                startActivityForResult(new Intent(this, AreaListActivity.class), RESULT_SELECT_AREA);
                break;
        }
    }

    private void showAreaTypeDialog() {
        if (areaTypePickerView == null) {
            final List<String> areaTypeItems;
            final List<String> areaTypeIds;
            JSONObject jsonObject = JSONObject.parseObject(StringUtils.readInfoForFile(StringUtils.LOGIN_INFO));
            JSONArray ordertype = jsonObject.getJSONArray("ordertype");
            if (ordertype.size() > 0) {
                areaTypeItems = new ArrayList<>();
                areaTypeIds = new ArrayList<>();
                for (int i = 0; i < ordertype.size(); i++) {
                    JSONObject object = ordertype.getJSONObject(i);
                    String name = object.getString("name");
                    String id = object.getString("id");
                    if ("1".equals(object.getString("moren"))) {
                        mCivAreaType.setValue(name);
                        mAreaTypeId = id;
                    }
                    areaTypeItems.add(name);
                    areaTypeIds.add(id);
                }
                areaTypePickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        mCivAreaType.setValue(areaTypeItems.get(options1));
                        mAreaTypeId = areaTypeIds.get(options1);
                    }
                })
                        .setContentTextSize(20)//设置滚轮文字大小
                        .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                        .setSelectOptions(0, 1)//默认选中项
                        .setBgColor(Color.WHITE)
                        .setTitleBgColor(Color.WHITE)
                        .setTitleColor(Color.LTGRAY)
                        .setCancelColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setSubmitColor(ContextCompat.getColor(this, R.color.colorPrimary))
                        .setTextColorCenter(Color.BLACK)
                        .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                        .setBackgroundId(0x00000000) //设置外部遮罩颜色
                        .build();
                areaTypePickerView.setPicker(areaTypeItems);
            }
        }
        if (mAreaTypeId != null) areaTypePickerView.show();
    }
}
