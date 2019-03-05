package com.example.pegasus1.a0108_customlistview;

import android.graphics.drawable.Drawable;

public class MyItem {

    private Drawable icon   ;
    private String tv_name     ;
    private String contents ;

    public Drawable getIcon(){
        return icon;
    }//Drawable 클래스를 사용하는 getIcon()이라는 함수 구현

    public void setIcon(Drawable icon){
        this.icon = icon;
    }

    public String getName(){
        return tv_name;
    }

    public void setName(String name){
        this.tv_name = name;
    }

    public String getContents(){
        return contents;
    }

    public void setContents(String contents){
        this.contents = contents;
    }
}
