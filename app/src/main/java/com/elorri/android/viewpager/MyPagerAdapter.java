package com.elorri.android.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {


    private final List<Object[]> mData;

    public MyPagerAdapter(FragmentManager fragmentManager, List<Object[]> data) {
        super(fragmentManager);
        mData = data;
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance((String) mData.get(position)[0], position, (String) mData.get
                (position)[1], (int) mData.get(position)[2]);
    }


    public void swapData(List<Object[]> data) {
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
}
