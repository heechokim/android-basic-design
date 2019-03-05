package com.example.pegasus1.a0108_3_listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class main extends AppCompatActivity {

    private String [] list  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new String[]{"사과", "배", "딸기", "수박", "참외", "파인애플", "포도", "바나나", "키위", "귤", "망고"};

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

    }
}
