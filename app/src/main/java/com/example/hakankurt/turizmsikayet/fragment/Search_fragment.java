package com.example.hakankurt.turizmsikayet.fragment;

/**
 * Created by HakanKurt on 24.11.2017.
 */

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.ListeAdapterTab1;
import com.example.hakankurt.turizmsikayet.MainActivity;
import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.SikayetClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search_fragment extends Fragment{
    AutoCompleteTextView tvAuto;
    ArrayList<SikayetClass> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ListView list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.tab1search, container, false);

        AutoCompleteTextView tvAuto=rootView.findViewById(R.id.tvAuto);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>((MainActivity)getActivity(),android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Markalar));

        tvAuto.setAdapter(arrayAdapter);

        list=rootView.findViewById(R.id.listView);

        tvAuto=rootView.findViewById(R.id.tvAuto);

        final AutoCompleteTextView finalTvAuto = tvAuto;

        tvAuto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String secilen= finalTvAuto.getText().toString();

                if(secilen.equals("Metro"))
                    SikayetGetir("1");
              if(secilen.equals("VİB"))
                    SikayetGetir("2");
                if(secilen.equals("Efe Tur"))
                    SikayetGetir("3");
                if(secilen.equals("Lüks Karadeniz"))
                    SikayetGetir("4");
                if(secilen.equals("Kamil Koç"))
                    SikayetGetir("5");

            }
        });

        return rootView;
    }

    public void SikayetGetir(final String markaId)
    {
        myRef=FirebaseDatabase.getInstance().getReference("Sikayetler");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    ListeAdapterTab1 adapter = new ListeAdapterTab1(getActivity());
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                       SikayetClass sikayetler = snapshot.getValue(SikayetClass.class);
                       String id = snapshot.getKey();
                        sikayetler.setSikayetID(id);
                        if (markaId.equals(sikayetler.getMarkaID()))
                        {
                            adapter.add(sikayetler);
                        }

                    }
                    list.setAdapter(adapter);

                }catch(Exception e){
                    MesajYaz("SearchFragment hatası "+e.getMessage().toString()+" "+e.getStackTrace().toString());
                    Log.e("Hata",e.getMessage().toString()+" "+e.getStackTrace().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public void MesajYaz(String s)
    {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

}
