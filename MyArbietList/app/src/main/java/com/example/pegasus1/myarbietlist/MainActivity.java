package com.example.pegasus1.myarbietlist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /** activity_main.xml 뷰 변수 선언 */
    private ListView listView;
    private BootstrapButton registerButton;

    /** 리스트뷰 어댑터 관련 */
    private Adapter adapter;


    /** 공통 변수 선언 */
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 공통 변수 초기화 */
        mContext = this;

        /* activity_main.xml 뷰 변수 초기화 */
        listView = (ListView)findViewById(R.id.myArbietList);
        registerButton = (BootstrapButton)findViewById(R.id.registerBtn);

        /* 어댑터 선언 및 커스텀 뷰 데이터 설정 */
        adapter = new Adapter(mContext);
        adapter.addItem("꼬끼꼬끼", "2019-02-04 16:30","2019-02-04 ~ 2019-02-25", "5",false );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",true );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",false );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",false );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",true );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",true );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",true );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",false );
        adapter.addItem("카페 그리다","2019-02-10 12:00","2019-02-10 ~ 2019-03-05", "1",false );

        /* 어댑터 set */
        listView.setAdapter(adapter);


        /* 아르바이트생 공고글 추가 버튼 클릭 리스너 */
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ㅎㅇ", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
