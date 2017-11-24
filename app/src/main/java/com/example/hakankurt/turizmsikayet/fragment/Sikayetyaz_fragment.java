package com.example.hakankurt.turizmsikayet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hakankurt.turizmsikayet.R;

/**
 * Created by HakanKurt on 24.11.2017.
 */

public class Sikayetyaz_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2sikayetyaz, container, false);
        return rootView;
    }
}
