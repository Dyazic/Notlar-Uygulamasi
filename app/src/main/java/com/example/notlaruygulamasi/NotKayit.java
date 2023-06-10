package com.example.notlaruygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotKayit extends AppCompatActivity {
    private Toolbar toolbar2;
    private EditText editTextNot1;
    private EditText editTextNot2;
    private EditText editTextDers;
    private Button buttonKaydet;
    private VeriTabani vt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_kayit);
        toolbar2=findViewById(R.id.toolbar2);
        editTextNot1=findViewById(R.id.editTextNot11);
        editTextNot2=findViewById(R.id.editTextNot12);
        editTextDers=findViewById(R.id.editTextDers);
        buttonKaydet=findViewById(R.id.buttonKaydet);
        vt=new VeriTabani(this);
        toolbar2.setTitle("Not Kaydet");
        setSupportActionBar(toolbar2);
        buttonKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dersAd=editTextDers.getText().toString().trim();
                String not1=editTextNot1.getText().toString().trim();
                String not2=editTextNot2.getText().toString().trim();
                if(TextUtils.isEmpty(dersAd)){
                    Toast.makeText(NotKayit.this, "Ders Adı Girin", Toast.LENGTH_SHORT).show();
                    return;
                } else if(TextUtils.isEmpty(not1)){
                    Toast.makeText(NotKayit.this, "Not 1", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(TextUtils.isEmpty(not2)){
                    Toast.makeText(NotKayit.this, "Not 2 ", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    new NotlarDao().notEkle(vt,dersAd,Integer.parseInt(not1),Integer.parseInt(not2));
                }

                startActivity(new Intent(NotKayit.this,MainActivity.class));
                finish();
            }
        });


    }
}