package com.example.notlaruygulamasi;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NotlarDao {

    public ArrayList<Notlar> tumNotlat(VeriTabani vt){
        ArrayList<Notlar> notlarArrayList=new ArrayList<>();
        SQLiteDatabase db=vt.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM notlar",null);
        while (c.moveToNext()){
            @SuppressLint("Range") Notlar n=new Notlar(c.getInt(c.getColumnIndex("not_id"))
            , c.getString(c.getColumnIndex("ders_ad"))
            ,c.getInt(c.getColumnIndex("not_1"))
            ,c.getInt(c.getColumnIndex("not_2")));
            notlarArrayList.add(n);

        }
        db.close();
        return notlarArrayList;

    }
    public void notSil(VeriTabani vt,int not_id){
        SQLiteDatabase db=vt.getWritableDatabase();
        db.delete("notlar","not_id=?",new String[]{String.valueOf(not_id)});
        db.close();

    }
    public void notEkle(VeriTabani vt,String ders_ad,int not1,int not2){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues vales=new ContentValues();
        vales.put("ders_ad",ders_ad);
        vales.put("not_1",not1);
        vales.put("not_2",not2);
        db.insertOrThrow("notlar",null,vales);
        db.close();

    }
    public void notGuncelle(VeriTabani vt,int not_id, String ders_ad,int not1,int not2){
        SQLiteDatabase db=vt.getWritableDatabase();
        ContentValues vales=new ContentValues();
        vales.put("ders_ad",ders_ad);
        vales.put("not_1",not1);
        vales.put("not_2",not2);
        db.update("notlar",vales,"not_id=?",new String[]{String.valueOf(not_id)});
        db.close();

    }
}
