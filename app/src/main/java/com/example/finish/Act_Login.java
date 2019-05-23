package com.example.finish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Act_Login extends AppCompatActivity {
    EditText Edt_Email,Edt_Pass;
    ImageView Img_Back;
    Button Btn_Ok,Btn_Cancel;
    Globalclass _Globalclass=new Globalclass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
      /* Btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _Email = Edt_Email.getText().toString().trim();
                String _Pass=Edt_Pass.getText().toString().trim();
                if (_Email.equals("Your Email")&& _Pass.equals("Your Password")){
                    Sh.putString("Email",_Email);
                    Sh.putString("Pass",_Pass);
                    Sh.commit();
                    finish();
                    startActivity(new Intent(Act_Login.this,MainActivity.class));

                }else
                    _Globalclass.DisPlayToast(Act_Login.this,"Check your info");
            }
        });*/
        Init();
    }void Init(){
        Edt_Email=findViewById(R.id.Edt_Email);
        Edt_Pass=findViewById(R.id.Edt_Pass);
        Img_Back=findViewById(R.id.Img_Back);
        Btn_Ok= findViewById(R.id.Btn_Ok);
        Btn_Cancel=findViewById(R.id.Btn_Cancel);
    }public void BtnOk(View _v){
        try {
            if (!ValidateEmail() | !ValidatePass()){
                return;
            }
            String input="Email: "+Edt_Email.getText().toString();
            input +="\n";
            input +="Password: "+Edt_Pass.getText().toString();
            Toast.makeText(this,input,Toast.LENGTH_SHORT).show();
            Intent _Intent=new Intent(this,Act_Products.class);
            startActivity(_Intent);


        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Login.this,Error.getMessage());
        }
    }private boolean ValidateEmail() {
        String _Email = Edt_Email.getText().toString().trim();
        if (_Email.isEmpty()){
            Edt_Email.setError("Field can't be empty");
            return false;
        }
            else if (!Patterns.EMAIL_ADDRESS.matcher(_Email).matches()){
                Edt_Email.setError("Please enter a valid email address");
                return false;
        }
            else {
            Edt_Email.setError(null);
            return true;
        }
    }private boolean ValidatePass(){
        String _Pass=Edt_Pass.getText().toString().trim();
        if (_Pass.isEmpty()){
            Edt_Pass.setError("Field can't be empty");
            return false;
        }else {
            Edt_Pass.setError(null);
            return true;
        }
    }public void Login(View _v){
        try {
            String _Email = Edt_Email.getText().toString().trim();
            String _Pass=Edt_Pass.getText().toString().trim();
            if (_Email.length()<24){
                DisPlayToast("Email text not true");
                return;
            }
            else if (_Pass.length()<4){
                DisPlayToast("Password number not enough");
                return;
            }
            SharedPreferences _SharedPreferences=getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            String _EmailUser = _SharedPreferences.getString("EmailUser",null);
            String _PassUser=_SharedPreferences.getString("PassUser",null);
            if (!_Email.equals(_EmailUser)){
                DisPlayToast("Email not true");
                return;
            }else if (!_Pass.equals(_PassUser)){

                _Globalclass.DisPlayToast(Act_Login.this,"Password not true");
                return;
            }else {
                //---------------------------------------







                //---------------------------------------
            }
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Login.this,Error.getMessage());
        }
    }void DisPlayToast(String Msg){
        Toast.makeText(this,Msg,Toast.LENGTH_SHORT).show();
    }
    public void Cancel(View _v){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }public void Img_Back(View _v){
        if (_v.getTag().equals("Back")){
            startActivity(new Intent(this,MainActivity.class));
        }
    }

}

