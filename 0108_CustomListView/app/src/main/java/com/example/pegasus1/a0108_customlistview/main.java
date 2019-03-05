package com.example.pegasus1.a0108_customlistview;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class main extends AppCompatActivity {

    private TextView textView   ;
    private ListView listView   ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listView);

        dataSetting();
    }

    private void dataSetting(){


        MyAdapter mMyAdapter = new MyAdapter();

        for (int i=0; i<10; i++) {
            mMyAdapter.addItem(ContextCompat.getDrawable(getApplicationContext(), R.drawable.icon2), "name_" + i, "contents_" + i);
        }

        listView.setAdapter(mMyAdapter);

    }
            }
