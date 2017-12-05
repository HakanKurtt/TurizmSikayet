package com.example.hakankurt.turizmsikayet.fragment;

/**
 * Created by HakanKurt on 24.11.2017.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.ListeAdapter;
import com.example.hakankurt.turizmsikayet.MainActivity;
import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.Sikayet;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Search_fragment extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1search, container, false);
        AutoCompleteTextView tvAuto=rootView.findViewById(R.id.tvAuto);
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>((MainActivity)getActivity(),android.R.layout.simple_dropdown_item_1line,getResources().getStringArray(R.array.Markalar));
        tvAuto.setAdapter(arrayAdapter);
        try{

        ListView list=rootView.findViewById(R.id.listView);
            ListeAdapter adapter=new ListeAdapter(getActivity());
         for(int i=0;i<6;i++)
            {
                adapter.add(new Sikayet("03.12.2017","Kısa Şikayet Başlık Yazısı","Biraz uzun şikayet içeriği","MarkaID","UyeID"));
            }
            list.setAdapter(adapter);
      }
      catch (Exception e)
      {
          Toast.makeText(getActivity(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
      }
        return rootView;
    }
}
