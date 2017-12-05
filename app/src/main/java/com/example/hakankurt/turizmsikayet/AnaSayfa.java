package com.example.hakankurt.turizmsikayet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnaSayfa extends BaseClass {

    Button girisYapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ana_sayfa);

        girisYapBtn=(Button)findViewById(R.id.girisYapBtn);

        girisYapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent girisIntent=new Intent(getApplicationContext(),GirisYap.class);
                startActivity(girisIntent);
            }
        });
    }
}
