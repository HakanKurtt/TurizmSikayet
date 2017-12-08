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

public class GirisYap extends BaseClass implements View.OnClickListener{

    private TextView uyeOlTextView;
    private Button signInButton;
    private TextView sifreUnutTextView;

    FirebaseAuth mAuth;
    private EditText kullaniciMailEdt;
    private EditText kullaniciPswrdEdt;
    private EditText kullaniciIsimEdt;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giris_yap);

        mAuth=FirebaseAuth.getInstance();

        /*kullaniciIsimEdt=(EditText)findViewById(R.id.kullaniciAdEdt);*/
        kullaniciMailEdt=(EditText)findViewById(R.id.epostaEdt);
        kullaniciPswrdEdt=(EditText)findViewById(R.id.sifreEdt);
        progressBar=(ProgressBar)findViewById(R.id.progressbarGiris);

        signInButton=(Button)findViewById(R.id.girisBtn);
        uyeOlTextView=(TextView)findViewById(R.id.uyeOlText);
        sifreUnutTextView=(TextView)findViewById(R.id.sifreUnuttumTxt);

        signInButton.setOnClickListener(this);
        uyeOlTextView.setOnClickListener(this);
        sifreUnutTextView.setOnClickListener(this);






    }

    private void userLogin(){
        String email=kullaniciMailEdt.getText().toString().trim();
        String password=kullaniciPswrdEdt.getText().toString().trim();
        /*String kullaniciIsim=kullaniciIsimEdt.getText().toString();*/



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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //giris islemi başarıyla gerçekleştiyse
                    Intent intent=new Intent(GirisYap.this,MainActivity.class);
                    //Tüm açık aktiviteleri kapat. Activity stackinin en üstüne taşı.
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.uyeOlText:
                Intent uyeolIntent=new Intent(getApplicationContext(),UyeOl.class);
                startActivity(uyeolIntent);
                break;
            case R.id.girisBtn:
                userLogin();
              // startActivity(new Intent(GirisYap.this,MainActivity.class));
              //  finish();
                break;
            case R.id.sifreUnuttumTxt:
                Intent sifreUnutIntent=new Intent(GirisYap.this,SifremiUnuttum.class);
                startActivity(sifreUnutIntent);
        }
    }



}
