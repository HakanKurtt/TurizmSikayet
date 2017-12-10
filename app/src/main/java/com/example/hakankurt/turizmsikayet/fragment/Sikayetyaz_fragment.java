package com.example.hakankurt.turizmsikayet.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.ListeAdapterTab1;
import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.SikayetClass;
import com.example.hakankurt.turizmsikayet.UyeClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class Sikayetyaz_fragment extends android.support.v4.app.Fragment {
    EditText edtBaslik,edtIcerik;
    Spinner firmaListesi;
    Button btnGonder;
    String baslik,icerik;
    DatabaseReference myRef2;
    String uyeAdi;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2sikayetyaz, container, false);

        edtBaslik=(EditText) rootView.findViewById(R.id.edtBaslik);
        edtIcerik=(EditText) rootView.findViewById(R.id.edtIcerik);
        btnGonder=(Button) rootView.findViewById(R.id.btnGonder);
        GetUserName();

        firmaListesi=(Spinner) rootView.findViewById(R.id.firmaListesi);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.Markalar,android.R.layout.simple_dropdown_item_1line);
        firmaListesi.setAdapter(adapter);

        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baslik=edtBaslik.getText().toString();
                icerik=edtIcerik.getText().toString();
                if (Kontrol())
                {
                    switch ((int) firmaListesi.getSelectedItemId())
                    {
                        case 0:  // metro seçildiyse
                        {
                            UUID id=UUID.randomUUID();
                            SikayetEkle(String.valueOf(id),baslik,icerik,"1",CurrentDate(),GetUserID());
                            MesajYaz("Şikayetiniz gönderildi.");
                        }
                        break;
                        case 1:  // vib seçildiyse
                        {
                            UUID id=UUID.randomUUID();
                            SikayetEkle(String.valueOf(id),baslik,icerik,"2",CurrentDate(),GetUserID());
                            MesajYaz("Şikayetiniz gönderildi.");
                        }
                        break;
                        case 2:  // efetur seçildiyse
                        {
                            UUID id=UUID.randomUUID();
                            SikayetEkle(String.valueOf(id),baslik,icerik,"3",CurrentDate(),GetUserID());
                            MesajYaz("Şikayetiniz gönderildi.");
                        }
                        break;
                        case 3:  // lüks karadeniz seçildiyse
                        {
                            UUID id=UUID.randomUUID();
                            SikayetEkle(String.valueOf(id),baslik,icerik,"4",CurrentDate(),GetUserID());
                            MesajYaz("Şikayetiniz gönderildi.");
                        }
                        break;
                        case 4:  // kamil koç seçildiyse
                        {
                            UUID id=UUID.randomUUID();
                            SikayetEkle(String.valueOf(id),baslik,icerik,"5",CurrentDate(),GetUserID());
                            MesajYaz("Kayıt Başarılı.");
                        }
                        break;
                        default:{
                            MesajYaz("Hata oluştu.");
                        }
                    }
                }
            }
        });

        return rootView;
    }

    public boolean Kontrol()    // şikayet başlığının ve içeriğinin boş olup  olmadığı kontrol ediliyor.
    {
        if(TextUtils.isEmpty(baslik))
        {
            edtBaslik.setError("Lütfen başlık giriniz.");
            edtBaslik.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(icerik))
        {
            edtIcerik.setError("Lütfen içerik giriniz.");
            edtIcerik.requestFocus();
            return false;
        }
        return true;

    }

    public String GetUserID(){  // oturum açmış olan firebase kullanıcısının id'sini döndürür.
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getUid();
    }

    public void SikayetEkle(String SikayetID,String Baslik,String Icerik,String MarkaID, String Tarih, String UyeID)
    { // firebase'e şikayet ekler

       FirebaseDatabase database1 = FirebaseDatabase.getInstance();
       DatabaseReference myRef1 = database1.getReference();
        SikayetClass sikayet=new SikayetClass(Tarih,Baslik,Icerik,MarkaID,UyeID,"",SikayetID);
        sikayet.setUyeAdi(uyeAdi);
        myRef1.child("Sikayetler").child(SikayetID).setValue(sikayet);
    }

    public void MesajYaz(String mesaj)
    {
        Toast.makeText(getActivity(),mesaj,Toast.LENGTH_LONG).show();
    }

    public void GetUserName() // oturum açmış olan kullanıcının Adını getirir.
    {
        myRef2=FirebaseDatabase.getInstance().getReference("Uyeler");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                       UyeClass uye = snapshot.getValue(UyeClass.class);
                        if (uye.getUyeID().equals(GetUserID()))
                        {
                            uyeAdi=uye.getUyeAdi();
                        }

                    }

                }catch(Exception e){
                    MesajYaz(e.getMessage().toString()+" "+e.getStackTrace().toString());
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

    public String CurrentDate() // sistem tarihini string olarak getirir.
    {
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();
        return String.valueOf(dateFormat.format(date));
    }
}
