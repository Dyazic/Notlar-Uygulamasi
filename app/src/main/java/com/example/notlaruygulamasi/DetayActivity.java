package com.example.notlaruygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class DetayActivity extends AppCompatActivity {
    private EditText editTextNot1;
    private EditText editTextNot2;
    private EditText editTextDers;

    private Toolbar toolbarDetay;
    private Notlar not;
    private VeriTabani vt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);
        toolbarDetay=  findViewById(R.id.toolbarDetay);
        editTextNot1=findViewById(R.id.editTextNot21);
        editTextNot2=findViewById(R.id.editTextNot22);
        editTextDers=findViewById(R.id.editTextDersAdi1);
        vt=new VeriTabani(this);
        not= (Notlar) getIntent().getSerializableExtra("nesne");
       editTextDers.setText(String.valueOf(not.getDers_adi()));
         editTextNot1.setText(String.valueOf(not.getNot1()));
         editTextNot2.setText(String.valueOf(not.getNot2()));



        toolbarDetay.setTitle("Not Detay");
        setSupportActionBar(toolbarDetay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sil:
                Snackbar.make(toolbarDetay,"Silinsin mi? ",Snackbar.LENGTH_SHORT).setAction("Evet", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new NotlarDao().notSil(vt,not.getNot_id());
                        startActivity(new Intent(DetayActivity.this,MainActivity.class));
                        finish();

                    }
                }).show();
                return true;
            case R.id.action_guncelle:
                String dersAd=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();
                if(TextUtils.isEmpty(dersAd)){
                    Toast.makeText(DetayActivity.this, "Ders Adı Girin", Toast.LENGTH_SHORT).show();
                    return false;
                } else if(TextUtils.isEmpty(not1)){
                    Toast.makeText(DetayActivity.this, "Not 1", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if(TextUtils.isEmpty(not2)){
                    Toast.makeText(DetayActivity.this, "Not 2 ", Toast.LENGTH_SHORT).show();
                    return false;
                }else{
                    new NotlarDao().notGuncelle(vt,not.getNot_id(),dersAd,Integer.parseInt(not1),Integer.parseInt(not2));
                }
                startActivity(new Intent(DetayActivity.this,MainActivity.class));
                finish();

                return true;
            default:
                return false;


        }

    }
}