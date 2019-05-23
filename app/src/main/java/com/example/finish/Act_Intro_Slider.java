package com.example.finish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
public class Act_Intro_Slider extends AppCompatActivity {
    ViewPager Viw_Pager;
    LinearLayout layoutDot;
    TextView[] dotstv;
    Button Btn_Next,Btn_Skip;
    int[]layouts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isFirstTimeStartApp()){
            StartMainActivity();
            finish();
        }

        MyPagerAdapter pagerAdapter;
        setstatusBar();
        setContentView(R.layout.intro_slider);
        Viw_Pager=(ViewPager)findViewById(R.id.Viw_Pager);
        layoutDot=(LinearLayout) findViewById(R.id.layoutDot);
        Btn_Next=(Button) findViewById(R.id.Btn_Next);
        Btn_Skip=(Button) findViewById(R.id.Btn_Skip);

        //when user press skip start main activity
        Btn_Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartMainActivity();
            }
        });
        Btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPage=Viw_Pager.getCurrentItem()+1;
                if (currentPage<layouts.length){
                    //move to nextpage
                    Viw_Pager.setCurrentItem(currentPage);
                }else {
                    StartMainActivity();
                }
            }
        });
        layouts = new int[]{R.layout.introslider_1, R.layout.introslider_2 ,R.layout.introslider_3, R.layout.introslider_4};
        pagerAdapter=new MyPagerAdapter(layouts,getApplicationContext());
        Viw_Pager.setAdapter(pagerAdapter);
        Viw_Pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==layouts.length-1){
                    Btn_Next.setText("Got It");
                    Btn_Skip.setVisibility(View.GONE);

                }else {
                    Btn_Next.setText("NEXT");
                    Btn_Skip.setVisibility(View.VISIBLE);

                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);
    }// Dar isFirstTimeStartApp hastesh k ma migim bare aval hast ya n va toye sharedprefrence zakhire mikonim
    //va aval baename migim k afg bare aval nistesh bere main activity(Asan esmesh roshe firsttimestartapp yani bare aval k
    // dari barname ro run mikoni)
    private boolean isFirstTimeStartApp(){
        SharedPreferences ref=getApplicationContext().getSharedPreferences("IntroSlider", Context.MODE_PRIVATE);
        return ref.getBoolean("FirsTimeStartFlag",true);
    }
    private void setFirstTimeStartStatus(boolean stt){
        SharedPreferences ref=getApplicationContext().getSharedPreferences("IntroSlider", Context.MODE_PRIVATE);
        SharedPreferences.Editor _Editor=ref.edit();
        _Editor.putBoolean("FirsTimeStartFlag",stt);
        _Editor.commit();
    }
    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotstv=new TextView[layouts.length];
        for (int i=0;i<dotstv.length;i++){
            dotstv[i]=new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#a9b4bb"));
            layoutDot.addView(dotstv[i]);

        }
        //Set current dot active
        if (dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#ffffff"));
        }
    }
    private void StartMainActivity(){
        //tike code paiin vase ine k slider faqat baraye bare aval k karbar app ro baz
        // mikone namayesh dade beshe k bayaf (false) bezarim
        setFirstTimeStartStatus(false);
        Intent _Intent=new Intent(this,MainActivity.class);
        startActivity(_Intent);
        finish();
    }
    private void setstatusBar(){
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window _Window=getWindow();
            _Window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            _Window.setStatusBarColor(Color.TRANSPARENT);
        }
    }



}

