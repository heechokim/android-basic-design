package com.example.pegasus1.push;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    /* acitivity_main2 뷰 변수 선언 */
    private TextView yourtitle  ;
    private TextView yourtitle2 ;
    private TextView yourcontent;
    private TextView yourcontent2;
    private ImageView yourImage;

    /* 넘겨 받을 intent 변수 선언 */
    private Intent intent   ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /** acitivity_main2 뷰 변수 초기화 */
        yourtitle = (TextView)findViewById(R.id.yourTitle);
        yourtitle2 = (TextView)findViewById(R.id.yourTitle2);
        yourcontent = (TextView)findViewById(R.id.yourContent);
        yourcontent2 = (TextView)findViewById(R.id.yourContent2);
        yourImage = (ImageView)findViewById(R.id.yourImage);


        /** MainActivity 에서 intent로 넘긴 데이터 받기 */
        intent = getIntent();
        yourtitle2.setText(intent.getStringExtra("title"));
        yourcontent2.setText(intent.getStringExtra("content"));
        yourImage.setImageResource(R.drawable.pushimage);
        Log.d("chohdd ; ", "들어옴4");

    }
}
