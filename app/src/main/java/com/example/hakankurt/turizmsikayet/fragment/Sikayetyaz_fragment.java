package com.example.hakankurt.turizmsikayet.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.Sikayet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;


public class Sikayetyaz_fragment extends android.support.v4.app.Fragment {
    EditText edtBaslik,edtIcerik;
    Spinner firmaListesi;
    Button btnGonder;
    String baslik,icerik;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2sikayetyaz, container, false);

        edtBaslik=(EditText) rootView.findViewById(R.id.edtBaslik);
        edtIcerik=(EditText) rootView.findViewById(R.id.edtIcerik);
        btnGonder=(Button) rootView.findViewById(R.id.btnGonder);

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
                            SikayetEkle(String.valueOf(id),baslik,icerik,"4",CurrentDate(),GetUserID() );
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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Sikayet sikayet=new Sikayet(Tarih,Baslik,Icerik,MarkaID,UyeID);
        myRef.child("Sikayetler").child(SikayetID).setValue(sikayet);
    }

    public void MesajYaz(String mesaj)
    {
        Toast.makeText(getActivity(),mesaj,Toast.LENGTH_LONG).show();
    }

    public String CurrentDate() // sistem tarihini string olarak getirir.
    {
        DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();
        return String.valueOf(dateFormat.format(date));
    }
}
