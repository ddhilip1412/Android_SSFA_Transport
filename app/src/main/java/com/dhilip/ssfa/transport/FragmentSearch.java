package com.dhilip.ssfa.transport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Dhilip on 6/24/2016.
 */


public class FragmentSearch extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    public static FragmentSearch newInstance()
    {
        return new FragmentSearch();
    }
}
