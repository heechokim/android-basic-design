package com.anddocstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        /** 이 액티비티가 실행될 때 intent를 가져와서 string을 추출 */
        //intent객체 생성 --> getIntent()생성자로 초기화
        Intent intent = getIntent();

        //string 가저옴
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //xml 파일의 textview 객체 생성
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}
