package com.example.finish;

import android.content.Context;
import android.widget.Toast;

public class Globalclass {
    void DisPlayToast(Context Ctx, String Msg){
        Toast.makeText(Ctx,Msg,Toast.LENGTH_SHORT).show();
    }
}