package com.example.hakankurt.turizmsikayet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class UyeOl extends BaseClass implements View.OnClickListener{

    private EditText kullaniciIsimEdt;
    private EditText kullaniciMailEdt;
    private EditText kullaniciPswrdEdt;
    private Button signUpButton;
    private TextView signInTextView;
    private ProgressBar progressBar;


    //Firebase authentication;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uye_ol_layout);



        kullaniciIsimEdt=(EditText)findViewById(R.id.kullaniciAdEdt);
        kullaniciMailEdt=(EditText)findViewById(R.id.kullaniciEpostaEdt);
        kullaniciPswrdEdt=(EditText)findViewById(R.id.kullaniciSifreEdt);
        signUpButton=(Button)findViewById(R.id.uyeOlBtn);
        signInTextView=(TextView)findViewById(R.id.zatenUyeTextView);
        progressBar=(ProgressBar)findViewById(R.id.progressbar) ;

        signUpButton.setOnClickListener(this);
        signInTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    private void registerUser(){
        String email=kullaniciMailEdt.getText().toString().trim();
        String password=kullaniciPswrdEdt.getText().toString().trim();
        String kullaniciIsim=kullaniciIsimEdt.getText().toString();

        if(TextUtils.isEmpty(kullaniciIsim)){
            //kullanici adsoyad kısmını bos bıraktı mı?
            kullaniciIsimEdt.setError("Lütfen Ad-Soyad giriniz.");
            kullaniciIsimEdt.requestFocus();
            //fonskiyon calısmayı durdurur.
            return;
        }
        if(TextUtils.isEmpty(email)){
            //kullanici email'i bos bıraktı mı?
            kullaniciMailEdt.setError("Lütfen E-mail giriniz.");
            kullaniciMailEdt.requestFocus();
            //fonskiyon calısmayı durdurur.
            return;
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //eger girilen email geçerli değilse
            kullaniciMailEdt.setError("Lütfen geçerli bir e-mail giriniz.");
            kullaniciMailEdt.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //kullanici sifre kısmını bos bıraktı mı?
            kullaniciPswrdEdt.setError("Lütfen Sifre giriniz.");
            kullaniciPswrdEdt.requestFocus();
            //fonskiyon calısmayı durdurur.
            return;
        }
        if(password.length()<6){
            //eger girilen sifre 6 karakterden az ise
            kullaniciPswrdEdt.setError("Minumum şifre karakter sayısı 6 olmalıdır.");
            kullaniciPswrdEdt.requestFocus();
            return;
        }
        // Girdiler tamam ise kullanıcıya beklerken progressbar gosterilecek.

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    //giris islemi başarıyla gerçekleştiyse
                    Intent intent=new Intent(UyeOl.this,MainActivity.class);
                    //Tüm açık aktiviteleri kapat. Activity stackinin en üstüne taşı.
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        //eğer email adresi onceden kayıtlıysa
                        Toast.makeText(getApplicationContext(),"Zaten üyesiniz.",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT);
                    }
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == signUpButton){
            //eger kullanici "uye ol" butonuna basarsa burası calısır.
            registerUser();
        }

        if(view == signInTextView){
            // eger kullanici "zaten uyeyime" basarsa burası calısır.
            Intent signInIntent=new Intent(UyeOl.this,GirisYap.class);
            startActivity(signInIntent);
        }

    }

}
