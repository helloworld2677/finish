package com.example.finish;

import android.support.v4.view.PagerAdapter;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class MyPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private int[] layouts;
    private Context Ctx;

    public MyPagerAdapter(int[] layouts, Context ctx) {
        this.layouts = layouts;
        Ctx = ctx;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;

    }  @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater=(LayoutInflater)Ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View _v= inflater.inflate(layouts[position],container,false);
        container.addView(_v);
        return _v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View _v=(View)object;
        container.removeView(_v);
    }
}

