package com.example.finish;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.finish.Globalclass;

import java.util.ArrayList;

public class ClassDatabase {
    String DatabaseName="Products";
    SQLiteDatabase SQL;
    Globalclass _Globalclass=new Globalclass();
    void CreateTable(Context Ctx){
        try {
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            SQL.execSQL("CREATE TABLE IF NOT EXISTS TableProduct(Id_Auto INTEGER PRIMARY KEY AUTOINCREMENT,Priceproduct INTEGER,Nameproduct TEXT);");
            _Globalclass.DisPlayToast(Ctx,"Create Database");
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }void Insert(Context Ctx,int Priceproduct,String Nameproduct){
        try {
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            SQL.execSQL("INSERT INTO TableProduct(Priceproduct,Nameproduct)VALUES("+Priceproduct+",'"+Nameproduct+"')");
            _Globalclass.DisPlayToast(Ctx,"Insert");
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }void Delete(Context Ctx,String Id_Productx){
        try {
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            SQL.execSQL("DELETE FROM TableProduct WHERE Id_Auto="+Id_Productx);
            _Globalclass.DisPlayToast(Ctx,"Delete");
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }void Update(Context Ctx, ContentValues Data){
        try {
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            SQL.execSQL("UPDATE TableProduct SET Id_Auto=\"+Data.getAsString(\"Id_Auto\")+\",Priceproduct="+Data.getAsString("Priceproduct")+",Nameproduct='"+Data.getAsString("Nameproduct")+"'");
            _Globalclass.DisPlayToast(Ctx,"Update");
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
    }

    ArrayList<ContentValues> Select(Context Ctx,String Search,int _Offset,String _Order){
        ArrayList<ContentValues> _ArrayList=new ArrayList<>();
        String _Limit=(" LIMIT "+_Offset+",4");
        try {
            if (Search==null)
                Search="";
            if (_Order==null)
                _Order="";
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            Cursor _Cursor=SQL.rawQuery("SELECT Id_Auto,Priceproduct,Nameproduct FROM TableProduct"+Search+_Order,null);
            if (_Cursor.moveToFirst()){
                do {
                    ContentValues _ContentValues=new ContentValues();
                    _ContentValues.put("Id_Auto",_Cursor.getString(0));
                    _ContentValues.put("Priceproduct",_Cursor.getString(1));
                    _ContentValues.put("Nameproduct",_Cursor.getString(2));
                    _ArrayList.add(_ContentValues);
                }while (_Cursor.moveToNext());
            }_Globalclass.DisPlayToast(Ctx,"Select");
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
        return _ArrayList;
    }int Sum_Count(Context Ctx,String Search,String SumOrCount){
        int Result=0;
        try {
            if (Search==null)
                Search="";
            SQL=Ctx.openOrCreateDatabase(DatabaseName,Context.MODE_PRIVATE,null);
            Cursor _Cursor=SQL.rawQuery("SELECT "+SumOrCount+" FROM TableProduct "+Search,null);
            if (_Cursor.moveToFirst())
                Result=_Cursor.getInt(0);
            SQL.close();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Ctx,Error.getMessage());
        }
        return Result;
    }
 }
