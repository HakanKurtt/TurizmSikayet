package com.example.hakankurt.turizmsikayet;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class ListeAdapterTab3 extends ArrayAdapter<Sikayet> {

    public ListeAdapterTab3(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sikayet sikayet=getItem(position);

        if (convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.tab3sikayetlerim_listesi,parent,false);
        }
        ImageView imDelete;
        TextView tvTarih=convertView.findViewById(R.id.tvTarih1);
        TextView tvBaslik=convertView.findViewById(R.id.tvBaslik1);
        TextView tvIcerik=convertView.findViewById(R.id.tvİcerik1);
        imDelete=convertView.findViewById(R.id.imDelete1);
        imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"tıklandı",Toast.LENGTH_LONG).show();
            }
        });
        String baslik=sikayet.Baslik;
        String icerik=sikayet.Icerik;
        if (baslik.length()>40)
        {
            baslik=baslik.substring(0,37)+"...";
        }
        if (icerik.length()>50)
        {
            icerik=icerik.substring(0,47)+"...";
        }
        tvTarih.setText(sikayet.Tarih);
        tvBaslik.setText(baslik);
        tvIcerik.setText(icerik);
        return convertView;
    }

}
