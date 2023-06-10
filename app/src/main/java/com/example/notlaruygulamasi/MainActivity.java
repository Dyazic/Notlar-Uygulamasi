package com.example.notlaruygulamasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private ArrayList<Notlar> notlarArrayList;
    private NotlarAdapter adapter;
    private VeriTabani vt;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerView);
        fab=findViewById(R.id.fab);
        toolbar.setTitle("Notlar");
        setSupportActionBar(toolbar);
        vt=new VeriTabani(this);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notlarArrayList=new ArrayList<>();
        notlarArrayList=new NotlarDao().tumNotlat(vt);

        adapter=new NotlarAdapter(this,notlarArrayList);
        recyclerView.setAdapter(adapter);

        double toplam=0.0;
        for(Notlar n:notlarArrayList){
            toplam=toplam+(n.getNot1()+n.getNot2())/2;
        }
        toolbar.setSubtitle("Ortalama : "+(toplam/notlarArrayList.size()));



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NotKayit.class));
            }
        });


    }

    @Override
    public void onBackPressed() {
       Intent intent =new Intent(Intent.ACTION_MAIN);
       intent.addCategory(Intent.CATEGORY_HOME);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       startActivity(intent);
    }
}