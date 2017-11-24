package com.example.hakankurt.turizmsikayet.fragment;

/**
 * Created by HakanKurt on 24.11.2017.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.example.hakankurt.turizmsikayet.R;

public class Search_fragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1search, container, false);
        return rootView;
    }
}
