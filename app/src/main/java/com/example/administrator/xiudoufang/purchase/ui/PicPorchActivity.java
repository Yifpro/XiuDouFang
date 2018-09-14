package com.example.administrator.xiudoufang.purchase.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.xiudoufang.R;
import com.example.administrator.xiudoufang.base.BaseFragment;
import com.example.administrator.xiudoufang.base.IActivityBase;
import com.example.administrator.xiudoufang.purchase.adapter.PicPorcAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/14
 */

public class PicPorchActivity extends AppCompatActivity implements IActivityBase {

    public static final String PIC_LIST = "pic_list";

    private ViewPager mViewPager;

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.activity_pic_porch;
    }

    @Override
    public void initView() {
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void initData() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        ArrayList<String> list = getIntent().getStringArrayListExtra(PIC_LIST);
        for (String photoUrl : list) {
            fragments.add(PicPorchSubFragment.newInstance(photoUrl));
        }
        PicPorcAdapter adapter = new PicPorcAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(adapter);
    }
}
