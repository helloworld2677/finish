package com.example.finish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ExmpleItem;

import java.util.ArrayList;

public class Act_Recycler extends AppCompatActivity {
private RecyclerView mRecyclerView;
private RecyclerView.Adapter mAdapter;
private RecyclerView.LayoutManager mLayouManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recycler);
        ArrayList<ExmpleItem> exampleList=new ArrayList<>();
        exampleList.add(new ExmpleItem(R.drawable.ic_android,"Line 1","Line 2"));
        exampleList.add(new ExmpleItem(R.drawable.ic_audiotrack_black_24dp,"Line 3","Line 4"));
        exampleList.add(new ExmpleItem(R.drawable.ic_sunny,"Line 5","Line 6"));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayouManger = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);
        mRecyclerView.setLayoutManager(mLayouManger);
        mRecyclerView.setAdapter(mAdapter);
    }
}
