package com.example.hakankurt.turizmsikayet.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.ListeAdapterTab3;
import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.Sikayet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Sikayetlerim_fragment extends Fragment {

    ArrayList<Sikayet> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ListView list;
    ImageView imDelete;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3sikayetlerim, container, false);
        list=rootView.findViewById(R.id.listSikayetlerim);
        KullaniciSikayetleriGetir();



        return rootView;
    }

    private void KullaniciSikayetleriGetir() {
            myRef=FirebaseDatabase.getInstance().getReference("Sikayetler");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try{
                        ListeAdapterTab3 adapter=new ListeAdapterTab3(getActivity());
                        for (DataSnapshot snapshot:dataSnapshot.getChildren())
                        {
                            Sikayet sikayetler=snapshot.getValue(Sikayet.class);
                            if (sikayetler.getUyeID().equals(GetUserID()))
                            {
                                adapter.add(sikayetler);
                            }
                        }
                        list.setAdapter(adapter);
                    }
                    catch (Exception e){
                        MesajYaz("SikayetlerimFragment hatası "+e.getMessage().toString()+" "+e.getStackTrace().toString());
                        Log.e("Hata",e.getMessage().toString()+" "+e.getStackTrace().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }
    public void MesajYaz(String s)
    {
        Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
    }

    public String GetUserID(){  // oturum açmış olan firebase kullanıcısının id'sini döndürür.
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getUid();
    }
}
