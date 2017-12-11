package com.example.hakankurt.turizmsikayet;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.hakankurt.turizmsikayet.DBHelper.TAG;

public class ListeAdapterTab3 extends ArrayAdapter<SikayetClass> {

    public ListeAdapterTab3(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final SikayetClass sikayet=getItem(position);

        if (convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.tab3sikayetlerim_listesi,parent,false);
        }
        ImageView imDelete,imFirma1;
        TextView tvTarih=convertView.findViewById(R.id.tvTarih1);
        TextView tvBaslik=convertView.findViewById(R.id.tvBaslik1);
        TextView tvIcerik=convertView.findViewById(R.id.tvİcerik1);
        imDelete=convertView.findViewById(R.id.imDelete1);
        imDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(),"tıklandı indis: "+sikayet.getSikayetID(),Toast.LENGTH_LONG).show();
                if (SikayetSil(sikayet.getSikayetID()))
                {
                    Toast.makeText(getContext(),"Şikayet silindi.",Toast.LENGTH_LONG).show();
                }
            }
        });
        String baslik=sikayet.Baslik;
        String icerik=sikayet.Icerik;
        if (baslik.length()>40)
        {
            baslik=baslik.substring(0,50)+"...";
        }
        if (icerik.length()>50)
        {
            icerik=icerik.substring(0,60)+"...";
        }
        tvTarih.setText(sikayet.Tarih);
        tvBaslik.setText(baslik);
        tvIcerik.setText(icerik);

        imFirma1=convertView.findViewById(R.id.imFirma1);
        if (sikayet.getMarkaID().equals("1")) // metro
        {
            imFirma1.setImageResource(R.mipmap.metro);
        }
        else if (sikayet.getMarkaID().equals("2"))
        {
            imFirma1.setImageResource(R.mipmap.vib);
        }
        else if (sikayet.getMarkaID().equals("3"))
        {
            imFirma1.setImageResource(R.mipmap.efe);
        }
        else if (sikayet.getMarkaID().equals("4"))
        {
            imFirma1.setImageResource(R.mipmap.luks);
        }
        else
        {
            imFirma1.setImageResource(R.mipmap.kamil);
        }

        return convertView;
    }

    public boolean SikayetSil(String sikayetID)
    {
       try{
           DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Sikayetler");
           ref.child(sikayetID).removeValue();
           return true;
       }
       catch (Exception e)
       {
           Toast.makeText(getContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
           return false;
       }

    }

}
