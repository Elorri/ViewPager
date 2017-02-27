package com.elorri.android.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PagerFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Object[]>>, ViewPager.OnPageChangeListener {

    public static final int LOADER = 0;
    private MyPagerAdapter mPagerAdapter;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mPagerAdapter = new MyPagerAdapter(getFragmentManager(), null);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mPagerAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().restartLoader(LOADER, null, this);
    }

    @Override
    public Loader<List<Object[]>> onCreateLoader(int id, Bundle args) {
        return new PagerLoader(getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Object[]>> loader, List<Object[]> data) {
        mPagerAdapter.swapData(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Object[]>> loader) {

    }

    public void selectPage(int index) {
        mViewPager.setCurrentItem(index);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PageFragment pageFragment = (PageFragment) mPagerAdapter.getItem(position);
        Object[] pageFragmentModel = pageFragment.getModel();
        ((MainActivity) getActivity()).pageSelectedEvent(pageFragment, pageFragmentModel);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
