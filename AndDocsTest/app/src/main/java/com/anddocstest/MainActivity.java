package com.anddocstest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /** public 변수 선언*/
    public static final String EXTRA_MESSAGE = "com.anddocstest.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    ** send 버튼 눌렀을 때 돌아가는 함수 */
    public void sendMessage(View view) {

        //intent 객체 생성 --> 현재 엑티비티와 타겟 엑티비티 지정해줘야 함
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        //xml에 있는 edittext를 이용하기 위해 EditText 객체 생성 --> 자바파일과 xml 파일은 연동 안 되어 있음
        EditText editText = (EditText) findViewById(R.id.editText3);

        //EditText 객체의 메소드를 사용해서 string 얻기 --> 변수화
        String message = editText.getText().toString();

        //intent로 액티비티를 바인딩 시킬 때 데이터를 전달하는 함수인 putExtra
        intent.putExtra(EXTRA_MESSAGE, message);

        //바인딩 시작
        startActivity(intent);
    }/
}


