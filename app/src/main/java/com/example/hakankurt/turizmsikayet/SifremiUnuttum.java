package com.example.hakankurt.turizmsikayet;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SifremiUnuttum extends AppCompatActivity implements View.OnClickListener{

    private EditText sifreUnuttumEdt;
    private Button mailGonderBtn;
    private ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sifremi_unuttum_layout);

        sifreUnuttumEdt=(EditText)findViewById(R.id.emailEditText);
        mailGonderBtn=(Button)findViewById(R.id.sifreSifirlaBtn);
        progressBar=(ProgressBar)findViewById(R.id.progressbarUnuttum);

        mailGonderBtn.setOnClickListener(this);

        mAuth=FirebaseAuth.getInstance();


    }

    private void resetPassword(){
        String email=sifreUnuttumEdt.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //kullanici email'i bos bıraktı mı?
            sifreUnuttumEdt.setError("Lütfen E-mail giriniz.");
            sifreUnuttumEdt.requestFocus();
            //fonskiyon calısmayı durdurur.
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //eger girilen email geçerli değilse
            sifreUnuttumEdt.setError("Lütfen geçerli bir e-mail giriniz.");
            sifreUnuttumEdt.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Sıfırlama e-maili gönderildi.",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Gönderme Başarısız.",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == mailGonderBtn){
            resetPassword();
        }
    }
}
