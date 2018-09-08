package com.example.administrator.xiudoufang.open.adapter;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseTextWatcher;
import com.example.administrator.xiudoufang.bean.OtherSeting_1;
import com.example.administrator.xiudoufang.bean.OtherSeting_2;
import com.example.administrator.xiudoufang.bean.OtherSeting_3;
import com.example.administrator.xiudoufang.bean.OtherSeting_4;
import com.example.administrator.xiudoufang.bean.OtherSettingItem;
import com.example.administrator.xiudoufang.common.utils.SizeUtils;
import com.example.administrator.xiudoufang.open.decoration.GridSpacingItemDecoration;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/9/4
 */

public class OtherSettingAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    public static final int TYPE_FIRST = 1;
    public static final int TYPE_SECOND = 2;
    public static final int TYPE_THIRD = 3;
    public static final int TYPE_FOUTH = 4;

    private TimePickerView mForcastDatePickerView;

    private OnPicSelectListener mListener;
    List<MultiItemEntity> data;
    public OtherSettingAdapter(List<MultiItemEntity> data) {
        super(data);
        this.data = data;
        addItemType(TYPE_FIRST, R.layout.layout_list_item_other_setting_1);
        addItemType(TYPE_SECOND, R.layout.layout_list_item_other_setting_2);
        addItemType(TYPE_THIRD, R.layout.layout_list_item_other_setting_3);
        addItemType(TYPE_FOUTH, R.layout.layout_list_item_other_setting_4);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if (item.getItemType() == TYPE_FIRST) {
            final OtherSeting_1 item_1 = (OtherSeting_1) item;
            helper.setText(R.id.tv_title, item_1.getTitle());
            RecyclerView recyclerView = helper.getView(R.id.recycler_view);
            final List<OtherSettingItem> list = item_1.getList();
            OtherSettingItemAdapter adapter = new OtherSettingItemAdapter(R.layout.layout_list_item_other_setting_item, list);
            adapter.bindToRecyclerView(recyclerView);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    for (OtherSettingItem item : list) {
                        item.setSelected(false);
                    }
                    list.get(position).setSelected(true);
                    adapter.setNewData(list);
                    item_1.setValue(list.get(position).getValue());
                }
            });
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, SizeUtils.dp2px(5), false));
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        } else if (item.getItemType() == TYPE_SECOND) {
            final OtherSeting_2 item_2 = (OtherSeting_2) item;
            helper.setText(R.id.tv_title, item_2.getTitle());
            List<String> list = item_2.getList();
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
                if (list.get(i).equals(item_2.getValue())) {
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
                        item_2.setValue(tv.getText().toString());
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
        } else if (item.getItemType() == TYPE_THIRD) {
            final OtherSeting_3 item_3 = (OtherSeting_3) item;
            helper.setText(R.id.tv_title, item_3.getTitle());
            final EditText etContent = helper.getView(R.id.et_content);
            etContent.setText(item_3.getValue());
            etContent.setHint(item_3.getHint());
            etContent.addTextChangedListener(new BaseTextWatcher() {
                @Override
                public void afterTextChanged(Editable editable) {
                    item_3.setValue(editable.toString().trim());
                }
            });
        } else {
            final OtherSeting_4 item_4 = (OtherSeting_4) item;
            helper.setText(R.id.tv_title, item_4.getTitle());
            final TextView tvContent = helper.getView(R.id.tv_content);
            tvContent.setText(item_4.isOnlyShowSubTitle() ? item_4.getSubTitle() : TextUtils.isEmpty(item_4.getValue()) ? item_4.getSubTitle() : item_4.getValue());
            if (!item_4.isOnlyShowSubTitle()) {
                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showForcastDateDialog(tvContent, item_4);
                    }
                });
            } else {
                tvContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       if (mListener != null) {
                           mListener.onPicSelect();
                       }
                    }
                });
            }
        }
    }

    private void showForcastDateDialog(final TextView tvContent, final OtherSeting_4 item_4) {
        if (mForcastDatePickerView == null) {
            mForcastDatePickerView = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "CN"));
                    tvContent.setText(format.format(date));
                    item_4.setValue(format.format(date));
                }
            })
                    .setLayoutRes(R.layout.layout_pickerview_custom_time, new CustomListener() {

                        @Override
                        public void customLayout(View v) {
                            final TextView tvSubmit = v.findViewById(R.id.tv_finish);
                            TextView tvCancel = v.findViewById(R.id.tv_cancel);
                            tvSubmit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mForcastDatePickerView.returnData();
                                    mForcastDatePickerView.dismiss();
                                }
                            });
                            tvCancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mForcastDatePickerView.dismiss();
                                }
                            });
                        }
                    })
                    .setType(new boolean[]{true, true, true, false, false, false})
                    .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                    .setDividerColor(Color.DKGRAY)
                    .setContentTextSize(20)
                    .setBackgroundId(0x00000000)
                    .isDialog(true)
                    .build();
        }
        Calendar calendar = Calendar.getInstance();
        String[] split = tvContent.getText().toString().split("-");
        calendar.set(Integer.parseInt(split[0]), Integer.parseInt(split[1]) - 1, Integer.parseInt(split[2]));
        mForcastDatePickerView.setDate(calendar);
        mForcastDatePickerView.show();
    }

    public void setOnPicSelectListener(OnPicSelectListener listener) {
        this.mListener = listener;
    }

    public interface OnPicSelectListener {
        void onPicSelect();
    }
}
