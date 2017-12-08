package com.example.hakankurt.turizmsikayet;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class BaseClass extends AppCompatActivity {
    public void MesajYaz(String s)
    {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}
