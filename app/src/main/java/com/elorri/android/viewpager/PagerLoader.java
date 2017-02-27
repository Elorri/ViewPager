package com.elorri.android.viewpager;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PagerLoader extends AsyncTaskLoader<List<Object[]>> {

    private final Context mContext;
    private final Resources mResources;

    public PagerLoader(Context context) {
        super(context);
        mContext = context;
        mResources = mContext.getResources();
        onContentChanged();
    }

    @Override
    public List<Object[]> loadInBackground() {
        longTask();
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"content://page1", "Page1 : blue", mResources.getColor(android.R.color
                .holo_blue_bright)});
        list.add(new Object[]{"content://page2", "Page2 : orange", mResources.getColor(android.R
                .color.holo_orange_dark)});
        list.add(new Object[]{"content://page3", "Page3 : purple", mResources.getColor(android.R
                .color.holo_purple)});
        return list;
    }

    private void longTask() {
        try {
            Thread.sleep(1000l);
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "1s");
            Thread.sleep(1000l);
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "2s");
            Thread.sleep(1000l);
            Log.e("App", Thread.currentThread().getStackTrace()[2] + "3s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(takeContentChanged()){
            forceLoad();
        }
    }
}
