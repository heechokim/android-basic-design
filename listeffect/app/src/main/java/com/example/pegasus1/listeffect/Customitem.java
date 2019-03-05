package com.example.pegasus1.listeffect;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class Customitem extends AppCompatActivity {


    /* activity_listview_item.xml 뷰 객체 변수 선언 */
    private TextView textView   ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_item);

        textView = (TextView)findViewById(R.id.textView);
    }
}
