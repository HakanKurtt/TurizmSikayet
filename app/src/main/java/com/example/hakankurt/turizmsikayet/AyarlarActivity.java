package com.example.hakankurt.turizmsikayet;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import static com.example.hakankurt.turizmsikayet.DBHelper.TAG;

public class AyarlarActivity extends AppCompatActivity {

    private android.support.v7.view.ActionMode actionMode;
    EditText etYaziBoyutu,etArkaPlan,etYaziRengi;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);

        MyActionModeCallBack callBack=new MyActionModeCallBack();
        actionMode=startSupportActionMode(callBack);

        db=new DBHelper(this);

        etArkaPlan=findViewById(R.id.etArkaPlanRengi);
        etYaziBoyutu=findViewById(R.id.etYaziBoyutu);
        etYaziRengi=findViewById(R.id.etYaziRengi);

      try{
          String veriler=db.AyarGetir();
          String yaziBoyutu=veriler.substring(0,veriler.indexOf("-"));
          String arkaPlan=veriler.substring(veriler.indexOf("-")+1,veriler.indexOf("+"));
          String yaziRengi=veriler.substring(veriler.indexOf("+")+1,veriler.length()-1);
          etYaziBoyutu.setText(yaziBoyutu);
          etArkaPlan.setText(arkaPlan);
          etYaziRengi.setText(yaziRengi);
      }
      catch (Exception e){
          Toast.makeText(getApplicationContext(),"Ayarlar activiy de hata var.",Toast.LENGTH_LONG).show();
      }

        Button btnKaydet=findViewById(R.id.btnKaydet);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if (db.AyarGuncelle(etYaziBoyutu.getText().toString(),etYaziRengi.getText().toString(),etArkaPlan.getText().toString()))
              {
                  Toast.makeText(getApplicationContext(),"Güncellendi.",Toast.LENGTH_LONG).show();
              }
                           }
        });
    }

    private class MyActionModeCallBack implements ActionMode.Callback, android.support.v7.view.ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_ayarlar,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {
            startActivity(new Intent(AyarlarActivity.this,MainActivity.class));
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.menu_ayarlar,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            startActivity(new Intent(AyarlarActivity.this,MainActivity.class));
        }
    }
}
