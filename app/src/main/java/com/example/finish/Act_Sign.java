package com.example.finish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Act_Sign extends AppCompatActivity {
    //Pattern paiin bayad az jens (regex) bashe
  /*  private static final Pattern PASSWORD_PATTERN =
            Pattern.compile( "^"+
                    "(?=.*[a-zA-Z])"+
                    "(?=.*[@#$%^&=+]])"+
                    "(?=\\S+$)"+
                    ".{6,}"+
                    "$");*/
    EditText Edt_Email,Edt_Username,Edt_Pass;
    Button Btn_Cancel,Btn_Ok;
    ImageView Img_Back;
    CheckBox Chexbox;
    RadioButton Radio_Female,Radio_Male;
    Globalclass _Globalclass=new Globalclass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_sign);
        Setdata();
        Init();
    }void Init(){
        Edt_Email= findViewById(R.id.Edt_Email);
        Edt_Username=findViewById(R.id.Edt_Username);
        Edt_Pass=findViewById(R.id.Edt_Pass);
        //deqat konim k baraye edit text ha to in no bayad id ro dakhel edit text bezary n toye textinputlayout:)
        Btn_Cancel= findViewById(R.id.Btn_Cancel);
        Btn_Ok=findViewById(R.id.Btn_Ok);
        Img_Back= findViewById(R.id.Img_Back);
        Chexbox= findViewById(R.id.Chexbox);
        Radio_Female= findViewById(R.id.Radio_Female);
        Radio_Male= findViewById(R.id.Radio_Male);
    }
    public void Sing(View _v){
        try {
            String _Email=Edt_Email.getText().toString().trim();
            String _Username=Edt_Username.getText().toString().trim();
            String _Pass=Edt_Pass.getText().toString().trim();
            boolean _Man=Radio_Male.isChecked();
            if (Chexbox.isChecked()==false){
                Btn_Ok.setEnabled(false);
            }else
                Btn_Ok.setEnabled(true);

            SharedPreferences _SharedPreferences=getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            SharedPreferences.Editor _Editor=_SharedPreferences.edit();
            _Editor.putString("EmailUser",_Email);
            _Editor.putString("NameUser",_Username);
            _Editor.putString("PassUser",_Pass);
            _Editor.putBoolean("Jens",_Man);
            _Editor.apply();
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Sign.this,Error.getMessage());
        }
    }private boolean ValidateEmail(){
        String _Email=Edt_Email.getText().toString().trim();
        if (_Email.isEmpty()){
            Edt_Email.setError("Field can't be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(_Email).matches()){
            Edt_Email.setError("Please enter a valid email address");
            return false;
        }
        else {
            Edt_Email.setError(null);
            return true;
        }
    }private boolean ValidateUsername(){
        String _Username=Edt_Username.getText().toString().trim();
        if (_Username.isEmpty()){
            Edt_Username.setError("Field can't be empty");
            return false;
        }else if (_Username.length()<5){
            Edt_Username.setError("Username too short");
            return false;
        }else if (_Username.length()>15){
            Edt_Username.setError("Username too long");
            return false;
        }
        else {
            Edt_Username.setError(null);
            return true;
        }
    }private boolean ValidatePass(){
        String _Pass=Edt_Pass.getText().toString().trim();
        if (_Pass.isEmpty()){
            Edt_Pass.setError("Field can't be empty");
            return false;
        }/*else if (!PASSWORD_PATTERN.matcher(_Pass).matches()){
            Edt_Pass.setError("Password too weak");
            return false;
        }*/
        else if (_Pass.length()<4){
            Edt_Pass.setError("Password is short");
            return false;
        }
        else {
            Edt_Pass.setError(null);
            return true;
        }
    }public void BtnOk(View _v){
        try {
            if (!ValidateEmail()|!ValidateUsername()|!ValidatePass()){
                return;
            }
            String input="Email: "+Edt_Email.getText().toString();
            input +="\n";
            input +="Username: "+Edt_Username.getText().toString();
            input +="\n";
            input +="Password: "+Edt_Pass.getText().toString();
            Toast.makeText(this,input,Toast.LENGTH_SHORT).show();
            Intent _Intent=new Intent(this,Act_Products.class);
            startActivity(_Intent);
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Sign.this,Error.getMessage());
        }
    }void Setdata(){
        try {
            SharedPreferences _SharedPreferences=getSharedPreferences("SignUser", Context.MODE_PRIVATE);
            Edt_Email.setText(_SharedPreferences.getString("EmailUser",null));
            Edt_Username.setText(_SharedPreferences.getString("NameUser",null));
            Edt_Pass.setText(_SharedPreferences.getString("PassUser",null));
            boolean Jens=_SharedPreferences.getBoolean("Jens",false);
            if (Jens)
                Radio_Male.setChecked(true);
            else
                Radio_Female.setChecked(true);
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Sign.this,Error.getMessage());
        }


    }public void Backclick(View _v){
        if (_v.getTag().equals("Back")){
            finish();
        }
    }public void BtnCancel(View _v){
        Intent _Intent=new Intent(this,MainActivity.class);
        startActivity(_Intent);
    }
}


