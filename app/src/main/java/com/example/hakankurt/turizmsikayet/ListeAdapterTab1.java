package com.example.hakankurt.turizmsikayet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

/**
 * Created by FURKAN on 3.12.2017.
 */

public class ListeAdapterTab1 extends ArrayAdapter<Sikayet>{


    public ListeAdapterTab1(@NonNull Context context) {

        super(context, 0);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Sikayet sikayet=getItem(position);

        if (convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.tab1sikayet_listesi,parent,false);
        }
        TextView tvTarih=convertView.findViewById(R.id.tvTarih);
        TextView tvBaslik=convertView.findViewById(R.id.tvBaslik);
        TextView tvIcerik=convertView.findViewById(R.id.tvİcerik);
        TextView tvUser=convertView.findViewById(R.id.tvUser);

        tvTarih.setText(sikayet.Tarih);
        tvBaslik.setText(sikayet.Baslik);
        tvIcerik.setText(sikayet.Icerik);
        tvUser.setText(GetUserName(sikayet.getUyeID()));
        return convertView;
    }

    public String GetUserName(String uuid){  // firebase kullanıcı id'sini döndürür.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       // if (user!=null)
        //{
            for(UserInfo profile:user.getProviderData())
            {
                if (profile.getUid().equals(uuid))
                {
                    return profile.getDisplayName();
                }
            }
       // }
        return null;
    }

}
