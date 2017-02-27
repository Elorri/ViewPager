package com.elorri.android.viewpager;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

public class PageLoader extends AsyncTaskLoader<Object[]> {


    private final Context mContext;
    private final Resources mResources;
    private final String mUri;
    private final String mName;
    private final int mIndex;
    private final int mColor;

    public PageLoader(Context context, String uri, int index, String name, int color) {
        super(context);
        mContext = context;
        mResources = mContext.getResources();
        mUri=uri;
        mIndex=index;
        mName=name;
        mColor=color;
        onContentChanged();
    }

    @Override
    public Object[] loadInBackground() {
        longTask();
        return new Object[]{mUri, mIndex, mName, mColor };
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
