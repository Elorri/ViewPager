package com.elorri.android.viewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PageFragment extends Fragment implements LoaderManager.LoaderCallbacks<Object[]> {

    private String uri;
    private String name;
    private int index;
    private int color;
    private int PAGE_LOADER = 1;
    private TextView textView;
    private Object[] mData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uri = getArguments().getString("uri");
        name = getArguments().getString("name");
        index = getArguments().getInt("index");
        color = getArguments().getInt("color");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container);
        textView = (TextView) view.findViewById(R.id.textview);
        return view;
    }

    public static Fragment newInstance(String uri, int index, String name, int color) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("uri", uri);
        bundle.putString("name", name);
        bundle.putInt("index", index);
        bundle.putInt("color", color);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().restartLoader(PAGE_LOADER, null, this);
    }

    @Override
    public Loader<Object[]> onCreateLoader(int id, Bundle args) {
        return new PageLoader(getContext(), uri, index, name, color);
    }

    @Override
    public void onLoadFinished(Loader<Object[]> loader, Object[] data) {
        mData=data;
        textView.setText(data[0] + " " + data[1] + " " + data[2]);
        textView.setBackgroundColor((int)data[3]);
        ((MainActivity) getActivity()).pageLoadedEvent(data);
    }

    @Override
    public void onLoaderReset(Loader<Object[]> loader) {

    }

    public Object[] getModel() {
        return mData;
    }
}
