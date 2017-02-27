package com.elorri.android.viewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String requestedUri="content://page1";

    private PenFragment mPenFragment;
    private PagerFragment mPagerFragment;
    private PageFragment mPageSelectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPenFragment= (PenFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_pen);
        mPagerFragment= (PagerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_pager);
    }

    public void pageLoadedEvent(Object[] data) {
        if(requestedUri.equals(data[0])){
            mPagerFragment.selectPage((int) data[1]);
        }
    }

    public void pageSelectedEvent(PageFragment pageFragment, Object[] pageFragmentModel) {
        mPageSelectedFragment=pageFragment;
        mPenFragment.updateUI(pageFragmentModel);
    }
}
