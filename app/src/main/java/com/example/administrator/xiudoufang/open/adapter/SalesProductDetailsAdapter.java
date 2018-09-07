package com.example.administrator.xiudoufang.open.adapter;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.base.GlideApp;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_0;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_1;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_2;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_3;
import com.example.administrator.xiudoufang.bean.SalesProductDetailsItem_4;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.common.utils.StringUtils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/3
 */

public class SalesProductDetailsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_IMG = 0;
    public static final int TYPE_FIRST = 1;
    public static final int TYPE_SECOND = 2;
    public static final int TYPE_THIRD = 3;
    public static final int TYPE_FOUTH = 4;

    public SalesProductDetailsAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_IMG, R.layout.layout_list_item_sales_product_details_0);
        addItemType(TYPE_FIRST, R.layout.layout_list_item_sales_product_details_1);
        addItemType(TYPE_SECOND, R.layout.layout_list_item_sales_product_details_2);
        addItemType(TYPE_THIRD, R.layout.layout_list_item_sales_product_details_3);
        addItemType(TYPE_FOUTH, R.layout.layout_list_item_sales_product_details_4);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TYPE_IMG:
                SalesProductDetailsItem_0 item_0 = (SalesProductDetailsItem_0) item;
                GlideApp.with(mContext).load(StringUtils.PIC_URL + item_0.getUrl()).error(R.mipmap.ic_icon).into((ImageView) helper.getView(R.id.iv_icon));
                break;
            case TYPE_FIRST:
                SalesProductDetailsItem_1 item_1 = (SalesProductDetailsItem_1) item;
                helper.setText(R.id.tv_key, item_1.getKey());
                helper.setText(R.id.tv_value, item_1.getValue());
                break;
            case TYPE_SECOND:
                final SalesProductDetailsItem_2 item_2 = (SalesProductDetailsItem_2) item;
                helper.setText(R.id.tv_key, item_2.getKey());
                if (item_2.isText()) {
                    helper.setText(R.id.tv_value, item_2.getValue());
                    helper.getView(R.id.et_value).setVisibility(View.GONE);
                    if (item_2.getListener() != null)
                        helper.getView(R.id.tv_value).setOnClickListener(item_2.getListener());
                } else {
                    helper.setText(R.id.et_value, item_2.getValue());
                    helper.getView(R.id.tv_value).setVisibility(View.GONE);
                    final EditText etValue = helper.getView(R.id.et_value);
                    etValue.addTextChangedListener(new BaseTextWatcher() {
                        @Override
                        public void afterTextChanged(Editable editable) {
                            item_2.setValue(editable.toString().trim());
                        }
                    });
                    etValue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            etValue.setCursorVisible(true);
                        }
                    });
                }
                break;
            case TYPE_THIRD:
                final SalesProductDetailsItem_3 item_3 = (SalesProductDetailsItem_3) item;
                helper.setText(R.id.tv_key, item_3.getKey());
                ArrayList<String> list = item_3.getList();
                LinearLayout linearLayout = helper.getView(R.id.linear_layout);
                final ArrayList<TextView> textViews = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    final TextView textView = new TextView(mContext);
                    textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                    textView.setPadding(0, 4, 0, 4);
                    textView.setText(list.get(i));
                    textView.setTextSize(14);
                    XmlResourceParser xml = mContext.getResources().getXml(R.xml.bg_segment_text_color_selector);
                    ColorStateList colorStateList = null;
                    try {
                        colorStateList = ColorStateList.createFromXml(mContext.getResources(), xml);
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    textView.setTextColor(colorStateList);
                    textView.setGravity(Gravity.CENTER);
                    if (i == 0) {
                        textView.setBackgroundResource(R.drawable.bg_left_segment_selector);
                    } else if (i == list.size() - 1) {
                        textView.setBackgroundResource(R.drawable.bg_right_segment_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.bg_segment_selector);
                    }
                    if (item_3.getValue().equals(list.get(i))) {
                        textView.setSelected(true);
                    }
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (TextView tv : textViews) {
                                tv.setSelected(false);
                            }
                            view.setSelected(true);
                            TextView tv = (TextView) view;
                            item_3.setValue(tv.getText().toString());
                        }
                    });
                    textViews.add(textView);
                    linearLayout.addView(textView);
                    if (i != list.size() - 1) {
                        View line = new View(mContext);
                        line.setLayoutParams(new LinearLayoutCompat.LayoutParams(SizeUtils.dp2px(1), LinearLayout.LayoutParams.MATCH_PARENT));
                        line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                        linearLayout.addView(line);
                    }
                }
                break;
            case TYPE_FOUTH:
                final SalesProductDetailsItem_4 item_4 = (SalesProductDetailsItem_4) item;
                helper.setText(R.id.tv_key, item_4.getKey());
                final EditText etValue = helper.getView(R.id.et_value);
                etValue.setText(item_4.getValue());
                etValue.addTextChangedListener(new BaseTextWatcher() {
                    @Override
                    public void afterTextChanged(Editable editable) {
                        item_4.setValue(editable.toString().trim());
                    }
                });
                etValue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        etValue.setCursorVisible(true);
                    }
                });
                break;
        }
    }

}
