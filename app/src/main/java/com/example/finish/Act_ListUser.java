package com.example.finish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Act_ListUser extends AppCompatActivity {
    String[] ListUser=new String[5];
    int[] ListUser_pic=new int[5];
    LinearLayout Liner_ListUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_list_user);
        LoadData();
        Liner_ListUser=findViewById(R.id.Liner_ListUser);
    }void LoadData(){
        ListUser[0]="User1";
        ListUser[1]="User2";
        ListUser[2]="User3";
        ListUser[3]="User4";
        ListUser[4]="User5";

        //ListUser_pic[0]=R.drawable.bb;
        ListUser_pic[0]=R.mipmap.ic_launcher;
        ListUser_pic[1]=R.mipmap.ic_launcher;
        ListUser_pic[2]=R.mipmap.ic_launcher;
        ListUser_pic[3]=R.mipmap.ic_launcher;
        ListUser_pic[4]=R.mipmap.ic_launcher;

       /* for (String Txt:ListUser){
            Log.i("PrintListUser",Txt);
        }*/
        for (int i=0;i<ListUser.length;i++){
            View _v=View.inflate(Act_ListUser.this,R.layout.row_user,null);
            TextView Text_User=_v.findViewById(R.id.Text_User);
            ImageView Img_User=_v.findViewById(R.id.Img_User);
            Img_User.setImageResource(ListUser_pic[i]);
            Text_User.setText(ListUser[i]);
            Liner_ListUser.addView(_v);
            Log.i("PrintListUser",ListUser[i]);


        }

    }View.OnClickListener BtnClick=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //v.getTag().toString()
            //Toast
        }
    };
}