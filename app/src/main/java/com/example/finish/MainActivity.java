package com.example.finish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button Btn_Sign,Btn_Login,btn_User;
    DrawerLayout Draw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btn_User=(Button)findViewById(R.id.btn_User);
        Btn_Sign =findViewById(R.id.Btn_Sign);
        Btn_Login =findViewById(R.id.Btn_Login);
        DisPlayToast("Welcome");
        CheckUser();
    }void CheckUser() {
        SharedPreferences _SharedPreferences=getSharedPreferences("SignUser", Context.MODE_PRIVATE);
        String _Email =_SharedPreferences.getString("EmailUser", null);
        String _Pass =_SharedPreferences.getString("PassUser", null);
        if (_Email==null&&_Pass==null){
            Btn_Login.setEnabled(false);
        }else
            Btn_Sign.setText("Edit");



    }void DisPlayToast(String Msg){
        Toast.makeText(this,Msg,Toast.LENGTH_LONG).show();
    }public void BtnLogin(View _v){
        Intent Intent=new Intent(this,Act_Login.class);
        startActivity(Intent);
    }public void BtnSign(View _v){
        Intent Intent=new Intent(this,Act_Sign.class);
        startActivity(Intent);
    }/*public void MenuClick(View _v){
        //  if (Draw.isDrawerOpen(Gravity.LEFT))
        if (_v.getTag().equals("2"))


            Draw.closeDrawers();
        else

            Draw.openDrawer(Gravity.LEFT);

    }*/
  /*  public void Data(View _v){
        Intent Intent=new Intent(this,Act_Database.class);
        startActivity(Intent);
    } */
  public void Recy(View _v){
        Intent Intent=new Intent(this,Act_Recyclerview.class);
        startActivity(Intent);
    }

}
