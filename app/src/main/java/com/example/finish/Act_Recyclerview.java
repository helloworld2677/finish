package com.example.finish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class Act_Recyclerview extends AppCompatActivity {
    RecyclerView Recycler;
    ClassDatabase _ClaasesDatabase;
    Context Ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recyclerview);
        Recycler=findViewById(R.id.Recycler);
        _ClaasesDatabase=new ClassDatabase();
        Ctx=this;
        _ClaasesDatabase.CreateTable(Ctx);
        PrintData(null,null);
    }void PrintData(String Search,String Order){

        ArrayList<ContentValues> _ArrayList=_ClaasesDatabase.Select(Ctx,Search,4,Order);

        LinearLayoutManager _LinearLayoutManager=new LinearLayoutManager(Ctx);
        Recycler.setLayoutManager(_LinearLayoutManager);
        adaptorrecy _adaptorrecy=new adaptorrecy(Ctx,_ArrayList,rowClick);
        Recycler.setAdapter(_adaptorrecy);
    }View.OnClickListener rowClick=new View.OnClickListener() {
        @Override
        public void onClick(View _v) {
            ContentValues _ContentValues=(ContentValues)_v.getTag();
        }
    };
}
