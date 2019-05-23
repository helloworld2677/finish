package com.example.finish;

import android.support.v7.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class adaptorrecy extends RecyclerView.Adapter<adaptorrecy._Holder> {
    Context Ctx;
    ArrayList<ContentValues> ListPrpoduct;
    View.OnClickListener rowClick;
    adaptorrecy(Context CtxFromAct,ArrayList<ContentValues> recivedata,View.OnClickListener reciveClick){
        super();
        Ctx=CtxFromAct;
        ListPrpoduct=recivedata;
        rowClick=reciveClick;
    }

    @Override
    public int getItemCount() {
        return ListPrpoduct.size();
    }

    @Override
    public _Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _View=View.inflate(Ctx,R.layout.row_product,parent);
        return new _Holder(_View);
    }
//---------------------------------------------------

    class _Holder extends RecyclerView.ViewHolder{
        TextView Text_NameUser,Text_Price;
        View _View;
        _Holder(View _v){
            super(_v);
            _View=_v;
            Text_NameUser=_v.findViewById(R.id.Text_NameUser);
            Text_Price=_v.findViewById(R.id.Text_Price);

        }
        //------------------------------------------------
    }

    @Override
    public void onBindViewHolder(_Holder holder, int position) {
        try {
            ContentValues _contentValues=ListPrpoduct.get(position);
            holder.Text_NameUser.setText(_contentValues.getAsString("Name"));
            holder.Text_Price.setText(_contentValues.getAsString("Price"));
            holder._View.setTag(_contentValues);
            holder._View.setOnClickListener(rowClick);
        }catch (Exception Error){

        }
    }
}
