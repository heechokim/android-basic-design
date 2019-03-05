package com.example.pegasus1.myarbietlist;

public class MyItem {

    private String store;
    private String date;
    private String period;
    private String peopleNum;
    private Boolean check;

    /* 데이터 set함수 */
    public void setStore(String string){
        store = string;
    }

    public void setDate(String string){
        date = string;
    }

    public void setPeriod(String string){
        period= string;
    }

    public void setPeopleNum(String string){
        peopleNum = string;
    }

    public void setCheck(Boolean check){
        this.check = check;
    }

    /* 데이터 get함수 */
    public String getStore(){
        return store;
    }

    public String getDate(){
        return date;
    }

    public String getPeriod(){
        return period;
    }

    public String getPeopleNum(){
        return peopleNum;
    }

    public Boolean getCheck(){
        return check;
    }
}
