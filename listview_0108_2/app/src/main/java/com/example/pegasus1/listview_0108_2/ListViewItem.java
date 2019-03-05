package com.example.pegasus1.listview_0108_2;

import android.graphics.drawable.Drawable;

/**this 자바 파일 및 클래스 설명
 *
 * listview_item.xml에 적용되는 클래스를 만든 자바 파일임
 */

public class ListViewItem {

    private Drawable iconDrawable   ;
    private String titleStr ;
    private String descStr  ;

    public void setIcon(Drawable icon){
        iconDrawable = icon;
    }
    public void setTitle(String title){
        titleStr = title;
    }
    public void setDesc(String desc){
        descStr = desc;
    }

    public Drawable getIcon(){
        return this.iconDrawable;
    }
    public String getTitle(){
        return this.titleStr;
    }
    public String getDesc(){
        return this.descStr;
    }


}

/** 이 클래스는 getter, setter 을 지정해 놓은 클래스이다
 *
 * */