package com.example.pegasus1.parttimejob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ComfirmActivity extends AppCompatActivity {

    /** activity_confirm.xml 뷰 변수 선언 */
    private TextView period;            // 모집기간
    private TextView number;            // 모집인원
    private TextView gender;            // 성별
    private TextView age;               // 나이
    private TextView favor;             // 우대사항
    private TextView graduate;          // 학력

    /** Intent 관련 변수 선언 */
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);


        /* activity_confirm.xml 뷰 변수 초기화 */
        period = (TextView)findViewById(R.id.tv_period);
        number = (TextView)findViewById(R.id.tv_number);
        gender = (TextView)findViewById(R.id.tv_gender);
        age = (TextView)findViewById(R.id.tv_age);
        favor = (TextView)findViewById(R.id.tv_favor);
        graduate = (TextView)findViewById(R.id.tv_graduate);

        /* 뷰 변수에 데이터 세팅 */
        intent = getIntent();
        period.setText(intent.getStringExtra("모집기간"));
        graduate.setText(intent.getStringExtra("학력"));
        number.setText(intent.getStringExtra("인원"));
        if(intent.getStringExtra("성별").equals("M")){
            gender.setText("남");
        }else{
            gender.setText("여");
        }

        age.setText(intent.getStringExtra("연령"));
        favor.setText(intent.getStringExtra("우대사항"));
    }
}
