package com.example.hakankurt.turizmsikayet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by FURKAN on 3.12.2017.
 */

public class ListeAdapter extends ArrayAdapter<Sikayet>{


    public ListeAdapter(@NonNull Context context) {

        super(context, 0);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sikayet sikayet=getItem(position);

        if (convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.sikayet_listesi,parent,false);
        }
        TextView tvTarih=convertView.findViewById(R.id.tvTarih);
        TextView tvBaslik=convertView.findViewById(R.id.tvBaslik);
        TextView tvIcerik=convertView.findViewById(R.id.tvİcerik);

        tvTarih.setText(sikayet.Tarih);
        tvBaslik.setText(sikayet.Baslik);
        tvIcerik.setText(sikayet.Icerik);

        return convertView;
    }
}
