package com.example.hakankurt.turizmsikayet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.AnaSayfa;
import com.example.hakankurt.turizmsikayet.DBHelper;
import com.example.hakankurt.turizmsikayet.DestekActivity;
import com.example.hakankurt.turizmsikayet.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profil_fragment extends Fragment {

    TextView tvCikis,tvDestek,tvUserName;
    private DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5profil, container, false);
        db=new DBHelper(getActivity());
        tvCikis=rootView.findViewById(R.id.tvCikis);
        tvDestek=rootView.findViewById(R.id.tvDestek);
        tvUserName=rootView.findViewById(R.id.tvUserName);
        tvUserName.setText(db.DB_KullaniciAdiBul(GetUserID()));
        tvDestek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DestekActivity.class));
            }
        });
        tvCikis.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(),"Çıkış yapıldı.",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), AnaSayfa.class));
            }
        });
        return rootView;
    }
    public String GetUserID(){  // oturum açmış olan firebase kullanıcısının id'sini döndürür.
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getEmail();
    }
}
