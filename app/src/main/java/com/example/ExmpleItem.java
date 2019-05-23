package com.example;

public class ExmpleItem {
    private int mImageResource;
    private String mText1;
    private String mText2;
    public ExmpleItem(int imageResource,String Text1,String Text2){
        mImageResource = imageResource;
        mText1 = Text1;
        mText2 = Text2;
    }public int getImageResource(){
        return mImageResource;
    }public String getText1(){
        return mText1;
    }public String getText2(){
        return mText2;
    }
}
