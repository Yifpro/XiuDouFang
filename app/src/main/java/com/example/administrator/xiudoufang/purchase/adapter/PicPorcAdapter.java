package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.xiudoufang.base.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2018/9/14
 */

public class PicPorcAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;

    public PicPorcAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
