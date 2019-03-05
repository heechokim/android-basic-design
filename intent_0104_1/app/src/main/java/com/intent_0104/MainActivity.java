package com.intent_0104;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    /** 변수 선언 */
    private EditText editText   ;
    private Button btn          ;


    /** onCreate() */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //변수 초기화 부분
        editText = (EditText)findViewById(R.id.editText);
        btn = (Button)findViewById(R.id.button);

        //버튼 클릭시
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //ACTION_VIEW는 인터넷 어플을 의미하고 Uri.parse는 ( ) 안의 값을 웹 브라우저의 주솟값으로 한다는 것
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(editText.getText().toString()));
                startActivity(intent);
            }
        });

    }
}
