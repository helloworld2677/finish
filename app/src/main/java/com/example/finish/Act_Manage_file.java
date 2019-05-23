package com.example.finish;

import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class Act_Manage_file extends AppCompatActivity {
    EditText Edt_Namef,Edt_Rename;
    Button Btn_Create,Btn_Delete,Btn_Rename;
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mToggle;
    Globalclass _Globalclass=new Globalclass();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_manage_file);
        Init();
    }void Init(){
        Edt_Namef=(EditText)findViewById(R.id.Edt_Namef);
        Edt_Rename=(EditText)findViewById(R.id.Edt_Rename);
        Btn_Create=(Button) findViewById(R.id.Btn_Create);
        Btn_Delete=(Button)findViewById(R.id.Btn_Delete);
        Btn_Rename=(Button)findViewById(R.id.Btn_Rename);
        Drawer=(DrawerLayout)findViewById(R.id.Drawer);
        mToggle=new ActionBarDrawerToggle(this,Drawer,R.string.Open,R.string.Close);
        Drawer.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }public void BtnClick(View _v){
        try {
            File _File=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+Edt_Namef.getText().toString());
            if (_v.getTag().equals("Create_Folder")){
                if (_File.exists())
                    _Globalclass.DisPlayToast(Act_Manage_file.this,"File Exists");
                else
                    _File.mkdir();
                _Globalclass.DisPlayToast(Act_Manage_file.this,"Create Folder");
            }else if (_v.getTag().equals("Delete_Folder")){
                if (!_File.exists())
                    _Globalclass.DisPlayToast(Act_Manage_file.this,"No Folder");
                else
                    _File.delete();
                _Globalclass.DisPlayToast(Act_Manage_file.this,"Delete Folder");
            }else if (_v.getTag().equals("Rename_Folder")){
                File _RenameFile=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+Edt_Rename.getText().toString());
                _File.renameTo(_RenameFile);
            }
        }catch (Exception Error){
            _Globalclass.DisPlayToast(Act_Manage_file.this,Error.getMessage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}


