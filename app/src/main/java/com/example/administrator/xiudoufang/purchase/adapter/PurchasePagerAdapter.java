package com.example.administrator.xiudoufang.purchase.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.xiudoufang.base.BaseFragment;

import java.util.List;

/**
 * Created by pc1994 on 2018/3/22.
 */

public class PurchasePagerAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> mFragments;
    private String[] titles;

    public PurchasePagerAdapter(FragmentManager fm, List<BaseFragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
