package com.example.hakankurt.turizmsikayet.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakankurt.turizmsikayet.AnaSayfa;
import com.example.hakankurt.turizmsikayet.AyarlarActivity;
import com.example.hakankurt.turizmsikayet.DBHelper;
import com.example.hakankurt.turizmsikayet.DestekActivity;
import com.example.hakankurt.turizmsikayet.R;
import com.example.hakankurt.turizmsikayet.UyeClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;
import static com.example.hakankurt.turizmsikayet.DBHelper.TAG;


public class Profil_fragment extends Fragment {

    TextView tvCikis,tvDestek,tvUserName,tvAyarlar;
    private DBHelper db;
    DatabaseReference myRef2;
    EditText etUserName,etUserMail;
    ImageView imProfil;
    FirebaseStorage storage= FirebaseStorage.getInstance();
    StorageReference referans=storage.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5profil, container, false);
        db=new DBHelper(getActivity());
        tvCikis=rootView.findViewById(R.id.tvCikis);
        tvDestek=rootView.findViewById(R.id.tvDestek);
        tvUserName=rootView.findViewById(R.id.tvUserName);
        tvAyarlar=rootView.findViewById(R.id.tvAyarlar);
        etUserName=rootView.findViewById(R.id.etUserName);
        etUserMail=rootView.findViewById(R.id.etUserMail);
        etUserMail.setText(GetUserMail());
        KullaniciAdiYaz();
        try{
            FotoGoster();
        }
        catch (Exception e)
        {

        }

        imProfil=rootView.findViewById(R.id.imProfil);
        imProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(Intent.ACTION_PICK);
               i.setType("image/*");
               startActivityForResult(i,1);
            }
        });


        try{
            String veriler=db.AyarGetir();
            String yaziBoyutu=veriler.substring(0,veriler.indexOf("-"));
            String arkaPlan=veriler.substring(veriler.indexOf("-")+1,veriler.indexOf("+"));
            String yaziRengi=veriler.substring(veriler.indexOf("+")+1,veriler.length());
            Log.e(TAG,"yazi boyutu: "+yaziBoyutu+" arkaplan: "+arkaPlan+" yazirengi: "+yaziRengi);
            tvCikis.setTextSize(Float.parseFloat(yaziBoyutu));
            tvCikis.setTextColor(Color.parseColor(yaziRengi));
            tvDestek.setTextSize(Float.parseFloat(yaziBoyutu));
            tvDestek.setTextColor(Color.parseColor(yaziRengi));
            tvAyarlar.setTextSize(Float.parseFloat(yaziBoyutu));
            tvAyarlar.setTextColor(Color.parseColor(yaziRengi));
            LinearLayout l=rootView.findViewById(R.id.linearLay);
            LinearLayout l2=rootView.findViewById(R.id.linearPro1);
            LinearLayout l3=rootView.findViewById(R.id.linearPro2);
            l.setBackgroundColor(Color.parseColor(arkaPlan));
            l2.setBackgroundColor(Color.parseColor(arkaPlan));
            l3.setBackgroundColor(Color.parseColor(arkaPlan));

        }
        catch (Exception e)
        {
            db.AyarKaydet("","","");
           // Toast.makeText(getActivity(),"Veritabanı yok. Yeni oluşturuldu."+e.getMessage().toString()+"-"+e.getStackTrace().toString(),Toast.LENGTH_LONG).show();
        }

        tvAyarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), AyarlarActivity.class));

            }
        });

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
        return currentFirebaseUser.getUid();
    }

    public String GetUserMail(){  // oturum açmış olan firebase kullanıcısının mailini döndürür.
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        return currentFirebaseUser.getEmail();
    }
    public void KullaniciAdiYaz()
    {
        myRef2= FirebaseDatabase.getInstance().getReference("Uyeler");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UyeClass uye = snapshot.getValue(UyeClass.class);
                        if (uye.getUyeID().equals(GetUserID()))
                        {
                            tvUserName.setText(uye.getUyeAdi());
                            etUserName.setText(uye.getUyeAdi());
                        }
                    }

                }catch(Exception e){
                    Log.e("profil fragment",e.getMessage().toString()+" "+e.getStackTrace().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {    // galeriden seçilen resim bu datanın içinde tutulur.
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK){    // seçilen fotoğraf firebase yüklenir.
            Uri uri=data.getData();
            StorageReference ref=referans.child(GetUserID());
            ref.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(getActivity(),"Fotoğraf Yüklendi.",Toast.LENGTH_LONG).show();
                        String fotoUrl=task.getResult().getDownloadUrl().toString();
                        FotoGoster();
                    }

                }
            });
    }
}

    private void FotoGoster() {
        StorageReference indir=referans.child(GetUserID());
        File localFile= null;
        try {
            localFile = File.createTempFile("images","jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final File finalLocalFile = localFile;
        indir.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Bitmap bitmap= BitmapFactory.decodeFile(finalLocalFile.getAbsolutePath());
                imProfil.setImageBitmap(bitmap);
            }
        });
    }
}
